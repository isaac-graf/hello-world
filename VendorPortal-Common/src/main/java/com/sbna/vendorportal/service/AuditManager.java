package com.sbna.vendorportal.service;

import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.AuditObservation;
import com.sbna.vendorportal.dto.AuditTrail;
import com.sbna.vendorportal.dto.BaseModel;
import com.sbna.vendorportal.dto.User;

public interface AuditManager {

    void save(AuditTrail auditTrail);

    void save(List<AuditTrail> auditTrail);

    void delete(Long id);

    List<AuditTrail> getAuditTrails(Map<String, String> requestParams);
    
    List<String> getUserFullName();

	boolean createAuditTrails(String auditTable, BaseModel oldModel, BaseModel newModel, String auditDesc,
			String sapTransCode, User user);

	List<AuditTrail> getAuditTrailList(String auditTable, BaseModel oldModel,
			BaseModel newModel, String auditDesc, String sapTransCode,
			String rowCreatedBy, String rowUpdatedBy)
			throws IllegalArgumentException, IllegalAccessException;
	
    List<AuditObservation> getAuditObservation(Long planId);
    AuditObservation getObsrv(Long obsrvId);
    void save(AuditObservation auditObservation);

}
