package com.ydc.model.cgj;

import com.ydc.model.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MemberRoll implements Serializable {
    private static final long serialVersionUID = -8113464775164088365L;
    /**
     * t_member_roll.id (主键)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Integer id;

    /**
     * t_member_roll.member_id (用户id)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Integer memberId;

    /**
     * t_member_roll.member_name (会员名)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private String memberName;

    /**
     * t_member_roll.member_phone (会员手机号码)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private String memberPhone;

    /**
     * t_member_roll.roll_id (券id)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Integer rollId;

    /**
     * t_member_roll.roll_name (券名称)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private String rollName;

    /**
     * t_member_roll.roll_code (券码)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private String rollCode;

    /**
     * t_member_roll.roll_amount (券金额)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private BigDecimal rollAmount;

    /**
     * 券类型
     */
    private Integer rollType;

    /**
     * t_member_roll.roll_status (券状态（1：未使用；2：已使用；3：已失效）)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Integer rollStatus;

    /**
     * t_member_roll.roll_content (券内容)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private String rollContent;

    /**
     * 券属性
     */
    private Integer rollAttribute;

    /**
     * 借款单id
     */
    private Integer loanId;

    private Date usedTime;

    /**
     * t_member_roll.send_time (发送时间)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Date sendTime;

    /**
     * t_member_roll.invalid_time (失效时间)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Date invalidTime;

    /**
     * t_member_roll.send_description (发送描述)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private String sendDescription;

    /**
     * t_member_roll.status (状态（0：失效；1：有效）)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Integer status;

    /**
     * t_member_roll.create_time (创建时间)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Date createTime;

    /**
     * t_member_roll.create_by (创建人)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Integer createBy;

    /**
     * t_member_roll.update_time (更新时间)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Date updateTime;

    /**
     * t_member_roll.update_by (更新人)
     * @ibatorgenerated 2018-10-30 15:20:18
     */
    private Integer updateBy;

    /**
     * 订单号
     */
    private String orderNo;


    public MemberRoll() {
    }

    public MemberRoll(Map<String,Object> memberRollMap){
        this.id = memberRollMap.get("id")==null?null:Integer.valueOf(memberRollMap.get("id").toString());
        this.memberId = memberRollMap.get("memberId")==null?null:Integer.valueOf(memberRollMap.get("memberId").toString());
        this.memberName =memberRollMap.get("memberName")==null?null:memberRollMap.get("memberName").toString();
        this.memberPhone = memberRollMap.get("memberPhone")==null?null:memberRollMap.get("memberPhone").toString();
        this.rollId = memberRollMap.get("rollId")==null?null:Integer.valueOf(memberRollMap.get("rollId").toString());
        this.rollName = memberRollMap.get("rollName")==null?null:memberRollMap.get("rollName").toString();
        this.rollCode = memberRollMap.get("rollCode")==null?null:memberRollMap.get("rollCode").toString();
        this.rollAmount = memberRollMap.get("rollAmount")==null?null:new BigDecimal(memberRollMap.get("rollAmount").toString());
        this.rollType = memberRollMap.get("rollType")==null?null:Integer.valueOf(memberRollMap.get("rollType").toString());
        this.rollStatus = memberRollMap.get("rollStatus")==null?null:Integer.valueOf(memberRollMap.get("rollStatus").toString());
        this.rollContent = memberRollMap.get("rollContent")==null?null:memberRollMap.get("rollContent").toString();
        this.rollAttribute =memberRollMap.get("rollAttribute")==null?null:Integer.valueOf(memberRollMap.get("rollAttribute").toString());
        this.loanId = memberRollMap.get("loanId")==null?null:Integer.valueOf(memberRollMap.get("loanId").toString());
        this.usedTime = memberRollMap.get("usedTime")==null?null:DateUtil.getDateByDatatimefStr(memberRollMap.get("usedTime").toString());
        this.sendTime =  memberRollMap.get("sendTime")==null?null:DateUtil.getDateByDatatimefStr(memberRollMap.get("sendTime").toString());
        this.invalidTime =  memberRollMap.get("invalidTime")==null?null:DateUtil.getDateByDatatimefStr(memberRollMap.get("invalidTime").toString());
        this.sendDescription = memberRollMap.get("sendDescription")==null?null:memberRollMap.get("sendDescription").toString();
        this.status = memberRollMap.get("status")==null?null:Integer.valueOf(memberRollMap.get("status").toString());
        this.createTime =  memberRollMap.get("createTime")==null?null:DateUtil.getDateByDatatimefStr(memberRollMap.get("createTime").toString());
        this.createBy = memberRollMap.get("createBy")==null?null:Integer.valueOf(memberRollMap.get("createBy").toString());
        this.updateTime =  memberRollMap.get("updateTime")==null?null:DateUtil.getDateByDatatimefStr(memberRollMap.get("updateTime").toString());
        this.updateBy = memberRollMap.get("updateBy")==null?null:Integer.valueOf(memberRollMap.get("updateBy").toString());
    }


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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public Integer getRollId() {
        return rollId;
    }

    public void setRollId(Integer rollId) {
        this.rollId = rollId;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public String getRollCode() {
        return rollCode;
    }

    public void setRollCode(String rollCode) {
        this.rollCode = rollCode;
    }

    public BigDecimal getRollAmount() {
        return rollAmount;
    }

    public void setRollAmount(BigDecimal rollAmount) {
        this.rollAmount = rollAmount;
    }

    public Integer getRollStatus() {
        return rollStatus;
    }

    public void setRollStatus(Integer rollStatus) {
        this.rollStatus = rollStatus;
    }

    public String getRollContent() {
        return rollContent;
    }

    public void setRollContent(String rollContent) {
        this.rollContent = rollContent;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getSendDescription() {
        return sendDescription;
    }

    public void setSendDescription(String sendDescription) {
        this.sendDescription = sendDescription;
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

    public Integer getRollType() {
        return rollType;
    }

    public void setRollType(Integer rollType) {
        this.rollType = rollType;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Integer getRollAttribute() {
        return rollAttribute;
    }

    public void setRollAttribute(Integer rollAttribute) {
        this.rollAttribute = rollAttribute;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}