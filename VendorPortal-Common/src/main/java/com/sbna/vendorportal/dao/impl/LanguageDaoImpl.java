package com.sbna.vendorportal.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.LanguageDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.Language_;
import com.sbna.vendorportal.dto.Role;
import com.sbna.vendorportal.dto.Role_;

@Repository("languageDao")
@Transactional(propagation=Propagation.MANDATORY)
public class LanguageDaoImpl extends AbstractHibernateDao<Language>implements LanguageDao {

	@Override
	public Language get(String name) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Language> criteria = builder.createQuery(Language.class);
		Root<Language> root = criteria.from(Language.class);
		criteria.where(builder.equal(root.get(Language_.langKey), name));

		try {
			return getEntityManager().createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
