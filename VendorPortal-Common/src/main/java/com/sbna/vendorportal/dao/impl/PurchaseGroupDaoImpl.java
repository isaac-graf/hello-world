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
import com.sbna.vendorportal.dao.PurchaseGroupDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.PurchaseGroup;
import com.sbna.vendorportal.dto.PurchaseGroupDesc;
import com.sbna.vendorportal.dto.PurchaseGroup_;

@Repository(value = "purchaseGroupDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PurchaseGroupDaoImpl extends AbstractHibernateDao<PurchaseGroup> implements PurchaseGroupDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseGroupDaoImpl.class);

	@Override
	public List<PurchaseGroup> getPurchaseGroups() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PurchaseGroup> criteria = builder.createQuery(PurchaseGroup.class);
		Root<PurchaseGroup> plantRoot = criteria.from(PurchaseGroup.class);
		criteria.select(plantRoot);
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public List<PurchaseGroup> getPurchaseGroupsByLang(String lang) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PurchaseGroup> query = builder.createQuery(PurchaseGroup.class);

		Root<PurchaseGroup> purchaseGrp = query.from(PurchaseGroup.class);
		Join<PurchaseGroup, PurchaseGroupDesc> prchsOrgDescs = purchaseGrp.join("prchsGrpDescs");
		Join<PurchaseGroupDesc, Language> languages = prchsOrgDescs.join("lang");
		List<Predicate> conditions = new ArrayList<>();
		conditions.add(builder.equal(languages.get("langKey"), lang));

		TypedQuery<PurchaseGroup> typedQuery = getEntityManager().createQuery(
				query.select(purchaseGrp).where(conditions.toArray(new Predicate[] {}))
						.orderBy(builder.asc(purchaseGrp.get("prchsGrpCode"))).distinct(true));
		return typedQuery.getResultList();
	}

	public PurchaseGroup get(String purchaseGroupID) {

		// todo deleted stuff
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PurchaseGroup> criteria = builder.createQuery(PurchaseGroup.class);
		Root<PurchaseGroup> purchaseGroupRoot = criteria.from(PurchaseGroup.class);
		List<Predicate> wherePredicates = new ArrayList<Predicate>();
		wherePredicates.add(builder.equal(purchaseGroupRoot.get(PurchaseGroup_.prchsGrpCode), purchaseGroupID));

		buildWhereClause(criteria, wherePredicates);

		try {
			return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public void handleUniqueViolation(List<PurchaseGroup> entities)
	{
		List<PurchaseGroup> dbEntities = new ArrayList<PurchaseGroup>();
		dbEntities = getPurchaseGroups();
		for(PurchaseGroup entity:entities)
		{
			for (PurchaseGroup dbEntity : dbEntities)
			{
				if(entity.getPrchsGrpCode().equals(dbEntity.getPrchsGrpCode()))
				{
					entity.setId(dbEntity.getId());
					entity.setPrchsGrpDescs(dbEntity.getPrchsGrpDescs());
				}
				
			}
			
		}	
	}
	
	
}
