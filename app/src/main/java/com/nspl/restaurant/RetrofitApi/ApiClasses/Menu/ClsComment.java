package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsComment {

    @SerializedName("COMMENT_ID")
    @Expose
    private Integer cOMMENTID;
    @SerializedName("SORT_NAME")
    @Expose
    private String sORTNAME;
    @SerializedName("REMARK")
    @Expose
    private String rEMARK;

    public Integer getCOMMENTID() {
        return cOMMENTID;
    }

    public void setCOMMENTID(Integer cOMMENTID) {
        this.cOMMENTID = cOMMENTID;
    }

    public String getSORTNAME() {
        return sORTNAME;
    }

    public void setSORTNAME(String sORTNAME) {
        this.sORTNAME = sORTNAME;
    }

    public String getREMARK() {
        return rEMARK;
    }

    public void setREMARK(String rEMARK) {
        this.rEMARK = rEMARK;
    }

}
