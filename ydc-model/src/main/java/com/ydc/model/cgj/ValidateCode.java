package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;
import java.util.EnumSet;
import java.util.Optional;

/**
 * 手机验证码
 */
public class ValidateCode implements Serializable {
    private static final long serialVersionUID = 2654648348671905639L;
    /**
     * t_validate_code.id (主键ID)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer id;

    /**
     * t_validate_code.mobile_phone (手机号)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String mobilePhone;

    /**
     * t_validate_code.code (验证码)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private String code;

    /**
     * t_validate_code.validate_type (验证类型（1：H5登录；2：APP登录）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer validateType;

    /**
     * t_validate_code.validate_supplier (短信提供商（1：阿里；2：信信客）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer validateSupplier;

    /**
     * t_validate_code.time_out (失效时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date timeOut;

    /**
     * t_validate_code.status (状态（1：正常；0：禁用）)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Integer status;

    /**
     * t_validate_code.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date createTime;

    /**
     * t_validate_code.update_time (更新时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:23
     */
    private Date updateTime;

    /**
     * 是否已使用（0：未使用；1：已使用）
     */
    private Integer used;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getValidateType() {
        return validateType;
    }

    public void setValidateType(Integer validateType) {
        this.validateType = validateType;
    }

    public Integer getValidateSupplier() {
        return validateSupplier;
    }

    public void setValidateSupplier(Integer validateSupplier) {
        this.validateSupplier = validateSupplier;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
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

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public static enum ValidateTypeEnum {
        VALIDATETYPE_ENUM_1(1, "注册验证码"),
        VALIDATETYPE_ENUM_2(2, "修改密码验证码"),
        VALIDATETYPE_ENUM_3(3, "登录验证码"),
        VALIDATETYPE_ENUM_5(5, "申请验证码"),
       // VALIDATETYPE_ENUM_4(4, "企业注册验证码"), //合代码时冲突 暂时注释掉
        VALIDATETYPE_ENUM_6(6, "企业登录验证码");


        private static EnumSet<ValidateTypeEnum> validateTypeEnum = EnumSet.allOf(ValidateTypeEnum.class);
        private Integer key;
        private String value;

        private ValidateTypeEnum(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public static Optional<ValidateTypeEnum> getValidateTypeEnumByKey(final Integer key) {
            return validateTypeEnum.stream()
                    .filter(validateTypeEnum -> validateTypeEnum.key.equals(key))
                    .findAny();
        }

        public static String getValidateTypeEnumValueByKey(final Integer key) {
            ValidateTypeEnum validateTypeEnum = getValidateTypeEnumByKey(key).orElse(VALIDATETYPE_ENUM_1);
            return validateTypeEnum.getValue();
        }
    }
}