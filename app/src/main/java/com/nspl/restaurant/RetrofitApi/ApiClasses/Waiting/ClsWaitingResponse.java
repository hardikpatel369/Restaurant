
package com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsWaitingResponse extends ClsWaitingList {

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;
    @SerializedName("DATA")
    @Expose
    private List<ClsWaitingList> dATA = null;

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

    public List<ClsWaitingList> getDATA() {
        return dATA;
    }

    public void setDATA(List<ClsWaitingList> dATA) {
        this.dATA = dATA;
    }

}
