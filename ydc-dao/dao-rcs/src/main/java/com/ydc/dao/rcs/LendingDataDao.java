package com.ydc.dao.rcs;

import com.ydc.commom.view.dto.rcs.LendingDataDTO;
import com.ydc.model.rcs.LendingData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LendingDataDao {

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-26 13:57:26
     */
    LendingData selectByPrimaryKey(Integer id);

    /**
     * 查询放款客户数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<LendingDataDTO> getLendingDataByFullScaleTime(@Param("startTime") String startTime, @Param("endTime") String endTime);
}