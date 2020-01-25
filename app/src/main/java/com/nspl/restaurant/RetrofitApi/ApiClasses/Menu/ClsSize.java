package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsSize {

    @SerializedName("ITEM_ID")
    @Expose
    private Integer iTEMID;
    @SerializedName("SIZE_ID")
    @Expose
    private Integer sIZEID;
    @SerializedName("SIZE")
    @Expose
    private String sIZE;
    @SerializedName("PRICE")
    @Expose
    private Double pRICE;

    public Integer getiTEMID() {
        return iTEMID;
    }

    public void setiTEMID(Integer iTEMID) {
        this.iTEMID = iTEMID;
    }

    public Integer getsIZEID() {
        return sIZEID;
    }

    public void setsIZEID(Integer sIZEID) {
        this.sIZEID = sIZEID;
    }

    public String getsIZE() {
        return sIZE;
    }

    public void setsIZE(String sIZE) {
        this.sIZE = sIZE;
    }

    public Double getpRICE() {
        return pRICE;
    }

    public void setpRICE(Double pRICE) {
        this.pRICE = pRICE;
    }
}
