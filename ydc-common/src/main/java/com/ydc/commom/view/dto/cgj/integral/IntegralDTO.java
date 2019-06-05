package com.ydc.commom.view.dto.cgj.integral;

import com.ydc.model.cgj.Pagination;

import java.io.Serializable;

/**
 * 积分
 *
 * @author gongjin
 * @create 2018-10-17 9:41
 **/
public class IntegralDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = -3262270014934754168L;

    /**
     * 收支类型(0：获取；1：消耗)
     */
    private Integer payType;

    /**
     * 会员id
     */
    private Integer memberId;

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public IntegralDTO(){}

    public IntegralDTO(Integer payType,Integer memberId) {
        this.payType = payType;
        this.memberId = memberId;
    }
}
