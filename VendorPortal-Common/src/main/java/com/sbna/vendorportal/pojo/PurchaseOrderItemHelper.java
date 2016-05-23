package com.sbna.vendorportal.pojo;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class PurchaseOrderItemHelper implements Serializable {

    Long poItemId;
    String poNo;
    String itemNo;
    String materialCode;
    String matExtraDesc;
    String materialDesc;
    String dispMatCode;
    String dispMatDesc;
    Double orderedQty;
    Double balanceQty;
    String uom;

    public PurchaseOrderItemHelper(Long poItemId, String poNo, String itemNo,
            String materialCode, String matExtraDesc, String materialDesc, Double orderedQty,
            Double balanceQty, String uom) {
        super();
        this.poItemId = poItemId;
        this.poNo = poNo;
        this.itemNo = itemNo;
        this.materialCode = materialCode;
        this.matExtraDesc = matExtraDesc;
        this.materialDesc = materialDesc;
        this.orderedQty = orderedQty;
        this.balanceQty = balanceQty;
        this.uom = uom;
        if (materialCode != null) {
            this.dispMatCode = materialCode;
            this.dispMatDesc = materialDesc;
        } else {
            this.dispMatCode = matExtraDesc;
            this.dispMatDesc = matExtraDesc;
        }
    }

    public PurchaseOrderItemHelper() {
    }

    public Long getPoItemId() {
        return poItemId;
    }

    public void setPoItemId(Long poItemId) {
        this.poItemId = poItemId;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMatExtraDesc() {
        return matExtraDesc;
    }

    public void setMatExtraDesc(String matExtraDesc) {
        this.matExtraDesc = matExtraDesc;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getDispMatCode() {
        return dispMatCode;
    }

    public void setDispMatCode(String dispMatCode) {
        this.dispMatCode = dispMatCode;
    }

    public String getDispMatDesc() {
        return dispMatDesc;
    }

    public void setDispMatDesc(String dispMatDesc) {
        this.dispMatDesc = dispMatDesc;
    }

    public Double getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(Double orderedQty) {
        this.orderedQty = orderedQty;
    }

    public Double getBalanceQty() {
        return balanceQty;
    }

    public void setBalanceQty(Double balanceQty) {
        this.balanceQty = balanceQty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

}
