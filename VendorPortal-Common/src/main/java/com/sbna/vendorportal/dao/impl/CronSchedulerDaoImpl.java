package com.sbna.vendorportal.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.CronSchedulerDao;
import com.sbna.vendorportal.dto.CronScheduler;
import com.sbna.vendorportal.dto.CronScheduler_;
import com.sbna.vendorportal.dto.Plant;
import com.sbna.vendorportal.dto.Plant_;

@Repository(value = "cronScheduler")
@Transactional(propagation = Propagation.MANDATORY)
public class CronSchedulerDaoImpl extends AbstractHibernateDao<CronScheduler> implements CronSchedulerDao {

	@Override
	public CronScheduler get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CronScheduler get(String cronName) {
		// TODO Auto-generated method stub
		// TODO deleted stuff
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CronScheduler> criteria = builder.createQuery(CronScheduler.class);
        Root<CronScheduler> cronSchRoot = criteria.from(CronScheduler.class);
        criteria.where(builder.equal(cronSchRoot.get(CronScheduler_.cronName), cronName));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

	@Override
	public void save(CronScheduler cronScheduler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
