package com.ydc.dao.cgj.car;

import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateOperationStatusDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateUseStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.CarPlateVO;
import com.ydc.commom.view.vo.cgj.rental.CommCarQueryVO;
import com.ydc.commom.view.vo.cgj.rental.CommCarSimpleVO;
import com.ydc.model.cgj.car.CommCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommCarDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CommCar record);

    int insertSelective(CommCar record);

    CommCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Map<String, Object> req);

    int updateByPrimaryKey(CommCar record);

    List<CommCarQueryVO> getCarList(CommCarQueryDTO commCarQueryDTO);

    Map<String, Object> getCarListCount(Map<String, Object> req);

    CommCar selectByPlateOrVinOrEngineNo(String keyWord);


    /**
     * 查询车辆等级
     * @return
     */
    List<Map<String,Object>> getCarLevelGroup();

    /**
     * 查询车辆品牌
     * @return
     */
    List<Map<String,Object>> getBrandByCarLevel(@Param("commCarDTO") CommCarDTO commCarDTO);

    /**
     * 查询车辆车系
     * @return
     */
    List<Map<String,Object>> getSeriesByBrand(@Param("commCarDTO") CommCarDTO commCarDTO);


    /**
     * 查询车辆车型
     * @return
     */
    List<Map<String,Object>> getModelBySeries(@Param("commCarDTO") CommCarDTO commCarDTO);

    /**
     * 查询对应车辆
     * @param commCarDTO
     * @return
     */
    List<CarPlateVO> getCarPlateList(@Param("commCarDTO") CommCarDTO commCarDTO);

    /**
     * 查询车辆少量信息
     *
     * @param id
     * @return
     */
    CommCarSimpleVO getCarInfoSimpleById(Integer id);

    /**
     * 查询门店中启用的车辆
     * @param commCarDTO
     * @return
     */
    List<CommCar> getEnabledCarByStoreId(@Param("commCarDTO") CommCarDTO commCarDTO);

    /**
     * 修改车辆启用状态
     * @param commCarUpdateUseStatusDTO
     * @return
     */
    int updateUseStatus(CommCarUpdateUseStatusDTO commCarUpdateUseStatusDTO);

    /**
     * 修改车辆运营状态
     * @param commCarUpdateOperationStatusDTO
     * @return
     */
    int updateOperationStatus(CommCarUpdateOperationStatusDTO commCarUpdateOperationStatusDTO);
}