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
public class DelPerfHeader {


    private List<DelPerf> delPerf;

	public List<DelPerf> getDelPerf() {
		return delPerf;
	}

	public void setDelPerf(List<DelPerf> delPerf) {
		this.delPerf = delPerf;
	}

	@Override
	public String toString() {
		return "DelPerfHeader [delPerf=" + delPerf + "]";
	}

    
}
