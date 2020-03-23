package com.nspl.restaurant.RetrofitApi.ApiClasses.MobileNo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsMobileNoResponse {

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("DATA")
    @Expose
    private ClsMobileNo dATA;

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

    public ClsMobileNo getDATA() {
        return dATA;
    }

    public void setDATA(ClsMobileNo dATA) {
        this.dATA = dATA;
    }
}
