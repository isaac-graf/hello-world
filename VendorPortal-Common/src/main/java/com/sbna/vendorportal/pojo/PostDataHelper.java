/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbna.vendorportal.pojo;

import java.io.Serializable;

/**
 *
 * @author Pbatthala
 */
public class PostDataHelper implements Serializable {

    private String asData;
    private String vpData;
    private String otherData;

    public String getAsData() {
        return asData;
    }

    public void setAsData(String asData) {
        this.asData = asData;
    }

    public String getVpData() {
        return vpData;
    }

    public void setVpData(String vpData) {
        this.vpData = vpData;
    }

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }

    @Override
    public String toString() {
        return "PostDataHelper{" + "asData=" + asData + ", vpData=" + vpData + '}';
    }

}
