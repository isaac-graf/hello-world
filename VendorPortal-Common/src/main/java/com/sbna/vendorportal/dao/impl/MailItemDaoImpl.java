package com.sbna.vendorportal.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.MailItemDao;
import com.sbna.vendorportal.dto.MailItem;
import com.sbna.vendorportal.dto.MailItem_;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.pojo.MailItemHelper;

@Repository("mailItemDao")
@Transactional(propagation = Propagation.MANDATORY)
public class MailItemDaoImpl extends AbstractHibernateDao<MailItem> implements
		MailItemDao {

	@Override
	public List<MailItem> getMailItems(User toUser) {
		CriteriaQuery<MailItem> criteria = getCriteriaQueryForRecepientUser(toUser);

		return getEntityManager().createQuery(criteria).getResultList();

	}

	private CriteriaQuery<MailItem> getCriteriaQueryForRecepientUser(User toUser) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MailItem> criteria = builder.createQuery(MailItem.class);
		Root<MailItem> mailItemRoot = criteria.from(MailItem.class);
		criteria.where(builder.equal(mailItemRoot.get(MailItem_.usr2), toUser));
		return criteria;
	}

	@Override
	public List<MailItemHelper> getMailItems(User toUser, int start, int limit) {

		Query query = getEntityManager()
				.createQuery(
						"select new com.sbna.vendorportal.pojo.MailItemHelper"
								+ "(m.id, m.mailRead, m.mailFrom, m.subject, m.message, m.created)"
								+ "from MailItem m where m.usr2 = :toUser order by m.created DESC");
		query.setParameter("toUser", toUser);

		return query.setFirstResult(start).setMaxResults(limit).getResultList();
	}

	@Override
	public Long getUnReadMailCount(User toUser) {
		Query query = getEntityManager().createQuery(
				"select count(m) from MailItem m "
						+ "where m.usr2 = :toUser and m.mailRead = false "
						+ "and ((m.subject is not null and TRIM(m.subject) <> '') "
						+ "or (m.message is not null and TRIM(m.message) <> '') )");
		query.setParameter("toUser", toUser);

		return (Long) query.getSingleResult();
	}

}
