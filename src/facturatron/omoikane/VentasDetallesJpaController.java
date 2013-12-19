/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import facturatron.omoikane.exceptions.NonexistentEntityException;
import facturatron.omoikane.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import java.util.List;
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
        if (ventasDetalles.getVentasDetallesPK() == null) {
            ventasDetalles.setVentasDetallesPK(new VentasDetallesPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ventasDetalles);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVentasDetalles(ventasDetalles.getVentasDetallesPK()) != null) {
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
                VentasDetallesPK id = ventasDetalles.getVentasDetallesPK();
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

    public void destroy(VentasDetallesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VentasDetalles ventasDetalles;
            try {
                ventasDetalles = em.getReference(VentasDetalles.class, id);
                ventasDetalles.getVentasDetallesPK();
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

    public List<VentasDetalles> findByVenta(Integer idVenta) {
        EntityManager em = getEntityManager();
        try {

            Query q = em.createNamedQuery("VentasDetalles.findByIdVenta");
            q.setParameter("idVenta", idVenta);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VentasDetalles findVentasDetalles(VentasDetallesPK id) {
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
    
    public SumaVentas sumaVentasDetalles(int desdeID, int hastaID) {
        EntityManager em = getEntityManager();
        try {

            TypedQuery<Double> q = em.createNamedQuery("VentasDetalles.sumImpuestosIntervalOfIDs", Double.class);
            q.setParameter("idinicial", desdeID);
            q.setParameter("idfinal"  , hastaID);
            Double sumaImpuestos = q.getSingleResult();
            
            q = em.createNamedQuery("VentasDetalles.sumTotalIntervalOfIDs", Double.class);
            q.setParameter("idinicial", desdeID);
            q.setParameter("idfinal"  , hastaID);
            Double sumaTotal = q.getSingleResult();
            
            Double sumaSubtotal = sumaTotal - sumaImpuestos;

            SumaVentas sumaVentas = new SumaVentas(sumaSubtotal, sumaImpuestos, sumaTotal);
            return sumaVentas;
        } finally {
            em.close();
        }
    }
    public class SumaVentas {
        public SumaVentas(Double s, Double i, Double t) {
            total     = new BigDecimal( t );
            subtotal  = new BigDecimal( s );
            impuestos = new BigDecimal( i );
        } 
        public BigDecimal total, impuestos, subtotal;
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb
                    .append("[Subtotal: ").append(subtotal).append(", ")
                    .append("Impustos: ").append(impuestos).append(", ")
                    .append("Total: ").append(total)
                    .append("]");
            return sb.toString();
        }
    }

}
