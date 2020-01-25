package com.nspl.restaurant.RetrofitApi.ApiClasses.Tables;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsTablesResponse {

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("DATA")
    @Expose
    private List<ClsTable> dATA = null;

    public String getsUCCESS() {
        return sUCCESS;
    }

    public void setsUCCESS(String sUCCESS) {
        this.sUCCESS = sUCCESS;
    }

    public String getmESSAGE() {
        return mESSAGE;
    }

    public void setmESSAGE(String mESSAGE) {
        this.mESSAGE = mESSAGE;
    }

    public List<ClsTable> getdATA() {
        return dATA;
    }

    public void setdATA(List<ClsTable> dATA) {
        this.dATA = dATA;
    }
}
