package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the prchs_org database table.
 * 
 */
@Entity
@Table(name = "prchs_org")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_prchsorg", allocationSize = 1)
//@NamedQuery(name = "PurchaseOrg.findAll", query = "SELECT p FROM PurchaseOrg p")
public class PurchaseOrg extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "prchs_org_code")
	private String prchsOrgCode;

	@Column(name = "prchs_org_desc_eng")
	private String prchsOrgDescEng;

	// bi-directional many-to-one association to AuditPlan
	@OneToMany(mappedBy = "prchsOrgBean")
	private List<AuditPlan> auditPlans;

	// bi-directional many-to-one association to PurchaseOrder
	@OneToMany(mappedBy = "prchsOrg")
	private List<PurchaseOrder> prchsOrders;

	// bi-directional many-to-one association to Status
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prchs_org_status_id")
	private Status status;

	// bi-directional many-to-one association to PurchaseOrgDesc
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "prchsOrg")
	private List<PurchaseOrgDesc> prchsOrgDescs;

	public PurchaseOrg() {
	}

	public String getPurchaseOrgCode() {
		return this.prchsOrgCode;
	}

	public void setPurchaseOrgCode(String prchsOrgCode) {
		this.prchsOrgCode = prchsOrgCode;
	}

	public String getPurchaseOrgDescEng() {
		return this.prchsOrgDescEng;
	}

	public void setPurchaseOrgDescEng(String prchsOrgDescEng) {
		this.prchsOrgDescEng = prchsOrgDescEng;
	}

	public List<AuditPlan> getAuditPlans() {
		return this.auditPlans;
	}

	public void setAuditPlans(List<AuditPlan> auditPlans) {
		this.auditPlans = auditPlans;
	}

	public AuditPlan addAuditPlan(AuditPlan auditPlan) {
		getAuditPlans().add(auditPlan);
		auditPlan.setPurchaseOrgBean(this);

		return auditPlan;
	}

	public AuditPlan removeAuditPlan(AuditPlan auditPlan) {
		getAuditPlans().remove(auditPlan);
		auditPlan.setPurchaseOrgBean(null);

		return auditPlan;
	}

	public List<PurchaseOrder> getPurchaseOrders() {
		return this.prchsOrders;
	}

	public void setPurchaseOrders(List<PurchaseOrder> prchsOrders) {
		this.prchsOrders = prchsOrders;
	}

	public PurchaseOrder addPurchaseOrder(PurchaseOrder prchsOrder) {
		getPurchaseOrders().add(prchsOrder);
		prchsOrder.setPrchsOrg(this);

		return prchsOrder;
	}

	public PurchaseOrder removePurchaseOrder(PurchaseOrder prchsOrder) {
		getPurchaseOrders().remove(prchsOrder);
		prchsOrder.setPrchsOrg(null);

		return prchsOrder;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<PurchaseOrgDesc> getPurchaseOrgDescs() {
		return this.prchsOrgDescs;
	}

	public void setPurchaseOrgDescs(List<PurchaseOrgDesc> prchsOrgDescs) {
		this.prchsOrgDescs = prchsOrgDescs;
	}

	public PurchaseOrgDesc addPurchaseOrgDesc(PurchaseOrgDesc prchsOrgDesc) {
		getPurchaseOrgDescs().add(prchsOrgDesc);
		prchsOrgDesc.setPrchsOrg(this);

		return prchsOrgDesc;
	}

	public PurchaseOrgDesc removePurchaseOrgDesc(PurchaseOrgDesc prchsOrgDesc) {
		getPurchaseOrgDescs().remove(prchsOrgDesc);
		prchsOrgDesc.setPrchsOrg(null);

		return prchsOrgDesc;
	}

}