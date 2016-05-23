package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.VendorProfileDao;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.dto.VendorProfile;
import com.sbna.vendorportal.dto.VendorProfile_;

@Repository(value = "vendorProfileDao")
@Transactional(propagation = Propagation.MANDATORY)
public class VendorProfileDaoImpl extends AbstractHibernateDao<VendorProfile> implements VendorProfileDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorProfileDaoImpl.class);

    @Override
    public void setProfile(User user, final String lang, String rowCreatedBy, String rowUpdatedBy) {
        VendorProfile vendorProfile = new VendorProfile();
        vendorProfile.setRowCreatedBy(rowCreatedBy);
        vendorProfile.setRowUpdatedBy(rowUpdatedBy);
        vendorProfile.setUser(user);
        save(vendorProfile);
   }

    @Override
    public VendorProfile fetchVendorProfile(long id) {
        System.out.println("fetching");
        VendorProfile vendorProfile = null;
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<VendorProfile> criteria = builder.createQuery(VendorProfile.class);
        Root<VendorProfile> root = criteria.from(VendorProfile.class);
        criteria.where(builder.equal(root.get(VendorProfile_.user), id));
        try {
            System.out.println("----------------------------fetchVendorProfile----------------------------------");
            vendorProfile = getEntityManager().createQuery(criteria).getSingleResult();

        } catch (NoResultException e) {
            System.out.println("catching exception");
        }
        return vendorProfile;
    }

    @Override
    public List<VendorProfile> getWaitingRequets() {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<VendorProfile> criteria = builder.createQuery(VendorProfile.class);
        Root<VendorProfile> vendorProfileRoot = criteria.from(VendorProfile.class);
        criteria.select(vendorProfileRoot);
        List<VendorProfile> vendorProfile = new ArrayList<VendorProfile>();
        try {
            vendorProfile = getEntityManager().createQuery(criteria).getResultList();
        } catch (NoResultException e) {
            System.out.println("catching exception");
        }
        return vendorProfile;
    }
}
