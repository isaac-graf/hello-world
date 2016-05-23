package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the prchs_order_items database table.
 * 
 */
@Entity
@Table(name = "prchs_order_items")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_poitems", allocationSize = 1)
// @NamedQuery(name = "PrchsOrderItem.findAll", query =
// "SELECT p FROM PrchsOrderItem p")
public class PurchaseOrderItem extends BaseModel {
	private static final long serialVersionUID = 1L;

//	@Column(name = "prchs_grp_id")
//	private long prchsGrp;
    @ManyToOne
    @JoinColumn(name = "prchs_grp_id")
    @JsonIgnore
    private PurchaseGroup prchsGrp;	

	@Column(name = "item_no")
	private String itemNo;

	private Double quantity;

	@Column(name = "balance_quantity")
	private Double balanceQuantity;
	
	@Column(name = "mat_extra_desc")
	private String matExtraDesc;
	
	private String uom;	

	// bi-directional many-to-one association to Material
	@ManyToOne(fetch = FetchType.EAGER)
	private Material material;

	// bi-directional many-to-one association to Plant
	@ManyToOne(fetch = FetchType.EAGER)
	private Plant plant;

	// bi-directional many-to-one association to PrchsOrder
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "po_no")
	private PurchaseOrder prchsOrder;

	// bi-directional many-to-one association to PrchsOrderItemsAsn
	@OneToMany(mappedBy = "prchsOrderItem")
	private List<PurchaseOrderItemsAsn> prchsOrderItemsAsns;

	public PurchaseOrderItem() {
	}

	public PurchaseGroup getPrchsGrp() {
		return prchsGrp;
	}

	public void setPrchsGrp(PurchaseGroup prchsGrp) {
		this.prchsGrp = prchsGrp;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Plant getPlant() {
		return this.plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public PurchaseOrder getPrchsOrder() {
		return this.prchsOrder;
	}

	public void setPrchsOrder(PurchaseOrder prchsOrder) {
		this.prchsOrder = prchsOrder;
	}

	public List<PurchaseOrderItemsAsn> getPrchsOrderItemsAsns() {
		return this.prchsOrderItemsAsns;
	}

	public void setPrchsOrderItemsAsns(
			List<PurchaseOrderItemsAsn> prchsOrderItemsAsns) {
		this.prchsOrderItemsAsns = prchsOrderItemsAsns;
	}

	public PurchaseOrderItemsAsn addPrchsOrderItemsAsn(
			PurchaseOrderItemsAsn prchsOrderItemsAsn) {
		getPrchsOrderItemsAsns().add(prchsOrderItemsAsn);
		prchsOrderItemsAsn.setPrchsOrderItem(this);

		return prchsOrderItemsAsn;
	}

	public PurchaseOrderItemsAsn removePrchsOrderItemsAsn(
			PurchaseOrderItemsAsn prchsOrderItemsAsn) {
		getPrchsOrderItemsAsns().remove(prchsOrderItemsAsn);
		prchsOrderItemsAsn.setPrchsOrderItem(null);

		return prchsOrderItemsAsn;
	}

	public Double getBalanceQuantity() {
		return this.balanceQuantity;
	}

	public void setBalanceQuantity(Double balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getMatExtraDesc() {
		return matExtraDesc;
	}

	public void setMatExtraDesc(String matExtraDesc) {
		this.matExtraDesc = matExtraDesc;
	}

	

	
}