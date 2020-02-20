package com.nspl.restaurant.RetrofitApi.ApiClasses.Order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsOrder  {

    @SerializedName("ORDER_ID")
    @Expose
    private Integer oRDERID;
    @SerializedName("ORDER_NO")
    @Expose
    private String oRDERNO;
    @SerializedName("ORDER_TYPE")
    @Expose
    private String oRDERTYPE;
    @SerializedName("TABLE_ID")
    @Expose
    private Integer tABLEID;
    @SerializedName("COUNTER_ID")
    @Expose
    private Integer cOUNTERID;
    @SerializedName("EmployeeName")
    @Expose
    private String fULLNAME;
    @SerializedName("EmployeeCode")
    @Expose
    private String eMPLOYEECODE;
    @SerializedName("DEPARTMENT_ID")
    @Expose
    private Object dEPARTMENTID;
    @SerializedName("BRANCH_ID")
    @Expose
    private Integer bRANCHID;
    @SerializedName("ListItem")//ListItem
    @Expose
    private String itemList = "";
    @SerializedName("itemList")//ListItem
    @Expose
    private List<ClsOrderDetail> OrderitemList = null;

    public Integer getoRDERID() {
        return oRDERID;
    }

    public void setoRDERID(Integer oRDERID) {
        this.oRDERID = oRDERID;
    }

    public String getoRDERNO() {
        return oRDERNO;
    }

    public void setoRDERNO(String oRDERNO) {
        this.oRDERNO = oRDERNO;
    }

    public String getoRDERTYPE() {
        return oRDERTYPE;
    }

    public void setoRDERTYPE(String oRDERTYPE) {
        this.oRDERTYPE = oRDERTYPE;
    }

    public Integer gettABLEID() {
        return tABLEID;
    }

    public void settABLEID(Integer tABLEID) {
        this.tABLEID = tABLEID;
    }

    public Integer getcOUNTERID() {
        return cOUNTERID;
    }

    public void setcOUNTERID(Integer cOUNTERID) {
        this.cOUNTERID = cOUNTERID;
    }

    public String getfULLNAME() {
        return fULLNAME;
    }

    public void setfULLNAME(String fULLNAME) {
        this.fULLNAME = fULLNAME;
    }

    public String geteMPLOYEECODE() {
        return eMPLOYEECODE;
    }

    public void seteMPLOYEECODE(String eMPLOYEECODE) {
        this.eMPLOYEECODE = eMPLOYEECODE;
    }

    public Object getdEPARTMENTID() {
        return dEPARTMENTID;
    }

    public void setdEPARTMENTID(Object dEPARTMENTID) {
        this.dEPARTMENTID = dEPARTMENTID;
    }

    public Integer getbRANCHID() {
        return bRANCHID;
    }

    public void setbRANCHID(Integer bRANCHID) {
        this.bRANCHID = bRANCHID;
    }

    public String getItemList() {
        return itemList;
    }

    public void setItemList(String itemList) {
        this.itemList = itemList;
    }

    public List<ClsOrderDetail> getOrderitemList() {
        return OrderitemList;
    }

    public void setOrderitemList(List<ClsOrderDetail> orderitemList) {
        OrderitemList = orderitemList;
    }
}
