package com.ydc.service.car.service;

import com.ydc.commom.view.vo.cgj.rental.CommCarSeriesVO;
import com.ydc.model.cgj.car.CommCarSeries;

import java.util.List;
import java.util.Map;

public interface CommCarSeriesService {
    int deleteByPrimaryKey(Integer id);

    int insert(CommCarSeries record);

    int insertSelective(CommCarSeries record);

    CommCarSeries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommCarSeries record);

    int updateByPrimaryKey(CommCarSeries record);

    CommCarSeries saveOrUpdate(Map<String, Object> req);

    /**
     * 查询车系车型列表
     * @param param
     * @return
     */
    List<CommCarSeriesVO> getCommCarSeriesList(Map<String, Object> param);

    /**
     * 查询车系车型列表总数
     * @param param
     * @return
     */
    Map<String, Object> getCommCarSeriesCount(Map<String, Object> param);

    /**
     * 更新启用状态
     * @param hasEnabled
     * @param updateBy
     * @return
     */
    int updateHasEnabledById(Integer commCarSeriesId, Integer hasEnabled, Integer updateBy);

    /**
     * 查询所有启用车辆品牌
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
     * 根据参数查询数据
     * @param param
     * @return
     */
    List<CommCarSeries> getCarSeriesByParam(Map<String, Object> param);

    /**
     * 通过车型id获取邮箱容量
     * @param modelId
     * @return
     */
    String getTankVolumeByModelId(Integer modelId);
}
