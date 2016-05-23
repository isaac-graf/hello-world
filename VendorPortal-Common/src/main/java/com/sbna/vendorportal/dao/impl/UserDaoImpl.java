package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.config.QueryConstants;
import com.sbna.vendorportal.config.SecurityConfig;
import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.UserDao;
import com.sbna.vendorportal.dto.Role;
import com.sbna.vendorportal.dto.Status;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.dto.User_;
import com.sbna.vendorportal.pojo.MasterDataHelper;
import com.sbna.vendorportal.pojo.UserDetailDataHelper;
import com.sbna.vendorportal.pojo.UserForm;

@Repository(value = "userDao")
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserDaoImpl.class);

    @Override
    public List<User> getUsers() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> userRoot = criteria.from(User.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();
        // always want to exclude deleted
        // wherePredicates.add(builder.equal(userRoot.get(User_.status), true));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<UserForm> getUserForms(Long curUserId) {
        Query query = getEntityManager().createQuery(QueryConstants.ADMIN_CONTROL_USER_QUERY);
        query.setParameter("curUserId", curUserId);
        return query.getResultList();
    }

    public User get(String email) {
        // TODO deleted stuff
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.where(builder.equal(userRoot.get(User_.email), email));

        User user = null;
        try {
            List<User> userList = getEntityManager().createQuery(criteria).getResultList();
            if (userList != null && userList.size() > 0) {
                user = userList.get(0);
            }
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getByUserName(String userName) {
        // TODO deleted stuff
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.where(builder.equal(userRoot.get(User_.usrName), userName));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<User> getUsers(int start, int limit) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        try {
            return getEntityManager().createQuery(criteria)
                    .setFirstResult(start).setMaxResults(limit).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<User> getVendors() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> users = query.from(User.class);
        Join<User, Role> userroles = users.join("roles");
        // Join<User, Profile> userprofile = users.join("profile");
        List<Predicate> conditions = new ArrayList<Predicate>();
        conditions.add(builder.equal(userroles.get("roleDescEng"), "ROLE_VENDOR"));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(
                query.select(users)
                .where(conditions.toArray(new Predicate[]{}))
                .orderBy(builder.asc(users.get("usrName")))
                .distinct(true));
        return typedQuery.getResultList();
    }

    @Override
    public List<User> getUsersByRoleName(String role) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> users = query.from(User.class);
        Join<User, Role> userroles = users.join("roles");
        Join<User, Status> statusJoin = users.join("status");
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(builder.equal(userroles.get("roleDescEng"), role));
        conditions.add(builder.equal(statusJoin.get("statusDescEng"), "Active"));
        TypedQuery<User> typedQuery = getEntityManager().createQuery(
                query.select(users)
                .where(conditions.toArray(new Predicate[]{}))
                .orderBy(builder.asc(users.get("usrName")))
                .distinct(true));
        return typedQuery.getResultList();
    }

    @Override
    public List<MasterDataHelper> getAllUsernameFullname() {
        Query query = getEntityManager()
                .createQuery(
                        "SELECT DISTINCT new com.sbna.vendorportal.pojo.MasterDataHelper"
                        + "(u.usrName, u.usrName + ' - ' + p.fullName) "
                        + "FROM User u JOIN u.profile p JOIN u.roles r WHERE r.roleDescEng = 'ROLE_VENDOR'");
        return query.getResultList();
    }

    @Override
    public List<UserDetailDataHelper> getUserDetailsByRolename(String role) {
        if (SecurityConfig.ROLE_VENDOR.equals(role)) {
            Query query = getEntityManager()
                    .createQuery(
                            "SELECT DISTINCT new com.sbna.vendorportal.pojo.UserDetailDataHelper"
                            + "(u.id, u.usrName, p.fullName) "
                            + "FROM User u JOIN u.profile p JOIN u.roles r JOIN u.status s WHERE s.statusDescEng = 'Active' "
                            + "AND r.roleDescEng = '" + role + "'");
            return query.getResultList();
        } else {
            Query query = getEntityManager()
                    .createQuery(
                            "SELECT DISTINCT new com.sbna.vendorportal.pojo.UserDetailDataHelper"
                            + "(u.id, u.usrName, CONCAT(p.firstName, ' ', p.lastName)) "
                            + "FROM User u JOIN u.profile p JOIN u.roles r JOIN u.status s WHERE s.statusDescEng = 'Active' "
                            + "AND r.roleDescEng = '" + role + "'");
            return query.getResultList();
        }
    }

    @Override
    public void handleUniqueViolation(List<User> users) {
        List<User> dbusers = new ArrayList<User>();
        dbusers = getUsers();
        for (User user : users) {
            for (User dbUser : dbusers) {
                if (user.getUsrName().equals(dbUser.getUsrName())) {
                    user.setId(dbUser.getId());
                    user.setStatus(dbUser.getStatus());
                    user.setUsrPwd(dbUser.getUsrPwd());
                    user.setRoles(dbUser.getRoles());
                    user.setPrchsGrp(dbUser.getPrchsGrp());
                    //users.remove(user);
                }
            }
        }
    }

    public List<User> getBuyersByPrchdGroup(Long prchsGrpId) {
        String queryString = "SELECT DISTINCT usr FROM User usr  ";
        String joinQuery = " JOIN usr.roles r JOIN usr.prchsGrp pg ";
        String whereQuery = " WHERE pg.id = :prchsGrpId AND r.roleDescEng=:roleDescEng ";
        queryString += joinQuery + whereQuery;
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("roleDescEng", "ROLE_BUYER");
        query.setParameter("prchsGrpId", prchsGrpId);

        return query.getResultList();
    }

    public List<User> getVendorEmailByVendorId(Long vendorId) {
        String queryString = "SELECT DISTINCT usr FROM User usr  ";
        String whereQuery = " WHERE usr.id = :vendorId ";
        queryString += whereQuery;
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("vendorId", vendorId);

        return query.getResultList();
    }

    public List<User> getActiveVendorEmailByVendorId(Long vendorId) {
        String queryString = "SELECT DISTINCT usr FROM User usr  ";
        String whereQuery = " WHERE usr.id = :vendorId and usr.status=(select id from Status status where status.statusDescEng = :statusDesc)";
        queryString += whereQuery;
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("vendorId", vendorId);
        query.setParameter("statusDesc", "Active");

        return query.getResultList();
    }

    public List<String> getEmailByPurchaseGroupId(Long prchsGrpId) {
        Query query = getEntityManager().createQuery(
                "select u.email from User u where u.prchsGrp.id = :prchsGrpId");
        query.setParameter("prchsGrpId", prchsGrpId);
        return query.getResultList();
    }

    @Override
    public List<String> getEmailList() {
        Query query = getEntityManager().createQuery(
                "select u.email from User u");
        return query.getResultList();
    }

    @Override
    public List<String> getAllEmailsByRole(String role) {
        Query query = getEntityManager().createQuery(
                "select u.email from User u JOIN u.roles r JOIN u.status s where r.roleDescEng = :roleDescEng AND s.statusDescEng = 'Active'");
        query.setParameter("roleDescEng", role);
        return query.getResultList();
    }

    @Override
    public List<String> getActiveUserEmails() {
        Query query = getEntityManager().createQuery(
                "select u.email from User u JOIN u.status s where s.statusDescEng = 'Active'");
        return query.getResultList();
    }
}
