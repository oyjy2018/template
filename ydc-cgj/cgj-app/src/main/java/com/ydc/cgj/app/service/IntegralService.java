package com.ydc.cgj.app.service;

import com.ydc.commom.view.dto.cgj.integral.IntegralDTO;

/**
 * 积分
 *
 * @author gongjin
 * @create 2018-09-07 13:56
 **/
public interface IntegralService {


    /**
     * 我的积分
     *
     * @param memberId
     * @return
     */
    String getMyIntegral(Integer memberId);


    /**
     * 我的积分-明细（收入和支出）
     *
     * @return
     */
    String getIntegralDetail(IntegralDTO integralDTO);
}
