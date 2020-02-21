package com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ClsWaitingResponseCls {


    int WaitingID = 0;
    String CustomerName = "";
    String CustomerNo = "";
    int Expected_Waiting_Time = 0;
    String FoodType = "";
    int BranchID = 0;
    String Special_Request = "";
    int Persons = 0;
    String EmployeeName = "";
    String EmployeeCode = "";
    String Mode = "";

    public int getWaitingID() {
        return WaitingID;
    }

    public void setWaitingID(int waitingID) {
        WaitingID = waitingID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerNo() {
        return CustomerNo;
    }

    public void setCustomerNo(String customerNo) {
        CustomerNo = customerNo;
    }

    public int getExpected_Waiting_Time() {
        return Expected_Waiting_Time;
    }

    public void setExpected_Waiting_Time(int expected_Waiting_Time) {
        Expected_Waiting_Time = expected_Waiting_Time;
    }

    public String getFoodType() {
        return FoodType;
    }

    public void setFoodType(String foodType) {
        FoodType = foodType;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public String getSpecial_Request() {
        return Special_Request;
    }

    public void setSpecial_Request(String special_Request) {
        Special_Request = special_Request;
    }

    public int getPersons() {
        return Persons;
    }

    public void setPersons(int persons) {
        Persons = persons;
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

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    @SerializedName("SUCCESS")
    @Expose
    private String sUCCESS;
    @SerializedName("MESSAGE")
    @Expose
    private String mESSAGE;

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


}
