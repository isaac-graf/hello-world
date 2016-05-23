package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

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

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.PurchaseOrgDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.PurchaseOrg;
import com.sbna.vendorportal.dto.PurchaseOrgDesc;
import com.sbna.vendorportal.dto.PurchaseOrg_;

@Repository(value = "purchaseOrgDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PurchaseOrgDaoImpl extends AbstractHibernateDao<PurchaseOrg> implements PurchaseOrgDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrgDaoImpl.class);

	@Override
	public List<PurchaseOrg> getPurchaseOrgs() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PurchaseOrg> criteria = builder.createQuery(PurchaseOrg.class);
		Root<PurchaseOrg> plantRoot = criteria.from(PurchaseOrg.class);
		criteria.select(plantRoot);
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<PurchaseOrg> getPurchaseOrgsByLang(String lang) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PurchaseOrg> query = builder.createQuery(PurchaseOrg.class);
		Root<PurchaseOrg> purchaseOrg = query.from(PurchaseOrg.class);
		Join<PurchaseOrg, PurchaseOrgDesc> prchsOrgDescs = purchaseOrg.join("prchsOrgDescs");
		Join<PurchaseOrgDesc, Language> languages = prchsOrgDescs.join("lang");
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(builder.equal(languages.get("langKey"), lang));
		
		TypedQuery<PurchaseOrg> typedQuery = getEntityManager().createQuery(
				query.select(purchaseOrg).where(conditions.toArray(new Predicate[] {}))
						.orderBy(builder.asc(purchaseOrg.get("prchsOrgCode"))).distinct(true));
		return typedQuery.getResultList();
	}

	public PurchaseOrg get(String purchaseOrgCode) {

		// todo deleted stuff
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PurchaseOrg> criteria = builder.createQuery(PurchaseOrg.class);
		Root<PurchaseOrg> purchaseOrgRoot = criteria.from(PurchaseOrg.class);
		criteria.where(builder.equal(purchaseOrgRoot.get(PurchaseOrg_.prchsOrgCode),purchaseOrgCode));

		try {
			return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public void handleUniqueViolation(List<PurchaseOrg> entities)
	{
		List<PurchaseOrg> dbEntities = new ArrayList<PurchaseOrg>();
		dbEntities = getPurchaseOrgs();
		for(PurchaseOrg entity:entities)
		{
			for (PurchaseOrg dbEntity : dbEntities)
			{
				if(entity.getPurchaseOrgCode().equals(dbEntity.getPurchaseOrgCode()))
				{
					entity.setId(dbEntity.getId());
					entity.setPurchaseOrgDescs(dbEntity.getPurchaseOrgDescs());

				}
				
			}
			
		}	
	}
	
}
