package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsMenuResponse {

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;

    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;


    @SerializedName("DATA")
    @Expose
    private List<ClsDataMenu> mDataMenu;

    String FailureException = "";

    public String getFailureException() {
        return FailureException;
    }

    public void setFailureException(String failureException) {
        FailureException = failureException;
    }

    public List<ClsDataMenu> getmDataMenu() {
        return mDataMenu;
    }

    public void setmDataMenu(List<ClsDataMenu> mDataMenu) {
        this.mDataMenu = mDataMenu;
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


}
