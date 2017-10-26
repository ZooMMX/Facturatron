/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.Dominio;

/**
 *
 * @author octavioruizcastillo
 */
public enum FormaDePago {
    EFECTIVO ("01", "Efectivo"),
    CHEQUE ("02", "Cheque nominativo"),
    TRANSFERENCIA ("03", "Transferencia electrónica de fondos"),
    TDC ("04", "Tarjeta de crédito"),
    MONEDERO_E ("05", "Monedero electrónico"),
    DINERO_E ("06", "Dinero electrónico"),
    VALES ("08", "Vales de despensa"),
    DACION ("12", "Dación en pago"),
    SUBROGACION ("13", "Pago por subrogación"),
    CONSIGNACION ("14", "Pago por consignación"),
    CONDONACION ("15", "Condonación"),
    COMPENSACION ("17", "Compensación"),
    NOVACION ("23", "Novación"),
    CONFUSION ("24", "Confusión"),
    REMISION_DE_DEUDA ("25", "Remisión de deuda"),
    PRESCRIPCION ("26", "Prescripción o caducidad"),
    A_SATISFACCION_ACREEDOR ("27", "A satisfacción del acreedor"),
    TDD ("28", "Tarjeta de débito"),
    TDS ("29", "Tarjeta de servicios"),
    APLICAION_ANTICIPOS ("30", "Aplicación de anticipos"),
    POR_DEFINIR ("99", "Por definir")
    ;

    private String texto;
    private String valorSat;

    FormaDePago(String valSat, String texto) {
        this.valorSat = valSat;
        this.texto = texto; 
    }

    public String toString() {
        return texto;
    }

    public String toSatConstant() {
        return valorSat;
    }
    
    public static FormaDePago fromSatConstant(String text) {
        for (FormaDePago b : FormaDePago.values()) {
          if (b.valorSat.equalsIgnoreCase(text)) {
            return b;
          }
        }
        return null;
    }
}
