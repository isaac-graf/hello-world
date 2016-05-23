package com.sbna.vendorportal.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
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
import com.sbna.vendorportal.dao.VendorContactDetailsDao;
import com.sbna.vendorportal.dto.VendorContactDetails;
import com.sbna.vendorportal.dto.VendorContactDetails_;

@Repository(value = "vendorContactDetailsDao")
@Transactional(propagation = Propagation.MANDATORY)
public class VendorContactDetailsDaoImpl extends AbstractHibernateDao<VendorContactDetails> implements
        VendorContactDetailsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorContactDetailsDaoImpl.class);

    @Override
    public List<VendorContactDetails> getVendorContactDetails(Map<String, String> requestParams) {

        SimpleDateFormat uiDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String temp = requestParams.get("toDate") + " 23:59:59.999";
        Date fromDate = null;
        Date toDate = null;
        Long vendorId = null;
        System.out.println("------------VendorContactDetailsDaoImpl------------------------------" + vendorId);
        Date formattedFromDate = null;
        Date formattedToDate = null;
        try {
            if (!(requestParams.get("fromDate") == "" || requestParams.get("fromDate").isEmpty())) {
                fromDate = uiDateFormat.parse(requestParams.get("fromDate") + " 00:00:00.000");
                String convertedFromDate = dbDateFormat.format(fromDate);
                formattedFromDate = dbDateFormat.parse(convertedFromDate);
            }
            if (!(requestParams.get("toDate") == "" || requestParams.get("toDate").isEmpty())) {
                toDate = uiDateFormat.parse(temp);
                String convertedToDate = dbDateFormat.format(toDate);
                formattedToDate = dbDateFormat.parse(convertedToDate);
                System.out.println("format:- " + convertedToDate + " ," + formattedToDate);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<VendorContactDetails> criteria = builder.createQuery(VendorContactDetails.class);
        Root<VendorContactDetails> vendorConDetRoot = criteria.from(VendorContactDetails.class);
        List<Predicate> conditions = new ArrayList<Predicate>();
        if (!(requestParams.get("vendorCode") == "" || requestParams.get("vendorCode").isEmpty())) {
            vendorId = new Long(requestParams.get("vendorCode"));
        }
        // criteria.where(builder.equal(vendorConDetRoot.<Long>
        // get(VendorContactDetails_.vendorProfileId), vendorId));
        System.out.println("------------VendorContactDetailsDaoImpl------------------------------" + vendorId);
        if (!(requestParams.get("fromDate") == "" || requestParams.get("fromDate").isEmpty())) {
            conditions.add(builder.greaterThanOrEqualTo(vendorConDetRoot.<Date>get(VendorContactDetails_.created),
                    formattedFromDate));
        }
        if (!(requestParams.get("toDate") == "" || requestParams.get("toDate").isEmpty())) {
            conditions.add(builder.lessThanOrEqualTo(vendorConDetRoot.<Date>get(VendorContactDetails_.created),
                    formattedToDate));
        }
        TypedQuery<VendorContactDetails> query = getEntityManager().createQuery(
                criteria.select(vendorConDetRoot).where(conditions.toArray(new Predicate[]{})));

        List<VendorContactDetails> lstvendorConDet = new ArrayList<VendorContactDetails>();
        lstvendorConDet = query.getResultList();
        return lstvendorConDet;

    }

    @Override
    public List<VendorContactDetails> getVendorContactInfofromDB(long vendorCode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<VendorContactDetails> criteria = builder.createQuery(VendorContactDetails.class);
        Root<VendorContactDetails> root = criteria.from(VendorContactDetails.class);
        // criteria.where(builder.equal(root.get(VendorContactDetails_.vendorId),
        // vendorCode));
        // CriteriaQuery<VendorContactDetails> where =
        // criteria.where(builder.equal(root.get(VendorContactDetails_.vendorProfileId),
        // vendorCode));
        try {

            return getEntityManager().createQuery(criteria).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

//    @Override
//    public void saveBulkDetails(List<VendorContactDetails> vendorContactDetails) {
//        bulkPersist(vendorContactDetails);
//    }
}
