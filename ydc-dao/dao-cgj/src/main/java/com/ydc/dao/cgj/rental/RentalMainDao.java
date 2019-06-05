package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalCarMainDetailQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarImgVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMainQueryDetailVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMainQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentalMainDao {

    /**
     * 查询首页banner
     * @return
     */
    List<RentalCarImgVO> getMainBannerUrl();

    /**
     * 寻找车辆
     * @param rentalCarMainQueryDTO
     * @return
     */
    List<RentalCarMainQueryVO> queryPublishCar(RentalCarMainQueryDTO rentalCarMainQueryDTO);

    /**
     * 查询车辆详情
     * @param rentalCarMainDetailQueryDTO
     * @return
     */
    RentalCarMainQueryDetailVO queryCarDetail(RentalCarMainDetailQueryDTO rentalCarMainDetailQueryDTO);

    /**
     * 品牌列表
     * @return
     */
    List<String> getBrandList();

    /**
     * 城市列表(己发布+己出租)
     * @return
     */
    List<String> getAllCities();

}