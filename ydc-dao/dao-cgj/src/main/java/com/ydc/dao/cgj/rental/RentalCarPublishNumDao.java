package com.ydc.dao.cgj.rental;


import com.ydc.model.cgj.rental.RentalCarPublishNum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentalCarPublishNumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalCarPublishNum record);

    int insertSelective(RentalCarPublishNum record);

    RentalCarPublishNum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalCarPublishNum record);

    int updateByPrimaryKey(RentalCarPublishNum record);

    /**
     * 新增车辆数量发布中间表数据
     * @param publishId
     * @param carNum
     * @param seriesId
     * @param carLevel
     */
    void addPublishNumMiddle(@Param("publishId") Integer publishId, @Param("carNum") int carNum, @Param("seriesId") int seriesId, @Param("carLevel") String carLevel);

    /**
     * 查询发布明细
     * @param rentalCarPublishNum
     * @return
     */
    List<RentalCarPublishNum> getRentalCarPublishNum(RentalCarPublishNum rentalCarPublishNum);

    /**
     * 出车更新数量
     * @param rentalCarPublishNum
     */
    void updateSubtractCarNum(RentalCarPublishNum rentalCarPublishNum);

    /**
     * 出车更新数量
     * @param rentalCarPublishNum
     */
    void updateAddCarNum(RentalCarPublishNum rentalCarPublishNum);

    void updateCarNum(RentalCarPublishNum rentalCarPublishNum);
}