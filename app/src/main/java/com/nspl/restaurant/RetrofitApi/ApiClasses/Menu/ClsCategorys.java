package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClsCategorys implements Serializable {

    @SerializedName("CATEGORY_ID")
    @Expose
    private Integer cATEGORYID;
    @SerializedName("CATEGORY_NAME")
    @Expose
    private String cATEGORYNAME;
    @SerializedName("ITEMS")
    @Expose
    private List<ClsItem> iTEMS = null;

    private boolean isHeader = true;

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public Integer getcATEGORYID() {
        return cATEGORYID;
    }

    public void setcATEGORYID(Integer cATEGORYID) {
        this.cATEGORYID = cATEGORYID;
    }

    public String getcATEGORYNAME() {
        return cATEGORYNAME;
    }

    public void setcATEGORYNAME(String cATEGORYNAME) {
        this.cATEGORYNAME = cATEGORYNAME;
    }

    public List<ClsItem> getiTEMS() {
        return iTEMS;
    }

    public void setiTEMS(List<ClsItem> iTEMS) {
        this.iTEMS = iTEMS;
    }
}
