package com.ydc.beans.commom.tembin;

import java.io.Serializable;

public class ResponseHead implements Serializable{

    
    /** @Fields serialVersionUID: */
      	
    private static final long serialVersionUID = -5370886402334369466L;
    
    private String orgCode;
    
    private String orgName;
    
    private String transNo;
    
    private String transDate;
    
    private String functionCode;
    
    private String tcTransNo;
    
    private String rtCode;
    
    private String rtMsg;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getTcTransNo() {
        return tcTransNo;
    }

    public void setTcTransNo(String tcTransNo) {
        this.tcTransNo = tcTransNo;
    }

    public String getRtCode() {
        return rtCode;
    }

    public void setRtCode(String rtCode) {
        this.rtCode = rtCode;
    }

    public String getRtMsg() {
        return rtMsg;
    }

    public void setRtMsg(String rtMsg) {
        this.rtMsg = rtMsg;
    }

    @Override
    public String toString() {
        return "ResponseHead [orgCode=" + orgCode + ", orgName=" + orgName + ", transNo=" + transNo
                + ", transDate=" + transDate + ", functionCode=" + functionCode + ", tcTransNo="
                + tcTransNo + ", rtCode=" + rtCode + ", rtMsg=" + rtMsg + "]";
    }
    
    

}
