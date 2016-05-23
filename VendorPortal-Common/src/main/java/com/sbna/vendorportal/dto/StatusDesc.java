package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sbna.vendorportal.config.TableConfig;

import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = TableConfig.TABLE_STATUSDESC)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_statusdesc", allocationSize = 1)
public class StatusDesc extends BaseModel {

	private static final long serialVersionUID = 9215458373861552759L;

	@Column(name = "status_desc", nullable = false)
	private String statusDesc;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	private Language lang;

	@ManyToOne
	private Status status;

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Language getLang() {
		return lang;
	}

	public void setLang(Language lang) {
		this.lang = lang;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
