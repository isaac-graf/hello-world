package com.sbna.vendorportal.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.VendorDetailsDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.Pcr;
import com.sbna.vendorportal.dto.Status;
import com.sbna.vendorportal.dto.StatusDesc;
import com.sbna.vendorportal.dto.StatusDesc_;
import com.sbna.vendorportal.dto.VendorDetails;
import com.sbna.vendorportal.dto.VendorDetails_;
import com.sbna.vendorportal.dto.VendorProfile;
import com.sbna.vendorportal.dto.VendorProfile_;

@Repository(value = "vendorDetailsDao")
@Transactional(propagation = Propagation.MANDATORY)
public class VendorDetailsDaoImpl extends AbstractHibernateDao<VendorDetails> implements VendorDetailsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorDetailsDaoImpl.class);

    @Override
    public List<VendorDetails> getVendorDetails(Map<String, String> requestParams) {
        SimpleDateFormat uiDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String temp = requestParams.get("toDate") + " 23:59:59.999";
        BigDecimal vendorId = null;
        Date fromDate = null;
        Date toDate = null;
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
        CriteriaQuery<VendorDetails> criteria = builder.createQuery(VendorDetails.class);
        Root<VendorDetails> vendorDetailsRoot = criteria.from(VendorDetails.class);
        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!(requestParams.get("vendorCode") == "" || requestParams.get("vendorCode").isEmpty())) {
            vendorId = new BigDecimal(requestParams.get("vendorCode"));
        }
        // conditions.add(builder.equal(vendorDetailsRoot.<BigInteger>
        // get(VendorDetails_.vendorId), vendorId));
        System.out.println("------------VendorContactsDaoImpl------------------------------" + vendorId);
        if (!(requestParams.get("fromDate") == "" || requestParams.get("fromDate").isEmpty())) {
            conditions.add(builder.greaterThanOrEqualTo(vendorDetailsRoot.<Date>get(VendorDetails_.created),
                    formattedFromDate));
        }
        if (!(requestParams.get("toDate") == "" || requestParams.get("toDate").isEmpty())) {
            conditions.add(builder.lessThanOrEqualTo(vendorDetailsRoot.<Date>get(VendorDetails_.created),
                    formattedToDate));
        }

        TypedQuery<VendorDetails> query = getEntityManager().createQuery(
                criteria.select(vendorDetailsRoot).where(conditions.toArray(new Predicate[]{})));

        List<VendorDetails> lstvendorDet = new ArrayList<VendorDetails>();
        lstvendorDet = query.getResultList();
        return lstvendorDet;

    }

    @Override
    public VendorDetails getVendorDetailFromDB(long vendorCode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<VendorDetails> criteria = builder.createQuery(VendorDetails.class);
        Root<VendorDetails> root = criteria.from(VendorDetails.class);
        // criteria.where(builder.equal(root.get(VendorDetails_.vendorId),
        // vendorCode));
        criteria.where(builder.equal(root.get(VendorDetails_.vendorProfileId), vendorCode));

        try {

            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<VendorDetails> getPendingRequests(final String lang) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<VendorDetails> query = builder.createQuery(VendorDetails.class);
        Root<VendorDetails> vendorDetails = query.from(VendorDetails.class);
        Join<VendorDetails, Status> status = vendorDetails.join("status");
        Join<Status, StatusDesc> statusDescs = status.join("statusDescs");
        Join<StatusDesc, Language> languages = statusDescs.join("lang");
        List<Predicate> conditions = new ArrayList<Predicate>();
        conditions.add((statusDescs.get(StatusDesc_.statusDesc).in(ProjectConfig.STATUS_REQUESTED,
                ProjectConfig.STATUS_PENDING)));
        conditions.add(builder.equal(languages.get("langKey"), lang));
        TypedQuery<VendorDetails> typedQuery = getEntityManager().createQuery(
                query.select(vendorDetails).where(conditions.toArray(new Predicate[]{})).orderBy(builder.asc(vendorDetails.get("id")))
                .distinct(true));
        List<VendorDetails> tstlist = typedQuery.getResultList();
        return tstlist;

    }

    @Override
    public VendorDetails get(Long id) {
        return super.get(id);
    }

}
