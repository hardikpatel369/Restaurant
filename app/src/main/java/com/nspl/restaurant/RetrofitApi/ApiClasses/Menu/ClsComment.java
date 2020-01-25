package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsComment {

    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("VALUE")
    @Expose
    private String vALUE;

    public String getnAME() {
        return nAME;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    public String getvALUE() {
        return vALUE;
    }

    public void setvALUE(String vALUE) {
        this.vALUE = vALUE;
    }
}
