package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * The persistent class for the plant database table.
 *
 */
@Entity
@Table(name = "plant")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_plant", allocationSize = 1)
// @NamedQuery(name = "Plant.findAll", query = "SELECT p FROM Plant p")
public class Plant extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "plant_code")
    private String plantCode;

    @Column(name = "plant_desc_eng")
    private String plantDescEng;

    // bi-directional many-to-one association to Status
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_status_id")
    private Status status;

    // bi-directional many-to-one association to PlantDesc
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "plant")
    private List<PlantDesc> plantDescs;

    // bi-directional many-to-one association to PrchsOrderItem
    @OneToMany(mappedBy = "plant")
    private List<PurchaseOrderItem> prchsOrderItems;

    // bi-directional many-to-one association to Pcr
    @OneToMany(mappedBy = "plant")
    @JsonManagedReference
    private List<Pcr> pcrs;

    public Plant() {
    }

    public String getPlantCode() {
        return this.plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantDescEng() {
        return this.plantDescEng;
    }

    public void setPlantDescEng(String plantDescEng) {
        this.plantDescEng = plantDescEng;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<PlantDesc> getPlantDescs() {
        return this.plantDescs;
    }

    public void setPlantDescs(List<PlantDesc> plantDescs) {
        this.plantDescs = plantDescs;
    }

    public PlantDesc addPlantDesc(PlantDesc plantDesc) {
        getPlantDescs().add(plantDesc);
        plantDesc.setPlant(this);

        return plantDesc;
    }

    public PlantDesc removePlantDesc(PlantDesc plantDesc) {
        getPlantDescs().remove(plantDesc);
        plantDesc.setPlant(null);

        return plantDesc;
    }

    public List<PurchaseOrderItem> getPrchsOrderItems() {
        return this.prchsOrderItems;
    }

    public void setPrchsOrderItems(List<PurchaseOrderItem> prchsOrderItems) {
        this.prchsOrderItems = prchsOrderItems;
    }

    public PurchaseOrderItem addPrchsOrderItem(PurchaseOrderItem prchsOrderItem) {
        getPrchsOrderItems().add(prchsOrderItem);
        prchsOrderItem.setPlant(this);

        return prchsOrderItem;
    }

    public PurchaseOrderItem removePrchsOrderItem(
            PurchaseOrderItem prchsOrderItem) {
        getPrchsOrderItems().remove(prchsOrderItem);
        prchsOrderItem.setPlant(null);

        return prchsOrderItem;
    }

}
