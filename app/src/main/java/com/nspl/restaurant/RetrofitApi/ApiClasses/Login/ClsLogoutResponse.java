package com.nspl.restaurant.RetrofitApi.ApiClasses.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsLogoutResponse {

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

    public String getSUCCESS() {
        return sUCCESS;
    }

    public void setSUCCESS(String sUCCESS) {
        this.sUCCESS = sUCCESS;
    }

}
