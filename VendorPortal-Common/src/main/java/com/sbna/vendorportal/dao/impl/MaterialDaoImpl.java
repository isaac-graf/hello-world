	package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
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
import com.sbna.vendorportal.dao.MaterialDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.Material;
import com.sbna.vendorportal.dto.MaterialDesc;
import com.sbna.vendorportal.dto.Material_;
import com.sbna.vendorportal.dto.PurchaseOrg;
import com.sbna.vendorportal.pojo.MaterialForm;

@Repository(value = "materialDao")
@Transactional(propagation = Propagation.MANDATORY)
public class MaterialDaoImpl extends AbstractHibernateDao<Material> implements MaterialDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(MaterialDaoImpl.class);

	@Override
	public List<Material> getMaterials() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Material> criteria= builder.createQuery(Material.class);
		Root<Material> materialRoot = criteria.from(Material.class);
		criteria.select(materialRoot);
		return getEntityManager().createQuery(criteria).getResultList();

	}

	@Override
	public List<Material> getMaterialsByLang(String lang) {
		//System.out.println("getting material");
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Material> query = builder.createQuery(Material.class);

		Root<Material> materials = query.from(Material.class);
		Join<Material, MaterialDesc> materialDescs = materials.join("materialDescs");
		Join<MaterialDesc, Language> languages = materialDescs.join("lang");
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(builder.equal(languages.get("langKey"), lang));
		
		TypedQuery<Material> typedQuery = getEntityManager().createQuery(
				query.select(materials).where(conditions.toArray(new Predicate[] {}))
						.orderBy(builder.asc(materials.get("materialCode"))).distinct(true));
		return typedQuery.getResultList();
	}

	public Material get(String materialCode) {

		// todo deleted stuff
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Material> criteria = builder.createQuery(Material.class);
		Root<Material> materialRoot = criteria.from(Material.class);
		criteria.where(builder.equal(materialRoot.get(Material_.materialCode),materialCode));

		try {
			return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Material> getMaterialCode(String materialCode) {

		// todo deleted stuff
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Material> criteria = builder.createQuery(Material.class);
		Root<Material> materialRoot = criteria.from(Material.class);
		criteria.where(builder.equal(materialRoot.get(Material_.materialCode),materialCode));

		try {
			return getEntityManager().createQuery(criteria).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void handleUniqueViolation(List<Material> entities)
	{
		List<Material> dbEntities = new ArrayList<Material>();
		dbEntities = getMaterials();
		for(Material entity:entities)
		{
			for (Material dbEntity : dbEntities)
			{
				if(entity.getMaterialCode().equals(dbEntity.getMaterialCode()))
				{
					entity.setId(dbEntity.getId());
					entity.setMaterialDescs(dbEntity.getMaterialDescs());

				}
				
			}
			
		}	
	}

	@Override
	public List<MaterialForm> getMaterialFormByLang(String lang) {
		Query query = getEntityManager().createQuery("SELECT new com.sbna.vendorportal.pojo.MaterialForm"
                + "(m.id,m.materialCode,m.materialDescEng,d.materialDesc) "
                + "FROM Material m JOIN m.materialDescs d "
                + "JOIN d.lang l "
                + "WHERE l.langKey = :lang")
                .setParameter("lang", lang);
        return query.getResultList();
	}
}
