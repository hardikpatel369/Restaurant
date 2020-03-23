package com.nspl.restaurant.RetrofitApi.ApiClasses.tax;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsTaxSlabResponse {

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("DATA")
    @Expose
    private ClsTaxSlab dATA;

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

    public ClsTaxSlab getDATA() {
        return dATA;
    }

    public void setDATA(ClsTaxSlab dATA) {
        this.dATA = dATA;
    }
}
