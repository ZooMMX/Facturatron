/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import facturatron.datasource.RenglonTicket;
import facturatron.datasource.Ticket;
import facturatron.datasource.omoikane.TicketOmoikane;
import facturatron.omoikane.exceptions.NonexistentEntityException;
import facturatron.omoikane.exceptions.PreexistingEntityException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author octavioruizcastillo
 */
public class VentasDetallesJpaController extends JpaController {

    public VentasDetallesJpaController() { super(); }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VentasDetalles ventasDetalles) throws PreexistingEntityException, Exception {
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ventasDetalles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVentasDetalles(ventasDetalles.getIdRenglon()) != null) {
                throw new PreexistingEntityException("VentasDetalles " + ventasDetalles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VentasDetalles ventasDetalles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ventasDetalles = em.merge(ventasDetalles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ventasDetalles.getIdRenglon();
                if (findVentasDetalles(id) == null) {
                    throw new NonexistentEntityException("The ventasDetalles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VentasDetalles ventasDetalles;
            try {
                ventasDetalles = em.getReference(VentasDetalles.class, id);
                ventasDetalles.getIdRenglon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ventasDetalles with id " + id + " no longer exists.", enfe);
            }
            em.remove(ventasDetalles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VentasDetalles> findVentasDetallesEntities() {
        return findVentasDetallesEntities(true, -1, -1);
    }

    public List<VentasDetalles> findVentasDetallesEntities(int maxResults, int firstResult) {
        return findVentasDetallesEntities(false, maxResults, firstResult);
    }

    private List<VentasDetalles> findVentasDetallesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VentasDetalles.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<VentasDetalles> findByVenta(Long idVenta) {
        EntityManager em = getEntityManager();
        try {

            Query q = em.createNamedQuery("VentasDetalles.findByIdVenta");
            q.setParameter("idVenta", idVenta);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VentasDetalles findVentasDetalles(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VentasDetalles.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentasDetallesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VentasDetalles> rt = cq.from(VentasDetalles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Ticket> getIdsNoFacturados(String desde, String hasta) {
        EntityManager em = getEntityManager();
        
        try {

            Query q = em.createNativeQuery(
                    "		SELECT \n" +
                    "			v.id_almacen, v.id_caja, v.id_venta, v.folio, v.total, vd.id_renglon, vdi.descripcion, vdi.base, vdi.impuestoId, vdi.porcentaje, vdi.total\n" +
                    "		FROM \n" +
                    "			ventas v, \n" +
                    "			ventas_detalles vd, " +
                    "                   ventas_detalles_impuestos vdi \n" +
                    "		WHERE \n" +
                    "				v.id_venta = vd.id_venta \n" +
                    "                   AND \n"+ 
                    "                           vd.id_renglon = vdi.id_renglon \n" +
                    "			AND\n" +
                    "				v.facturada = 0\n" +
                    "			AND\n" +
                    "				vd.id_linea NOT IN (SELECT id_linea FROM lineas_dual)\n" +
                    "			AND \n" +
                    "				v.fecha_hora BETWEEN ?1 AND ?2  \n" +
                    "           ORDER BY v.id_caja ASC \n" 
                    );
            
            q.setParameter(1, desde);
            q.setParameter(2, hasta);
            List<Object[]> ventas = q.getResultList();
            if(ventas==null||ventas.isEmpty())
                return new ArrayList<>();
            return armarTickets(ventas);
        } finally {
            em.close();
        }
    }
    
    public List<Ticket> armarTickets(List<Object[]> ventas){
        TicketOmoikane ticket=new TicketOmoikane();
        List<Ticket> tickets= new ArrayList<>();
        BigDecimal importeImpuesto=new BigDecimal("0.00");
        
            for (Object[] idEnGlobal : ventas) {
            //Construyo el formato por defecto del ID de ticket de Omoikane
            String id = ((Integer) idEnGlobal[0]).toString() + "-" +
                        ((Integer) idEnGlobal[1]).toString() + "-" +
                        ((Integer) idEnGlobal[2]).toString();
            
            //Construyo el formato por defecto del folio de ticket de Omoikane
            String folio = ((Integer) idEnGlobal[0]).toString() + "-" +
                           ((Integer) idEnGlobal[1]).toString() + "-" +
                           ((BigInteger) idEnGlobal[3]).toString();

            
            BigDecimal importe = new BigDecimal((Double) idEnGlobal[4]);
            importe.setScale(2,RoundingMode.HALF_EVEN);

            /*
            [0] v.id_almacen, 
            [1] v.id_caja, 
            [2] v.id_venta, 
            [3] v.folio, 
            [4] v.total, 
            [5] vd.id_renglon, 
            [6] vdi.descripcion, 
            [7] vdi.base, 
            [8] vdi.impuestoId, 
            [9] vdi.porcentaje, 
            [10] vdi.total
            */
            
            if(ticket.getId()!=null&&!ticket.getId().contentEquals(id)){
                tickets.add(ticket);
                ticket=new TicketOmoikane();
            }
            
            ticket.setId(id);
            ticket.setFolio(folio);
            ticket.setImporte(importe); 
            
            importeImpuesto = new BigDecimal(idEnGlobal[10].toString());
            importeImpuesto.setScale(2, RoundingMode.HALF_EVEN);
            
            if(!ticket.getImportesImpuestos().containsKey(idEnGlobal[6].toString()))
                    ticket.getImportesImpuestos().put(idEnGlobal[6].toString(), BigDecimal.ZERO);
            ticket.getImportesImpuestos().put(idEnGlobal[6].toString(), ticket.getImportesImpuestos().get(idEnGlobal[6].toString()).add(importeImpuesto));
        }
        tickets.add(ticket);
        return tickets;
    }
    
    public List<SumaVentas> sumaVentasDetalles(String desde, String hasta) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery(
                    "select\n" +
                    "	imps.imps, \n" +
                    "	sum(imps.subtotal*imps.factor),\n" +
                    "	sum(imps.descuento*imps.factor)\n" +
                    "	FROM (\n" +
                    "		select \n" +
                    "		vd.id_renglon, \n" +
                    "		if(\n" +
                    "			v.facturada = 0, \n" +
                    "				if(\n" +
                    "					EXISTS(SELECT id_linea FROM lineas_dual WHERE id_linea = vd.id_linea),\n" +
                    "					0,\n" +
                    "					1\n" +
                    "				), \n" +
                    "				if(\n" +
                    "					EXISTS(SELECT id_linea FROM lineas_dual WHERE id_linea = vd.id_linea),\n" +
                    "					-1,\n" +
                    "					0\n" +
                    "				)\n" +
                    "			) factor,\n" +
                    "		vd.subtotal subtotal,\n" +
                    "		vd.descuento descuento,\n" +
                    "		GROUP_CONCAT(vdi.impuestoId ORDER BY vdi.impuestoId ASC) imps \n" +
                    "	    FROM \n" +
                    "	    ventas v, \n" +
                    "	    	ventas_detalles vd \n" +
                    "	        LEFT JOIN ventas_detalles_impuestos vdi ON vd.id_renglon = vdi.id_renglon  \n" +
                    "	        WHERE \n" +
                    "	        	v.id_venta = vd.id_venta \n" +
                    "	        AND \n" +
                    "	        	v.fecha_hora BETWEEN ?1 AND ?2  \n" +
                    "	        	GROUP BY vd.id_renglon\n" +
                    "	        ) imps \n" +
                    "	GROUP BY imps.imps;"
            );
            q.setParameter(1, desde);
            q.setParameter(2, hasta);
            List<Object[]> ventas = q.getResultList();
            ImpuestoJpaController impuestoJpa = new ImpuestoJpaController();
            List<SumaVentas> gruposSumas = new ArrayList();
            
            for (Object[] grupoVentas : ventas) {
                try {
                    List<Impuesto> impuestosList = new ArrayList<Impuesto>();
                    
                    //Si hay impuestos los definimos
                    if(grupoVentas[0] != null) {
                        String grupoImpuestos;
                        //Grupoventas puede venir de la BD como String o como Byte[], aquí se ocupa de ambos casos
                        if(grupoVentas[0] instanceof byte[])
                            grupoImpuestos = new String((byte[]) grupoVentas[0], "US-ASCII");
                        else
                            grupoImpuestos = (String) grupoVentas[0];
                        for (String stringIdImpuesto : grupoImpuestos.split(",")) {
                            Long idImpuesto = Long.valueOf(stringIdImpuesto);
                            Impuesto i = impuestoJpa.findImpuesto(idImpuesto);
                            impuestosList.add(i);
                        }      
                    } 
                    SumaVentas sm = new SumaVentas(
                            new BigDecimal((Double)grupoVentas[1]), 
                            new BigDecimal((Double)grupoVentas[2]), 
                            impuestosList);
                    gruposSumas.add(sm);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(VentasDetallesJpaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return gruposSumas;
        } finally {
            em.close();
        }
    }
    
    public class SumaVentas {
        
        final public List<Impuesto> impuestos;
        public List<Long> ids;
        public SumaVentas(BigDecimal subtotal, BigDecimal descuentos, List<Impuesto> impuestos) {
            this.impuestos = impuestos;
            this.descuentos = descuentos;
            this.subtotal  = subtotal;
        } 
        public BigDecimal descuentos, subtotal;
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb
                    .append("[Subtotal: ").append(subtotal).append(", ")
                    .append("Impustos: ").append(impuestos).append(", ")
                    .append("Descuentos: ").append(descuentos)
                    .append("]");
            return sb.toString();
        }
                
    }

}
