package com.sbna.vendorportal.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the prchs_order database table.
 * 
 */
@Entity
@Table(name = "prchs_order")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_po", allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "getUniqueASNs", query = "SELECT DISTINCT asn.id FROM PurchaseOrderItemsAsn asn"),
	@NamedQuery(name = "PurchaseOrder.getPurchaseOrdersVendor",
    query = "SELECT byrRemarks,vndrRemarks,poNo,poDate FROM PurchaseOrder po WHERE po.poNo >= :poNoMin AND po.poNo <= :poNoMax"),
	@NamedQuery(name = "getUniquePOs", query = "SELECT DISTINCT po.poNo FROM PurchaseOrder po") })
//@NamedQuery(name = "PrchsOrder.findAll", query = "SELECT p FROM PrchsOrder p")
public class PurchaseOrder extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "byr_remarks")
	private String byrRemarks;

	@Column(name = "po_date")
	private Date poDate;

	@Column(name = "po_no")
	private String poNo;

	@Column(name = "vndr_remarks")
	private String vndrRemarks;

	// bi-directional many-to-one association to PrchsOrg
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prchs_org_id")
	private PurchaseOrg prchsOrg;

	// bi-directional many-to-one association to Status
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "po_status_id")
	private Status status;

	// bi-directional many-to-one association to PrchsOrderItem
	@OneToMany(mappedBy = "prchsOrder",fetch = FetchType.EAGER)
	private List<PurchaseOrderItem> prchsOrderItems;
	
	// bi-directional many-to-one association to Status
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vndr_code")
	private User user;

	public PurchaseOrder() {
	}

	public String getByrRemarks() {
		return this.byrRemarks;
	}

	public void setByrRemarks(String byrRemarks) {
		this.byrRemarks = byrRemarks;
	}

	public Date getPoDate() {
		return this.poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getVndrRemarks() {
		return this.vndrRemarks;
	}

	public void setVndrRemarks(String vndrRemarks) {
		this.vndrRemarks = vndrRemarks;
	}

	public PurchaseOrg getPrchsOrg() {
		return this.prchsOrg;
	}

	public void setPrchsOrg(PurchaseOrg prchsOrg) {
		this.prchsOrg = prchsOrg;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<PurchaseOrderItem> getPrchsOrderItems() {
		return this.prchsOrderItems;
	}

	public void setPrchsOrderItems(List<PurchaseOrderItem> prchsOrderItems) {
		this.prchsOrderItems = prchsOrderItems;
	}

	public PurchaseOrderItem addPrchsOrderItem(PurchaseOrderItem prchsOrderItem) {
		getPrchsOrderItems().add(prchsOrderItem);
		prchsOrderItem.setPrchsOrder(this);

		return prchsOrderItem;
	}

	public PurchaseOrderItem removePrchsOrderItem(PurchaseOrderItem prchsOrderItem) {
		getPrchsOrderItems().remove(prchsOrderItem);
		prchsOrderItem.setPrchsOrder(null);

		return prchsOrderItem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [byrRemarks=" + byrRemarks + ", poDate=" + poDate
				+ ", poNo=" + poNo + ", vndrRemarks=" + vndrRemarks
				+ ", prchsOrg=" + prchsOrg + ", status=" + status
				+ ", prchsOrderItems=" + prchsOrderItems + ", user=" + user
				+ "]";
	}

	
}