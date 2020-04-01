package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClsItem implements Serializable {

    @SerializedName("ITEM_ID")
    @Expose
    private Integer iTEMID;
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("KITCHEN_ID")
    @Expose
    private Object kITCHENID;
    @SerializedName("KITCHEN_SECTION")
    @Expose
    private String kITCHENSECTION;
    @SerializedName("NUTRITION")
    @Expose
    private Boolean nUTRITION;
    @SerializedName("NUTRITION_TITLE")
    @Expose
    private Object nUTRITIONTITLE;
    @SerializedName("FOOD_TYPE")
    @Expose
    private String fOODTYPE;
    @SerializedName("PARCEL_CHARGES")
    @Expose
    private Boolean pARCELCHARGES;
    @SerializedName("PARCEL_CHARGE")
    @Expose
    private Double pARCELCHARGE;
    @SerializedName("PARCEL_PER_QUANTITY")
    @Expose
    private Boolean pARCELPERQUANTITY;
    @SerializedName("PRINT_KOT")
    @Expose
    private Boolean pRINTKOT;
    @SerializedName("SIZES")
    @Expose
    private List<ClsSize> sIZES = null;
    @SerializedName("ADDONS")
    @Expose
    private List<ClsAddon> aDDONS = null;
    @SerializedName("COMMENTSID")
    @Expose
    private List<ClsComment> cOMMENTS = null;
    @SerializedName("NUTRITIONS")
    @Expose
    private List<ClsNutrition> nUTRITIONS = null;
    @SerializedName("IMAGES")
    @Expose
    private String iMAGES;
    @SerializedName("ITEM_IMAGES")
    @Expose
    private List<String> itemIMAGE = null;
    @SerializedName("INGREDIENTS")
    @Expose
    private List<String> iNGREDIENTS  = null;
    @SerializedName("ORDER_CANCEL_OPTION")
    @Expose
    private Boolean oRDERCANCELOPTION;

    public Integer getiTEMID() {
        return iTEMID;
    }

    public void setiTEMID(Integer iTEMID) {
        this.iTEMID = iTEMID;
    }

    public String getnAME() {
        return nAME;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    public Object getkITCHENID() {
        return kITCHENID;
    }

    public void setkITCHENID(Object kITCHENID) {
        this.kITCHENID = kITCHENID;
    }

    public String getkITCHENSECTION() {
        return kITCHENSECTION;
    }

    public void setkITCHENSECTION(String kITCHENSECTION) {
        this.kITCHENSECTION = kITCHENSECTION;
    }

    public Boolean getnUTRITION() {
        return nUTRITION;
    }

    public void setnUTRITION(Boolean nUTRITION) {
        this.nUTRITION = nUTRITION;
    }

    public Object getnUTRITIONTITLE() {
        return nUTRITIONTITLE;
    }

    public void setnUTRITIONTITLE(Object nUTRITIONTITLE) {
        this.nUTRITIONTITLE = nUTRITIONTITLE;
    }

    public String getfOODTYPE() {
        return fOODTYPE;
    }

    public void setfOODTYPE(String fOODTYPE) {
        this.fOODTYPE = fOODTYPE;
    }

    public Boolean getpARCELCHARGES() {
        return pARCELCHARGES;
    }

    public void setpARCELCHARGES(Boolean pARCELCHARGES) {
        this.pARCELCHARGES = pARCELCHARGES;
    }

    public Double getpARCELCHARGE() {
        return pARCELCHARGE;
    }

    public void setpARCELCHARGE(Double pARCELCHARGE) {
        this.pARCELCHARGE = pARCELCHARGE;
    }

    public Boolean getpARCELPERQUANTITY() {
        return pARCELPERQUANTITY;
    }

    public void setpARCELPERQUANTITY(Boolean pARCELPERQUANTITY) {
        this.pARCELPERQUANTITY = pARCELPERQUANTITY;
    }

    public Boolean getpRINTKOT() {
        return pRINTKOT;
    }

    public void setpRINTKOT(Boolean pRINTKOT) {
        this.pRINTKOT = pRINTKOT;
    }

    public List<ClsSize> getsIZES() {
        return sIZES;
    }

    public void setsIZES(List<ClsSize> sIZES) {
        this.sIZES = sIZES;
    }

    public List<ClsAddon> getaDDONS() {
        return aDDONS;
    }

    public void setaDDONS(List<ClsAddon> aDDONS) {
        this.aDDONS = aDDONS;
    }

    public List<ClsComment> getcOMMENTS() {
        return cOMMENTS;
    }

    public void setcOMMENTS(List<ClsComment> cOMMENTS) {
        this.cOMMENTS = cOMMENTS;
    }

    public List<ClsNutrition> getnUTRITIONS() {
        return nUTRITIONS;
    }

    public void setnUTRITIONS(List<ClsNutrition> nUTRITIONS) {
        this.nUTRITIONS = nUTRITIONS;
    }

    public String getiMAGES() {
        return iMAGES;
    }

    public void setiMAGES(String iMAGES) {
        this.iMAGES = iMAGES;
    }

    public List<String> getItemIMAGE() {
        return itemIMAGE;
    }

    public void setItemIMAGE(List<String> itemIMAGE) {
        this.itemIMAGE = itemIMAGE;
    }

    public List<String> getiNGREDIENTS() {
        return iNGREDIENTS;
    }

    public void setiNGREDIENTS(List<String> iNGREDIENTS) {
        this.iNGREDIENTS = iNGREDIENTS;
    }

    public Boolean getoRDERCANCELOPTION() {
        return oRDERCANCELOPTION;
    }

    public void setoRDERCANCELOPTION(Boolean oRDERCANCELOPTION) {
        this.oRDERCANCELOPTION = oRDERCANCELOPTION;
    }
}
