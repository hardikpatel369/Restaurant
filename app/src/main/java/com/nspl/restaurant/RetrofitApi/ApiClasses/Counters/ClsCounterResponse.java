package com.nspl.restaurant.RetrofitApi.ApiClasses.Counters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsCounterResponse {


    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("DATA")
    @Expose
    private List<ClsCounterData> dATA = null;

    String FailureException = "";

    public String getFailureException() {
        return FailureException;
    }

    public void setFailureException(String failureException) {
        FailureException = failureException;
    }


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

    public List<ClsCounterData> getdATA() {
        return dATA;
    }

    public void setdATA(List<ClsCounterData> dATA) {
        this.dATA = dATA;
    }
}
