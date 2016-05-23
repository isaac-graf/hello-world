package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sbna.vendorportal.config.TableConfig;

@Entity
@Table(name = TableConfig.TABLE_AUDITTRAIL)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_audittrail", allocationSize = 1)
public class AuditTrail extends BaseModel {

	private static final long serialVersionUID = -8309150906560351973L;


	@Column(name = "audit_table",nullable = false)
	private String auditTable;
	
	@Column(name = "audit_column",nullable = false)
	private String auditColumn;
		
	@Column(name = "old_value",nullable = false)
	private String oldValue;
	
	@Column(name = "new_value",nullable = false)
	private String newValue;
	
	@Column(name = "audit_desc",nullable = false)
	private String auditDesc;
	
	@Column(name = "sap_trans_code")
	private String sapTransCode;

	public String getAuditTable() {
		return auditTable;
	}

	public void setAuditTable(String auditTable) {
		this.auditTable = auditTable;
	}

	public String getAuditColumn() {
		return auditColumn;
	}

	public void setAuditColumn(String auditColumn) {
		this.auditColumn = auditColumn;
	}

	
	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	
	public String getSapTransCode() {
		return sapTransCode;
	}

	public void setSapTransCode(String sapTransCode) {
		this.sapTransCode = sapTransCode;
	}

	@Override
	public String toString() {
		return "AuditTrail [auditTable=" + auditTable + ", auditColumn="
				+ auditColumn + ", oldValue=" + oldValue + ", newValue="
				+ newValue + ", auditDesc=" + auditDesc + "]";
	}

	
	
}
