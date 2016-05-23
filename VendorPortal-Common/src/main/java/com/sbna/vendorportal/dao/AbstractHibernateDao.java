package com.sbna.vendorportal.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import com.sbna.vendorportal.dto.BaseModel;
import com.sbna.vendorportal.util.Utils;

/**
 * @author PhaneendraGoutham B Each DAO will be based on an parametrized,
 * abstract DAO class class with support for the common generic operations
 */
public abstract class AbstractHibernateDao<T extends BaseModel> {

    private Class<T> clazz
            = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Utils utils;

    public T get(final Long id) {
        if (id != null) {
            return entityManager.find(clazz, id);
        } else {
            return null;
        }
    }

    public List<T> getAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void save(final T entity) {
        if (!entity.hasValidId()) {
            if (entity.getCreated() == null) {
				entity.setCreated(new Date());
			}
			//using the default current timestamp, hence skipping to set updated Ts
//            entity.setUpdated(ByteBuffer.allocate(8).putLong(System.currentTimeMillis()).array());
            entityManager.persist(entity);
        } else {
//            entity.setUpdated(ByteBuffer.allocate(8).putLong(System.currentTimeMillis()).array());
            entityManager.merge(entity);
        }
    }

    public void delete(final T entity) {
        save(entity);
    }

    public void delete(final Long entityId) {
        final T entity = get(entityId);
        delete(entity);
    }

    protected <T> void buildWhereClause(CriteriaQuery<T> criteria, List<Predicate> wherePredicates) {
        if (!wherePredicates.isEmpty()) {
            Predicate[] predicates = new Predicate[wherePredicates.size()];

            for (int x = 0; x < wherePredicates.size(); x++) {
                predicates[x] = wherePredicates.get(x);
            }
            criteria.where(entityManager.getCriteriaBuilder().and(predicates));
        }
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected Utils getUtils() {
        return utils;
    }

    public void bulkPersist(List<T> entities) {
    	/*EntityTransaction txn = entityManager.getTransaction();
    	txn.begin();*/
        for (T entity : entities) {
        	System.out.println("------------I am in bulkpersit---------------------");
            if (!entity.hasValidId()) {
                entity.setCreated(new Date());
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
            flush();
            clear();

        }
       // txn.commit();
    }

    private void flush() {
        entityManager.flush();
    }

    private void clear() {
        entityManager.clear();
    }
}
