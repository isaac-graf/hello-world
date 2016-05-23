package com.sbna.vendorportal.pojo;

public class PCRSearchForm {
	private long minPcr;
	private long maxPcr;
	private long minMatCode;
	private long maxMatCode;
	private String year;
	private String month;
	private long status;
	private long vendorId;
	public long getMinPcr() {
		return minPcr;
	}
	public void setMinPcr(long minPcr) {
		this.minPcr = minPcr;
	}
	public long getMaxPcr() {
		return maxPcr;
	}
	public void setMaxPcr(long maxPcr) {
		this.maxPcr = maxPcr;
	}
	public long getMinMatCode() {
		return minMatCode;
	}
	public void setMinMatCode(long minMatCode) {
		this.minMatCode = minMatCode;
	}
	public long getMaxMatCode() {
		return maxMatCode;
	}
	public void setMaxMatCode(long maxMatCode) {
		this.maxMatCode = maxMatCode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	
	

}
