package com.sbna.vendorportal.dao;

import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.AuditTrail;

public interface AuditTrailDao {

	List<AuditTrail> getAuditTrails();
	AuditTrail get(Long id);
    void save(AuditTrail auditTrail);
    void save(List<AuditTrail> auditTrailList);
    void delete(Long id);
    List<AuditTrail> getAuditTrails(Map<String,String> requestParams);
}
