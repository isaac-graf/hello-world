package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * The persistent class for the prchs_grp database table.
 *
 */
@Entity
@Table(name = "prchs_grp")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_prchsgrp", allocationSize = 1)
//@NamedQuery(name = "PrchsGrp.findAll", query = "SELECT p FROM PrchsGrp p")
public class PurchaseGroup extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "prchs_grp_code")
    private String prchsGrpCode;

    @Column(name = "prchs_grp_desc_eng")
    private String prchsGrpDescEng;

    // bi-directional many-to-one association to Pcr
    @OneToMany(mappedBy = "purchaseGroup")
    @JsonManagedReference
    private List<Pcr> pcrs;

    // bi-directional many-to-one association to Status
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prchs_grp_status_id")
    @JsonManagedReference
    private Status status;

    // bi-directional many-to-one association to PrchsGrpDesc
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "prchsGrp")
    @JsonBackReference
    private List<PurchaseGroupDesc> prchsGrpDescs;

    // bi-directional many-to-one association to Usr
    @OneToMany(mappedBy = "prchsGrp")
    private List<User> usrs;

    @OneToMany(mappedBy = "purchaseGroupId", cascade = CascadeType.ALL)
    private List<ReqForQuote> reqForQuotes;

    public PurchaseGroup() {
    }

    public String getPrchsGrpDescEng() {
        return this.prchsGrpDescEng;
    }

    public void setPrchsGrpDescEng(String prchsGrpDescEng) {
        this.prchsGrpDescEng = prchsGrpDescEng;
    }

    public List<Pcr> getPcrs() {
        return this.pcrs;
    }

    public void setPcrs(List<Pcr> pcrs) {
        this.pcrs = pcrs;
    }

    public Pcr addPcr(Pcr pcr) {
        getPcrs().add(pcr);
        pcr.setPurchaseGroup(this);

        return pcr;
    }

    public Pcr removePcr(Pcr pcr) {
        getPcrs().remove(pcr);
        pcr.setPurchaseGroup(null);

        return pcr;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<PurchaseGroupDesc> getPrchsGrpDescs() {
        return this.prchsGrpDescs;
    }

    public void setPrchsGrpDescs(List<PurchaseGroupDesc> prchsGrpDescs) {
        this.prchsGrpDescs = prchsGrpDescs;
    }

    public PurchaseGroupDesc addPrchsGrpDesc(PurchaseGroupDesc prchsGrpDesc) {
        getPrchsGrpDescs().add(prchsGrpDesc);
        prchsGrpDesc.setPrchsGrp(this);

        return prchsGrpDesc;
    }

    public PurchaseGroupDesc removePrchsGrpDesc(PurchaseGroupDesc prchsGrpDesc) {
        getPrchsGrpDescs().remove(prchsGrpDesc);
        prchsGrpDesc.setPrchsGrp(null);

        return prchsGrpDesc;
    }

    public List<User> getUsrs() {
        return this.usrs;
    }

    public void setUsrs(List<User> usrs) {
        this.usrs = usrs;
    }

    public User addUsr(User usr) {
        getUsrs().add(usr);
        usr.setPrchsGrp(this);

        return usr;
    }

    public User removeUsr(User usr) {
        getUsrs().remove(usr);
        usr.setPrchsGrp(null);

        return usr;
    }

    public String getPrchsGrpCode() {
        return prchsGrpCode;
    }

    public void setPrchsGrpCode(String prchsGrpCode) {
        this.prchsGrpCode = prchsGrpCode;
    }

    public List<ReqForQuote> getReqForQuotes() {
        return reqForQuotes;
    }

    public void setReqForQuotes(List<ReqForQuote> reqForQuotes) {
        this.reqForQuotes = reqForQuotes;
    }

}
