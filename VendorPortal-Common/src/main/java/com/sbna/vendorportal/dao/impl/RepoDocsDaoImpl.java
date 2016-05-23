package com.sbna.vendorportal.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.RepoDocsDao;
import com.sbna.vendorportal.dto.RepoDocs;
import com.sbna.vendorportal.pojo.RepoDocForm;

@Repository(value = "repoDocsDao")
@Transactional(propagation = Propagation.MANDATORY)
public class RepoDocsDaoImpl extends AbstractHibernateDao<RepoDocs> implements
		RepoDocsDao {

	@Override
	public List<RepoDocForm> getAllDocs(String docType) {
		Query query = getEntityManager().createQuery(
				"select new com.sbna.vendorportal.pojo.RepoDocForm("
						+ "r.id, r.docName, r.docDesc, r.created) "
						+ "from RepoDocs r "
						+ "where r.docType = :docType "
						+ "order by r.created desc");
		query.setParameter("docType", docType);
		return query.getResultList();
	}

	@Override
	public List<RepoDocs> getList(Long[] repoDocIdArr, String docType) {
		Query query = getEntityManager().createQuery(
				"select r from RepoDocs r where r.id in :repoDocIdArr and r.docType = :docType");
		query.setParameter("repoDocIdArr", Arrays.asList(repoDocIdArr));
		query.setParameter("docType", docType);
		return query.getResultList();
	}

	@Override
	public void remove(RepoDocs repoDocs) {
		getEntityManager().remove(repoDocs);
	}

}
