package com.nspl.restaurant.DataModel;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsAddon;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsComment;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsNutrition;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsSize;

import java.util.List;

public class ClsMenuItems {

    boolean isHeader = false;

    private String Categoryname;

    private Integer iTEMID;

    private String nAME;

    private Object kITCHENID;

    private String kITCHENSECTION;

    private Boolean nUTRITION;

    private Object nUTRITIONTITLE;

    private String fOODTYPE;

    private Double pARCELCHARGES;

    private List<ClsSize> sIZES = null;

    private List<ClsAddon> aDDONS = null;

    private List<ClsComment> cOMMENTS = null;

    private List<ClsNutrition> nUTRITIONS = null;

    private Object iMAGES;


    public ClsMenuItems(boolean isHeader, String categoryname, Integer iTEMID, String nAME,
                        Object kITCHENID, String kITCHENSECTION, Boolean nUTRITION, Object nUTRITIONTITLE,
                        String fOODTYPE, Double pARCELCHARGES, List<ClsSize> sIZES, List<ClsAddon> aDDONS,
                        List<ClsComment> cOMMENTS, List<ClsNutrition> nUTRITIONS, Object iMAGES) {
        this.isHeader = isHeader;
        Categoryname = categoryname;
        this.iTEMID = iTEMID;
        this.nAME = nAME;
        this.kITCHENID = kITCHENID;
        this.kITCHENSECTION = kITCHENSECTION;
        this.nUTRITION = nUTRITION;
        this.nUTRITIONTITLE = nUTRITIONTITLE;
        this.fOODTYPE = fOODTYPE;
        this.pARCELCHARGES = pARCELCHARGES;
        this.sIZES = sIZES;
        this.aDDONS = aDDONS;
        this.cOMMENTS = cOMMENTS;
        this.nUTRITIONS = nUTRITIONS;
        this.iMAGES = iMAGES;
    }

    public ClsMenuItems(boolean isHeader, String categoryname, Integer iTEMID, String nAME) {
        this.isHeader = isHeader;
        Categoryname = categoryname;
        this.iTEMID = iTEMID;
        this.nAME = nAME;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getCategoryname() {
        return Categoryname;
    }

    public void setCategoryname(String categoryname) {
        Categoryname = categoryname;
    }

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

    public Double getpARCELCHARGES() {
        return pARCELCHARGES;
    }

    public void setpARCELCHARGES(Double pARCELCHARGES) {
        this.pARCELCHARGES = pARCELCHARGES;
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

    public Object getiMAGES() {
        return iMAGES;
    }

    public void setiMAGES(Object iMAGES) {
        this.iMAGES = iMAGES;
    }
}
