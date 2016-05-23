package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.PurchaseOrg;

public interface PurchaseOrgDao {

	List<PurchaseOrg> getPurchaseOrgs();

	List<PurchaseOrg> getPurchaseOrgsByLang(String lang);

	PurchaseOrg get(Long id);
	
	List<PurchaseOrg> getAll();

	PurchaseOrg get(String purchaseOrgID);

	void save(PurchaseOrg purchaseOrg);

	void delete(Long id);
	
	void bulkPersist(List<PurchaseOrg> entities);

	void handleUniqueViolation(List<PurchaseOrg> purchaseOrgs);
}
