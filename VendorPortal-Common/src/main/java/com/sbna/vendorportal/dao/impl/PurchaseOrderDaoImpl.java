package com.sbna.vendorportal.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.PurchaseOrderDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.Material;
import com.sbna.vendorportal.dto.MaterialDesc;
import com.sbna.vendorportal.dto.Material_;
import com.sbna.vendorportal.dto.Plant;
import com.sbna.vendorportal.dto.Plant_;
import com.sbna.vendorportal.dto.PurchaseOrder;
import com.sbna.vendorportal.dto.PurchaseOrderItem;
import com.sbna.vendorportal.dto.PurchaseOrderItemsAsn;
import com.sbna.vendorportal.dto.PurchaseOrderItemsAsn_;
import com.sbna.vendorportal.dto.PurchaseOrder_;
import com.sbna.vendorportal.dto.PurchaseOrg;
import com.sbna.vendorportal.dto.PurchaseOrg_;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.pojo.AsnSearchForm;
import javax.persistence.criteria.Path;
import com.sbna.vendorportal.dto.Status;

@Repository(value = "purchaseOrderDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PurchaseOrderDaoImpl extends AbstractHibernateDao<PurchaseOrder>
        implements PurchaseOrderDao {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(PurchaseOrderDaoImpl.class);

    @Override
    public List<Long> getUniquePOs() {
        Query query = getEntityManager().createNamedQuery("getUniquePOs");
        return query.getResultList();
    }

    @Override
    public List<Long> getUniqueASNs() {
        Query query = getEntityManager().createNamedQuery("getUniqueASNs");
        return query.getResultList();

    }

    @Override
    public PurchaseOrder get(String poNo) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PurchaseOrder> criteria = builder
                .createQuery(PurchaseOrder.class);
        Root<PurchaseOrder> purchaseOrderRoot = criteria
                .from(PurchaseOrder.class);
        criteria.where(builder.equal(
                purchaseOrderRoot.get(PurchaseOrder_.poNo),
                poNo));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrders(User vendor, boolean isVendor) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PurchaseOrder> criteria = builder
                .createQuery(PurchaseOrder.class);

        Root<PurchaseOrder> purchaseOrderRoot = criteria
                .from(PurchaseOrder.class);
        List<Predicate> wherePredicates = new ArrayList<>();
        if (isVendor) {
            Path<User> user = purchaseOrderRoot.get("user");
            wherePredicates.add(builder.equal(user, vendor));
        }
        // always want to exclude deleted
        // wherePredicates.add(builder.equal(userRoot.get(User_.status), true));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public PurchaseOrderItemsAsn getASNByASNNo(String asnNo) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PurchaseOrderItemsAsn> criteria = builder
                .createQuery(PurchaseOrderItemsAsn.class);
        Root<PurchaseOrderItemsAsn> root = criteria
                .from(PurchaseOrderItemsAsn.class);
        criteria.where(builder.equal(root.get(PurchaseOrderItemsAsn_.id),
                asnNo));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public PurchaseOrderItemsAsn updateASN(PurchaseOrderItemsAsn asnDetails) {
        return getEntityManager().merge(asnDetails);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PurchaseOrder> getPurchaseOrdersFromFilter(
            Map<String, String> requestParams, boolean isVendor, User user) {
        String poNum = requestParams.get("poNo");
        String poDateMin = requestParams.get("poDateMin");
        String poDateMax = requestParams.get("poDateMax");
        String plant = requestParams.get("plantCode");
        String material = requestParams.get("materialCode");
        String queryString = "SELECT DISTINCT po FROM PurchaseOrder po  ";

        String joinQuery = " JOIN po.status stat ";
        String whereQuery = " WHERE stat.statusDescEng = :statusDescEng ";
        if (null != poNum) {
            whereQuery += " AND po.poNo =:poNum ";
        }
        if (null != poDateMin) {
            whereQuery += " AND po.poDate >=:poDateMin ";
        }
        if (isVendor && null != user) {
            whereQuery += " AND po.user =:user ";
        }
        if (null != poDateMax) {
            whereQuery += " AND po.poDate <=:poDateMax ";
        }
        if (null != plant || null != material) {
            joinQuery += " JOIN po.prchsOrderItems poi  ";
            if (null != plant) {
                whereQuery += " AND plnt.plantCode =:plant ";
                joinQuery += " JOIN poi.plant plnt ";
            }
            if (null != material) {
                whereQuery += " AND mat.materialCode =:material ";
                joinQuery += " JOIN poi.material mat ";
            }
        }
        queryString += joinQuery + whereQuery + " order by po.id desc";

        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("statusDescEng", ProjectConfig.PO_STATUS_ACK);
        if (null != poNum) {
            query.setParameter("poNum", poNum);
        }
        if (null != poDateMin) {

            query.setParameter("poDateMin", getFormatedDateForFilter(poDateMin + " 00:00:00.0000000"));
        }
        if (null != poDateMax) {

            query.setParameter("poDateMax", getFormatedDateForFilter(poDateMax + " 23:59:59.9999999"));
        }
        if (null != plant) {
            query.setParameter("plant", plant);
        }
        if (null != material) {
            query.setParameter("material", material);
        }
        if (isVendor && null != user) {
            query.setParameter("user", user);
        }

        return query.getResultList();
    }

    protected Date getFormatedDateForFilter(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss.sssssss");
        Date d = new Date();
        try {
            d = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public List<PurchaseOrder> getAsnDetails(String locale,
            AsnSearchForm asnSearch) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PurchaseOrder> query = builder
                .createQuery(PurchaseOrder.class);

        Root<PurchaseOrder> po = query.from(PurchaseOrder.class);
        Join<PurchaseOrder, PurchaseOrg> orgs = po.join("prchsOrg");
        Join<PurchaseOrder, PurchaseOrderItem> items = po
                .join("prchsOrderItems");
        Join<PurchaseOrderItem, PurchaseOrderItemsAsn> asns = items
                .join("prchsOrderItemsAsns");
        Join<PurchaseOrderItem, Plant> plants = items.join("plant");
        Join<PurchaseOrderItem, Material> materials = items.join("material");
        Join<Material, MaterialDesc> materialDescs = materials
                .join("materialDescs");
        Join<MaterialDesc, Language> languages = materialDescs.join("lang");
        List<Predicate> conditions = new ArrayList<Predicate>();
        conditions.add(builder.equal(languages.get("langKey"), locale));

        if (null != asnSearch) {
            // ASN Number search
            if (null != asnSearch.getFromAsnNo()
                    & null != asnSearch.getToAsnNo()) {
                Predicate condition = builder.between(
                        asns.get(PurchaseOrderItemsAsn_.id),
                        asnSearch.getFromAsnNo(), asnSearch.getToAsnNo());
                conditions.add(condition);
            } else if (null != asnSearch.getFromAsnNo()) {
                conditions.add(builder.equal(asns.get("asn_no"),
                        asnSearch.getFromAsnNo()));
            } else if (null != asnSearch.getToAsnNo()) {
                conditions.add(builder.equal(asns.get("asn_no"),
                        asnSearch.getToAsnNo()));
            }
            // Delivery Date
            if (null != asnSearch.getFromDeliveryDate()
                    & null != asnSearch.getToDeliveryDate()) {
                Predicate condition = builder.between(
                        asns.get(PurchaseOrderItemsAsn_.deliveryDate),
                        asnSearch.getFromDeliveryDate(),
                        asnSearch.getToDeliveryDate());
                conditions.add(condition);
            } else if (null != asnSearch.getFromDeliveryDate()) {
                conditions.add(builder.equal(asns.get("deliveryDate"),
                        asnSearch.getFromDeliveryDate()));
            } else if (null != asnSearch.getToDeliveryDate()) {
                conditions.add(builder.equal(asns.get("deliveryDate"),
                        asnSearch.getToDeliveryDate()));
            }
            // Plant Code
            if (null != asnSearch.getFromPlantCode()
                    & null != asnSearch.getToPlantCode()) {
                Predicate condition = builder.between(
                        plants.get(Plant_.plantCode),
                        asnSearch.getFromPlantCode(),
                        asnSearch.getToPlantCode());
                conditions.add(condition);
            } else if (null != asnSearch.getFromPlantCode()) {
                conditions.add(builder.equal(plants.get("plantCode"),
                        asnSearch.getFromPlantCode()));
            } else if (null != asnSearch.getToPlantCode()) {
                conditions.add(builder.equal(plants.get("plantCode"),
                        asnSearch.getToPlantCode()));
            }
            // Material Code
            if (null != asnSearch.getFromMaterialCode()
                    & null != asnSearch.getToMaterialCode()) {
                Predicate condition = builder.between(
                        materials.get(Material_.materialCode),
                        asnSearch.getFromMaterialCode(),
                        asnSearch.getToMaterialCode());
                conditions.add(condition);
            } else if (null != asnSearch.getFromMaterialCode()) {
                conditions.add(builder.equal(materials.get("materialCode"),
                        asnSearch.getFromMaterialCode()));
            } else if (null != asnSearch.getToMaterialCode()) {
                conditions.add(builder.equal(materials.get("materialCode"),
                        asnSearch.getToMaterialCode()));
            }
            // Purchase Order Number
            if (null != asnSearch.getFromPoNo() & null != asnSearch.getToPoNo()) {
                Predicate condition = builder.between(
                        po.get(PurchaseOrder_.poNo), asnSearch.getFromPoNo(),
                        asnSearch.getToPoNo());
                conditions.add(condition);
            } else if (null != asnSearch.getFromPoNo()) {
                conditions.add(builder.equal(po.get("poNo"),
                        asnSearch.getFromPoNo()));
            } else if (null != asnSearch.getToPoNo()) {
                conditions.add(builder.equal(po.get("poNo"),
                        asnSearch.getToPoNo()));
            }
            // Purchase Organization
            if (null != asnSearch.getFromPurchaseOrg()
                    & null != asnSearch.getToPurchaseOrg()) {
                Predicate condition = builder.between(
                        orgs.get(PurchaseOrg_.prchsOrgCode),
                        asnSearch.getFromPurchaseOrg(),
                        asnSearch.getToPurchaseOrg());
                conditions.add(condition);
            } else if (null != asnSearch.getFromPurchaseOrg()) {
                conditions.add(builder.equal(orgs.get("prchsOrgCode"),
                        asnSearch.getFromPurchaseOrg()));
            } else if (null != asnSearch.getToPurchaseOrg()) {
                conditions.add(builder.equal(orgs.get("prchsOrgCode"),
                        asnSearch.getToPurchaseOrg()));
            }
        }

        TypedQuery<PurchaseOrder> typedQuery = getEntityManager().createQuery(
                query.select(po).where(conditions.toArray(new Predicate[]{}))
                .orderBy(builder.asc(po.get("id"))).distinct(true));
        List<PurchaseOrder> tstlist = typedQuery.getResultList();        
        return tstlist;

    }

    @Override
    public void handleUniqueViolation(List<PurchaseOrder> entities) {
        List<PurchaseOrder> dbEntities = new ArrayList<PurchaseOrder>();
        dbEntities = getPurchaseOrders(null, false);
        for (PurchaseOrder entity : entities) {
            for (PurchaseOrder dbEntity : dbEntities) {
                if (entity.getPoNo().equals(dbEntity.getPoNo())) {
                    entity.setId(dbEntity.getId());
                }
            }
        }
    }
    
	@Override
	public List<PurchaseOrder> getNotAcknowledgePOs() {
		String queryString = "SELECT DISTINCT po FROM PurchaseOrder po  ";
		String joinQuery = " JOIN po.status stat ";
		String whereQuery = " WHERE stat.statusDescEng = :statusDescEng ";
		queryString += joinQuery + whereQuery + " order by po.id desc";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("statusDescEng", ProjectConfig.PO_STATUS_NOT_ACK);
		return query.getResultList();
	}

}
