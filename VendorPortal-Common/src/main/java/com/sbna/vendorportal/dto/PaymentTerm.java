package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sbna.vendorportal.config.TableConfig;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = TableConfig.TABLE_PAYMENTTERM)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_pmttrm", allocationSize = 1)
public class PaymentTerm extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7149906058024005527L;
	@Column(name = "pmttrm_val", nullable = false)
	private String paymentTermVal;

	public String getPaymentTermVal() {
		return paymentTermVal;
	}

	public void setPaymentTermVal(String paymentTermVal) {
		this.paymentTermVal = paymentTermVal;
	}
}
