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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.PcrDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.Pcr;
import com.sbna.vendorportal.dto.Pcr_;
import com.sbna.vendorportal.dto.Status;
import com.sbna.vendorportal.dto.StatusDesc;
import com.sbna.vendorportal.dto.StatusDesc_;
import com.sbna.vendorportal.dto.User;

@Repository(value = "pcrDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PcrDaoImpl extends AbstractHibernateDao<Pcr> implements PcrDao {

    @Override
    public List<Pcr> getPcrByGroupAndLang(long purchaseGroupId, String lang) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Pcr> query = builder.createQuery(Pcr.class);
        Root<Pcr> pcr = query.from(Pcr.class);
        Join<Pcr, Status> status = pcr.join("status");
        Join<Pcr, User> vendor = pcr.join("user");
        Join<User, Status> vendorStatus = vendor.join("status");
        Join<Status, StatusDesc> statusDescs = status.join("statusDescs");
        Join<StatusDesc, Language> languages = statusDescs.join("lang");
        List<Predicate> conditions = new ArrayList<>();
        conditions.add((statusDescs.get(StatusDesc_.statusDesc).in(ProjectConfig.STATUS_REQUESTED,
                ProjectConfig.STATUS_PENDING)));
        conditions.add(builder.equal(vendorStatus.get("statusDescEng"), "Active"));
        conditions.add(builder.equal(languages.get("langKey"), lang));
//        conditions.add(builder.equal(pcr.get("purchaseGroup").get("id"), purchaseGroupId));
        TypedQuery<Pcr> typedQuery = getEntityManager().createQuery(
                query.select(pcr).where(conditions.toArray(new Predicate[]{})).orderBy(builder.asc(pcr.get("id")))
                .distinct(true));
        List<Pcr> tstlist = typedQuery.getResultList();
        return tstlist;
    }

    @Override
    public Pcr update(Pcr changeCharges) {
        return getEntityManager().merge(changeCharges);
    }

    @Override
    public List<Pcr> getPcrByPcrNo(long id) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Pcr> criteria = builder.createQuery(Pcr.class);
        Root<Pcr> root = criteria.from(Pcr.class);
        criteria.where(builder.equal(root.get(Pcr_.id), id));

        try {

            return getEntityManager().createQuery(criteria).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Status getStatusId(final String statusDesc, final String lang) {

        System.out.println("--------------------in status id method--------------------------");
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Status> query = builder.createQuery(Status.class);

        Root<Status> status = query.from(Status.class);
        Join<Status, StatusDesc> statusDescs = status.join("statusDescs");
        Join<StatusDesc, Language> languages = statusDescs.join("lang");

        List<Predicate> conditions = new ArrayList<Predicate>();
        conditions.add(builder.equal(statusDescs.get(StatusDesc_.statusDesc), statusDesc));
        conditions.add(builder.equal(languages.get("langKey"), lang));

        TypedQuery<Status> typedQuery = getEntityManager().createQuery(
                query.select(status).where(conditions.toArray(new Predicate[]{})));

        return typedQuery.getSingleResult();

    }
}
