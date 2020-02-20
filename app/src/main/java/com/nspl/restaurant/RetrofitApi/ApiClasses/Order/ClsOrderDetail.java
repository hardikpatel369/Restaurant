package com.nspl.restaurant.RetrofitApi.ApiClasses.Order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ClsOrderDetail {

    public ClsOrderDetail() {}

    @SerializedName("ORDER_DETAIL_ID")
    @Expose
    private Integer oRDERDETAILID;
    @SerializedName("ORDER_ID")
    @Expose
    private Integer oRDERID;
    @SerializedName("ORDER_NO")
    @Expose
    private Object oRDERNO;
    @SerializedName("SIZE_ID")
    @Expose
    private Integer sIZEID;
    @SerializedName("SIZE")
    @Expose
    private String sIZE;
    @SerializedName("ITEM_ID")
    @Expose
    private Integer iTEMID;
    @SerializedName("ITEM_NAME")
    @Expose
    private String iTEMNAME;
    @SerializedName("ADDON_ID")
    @Expose
    private Integer aDDONID;
    @SerializedName("ADDON")
    @Expose
    private String aDDON;
    @SerializedName("REFRANCE_ITEM_ID")
    @Expose
    private String rEFRANCEITEMID;
    @SerializedName("COMMENTS")
    @Expose
    private Object cOMMENTS;
    @SerializedName("PRICE")
    @Expose
    private Double pRICE;
    @SerializedName("QUANTITY")
    @Expose
    private Integer qUANTITY;
    @SerializedName("PARCEL_CHARGES")
    @Expose
    private Object pARCELCHARGES;
    @SerializedName("PARCEL_PER_QUANTITY")
    @Expose
    private Object pARCELPERQUANTITY;
    @SerializedName("PARCEL_APPLY_TAX")
    @Expose
    private Object pARCELAPPLYTAX;
    @SerializedName("TOTAL_AMOUNT")
    @Expose
    private Double tOTALAMOUNT;
    @SerializedName("IS_ADDON")
    @Expose
    private Boolean iSADDON;
    @SerializedName("EXPECTED_COOKING_TIME")
    @Expose
    private Object eXPECTEDCOOKINGTIME;
    @SerializedName("ORDER_TIME")
    @Expose
    private Object oRDERTIME;
    @SerializedName("COOKING_START_TIME")
    @Expose
    private Object cOOKINGSTARTTIME;
    @SerializedName("READY_TIME")
    @Expose
    private Object rEADYTIME;
    @SerializedName("SERVE_TIME")
    @Expose
    private Object sERVETIME;
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("ORDER_TYPE")
    @Expose
    private Object oRDERTYPE;
    @SerializedName("COMPLETE_TIME")
    @Expose
    private Object cOMPLETETIME;
    @SerializedName("ENTRY_DATE")
    @Expose
    private Object eNTRYDATE;
    @SerializedName("REMARK")
    @Expose
    private Object rEMARK;
    @SerializedName("Wastage")
    @Expose
    private Object wastage;
    @SerializedName("IS_STATUS_CHECK")
    @Expose
    private Object iSSTATUSCHECK;
    @SerializedName("PRINT_KOT")
    @Expose
    private Object pRINTKOT;

    public Integer getoRDERDETAILID() {
        return oRDERDETAILID;
    }

    public void setoRDERDETAILID(Integer oRDERDETAILID) {
        this.oRDERDETAILID = oRDERDETAILID;
    }

    public Integer getoRDERID() {
        return oRDERID;
    }

    public void setoRDERID(Integer oRDERID) {
        this.oRDERID = oRDERID;
    }

    public Object getoRDERNO() {
        return oRDERNO;
    }

    public void setoRDERNO(Object oRDERNO) {
        this.oRDERNO = oRDERNO;
    }

    public Integer getsIZEID() {
        return sIZEID;
    }

    public void setsIZEID(Integer sIZEID) {
        this.sIZEID = sIZEID;
    }

    public String getsIZE() {
        return sIZE;
    }

    public void setsIZE(String sIZE) {
        this.sIZE = sIZE;
    }

    public Integer getiTEMID() {
        return iTEMID;
    }

    public void setiTEMID(Integer iTEMID) {
        this.iTEMID = iTEMID;
    }

    public String getiTEMNAME() {
        return iTEMNAME;
    }

    public void setiTEMNAME(String iTEMNAME) {
        this.iTEMNAME = iTEMNAME;
    }

    public Integer getaDDONID() {
        return aDDONID;
    }

    public void setaDDONID(Integer aDDONID) {
        this.aDDONID = aDDONID;
    }

    public String getaDDON() {
        return aDDON;
    }

    public void setaDDON(String aDDON) {
        this.aDDON = aDDON;
    }

    public String getrEFRANCEITEMID() {
        return rEFRANCEITEMID;
    }

    public void setrEFRANCEITEMID(String rEFRANCEITEMID) {
        this.rEFRANCEITEMID = rEFRANCEITEMID;
    }

    public Object getcOMMENTS() {
        return cOMMENTS;
    }

    public void setcOMMENTS(Object cOMMENTS) {
        this.cOMMENTS = cOMMENTS;
    }

    public Double getpRICE() {
        return pRICE;
    }

    public void setpRICE(Double pRICE) {
        this.pRICE = pRICE;
    }

    public Integer getqUANTITY() {
        return qUANTITY;
    }

    public void setqUANTITY(Integer qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public Object getpARCELCHARGES() {
        return pARCELCHARGES;
    }

    public void setpARCELCHARGES(Object pARCELCHARGES) {
        this.pARCELCHARGES = pARCELCHARGES;
    }

    public Object getpARCELPERQUANTITY() {
        return pARCELPERQUANTITY;
    }

    public void setpARCELPERQUANTITY(Object pARCELPERQUANTITY) {
        this.pARCELPERQUANTITY = pARCELPERQUANTITY;
    }

    public Object getpARCELAPPLYTAX() {
        return pARCELAPPLYTAX;
    }

    public void setpARCELAPPLYTAX(Object pARCELAPPLYTAX) {
        this.pARCELAPPLYTAX = pARCELAPPLYTAX;
    }

    public Double gettOTALAMOUNT() {
        return tOTALAMOUNT;
    }

    public void settOTALAMOUNT(Double tOTALAMOUNT) {
        this.tOTALAMOUNT = tOTALAMOUNT;
    }

    public Boolean getiSADDON() {
        return iSADDON;
    }

    public void setiSADDON(Boolean iSADDON) {
        this.iSADDON = iSADDON;
    }

    public Object geteXPECTEDCOOKINGTIME() {
        return eXPECTEDCOOKINGTIME;
    }

    public void seteXPECTEDCOOKINGTIME(Object eXPECTEDCOOKINGTIME) {
        this.eXPECTEDCOOKINGTIME = eXPECTEDCOOKINGTIME;
    }

    public Object getoRDERTIME() {
        return oRDERTIME;
    }

    public void setoRDERTIME(Object oRDERTIME) {
        this.oRDERTIME = oRDERTIME;
    }

    public Object getcOOKINGSTARTTIME() {
        return cOOKINGSTARTTIME;
    }

    public void setcOOKINGSTARTTIME(Object cOOKINGSTARTTIME) {
        this.cOOKINGSTARTTIME = cOOKINGSTARTTIME;
    }

    public Object getrEADYTIME() {
        return rEADYTIME;
    }

    public void setrEADYTIME(Object rEADYTIME) {
        this.rEADYTIME = rEADYTIME;
    }

    public Object getsERVETIME() {
        return sERVETIME;
    }

    public void setsERVETIME(Object sERVETIME) {
        this.sERVETIME = sERVETIME;
    }

    public String getsTATUS() {
        return sTATUS;
    }

    public void setsTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public Object getoRDERTYPE() {
        return oRDERTYPE;
    }

    public void setoRDERTYPE(Object oRDERTYPE) {
        this.oRDERTYPE = oRDERTYPE;
    }

    public Object getcOMPLETETIME() {
        return cOMPLETETIME;
    }

    public void setcOMPLETETIME(Object cOMPLETETIME) {
        this.cOMPLETETIME = cOMPLETETIME;
    }

    public Object geteNTRYDATE() {
        return eNTRYDATE;
    }

    public void seteNTRYDATE(Object eNTRYDATE) {
        this.eNTRYDATE = eNTRYDATE;
    }

    public Object getrEMARK() {
        return rEMARK;
    }

    public void setrEMARK(Object rEMARK) {
        this.rEMARK = rEMARK;
    }

    public Object getWastage() {
        return wastage;
    }

    public void setWastage(Object wastage) {
        this.wastage = wastage;
    }

    public Object getiSSTATUSCHECK() {
        return iSSTATUSCHECK;
    }

    public void setiSSTATUSCHECK(Object iSSTATUSCHECK) {
        this.iSSTATUSCHECK = iSSTATUSCHECK;
    }

    public Object getpRINTKOT() {
        return pRINTKOT;
    }

    public void setpRINTKOT(Object pRINTKOT) {
        this.pRINTKOT = pRINTKOT;
    }
}
