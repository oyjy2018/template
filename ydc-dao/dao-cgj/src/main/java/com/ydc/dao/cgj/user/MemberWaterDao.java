package com.ydc.dao.cgj.user;

import com.ydc.commom.view.dto.cgj.order.MemberWaterDTO;
import com.ydc.model.cgj.MemberWater;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberWaterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberWater record);

    int insertSelective(MemberWater record);

    MemberWater selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberWater record);

    int updateByPrimaryKey(MemberWater record);

    /**
     * 更新流水状态
     * @param payWater
     * @param newWaterStatus
     * @param oldWaterStatus
     * @return
     */
    int updateWaterStatus(@Param(value = "payWater") String payWater,
                          @Param(value = "newWaterStatus") int newWaterStatus,
                          @Param(value = "oldWaterStatus") Integer oldWaterStatus,
                          @Param(value = "transactionId") String transactionId);
    /**
     * 查询交易流水
     * @author: hejiangping
     * @date: 2019/1/15
     */
    List<Map<String, Object>> getMemberSaters(@Param(value = "memberWaterDTO") MemberWaterDTO memberWaterDTO);
}