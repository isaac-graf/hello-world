package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the prchs_order database table.
 * 
 */
@Entity
@Table(name = "delivery_perf")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_del_perf", allocationSize = 1)
public class DeliveryPerformance extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "po_no")
	private String poNo;

	// bi-directional many-to-one association to Status
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "po_status_id")
	private Status status;

	

	public DeliveryPerformance() {
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "DeliveryPerformance [poNo=" + poNo + ", status=" + status + "]";
	}

	
}