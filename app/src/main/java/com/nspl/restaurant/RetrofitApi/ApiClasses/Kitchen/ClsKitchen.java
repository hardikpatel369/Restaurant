package com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsKitchen {

    @SerializedName("KITCHEN_ID")
    @Expose
    private Integer kITCHENID;
    @SerializedName("KITCHEN_NAME")
    @Expose
    private String kITCHENNAME;
    @SerializedName("SECTION")
    @Expose
    private String sECTION;

    public Integer getKITCHENID() {
        return kITCHENID;
    }

    public void setKITCHENID(Integer kITCHENID) {
        this.kITCHENID = kITCHENID;
    }

    public String getKITCHENNAME() {
        return kITCHENNAME;
    }

    public void setKITCHENNAME(String kITCHENNAME) {
        this.kITCHENNAME = kITCHENNAME;
    }

    public String getSECTION() {
        return sECTION;
    }

    public void setSECTION(String sECTION) {
        this.sECTION = sECTION;
    }
}
