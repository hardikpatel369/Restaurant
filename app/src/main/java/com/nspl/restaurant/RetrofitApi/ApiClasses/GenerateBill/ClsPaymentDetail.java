package com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsPaymentDetail {

    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("TableNo")
    @Expose
    private String tableNo;
    @SerializedName("TokenNo")
    @Expose
    private String tokenNo;
    @SerializedName("DepartmentID")
    @Expose
    private Integer departmentID;
    @SerializedName("CounterID")
    @Expose
    private Integer counterID;
    @SerializedName("CounterType")
    @Expose
    private Object counterType;
    @SerializedName("NetAmount")
    @Expose
    private Double netAmount;
    @SerializedName("DISC_PER")
    @Expose
    private Integer dISCPER;
    @SerializedName("Discount")
    @Expose
    private Double discount;
    @SerializedName("TaxableAmount")
    @Expose
    private Double taxableAmount;
    @SerializedName("APPLY_TAX")
    @Expose
    private Boolean aPPLYTAX;
    @SerializedName("TAX_TYPE")
    @Expose
    private String tAXTYPE;
    @SerializedName("CGST_PER")
    @Expose
    private Double cGSTPER;
    @SerializedName("SGST_PER")
    @Expose
    private Double sGSTPER;
    @SerializedName("IGST_PER")
    @Expose
    private Double iGSTPER;
    @SerializedName("CGST")
    @Expose
    private Double cGST;
    @SerializedName("SGST")
    @Expose
    private Double sGST;
    @SerializedName("IGST")
    @Expose
    private Double iGST;
    @SerializedName("TaxAmout")
    @Expose
    private Double taxAmout;
    @SerializedName("PaybleAmount")
    @Expose
    private Double paybleAmount;
    @SerializedName("RountOff")
    @Expose
    private Double rountOff;
    @SerializedName("ReceiveableAmount")
    @Expose
    private Double receiveableAmount;
    @SerializedName("PaymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("PaymentDetail")
    @Expose
    private Object paymentDetail;
    @SerializedName("OrderId")
    @Expose
    private Integer orderId;
    @SerializedName("BranchID")
    @Expose
    private Integer branchID;
    @SerializedName("BRANCH_CODE")
    @Expose
    private String bRANCHCODE;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public Integer getCounterID() {
        return counterID;
    }

    public void setCounterID(Integer counterID) {
        this.counterID = counterID;
    }

    public Object getCounterType() {
        return counterType;
    }

    public void setCounterType(Object counterType) {
        this.counterType = counterType;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Integer getDISCPER() {
        return dISCPER;
    }

    public void setDISCPER(Integer dISCPER) {
        this.dISCPER = dISCPER;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public Boolean getAPPLYTAX() {
        return aPPLYTAX;
    }

    public void setAPPLYTAX(Boolean aPPLYTAX) {
        this.aPPLYTAX = aPPLYTAX;
    }

    public String getTAXTYPE() {
        return tAXTYPE;
    }

    public void setTAXTYPE(String tAXTYPE) {
        this.tAXTYPE = tAXTYPE;
    }

    public Double getCGSTPER() {
        return cGSTPER;
    }

    public void setCGSTPER(Double cGSTPER) {
        this.cGSTPER = cGSTPER;
    }

    public Double getSGSTPER() {
        return sGSTPER;
    }

    public void setSGSTPER(Double sGSTPER) {
        this.sGSTPER = sGSTPER;
    }

    public Double getIGSTPER() {
        return iGSTPER;
    }

    public void setIGSTPER(Double iGSTPER) {
        this.iGSTPER = iGSTPER;
    }

    public Double getCGST() {
        return cGST;
    }

    public void setCGST(Double cGST) {
        this.cGST = cGST;
    }

    public Double getSGST() {
        return sGST;
    }

    public void setSGST(Double sGST) {
        this.sGST = sGST;
    }

    public Double getIGST() {
        return iGST;
    }

    public void setIGST(Double iGST) {
        this.iGST = iGST;
    }

    public Double getTaxAmout() {
        return taxAmout;
    }

    public void setTaxAmout(Double taxAmout) {
        this.taxAmout = taxAmout;
    }

    public Double getPaybleAmount() {
        return paybleAmount;
    }

    public void setPaybleAmount(Double paybleAmount) {
        this.paybleAmount = paybleAmount;
    }

    public Double getRountOff() {
        return rountOff;
    }

    public void setRountOff(Double rountOff) {
        this.rountOff = rountOff;
    }

    public Double getReceiveableAmount() {
        return receiveableAmount;
    }

    public void setReceiveableAmount(Double receiveableAmount) {
        this.receiveableAmount = receiveableAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Object getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(Object paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public String getBRANCHCODE() {
        return bRANCHCODE;
    }

    public void setBRANCHCODE(String bRANCHCODE) {
        this.bRANCHCODE = bRANCHCODE;
    }

}
