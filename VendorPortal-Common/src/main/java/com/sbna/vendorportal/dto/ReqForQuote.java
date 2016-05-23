package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sbna.vendorportal.config.TableConfig;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = TableConfig.TABLE_RFQ)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_rfq", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "ReqForQuote.getByRfqNoVendorCode",
            query = "SELECT rfq FROM ReqForQuote rfq WHERE rfq.rfqNo = :rfqNo AND rfq.vendorId.usrName = :vendorCode"),
    @NamedQuery(name = "ReqForQuote.getAllRFQIdsByVendor",
            query = "SELECT rfq.rfqNo FROM ReqForQuote rfq WHERE rfq.vendorId.id = :vendorId ORDER BY rfq.rfqNo"),
    @NamedQuery(name = "ReqForQuote.getAllRFQIds",
            query = "SELECT DISTINCT rfq.rfqNo FROM ReqForQuote rfq ORDER BY rfq.rfqNo"),
    @NamedQuery(name = "ReqForQuote.getAllCollecNos",
            query = "SELECT DISTINCT rfq.collectiveNo FROM ReqForQuote rfq ORDER BY rfq.collectiveNo")
})
public class ReqForQuote extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -370506493383863027L;

    @Column(name = "collec_no", nullable = false)
    private String collectiveNo;

    @Column(name = "rfq_no", nullable = false)
    private String rfqNo;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "prchs_grp_id")
    @JsonIgnore
    private PurchaseGroup purchaseGroupId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    @JsonIgnore
    private User vendorId;

    @OneToMany(mappedBy = "reqForQuoteId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<QuoteLineItem> quoteLineItems;

    @Transient
    private String rfqDate;

    @Transient
    private String purchaseGroupCode;

    @Transient
    private String oldAuditData;

    @Transient
    private String newAuditData;

    public String getCollectiveNo() {
        return collectiveNo;
    }

    public void setCollectiveNo(String collectiveNo) {
        this.collectiveNo = collectiveNo;
    }

    public String getRfqNo() {
        return rfqNo;
    }

    public void setRfqNo(String rfqNo) {
        this.rfqNo = rfqNo;
    }

    public User getVendorId() {
        return vendorId;
    }

    public void setVendorId(User vendorId) {
        this.vendorId = vendorId;
    }

    public List<QuoteLineItem> getQuoteLineItems() {
        return quoteLineItems;
    }

    public void setQuoteLineItems(List<QuoteLineItem> quoteLineItems) {
        this.quoteLineItems = quoteLineItems;
    }

    public PurchaseGroup getPurchaseGroupId() {
        return purchaseGroupId;
    }

    public void setPurchaseGroupId(PurchaseGroup purchaseGroupId) {
        this.purchaseGroupId = purchaseGroupId;
    }

    public String getRfqDate() {
        return rfqDate;
    }

    public void setRfqDate(String rfqDate) {
        this.rfqDate = rfqDate;
    }

    public String getPurchaseGroupCode() {
        return purchaseGroupCode;
    }

    public void setPurchaseGroupCode(String purchaseGroupCode) {
        this.purchaseGroupCode = purchaseGroupCode;
    }

    public String getOldAuditData() {
        return oldAuditData;
    }

    public void setOldAuditData(String oldAuditData) {
        this.oldAuditData = oldAuditData;
    }

    public String getNewAuditData() {
        return newAuditData;
    }

    public void setNewAuditData(String newAuditData) {
        this.newAuditData = newAuditData;
    }

    @Override
    public String toString() {
        return "(" + "ID=" + getId() + ", RFQ No=" + rfqNo + "Collec No=" + collectiveNo
                + ", Purchase Group=" + purchaseGroupCode + ", No of line items=" + quoteLineItems.size() + "Created=" + getCreated() + ")";
    }

}
