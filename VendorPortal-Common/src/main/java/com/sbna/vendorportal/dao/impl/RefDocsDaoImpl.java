package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.sbna.vendorportal.dao.RefDocsDao;
import com.sbna.vendorportal.dto.RefDocs;
import com.sbna.vendorportal.dto.RefDocs_;

@Repository(value = "refDocsDao")
@Transactional(propagation = Propagation.MANDATORY)
public class RefDocsDaoImpl extends AbstractHibernateDao<RefDocs> implements RefDocsDao {

    @Override
    public List<RefDocs> getRefDocs(Long id, Long refId, String refModule) {
    	return getRefDocs(id, refId, refModule, null);
    }
    
    @Override
    public List<RefDocs> getRefDocs(Long id, Long refId, String refModule, String grpId) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RefDocs> criteria = builder.createQuery(RefDocs.class);
        Root<RefDocs> refDocsRoot = criteria.from(RefDocs.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();
        if (null != id) {
            wherePredicates.add(builder.equal(refDocsRoot.get(RefDocs_.id), id));
        }
        if (null != refId) {
            wherePredicates.add(builder.equal(refDocsRoot.get(RefDocs_.refId), refId));
        }
        if (null != refModule) {
            wherePredicates.add(builder.equal(refDocsRoot.get(RefDocs_.refModule), refModule));
        }
        if(null != grpId){
        	wherePredicates.add(builder.equal(refDocsRoot.get(RefDocs_.grpId), grpId));
        }
        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.desc(refDocsRoot.get(RefDocs_.created)));
        try {
            return getEntityManager().createQuery(criteria).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }    

    @Override
    public List<RefDocs> getRefDocs(List<Long> refIds, String refModule) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RefDocs> criteria = builder.createQuery(RefDocs.class);
        Root<RefDocs> refDocsRoot = criteria.from(RefDocs.class);
        List<Predicate> wherePredicates = new ArrayList<>();
        wherePredicates.add((refDocsRoot.get(RefDocs_.refId).in(refIds)));
        wherePredicates.add(builder.equal(refDocsRoot.get(RefDocs_.refModule), refModule));
        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
    
    @Override
    public void delete(Long id){
    	RefDocs refDocs=new RefDocs();
    	refDocs=get(id);
    	getEntityManager().remove(refDocs);
    }

}
