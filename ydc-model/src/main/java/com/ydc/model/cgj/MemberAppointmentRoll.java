package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class MemberAppointmentRoll implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 预约单id
     */
    private Integer appointId;

    /**
     * 优惠券id
     */
    private Integer rollId;

    /**
     * 优惠券状态（1：使用中；2：使用完成；3：退回）
     */
    private Integer rollStatus;

    /**
     * (状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer status;

    /**
     * (创建时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date createTime;

    /**
     *  (创建人)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer createBy;

    /**
     *  (更新时间)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Date updateTime;

    /**
     * (更新人)
     * @ibatorgenerated 2018-10-30 15:20:17
     */
    private Integer updateBy;


    public MemberAppointmentRoll() {
    }

    public MemberAppointmentRoll(Map<String,Object> map) {
        this.id = map.get("marId")==null?null:Integer.valueOf(map.get("marId").toString());
        this.appointId = map.get("appoint_id")==null?null:Integer.valueOf(map.get("appoint_id").toString());
        this.rollStatus = map.get("order_roll_status")==null?null:Integer.valueOf(map.get("order_roll_status").toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointId() {
        return appointId;
    }

    public void setAppointId(Integer appointId) {
        this.appointId = appointId;
    }

    public Integer getRollId() {
        return rollId;
    }

    public void setRollId(Integer rollId) {
        this.rollId = rollId;
    }

    public Integer getRollStatus() {
        return rollStatus;
    }

    public void setRollStatus(Integer rollStatus) {
        this.rollStatus = rollStatus;
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
