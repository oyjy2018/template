package com.ydc.commom.view.dto.cgj;

import com.ydc.model.cgj.Pagination;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author
 * @create 2018-10-29 18:24
 **/
public class LendingCustomerDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = -541266838123279532L;

    private Integer lendingCustomerId;
    private String mobilePhone;
    private String fullScaleStartTime;//满标时间开始
    private String fullScaleEndTime;//满标时间结束

    public LendingCustomerDTO(){}

    public LendingCustomerDTO(Integer lendingCustomerId, String mobilePhone, String fullScaleStartTime, String fullScaleEndTime) {
        this.lendingCustomerId = lendingCustomerId;
        this.mobilePhone = mobilePhone;
        this.fullScaleStartTime = fullScaleStartTime;
        this.fullScaleEndTime = fullScaleEndTime;
    }

    public LendingCustomerDTO changeDTO(){
        this.fullScaleStartTime = (StringUtils.isEmpty(this.fullScaleStartTime) ? null : this.fullScaleStartTime +" 00:00:00");
        this.fullScaleEndTime = (StringUtils.isEmpty(this.fullScaleEndTime) ? null : this.fullScaleEndTime +" 23:59:59");
        return this;
    }

    public Integer getLendingCustomerId() {
        return lendingCustomerId;
    }

    public void setLendingCustomerId(Integer lendingCustomerId) {
        this.lendingCustomerId = lendingCustomerId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFullScaleStartTime() {
        return fullScaleStartTime;
    }

    public void setFullScaleStartTime(String fullScaleStartTime) {
        this.fullScaleStartTime = fullScaleStartTime;
    }

    public String getFullScaleEndTime() {
        return fullScaleEndTime;
    }

    public void setFullScaleEndTime(String fullScaleEndTime) {
        this.fullScaleEndTime = fullScaleEndTime;
    }
}
