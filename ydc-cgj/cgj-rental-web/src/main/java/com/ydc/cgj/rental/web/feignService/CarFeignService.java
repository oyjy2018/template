package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateOperationStatusDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateUseStatusDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalViolationUpdateDealStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Service
@FeignClient(value = "service-car")
public interface CarFeignService {

    /**
     * 修改或保存车辆信息
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/car/saveOrUpdateCar")
    public String saveOrUpdateCar(@RequestBody Map<String, Object> req);

    /**
     * 查询车辆列表
     *
     * @param commCarQueryDTO
     * @return
     */
    @PostMapping(value = "/car/getCarList")
    public String getCarList(@RequestBody CommCarQueryDTO commCarQueryDTO);

    /**
     * 查询车辆品牌
     *
     * @return
     */
    @PostMapping(value = "/car/getBrandList")
    public String getBrandList(@RequestBody Map<String, Object> req);

    /**
     * 根据车品牌查车系
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/car/getSeriesList")
    public String getSeriesList(@RequestBody Map<String, Object> req);

    /**
     * 根据车系查询车型
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/car/getModelList")
    public String getModelList(@RequestBody Map<String, Object> req);

    /**
     * 查询车系车型详情
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/car/getCarSeriesDetails")
    public String getCarSeriesDetails(@RequestBody Map<String, Object> req);

    /**
     * 查询车辆详情
     * @param id
     * @return
     */
    @PostMapping(value = "/car/getCarInfo")
    String getCarInfo(@RequestParam("id") Integer id);

    /**
     * 保存或修改车系车型
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/car/saveOrUpdateCarSeries")
    public String saveOrUpdateCarSeries(@RequestBody Map<String, Object> req);

    /**
     * 查询车系车型列表
     * @param req
     * @return
     */
    @PostMapping(value = "/car/getCommCarSeriesList")
    public String getCommCarSeriesList(@RequestBody Map<String, Object> req);

    /**
     * 启用/禁用车系车型
     * @param req
     * @return
     */
    @PostMapping(value = "/car/updateHasEnabled")
    public String updateHasEnabled(@RequestBody Map<String, Object> req);

    /**
     * 启用/禁用车辆
     * @param commCarUpdateUseStatusDTO
     * @return
     */
    @PostMapping(value = "/car/updateCarUseStatusById")
    public String updateCarUseStatusById(@RequestBody CommCarUpdateUseStatusDTO commCarUpdateUseStatusDTO);

    /**
     * 获取 全部启用品牌
     *
     * @return
     */
    @PostMapping(value = "/car/getAllEnableBrand")
    String getAllEnableBrand();

    /**
     * 通过品牌查询启用车系
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/car/getEnableSeriesByBrand")
    String getEnableSeriesByBrand(@RequestBody Map<String, Object> req);

    /**
     * 通过品牌查询启用车系
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/car/getEnableModelBySeries")
    String getEnableModelBySeries(@RequestBody Map<String, Object> req);

    /**
     * 获取车辆少量信息
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/car/getCarInfoSimple")
    String getCarInfoSimple(@RequestParam("id") Integer id);

    /**
     * 修改运营状态
     *
     * @param commCarUpdateOperationStatusDTO
     * @return
     */
    @PostMapping(value = "/car/updateCarOperationStatusById")
    String updateCarOperationStatusById(@RequestBody CommCarUpdateOperationStatusDTO commCarUpdateOperationStatusDTO);

    /**
     * 通过条件获取违章列表
     * @param rentalViolationDTO
     * @return
     */
    @PostMapping(value = "/rentalViolation/getRentalViolationList")
    Result getRentalViolationList(@RequestBody RentalViolationDTO rentalViolationDTO);

    /**
     * 通过违章id查询违章详情
     * @param id
     * @return
     */
    @GetMapping(value = "/rentalViolation/getRentalViolationById/{id}")
    Result getRentalViolationById(@PathVariable("id") int id);

    /**
     * 新增违章单
     * @param rentalViolationVO
     * @return
     */
    @PostMapping(value = "/rentalViolation/insertRentalViolation")
    String insertRentalViolation(@RequestBody RentalViolationVO rentalViolationVO);

    /**
     * 编辑违章单
     * @param rentalViolationVO
     * @return
     */
    @PostMapping(value = "/rentalViolation/updateRentalViolation")
    String updateRentalViolation(@RequestBody RentalViolationVO rentalViolationVO);

    /**
     * 删除违章记录
     * @param id
     * @return
     */
    @GetMapping(value = "/rentalViolation/updateRentalViolationStatus/{id}")
    String updateRentalViolationStatus(@PathVariable("id") int id);

    /**
     * 获取违章类型
     * @return
     */
    @GetMapping(value = "/rentalViolation/getViolationType")
    String getViolationType();

    /**
     * 提交违章结算单
     * @param req
     */
    @PostMapping(value = "/rentalViolation/updateViolationSettlement")
    Result updateViolationSettlement(@RequestBody Map<String,Object> req);

    /**
     * 更新违章处理状态
     * @param rentalViolationUpdateDealStatusDTO
     * @return
     */
    @PostMapping(value = "/rentalViolation/updateDealStatus")
    String updateRentalViolationDealStatus(@RequestBody RentalViolationUpdateDealStatusDTO rentalViolationUpdateDealStatusDTO);


    /**
     * 通过车型id获取邮箱容量
     * @param modelId
     * @return
     */
    @PostMapping(value = "/car/getTankVolumeByModelId")
    String getTankVolumeByModelId(@RequestParam("modelId") Integer modelId);
}
