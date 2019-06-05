package com.ydc.dao.cgj.car;

import com.ydc.commom.view.dto.cgj.rental.CommCarSeriesDTO;
import com.ydc.commom.view.vo.cgj.rental.CommCarSeriesVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalEnterpriseCarVO;
import com.ydc.model.cgj.car.CommCarSeries;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommCarSeriesDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(CommCarSeries record);

    int insertSelective(CommCarSeries record);

    CommCarSeries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommCarSeries record);

    int updateByPrimaryKey(CommCarSeries record);

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
    int updateHasEnabledById(@Param("commCarSeriesId") Integer commCarSeriesId, @Param("hasEnabled") Integer hasEnabled, @Param("updateBy") Integer updateBy);

    /**
     * 查询所有启用车辆品牌
     * @return
     */
    List<Map<String, Object>> getAllEnableBrand();


    /**
     * 通过品牌查询启用车系
     *
     * @param req
     * @return
     */
    List<Map<String, Object>> getEnableSeriesByBrand(Map<String, Object> req);

    /**
     * 通过车系查询启用车型
     *
     * @param req
     * @return
     */
    List<Map<String, Object>> getEnableModelBySeries(Map<String, Object> req);

    /**
     * 根据参数查询数据
     * @param param
     * @return
     */
    List<CommCarSeries> getCarSeriesByParam(Map<String, Object> param);


    /**
     * 车辆品牌
     * @return
     */
    List<RentalEnterpriseCarVO> getCarBrand();

    /**
     * 车辆车型
     * @return
     */
    List<RentalEnterpriseCarVO> getCarSeries(CommCarSeriesDTO dto);

    /**
     * 车辆车系
     * @return
     */
    List<RentalEnterpriseCarVO> getCarModel(CommCarSeriesDTO dto);

    /**
     * 通过车型id获取邮箱容量
     * @param modelId
     * @return
     */
    Map getTankVolumeByModelId(Integer modelId);
}