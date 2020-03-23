package com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsCustomerDetail {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Company")
    @Expose
    private String company;
    @SerializedName("GSTIN")
    @Expose
    private String gSTIN;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGSTIN() {
        return gSTIN;
    }

    public void setGSTIN(String gSTIN) {
        this.gSTIN = gSTIN;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
