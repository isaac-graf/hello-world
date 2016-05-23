package com.sbna.vendorportal.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.aspect.AuditTransient;
import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.config.SecurityConfig;
import com.sbna.vendorportal.dao.AuditObsrvDao;
import com.sbna.vendorportal.dao.AuditPlanDao;
import com.sbna.vendorportal.dao.AuditTrailDao;
import com.sbna.vendorportal.dao.ProfileDao;
import com.sbna.vendorportal.dto.AuditObservation;
import com.sbna.vendorportal.dto.AuditPlan;
import com.sbna.vendorportal.dto.AuditTrail;
import com.sbna.vendorportal.dto.BaseModel;
import com.sbna.vendorportal.dto.Profile;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.service.AuditManager;

@Service(value = "auditManager")
@Transactional(propagation = Propagation.REQUIRED)
public class AuditManagerImpl implements AuditManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuditManagerImpl.class);	

    @Inject
    private AuditTrailDao auditTrailDao;
    
    @Inject
    private ProfileDao profileDao;
    
    @Inject
    private AuditPlanDao auditPlanDao;
    
    @Inject
    private AuditObsrvDao auditObsrvDao;
    

    

    @Override
    public void save(List<AuditTrail> auditTrailList) {
        auditTrailDao.save(auditTrailList);
    }

    @Override
    public void delete(Long id) {
        auditTrailDao.delete(id);
    }

    @Override
    @Secured(SecurityConfig.ROLE_ADMIN)
    public List<AuditTrail> getAuditTrails(Map<String, String> requestParams) {
        return auditTrailDao.getAuditTrails(requestParams);
    }

    @Override
    public void save(AuditTrail auditTrail) {
        auditTrailDao.save(auditTrail);
    }

	@Override
	public List<String> getUserFullName() {
		List<Profile> lstProfile = profileDao.getProfiles();
		List<String> lstUsrNames = new ArrayList<String>();
		if(lstProfile.size() != 0 && lstProfile !=null){
			for(Profile profile : lstProfile){
				lstUsrNames.add(profile.getFullName());
			}
		}
		return lstUsrNames;
	}
	
	
	@Override
	public boolean createAuditTrails(String auditTable,
            BaseModel oldModel, BaseModel newModel,
            String auditDesc, String sapTransCode, User user){
		try {
			String fullName = "";
			if(user.inRole(SecurityConfig.ROLE_VENDOR)){
				fullName = user.getProfile().getFullName();
			}else{
				fullName = user.getProfile().getFirstName() + " " + user.getProfile().getLastName();
			}
			List<AuditTrail> auditTrails = getAuditTrailList(auditTable, oldModel, newModel, auditDesc, sapTransCode, fullName, fullName);
			save(auditTrails);
			return true;
		} catch (Exception e) {
			LOGGER.error("Error in creating audit trail :"+e.getMessage(), e);
			e.printStackTrace();
		}
		return false;
	}
	
	
    @SuppressWarnings("unchecked")
    @Override
    public List<AuditTrail> getAuditTrailList(String auditTable,
            BaseModel oldModel, BaseModel newModel,
            String auditDesc, String sapTransCode, String rowCreatedBy, String rowUpdatedBy)
            throws IllegalArgumentException, IllegalAccessException {
        List<AuditTrail> auditTrailList = new ArrayList<AuditTrail>();
        if (oldModel == null) {
            // new record inserted
            AuditTrail auditTrail = new AuditTrail();
            auditTrail.setAuditTable(auditTable);
            auditTrail.setAuditColumn("NA");
            auditTrail.setOldValue("NA");
            auditTrail.setNewValue(newModel.auditString());
            auditTrail.setAuditDesc(auditDesc);
            auditTrail.setSapTransCode(sapTransCode);
            auditTrail.setRowCreatedBy(rowCreatedBy);
            auditTrail.setRowUpdatedBy(rowUpdatedBy);
            auditTrailList.add(auditTrail);
        } else {
            Field[] fields = oldModel.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                AuditTransient auditTransient= field.getAnnotation(AuditTransient.class);
                if(auditTransient != null){
                	continue;
                }

                if (BaseModel.class.isAssignableFrom(field.getType())) {
                    // foreign key value change
                    BaseModel oldBaseModel = (BaseModel) field
                            .get(oldModel);
                    BaseModel newBaseModel = (BaseModel) field
                            .get(newModel);
                    Long oldId = oldBaseModel != null ? oldBaseModel.getId()
                            : null;
                    Long newId = newBaseModel != null ? newBaseModel.getId()
                            : null;
                    if (oldId != null) {
                        if (oldId.equals(newId) == false) {
                            AuditTrail auditTrail = initAuditTrail(auditTable,
                                    auditDesc, sapTransCode,
                                    getTableColumnName(field),
                                    String.valueOf(oldId),
                                    String.valueOf(newId), rowCreatedBy, rowUpdatedBy);
                            auditTrailList.add(auditTrail);
                        }
                    } else {
                        if (newId != null) {
                            AuditTrail auditTrail = initAuditTrail(auditTable,
                                    auditDesc, sapTransCode,
                                    getTableColumnName(field),
                                    String.valueOf(oldId),
                                    String.valueOf(newId), rowCreatedBy, rowUpdatedBy);
                            auditTrailList.add(auditTrail);
                        }
                    }

                } else if (Collection.class.isAssignableFrom(field.getType())) {
                    JoinTable joinTable = field.getAnnotation(JoinTable.class);
                    if (joinTable != null) {
                        // handle only many to many
                        Collection<BaseModel> oldBaseModels = (Collection<BaseModel>) field
                                .get(oldModel);
                        Collection<BaseModel> newBaseModels = (Collection<BaseModel>) field
                                .get(newModel);
                        if (oldBaseModels != null) {
                            if (oldBaseModels.equals(newBaseModels) == false) {
                                Collection<BaseModel> clonedOldBaseModels = (Collection<BaseModel>) SerializationUtils
                                        .clone((Serializable) oldBaseModels);
                                Collection<BaseModel> clonedNewBaseModels = (Collection<BaseModel>) SerializationUtils
                                        .clone((Serializable) newBaseModels);
                                if (newBaseModels != null) {
                                    clonedOldBaseModels
                                            .removeAll(newBaseModels);

                                    clonedNewBaseModels
                                            .removeAll(oldBaseModels);

                                    for (BaseModel baseModel : clonedNewBaseModels) {
                                        AuditTrail auditTrail = initAuditTrail(
                                                joinTable.name(),
                                                auditDesc,
                                                sapTransCode,
                                                joinTable.joinColumns()[0]
                                                .name()
                                                + ProjectConfig.AUDIT_TRAIL_DELIMITER
                                                + joinTable
                                                .inverseJoinColumns()[0]
                                                .name(),
                                                "",
                                                ((BaseModel) newModel)
                                                .getId()
                                                + ProjectConfig.AUDIT_TRAIL_DELIMITER
                                                + baseModel.getId(), rowCreatedBy, rowUpdatedBy);
                                        auditTrailList.add(auditTrail);
                                    }

                                }

                                for (BaseModel baseModel : clonedOldBaseModels) {
                                    AuditTrail auditTrail = initAuditTrail(
                                            joinTable.name(),
                                            auditDesc,
                                            sapTransCode,
                                            joinTable.joinColumns()[0].name()
                                            + ProjectConfig.AUDIT_TRAIL_DELIMITER
                                            + joinTable
                                            .inverseJoinColumns()[0]
                                            .name(),
                                            ((BaseModel) oldModel).getId()
                                            + ProjectConfig.AUDIT_TRAIL_DELIMITER + baseModel.getId(),
                                            "", rowCreatedBy, rowUpdatedBy);
                                    auditTrailList.add(auditTrail);
                                }
                            }
                        } else {
                            if (newBaseModels != null) {
                                for (BaseModel baseModel : newBaseModels) {
                                    AuditTrail auditTrail = initAuditTrail(
                                            joinTable.name(),
                                            auditDesc,
                                            sapTransCode,
                                            joinTable.joinColumns()[0].name()
                                            + ProjectConfig.AUDIT_TRAIL_DELIMITER
                                            + joinTable
                                            .inverseJoinColumns()[0]
                                            .name(), "",
                                            ((BaseModel) newModel).getId()
                                            + ProjectConfig.AUDIT_TRAIL_DELIMITER + baseModel.getId(), rowCreatedBy, rowUpdatedBy);
                                    auditTrailList.add(auditTrail);
                                }
                            }
                        }
                    }
                } else {
                    Object oldVal = field.get(oldModel);
                    Object newVal = field.get(newModel);
                    if (oldVal != null) {
                        if (oldVal.equals(newVal) == false) {
                            AuditTrail auditTrail = initAuditTrail(auditTable,
                                    auditDesc, sapTransCode,
                                    getTableColumnName(field),
                                    String.valueOf(oldVal),
                                    String.valueOf(newVal), rowCreatedBy, rowUpdatedBy);
                            auditTrailList.add(auditTrail);
                        }
                    } else {
                        if (newVal != null) {
                            AuditTrail auditTrail = initAuditTrail(auditTable,
                                    auditDesc, sapTransCode,
                                    getTableColumnName(field),
                                    String.valueOf(oldVal),
                                    String.valueOf(newVal), rowCreatedBy, rowUpdatedBy);
                            auditTrailList.add(auditTrail);
                        }
                    }
                }
            }
        }
        return auditTrailList;
    }

    private AuditTrail initAuditTrail(String auditTable, String auditDesc,
            String sapTransCode, String colName, String oldVal, String newVal, 
            String rowCreatedBy, String rowUpdatedBy) {
        AuditTrail auditTrail = new AuditTrail();
        auditTrail.setAuditTable(auditTable);
        auditTrail.setAuditDesc(auditDesc);
        auditTrail.setSapTransCode(sapTransCode);
        auditTrail.setAuditColumn(colName);
        auditTrail.setOldValue(oldVal);
        auditTrail.setNewValue(newVal);
        auditTrail.setRowCreatedBy(rowCreatedBy);
        auditTrail.setRowUpdatedBy(rowUpdatedBy);
        return auditTrail;
    }

    private String getTableColumnName(Field field) {
        Column colAnnotation = field.getAnnotation(Column.class);
        JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
        if (colAnnotation != null) {
            return colAnnotation.name();
        } else if (joinColumn != null) {
            return joinColumn.name();
        }
        {
            return field.getName();
        }

    }

	@Override
	public List<AuditObservation> getAuditObservation(Long planId) {
		AuditPlan auditPlan=auditPlanDao.get(planId);
		return auditObsrvDao.getAuditObservation(auditPlan);
	}

	@Override
	public AuditObservation getObsrv(Long obsrvId) {
		return auditObsrvDao.get(obsrvId);
	}

	@Override
	public void save(AuditObservation auditObservation) {
		auditObsrvDao.save(auditObservation);		
	}
	
}
