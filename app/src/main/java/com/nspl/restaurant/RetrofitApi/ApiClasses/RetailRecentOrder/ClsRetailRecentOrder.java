package com.nspl.restaurant.RetrofitApi.ApiClasses.RetailRecentOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsRetailRecentOrder {

    @SerializedName("ORDER_ID")
    @Expose
    private Integer oRDERID;
    @SerializedName("ORDER_NO")
    @Expose
    private String oRDERNO;
    @SerializedName("MOBILENO")
    @Expose
    private String mOBILENO;
    @SerializedName("CUSTOMER")
    @Expose
    private String cUSTOMER;
    @SerializedName("ITEM")
    @Expose
    private Integer iTEM;
    @SerializedName("AMOUNT")
    @Expose
    private Double aMOUNT;

    public Integer getORDERID() {
        return oRDERID;
    }

    public void setORDERID(Integer oRDERID) {
        this.oRDERID = oRDERID;
    }

    public String getORDERNO() {
        return oRDERNO;
    }

    public void setORDERNO(String oRDERNO) {
        this.oRDERNO = oRDERNO;
    }

    public String getMOBILENO() {
        return mOBILENO;
    }

    public void setMOBILENO(String mOBILENO) {
        this.mOBILENO = mOBILENO;
    }

    public String getCUSTOMER() {
        return cUSTOMER;
    }

    public void setCUSTOMER(String cUSTOMER) {
        this.cUSTOMER = cUSTOMER;
    }

    public Integer getITEM() {
        return iTEM;
    }

    public void setITEM(Integer iTEM) {
        this.iTEM = iTEM;
    }

    public Double getAMOUNT() {
        return aMOUNT;
    }

    public void setAMOUNT(Double aMOUNT) {
        this.aMOUNT = aMOUNT;
    }
}
