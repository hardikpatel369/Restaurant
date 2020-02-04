
package com.nspl.restaurant.RetrofitApi.ApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsLoginResponseData {

    @SerializedName("EMPLOYEE_ID")
    @Expose
    private Integer eMPLOYEEID;
    @SerializedName("FIRST_NAME")
    @Expose
    private String fIRSTNAME;
    @SerializedName("ROLE_ID")
    @Expose
    private Integer rOLEID;
    @SerializedName("EMPLOYEE_CODE")
    @Expose
    private String eMPLOYEECODE;
    @SerializedName("FULL_NAME")
    @Expose
    private String fULLNAME;
    @SerializedName("DESIGNATION_ID")
    @Expose
    private Integer dESIGNATIONID;
    @SerializedName("DEPARTMENT_IDS")
    @Expose
    private String dEPARTMENTIDS;
    @SerializedName("DESIGNATION_NAME")
    @Expose
    private String dESIGNATIONNAME;
    @SerializedName("LOGIN_STATUS")
    @Expose
    private String lOGINSTATUS;
    @SerializedName("COUNTER_IDS")
    @Expose
    private String cOUNTERIDS;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;
    @SerializedName("BRANCH_ID")
    @Expose
    private Integer bRANCHID;
    @SerializedName("BRANCH_CODE")
    @Expose
    private String bRANCHCODE;

    public Integer getEMPLOYEEID() {
        return eMPLOYEEID;
    }

    public void setEMPLOYEEID(Integer eMPLOYEEID) {
        this.eMPLOYEEID = eMPLOYEEID;
    }

    public String getFIRSTNAME() {
        return fIRSTNAME;
    }

    public void setFIRSTNAME(String fIRSTNAME) {
        this.fIRSTNAME = fIRSTNAME;
    }

    public Integer getROLEID() {
        return rOLEID;
    }

    public void setROLEID(Integer rOLEID) {
        this.rOLEID = rOLEID;
    }

    public String getEMPLOYEECODE() {
        return eMPLOYEECODE;
    }

    public void setEMPLOYEECODE(String eMPLOYEECODE) {
        this.eMPLOYEECODE = eMPLOYEECODE;
    }

    public String getFULLNAME() {
        return fULLNAME;
    }

    public void setFULLNAME(String fULLNAME) {
        this.fULLNAME = fULLNAME;
    }

    public Integer getDESIGNATIONID() {
        return dESIGNATIONID;
    }

    public void setDESIGNATIONID(Integer dESIGNATIONID) {
        this.dESIGNATIONID = dESIGNATIONID;
    }

    public String getDEPARTMENTIDS() {
        return dEPARTMENTIDS;
    }

    public void setDEPARTMENTIDS(String dEPARTMENTIDS) {
        this.dEPARTMENTIDS = dEPARTMENTIDS;
    }

    public String getDESIGNATIONNAME() {
        return dESIGNATIONNAME;
    }

    public void setDESIGNATIONNAME(String dESIGNATIONNAME) {
        this.dESIGNATIONNAME = dESIGNATIONNAME;
    }

    public String getLOGINSTATUS() {
        return lOGINSTATUS;
    }

    public void setLOGINSTATUS(String lOGINSTATUS) {
        this.lOGINSTATUS = lOGINSTATUS;
    }

    public String getCOUNTERIDS() {
        return cOUNTERIDS;
    }

    public void setCOUNTERIDS(String cOUNTERIDS) {
        this.cOUNTERIDS = cOUNTERIDS;
    }

    public String getIMEI() {
        return iMEI;
    }

    public void setIMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    public Integer getBRANCHID() {
        return bRANCHID;
    }

    public void setBRANCHID(Integer bRANCHID) {
        this.bRANCHID = bRANCHID;
    }

    public String getBRANCHCODE() {
        return bRANCHCODE;
    }

    public void setBRANCHCODE(String bRANCHCODE) {
        this.bRANCHCODE = bRANCHCODE;
    }

}
