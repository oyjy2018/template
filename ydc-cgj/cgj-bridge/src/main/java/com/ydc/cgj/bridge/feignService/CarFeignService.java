package com.ydc.cgj.bridge.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@FeignClient(value = "service-car")
public interface CarFeignService {

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
     * 查询车系车型列表
     * @param req
     * @return
     */
    @PostMapping(value = "/car/getCommCarSeriesList")
    public String getCommCarSeriesList(@RequestBody Map<String, Object> req);

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
     * 通过车系查询启用车型
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
}
