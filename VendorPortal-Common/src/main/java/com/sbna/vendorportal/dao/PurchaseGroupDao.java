package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.PurchaseGroup;

public interface PurchaseGroupDao{

	List<PurchaseGroup> getPurchaseGroups();

	List<PurchaseGroup> getPurchaseGroupsByLang(String lang);

	PurchaseGroup get(Long id);

	PurchaseGroup get(String purchaseGroupID);
	
	List<PurchaseGroup> getAll();

	void save(PurchaseGroup purchaseGroup);

	void delete(Long id);
	
	void bulkPersist(List<PurchaseGroup> entities);

	void handleUniqueViolation(List<PurchaseGroup> purchaseGrps);
}
