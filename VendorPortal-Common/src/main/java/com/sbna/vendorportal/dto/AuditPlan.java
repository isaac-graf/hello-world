package com.sbna.vendorportal.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the audit_plan database table.
 *
 */
@Entity
@Table(name = "audit_plan")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_auditplan", allocationSize = 1)
//@NamedQuery(name = "AuditPlan.findAll", query = "SELECT a FROM AuditPlan a")
public class AuditPlan extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "audit_date")
    private Date auditDate;

    // bi-directional many-to-one association to AuditObsr
    @OneToMany(mappedBy = "auditPlan")
    private List<AuditObservation> auditObsrs;

    // bi-directional many-to-one association to PurchaseOrg
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prchs_org")
    private PurchaseOrg prchsOrgBean;

    // bi-directional many-to-one association to User
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auditor_id")
    private User usr1;

    // bi-directional many-to-one association to User
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private User usr2;

    public AuditPlan() {
    }

    public Date getAuditDate() {
        return this.auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public List<AuditObservation> getAuditObsrs() {
        return this.auditObsrs;
    }

    public void setAuditObsrs(List<AuditObservation> auditObsrs) {
        this.auditObsrs = auditObsrs;
    }

    public AuditObservation addAuditObsr(AuditObservation auditObsr) {
        getAuditObsrs().add(auditObsr);
        auditObsr.setAuditPlan(this);

        return auditObsr;
    }

    public AuditObservation removeAuditObsr(AuditObservation auditObsr) {
        getAuditObsrs().remove(auditObsr);
        auditObsr.setAuditPlan(null);

        return auditObsr;
    }

    public PurchaseOrg getPurchaseOrgBean() {
        return this.prchsOrgBean;
    }

    public void setPurchaseOrgBean(PurchaseOrg prchsOrgBean) {
        this.prchsOrgBean = prchsOrgBean;
    }

    public User getUser1() {
        return this.usr1;
    }

    public void setUser1(User usr1) {
        this.usr1 = usr1;
    }

    public User getUser2() {
        return this.usr2;
    }

    public void setUser2(User usr2) {
        this.usr2 = usr2;
    }

}
