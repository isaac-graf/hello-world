package com.sbna.vendorportal.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.ReportPCRDao;
import com.sbna.vendorportal.dto.Pcr;
import com.sbna.vendorportal.dto.Pcr_;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.pojo.MasterKeyValueHelper;
import com.sbna.vendorportal.pojo.PCRDataHelper;

@Repository("reportPCRDao")
@Transactional(propagation = Propagation.MANDATORY)
public class ReportPCRDaoImpl extends AbstractHibernateDao<Pcr> implements ReportPCRDao {

    public List<MasterKeyValueHelper> getPCRNumber(String userId) {
        String query = "SELECT new com.sbna.vendorportal.pojo.MasterKeyValueHelper(E.id,E.id) FROM Pcr E WHERE E.user.id= :USERID";
        Query q = getEntityManager().createQuery(query);
        q.setParameter("USERID", new Long(userId));
        return q.getResultList();
    }

    public List<MasterKeyValueHelper> getPCRNumber4Buyer(String userId) {
        String query = "SELECT new com.sbna.vendorportal.pojo.MasterKeyValueHelper(E.id,E.id) FROM Pcr E ";
        Query q = getEntityManager().createQuery(query);
        // q.setParameter("USERID", new Long(userId));
        return q.getResultList();
    }

    public List<MasterKeyValueHelper> getAllStatus(String locale, String moduleUrlRole) {
        Query query = getEntityManager()
                .createQuery(
                        "SELECT new com.sbna.vendorportal.pojo.MasterKeyValueHelper" + "(s.id, sd.statusDesc) "
                        + "FROM Module m JOIN m.statuses s " + "JOIN s.statusDescs sd " + "JOIN sd.lang l "
                        + "WHERE l.langKey = :locale " + "AND m.moduleUrlRole = :moduleUrlRole")
                .setParameter("locale", locale).setParameter("moduleUrlRole", moduleUrlRole);
        return query.getResultList();
    }

    public List<PCRDataHelper> formtePCRData(List<Object> pcrList) {
        Iterator<Object> it = pcrList.iterator();
        List<PCRDataHelper> pcrDataList = new ArrayList<PCRDataHelper>();
        try {
            while (it.hasNext()) {
                Object[] result = (Object[]) it.next();
				// pcrDataList.add(new PCRDataHelper(result[0], result[1],
                // result[2], result[3], result[4], result[5], result[6],
                // result[7],result[9], result[10], result[11],result[12]));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pcrDataList;
    }

    @Override
    public List<Pcr> getPCR(Map<String, String> requestParams) {

        SimpleDateFormat uiDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");
        SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String temp = requestParams.get("toDate") + " 23:59:59.999";
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
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Pcr> criteria = builder.createQuery(Pcr.class);
        Root<Pcr> pcrRoot = criteria.from(Pcr.class);
        Join<Pcr, User> userJoin = pcrRoot.join("user");
        List<Predicate> conditions = new ArrayList<Predicate>();
        if (!requestParams.get("userId").isEmpty()) {
            conditions.add(builder.equal(userJoin.get("id"), Long.parseLong(requestParams.get("userId"))));
        }
        if (!(requestParams.get("fromDate") == "" || requestParams.get("fromDate").isEmpty())) {
            conditions.add(builder.greaterThanOrEqualTo(pcrRoot.<Date>get(Pcr_.created), formattedFromDate));
        }
        if (!(requestParams.get("toDate") == "" || requestParams.get("toDate").isEmpty())) {
            conditions.add(builder.lessThanOrEqualTo(pcrRoot.<Date>get(Pcr_.created), formattedToDate));
        }
        TypedQuery<Pcr> query = getEntityManager().createQuery(criteria.select(pcrRoot).where(conditions.toArray(new Predicate[]{})));

        List<Pcr> lstPcr = new ArrayList<Pcr>();
        lstPcr = query.getResultList();
        return lstPcr;

    }
}
