package com.nspl.restaurant.RetrofitApi.ApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsLoginResponse extends ClsLoginResponseData {

    String FailureException = "";

    public String getFailureException() {
        return FailureException;
    }

    public void setFailureException(String failureException) {
        FailureException = failureException;
    }

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;

    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;

    @SerializedName("DATA")
    @Expose
    private ClsLoginResponseData dATA;

    public String getSUCCESS() {
        return sUCCESS;
    }

    public void setSUCCESS(String sUCCESS) {
        this.sUCCESS = sUCCESS;
    }

    public String getMESSAGE() {
        return mESSAGE;
    }

    public void setMESSAGE(String mESSAGE) {
        this.mESSAGE = mESSAGE;
    }

    public ClsLoginResponseData getDATA() {
        return dATA;
    }

    public void setDATA(ClsLoginResponseData dATA) {
        this.dATA = dATA;
    }

}


