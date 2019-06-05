package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalCarPublishDTO;
import com.ydc.commom.view.vo.cgj.rental.PublishInfoVO;
import com.ydc.commom.view.vo.cgj.rental.carPublishVO.CarPublishCarDetailsVO;
import com.ydc.model.cgj.rental.RentalCarLevel;
import com.ydc.model.cgj.rental.RentalCarPublish;
import org.apache.ibatis.annotations.Param;

public interface RentalCarPublishDao {

    int deleteByPrimaryKey(Integer id);

    int insert(RentalCarPublish record);

    int insertSelective(RentalCarPublish record);

    RentalCarPublish selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalCarPublish record);

    int updateByPrimaryKey(RentalCarPublish record);

    /**
     * 根据版本号更新车辆发布信息的发布数量
     * @param carPublishId
     * @param publishNum
     * @param version
     * @return
     */
    int updatePublishNumByVersion(@Param("carPublishId")Integer carPublishId,
                                  @Param("publishNum")Integer publishNum,
                                  @Param("version")Integer version);

    /**
     * 获取发布信息
     * @param rentalCarPublishDTO
     * @return
     */
    PublishInfoVO getCarPublishBasicDetails(RentalCarPublishDTO rentalCarPublishDTO);

    /**
     * 获取发布信息：车辆信息
     * @param rentalCarPublishDTO
     * @return
     */
    CarPublishCarDetailsVO getCarPublishCarDetails(RentalCarPublishDTO rentalCarPublishDTO);

    /**
     * 查询最后一次发布数据
     * @param carId
     * @return
     */
    RentalCarPublish getLatestPublishData(int carId);

    /**
     * 更新t_rental_car_publish_num数量
     * @param publishId
     * @param carSeriesId
     * @param carLevel
     */
    void updateMiddleNum(@Param("publishId") Integer publishId, @Param("carSeriesId") Integer carSeriesId, @Param("carLevel") String carLevel);



    /**
     * 车等级查询授权额
     * @param carLevel
     * @return
     */
    RentalCarLevel getPreAuthorizationByCarLevel(@Param("carLevel") String carLevel);
}