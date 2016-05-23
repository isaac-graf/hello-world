/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbna.vendorportal.dto;


/**
 *
 * @author santosh kumar
 */

public class DelPerf {

    private String poDocNo;
    
    private String poItemNo;
    
    private String plantCode;
    
    private String materialCode;
    
    private String materialDesc;
    
    private String poDate;
    
    private String passedQty;
    
    private String poUnit;
    
    private String goodReceiptNo;
    
    private String goodReceiptQty;
    
    private String poHistoryCat;
    
    private String invoiceQty;
    
    private String balanceQty;
    
    private String rejectionQty;
    
    private String reason;
    
    private String remarks;
        
    private String status;

	public String getPoDocNo() {
		return poDocNo;
	}

	public void setPoDocNo(String poDocNo) {
		this.poDocNo = poDocNo;
	}

	public String getPoItemNo() {
		return poItemNo;
	}

	public void setPoItemNo(String poItemNo) {
		this.poItemNo = poItemNo;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getPassedQty() {
		return passedQty;
	}

	public void setPassedQty(String passedQty) {
		this.passedQty = passedQty;
	}

	public String getPoUnit() {
		return poUnit;
	}

	public void setPoUnit(String poUnit) {
		this.poUnit = poUnit;
	}

	public String getGoodReceiptNo() {
		return goodReceiptNo;
	}

	public void setGoodReceiptNo(String goodReceiptNo) {
		this.goodReceiptNo = goodReceiptNo;
	}

	public String getGoodReceiptQty() {
		return goodReceiptQty;
	}

	public void setGoodReceiptQty(String goodReceiptQty) {
		this.goodReceiptQty = goodReceiptQty;
	}

	public String getPoHistoryCat() {
		return poHistoryCat;
	}

	public void setPoHistoryCat(String poHistoryCat) {
		this.poHistoryCat = poHistoryCat;
	}

	public String getInvoiceQty() {
		return invoiceQty;
	}

	public void setInvoiceQty(String invoiceQty) {
		this.invoiceQty = invoiceQty;
	}

	public String getBalanceQty() {
		return balanceQty;
	}

	public void setBalanceQty(String balanceQty) {
		this.balanceQty = balanceQty;
	}

	public String getRejectionQty() {
		return rejectionQty;
	}

	public void setRejectionQty(String rejectionQty) {
		this.rejectionQty = rejectionQty;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DelPerf [poDocNo=" + poDocNo + ", poItemNo=" + poItemNo
				+ ", plantCode=" + plantCode + ", materialCode=" + materialCode
				+ ", materialDesc=" + materialDesc + ", poDate=" + poDate
				+ ", passedQty=" + passedQty + ", poUnit=" + poUnit
				+ ", goodReceiptNo=" + goodReceiptNo + ", goodReceiptQty="
				+ goodReceiptQty + ", poHistoryCat=" + poHistoryCat
				+ ", invoiceQty=" + invoiceQty + ", balanceQty=" + balanceQty
				+ ", rejectionQty=" + rejectionQty + ", reason=" + reason
				+ ", remarks=" + remarks + ", status="
				+ status + "]";
	}

	
	
}
