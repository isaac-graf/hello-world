package com.sbna.vendorportal.dto;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the cron_scheduler_run database table.
 * 
 */
@Entity
@Table(name="cron_scheduler_run")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_cronschedulerrun", allocationSize = 1) 
public class CronSchedulerRun extends BaseModel{
	private static final long serialVersionUID = 1L;

	@Column(name="cron_scheduler_id")
	private long cronSchedulerId;

	@Column(name="end_date")
	private Date endDate;

	
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Status
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cron_run_status_id")
	private Status status;

	public CronSchedulerRun() {
	}

	public long getCronSchedulerId() {
		return this.cronSchedulerId;
	}

	public void setCronSchedulerId(long cronSchedulerId) {
		this.cronSchedulerId = cronSchedulerId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}