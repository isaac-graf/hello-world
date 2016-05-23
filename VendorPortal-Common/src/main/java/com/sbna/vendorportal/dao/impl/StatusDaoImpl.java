package com.sbna.vendorportal.dao.impl;

import com.sbna.vendorportal.config.ProjectConfig;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.StatusDao;
import com.sbna.vendorportal.dto.Status;
import com.sbna.vendorportal.dto.Status_;

@Repository(value = "statusDao")
@Transactional(propagation = Propagation.MANDATORY)
public class StatusDaoImpl extends AbstractHibernateDao<Status> implements
        StatusDao {

    @Override
    public Status get(String name) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
        Root<Status> statusRoot = criteria.from(Status.class);
        criteria.where(builder.equal(builder.lower(statusRoot.get(Status_.statusDescEng)),
                name.toLowerCase()));
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Status getActiveStatus() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
        Root<Status> statusRoot = criteria.from(Status.class);
        criteria.where(builder.equal(builder.lower(statusRoot.get(Status_.statusDescEng)),
                ProjectConfig.STATUS_ACTIVE.toLowerCase()));
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
