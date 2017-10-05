package facturatron.lib;

import facturatron.lib.entities.ComprobanteTron;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Iterator;
import mx.bigdata.sat.cfdi.v33.schema.CTipoDeComprobante;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante.Emisor;

/**
 * Clase encargada de la generación del reporte mensual de acuerdo al anexo 20.
 * IMPORTANTE: 1) No implementa los campos: pedimento, fecha de pedimento ni
 * aduana 2) No es reponsabilidad de ésta clase obtener los comprobantes desde
 * dónde estén almacenados, asímismo no es su reponsabilidad asegurar que los
 * comprobantes otorgados sean del mes correspondiente, eso debe comprobarse
 * antes de pasarlerselos
 *
 * @author Octavio Ruiz @ Phesus
 */
public class InformeMensual {

    private ArrayList<EnumMap<Campos, String>> dataMap;
    private Emisor emisor;
    private Calendar periodo;
    private Esquema esquema;

    /**
     * Define en ordenadamente (secuencialmente) los campos del reporte
     */
    public enum Campos {

        RFC, SERIE, FOLIO, NO_APROBACION, FECHA, TOTAL, IMPUESTOS, EDO_DEL_COMPROBANTE, TIPO_COMPROBANTE, PEDIMIENTO,
        FECHA_PEDIMIENTO, ADUANA;
    }

    /**
     * CFD = Comprobantes Fiscales Digitales COMPROBANTESSOLICITADOS =
     * (Comprobante semi-tradicional) Comprobantes solicitados por medio de un
     * establecimiento autorizado
     */
    public enum Esquema {

        CFD, COMPROBANTESSOLICITADOS;
    }

    /**
     * @return the dataMap
     */
    private ArrayList<EnumMap<Campos, String>> getDataMap() {
        return dataMap;
    }

    /**
     * @return the emisor
     */
    private Emisor getEmisor() {
        return emisor;
    }

    /**
     * @param emisor the emisor to set
     */
    private void setEmisor(Emisor emisor) {
        this.emisor = emisor;
    }

    /**
     * @return the periodo
     */
    private Calendar getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    private void setPeriodo(Calendar periodo) {
        this.periodo = periodo;
    }

    private String getTXTFileDestination(String dir) throws Exception {
        String nombre;

        File file = new File(dir);
        if (!file.exists()) {
            throw new Exception("La carpeta destino no exíste");
        }
        nombre = dir;
        nombre += String.valueOf(esquema.ordinal() + 1);
        nombre += emisor.getRfc();
        nombre += String.valueOf(periodo.get(Calendar.MONTH) + 1);
        nombre += String.valueOf(periodo.get(Calendar.YEAR));
        nombre += ".txt";

        return nombre;
    }

    /**
     * @return the esquema
     */
    private Esquema getEsquema() {
        return esquema;
    }

    /**
     * @param esquema the esquema to set
     */
    private void setEsquema(Esquema esquema) {
        this.esquema = esquema;
    }

    /**
     * Constructor del reporte mensual
     *
     * @param esquema Ver documentación del tipo Esquema
     * @param emisor
     * @param periodo Sólo se tomarán en cuenta el año y mes del objeto Date
     */
    public InformeMensual(Esquema esquema, Emisor emisor, Calendar periodo) {
        dataMap = new ArrayList<EnumMap<Campos, String>>();
        setEmisor(emisor);
        setPeriodo(periodo);
        setEsquema(esquema);
    }

    public void cargarComprobantes(ArrayList<ComprobanteTron> comps) throws Exception {
        for (ComprobanteTron comprobante : comps) {
            addConcepto(comprobante);
        }
    }

    private void addConcepto(ComprobanteTron comp) throws Exception {
        EnumMap<Campos, String> renglon = new EnumMap<Campos, String>(Campos.class);

        String edoComprobante = String.format("%s", comp.isEstadoComprobante() ? "1" : "0");
        String tipoComprobante = "";

        if (comp.getTipoDeComprobante() == CTipoDeComprobante.I) {
            tipoComprobante = "I";
        } else if (comp.getTipoDeComprobante() == CTipoDeComprobante.E) {
            tipoComprobante = "E";
        } else if (comp.getTipoDeComprobante() == CTipoDeComprobante.T) {
            tipoComprobante = "T";
        } else {
            throw new Exception("Tipo de comprobante desconocido");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaHora = formatter.format(comp.getFecha());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(comp.getFecha().toGregorianCalendar().getTime());
        //Utilizo String.format en lugar de conversión o casting porqué en éste tipo de reportes suele necesitarse
        //  modificar el formato de algunos campos o llenarlos de ceros, etc.
        String serie = comp.getSerie();
        if(serie != null) serie = serie.toUpperCase();
        if(serie == null) serie = "";
        renglon.put(Campos.RFC, comp.getReceptor().getRfc());
        renglon.put(Campos.SERIE, serie);
        renglon.put(Campos.FOLIO, comp.getFolio());
        renglon.put(Campos.FECHA, fechaHora);
        renglon.put(Campos.TOTAL, String.format("%.2f", comp.getTotal()));
        renglon.put(Campos.IMPUESTOS, String.format("%.2f", comp.getImpuestos().getTotalImpuestosTrasladados()));
        renglon.put(Campos.EDO_DEL_COMPROBANTE, edoComprobante);
        renglon.put(Campos.TIPO_COMPROBANTE, tipoComprobante);
        renglon.put(Campos.PEDIMIENTO, "");
        renglon.put(Campos.FECHA_PEDIMIENTO, "");
        renglon.put(Campos.ADUANA, "");

        if (!comp.isEstadoComprobante()) {
            comp.setEstadoComprobante(true);
            addConcepto(comp);
        }
        getDataMap().add(renglon);
    }

    /**
     * Método fundamental que entrega en el sistema de archivos en formato TXT
     * el reporte con su debido nombre. Notar que rutaTXT debe ser la ruta al
     * DIRECTORIO (CARPETA) y no el nombre del archivo destino, éste nombre lo
     * generará el método de acuerdo a lo requerido en el anexo 20.
     *
     * @param rutaTXT debe ser la ruta al DIRECTORIO (CARPETA) y no el nombre
     * del archivo destino, éste nombre lo generará el método de acuerdo a lo
     * requerido en el anexo 20.
     * @return El nombre del archivo creado.
     * @throws IOException
     * @throws Exception
     */
    public String toTXTFile(String rutaTXT) throws IOException, Exception {
        if (getDataMap().isEmpty()) {
            throw new Exception("No se han proporcionado comprobantes. Si no los hay en éste periódo probablemente debería presentar otro tipo de reporte");
        }

        String file = getTXTFileDestination(rutaTXT);
        FileWriter fstream = new FileWriter(file, false);
        BufferedWriter out = new BufferedWriter(fstream);

        for (EnumMap<Campos, String> renglon : getDataMap()) {
            out.write("|");
            out.write(join((AbstractCollection<String>) renglon.values(), "|"));
            out.write("|");
            out.write('\r');
            out.write('\n');
        }
        out.close();
        return file;
    }

    /**
     * Función estática pública para el manejo
     *
     * @param s
     * @param delimiter
     * @return
     */
    public static String join(AbstractCollection<String> s, String delimiter) {
        if (s.isEmpty()) {
            return "";
        }
        Iterator<String> iter = s.iterator();
        StringBuffer buffer = new StringBuffer(iter.next());
        while (iter.hasNext()) {
            buffer.append(delimiter);
            buffer.append(iter.next());
        }
        return buffer.toString();
    }
}
