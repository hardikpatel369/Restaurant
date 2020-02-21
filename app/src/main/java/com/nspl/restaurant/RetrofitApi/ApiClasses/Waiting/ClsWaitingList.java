
package com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsWaitingList {

    @SerializedName("WaitingID")
    @Expose
    private Integer waitingID;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerNo")
    @Expose
    private String customerNo;
    @SerializedName("Expected_Waiting_Time")
    @Expose
    private Integer expectedWaitingTime;
    @SerializedName("WaitingNo")
    @Expose
    private Integer waitingNo;
    @SerializedName("FoodType")
    @Expose
    private String foodType;
    @SerializedName("Special_Request")
    @Expose
    private String specialRequest;
    @SerializedName("Persons")
    @Expose
    private Integer persons;


    String EmployeeName = "";
    String EmployeeCode = "";
    String Mode = "";
    int BranchID = 0;

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

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public Integer getWaitingID() {
        return waitingID;
    }

    public void setWaitingID(Integer waitingID) {
        this.waitingID = waitingID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public Integer getExpectedWaitingTime() {
        return expectedWaitingTime;
    }

    public void setExpectedWaitingTime(Integer expectedWaitingTime) {
        this.expectedWaitingTime = expectedWaitingTime;
    }

    public Integer getWaitingNo() {
        return waitingNo;
    }

    public void setWaitingNo(Integer waitingNo) {
        this.waitingNo = waitingNo;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

}
