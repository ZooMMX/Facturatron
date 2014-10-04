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

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ventas);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            if (findVentas(ventas.getId()) != null) {
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
        } catch (PersistenceException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ventas.getId();
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
    
    public void editMany(List<Ventas> ventasList) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        Ventas v = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            for(Ventas ventas : ventasList) {
                v = ventas;
                ventas = em.merge(ventas);
            }
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            String msg = ex.getLocalizedMessage();
            if(v == null) {
                throw new Exception("ID de venta inválido");
            }
            if (msg == null || msg.length() == 0) {
                Long id = v.getId();
                if (findVentas(id) == null) {
                    throw new NonexistentEntityException("Venta con el id " + id + " ya no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Método optimizado únicamente para cambiar el atributo "facturada" de tabla ventas
     * @param ventasList
     * @param facturada
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    public void editManySetFacturada(List<Ventas> ventasList, Integer facturada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        Ventas v = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            for(Ventas ventas : ventasList) {
                v = ventas;
                Query q = em.createQuery("UPDATE Ventas v SET v.facturada = ?1 WHERE v.id = ?2");

                q.setParameter(1, facturada);
                q.setParameter(2, v.getId());
                q.executeUpdate();
            }
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            if(em != null && em.getTransaction() != null)
                em.getTransaction().rollback();
            String msg = ex.getLocalizedMessage();
            if(v == null) {
                throw new Exception("ID de venta inválido");
            }
            if (msg == null || msg.length() == 0) {
                Long id = v.getId();
                if (findVentas(id) == null) {
                    throw new NonexistentEntityException("Venta con el id " + id + " ya no existe.");
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
    
    public List<Ventas> findVentasByFactura(Integer idFactura) throws CommunicationsException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query cq = em.createQuery("SELECT v FROM Ventas v WHERE v.facturada = :idFactura", Integer.class);
            cq.setParameter("idFactura", idFactura);
            
            return cq.getResultList();
        } catch (PersistenceException ex) {
            throw ex;
        } finally {
            if(em != null) em.close();
        }
    }

    public Ventas findVentas(Long id) throws PersistenceException {
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
    
    public Long[] getIDIntervalFromDay(Date dayIni, Date dayFin) throws DatasourceException {
        Long[] intervalo = {0l,0l};
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q1 = em.createQuery("SELECT min(v.id) FROM Ventas v WHERE v.fechaHora between :dayIni and :dayFin", Integer.class);
            Query q2 = em.createQuery("SELECT max(v.id) FROM Ventas v WHERE v.fechaHora between :dayIni and :dayFin", Integer.class);
                        
            q1.setParameter("dayIni", dayIni);
            q1.setParameter("dayFin", dayFin);
            
            q2.setParameter("dayIni", dayIni);
            q2.setParameter("dayFin", dayFin);
            
            if(q1.getSingleResult() == null || q2.getSingleResult() == null)
                throw new DatasourceException("No se encontraron ventas para facturar");
            
            intervalo[0] = ((Long) q1.getSingleResult()).longValue();
            intervalo[1] = ((Long) q2.getSingleResult()).longValue();
            
            return intervalo;
        } catch (PersistenceException ex) {
            throw new DatasourceException("No hay comunicación con el origen de datos o está mal configurado", ex);
        } finally {
            if(em != null) em.close();
        }        
    }

}
