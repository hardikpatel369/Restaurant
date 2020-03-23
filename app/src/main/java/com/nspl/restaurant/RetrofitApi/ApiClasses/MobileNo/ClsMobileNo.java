package com.nspl.restaurant.RetrofitApi.ApiClasses.MobileNo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsMobileNo {

    @SerializedName("CustomerID")
    @Expose
    private Integer customerID;
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
    @SerializedName("MobileNo2")
    @Expose
    private Object mobileNo2;
    @SerializedName("BranchID")
    @Expose
    private Integer branchID;
    @SerializedName("ACTIVE")
    @Expose
    private Object aCTIVE;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

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

    public Object getMobileNo2() {
        return mobileNo2;
    }

    public void setMobileNo2(Object mobileNo2) {
        this.mobileNo2 = mobileNo2;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public Object getACTIVE() {
        return aCTIVE;
    }

    public void setACTIVE(Object aCTIVE) {
        this.aCTIVE = aCTIVE;
    }
}
