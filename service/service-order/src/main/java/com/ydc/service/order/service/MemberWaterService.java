package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.order.MemberWaterDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberWaterService {

    /**
     * 更新流水状态
     * @param payWater
     * @param newWaterStatus
     * @param oldWaterStatus
     * @return
     */
    int updateWaterStatus(String payWater, int newWaterStatus, Integer oldWaterStatus, String transactionId);
    /**
     * 查询交易流水
     * @author: hejiangping
     * @date: 2019/1/15
     */
    List<Map<String, Object>> getMemberSaters(@Param(value = "memberWaterDTO") MemberWaterDTO memberWaterDTO);
}
