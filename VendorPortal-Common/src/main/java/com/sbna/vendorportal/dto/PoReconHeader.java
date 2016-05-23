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
public class PoReconHeader {

    private String testStr = "A";

    private List<PoRecon> poRecon;

    public List<PoRecon> getPoRecon() {
        return poRecon;
    }

    public void setPoRecon(List<PoRecon> poRecon) {
        this.poRecon = poRecon;
    }

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    @Override
    public String toString() {
        return "PoReconHeader [poRecon=" + poRecon + "]";
    }

}
