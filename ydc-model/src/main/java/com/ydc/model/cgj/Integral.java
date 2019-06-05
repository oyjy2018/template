package com.ydc.model.cgj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分表
 */
public class Integral implements Serializable {
    private static final long serialVersionUID = -1770334996728989198L;
    /**
     * t_integral.id
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer id;

    /**
     * t_integral.member_id (会员ID)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer memberId;

    /**
     * t_integral.usable_integral (可用积分)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private BigDecimal usableIntegral;

    /**
     * t_integral.version (版本号)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer version;

    /**
     * t_integral.create_time (创建时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date createTime;

    /**
     * t_integral.create_by (创建人)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer createBy;

    /**
     * t_integral.update_time (修改时间)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Date updateTime;

    /**
     * t_integral.update_by (修改人)
     *
     * @ibatorgenerated 2018-09-04 10:11:24
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getUsableIntegral() {
        return usableIntegral;
    }

    public void setUsableIntegral(BigDecimal usableIntegral) {
        this.usableIntegral = usableIntegral;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}