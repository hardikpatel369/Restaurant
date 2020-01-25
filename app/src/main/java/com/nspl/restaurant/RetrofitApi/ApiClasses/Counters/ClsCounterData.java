package com.nspl.restaurant.RetrofitApi.ApiClasses.Counters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsCounterData {

    @SerializedName("COUNTER_ID")
    @Expose
    private Integer cOUNTERID;
    @SerializedName("COUNTER_NAME")
    @Expose
    private String cOUNTERNAME;
    @SerializedName("COUNTER_TYPE")
    @Expose
    private String cOUNTERTYPE;
    @SerializedName("DEPARTMENT_ID")
    @Expose
    private Integer dEPARTMENTID;
    @SerializedName("DEPARTMENT_NAME")
    @Expose
    private String dEPARTMENTNAME;
    @SerializedName("DEFAULT_MENU_ID")
    @Expose
    private Integer dEFAULTMENUID;
    @SerializedName("MENU_NAME")
    @Expose
    private String mENUNAME;

    public Integer getCOUNTERID() {
        return cOUNTERID;
    }

    public void setCOUNTERID(Integer cOUNTERID) {
        this.cOUNTERID = cOUNTERID;
    }

    public String getCOUNTERNAME() {
        return cOUNTERNAME;
    }

    public void setCOUNTERNAME(String cOUNTERNAME) {
        this.cOUNTERNAME = cOUNTERNAME;
    }

    public String getCOUNTERTYPE() {
        return cOUNTERTYPE;
    }

    public void setCOUNTERTYPE(String cOUNTERTYPE) {
        this.cOUNTERTYPE = cOUNTERTYPE;
    }

    public Integer getDEPARTMENTID() {
        return dEPARTMENTID;
    }

    public void setDEPARTMENTID(Integer dEPARTMENTID) {
        this.dEPARTMENTID = dEPARTMENTID;
    }

    public String getDEPARTMENTNAME() {
        return dEPARTMENTNAME;
    }

    public void setDEPARTMENTNAME(String dEPARTMENTNAME) {
        this.dEPARTMENTNAME = dEPARTMENTNAME;
    }

    public Integer getDEFAULTMENUID() {
        return dEFAULTMENUID;
    }

    public void setDEFAULTMENUID(Integer dEFAULTMENUID) {
        this.dEFAULTMENUID = dEFAULTMENUID;
    }

    public String getMENUNAME() {
        return mENUNAME;
    }

    public void setMENUNAME(String mENUNAME) {
        this.mENUNAME = mENUNAME;
    }
}
