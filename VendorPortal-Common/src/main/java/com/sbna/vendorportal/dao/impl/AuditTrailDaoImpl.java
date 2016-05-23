package com.sbna.vendorportal.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.AuditTrailDao;
import com.sbna.vendorportal.dto.AuditTrail;
import com.sbna.vendorportal.dto.AuditTrail_;

@Repository(value = "auditTrailDao")
@Transactional(propagation = Propagation.MANDATORY)
public class AuditTrailDaoImpl extends AbstractHibernateDao<AuditTrail> implements AuditTrailDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditTrailDaoImpl.class);

    @Override
    public List<AuditTrail> getAuditTrails() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AuditTrail> criteria = builder.createQuery(AuditTrail.class);
        return getEntityManager().createQuery(criteria).getResultList();
    }
    
    @Override
	public void save(List<AuditTrail> auditTrailList) {
		bulkPersist(auditTrailList);		
	}
    
    @Override
	public List<AuditTrail> getAuditTrails(Map<String,String> requestParams) {

		SimpleDateFormat uiDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");
		SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String temp = requestParams.get("toDate") + " 23:59:59.999";
		Date fromDate = null;
		Date toDate = null;
		Date formattedFromDate = null;
		Date formattedToDate = null;
		try {
			if(!(requestParams.get("fromDate") == "" || requestParams.get("fromDate").isEmpty())){	
				fromDate= uiDateFormat.parse(requestParams.get("fromDate")+ " 00:00:00.000");
				String convertedFromDate = dbDateFormat.format(fromDate);
				formattedFromDate=dbDateFormat.parse(convertedFromDate);
			}
			if(!(requestParams.get("toDate") == "" || requestParams.get("toDate").isEmpty())){	
				toDate= uiDateFormat.parse(temp);
				String convertedToDate = dbDateFormat.format(toDate);
				formattedToDate=dbDateFormat.parse(convertedToDate);
				System.out.println("format:- "+convertedToDate+" ,"+formattedToDate);
			} 
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AuditTrail> criteria = builder.createQuery(AuditTrail.class);
        Root<AuditTrail> auditTrailRoot = criteria.from(AuditTrail.class);
        List<Predicate> conditions =  new ArrayList<Predicate>();
        if(!(requestParams.get("fromDate") == "" || requestParams.get("fromDate").isEmpty()))
        	conditions.add(builder.greaterThanOrEqualTo(auditTrailRoot.<Date>get(AuditTrail_.created), formattedFromDate));
        if(!(requestParams.get("toDate") == "" || requestParams.get("toDate").isEmpty()))
        	conditions.add(builder.lessThanOrEqualTo(auditTrailRoot.<Date>get(AuditTrail_.created), formattedToDate));        
        TypedQuery<AuditTrail> query =getEntityManager().createQuery(criteria.select(auditTrailRoot).where(conditions.toArray(new Predicate[]{})));

        List<AuditTrail> lstAuditTrail = new ArrayList<AuditTrail>();
        lstAuditTrail = query.getResultList();
        return lstAuditTrail;

	}
}
