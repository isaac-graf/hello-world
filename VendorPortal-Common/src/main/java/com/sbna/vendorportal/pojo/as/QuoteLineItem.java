/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbna.vendorportal.pojo.as;

import java.io.Serializable;

/**
 *
 * @author PhaneendraGoutham B
 */
public class QuoteLineItem implements Serializable {

    private String rfqNo;

    private String itemNo;

    private String total;

    private String plantCode;

    private String freightType;

    private String taxCode;

    private String unitPrice;

    private String material;

    private String freight;

    private String uom;

    private String packing;

    private String currency;

    private String packingType;

    private String description;

    private String quantity;

    private String accepted;

    private String vendorRemarks;

    private String packingPer;

    private String freightPer;

    private String status;

    public String getRfqNo() {
        return rfqNo;
    }

    public void setRfqNo(String rfqNo) {
        this.rfqNo = rfqNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getFreightType() {
        return freightType;
    }

    public void setFreightType(String freightType) {
        this.freightType = freightType;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getVendorRemarks() {
        return vendorRemarks;
    }

    public void setVendorRemarks(String vendorRemarks) {
        this.vendorRemarks = vendorRemarks;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QuoteLineItem{" + "rfqNo=" + rfqNo + ", itemNo=" + itemNo + ", total=" + total
                + ", plantCode=" + plantCode + ", freightType=" + freightType + ", taxCode=" + taxCode
                + ", unitPrice=" + unitPrice + ", material=" + material + ", freight=" + freight
                + ", uom=" + uom + ", packing=" + packing + ", currency=" + currency + ", packingType=" + packingType
                + ", description=" + description + ", quantity=" + quantity + ", accepted=" + accepted
                + ", vendorRemarks=" + vendorRemarks + ", packingPer=" + packingPer + ", freightPer=" + freightPer + '}';
    }

}
