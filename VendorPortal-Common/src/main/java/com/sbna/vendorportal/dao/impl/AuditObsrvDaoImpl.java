package com.sbna.vendorportal.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.AuditObsrvDao;
import com.sbna.vendorportal.dto.AuditObservation;
import com.sbna.vendorportal.dto.AuditObservation_;
import com.sbna.vendorportal.dto.AuditPlan;

@Repository(value = "auditObsrvDao")
@Transactional(propagation = Propagation.MANDATORY)
public class AuditObsrvDaoImpl extends AbstractHibernateDao<AuditObservation> implements AuditObsrvDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditObsrvDaoImpl.class);

    @Override
    public List<AuditObservation> getAuditObservation(AuditPlan auditPlan) {
    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<AuditObservation> criteria = builder.createQuery(AuditObservation.class);
		Root<AuditObservation> auditObservationRoot = criteria.from(AuditObservation.class);
		criteria.where(builder.equal(auditObservationRoot.get(AuditObservation_.auditPlan),auditPlan));
		try {
			return getEntityManager().createQuery(criteria).getResultList();
		} catch (NoResultException e) {
			return null;
		}
    }
    
    
   
}
