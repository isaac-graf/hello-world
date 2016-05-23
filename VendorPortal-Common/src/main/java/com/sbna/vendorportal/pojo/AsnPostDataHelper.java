package com.sbna.vendorportal.pojo;

public class AsnPostDataHelper {
	private String asnNo;
	private String asnGroupId;
	private String carrier2;
	private String trackingNo2;

	public String getAsnNo() {
		return asnNo;
	}
	public void setAsnNo(String asnNo) {
		this.asnNo = asnNo;
	}
	public String getAsnGroupId() {
		return asnGroupId;
	}
	public void setAsnGroupId(String asnGroupId) {
		this.asnGroupId = asnGroupId;
	}
	public String getCarrier2() {
		return carrier2;
	}
	public void setCarrier2(String carrier2) {
		this.carrier2 = carrier2;
	}
	public String getTrackingNo2() {
		return trackingNo2;
	}
	public void setTrackingNo2(String trackingNo2) {
		this.trackingNo2 = trackingNo2;
	}

	@Override
	public String toString() {
		return "AsnPostDataHelper [asnNo=" + asnNo + ", asnGroupId=" + asnGroupId + ", carrier2=" + carrier2
				+ ", trackingNo2=" + trackingNo2 + "]";
	}	
}
