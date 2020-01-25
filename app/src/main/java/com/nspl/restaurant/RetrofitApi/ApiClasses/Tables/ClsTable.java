package com.nspl.restaurant.RetrofitApi.ApiClasses.Tables;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsTable {

    @SerializedName("TABLE_ID")
    @Expose
    private Integer tABLEID;
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("TABLE_NAME_NUMBER")
    @Expose
    private String tABLENAMENUMBER;
    @SerializedName("SEATING_CAPACITY")
    @Expose
    private Object sEATINGCAPACITY;
    @SerializedName("RUNNING_ORDER_ID")
    @Expose
    private Integer rUNNINGORDERID;
    @SerializedName("RunningOrderNo")
    @Expose
    private String runningOrderNo;
    @SerializedName("TOTAL_AMOUNT")
    @Expose
    private Double tOTALAMOUNT;
    @SerializedName("TOTAL_QUANTITY")
    @Expose
    private Integer tOTALQUANTITY;

    public Integer getTABLEID() {
        return tABLEID;
    }

    public void setTABLEID(Integer tABLEID) {
        this.tABLEID = tABLEID;
    }

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getTABLENAMENUMBER() {
        return tABLENAMENUMBER;
    }

    public void setTABLENAMENUMBER(String tABLENAMENUMBER) {
        this.tABLENAMENUMBER = tABLENAMENUMBER;
    }

    public Object getSEATINGCAPACITY() {
        return sEATINGCAPACITY;
    }

    public void setSEATINGCAPACITY(Object sEATINGCAPACITY) {
        this.sEATINGCAPACITY = sEATINGCAPACITY;
    }

    public Integer getRUNNINGORDERID() {
        return rUNNINGORDERID;
    }

    public void setRUNNINGORDERID(Integer rUNNINGORDERID) {
        this.rUNNINGORDERID = rUNNINGORDERID;
    }

    public String getRunningOrderNo() {
        return runningOrderNo;
    }

    public void setRunningOrderNo(String runningOrderNo) {
        this.runningOrderNo = runningOrderNo;
    }

    public Double getTOTALAMOUNT() {
        return tOTALAMOUNT;
    }

    public void setTOTALAMOUNT(Double tOTALAMOUNT) {
        this.tOTALAMOUNT = tOTALAMOUNT;
    }

    public Integer getTOTALQUANTITY() {
        return tOTALQUANTITY;
    }

    public void setTOTALQUANTITY(Integer tOTALQUANTITY) {
        this.tOTALQUANTITY = tOTALQUANTITY;
    }

}
