package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sbna.vendorportal.config.TableConfig;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = TableConfig.TABLE_RFQDET)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_rfqdet", allocationSize = 1)
public class QuoteLineItem extends BaseModel {

    private static final long serialVersionUID = -7128482501822626382L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "rfq_id")
    @JsonIgnore
    private ReqForQuote reqForQuoteId;

    @Column(name = "item_no", nullable = false)
    private String lineItemNo;

    @Column(name = "packing_per")
    private String packingPer;

    @Column(name = "freight_per")
    private String freightPer;

    @Column(name = "vendor_remarks")
    private String vendorRemarks;

    @Column(name = "accepted")
    private boolean accepted;

    public ReqForQuote getReqForQuoteId() {
        return reqForQuoteId;
    }

    public void setReqForQuoteId(ReqForQuote reqForQuoteId) {
        this.reqForQuoteId = reqForQuoteId;
    }

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getVendorRemarks() {
        return vendorRemarks;
    }

    public void setVendorRemarks(String vendorRemarks) {
        this.vendorRemarks = vendorRemarks;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getPackingPer() {
        return packingPer;
    }

    public void setPackingPer(String packingPer) {
        this.packingPer = packingPer;
    }

    public String getFreightPer() {
        return freightPer;
    }

    public void setFreightPer(String freightPer) {
        this.freightPer = freightPer;
    }

    @Override
    public String toString() {
        return "(" + "ID=" + getId() + "Line Item=" + lineItemNo
                + ", Packing Per=" + packingPer + ", Freight Per=" + freightPer
                + ", Vendor Remarks=" + vendorRemarks + ", No Quote=" + (accepted ? "Quoted" : "Not Quoted") + ")";
    }

}
