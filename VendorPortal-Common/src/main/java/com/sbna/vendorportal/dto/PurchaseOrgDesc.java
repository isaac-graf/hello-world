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
 * The persistent class for the prchs_org_desc database table.
 * 
 */
@Entity
@Table(name = "prchs_org_desc")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_prchsorgdesc", allocationSize = 1)
//@NamedQuery(name = "PrchsOrgDesc.findAll", query = "SELECT p FROM PrchsOrgDesc p")
public class PurchaseOrgDesc extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "prchs_org_desc")
	private String prchsOrgDesc;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	private Language lang;

	// bi-directional many-to-one association to PrchsOrg
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prchs_org_id")
	private PurchaseOrg prchsOrg;

	public PurchaseOrgDesc() {
	}

	public String getPrchsOrgDesc() {
		return this.prchsOrgDesc;
	}

	public void setPrchsOrgDesc(String prchsOrgDesc) {
		this.prchsOrgDesc = prchsOrgDesc;
	}

	public Language getLanguage() {
		return this.lang;
	}

	public void setLanguage(Language lang) {
		this.lang = lang;
	}

	public PurchaseOrg getPrchsOrg() {
		return this.prchsOrg;
	}

	public void setPrchsOrg(PurchaseOrg prchsOrg) {
		this.prchsOrg = prchsOrg;
	}

}