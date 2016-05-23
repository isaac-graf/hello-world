/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbna.vendorportal.pojo.as;

import java.math.BigDecimal;
import java.util.Date;

public class PirDetails {

    private String prchInfoRecNo;

    private String materialNo;

    private String vendorAcctNo;

    private String purchGroup;

    private String prchOrg;

    private String plant;

    private BigDecimal materialPrice;

    private BigDecimal packagingPrice;

    private String packagingType;

    private BigDecimal freightPrice;

    private String freightType;

    private Date effecDate;

    public String getPrchInfoRecNo() {
        return prchInfoRecNo;
    }

    public void setPrchInfoRecNo(String prchInfoRecNo) {
        this.prchInfoRecNo = prchInfoRecNo;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getVendorAcctNo() {
        return vendorAcctNo;
    }

    public void setVendorAcctNo(String vendorAcctNo) {
        this.vendorAcctNo = vendorAcctNo;
    }

    public String getPurchGroup() {
        return purchGroup;
    }

    public void setPurchGroup(String purchGroup) {
        this.purchGroup = purchGroup;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public BigDecimal getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(BigDecimal materialPrice) {
        this.materialPrice = materialPrice;
    }

    public BigDecimal getPackagingPrice() {
        return packagingPrice;
    }

    public void setPackagingPrice(BigDecimal packagingPrice) {
        this.packagingPrice = packagingPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getPrchOrg() {
        return prchOrg;
    }

    public void setPrchOrg(String prchOrg) {
        this.prchOrg = prchOrg;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public String getFreightType() {
        return freightType;
    }

    public void setFreightType(String freightType) {
        this.freightType = freightType;
    }

    public Date getEffecDate() {
        return effecDate;
    }

    public void setEffecDate(Date effecDate) {
        this.effecDate = effecDate;
    }

}
