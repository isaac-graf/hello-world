package com.sbna.vendorportal.dao;

import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.AuditObservation;
import com.sbna.vendorportal.dto.AuditPlan;
import com.sbna.vendorportal.dto.AuditTrail;

public interface AuditObsrvDao {

	List<AuditObservation> getAuditObservation(AuditPlan auditPlan);
	AuditObservation get(Long id);
    void save(AuditObservation auditObservation);
    void delete(Long id);
}
