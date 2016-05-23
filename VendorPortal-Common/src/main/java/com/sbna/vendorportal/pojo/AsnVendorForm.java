package com.sbna.vendorportal.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AsnVendorForm extends AsnForm {

	private String poNo;

	private Date poDate;

	private String poDateStr;

	private long poDateInMs;

	private String plantCode;
	
	private String plantDescription;

	private String materialCode;
	
	private String matExtraDesc;

	private String materialDescription;
	
	private String dispMatCode;
	
	private String dispMatDesc;	

	private Double deliveryQuantity;

	private String uom;

	private Date deliveryDate;

	private String deliveryDateStr;

	private long deliveryDateInMs;

	private String asnNo;
	
	private String asnGroupId;	

	private String carrier1;

	private String trackingNo1;

	private String carrier2;

	private String trackingNo2;

	private static DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

	public AsnVendorForm() {
	}

	public AsnVendorForm(String poNo, Date poDate, String plantCode, 
			String plantDescription, String materialCode, String matExtraDesc, 
			String materialDescription, Double deliveryQuantity, String uom,
			Date deliveryDate, Long asnNo, String asnGroupId, String carrier1, String trackingNo1,
			String carrier2, String trackingNo2) {
		super();
		this.poNo = poNo;
		this.poDate = poDate;
		this.poDateStr = poDate != null ? df.format(poDate) : "";
		this.poDateInMs = poDate != null ? poDate.getTime() : 0L;
		this.plantCode = plantCode;
		this.plantDescription = plantDescription;
		this.materialCode = materialCode;
		this.matExtraDesc = matExtraDesc;
		this.materialDescription = materialDescription;
		this.deliveryQuantity = deliveryQuantity;
		this.uom = uom;
		this.deliveryDate = deliveryDate;
		this.asnNo = String.valueOf(asnNo);
		this.asnGroupId = asnGroupId;
		this.carrier1 = carrier1;
		this.trackingNo1 = trackingNo1;
		this.carrier2 = carrier2;
		this.trackingNo2 = trackingNo2;
		this.deliveryDateStr = deliveryDate != null ? df.format(deliveryDate)
				: "";
		this.deliveryDateInMs = deliveryDate != null ? deliveryDate.getTime()
				: 0L;
		
		if(materialCode != null){
			this.dispMatCode = materialCode;
			this.dispMatDesc = materialDescription;
		}else{
			this.dispMatCode = matExtraDesc;
			this.dispMatDesc = matExtraDesc;			
		}		

	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getPoDateStr() {
		return poDateStr;
	}

	public void setPoDateStr(String poDateStr) {
		this.poDateStr = poDateStr;
	}

	public long getPoDateInMs() {
		return poDateInMs;
	}

	public void setPoDateInMs(long poDateInMs) {
		this.poDateInMs = poDateInMs;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public String getPlantDescription() {
		return plantDescription;
	}

	public void setPlantDescription(String plantDescription) {
		this.plantDescription = plantDescription;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMatExtraDesc() {
		return matExtraDesc;
	}

	public void setMatExtraDesc(String matExtraDesc) {
		this.matExtraDesc = matExtraDesc;
	}

	public String getDispMatCode() {
		return dispMatCode;
	}

	public void setDispMatCode(String dispMatCode) {
		this.dispMatCode = dispMatCode;
	}

	public String getDispMatDesc() {
		return dispMatDesc;
	}

	public void setDispMatDesc(String dispMatDesc) {
		this.dispMatDesc = dispMatDesc;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public Double getDeliveryQuantity() {
		return deliveryQuantity;
	}

	public void setDeliveryQuantity(Double deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryDateStr() {
		return deliveryDateStr;
	}

	public void setDeliveryDateStr(String deliveryDateStr) {
		this.deliveryDateStr = deliveryDateStr;
	}

	public long getDeliveryDateInMs() {
		return deliveryDateInMs;
	}

	public void setDeliveryDateInMs(long deliveryDateInMs) {
		this.deliveryDateInMs = deliveryDateInMs;
	}

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

	public String getCarrier1() {
		return carrier1;
	}

	public void setCarrier1(String carrier1) {
		this.carrier1 = carrier1;
	}

	public String getTrackingNo1() {
		return trackingNo1;
	}

	public void setTrackingNo1(String trackingNo1) {
		this.trackingNo1 = trackingNo1;
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
		return "AsnVendorForm [poNo=" + poNo + ", poDate=" + poDate
				+ ", poDateStr=" + poDateStr + ", poDateInMs=" + poDateInMs
				+ ", plantCode=" + plantCode
				+ ", plantDescription=" + plantDescription + ", materialCode="
				+ materialCode + ", matExtraDesc=" + matExtraDesc+ ", materialDescription=" + materialDescription
				+ ", deliveryQuantity=" + deliveryQuantity + ", uom=" + uom
				+ ", deliveryDate=" + deliveryDate + ", deliveryDateStr="
				+ deliveryDateStr + ", deliveryDateInMs=" + deliveryDateInMs
				+ ", asnNo=" + asnNo + ", asnGroupId=" + asnGroupId + ", carrier1=" + carrier1
				+ ", trackingNo1=" + trackingNo1 + ", carrier2=" + carrier2
				+ ", trackingNo2=" + trackingNo2 + "]";
	}

}