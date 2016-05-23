package com.sbna.vendorportal.dao;

import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.PurchaseOrder;
import com.sbna.vendorportal.dto.PurchaseOrderItemsAsn;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.pojo.AsnSearchForm;

public interface PurchaseOrderDao {

    List<Long> getUniquePOs();

    List<Long> getUniqueASNs();

    PurchaseOrder get(Long id);

    List<PurchaseOrder> getPurchaseOrders(User vendor, boolean isVendor);

    List<PurchaseOrder> getPurchaseOrdersFromFilter(Map<String, String> requestParams, boolean isVendor, User user);

    void save(PurchaseOrder purchaseOrder);

    PurchaseOrder get(String poNo);

    PurchaseOrderItemsAsn getASNByASNNo(String asnNo);

    PurchaseOrderItemsAsn updateASN(PurchaseOrderItemsAsn asnDetails);

    List<PurchaseOrder> getAsnDetails(String locale, AsnSearchForm asnSearch);

    void bulkPersist(List<PurchaseOrder> purchaseOrders);

    void handleUniqueViolation(List<PurchaseOrder> entities);
    
    List<PurchaseOrder> getNotAcknowledgePOs();

}
