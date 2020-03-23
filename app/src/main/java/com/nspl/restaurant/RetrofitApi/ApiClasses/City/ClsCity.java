package com.nspl.restaurant.RetrofitApi.ApiClasses.City;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsCity {

    @SerializedName("CITY_ID")
    @Expose
    private Integer cITYID;
    @SerializedName("STATE_ID")
    @Expose
    private Integer sTATEID;
    @SerializedName("CITY")
    @Expose
    private String cITY;
    @SerializedName("REMARK")
    @Expose
    private String rEMARK;
    @SerializedName("ENTRYDATE")
    @Expose
    private String eNTRYDATE;
    @SerializedName("ISDELETE")
    @Expose
    private Object iSDELETE;

    public Integer getCITYID() {
        return cITYID;
    }

    public void setCITYID(Integer cITYID) {
        this.cITYID = cITYID;
    }

    public Integer getSTATEID() {
        return sTATEID;
    }

    public void setSTATEID(Integer sTATEID) {
        this.sTATEID = sTATEID;
    }

    public String getCITY() {
        return cITY;
    }

    public void setCITY(String cITY) {
        this.cITY = cITY;
    }

    public String getREMARK() {
        return rEMARK;
    }

    public void setREMARK(String rEMARK) {
        this.rEMARK = rEMARK;
    }

    public String getENTRYDATE() {
        return eNTRYDATE;
    }

    public void setENTRYDATE(String eNTRYDATE) {
        this.eNTRYDATE = eNTRYDATE;
    }

    public Object getISDELETE() {
        return iSDELETE;
    }

    public void setISDELETE(Object iSDELETE) {
        this.iSDELETE = iSDELETE;
    }

}
