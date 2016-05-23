package com.sbna.vendorportal.dao.impl;

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
import com.sbna.vendorportal.dao.DeliveryPerformanceDao;
import com.sbna.vendorportal.dto.DeliveryPerformance;
import com.sbna.vendorportal.dto.DeliveryPerformance_;
import com.sbna.vendorportal.dto.PurchaseOrder;
import com.sbna.vendorportal.dto.PurchaseOrder_;

@Repository(value = "deliveryPerformanceDao")
@Transactional(propagation = Propagation.MANDATORY)
public class DeliveryPerformanceDaoImpl extends AbstractHibernateDao<DeliveryPerformance> implements DeliveryPerformanceDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryPerformanceDaoImpl.class);
	@Override
	public DeliveryPerformance get(String poNo) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<DeliveryPerformance> criteria = builder.createQuery(DeliveryPerformance.class);
		Root<DeliveryPerformance> deliveryPerformanceRoot = criteria.from(DeliveryPerformance.class);
		criteria.where(builder.equal(deliveryPerformanceRoot.get(DeliveryPerformance_.poNo),poNo));
		try {
			return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
    
}
