package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalCarListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMyListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalCarByCarPlateVO;
import com.ydc.model.cgj.rental.RentalCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RentalCarDao {

    /**
     * 查询外部车列表
     *
     * @param rentalCarQueryDTO
     * @return
     */
    List<RentalCarQueryVO> getList(RentalCarQueryDTO rentalCarQueryDTO);

    /**
     * 查询车辆详情
     *
     * @param id
     * @return
     */
    RentalCarQueryDetailVO getDetail(Integer id);

    /**
     * 查询外部车查询条件
     *
     * @param
     * @return
     */
    List<RentalCarConditionMiddleVO> getCondition();

    /**
     * 通过企业名称查询门店列表
     * @param companyName
     * @return
     */
    List<String> getStoreNameByCompanyName(String companyName);

    /**
     * 查询所有品牌
     *
     * @return
     */
    List<String> getAddBrandList();

    /**
     * 新增车辆-通过车系查询车型
     * @param brand
     * @return
     */
    List<String> getAddSeriesByBrand(String brand);


    /**
     * 新增车辆-通过车系查询车型
     * @param series
     * @return
     */
    List<RentalCarSeriesVo> getAddModelBySeries(String series);

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
     * 查询条数
     *
     * @param rentalCar
     */
    int getCount(RentalCar rentalCar);

    int deleteByPrimaryKey(Integer id);

    int insert(RentalCar record);

    int insertSelective(RentalCar record);

    RentalCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalCar record);

    int updateByPrimaryKey(RentalCar record);

    /**
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>以下为B2B端接口>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * 查询我的车辆
     *
     * @param rentalCarListDTO
     * @return
     */
    List<RentalCarMyQueryVO> getMy(RentalCarListDTO rentalCarListDTO);

    /**
     * 查询我的车辆列表
     *
     * @param rentalCarMyListDTO
     * @return
     */
    List<RentalCarMiniVO> getMyList(RentalCarMyListDTO rentalCarMyListDTO);

    /**
     * 根据状态查询车辆列表
     *
     * @param companyId
     * @param status
     * @return
     */
    List<RentalCarListVO> getCarListByStatus(@Param("companyId") Integer companyId, @Param("status") Integer status);

    /**
     * 新增发布中间表数据
     *
     * @param params
     */
    void addPublishMiddle(Map<String, Object> params);

    /**
     * 批量更新车辆状态
     *
     * @param list
     * @param status
     * @param updateBy
     */
    void batchUpdateCar(@Param("list") List<String> list, @Param("status") Integer status, @Param("updateBy") Integer updateBy);

    /**
     * 外部车辆信息表
     *
     * @param rentalCar
     * @return
     */
    List<RentalCar> getRentalCar(RentalCar rentalCar);

    /**
     * 查询车辆详情
     * @param list
     * @return
     */
    List<RentalCarByCarPlateVO> getRentalCarByCarPlateList(@Param("list") List<String> list);

}