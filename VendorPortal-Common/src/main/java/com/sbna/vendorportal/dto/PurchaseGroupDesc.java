package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * The persistent class for the prchs_grp_desc database table.
 * 
 */
@Entity
@Table(name = "prchs_grp_desc")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_prchsgrpdesc", allocationSize = 1)
//@NamedQuery(name = "PrchsGrpDesc.findAll", query = "SELECT p FROM PrchsGrpDesc p")
public class PurchaseGroupDesc extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "prchs_grp_desc")
	private String prchsGrpDesc;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	private Language lang;

	// bi-directional many-to-one association to PrchsGrp
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prchs_grp_id")
	@JsonManagedReference
	private PurchaseGroup prchsGrp;

	public PurchaseGroupDesc() {
	}

	public String getPrchsGrpDesc() {
		return this.prchsGrpDesc;
	}

	public void setPrchsGrpDesc(String prchsGrpDesc) {
		this.prchsGrpDesc = prchsGrpDesc;
	}

	public Language getLanguage() {
		return this.lang;
	}

	public void setLanguage(Language lang) {
		this.lang = lang;
	}

	public PurchaseGroup getPrchsGrp() {
		return this.prchsGrp;
	}

	public void setPrchsGrp(PurchaseGroup prchsGrp) {
		this.prchsGrp = prchsGrp;
	}

}