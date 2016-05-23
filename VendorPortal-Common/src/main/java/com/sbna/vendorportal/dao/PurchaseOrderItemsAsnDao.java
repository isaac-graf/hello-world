package com.sbna.vendorportal.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.PurchaseOrderItemsAsn;
import com.sbna.vendorportal.pojo.AsnBuyerForm;
import com.sbna.vendorportal.pojo.AsnVendorForm;

public interface PurchaseOrderItemsAsnDao {
	void save(List<PurchaseOrderItemsAsn> purchaseOrderItemsAsnList);

	void save(PurchaseOrderItemsAsn purchaseOrderItemsAsn);

	List<AsnVendorForm> getAsnForVendor(String curLocale, Long curUserId,
			Map<String, Date> qDateMap);

	List<AsnBuyerForm> getAsnForBuyer(String curLocale, Long curUserId,
			Map<String, Date> qDateMap);

	PurchaseOrderItemsAsn get(Long id);

	List<PurchaseOrderItemsAsn> getAsnGroup(String asnGroupId);
}
