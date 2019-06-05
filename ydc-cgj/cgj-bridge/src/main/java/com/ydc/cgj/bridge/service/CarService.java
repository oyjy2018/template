package com.ydc.cgj.bridge.service;

import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;

import java.util.Map;

public interface CarService {

    /**
     * 查询车辆列表
     *
     * @param commCarQueryDTO
     * @return
     */
    public String getCarList(CommCarQueryDTO commCarQueryDTO);

    /**
     * 查询车辆品牌
     *
     * @param req
     * @return
     */
    public String getBrandList(Map<String, Object> req);

    /**
     * 根据车品牌查车系
     *
     * @param req
     * @return
     */
    public String getSeriesList(Map<String, Object> req);

    /**
     * 根据车系查询车型
     *
     * @param req
     * @return
     */
    public String getModelList(Map<String, Object> req);

    /**
     * 查询车系车型详情
     *
     * @param req
     * @return
     */
    public String getCarSeriesDetails(Map<String, Object> req);

    /**
     * 查询车辆详情
     * @param id
     * @return
     */
    String getCarInfo(Integer id);

    /**
     * 查询车系车型列表
     * @param req
     * @return
     */
    public String getCommCarSeriesList(Map<String, Object> req);

    /**
     * 获取 全部启用品牌
     *
     * @return
     */
    String getAllEnableBrand();

    /**
     * 通过品牌查询启用车系
     *
     * @param req
     * @return
     */
    String getEnableSeriesByBrand(Map<String, Object> req);

    /**
     * 通过车系查询启用车型
     *
     * @param req
     * @return
     */
    String getEnableModelBySeries(Map<String, Object> req);

    /**
     * 获取车辆少量信息
     *
     * @param id
     * @return
     */
    String getCarInfoSimple(Integer id);
}
