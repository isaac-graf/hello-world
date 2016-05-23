package com.sbna.vendorportal.pojo;

import com.sbna.vendorportal.config.ProjectConfig;
import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PCRDataHelper implements Serializable {

    private String pcrNumber;
    private String vendorCode;
    private String vendorName;
    private String buyerName;
    private String matCode;
    private String materialDescEng;
    private String plantCode;
    private String plantDescEng;
    private String extngMaterialPrice;
    private String extngPackingPrice;
    private String extngFreightPrice;
    private String extngTotal;
    private String rqstdMaterialPrice;
    private String rqstdPackingPrice;
    private String rqstdFreightPrice;
    private String rqstdTotal;
    private String supplierRemarks;
    private String buyerDecision;
    private String attachment;
    private String pcrDate;
    private String effDate;
    private String status;
    private String currency;
    private String extngFreightPer;
    private String extngPackingPer;
    private String rqstdFreightPer;
    private String rqstdPackingPer;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPcrNumber() {
        return pcrNumber;
    }

    public void setPcrNumber(String pcrNumber) {
        this.pcrNumber = pcrNumber;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getExtngMaterialPrice() {
        if (extngMaterialPrice != null && !("").equals(extngMaterialPrice.trim())) {
            extngMaterialPrice = String.format("%.4f", Double.parseDouble(extngMaterialPrice));
        } else {
            extngMaterialPrice = "";
        }
        return extngMaterialPrice;
    }

    public void setExtngMaterialPrice(String extngMaterialPrice) {
        this.extngMaterialPrice = extngMaterialPrice;
    }

    public String getExtngPackingPrice() {
        if (extngPackingPrice != null && !("").equals(extngPackingPrice.trim())) {
            extngPackingPrice = String.format("%.2f", Double.parseDouble(extngPackingPrice));
        } else {
            extngPackingPrice = "";
        }
        return extngPackingPrice;
    }

    public void setExtngPackingPrice(String extngPackingPrice) {
        this.extngPackingPrice = extngPackingPrice;
    }

    public String getExtngFreightPrice() {
        if (extngFreightPrice != null && !("").equals(extngFreightPrice.trim())) {
            extngFreightPrice = String.format("%.2f", Double.parseDouble(extngFreightPrice));
        } else {
            extngFreightPrice = "";
        }
        return extngFreightPrice;
    }

    public void setExtngFreightPrice(String extngFreightPrice) {
        this.extngFreightPrice = extngFreightPrice;
    }

    public String getRqstdMaterialPrice() {
        if (rqstdMaterialPrice != null) {
            rqstdMaterialPrice = String.format("%.4f", Double.parseDouble(rqstdMaterialPrice));
        }
        return rqstdMaterialPrice;
    }

    public void setRqstdMaterialPrice(String rqstdMaterialPrice) {
        this.rqstdMaterialPrice = rqstdMaterialPrice;
    }

    public String getRqstdPackingPrice() {
        if (rqstdPackingPrice != null) {
            rqstdPackingPrice = String.format("%.2f", Double.parseDouble(rqstdPackingPrice));
        }
        return rqstdPackingPrice;
    }

    public void setRqstdPackingPrice(String rqstdPackingPrice) {
        this.rqstdPackingPrice = rqstdPackingPrice;
    }

    public String getRqstdFreightPrice() {
        if (rqstdFreightPrice != null) {
            rqstdFreightPrice = String.format("%.2f", Double.parseDouble(rqstdFreightPrice));
        }
        return rqstdFreightPrice;
    }

    public void setRqstdFreightPrice(String rqstdFreightPrice) {
        this.rqstdFreightPrice = rqstdFreightPrice;
    }

    public String getSupplierRemarks() {
        return supplierRemarks;
    }

    public void setSupplierRemarks(String supplierRemarks) {
        this.supplierRemarks = supplierRemarks;
    }

    public String getBuyerDecision() {
        return buyerDecision;
    }

    public void setBuyerDecision(String buyerDecision) {
        this.buyerDecision = buyerDecision;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getPcrDate() {
        return pcrDate;
    }

    public void setPcrDate(String pcrDate) {
        this.pcrDate = pcrDate;
    }

    public String getMaterialDescEng() {
        return materialDescEng;
    }

    public void setMaterialDescEng(String materialDescEng) {
        this.materialDescEng = materialDescEng;
    }

    public String getPlantDescEng() {
        return plantDescEng;
    }

    public void setPlantDescEng(String plantDescEng) {
        this.plantDescEng = plantDescEng;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /*public PCRDataHelper(Object pcrNumber, Object vendorName, Object buyerName,
     Object matCode, Object reqestedPrice1, Object approvedPrice1,
     Object supplierRemarks, Object buyerDecision,Object reqestedPrice2,Object reqestedPrice3,Object approvedPrice2,Object approvedPrice3) {
		
     this.pcrNumber = ((BigInteger)pcrNumber).longValue();
     this.vendorName = (String)vendorName;
     this.buyerName = (String)buyerName;
     this.matCode = (String)matCode;
     this.reqestedPrice1 =reqestedPrice1!=null? ((BigDecimal)reqestedPrice1).doubleValue():0.0;
     this.reqestedPrice2 =reqestedPrice2!=null? ((BigDecimal)reqestedPrice2).doubleValue():0.0;
     this.reqestedPrice3 =reqestedPrice3!=null? ((BigDecimal)reqestedPrice3).doubleValue():0.0;
     this.approvedPrice1 =approvedPrice1!=null?((BigDecimal)approvedPrice1).doubleValue():0.0;
     this.approvedPrice2 =approvedPrice2!=null?((BigDecimal)approvedPrice2).doubleValue():0.0;
     this.approvedPrice3 =approvedPrice2!=null?((BigDecimal)approvedPrice3).doubleValue():0.0;
		
     this.supplierRemarks = (String)supplierRemarks;
     this.buyerDecision = (String)buyerDecision;
     }*/
    public String getExtngFreightPer() {
        if (extngFreightPer != null && ProjectConfig.FREIGHT_TYPE.get(extngFreightPer) != null) {
            extngFreightPer = ProjectConfig.FREIGHT_TYPE.get(extngFreightPer);
        } else {
            if (extngFreightPer == null) {
                extngFreightPer = "";
            }
        }
        return extngFreightPer;
    }

    public void setExtngFreightPer(String extngFreightPer) {
        this.extngFreightPer = extngFreightPer;
    }

    public String getExtngPackingPer() {
        if (extngPackingPer != null && ProjectConfig.PACKING_TYPE.get(extngPackingPer) != null) {
            extngPackingPer = ProjectConfig.PACKING_TYPE.get(extngPackingPer);
        } else {
            if (extngPackingPer == null) {
                extngPackingPer = "";
            }
        }
        return extngPackingPer;
    }

    public void setExtngPackingPer(String extngPackingPer) {
        this.extngPackingPer = extngPackingPer;
    }

    public String getRqstdFreightPer() {
        if (rqstdFreightPer != null && ProjectConfig.FREIGHT_TYPE.get(rqstdFreightPer) != null) {
            rqstdFreightPer = ProjectConfig.FREIGHT_TYPE.get(rqstdFreightPer);
        }
        return rqstdFreightPer;
    }

    public void setRqstdFreightPer(String rqstdFreightPer) {
        this.rqstdFreightPer = rqstdFreightPer;
    }

    public String getRqstdPackingPer() {
        if (rqstdPackingPer != null && ProjectConfig.PACKING_TYPE.get(rqstdPackingPer) != null) {
            rqstdPackingPer = ProjectConfig.PACKING_TYPE.get(rqstdPackingPer);
        }
        return rqstdPackingPer;
    }

    public void setRqstdPackingPer(String rqstdPackingPer) {
        this.rqstdPackingPer = rqstdPackingPer;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getExtngTotal() {
        extngTotal = "";
        Double extngTotalTemp = 0D;
        try {
            if (extngMaterialPrice != null && !("").equals(extngMaterialPrice.trim())) {
                extngTotalTemp += Double.parseDouble(extngMaterialPrice);
            }
            if (extngPackingPrice != null && !("").equals(extngPackingPrice.trim())) {
                extngTotalTemp += Double.parseDouble(extngPackingPrice);
            }
            if (extngFreightPrice != null && !("").equals(extngFreightPrice.trim())) {
                extngTotalTemp += Double.parseDouble(extngFreightPrice);
            }
            extngTotal = String.format("%.4f", extngTotalTemp);
        } catch (Exception e) {
        }
        return extngTotal;
    }

    public void setExtngTotal(String extngTotal) {
        this.extngTotal = extngTotal;
    }

    public String getRqstdTotal() {
        rqstdTotal = "";
        Double rqstdTotalTemp = 0D;
        try {
            if (rqstdMaterialPrice != null) {
                rqstdTotalTemp += Double.parseDouble(rqstdMaterialPrice);
            }
            if (extngPackingPrice != null) {
                rqstdTotalTemp += Double.parseDouble(rqstdPackingPrice);
            }
            if (extngFreightPrice != null) {
                rqstdTotalTemp += Double.parseDouble(rqstdFreightPrice);
            }
            rqstdTotal = String.format("%.4f", rqstdTotalTemp);
        } catch (Exception e) {
        }
        return rqstdTotal;
    }

    public void setRqstdTotal(String rqstdTotal) {
        this.rqstdTotal = rqstdTotal;
    }

}
