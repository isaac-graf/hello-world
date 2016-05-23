package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.CronSchedulerRunDao;
import com.sbna.vendorportal.dto.CronSchedulerRun;
import com.sbna.vendorportal.dto.CronSchedulerRun_;
import com.sbna.vendorportal.dto.RefDocs_;
import com.sbna.vendorportal.dto.Status;

@Repository(value = "cronSchedulerRun")
@Transactional(propagation = Propagation.MANDATORY)
public class CronSchedulerRunDaoImpl extends AbstractHibernateDao<CronSchedulerRun> implements CronSchedulerRunDao {

	@Override
	public CronSchedulerRun get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public void save(CronSchedulerRun cronScheduler) {
		// TODO Auto-generated method stub

	}*/
	
	@Override
	public CronSchedulerRun getMax(Long id,Status status) {

        // TODO deleted stuff		
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CronSchedulerRun> criteria = builder.createQuery(CronSchedulerRun.class);
        Root<CronSchedulerRun> cronSCHRoot = criteria.from(CronSchedulerRun.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        if (null != id) {
            wherePredicates.add(builder.equal(cronSCHRoot.get(CronSchedulerRun_.cronSchedulerId), id));
        }
        if (null != status) {
            wherePredicates.add(builder.equal(cronSCHRoot.get(CronSchedulerRun_.status), status));
        }
        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.desc(cronSCHRoot.get(CronSchedulerRun_.created)));

        try {
            return getEntityManager().createQuery(criteria).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
