package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.AuditPlan;
import com.sbna.vendorportal.dto.User;
import java.util.Date;
import java.util.List;

public interface AuditPlanDao {

    AuditPlan get(Long id);

    void save(AuditPlan auditPlan);

    List<AuditPlan> getAuditPlans(Date startDate, Date endDate);
    
    List<AuditPlan> getAuditPlans(Date startDate, Date endDate, User vendor);

    void delete(Long id);
}
