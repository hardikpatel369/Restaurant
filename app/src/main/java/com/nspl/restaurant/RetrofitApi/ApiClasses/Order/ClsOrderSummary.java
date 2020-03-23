package com.nspl.restaurant.RetrofitApi.ApiClasses.Order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClsOrderSummary implements Serializable {

    @SerializedName("ORDER_DETAIL_ID")
    @Expose
    private Integer oRDERDETAILID;
    @SerializedName("ORDER_ID")
    @Expose
    private Integer oRDERID;
    @SerializedName("ORDER_NO")
    @Expose
    private String oRDERNO;
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
    private String cOMMENTS;
    @SerializedName("PRICE")
    @Expose
    private Double pRICE;
    @SerializedName("QUANTITY")
    @Expose
    private Integer qUANTITY;
    @SerializedName("PARCEL_CHARGES")
    @Expose
    private Double pARCELCHARGES;
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
    private String oRDERTIME;
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
    private String oRDERTYPE;
    @SerializedName("COMPLETE_TIME")
    @Expose
    private String cOMPLETETIME;
    @SerializedName("ENTRY_DATE")
    @Expose
    private String eNTRYDATE;
    @SerializedName("REMARK")
    @Expose
    private String rEMARK;
    @SerializedName("Wastage")
    @Expose
    private Object wastage;
    @SerializedName("IS_STATUS_CHECK")
    @Expose
    private Boolean iSSTATUSCHECK;
    @SerializedName("PRINT_KOT")
    @Expose
    private Object pRINTKOT;
    @SerializedName("ORDER_CANCEL_OPTION")
    @Expose
    private Boolean oRDERCANCELOPTION;

    private List<ClsOrderSummary> listAddons;

    public List<ClsOrderSummary> getListAddons() {
        return listAddons;
    }

    public void setListAddons(List<ClsOrderSummary> listAddons) {
        this.listAddons = listAddons;
    }

    public Integer getORDERDETAILID() {
        return oRDERDETAILID;
    }

    public void setORDERDETAILID(Integer oRDERDETAILID) {
        this.oRDERDETAILID = oRDERDETAILID;
    }

    public Integer getORDERID() {
        return oRDERID;
    }

    public void setORDERID(Integer oRDERID) {
        this.oRDERID = oRDERID;
    }

    public String getORDERNO() {
        return oRDERNO;
    }

    public void setORDERNO(String oRDERNO) {
        this.oRDERNO = oRDERNO;
    }

    public Integer getSIZEID() {
        return sIZEID;
    }

    public void setSIZEID(Integer sIZEID) {
        this.sIZEID = sIZEID;
    }

    public String getSIZE() {
        return sIZE;
    }

    public void setSIZE(String sIZE) {
        this.sIZE = sIZE;
    }

    public Integer getITEMID() {
        return iTEMID;
    }

    public void setITEMID(Integer iTEMID) {
        this.iTEMID = iTEMID;
    }

    public String getITEMNAME() {
        return iTEMNAME;
    }

    public void setITEMNAME(String iTEMNAME) {
        this.iTEMNAME = iTEMNAME;
    }

    public Integer getADDONID() {
        return aDDONID;
    }

    public void setADDONID(Integer aDDONID) {
        this.aDDONID = aDDONID;
    }

    public String getADDON() {
        return aDDON;
    }

    public void setADDON(String aDDON) {
        this.aDDON = aDDON;
    }

    public String getREFRANCEITEMID() {
        return rEFRANCEITEMID;
    }

    public void setREFRANCEITEMID(String rEFRANCEITEMID) {
        this.rEFRANCEITEMID = rEFRANCEITEMID;
    }

    public String getCOMMENTS() {
        return cOMMENTS;
    }

    public void setCOMMENTS(String cOMMENTS) {
        this.cOMMENTS = cOMMENTS;
    }

    public Double getPRICE() {
        return pRICE;
    }

    public void setPRICE(Double pRICE) {
        this.pRICE = pRICE;
    }

    public Integer getQUANTITY() {
        return qUANTITY;
    }

    public void setQUANTITY(Integer qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public Double getPARCELCHARGES() {
        return pARCELCHARGES;
    }

    public void setPARCELCHARGES(Double pARCELCHARGES) {
        this.pARCELCHARGES = pARCELCHARGES;
    }

    public Object getPARCELPERQUANTITY() {
        return pARCELPERQUANTITY;
    }

    public void setPARCELPERQUANTITY(Object pARCELPERQUANTITY) {
        this.pARCELPERQUANTITY = pARCELPERQUANTITY;
    }

    public Object getPARCELAPPLYTAX() {
        return pARCELAPPLYTAX;
    }

    public void setPARCELAPPLYTAX(Object pARCELAPPLYTAX) {
        this.pARCELAPPLYTAX = pARCELAPPLYTAX;
    }

    public Double getTOTALAMOUNT() {
        return tOTALAMOUNT;
    }

    public void setTOTALAMOUNT(Double tOTALAMOUNT) {
        this.tOTALAMOUNT = tOTALAMOUNT;
    }

    public Boolean getISADDON() {
        return iSADDON;
    }

    public void setISADDON(Boolean iSADDON) {
        this.iSADDON = iSADDON;
    }

    public Object getEXPECTEDCOOKINGTIME() {
        return eXPECTEDCOOKINGTIME;
    }

    public void setEXPECTEDCOOKINGTIME(Object eXPECTEDCOOKINGTIME) {
        this.eXPECTEDCOOKINGTIME = eXPECTEDCOOKINGTIME;
    }

    public String getORDERTIME() {
        return oRDERTIME;
    }

    public void setORDERTIME(String oRDERTIME) {
        this.oRDERTIME = oRDERTIME;
    }

    public Object getCOOKINGSTARTTIME() {
        return cOOKINGSTARTTIME;
    }

    public void setCOOKINGSTARTTIME(Object cOOKINGSTARTTIME) {
        this.cOOKINGSTARTTIME = cOOKINGSTARTTIME;
    }

    public Object getREADYTIME() {
        return rEADYTIME;
    }

    public void setREADYTIME(Object rEADYTIME) {
        this.rEADYTIME = rEADYTIME;
    }

    public Object getSERVETIME() {
        return sERVETIME;
    }

    public void setSERVETIME(Object sERVETIME) {
        this.sERVETIME = sERVETIME;
    }

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getORDERTYPE() {
        return oRDERTYPE;
    }

    public void setORDERTYPE(String oRDERTYPE) {
        this.oRDERTYPE = oRDERTYPE;
    }

    public String getCOMPLETETIME() {
        return cOMPLETETIME;
    }

    public void setCOMPLETETIME(String cOMPLETETIME) {
        this.cOMPLETETIME = cOMPLETETIME;
    }

    public String getENTRYDATE() {
        return eNTRYDATE;
    }

    public void setENTRYDATE(String eNTRYDATE) {
        this.eNTRYDATE = eNTRYDATE;
    }

    public String getREMARK() {
        return rEMARK;
    }

    public void setREMARK(String rEMARK) {
        this.rEMARK = rEMARK;
    }

    public Object getWastage() {
        return wastage;
    }

    public void setWastage(Object wastage) {
        this.wastage = wastage;
    }

    public Boolean getISSTATUSCHECK() {
        return iSSTATUSCHECK;
    }

    public void setISSTATUSCHECK(Boolean iSSTATUSCHECK) {
        this.iSSTATUSCHECK = iSSTATUSCHECK;
    }

    public Object getPRINTKOT() {
        return pRINTKOT;
    }

    public void setPRINTKOT(Object pRINTKOT) {
        this.pRINTKOT = pRINTKOT;
    }

    public Boolean getoRDERCANCELOPTION() {
        return oRDERCANCELOPTION;
    }

    public void setoRDERCANCELOPTION(Boolean oRDERCANCELOPTION) {
        this.oRDERCANCELOPTION = oRDERCANCELOPTION;
    }
}
