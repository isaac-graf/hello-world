package com.sbna.vendorportal.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.config.QueryConstants;
import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.PurchaseOrderItemDao;
import com.sbna.vendorportal.dto.Material;
import com.sbna.vendorportal.dto.Plant;
import com.sbna.vendorportal.dto.PurchaseOrder;
import com.sbna.vendorportal.dto.PurchaseOrderItem;
import com.sbna.vendorportal.pojo.PurchaseOrderItemHelper;

@Repository(value = "purchaseOrderItemDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PurchaseOrderItemDaoImpl extends AbstractHibernateDao<PurchaseOrderItem> implements PurchaseOrderItemDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderItemDaoImpl.class);

    @Override
    public void save(List<PurchaseOrderItem> poiList) {
        bulkPersist(poiList);
    }

    @Override
    public List<PurchaseOrderItem> getPurchaseOrderItems() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PurchaseOrderItem> criteria = builder.createQuery(PurchaseOrderItem.class);

        Root<PurchaseOrderItem> purchaseOrderItemRoot = criteria.from(PurchaseOrderItem.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PurchaseOrderItem> getPurchaseOrderItemsFromFilter(Map<String, String> requestParams) {

        String poNum = requestParams.get("poNo");
        String poDateMin = requestParams.get("poDateMin");
        String poDateMax = requestParams.get("poDateMax");
        String plant = requestParams.get("plantCode");
        String material = requestParams.get("materialCode");
        String venCode = requestParams.get("venCode");
        String queryString = "SELECT DISTINCT poi FROM PurchaseOrderItem poi  ";

        String joinQuery = " JOIN  poi.prchsOrder po JOIN po.status stat ";
        String whereQuery = " WHERE stat.statusDescEng = :statusDescEng ";

        if (null != poNum) {
            whereQuery += " AND po.poNo =:poNum ";
        }

        if (null != poDateMin) {
            whereQuery += " AND po.poDate >=:poDateMin ";
        }
        if (null != poDateMax) {
            whereQuery += " AND po.poDate <=:poDateMax ";
        }

        if (null != venCode) {
            if (null != venCode) {
                whereQuery += " AND usr.id =:venCode ";
            }
            joinQuery += " JOIN po.user usr ";
        } else {
            whereQuery += " AND st.statusDescEng =:statDesc ";
            joinQuery += " JOIN po.user usr JOIN usr.status st ";
        }

        if (null != plant) {
            if (null != plant) {
                whereQuery += " AND plnt.plantCode =:plant ";
            }
            joinQuery += " JOIN poi.plant plnt ";
        }

        if (null != material) {
            if (null != material) {
                whereQuery += " AND mat.materialCode =:material ";
            }
            joinQuery += " JOIN poi.material mat ";
        }
        queryString += joinQuery + whereQuery + " order by poi.id desc";

        LOGGER.debug(queryString);

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
        if (null != venCode) {
            query.setParameter("venCode", Long.parseLong(venCode));
        } else {
            query.setParameter("statDesc", "Active");
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
    public List<PurchaseOrderItemHelper> getPurchaseOrderItems(Long poId, String langKey) {
        Query query = getEntityManager().createQuery(QueryConstants.POI_WITH_BALANCE_QTY_BY_PO_ID);
        query.setParameter("poId", poId);
        query.setParameter("langKey", langKey);
        return query.getResultList();
    }

    @Override
    public List<PurchaseOrderItemHelper> getPurchaseOrderItemsByPoNo(String poNo, String langKey) {
        Query query = getEntityManager().createQuery(QueryConstants.POI_BY_PO_NO);
        query.setParameter("poNo", poNo);
        query.setParameter("langKey", langKey);
        return query.getResultList();
    }

    @Override
    public PurchaseOrderItem getPOI(PurchaseOrder purchaseOrder, Plant plant, Material material) {

        if (null == plant || null == material) {
            return null;
        }
        String queryString = "SELECT DISTINCT poi FROM PurchaseOrderItem poi  ";
        String joinQuery = " JOIN  poi.prchsOrder po JOIN poi.material mat JOIN poi.plant pln";
        String whereQuery = " WHERE po.id = :poid and mat.id = :matid and pln.id = :plantid";
        queryString += joinQuery + whereQuery;
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("poid", purchaseOrder.getId());
        query.setParameter("plantid", plant.getId());
        query.setParameter("matid", material.getId());
        LOGGER.debug(queryString);
        if (query.getResultList().size() == 0) {
            return null;
        } else {
            return (PurchaseOrderItem) query.getResultList().get(0);
        }

    }

    @Override
    public PurchaseOrderItem get(String poNo, String poItemNo) {
        String queryString = "SELECT poi FROM PurchaseOrderItem poi  ";
        String joinQuery = " JOIN  poi.prchsOrder po ";
        String whereQuery = " WHERE po.poNo = :poNo and poi.itemNo = :poItemNo";
        queryString += joinQuery + whereQuery;
        Query query = getEntityManager().createQuery(queryString);
        query.setParameter("poNo", poNo);
        query.setParameter("poItemNo", poItemNo);
        if (query.getResultList().size() == 0) {
            return null;
        } else {
            return (PurchaseOrderItem) query.getSingleResult();
        }

    }
}
