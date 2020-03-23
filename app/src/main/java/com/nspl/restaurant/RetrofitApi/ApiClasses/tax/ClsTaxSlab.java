package com.nspl.restaurant.RetrofitApi.ApiClasses.tax;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsTaxSlab {

    @SerializedName("SlabID")
    @Expose
    private Integer slabID;
    @SerializedName("SlabName")
    @Expose
    private String slabName;
    @SerializedName("CGST")
    @Expose
    private Double cGST;
    @SerializedName("SGST")
    @Expose
    private Double sGST;
    @SerializedName("IGST")
    @Expose
    private Double iGST;
    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("Remark")
    @Expose
    private Object remark;
    @SerializedName("TAX_TYPE")
    @Expose
    private String tAXTYPE;
    @SerializedName("BRANCH_ID")
    @Expose
    private Integer bRANCHID;

    public Integer getSlabID() {
        return slabID;
    }

    public void setSlabID(Integer slabID) {
        this.slabID = slabID;
    }

    public String getSlabName() {
        return slabName;
    }

    public void setSlabName(String slabName) {
        this.slabName = slabName;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getTAXTYPE() {
        return tAXTYPE;
    }

    public void setTAXTYPE(String tAXTYPE) {
        this.tAXTYPE = tAXTYPE;
    }

    public Integer getBRANCHID() {
        return bRANCHID;
    }

    public void setBRANCHID(Integer bRANCHID) {
        this.bRANCHID = bRANCHID;
    }

}
