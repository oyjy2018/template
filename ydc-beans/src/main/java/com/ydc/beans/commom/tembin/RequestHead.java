package com.ydc.beans.commom.tembin;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestHead implements Serializable{

    
    /** @Fields serialVersionUID: */
      	
    private static final long serialVersionUID = 9011870341582515153L;
    
    private String orgCode;
    
    private String orgName;
    
    private String transNo;
    
    private String transDate;
    
    private String userName;
    
    private String userPassword;
    
    private String functionCode;
    
    private String tcTransNo;
    
    public RequestHead() {}
    
    public RequestHead(String orgCode, String orgName, String userName, String userPassword, String functionCode) {
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.tcTransNo = new SnowflakeIdWorker().nextId()+"";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.transDate = formatter.format(new Date());
        this.userName = userName;
        this.userPassword = MD5Util.MD5(userPassword).toLowerCase();
        this.functionCode = functionCode;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    @Override
    public String toString() {
        return "RequestHead [orgCode=" + orgCode + ", orgName=" + orgName + ", transNo=" + transNo
                + ", transDate=" + transDate + ", userName=" + userName + ", userPassword="
                + userPassword + ", functionCode=" + functionCode + ", tcTransNo=" + tcTransNo + "]";
    }
    
    

}
