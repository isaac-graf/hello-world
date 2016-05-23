package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * The persistent class for the ref_docs database table.
 *
 */
@Entity
@Table(name = "ref_docs")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_refdocs", allocationSize = 1)
public class RefDocs extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "ref_doc_name")
	private String refDocName;

	@Column(name = "ref_doc_obj")
	@JsonIgnore
	private byte[] refDocObj;

	@Column(name = "ref_id")
	private long refId;

	@Column(name = "ref_module")
	private String refModule;
	
	@Column(name = "grp_id")
	private String grpId;	

/*	@OneToOne(optional = false, mappedBy = "refDocs")
	@JsonIgnore
	private VendorContactDetails vendorContactDetails;*/

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getRefDocName() {
		return refDocName;
	}

	public void setRefDocName(String refDocName) {
		this.refDocName = refDocName;
	}

	public byte[] getRefDocObj() {
		return refDocObj;
	}

	public void setRefDocObj(byte[] refDocObj) {
		this.refDocObj = refDocObj;
	}

	public long getRefId() {
		return refId;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}

	public String getRefModule() {
		return refModule;
	}

	public void setRefModule(String refModule) {
		this.refModule = refModule;
	}

	/*public VendorContactDetails getVendorContactDetails() {
		return vendorContactDetails;
	}

	public void setVendorContactDetails(VendorContactDetails vendorContactDetails) {
		this.vendorContactDetails = vendorContactDetails;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
