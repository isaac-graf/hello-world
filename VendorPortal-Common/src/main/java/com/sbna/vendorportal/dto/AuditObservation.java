package com.sbna.vendorportal.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the audit_obsr database table.
 *
 */
@Entity
@Table(name = "audit_obsr")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_auditobsr", allocationSize = 1)
//@NamedQuery(name = "AuditObsr.findAll", query = "SELECT a FROM AuditObsr a")
public class AuditObservation extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "corr_actn")
    private String corrActn;

    @Column(name = "corr_actn_date")
    private Date corrActnDate;

    @Column(name = "obsr_date")
    private Date obsrDate;

    @Column(name = "obsr_detl")
    private String obsrDetl;

    @Column(name = "respn_prsn")
    private String respnPrsn;

    private Date tdc;
    
    // bi-directional many-to-one association to AuditPlan
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_plan_id")
    private AuditPlan auditPlan;

    // bi-directional many-to-one association to Status
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_obsr_status_id")
    private Status status;

    public AuditObservation() {
    }

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getCorrActn() {
		return corrActn;
	}

	public void setCorrActn(String corrActn) {
		this.corrActn = corrActn;
	}

	public Date getCorrActnDate() {
		return corrActnDate;
	}

	public void setCorrActnDate(Date corrActnDate) {
		this.corrActnDate = corrActnDate;
	}

	public Date getObsrDate() {
		return obsrDate;
	}

	public void setObsrDate(Date obsrDate) {
		this.obsrDate = obsrDate;
	}

	public String getObsrDetl() {
		return obsrDetl;
	}

	public void setObsrDetl(String obsrDetl) {
		this.obsrDetl = obsrDetl;
	}

	public String getRespnPrsn() {
		return respnPrsn;
	}

	public void setRespnPrsn(String respnPrsn) {
		this.respnPrsn = respnPrsn;
	}

	public Date getTdc() {
		return tdc;
	}

	public void setTdc(Date tdc) {
		this.tdc = tdc;
	}

	public AuditPlan getAuditPlan() {
		return auditPlan;
	}

	public void setAuditPlan(AuditPlan auditPlan) {
		this.auditPlan = auditPlan;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AuditObservation [closeDate=" + closeDate + ", corrActn="
				+ corrActn + ", corrActnDate=" + corrActnDate + ", obsrDate="
				+ obsrDate + ", obsrDetl=" + obsrDetl + ", respnPrsn="
				+ respnPrsn + ", tdc=" + tdc + ", auditPlan=" + auditPlan
				+ ", status=" + status + "]";
	}

    
}
