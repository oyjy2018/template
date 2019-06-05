package com.ydc.model.cgj;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.EnumSet;
import java.util.Optional;

/**
 * 会员表
 */
public class Member implements Serializable {
    private static final long serialVersionUID = 6873432136927377605L;
    /**
     * t_member.id
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer id;

    /**
     * t_member.customer_id (风控用户ID)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer customerId;

    /**
     * t_member.head_picture (头像图片地址)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String headPicture;

    /**
     * t_member.member_name (会员名)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String memberName;

    /**
     * t_member.password (密码)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String password;

    /**
     * t_member.id_card (身份证)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String idCard;

    /**
     * t_member.gender (性别)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String gender;

    /**
     * t_member.age (年龄)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String age;

    /**
     * t_member.mobile_phone (手机号)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String mobilePhone;

    /**
     * t_member.email (邮箱)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String email;

    /**
     * t_member.source (会员来源（1：风控云；2：H5注册）)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer source;

    /**
     * t_member.referrer_mobile_phone (推荐人手机号)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private String referrerMobilePhone;

    /**
     * t_member.referrer_time (推荐人时间)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Date referrerTime;

    /**
     * t_member.whether_real_name (是否实名（1：是；0：否）)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer whetherRealName;

    /**
     * t_member.first_order_date (首次下单日期)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Date firstOrderDate;

    /**
     * t_member.whether_loan (是否借款（1：是；0：否）)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer whetherLoan;

    /**
     * t_member.first_loan_date (首次借款日期)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Date firstLoanDate;

    /**
     * t_member.first_appointment_date (首次预约时间)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Date firstAppointmentDate;

    /**
     * t_member.status (状态（1：正常；2：注销；3：锁定）)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer status;

    /**
     * t_member.create_time (创建时间(注册时间))
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Date createTime;

    /**
     * t_member.logout_time (注销时间)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Date logoutTime;

    /**
     * t_member.logout_by (注销人)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer logoutBy;

    /**
     * t_member.lock_time (锁定时间)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Date lockTime;

    /**
     * t_member.lock_by (锁定人)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer lockBy;

    /**
     * t_member.update_by (修改人)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Integer updateBy;

    /**
     * t_member.update_time (修改时间)
     * @ibatorgenerated 2018-10-30 16:17:10
     */
    private Date updateTime;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证有效日期
     */
    private Date identityValidTime;

    /**
     * 身份证认证时间
     */
    private Date identityCertificateTime;

    /**
     * 驾驶证号
     */
    private String driversLicense;

    /**
     * 驾驶证有效日期
     */
    private Date driversLicenseValidTime;

    /**
     * 是否驾驶证认证（0：未认证；1：已认证）
     */
    private Integer driversLicenseCertificate;

    /**
     * 驾驶证认证日期
     */
    private Date driversLicenseCertificateTime;

    /*
            微信openid
        */
    private String weixinOpenId;

    /*
        微信unionid
    */
    private String weixinUnionId;

    /*
        微信昵称
    */
    private String weixinNickName;

    /*
        微信地址省份
    */
    private String weixinProvince;

    /*
        微信城市
    */
    private String weixinCity;


    /**
     * 微信支付openId
     */
    private String weixinPayOpenId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getReferrerMobilePhone() {
        return referrerMobilePhone;
    }

    public void setReferrerMobilePhone(String referrerMobilePhone) {
        this.referrerMobilePhone = referrerMobilePhone;
    }

    public Date getReferrerTime() {
        return referrerTime;
    }

    public void setReferrerTime(Date referrerTime) {
        this.referrerTime = referrerTime;
    }

    public Integer getWhetherRealName() {
        return whetherRealName;
    }

    public void setWhetherRealName(Integer whetherRealName) {
        this.whetherRealName = whetherRealName;
    }

    public Date getFirstOrderDate() {
        return firstOrderDate;
    }

    public void setFirstOrderDate(Date firstOrderDate) {
        this.firstOrderDate = firstOrderDate;
    }

    public Integer getWhetherLoan() {
        return whetherLoan;
    }

    public void setWhetherLoan(Integer whetherLoan) {
        this.whetherLoan = whetherLoan;
    }

