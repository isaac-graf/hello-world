package com.sbna.vendorportal.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sbna.vendorportal.util.Utils;
import com.sbna.vendorportal.util.impl.UtilsImpl;

public class MailItemHelper {
	private Long id;

	private boolean mailRead;

	private String fromUsername;

	private String subject;

	private String message;

	private Date created;

	private String mailDate;
	
	private Utils utils = new UtilsImpl();
	private DateFormat curDateDf = new SimpleDateFormat("hh:mm a");
	private DateFormat curYearDf = new SimpleDateFormat("MMM dd");
	private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

	public MailItemHelper(Long id, boolean mailRead, String fromUsername,
			String subject, String message, Date created) {
		super();
		this.id = id;
		this.mailRead = mailRead;
		this.fromUsername = fromUsername;
		this.subject = subject;
		this.message = message;
		this.created = created;

		setMailDate(created);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isMailRead() {
		return mailRead;
	}

	public void setMailRead(boolean mailRead) {
		this.mailRead = mailRead;
	}

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getMailDate() {
		return mailDate;
	}

	public void setMailDate(Date created) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(created);
		
		if (utils.isSameDate(created)) {
			
			this.mailDate = curDateDf.format(created);
		} else if(utils.isSameYear(created)){
			
			this.mailDate = curYearDf.format(created);
		} else{
			this.mailDate = df.format(created);
		}
		
	}

	@Override
	public String toString() {
		return "MailItemHelper [id=" + id + ", mailRead=" + mailRead
				+ ", fromUsername=" + fromUsername + ", subject=" + subject
				+ ", message=" + message + ", created=" + created
				+ ", mailDate=" + mailDate + "]";
	}

}
