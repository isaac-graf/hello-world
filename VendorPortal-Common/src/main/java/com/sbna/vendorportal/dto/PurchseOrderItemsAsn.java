package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the prchs_order_items_asn database table.
 * 
 */
@Entity
@Table(name = "prchs_order_items_asn")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_poitemsasn", allocationSize = 1)
//@NamedQuery(name = "PurchaseOrderItemsAsn.findAll", query = "SELECT p FROM PurchaseOrderItemsAsn p")
public class PurchseOrderItemsAsn extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "asn_no")
	private String asnNo;

	@Column(name = "balance_quantity")
	private long balanceQuantity;

	@Column(name = "career_name1")
	private String careerName1;

	@Column(name = "career_name2")
	private String careerName2;

	@Column(name = "current_dispatch_quantity")
	private long currentDispatchQuantity;

	@Column(name = "delivery_date")
	private String deliveryDate;

	@Column(name = "item_no")
	private long itemNo;

	private long quantity;

	@Column(name = "track_no1")
	private String trackNo1;

	@Column(name = "track_no2")
	private String trackNo2;

	private String uom;

	// bi-directional many-to-one association to PurchaseOrderItem
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "po_items_id")
	private PurchaseOrderItem prchsOrderItem;

	public PurchseOrderItemsAsn() {
	}

	public String getAsnNo() {
		return this.asnNo;
	}

	public void setAsnNo(String asnNo) {
		this.asnNo = asnNo;
	}

	public long getBalanceQuantity() {
		return this.balanceQuantity;
	}

	public void setBalanceQuantity(long balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public String getCareerName1() {
		return this.careerName1;
	}

	public void setCareerName1(String careerName1) {
		this.careerName1 = careerName1;
	}

	public String getCareerName2() {
		return this.careerName2;
	}

	public void setCareerName2(String careerName2) {
		this.careerName2 = careerName2;
	}

	public long getCurrentDispatchQuantity() {
		return this.currentDispatchQuantity;
	}

	public void setCurrentDispatchQuantity(long currentDispatchQuantity) {
		this.currentDispatchQuantity = currentDispatchQuantity;
	}

	public String getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public long getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(long itemNo) {
		this.itemNo = itemNo;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getTrackNo1() {
		return this.trackNo1;
	}

	public void setTrackNo1(String trackNo1) {
		this.trackNo1 = trackNo1;
	}

	public String getTrackNo2() {
		return this.trackNo2;
	}

	public void setTrackNo2(String trackNo2) {
		this.trackNo2 = trackNo2;
	}

	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public PurchaseOrderItem getPurchaseOrderItem() {
		return this.prchsOrderItem;
	}

	public void setPurchaseOrderItem(PurchaseOrderItem prchsOrderItem) {
		this.prchsOrderItem = prchsOrderItem;
	}

}