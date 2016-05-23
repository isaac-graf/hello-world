package com.sbna.vendorportal.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sbna.vendorportal.config.TableConfig;

/**
 * The persistent class for the prchs_order_items_asn database table.
 * 
 */
@Entity
@Table(name = TableConfig.TABLE_PURCHASEORDERITEM_ASN)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_poitemsasn", allocationSize = 1)

public class PurchaseOrderItemsAsn extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "asn_group_id")
	private String asnGroupId;	
	
	@Column(name = "carrier_name1")
	private String carrierName1;

	@Column(name = "carrier_name2")
	private String carrierName2;

	@Column(name = "current_dispatch_quantity")
	private Double currentDispatchQuantity;

	@Column(name = "delivery_date")
	private Date deliveryDate;

	@Column(name = "track_no1")
	private String trackNo1;

	@Column(name = "track_no2")
	private String trackNo2;

	// bi-directional many-to-one association to PrchsOrderItem
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "po_items_id")
	private PurchaseOrderItem prchsOrderItem;

	public PurchaseOrderItemsAsn() {
	}

	public String getAsnGroupId() {
		return asnGroupId;
	}

	public void setAsnGroupId(String asnGroupId) {
		this.asnGroupId = asnGroupId;
	}

	public String getCarrierName1() {
		return this.carrierName1;
	}

	public void setCarrierName1(String carrierName1) {
		this.carrierName1 = carrierName1;
	}

	public String getCarrierName2() {
		return this.carrierName2;
	}

	public void setCarrierName2(String carrierName2) {
		this.carrierName2 = carrierName2;
	}

	public Double getCurrentDispatchQuantity() {
		return this.currentDispatchQuantity;
	}

	public void setCurrentDispatchQuantity(Double currentDispatchQuantity) {
		this.currentDispatchQuantity = currentDispatchQuantity;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
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

	public PurchaseOrderItem getPrchsOrderItem() {
		return this.prchsOrderItem;
	}

	public void setPrchsOrderItem(PurchaseOrderItem prchsOrderItem) {
		this.prchsOrderItem = prchsOrderItem;
	}

	@Override
	public String auditString() {
		String prchsOrderItemId = prchsOrderItem != null ? String.valueOf(prchsOrderItem.getId()) : null ;
		String auditStr = "asnGroupId=" + asnGroupId
				+ ", carrierName1=" + carrierName1
				+ ", carrierName2=" + carrierName2
				+ ", currentDispatchQuantity=" + currentDispatchQuantity
				+ ", deliveryDate=" + deliveryDate + ", trackNo1=" + trackNo1
				+ ", trackNo2=" + trackNo2 + ", prchsOrderItemId="
				+ prchsOrderItemId;
		return auditStr;
	}

}