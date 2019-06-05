package com.ydc.service.user.service;

import com.ydc.commom.view.dto.cgj.integral.IntegralDTO;
import com.ydc.commom.view.dto.cgj.integral.IntegralManageDTO;
import com.ydc.commom.view.vo.cgj.order.IntegralDetailVO;
import com.ydc.model.cgj.IntegralDetail;
import java.util.List;
import java.util.Map;

/**
 * 积分明细
 *
 * @author gongjin
 * @create 2018-09-05 11:35
 **/
public interface IntegralDetailService {

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     *
     * @ibatorgenerated 2018-09-04 10:11:22
     */
    Integer insert(IntegralDetail record);

    /**
     * 积分明细列表
     *
     * @param integralManageDTO
     * @return
     */
    List<IntegralDetailVO> getIntegralDetailList(IntegralManageDTO integralManageDTO);

    /**
     * 会员id会员积分明细
     *
     * @param memberId
     * @return
     */
    List<IntegralDetail> getIntegralDetailByMemberId(Integer memberId);


    /**
     * H5积分明细
     * @param integralDTO
     * @return
     */
    List<IntegralDetailVO> h5GetIntegralDetailList(IntegralDTO integralDTO);
}
