package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * The persistent class for the material database table.
 *
 */
@Entity
@Table(name = "material")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_material", allocationSize = 1)
//@NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m")
public class Material extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "material_desc_eng")
    private String materialDescEng;

    // bi-directional many-to-one association to Status
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "material_status_id")
    private Status status;

    // bi-directional many-to-one association to MaterialDesc
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "material")
    private List<MaterialDesc> materialDescs;

    // bi-directional many-to-one association to PurchaseOrderItem
    @OneToMany(mappedBy = "material")
    private List<PurchaseOrderItem> prchsOrderItems;

    public Material() {
    }

    public String getMaterialCode() {
        return this.materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialDescEng() {
        return this.materialDescEng;
    }

    public void setMaterialDescEng(String materialDescEng) {
        this.materialDescEng = materialDescEng;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<MaterialDesc> getMaterialDescs() {
        return this.materialDescs;
    }

    public void setMaterialDescs(List<MaterialDesc> materialDescs) {
        this.materialDescs = materialDescs;
    }

    public MaterialDesc addMaterialDesc(MaterialDesc materialDesc) {
        getMaterialDescs().add(materialDesc);
        materialDesc.setMaterial(this);

        return materialDesc;
    }

    public MaterialDesc removeMaterialDesc(MaterialDesc materialDesc) {
        getMaterialDescs().remove(materialDesc);
        materialDesc.setMaterial(null);

        return materialDesc;
    }

    public List<PurchaseOrderItem> getPurchaseOrderItems() {
        return this.prchsOrderItems;
    }

    public void setPurchaseOrderItems(List<PurchaseOrderItem> prchsOrderItems) {
        this.prchsOrderItems = prchsOrderItems;
    }

    public PurchaseOrderItem addPurchaseOrderItem(PurchaseOrderItem prchsOrderItem) {
        getPurchaseOrderItems().add(prchsOrderItem);
        prchsOrderItem.setMaterial(this);

        return prchsOrderItem;
    }

    public PurchaseOrderItem removePurchaseOrderItem(PurchaseOrderItem prchsOrderItem) {
        getPurchaseOrderItems().remove(prchsOrderItem);
        prchsOrderItem.setMaterial(null);

        return prchsOrderItem;
    }

}
