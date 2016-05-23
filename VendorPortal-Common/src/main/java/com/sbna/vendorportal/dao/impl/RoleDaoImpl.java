package com.sbna.vendorportal.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.RoleDao;
import com.sbna.vendorportal.dto.Role;
import com.sbna.vendorportal.dto.Role_;

@Repository("roleDao")
@Transactional(propagation=Propagation.MANDATORY)
public class RoleDaoImpl extends AbstractHibernateDao<Role> implements RoleDao{

	@Override
	public Role get(String name) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
		Root<Role> root = criteria.from(Role.class);
		criteria.where(builder.equal(root.get(Role_.roleDescEng), name));

		try {
			return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
			
	}

}
