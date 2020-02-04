package com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting;

public class ClsWaitingPerson {//Todo
     String CustomerName ;
    String CustomerNo ;
    // Nullable<int> Expected_Waiting_Time { get; set; }
    String FoodType ;
    String Special_Request ;
     int Persons ;
    String EmployeeName ;
    String EmployeeCode ;
     int BranchID;
    String Mode ;

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

    public String getFoodType() {
        return FoodType;
    }

    public void setFoodType(String foodType) {
        FoodType = foodType;
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

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }
}
