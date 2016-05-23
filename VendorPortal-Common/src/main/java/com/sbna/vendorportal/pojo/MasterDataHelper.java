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
public class MasterDataHelper implements Serializable {

    private String optionKey;
    private String optionVal;

    public MasterDataHelper(){
    	
    }
    public MasterDataHelper(String optionKey, String optionVal) {
        this.optionKey = optionKey;
        this.optionVal = optionVal;
    }

    public String getOptionKey() {
        return optionKey;
    }

    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }

    public String getOptionVal() {
        return optionVal;
    }

    public void setOptionVal(String optionVal) {
        this.optionVal = optionVal;
    }

    @Override
    public String toString() {
        return "MasterDataHelper{" + "optionKey=" + optionKey + ", optionVal=" + optionVal + '}';
    }

}
