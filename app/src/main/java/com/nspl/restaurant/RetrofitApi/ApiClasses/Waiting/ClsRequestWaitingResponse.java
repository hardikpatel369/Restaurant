
package com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsRequestWaitingResponse {

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("DATA")
    @Expose
    private List<String> dATA = null;

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

    public List<String> getDATA() {
        return dATA;
    }

    public void setDATA(List<String> dATA) {
        this.dATA = dATA;
    }

}
