package com.sbna.vendorportal.pojo;

import java.util.Date;

public class RepoDocForm {
	
	private Long id;
	
	private String docName;
	
	private String docDesc;
	
	private Date created;

	private Long createdInMs;
	
	public RepoDocForm(Long id, String docName, String docDesc, Date created) {
		super();
		this.id = id;
		this.docName = docName;
		this.docDesc = docDesc;
		this.created = created;
		this.createdInMs = created.getTime();
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getCreatedInMs() {
		return createdInMs;
	}

	public void setCreatedInMs(Long createdInMs) {
		this.createdInMs = createdInMs;
	}		
	
}
