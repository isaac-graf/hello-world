package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the mail_items database table.
 * 
 */
@Entity
@Table(name = "mail_items")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_mailitems", allocationSize = 1)
public class MailItem extends BaseModel {

	@Column(name = "mail_read")
	private boolean mailRead;

	private String message;

	private String subject;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "mail_from")
	@Column(name = "mail_from")
	private String mailFrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mail_to")
	private User usr2;

	public MailItem() {
	}

	public boolean getMailRead() {
		return this.mailRead;
	}

	public void setMailRead(boolean mailRead) {
		this.mailRead = mailRead;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public User getUsr2() {
		return this.usr2;
	}

	public void setUsr2(User usr2) {
		this.usr2 = usr2;
	}

	@Override
	public String toString() {
		return "MailItem [mailRead=" + mailRead + ", subject=" + subject
				+ ", toString()=" + super.toString() + "]";
	}

}