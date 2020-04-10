package com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsKitchenSection {

    @SerializedName("ORDER_NO")
    @Expose
    private String oRDERNO;
    @SerializedName("ORDER_ID")
    @Expose
    private Integer oRDERID;
    @SerializedName("ORDER_DETAIL_ID")
    @Expose
    private Integer oRDERDETAILID;
    @SerializedName("TABLE_NAME_NUMBER")
    @Expose
    private String tABLENAMENUMBER;
    @SerializedName("ORDER_TIME")
    @Expose
    private String oRDERTIME;
    @SerializedName("ORDER_TIME_SECONDS")
    @Expose
    private Integer oRDERTIMESECONDS;
    @SerializedName("ITEM_ID")
    @Expose
    private Integer iTEMID;
    @SerializedName("ITEM_NAME")
    @Expose
    private String iTEMNAME;
    @SerializedName("FOOD_TYPE")
    @Expose
    private String fOODTYPE;
    @SerializedName("SIZE")
    @Expose
    private String sIZE;
    @SerializedName("QUANTITY")
    @Expose
    private Integer qUANTITY;
    @SerializedName("COMMENTS")
    @Expose
    private String cOMMENTS;
    @SerializedName("IS_ADDON")
    @Expose
    private Boolean iSADDON;
    @SerializedName("ADDON_ID")
    @Expose
    private Integer aDDONID;
    @SerializedName("ADDON")
    @Expose
    private String aDDON;
    @SerializedName("ORDER_TYPE")
    @Expose
    private Object oRDERTYPE;
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("REFRANCE_ITEM_ID")
    @Expose
    private String rEFRANCEITEMID;
    @SerializedName("SHOW_COOKING_TIME")
    @Expose
    private Boolean sHOWCOOKINGTIME;
    @SerializedName("COOKING_MINUTE")
    @Expose
    private Integer cOOKINGMINUTE;
    @SerializedName("COOKING_SECOND")
    @Expose
    private Integer cOOKINGSECOND;
    @SerializedName("PASSED_SECOND")
    @Expose
    private Integer pASSEDSECOND;
    @SerializedName("REMAIN_SECOND")
    @Expose
    private Integer rEMAINSECOND;

    private List<ClsKitchenSection> listAddons;
    private List<ClsKitchenSection> listTableToken;

    public List<ClsKitchenSection> getListTableToken() {
        return listTableToken;
    }

    public void setListTableToken(List<ClsKitchenSection> listTableToken) {
        this.listTableToken = listTableToken;
    }

    public List<ClsKitchenSection> getListAddons() {
        return listAddons;
    }

    public void setListAddons(List<ClsKitchenSection> listAddons) {
        this.listAddons = listAddons;
    }

    public String getORDERNO() {
        return oRDERNO;
    }

    public void setORDERNO(String oRDERNO) {
        this.oRDERNO = oRDERNO;
    }

    public Integer getORDERID() {
        return oRDERID;
    }

    public void setORDERID(Integer oRDERID) {
        this.oRDERID = oRDERID;
    }

    public Integer getORDERDETAILID() {
        return oRDERDETAILID;
    }

    public void setORDERDETAILID(Integer oRDERDETAILID) {
        this.oRDERDETAILID = oRDERDETAILID;
    }

    public String getTABLENAMENUMBER() {
        return tABLENAMENUMBER;
    }

    public void setTABLENAMENUMBER(String tABLENAMENUMBER) {
        this.tABLENAMENUMBER = tABLENAMENUMBER;
    }

    public String getORDERTIME() {
        return oRDERTIME;
    }

    public void setORDERTIME(String oRDERTIME) {
        this.oRDERTIME = oRDERTIME;
    }

    public Integer getORDERTIMESECONDS() {
        return oRDERTIMESECONDS;
    }

    public void setORDERTIMESECONDS(Integer oRDERTIMESECONDS) {
        this.oRDERTIMESECONDS = oRDERTIMESECONDS;
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

    public String getFOODTYPE() {
        return fOODTYPE;
    }

    public void setFOODTYPE(String fOODTYPE) {
        this.fOODTYPE = fOODTYPE;
    }

    public String getSIZE() {
        return sIZE;
    }

    public void setSIZE(String sIZE) {
        this.sIZE = sIZE;
    }

    public Integer getQUANTITY() {
        return qUANTITY;
    }

    public void setQUANTITY(Integer qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public String getCOMMENTS() {
        return cOMMENTS;
    }

    public void setCOMMENTS(String cOMMENTS) {
        this.cOMMENTS = cOMMENTS;
    }

    public Boolean getISADDON() {
        return iSADDON;
    }

    public void setISADDON(Boolean iSADDON) {
        this.iSADDON = iSADDON;
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

    public Object getORDERTYPE() {
        return oRDERTYPE;
    }

    public void setORDERTYPE(Object oRDERTYPE) {
        this.oRDERTYPE = oRDERTYPE;
    }

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getREFRANCEITEMID() {
        return rEFRANCEITEMID;
    }

    public void setREFRANCEITEMID(String rEFRANCEITEMID) {
        this.rEFRANCEITEMID = rEFRANCEITEMID;
    }

    public Boolean getSHOWCOOKINGTIME() {
        return sHOWCOOKINGTIME;
    }

    public void setSHOWCOOKINGTIME(Boolean sHOWCOOKINGTIME) {
        this.sHOWCOOKINGTIME = sHOWCOOKINGTIME;
    }

    public Integer getCOOKINGMINUTE() {
        return cOOKINGMINUTE;
    }

    public void setCOOKINGMINUTE(Integer cOOKINGMINUTE) {
        this.cOOKINGMINUTE = cOOKINGMINUTE;
    }

    public Integer getCOOKINGSECOND() {
        return cOOKINGSECOND;
    }

    public void setCOOKINGSECOND(Integer cOOKINGSECOND) {
        this.cOOKINGSECOND = cOOKINGSECOND;
    }

    public Integer getPASSEDSECOND() {
        return pASSEDSECOND;
    }

    public void setPASSEDSECOND(Integer pASSEDSECOND) {
        this.pASSEDSECOND = pASSEDSECOND;
    }

    public Integer getREMAINSECOND() {
        return rEMAINSECOND;
    }

    public void setREMAINSECOND(Integer rEMAINSECOND) {
        this.rEMAINSECOND = rEMAINSECOND;
    }
}
