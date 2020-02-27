package com.nspl.restaurant.RetrofitApi.ApiClasses.Order;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsOrderSummaryResponse {

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("DATA")
    @Expose
    private List<ClsOrderSummary> dATA = null;

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

    public List<ClsOrderSummary> getDATA() {
        return dATA;
    }

    public void setDATA(List<ClsOrderSummary> dATA) {
        this.dATA = dATA;
    }

}
