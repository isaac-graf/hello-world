package com.sbna.vendorportal.dto;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the cron_scheduler database table.
 * 
 */
@Entity
@Table(name="cron_scheduler")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_cronscheduler", allocationSize = 1) 
public class CronScheduler extends BaseModel{
	private static final long serialVersionUID = 1L;

	@Column(name="cron_freq")
	private String cronFreq;

	@Column(name="cron_name")
	private String cronName;
	
	//bi-directional many-to-one association to Status
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cron_status_id")
	private Status status;

	public CronScheduler() {
	}

	public String getCronFreq() {
		return this.cronFreq;
	}

	public void setCronFreq(String cronFreq) {
		this.cronFreq = cronFreq;
	}

	public String getCronName() {
		return this.cronName;
	}

	public void setCronName(String cronName) {
		this.cronName = cronName;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}