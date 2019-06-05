package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;

/**
 * 积分
 *
 * @author gongjin
 * @create 2018-09-07 8:50
 **/
public interface IntegralService {

    /**
     * 积分列表
     *
     * @param integralManageDTO
     * @return
     */
    String getIntegralList(IntegralManageDTO integralManageDTO);

    /**
     * 积分充值
     *
     * @param integralManageDTO
     * @return
     */
    String saveOrUpdateIntegral(IntegralManageDTO integralManageDTO);

    /**
     * 积分明细列表
     *
     * @param integralManageDTO
     * @return
     */
    String getIntegralDetailList(IntegralManageDTO integralManageDTO);
}
