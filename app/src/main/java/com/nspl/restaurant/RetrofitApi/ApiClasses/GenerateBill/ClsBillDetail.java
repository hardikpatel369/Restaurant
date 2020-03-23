package com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsBillDetail {

    @SerializedName("Objcustomer")
    @Expose
    private ClsCustomerDetail clsCustomerDetail;
    @SerializedName("Objpayment")
    @Expose
    private ClsPaymentDetail clsPaymentDetail;
    @SerializedName("ORDER_ID")
    @Expose
    private Integer oRDERID;
    @SerializedName("TableNo")
    @Expose
    private String tABLENO;
    @SerializedName("TABLE_ID")
    @Expose
    private Integer tABLEID;
    @SerializedName("NOP")
    @Expose
    private Integer nOP;
    @SerializedName("OrderRemark")
    @Expose
    private String oRDERREMARK;
    @SerializedName("updateTableStatus")
    @Expose
    private boolean uPDATETABLESTATUS;
    @SerializedName("orderDetialList")
    @Expose
    private List<Integer> oRDERDETAILLIST;
    @SerializedName("EmployeeCode")
    @Expose
    private String eMPLOYEECODE;
    @SerializedName("EmployeeName")
    @Expose
    private String eMPLOYEENAME;


    @SerializedName("CustomerDetail")
    @Expose
    private String CustomerDetail = "";
    @SerializedName("PaymentDetail")
    @Expose
    private String PaymentDetail = "";
    @SerializedName("_OrderDetailList")
    @Expose
    private String _OrderDetailList = "";

    public String getCustomerDetail() {
        return CustomerDetail;
    }

    public void setCustomerDetail(String customerDetail) {
        CustomerDetail = customerDetail;
    }

    public String getPaymentDetail() {
        return PaymentDetail;
    }

    public void setPaymentDetail(String paymentDetail) {
        PaymentDetail = paymentDetail;
    }

    public String get_OrderDetailList() {
        return _OrderDetailList;
    }

    public void set_OrderDetailList(String _OrderDetailList) {
        this._OrderDetailList = _OrderDetailList;
    }

    public ClsCustomerDetail getClsCustomerDetail() {
        return clsCustomerDetail;
    }

    public void setClsCustomerDetail(ClsCustomerDetail clsCustomerDetail) {
        this.clsCustomerDetail = clsCustomerDetail;
    }

    public ClsPaymentDetail getClsPaymentDetail() {
        return clsPaymentDetail;
    }

    public void setClsPaymentDetail(ClsPaymentDetail clsPaymentDetail) {
        this.clsPaymentDetail = clsPaymentDetail;
    }

    public Integer getoRDERID() {
        return oRDERID;
    }

    public void setoRDERID(Integer oRDERID) {
        this.oRDERID = oRDERID;
    }

    public String gettABLENO() {
        return tABLENO;
    }

    public void settABLENO(String tABLENO) {
        this.tABLENO = tABLENO;
    }

    public Integer gettABLEID() {
        return tABLEID;
    }

    public void settABLEID(Integer tABLEID) {
        this.tABLEID = tABLEID;
    }

    public Integer getnOP() {
        return nOP;
    }

    public void setnOP(Integer nOP) {
        this.nOP = nOP;
    }

    public String getoRDERREMARK() {
        return oRDERREMARK;
    }

    public void setoRDERREMARK(String oRDERREMARK) {
        this.oRDERREMARK = oRDERREMARK;
    }

    public boolean isuPDATETABLESTATUS() {
        return uPDATETABLESTATUS;
    }

    public void setuPDATETABLESTATUS(boolean uPDATETABLESTATUS) {
        this.uPDATETABLESTATUS = uPDATETABLESTATUS;
    }

    public List<Integer> getoRDERDETAILLIST() {
        return oRDERDETAILLIST;
    }

    public void setoRDERDETAILLIST(List<Integer> oRDERDETAILLIST) {
        this.oRDERDETAILLIST = oRDERDETAILLIST;
    }

    public String geteMPLOYEECODE() {
        return eMPLOYEECODE;
    }

    public void seteMPLOYEECODE(String eMPLOYEECODE) {
        this.eMPLOYEECODE = eMPLOYEECODE;
    }

    public String geteMPLOYEENAME() {
        return eMPLOYEENAME;
    }

    public void seteMPLOYEENAME(String eMPLOYEENAME) {
        this.eMPLOYEENAME = eMPLOYEENAME;
    }
}
