package com.sbna.vendorportal.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.config.QueryConstants;
import com.sbna.vendorportal.config.SecurityConfig;
import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.PurchaseOrderItemsAsnDao;
import com.sbna.vendorportal.dto.PurchaseOrderItemsAsn;
import com.sbna.vendorportal.pojo.AsnBuyerForm;
import com.sbna.vendorportal.pojo.AsnForm;
import com.sbna.vendorportal.pojo.AsnVendorForm;
import com.sbna.vendorportal.util.Utils;

@Repository(value = "purchaseOrderItemsAsnDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PurchaseOrderItemsAsnDaoImpl extends
		AbstractHibernateDao<PurchaseOrderItemsAsn> implements
		PurchaseOrderItemsAsnDao {

	@Autowired
	private Utils utils;

	@Override
	public void save(List<PurchaseOrderItemsAsn> purchaseOrderItemsAsnList) {
		bulkPersist(purchaseOrderItemsAsnList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AsnBuyerForm> getAsnForBuyer(String curLocale, Long curUserId,
			Map<String, Date> qDateMap) {
		return (List<AsnBuyerForm>) getAsns(curLocale, curUserId, qDateMap,
				SecurityConfig.ROLE_BUYER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AsnVendorForm> getAsnForVendor(String curLocale,
			Long curUserId, Map<String, Date> qDateMap) {
		return (List<AsnVendorForm>) getAsns(curLocale, curUserId, qDateMap,
				SecurityConfig.ROLE_VENDOR);
	}

	@SuppressWarnings("unchecked")
	private List<? extends AsnForm> getAsns(String curLocale, Long curUserId,
			Map<String, Date> qDateMap, String role) {
		String queryStr;
		if (SecurityConfig.ROLE_VENDOR.equals(role)) {
			queryStr = QueryConstants.ASN_DISPLAY_VENDOR_QUERY;
		} else {
			queryStr = QueryConstants.ASN_DISPLAY_BUYER_QUERY;
		}
		String whereClause = "";

		Date minDate = null;
		Date maxDate = null;

		if (qDateMap != null) {
			minDate = qDateMap.get("asnDateMin");
			
			//removing max date, as there can be future dates also
//			maxDate = qDateMap.get("asnDateMax");

			if (minDate != null) {
				whereClause += " AND asn.created >=:asnDateMin ";
			}
//			if (maxDate != null) {
//				whereClause += " AND asn.created <=:asnDateMax ";
//			}
		}

		String qlString = queryStr + whereClause;
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter("langKey", curLocale);
		
		if (SecurityConfig.ROLE_VENDOR.equals(role)) {
			query.setParameter("userId", curUserId);
		}

		if (minDate != null) {
			query.setParameter("asnDateMin", minDate);
		}

//		if (maxDate != null) {
//			query.setParameter("asnDateMax", maxDate);
//		}
		return query.getResultList();
	}

	@Override
	public List<PurchaseOrderItemsAsn> getAsnGroup(String asnGroupId) {
		Query query = getEntityManager().createQuery(QueryConstants.ASN_FETCH_GROUP_QUERY);
		query.setParameter("asnGroupId", asnGroupId);
		return query.getResultList();
	}

}
