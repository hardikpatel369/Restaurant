package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import java.io.Serializable;
import java.util.List;

public class ClsCustomCategory implements Serializable {

    private String cATEGORYNAME = "",nAME = "",fOODTYPE = "";
    private Integer iTEMID = 0;
    private boolean isHeader = false;
    private List<String> itemIMAGE = null;
    private List<ClsSize> sIZES = null;
    private List<ClsAddon> aDDONS = null;
    private List<ClsComment> cOMMENTS = null;
    private List<ClsNutrition> nUTRITIONS = null;
    private Boolean pARCELPERQUANTITY;
    private Double pARCELCHARGE;
    private Object nUTRITIONTITLE;


    public ClsCustomCategory(){
    }

    public ClsCustomCategory(String cATEGORYNAME, String nAME, String fOODTYPE,
                             Integer iTEMID, List<String> itemIMAGE,List<ClsSize> sIZES,
                             List<ClsAddon> aDDONS,List<ClsComment> cOMMENTS,List<ClsNutrition> nUTRITIONS,
                             Boolean pARCELPERQUANTITY,Double pARCELCHARGE,Object nUTRITIONTITLE) {
        this.cATEGORYNAME = cATEGORYNAME;
        this.nAME = nAME;
        this.fOODTYPE = fOODTYPE;
        this.iTEMID = iTEMID;
        this.itemIMAGE = itemIMAGE;
        this.sIZES = sIZES;
        this.aDDONS = aDDONS;
        this.cOMMENTS = cOMMENTS;
        this.nUTRITIONS = nUTRITIONS;
        this.pARCELPERQUANTITY = pARCELPERQUANTITY;
        this.pARCELCHARGE = pARCELCHARGE;
        this.nUTRITIONTITLE = nUTRITIONTITLE;
    }

    public ClsCustomCategory(String cATEGORYNAME, boolean isHeader) {
        this.cATEGORYNAME = cATEGORYNAME;
        this.isHeader = isHeader;
    }

    public String getcATEGORYNAME() {
        return cATEGORYNAME;
    }

    public void setcATEGORYNAME(String cATEGORYNAME) {
        this.cATEGORYNAME = cATEGORYNAME;
    }

    public String getnAME() {
        return nAME;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    public String getfOODTYPE() {
        return fOODTYPE;
    }

    public void setfOODTYPE(String fOODTYPE) {
        this.fOODTYPE = fOODTYPE;
    }

    public Integer getiTEMID() {
        return iTEMID;
    }

    public void setiTEMID(Integer iTEMID) {
        this.iTEMID = iTEMID;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public List<String> getItemIMAGE() {
        return itemIMAGE;
    }

    public void setItemIMAGE(List<String> itemIMAGE) {
        this.itemIMAGE = itemIMAGE;
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

    public Boolean getpARCELPERQUANTITY() {
        return pARCELPERQUANTITY;
    }

    public void setpARCELPERQUANTITY(Boolean pARCELPERQUANTITY) {
        this.pARCELPERQUANTITY = pARCELPERQUANTITY;
    }

    public Double getpARCELCHARGE() {
        return pARCELCHARGE;
    }

    public void setpARCELCHARGE(Double pARCELCHARGE) {
        this.pARCELCHARGE = pARCELCHARGE;
    }

    public Object getnUTRITIONTITLE() {
        return nUTRITIONTITLE;
    }

    public void setnUTRITIONTITLE(Object nUTRITIONTITLE) {
        this.nUTRITIONTITLE = nUTRITIONTITLE;
    }
}
