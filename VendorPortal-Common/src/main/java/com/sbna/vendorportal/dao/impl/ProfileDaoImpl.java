package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.ProfileDao;
import com.sbna.vendorportal.dto.Material;
import com.sbna.vendorportal.dto.Profile;
import com.sbna.vendorportal.dto.PurchaseGroup;

@Repository(value = "profileDao")
@Transactional(propagation = Propagation.MANDATORY)
public class ProfileDaoImpl extends AbstractHibernateDao<Profile> implements
		ProfileDao {

	@Override
	public List<Profile> getProfiles() {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Profile> criteria= builder.createQuery(Profile.class);
		Root<Profile> materialRoot = criteria.from(Profile.class);
		criteria.select(materialRoot);
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public Profile get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Profile profile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Profile> getProfiles(int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void handleUniqueViolation(List<Profile> entities)
	{
		List<Profile> dbEntities = new ArrayList<Profile>();
		dbEntities = getProfiles();
		for(Profile entity:entities)
		{
			for (Profile dbEntity : dbEntities)
			{
				if(entity.getUsr().getId().equals(dbEntity.getUsr().getId()))
				{
					entity.setId(dbEntity.getId());
				}
				
			}
			
		}	
	}

}
