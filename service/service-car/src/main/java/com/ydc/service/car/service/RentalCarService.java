package com.ydc.service.car.service;

import com.alibaba.fastjson.JSONArray;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarConditionVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarQueryDetailVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarQueryVO;

import java.util.List;

public interface RentalCarService {

    /**
     * 查询外部车辆列表
     *
     * @param rentalCarQueryDTO
     * @return
     */
    List<RentalCarQueryVO> getList(RentalCarQueryDTO rentalCarQueryDTO);

    /**
     * 查询外部车辆查询条件
     *
     * @param
     * @return
     */
    RentalCarConditionVO getCondition();

    /**
     * 查询外部车辆详情
     *
     * @param id
     * @return
     */
    RentalCarQueryDetailVO getDetail(Integer id) throws Exception;

    /**
     * 根据品牌查询车系
     *
     * @param brand
     * @return
     */
    List<String> getSeriesByBrand(String brand);

    /**
     * 根据车系查询车型
     *
     * @param series
     * @return
     */
    List<String> getModelBySeries(String series);

    /**
     * 下架车辆
     *
     * @param carIds
     * @param userId
     * @return
     */
    String removeRentalCar(JSONArray carIds, int userId);

    /**
     * 通过企业名称查询门店列表
     * @param companyName
     * @return
     */
    List<String> getStoreNameByCompanyName(String companyName);
}
