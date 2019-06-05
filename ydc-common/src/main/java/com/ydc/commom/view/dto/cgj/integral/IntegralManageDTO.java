package com.ydc.commom.view.dto.cgj.integral;

import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.annotation.IsEmpty;
import com.ydc.model.cgj.Pagination;
import io.netty.util.internal.StringUtil;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * WEB积分管理
 *
 * @author gongjin
 * @create 2018-10-18 17:37
 **/
public class IntegralManageDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 1324055308503485898L;


    private String mobilePhone; //手机号码
    private Integer payType; //收支类型(0：获取；1：消耗)
    private String payTimeStart;    //收支时间开始
    private String payTimeEnd;  //收支时间结束
    private Integer memberId;    //会员id

    private String createTimeStart; //注册时间开始
    private String createTimeEnd;   //注册时间结束

    private Integer operatorId; //操作人id
    private BigDecimal usableIntegral;  //充值积分
    private String integralTypeAcquire;    //积分获取(0 车主贷款; 1 每日签到; 2 意见采纳)

    public IntegralManageDTO(){}

    public IntegralManageDTO(int page,int rows, String mobilePhone, Integer payType,
                             String payTimeStart, String payTimeEnd, Integer memberId, String createTimeStart,
                             String createTimeEnd, Integer operatorId, BigDecimal usableIntegral, String integralTypeAcquire) {
        super.setPage(page);
        super.setRows(rows);
        this.mobilePhone = mobilePhone;
        this.payType = payType;
        this.payTimeStart = payTimeStart;
        this.payTimeEnd = payTimeEnd;
        this.memberId = memberId;
        this.createTimeStart = createTimeStart;
        this.createTimeEnd = createTimeEnd;
        this.operatorId = operatorId;
        this.usableIntegral = usableIntegral;
        this.integralTypeAcquire = integralTypeAcquire;
    }

    public IntegralManageDTO changeDTO(){
        this.payTimeStart = (StringUtil.isNullOrEmpty(this.payTimeStart) ? null : this.payTimeStart +" 00:00:00");
        this.payTimeEnd = (StringUtil.isNullOrEmpty(this.payTimeEnd) ? null : this.payTimeEnd +" 23:59:59");
        this.createTimeStart = (StringUtil.isNullOrEmpty(this.createTimeStart) ? null : this.createTimeStart +" 00:00:00");
        this.createTimeEnd = (StringUtil.isNullOrEmpty(this.createTimeEnd) ? null : this.createTimeEnd +" 23:59:59");
        return this;
    }

    public static void main(String[] args) {
        Map<String,Object> data = new HashMap<>();
        data.put("integralId",8);
        data.put("memberId",1);
        data.put("usableIntegral",100);
        data.put("integralTypeAcquire"," ");
        data.put("operatorId",12);
        IntegralManageDTO integralManageDTO = JSONObject.parseObject(JsonUtil.gsonStr(data),IntegralManageDTO.class);
        System.out.println(JsonUtil.gsonStr(integralManageDTO));
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTimeStart() {
        return payTimeStart;
    }

    public void setPayTimeStart(String payTimeStart) {
        this.payTimeStart = payTimeStart;
    }

    public String getPayTimeEnd() {
        return payTimeEnd;
    }

    public void setPayTimeEnd(String payTimeEnd) {
        this.payTimeEnd = payTimeEnd;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public BigDecimal getUsableIntegral() {
        return usableIntegral;
    }

    public void setUsableIntegral(BigDecimal usableIntegral) {
        this.usableIntegral = usableIntegral;
    }

    public String getIntegralTypeAcquire() {
        return integralTypeAcquire;
    }

    public void setIntegralTypeAcquire(String integralTypeAcquire) {
        this.integralTypeAcquire = integralTypeAcquire;
    }
}
