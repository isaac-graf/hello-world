/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbna.vendorportal.pojo.as;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author PhaneendraGoutham B
 */
public class QuoteHeader implements Serializable {

    private String incoTermsText;

    private String rfqDate;

    private String deadLineDate;

    private String status;

    private String payTermsText;

    private String plant;

    private String payTermsKey;

    private String rfqDesc;

    private String rfqNumber;

    private String incoTermsKey;

    private String endDate;

    private String vendorCode;

    private String startDate;

    private String vendorName;

    private String collectiveNo;

    private String plantName;

    private String purchaseGroupCode;

    private List<QuoteLineItem> quoteLineItems;

    public String getIncoTermsText() {
        return incoTermsText;
    }

    public void setIncoTermsText(String incoTermsText) {
        this.incoTermsText = incoTermsText;
    }

    public String getRfqDate() {
        return rfqDate;
    }

    public void setRfqDate(String rfqDate) {
        this.rfqDate = rfqDate;
    }

    public String getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(String deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayTermsText() {
        return payTermsText;
    }

    public void setPayTermsText(String payTermsText) {
        this.payTermsText = payTermsText;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPayTermsKey() {
        return payTermsKey;
    }

    public void setPayTermsKey(String payTermsKey) {
        this.payTermsKey = payTermsKey;
    }

    public String getRfqDesc() {
        return rfqDesc;
    }

    public void setRfqDesc(String rfqDesc) {
        this.rfqDesc = rfqDesc;
    }

    public String getRfqNumber() {
        return rfqNumber;
    }

    public void setRfqNumber(String rfqNumber) {
        this.rfqNumber = rfqNumber;
    }

    public String getIncoTermsKey() {
        return incoTermsKey;
    }

    public void setIncoTermsKey(String incoTermsKey) {
        this.incoTermsKey = incoTermsKey;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCollectiveNo() {
        return collectiveNo;
    }

    public void setCollectiveNo(String collectiveNo) {
        this.collectiveNo = collectiveNo;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPurchaseGroupCode() {
        return purchaseGroupCode;
    }

    public void setPurchaseGroupCode(String purchaseGroupCode) {
        this.purchaseGroupCode = purchaseGroupCode;
    }

    public List<QuoteLineItem> getQuoteLineItems() {
        return quoteLineItems;
    }

    public void setQuoteLineItems(List<QuoteLineItem> quoteLineItems) {
        this.quoteLineItems = quoteLineItems;
    }

    @Override
    public String toString() {
        return "QuoteHeader{" + "incoTermsText=" + incoTermsText + ", rfqDate=" + rfqDate + ", deadLineDate=" + deadLineDate + ", status=" + status + ", payTermsText=" + payTermsText + ", plant=" + plant + ", payTermsKey=" + payTermsKey + ", rfqDesc=" + rfqDesc + ", rfqNumber=" + rfqNumber + ", incoTermsKey=" + incoTermsKey + ", endDate=" + endDate + ", vendorCode=" + vendorCode + ", startDate=" + startDate + '}';
    }
}
