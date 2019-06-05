package com.ydc.model.cgj;

import java.io.Serializable;
import java.util.Date;

public class BRollNumber implements Serializable {


    private static final long serialVersionUID = -9104339782928971676L;
    /**
     * t_b_roll_number.id
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    private Integer id;

    /**
     * t_b_roll_number.roll_template_id (券模板id)
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    private Integer rollTemplateId;

    /**
     * t_b_roll_number.roll_amount (劵数量)
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    private Integer rollAmount;

    /**
     * t_b_roll_number.roll_type (劵类型（1：洗车劵；2：保养劵；3：车险劵）)
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    private Integer rollType;

    /**
     * t_b_roll_number.create_time (创建时间)
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    private Date createTime;

    /**
     * t_b_roll_number.create_by (创建人)
     * @ibatorgenerated 2018-10-31 18:38:02
     */
    private Integer createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRollTemplateId() {
        return rollTemplateId;
    }

    public void setRollTemplateId(Integer rollTemplateId) {
        this.rollTemplateId = rollTemplateId;
    }

    public Integer getRollAmount() {
        return rollAmount;
    }

    public void setRollAmount(Integer rollAmount) {
        this.rollAmount = rollAmount;
    }

    public Integer getRollType() {
        return rollType;
    }

    public void setRollType(Integer rollType) {
        this.rollType = rollType;
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
}