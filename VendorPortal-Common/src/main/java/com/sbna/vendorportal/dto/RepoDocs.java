package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the ref_docs database table.
 *
 */
@Entity
@Table(name = "repo_docs")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_repodocs", allocationSize = 1)
public class RepoDocs extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "doc_name")
	private String docName;

	@Column(name = "doc_obj")
	@JsonIgnore
	private byte[] docObj;
	
	@Column(name = "doc_desc")
	private String docDesc;
	
	@Column(name = "doc_type")
	private String docType;	

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public byte[] getDocObj() {
		return docObj;
	}

	public void setDocObj(byte[] docObj) {
		this.docObj = docObj;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}	


}
