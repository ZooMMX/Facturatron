/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturatron.omoikane;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import facturatron.datasource.DatasourceException;
import facturatron.omoikane.exceptions.NonexistentEntityException;
import facturatron.omoikane.exceptions.PreexistingEntityException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author octavioruizcastillo
 */
public class VentasJpaController extends JpaController {

    public VentasJpaController() {
        super();
    }

    public EntityManager getEntityManager() throws PersistenceException {
        return emf.createEntityManager();
    }

    public void create(Ventas ventas) throws PreexistingEntityException, Exception {
        if (ventas.getVentasPK() == null) {
            ventas.setVentasPK(new VentasPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ventas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVentas(ventas.getVentasPK()) != null) {
                throw new PreexistingEntityException("Ventas " + ventas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ventas ventas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ventas = em.merge(ventas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VentasPK id = ventas.getVentasPK();
                if (findVentas(id) == null) {
                    throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ventas> findVentasEntities() throws CommunicationsException {
        return findVentasEntities(true, -1, -1);
    }

    public List<Ventas> findVentasEntities(int maxResults, int firstResult) throws CommunicationsException {
        return findVentasEntities(false, maxResults, firstResult);
    }

    private List<Ventas> findVentasEntities(boolean all, int maxResults, int firstResult) throws CommunicationsException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ventas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (PersistenceException ex) {
            throw ex;
        } finally {
            if(em != null) em.close();
        }
    }

    public Ventas findVentas(VentasPK id) throws PersistenceException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            return em.find(Ventas.class, id);
        } finally {
            if(em != null) em.close();
        }
    }

    public int getVentasCount() throws PersistenceException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ventas> rt = cq.from(Ventas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if(em != null) em.close();
        }
    }
    
    public int[] getIDIntervalFromDay(Date dayIni, Date dayFin) throws DatasourceException {
        int[] intervalo = {0,0};
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q1 = em.createQuery("SELECT min(v.ventasPK.idVenta) FROM Ventas v WHERE v.fechaHora between :dayIni and :dayFin", Integer.class);
            Query q2 = em.createQuery("SELECT max(v.ventasPK.idVenta) FROM Ventas v WHERE v.fechaHora between :dayIni and :dayFin", Integer.class);
                        
            q1.setParameter("dayIni", dayIni);
            q1.setParameter("dayFin", dayFin);
            
            q2.setParameter("dayIni", dayIni);
            q2.setParameter("dayFin", dayFin);
            
            if(q1.getSingleResult() == null || q2.getSingleResult() == null)
                throw new DatasourceException("No se encontraron ventas para facturar");
            
            intervalo[0] = ((Integer) q1.getSingleResult()).intValue();
            intervalo[1] = ((Integer) q2.getSingleResult()).intValue();
            
            return intervalo;
        } catch (PersistenceException ex) {
            throw new DatasourceException("No hay comunicación con el origen de datos o está mal configurado", ex);
        } finally {
            if(em != null) em.close();
        }        
    }

}
