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
public class PayHistory {

    private String fisCalYear;
    
    private String invoice;
    
    private String supplierInvoiceNo;
    
    private String invoiceDate;
    
    private String purDocNo;
    
    private String poDate;
    
    private String invoiceAmount;
    
    private String currency;
    
    private String tds;
    
    private String houseBank;
    
    private String chequeNo;
    
    private String amount;
    
    private String accountNo;
    
    private String name;

	public String getFisCalYear() {
		return fisCalYear;
	}

	public void setFisCalYear(String fisCalYear) {
		this.fisCalYear = fisCalYear;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getSupplierInvoiceNo() {
		return supplierInvoiceNo;
	}

	public void setSupplierInvoiceNo(String supplierInvoiceNo) {
		this.supplierInvoiceNo = supplierInvoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPurDocNo() {
		return purDocNo;
	}

	public void setPurDocNo(String purDocNo) {
		this.purDocNo = purDocNo;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getHouseBank() {
		return houseBank;
	}

	public void setHouseBank(String houseBank) {
		this.houseBank = houseBank;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PayHistory [fisCalYear=" + fisCalYear + ", invoice=" + invoice
				+ ", supplierInvoiceNo=" + supplierInvoiceNo + ", invoiceDate="
				+ invoiceDate + ", purDocNo=" + purDocNo + ", poDate=" + poDate
				+ ", invoiceAmount=" + invoiceAmount + ", currency="
				+ currency + ", tds=" + tds + ", houseBank=" + houseBank
				+ ", chequeNo=" + chequeNo + ", amount=" + amount
				+ ", accountNo=" + accountNo + ", name=" + name + "]";
	}

	
}
