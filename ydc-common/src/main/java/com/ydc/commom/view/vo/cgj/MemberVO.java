package com.ydc.commom.view.vo.cgj;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.common.CommonEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车管家会员列表
 *
 * @author
 * @create 2018-12-07 15:41
 **/
public class MemberVO implements Serializable {
    private static final long serialVersionUID = 4281964970505884008L;

    private Integer memberId;
    private String mobilePhone;
    private String memberName;
    private String idCard;
    private Date createTime;
    private String referrerMobilePhone;
    private Integer whetherReal;
    private String whetherRealName;
    private Date firstOrderDate;
    private Integer whetherLoanCode;
    private String whetherLoan;
    private Date firstLoanDate;
    private BigDecimal usableIntegral;
    private Integer status;
    private String statusName;
    private Date firstAppointmentDate;
    private Integer usableTicketNumber;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReferrerMobilePhone() {
        return referrerMobilePhone;
    }

    public void setReferrerMobilePhone(String referrerMobilePhone) {
        this.referrerMobilePhone = referrerMobilePhone;
    }

    public Integer getWhetherReal() {
        return whetherReal;
    }

    public void setWhetherReal(Integer whetherReal) {
        this.whetherReal = whetherReal;
    }

    public String getWhetherRealName() {
        return CommonEnum.WhetherEnum.getCodeName(this.whetherReal);
    }

    public void setWhetherRealName(String whetherRealName) {
        this.whetherRealName = whetherRealName;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getFirstOrderDate() {
        return firstOrderDate;
    }

    public void setFirstOrderDate(Date firstOrderDate) {
        this.firstOrderDate = firstOrderDate;
    }

    public Integer getWhetherLoanCode() {
        return whetherLoanCode;
    }

    public void setWhetherLoanCode(Integer whetherLoanCode) {
        this.whetherLoanCode = whetherLoanCode;
    }

    public String getWhetherLoan() {
        return CommonEnum.WhetherEnum.getCodeName(this.whetherLoanCode);
    }

    public void setWhetherLoan(String whetherLoan) {
        this.whetherLoan = whetherLoan;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getFirstLoanDate() {
        return firstLoanDate;
    }

    public void setFirstLoanDate(Date firstLoanDate) {
        this.firstLoanDate = firstLoanDate;
    }

    public BigDecimal getUsableIntegral() {
        return usableIntegral;
    }

    public void setUsableIntegral(BigDecimal usableIntegral) {
        this.usableIntegral = usableIntegral;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return CommonEnum.MemberStatusEnum.getCodeName(this.status);
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getFirstAppointmentDate() {
        return firstAppointmentDate;
    }

    public void setFirstAppointmentDate(Date firstAppointmentDate) {
        this.firstAppointmentDate = firstAppointmentDate;
    }

    public Integer getUsableTicketNumber() {
        return usableTicketNumber;
    }

    public void setUsableTicketNumber(Integer usableTicketNumber) {
        this.usableTicketNumber = usableTicketNumber;
    }
}
