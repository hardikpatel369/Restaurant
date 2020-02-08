package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsItemImages {

    @SerializedName("ITEM_ID")
    @Expose
    private Integer iTEMID;
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("REMARK")
    @Expose
    private Object rEMARK;
    @SerializedName("URL")
    @Expose
    private String uRL;

    public Integer getITEMID() {
        return iTEMID;
    }

    public void setITEMID(Integer iTEMID) {
        this.iTEMID = iTEMID;
    }

    public String getNAME() {
        return nAME;
    }

    public void setNAME(String nAME) {
        this.nAME = nAME;
    }

    public Object getREMARK() {
        return rEMARK;
    }

    public void setREMARK(Object rEMARK) {
        this.rEMARK = rEMARK;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }
}
