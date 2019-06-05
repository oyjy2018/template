package com.ydc.service.car.service;

import com.ydc.commom.view.dto.cgj.rental.RentalCarMainDetailQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMainCityVo;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMainQueryDetailVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMainQueryVO;

import java.util.List;

public interface RentalCarMainService {

    /**
     * 获取主页内容
     * @param
     * @return
     */
    List<String> getMainContent() throws Exception;

    /**
     * 查询品牌列表(已经存在车源的)
     */
    List<String> getBrandList();

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
    RentalCarMainQueryDetailVO getCarDetail(RentalCarMainDetailQueryDTO rentalCarMainDetailQueryDTO) throws Exception;

    /**
     * 获取所有城市(己发布+己出租)
     * @return
     */
    List<String> getAllCities();

    /**
     * 热门城市列表
     * @param companyId
     * @return
     */
    RentalCarMainCityVo getHotCitiesList(Integer companyId);

}
