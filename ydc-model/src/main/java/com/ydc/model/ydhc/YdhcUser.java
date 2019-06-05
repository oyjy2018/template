package com.ydc.model.ydhc;

import java.io.Serializable;
import java.util.Date;

public class YdhcUser implements Serializable {
    /**
     * t_ydhc_user.id
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Integer id;

    /**
     * t_ydhc_user.head_picture (头像图片地址)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String headPicture;

    /**
     * t_ydhc_user.user_name (会员名)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String userName;

    /**
     * t_ydhc_user.password (密码)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String password;

    /**
     * t_ydhc_user.mobile_phone (手机号)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String mobilePhone;

    /**
     * t_ydhc_user.id_card (身份证)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String idCard;

    /**
     * t_ydhc_user.gender (性别)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String gender;

    /**
     * t_ydhc_user.age (年龄)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String age;

    /**
     * t_ydhc_user.email (邮箱)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String email;

    /**
     * t_ydhc_user.openid (微信用户ID)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String openid;

    /**
     * t_ydhc_user.whether_real_name (是否实名（1：是；0：否）)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Integer whetherRealName;

    /**
     * t_ydhc_user.has_salesman (是否是业务人员（0：否；1：是）)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Integer hasSalesman;

    /**
     * t_ydhc_user.referrer_id (推荐人ID)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Integer referrerId;

    /**
     * t_ydhc_user.referrer_name (推荐人名字)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private String referrerName;

    /**
     * t_ydhc_user.status (状态（0：无效；1：有效）)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Integer status;

    /**
     * t_ydhc_user.create_time (创建时间(注册时间))
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Date createTime;

    /**
     * t_ydhc_user.update_time (修改时间)
     * @ibatorgenerated 2018-10-08 15:37:08
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getWhetherRealName() {
        return whetherRealName;
    }

    public void setWhetherRealName(Integer whetherRealName) {
        this.whetherRealName = whetherRealName;
    }

    public Integer getHasSalesman() {
        return hasSalesman;
    }

    public void setHasSalesman(Integer hasSalesman) {
        this.hasSalesman = hasSalesman;
    }

    public Integer getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(Integer referrerId) {
        this.referrerId = referrerId;
    }

    public String getReferrerName() {
        return referrerName;
    }

    public void setReferrerName(String referrerName) {
        this.referrerName = referrerName;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}