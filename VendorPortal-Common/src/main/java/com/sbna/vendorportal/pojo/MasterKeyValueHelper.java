package com.sbna.vendorportal.pojo;

import java.io.Serializable;

public class MasterKeyValueHelper implements Serializable {
	
	private Long key;
	private Long value;
	private String valueAsString;
	
	public MasterKeyValueHelper(){}
	
	public MasterKeyValueHelper(Long key,Long value){
		this.key=key;
		this.value=value;
	}
	public MasterKeyValueHelper(Long key,String valueAsString){
		this.key=key;
		this.valueAsString=valueAsString;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getValueAsString() {
		return valueAsString;
	}
	public void setValueAsString(String valueAsString) {
		this.valueAsString = valueAsString;
	}
	
	

}
