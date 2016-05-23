package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.ReqForQuoteDao;
import com.sbna.vendorportal.dto.ReqForQuote;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "reqForQuoteDao")
@Transactional(propagation = Propagation.MANDATORY)
public class ReqForQuoteDaoImpl extends AbstractHibernateDao<ReqForQuote> implements ReqForQuoteDao {

    public List getRfqIdsByVendor(Long vendorId) {
        Query query = getEntityManager().createNamedQuery("ReqForQuote.getAllRFQIdsByVendor");
        query.setParameter("vendorId", vendorId);
        return query.getResultList();
    }

    public List getRfqIds() {
        Query query = getEntityManager().createNamedQuery("ReqForQuote.getAllRFQIds");
        return query.getResultList();
    }

    @Override
    public List<ReqForQuote> getRFQQuotes() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ReqForQuote> criteria = builder.createQuery(ReqForQuote.class);
        Root<ReqForQuote> rfqRoot = criteria.from(ReqForQuote.class);
        criteria.select(rfqRoot);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    public List<ReqForQuote> getByRfqNoVendorCode(String rfqNo, String vendorCode) {
        TypedQuery<ReqForQuote> typedQuery = getEntityManager().createNamedQuery("ReqForQuote.getByRfqNoVendorCode", ReqForQuote.class);
        typedQuery.setParameter("rfqNo", rfqNo);
        typedQuery.setParameter("vendorCode", vendorCode);
        return typedQuery.getResultList();
    }

    public List getCollecNos() {
        Query query = getEntityManager().createNamedQuery("ReqForQuote.getAllCollecNos");
        return query.getResultList();
    }

    @Override
    public void handleUniqueViolation(List<ReqForQuote> entities) {
        List<ReqForQuote> dbEntities = new ArrayList<ReqForQuote>();
        dbEntities = getRFQQuotes();
        for (ReqForQuote entity : entities) {
            for (ReqForQuote dbEntity : dbEntities) {
                if (entity.getRfqNo().equals(dbEntity.getRfqNo())) {
                    entity.setId(dbEntity.getId());
                    break;
                }
            }
        }
    }
}
