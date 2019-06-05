package com.ydc.commom.view.dto.cgj;

import com.ydc.model.cgj.Pagination;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author
 * @create 2018-10-29 15:40
 **/
public class MemberDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 8884716103927143204L;

    private Integer memberId;//会员id
    private String mobilePhone;//手机号码
    private String createTimeStart;//注册时间
    private String createTimeEnd;//注册时间
    private Integer application;//应用
    private Integer status;//会员状态（1：正常；2：注销；3：锁定）
    private Integer updateBy;//更新人

    private String realName; //真实姓名
    private Integer identityCertificate; //是否身份认证（0：未认证；1：认证；-1：全部）
    private Integer driversLicenseCertificate; //驾驶证认证（0：未认证；1：认证；-1：全部）

    public MemberDTO changeDTO(){
        this.createTimeStart = StringUtils.isEmpty(this.createTimeStart) ? null : this.createTimeStart +" 00:00:00";
        this.createTimeEnd = StringUtils.isEmpty(this.createTimeEnd) ? null : this.createTimeEnd +" 23:59:59";
        return this;
    }

    public MemberDTO changeEndTime(String time){
        if (this.createTimeEnd != null && !("").equals(this.createTimeEnd)){
            this.createTimeEnd = this.createTimeEnd + time;
        }
        return this;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApplication() {
        return application;
    }

    public void setApplication(Integer application) {
        this.application = application;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getIdentityCertificate() {
        return identityCertificate;
    }

    public void setIdentityCertificate(Integer identityCertificate) {
        this.identityCertificate = identityCertificate;
    }

    public Integer getDriversLicenseCertificate() {
        return driversLicenseCertificate;
    }

    public void setDriversLicenseCertificate(Integer driversLicenseCertificate) {
        this.driversLicenseCertificate = driversLicenseCertificate;
    }
}
