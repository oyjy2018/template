package com.ydc.commom.view.dto.cgj;


import java.io.Serializable;

/**
 * 短信发生
 *
 * @author gongjin
 * @create 2018-10-17 14:56
 **/
public class SMSDTO implements Serializable {

    private static final long serialVersionUID = -3447349018059677842L;

    private Integer memberId;

    private String smsContent;

    private String validateCode;

    public SMSDTO(){}

    public SMSDTO(Integer memberId,String smsContent,String validateCode){
        this.memberId = memberId;
        this.smsContent = smsContent;
        this.validateCode = validateCode;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
