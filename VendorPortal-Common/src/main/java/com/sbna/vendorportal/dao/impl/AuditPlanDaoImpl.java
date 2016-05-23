package com.sbna.vendorportal.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.AuditPlanDao;
import com.sbna.vendorportal.dto.AuditPlan;
import com.sbna.vendorportal.dto.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository(value = "auditPlanDao")
@Transactional(propagation = Propagation.MANDATORY)
public class AuditPlanDaoImpl extends AbstractHibernateDao<AuditPlan> implements AuditPlanDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditPlanDaoImpl.class);

    @Override
    public List<AuditPlan> getAuditPlans(Date startDate, Date endDate) {
//        Query query = getEntityManager().createQuery("SELECT new com.sbna.vendorportal.pojo.AuditGenericDataHelper(ap.id, v.id, v.usrName,v.profile.fullName, a.id, a.usrName, a.profile.firstName, a.profile.lastName, ap.auditDate) FROM AuditPlan ap LEFT JOIN ap.usr2 v LEFT OUTER JOIN ap.usr1 a WHERE ap.auditDate BETWEEN :startDate AND :endDate ORDER BY v.usrName, ap.auditDate")
//                .setParameter("startDate", startDate)
//                .setParameter("endDate", endDate);
//        return query.getResultList();
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AuditPlan> query = builder.createQuery(AuditPlan.class);

        Root<AuditPlan> root = query.from(AuditPlan.class);
        List<Predicate> conditions = new ArrayList<>();
        Path<Date> auditDate = root.get("auditDate");
        conditions.add(builder.between(auditDate, startDate, endDate));

        TypedQuery<AuditPlan> typedQuery = getEntityManager().createQuery(
                query.select(root).where(conditions.toArray(new Predicate[]{}))
                .orderBy(builder.asc(root.get("usr2"))));
        return typedQuery.getResultList();

    }

    @Override
    public List<AuditPlan> getAuditPlans(Date startDate, Date endDate, User vendor) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AuditPlan> query = builder.createQuery(AuditPlan.class);

        Root<AuditPlan> root = query.from(AuditPlan.class);
        List<Predicate> conditions = new ArrayList<>();
        Path<User> vendorCol = root.get("usr2");
        Path<Date> auditDate = root.get("auditDate");
        conditions.add(builder.equal(vendorCol, vendor));
        conditions.add(builder.between(auditDate, startDate, endDate));

        TypedQuery<AuditPlan> typedQuery = getEntityManager().createQuery(
                query.select(root).where(conditions.toArray(new Predicate[]{}))
                .orderBy(builder.asc(root.get("usr2"))));
        return typedQuery.getResultList();

    }

}
