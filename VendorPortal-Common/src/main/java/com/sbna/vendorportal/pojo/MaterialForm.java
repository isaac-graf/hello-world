package com.sbna.vendorportal.pojo;

public class MaterialForm {

	private Long materialId;

	private String materialCode;

	private String materialEngName;

	private String materialLocalName;
	
	public MaterialForm(){
	}
	
	public MaterialForm(Long materialId, String materialCode, String materialEngName, String materialLocalName) {
		super();
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialEngName = materialEngName;
		this.materialLocalName = materialLocalName;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}



	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialEngName() {
		return materialEngName;
	}

	public void setMaterialEngName(String materialEngName) {
		this.materialEngName = materialEngName;
	}

	public String getMaterialLocalName() {
		return materialLocalName;
	}

	public void setMaterialLocalName(String materialLocalName) {
		this.materialLocalName = materialLocalName;
	}

}
