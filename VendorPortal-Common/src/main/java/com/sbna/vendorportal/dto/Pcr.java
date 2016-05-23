package com.sbna.vendorportal.dto;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Data access object for the PCR (Price Change Request) table. contains the
 * mappings of columns with Pojo with relationships.
 *
 */
@Entity
@Table(name = "pcr")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_pcr", allocationSize = 1)
public class Pcr extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "effective_date")
    private Date effectiveDate;

    @Column(name = "prchs_info_record")
    private String prchsInfoRecord;

    @Column(name = "prchs_org_code")
    private String prchsOrgCode;

    @Column(name = "currency")
    private String currency;

    @Column(name = "extng_packing_per")
    private String extngPackingPer;

    @Column(name = "extng_freight_per")
    private String extngFreightPer;

    @Column(name = "extng_freight_price")
    private BigDecimal extngFreightPrice;

    @Column(name = "extng_material_price")
    private BigDecimal extngMaterialPrice;

    @Column(name = "extng_packing_price")
    private BigDecimal extngPackingPrice;

    @Column(name = "rqstd_freight_price")
    private BigDecimal rqstdFreightPrice;

    @Column(name = "rqstd_material_price")
    private BigDecimal rqstdMaterialPrice;

    @Column(name = "rqstd_packing_price")
    private BigDecimal rqstdPackingPrice;

    @Column(name = "rqstd_packing_per")
    private String rqstdPackingPer;

    @Column(name = "rqstd_freight_per")
    private String rqstdFreightPer;

    @Column(name = "vendor_remarks")
    private String vendorRemarks;

    // bi-directional many-to-one association to Material
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_code")
    @JsonIgnore
    private Material material;

    // bi-directional many-to-one association to Plant
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "plant_code")
    @JsonIgnore
    private Plant plant;

    // bi-directional many-to-one association to PrchsGrp
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "prchs_grp_id")
    @JsonIgnore
    private PurchaseGroup purchaseGroup;

    // bi-directional many-to-one association to Status
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pcr_status_id")
    @JsonIgnore
    private Status status;

    // bi-directional many-to-one association to Usr
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    @JsonIgnore
    private User user;

    @Transient
    private String statusTemp;

    @Transient
    private String materialCode;

    @Transient
    private String materialDesc;

    @Transient
    private String plantCode;

    @Transient
    private String plantDesc;

    @Transient
    private String vendorCode;

    @Transient
    private String vendorFullname;

    public Pcr() {
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public BigDecimal getExtngFreightPrice() {
        return extngFreightPrice;
    }

    public void setExtngFreightPrice(final BigDecimal extngFreightPrice) {
        this.extngFreightPrice = extngFreightPrice;
    }

    public BigDecimal getExtngMaterialPrice() {
        return extngMaterialPrice;
    }

    public void setExtngMaterialPrice(final BigDecimal extngMaterialPrice) {
        this.extngMaterialPrice = extngMaterialPrice;
    }

    public BigDecimal getExtngPackingPrice() {
        return extngPackingPrice;
    }

    public void setExtngPackingPrice(final BigDecimal extngPackingPrice) {
        this.extngPackingPrice = extngPackingPrice;
    }

    public BigDecimal getRqstdFreightPrice() {
        return rqstdFreightPrice;
    }

    public void setRqstdFreightPrice(final BigDecimal rqstdFreightPrice) {
        this.rqstdFreightPrice = rqstdFreightPrice;
    }

    public BigDecimal getRqstdMaterialPrice() {
        return rqstdMaterialPrice;
    }

    public void setRqstdMaterialPrice(final BigDecimal rqstdMaterialPrice) {
        this.rqstdMaterialPrice = rqstdMaterialPrice;
    }

    public BigDecimal getRqstdPackingPrice() {
        return rqstdPackingPrice;
    }

    public void setRqstdPackingPrice(final BigDecimal rqstdPackingPrice) {
        this.rqstdPackingPrice = rqstdPackingPrice;
    }

    public String getVendorRemarks() {
        return vendorRemarks;
    }

    public void setVendorRemarks(final String vendorRemarks) {
        this.vendorRemarks = vendorRemarks;
    }

    public String getStatusTemp() {
        if (status != null) {
            statusTemp = status.getStatusDescEng();
        }
        return statusTemp;
    }

    public void setStatusTemp(final String statusTemp) {
        this.statusTemp = statusTemp;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(final Material material) {
        this.material = material;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(final Plant plant) {
        this.plant = plant;
    }

    public PurchaseGroup getPurchaseGroup() {
        return purchaseGroup;
    }

    public void setPurchaseGroup(final PurchaseGroup purchaseGroup) {
        this.purchaseGroup = purchaseGroup;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getMaterialCode() {
        if (material != null) {
            materialCode = material.getMaterialCode();
        }
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialDesc() {
        if (material != null) {
            materialDesc = material.getMaterialDescEng();
        }
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getPlantCode() {
        if (plant != null) {
            plantCode = plant.getPlantCode();
        }
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantDesc() {
        if (plant != null) {
            plantDesc = plant.getPlantDescEng();
        }
        return plantDesc;
    }

    public void setPlantDesc(String plantDesc) {
        this.plantDesc = plantDesc;
    }

    public String getVendorCode() {
        if (user != null) {
            vendorCode = user.getUsrName();
        }
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorFullname() {
        if (user != null && user.getProfile() != null) {
            vendorFullname = user.getProfile().getFullName();
        }
        return vendorFullname;
    }

    public void setVendorFullname(String vendorFullname) {
        this.vendorFullname = vendorFullname;
    }

    public String getPrchsInfoRecord() {
        return prchsInfoRecord;
    }

    public void setPrchsInfoRecord(String prchsInfoRecord) {
        this.prchsInfoRecord = prchsInfoRecord;
    }

    public String getPrchsOrgCode() {
        return prchsOrgCode;
    }

    public void setPrchsOrgCode(String prchsOrgCode) {
        this.prchsOrgCode = prchsOrgCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExtngPackingPer() {
        return extngPackingPer;
    }

    public void setExtngPackingPer(String extngPackingPer) {
        this.extngPackingPer = extngPackingPer;
    }

    public String getExtngFreightPer() {
        return extngFreightPer;
    }

    public void setExtngFreightPer(String extngFreightPer) {
        this.extngFreightPer = extngFreightPer;
    }

    public String getRqstdPackingPer() {
        return rqstdPackingPer;
    }

    public void setRqstdPackingPer(String rqstdPackingPer) {
        this.rqstdPackingPer = rqstdPackingPer;
    }

    public String getRqstdFreightPer() {
        return rqstdFreightPer;
    }

    public void setRqstdFreightPer(String rqstdFreightPer) {
        this.rqstdFreightPer = rqstdFreightPer;
    }

    @Override
    public String toString() {
        return "PCR {" + "ID=" + getId() + ", EffectiveDate=" + effectiveDate + ", PurchaseInfoRecord="
                + prchsInfoRecord + ", ExtngFreightPrice=" + extngFreightPrice
                + ", ExtngMaterialPrice=" + extngMaterialPrice + ", ExtngPackingPrice="
                + extngPackingPrice + ", RqstdFreightPrice=" + rqstdFreightPrice
                + ", RqstdMaterialPrice=" + rqstdMaterialPrice + ", RqstdPackingPrice="
                + rqstdPackingPrice + ", Remarks=" + vendorRemarks + '}';
    }

}
