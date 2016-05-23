package com.sbna.vendorportal.dao;

import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.Material;
import com.sbna.vendorportal.dto.Plant;
import com.sbna.vendorportal.dto.PurchaseOrder;
import com.sbna.vendorportal.dto.PurchaseOrderItem;
import com.sbna.vendorportal.pojo.PurchaseOrderItemHelper;

public interface PurchaseOrderItemDao {

    PurchaseOrderItem get(Long id);

    PurchaseOrderItem get(String poNo, String poItemNo);

    List<PurchaseOrderItem> getPurchaseOrderItems();

    List<PurchaseOrderItem> getPurchaseOrderItemsFromFilter(Map<String, String> requestParams);

    void save(PurchaseOrderItem purchaseOrderItem);

    void save(List<PurchaseOrderItem> poiList);

    List<PurchaseOrderItemHelper> getPurchaseOrderItems(Long poId,
            String langKey);

    public PurchaseOrderItem getPOI(PurchaseOrder purchaseOrder, Plant plant, Material material);

    List<PurchaseOrderItemHelper> getPurchaseOrderItemsByPoNo(String poNo, String langKey);

}
