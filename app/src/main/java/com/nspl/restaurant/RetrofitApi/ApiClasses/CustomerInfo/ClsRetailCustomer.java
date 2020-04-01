package com.nspl.restaurant.RetrofitApi.ApiClasses.CustomerInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsRetailCustomer {

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Company")
    @Expose
    private String Company;

    @SerializedName("GSTIN")
    @Expose
    private String GSTIN;

    @SerializedName("City")
    @Expose
    private String City;

    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;

    @SerializedName("EmployeeName")
    @Expose
    private String EmployeeName;

    @SerializedName("EmployeeCode")
    @Expose
    private String EmployeeCode;

    @SerializedName("BranchID")
    @Expose
    private String BranchID;

    @SerializedName("OrderID")
    @Expose
    private int OrderID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }
}
