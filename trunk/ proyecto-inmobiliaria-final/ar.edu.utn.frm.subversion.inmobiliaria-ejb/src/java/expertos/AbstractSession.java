package expertos;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Sebastian Torres
 */
public abstract class AbstractSession<T> {
    private Class<T> entityClass;

    public AbstractSession(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    @Deprecated
    public void create(T entity) {
        
        if(entity == null){
            System.out.println("\n\n\n Error al intentar persistir un null \n\n\n");
        }else{
            getEntityManager().persist(entity);
        }
        
    }

    @Deprecated
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }
    
    /**
     * Crea o actualiza una entidad en la base de datos, dependiendo si ya existe
     * @param entity entidad a crear o actualizar 
     */
    public void save(T entity) {
         if(entity == null){
            System.out.println("\n\n\n Error al intentar persistir. Parametro nulo. \n\n\n");
        }        
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
