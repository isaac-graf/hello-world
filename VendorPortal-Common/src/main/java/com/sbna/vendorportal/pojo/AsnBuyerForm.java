package com.sbna.vendorportal.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AsnBuyerForm extends AsnForm {

	private String vendorName;
	private String plantCode;
	private String plantDescription;	
	private String materialCode;
	private String matExtraDesc;	
	private String materialDescription;
	private String dispMatCode;	
	private String dispMatDesc;	
	private Double deliveryQty;
	private String uom;
	private Date deliveryDate;
	private String deliveryDateStr;
	private long deliveryDateInMs;	
	private String poNo;
	private String carrier1;
	private String trackingNo1;
	private String carrier2;
	private String trackingNo2;
	private String asnNo;
	
	private static DateFormat df = new SimpleDateFormat("MM-dd-yyyy");	

	public AsnBuyerForm(String vendorName, String plantCode, String plantDescription,
			String materialCode, String matExtraDesc, String materialDescription, Double deliveryQty,
			String uom, Date deliveryDate, String poNo, String carrier1,
			String trackingNo1, String carrier2, String trackingNo2, Long asnNo) {
		super();
		this.vendorName = vendorName;
		this.plantCode = plantCode;
		this.plantDescription = plantDescription;
		this.materialCode = materialCode;
		this.matExtraDesc = matExtraDesc;
		this.materialDescription = materialDescription;
		this.deliveryQty = deliveryQty;
		this.uom = uom;
		this.deliveryDate = deliveryDate;
		this.poNo = poNo;
		this.carrier1 = carrier1;
		this.trackingNo1 = trackingNo1;
		this.carrier2 = carrier2;
		this.trackingNo2 = trackingNo2;
		this.asnNo = String.valueOf(asnNo);
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

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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

	public Double getDeliveryQty() {
		return deliveryQty;
	}

	public void setDeliveryQty(Double deliveryQty) {
		this.deliveryQty = deliveryQty;
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

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
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

	public String getAsnNo() {
		return asnNo;
	}

	public void setAsnNo(String asnNo) {
		this.asnNo = asnNo;
	}

	@Override
	public String toString() {
		return "AsnBuyerForm [vendorName=" + vendorName + ", plantCode="
				+ plantCode + ", plantDescription=" + plantDescription
				+ ", materialCode=" + materialCode+ ", matExtraDesc=" + matExtraDesc + ", materialDescription="
				+ materialDescription + ", deliveryQty=" + deliveryQty
				+ ", uom=" + uom + ", deliveryDate=" + deliveryDate
				+ ", deliveryDateStr=" + deliveryDateStr
				+ ", deliveryDateInMs=" + deliveryDateInMs + ", poNo=" + poNo
				+ ", carrier1=" + carrier1 + ", trackingNo1=" + trackingNo1
				+ ", carrier2=" + carrier2 + ", trackingNo2=" + trackingNo2
				+ ", asnNo=" + asnNo + "]";
	}

}
