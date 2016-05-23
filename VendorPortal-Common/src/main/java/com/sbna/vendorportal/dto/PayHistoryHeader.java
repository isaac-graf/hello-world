/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbna.vendorportal.dto;

import java.util.List;


/**
 *
 * @author santosh kumar
 */
public class PayHistoryHeader {

    private List<PayHistory> payHistory;

	public List<PayHistory> getPayHistory() {
		return payHistory;
	}

	public void setPayHistory(List<PayHistory> payHistory) {
		this.payHistory = payHistory;
	}

	@Override
	public String toString() {
		return "PayHistoryHeader [payHistory=" + payHistory + "]";
	}


	
}
