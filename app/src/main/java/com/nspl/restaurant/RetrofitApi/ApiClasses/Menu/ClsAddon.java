package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsAddon {

    @SerializedName("ITEM_ID")
    @Expose
    private Integer iTEMID;
    @SerializedName("ADDON_ID")
    @Expose
    private Integer aDDONID;
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("PRICE")
    @Expose
    private Double pRICE;
    @SerializedName("REMARK")
    @Expose
    private String rEMARK;

    Boolean selected=false;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getiTEMID() {
        return iTEMID;
    }

    public void setiTEMID(Integer iTEMID) {
        this.iTEMID = iTEMID;
    }

    public Integer getaDDONID() {
        return aDDONID;
    }

    public void setaDDONID(Integer aDDONID) {
        this.aDDONID = aDDONID;
    }

    public String getnAME() {
        return nAME;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    public Double getpRICE() {
        return pRICE;
    }

    public void setpRICE(Double pRICE) {
        this.pRICE = pRICE;
    }

    public String getrEMARK() {
        return rEMARK;
    }

    public void setrEMARK(String rEMARK) {
        this.rEMARK = rEMARK;
    }
}