    public Date getFirstLoanDate() {
        return firstLoanDate;
    }

    public void setFirstLoanDate(Date firstLoanDate) {
        this.firstLoanDate = firstLoanDate;
    }

    public Date getFirstAppointmentDate() {
        return firstAppointmentDate;
    }

    public void setFirstAppointmentDate(Date firstAppointmentDate) {
        this.firstAppointmentDate = firstAppointmentDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Integer getLogoutBy() {
        return logoutBy;
    }

    public void setLogoutBy(Integer logoutBy) {
        this.logoutBy = logoutBy;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Integer getLockBy() {
        return lockBy;
    }

    public void setLockBy(Integer lockBy) {
        this.lockBy = lockBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getIdentityValidTime() {
        return identityValidTime;
    }

    public void setIdentityValidTime(Date identityValidTime) {
        this.identityValidTime = identityValidTime;
    }

    public Date getIdentityCertificateTime() {
        return identityCertificateTime;
    }

    public void setIdentityCertificateTime(Date identityCertificateTime) {
        this.identityCertificateTime = identityCertificateTime;
    }

    public Date getDriversLicenseValidTime() {
        return driversLicenseValidTime;
    }

    public void setDriversLicenseValidTime(Date driversLicenseValidTime) {
        this.driversLicenseValidTime = driversLicenseValidTime;
    }

    public Integer getDriversLicenseCertificate() {
        return driversLicenseCertificate;
    }

    public void setDriversLicenseCertificate(Integer driversLicenseCertificate) {
        this.driversLicenseCertificate = driversLicenseCertificate;
    }

    public Date getDriversLicenseCertificateTime() {
        return driversLicenseCertificateTime;
    }

    public void setDriversLicenseCertificateTime(Date driversLicenseCertificateTime) {
        this.driversLicenseCertificateTime = driversLicenseCertificateTime;
    }

    public String getDriversLicense() {
        return driversLicense;
    }

    public void setDriversLicense(String driversLicense) {
        this.driversLicense = driversLicense;
    }

    public String getWeixinOpenId() {
        return this.weixinOpenId;
    }

    public void setWeixinOpenId(String weixinOpenId) {
        this.weixinOpenId = weixinOpenId;
    }

    public String getWeixinUnionId() {
        return this.weixinUnionId;
    }

    public void setWeixinUnionId(String weixinUnionId) {
        this.weixinUnionId = weixinUnionId;
    }

    public String getWeixinNickName() {
        return this.weixinNickName;
    }

    public void setWeixinNickName(String weixinNickName) {
        this.weixinNickName = weixinNickName;
    }

    public String getWeixinProvince() {
        return this.weixinProvince;
    }

    public void setWeixinProvince(String weixinProvince) {
        this.weixinProvince = weixinProvince;
    }

    public String getWeixinCity() {
        return this.weixinCity;
    }

    public void setWeixinCity(String weixinCity) {
        this.weixinCity = weixinCity;
    }

    public String getWeixinPayOpenId() {
        return weixinPayOpenId;
    }

    public void setWeixinPayOpenId(String weixinPayOpenId) {
        this.weixinPayOpenId = weixinPayOpenId;
    }



    public static enum SourceEnum {
        SOURCE_ENUM_1(1, "风控云"),
        SOURCE_ENUM_2(2, "H5注册"),
        MEMBER_STATUS_ENUM_1(1,"正常"),
        MEMBER_STATUS_ENUM_2(2,"注销"),
        MEMBER_STATUS_ENUM_3(3,"锁定");
        private static EnumSet<SourceEnum> sourceEnum = EnumSet.allOf(SourceEnum.class);
        private Integer key;
        private String value;

        private SourceEnum(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static Optional<SourceEnum> getSourceEnumByKey(final Integer key) {
            return sourceEnum.stream()
                    .filter(sourceEnum -> sourceEnum.key.equals(key))
                    .findAny();
        }

        public static String getSourceEnumValueByKey(final Integer key) {
            SourceEnum sourceEnum = getSourceEnumByKey(key).orElse(SOURCE_ENUM_2);
            return sourceEnum.getValue();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}