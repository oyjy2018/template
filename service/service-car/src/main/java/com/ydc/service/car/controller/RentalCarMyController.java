package com.ydc.service.car.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalCarListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMyListDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMiniVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarMyQueryVO;
import com.ydc.service.car.service.RentalCarMyService;
import com.ydc.service.car.service.RentalCarPublishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的外部车辆服务
 */
@RestController
@RequestMapping(value = "/rentalCarMy")
public class RentalCarMyController {
    private static final Logger logger = LogManager.getLogger(RentalCarMyController.class);

    private final RentalCarMyService rentalCarMyService;
    private final RentalCarPublishService rentalCarPublishService;

    @Autowired
    public RentalCarMyController(RentalCarMyService rentalCarMyService, RentalCarPublishService rentalCarPublishService) {
        this.rentalCarMyService = rentalCarMyService;
        this.rentalCarPublishService = rentalCarPublishService;
    }


    /**
     * 新增车辆-查询所有品牌
     *
     * @return
     */
    @PostMapping(value = "/getAddBrandList")
    public String getAddBrandList() {
        return Result.success(rentalCarMyService.getAddBrandList()).toJSON();
    }

    /**
     * 新增车辆-通过车系查询车型
     *
     * @return
     */
    @PostMapping(value = "/getAddSeriesByBrand")
    public String getAddSeriesByBrand(@RequestParam("brand") String brand) {
        return Result.success(rentalCarMyService.getAddSeriesByBrand(brand)).toJSON();
    }

    /**
     * 新增车辆-通过车系查询车型
     *
     * @param series
     * @return
     */
    @PostMapping(value = "/getAddModelBySeries")
    public String getAddModelBySeries(@RequestParam("series") String series) {
        logger.info("getAddModelBySeries: {}", series);
        return Result.success(rentalCarMyService.getAddModelBySeries(series)).toJSON();
    }

    /**
     * 查询外部车辆列表
     *
     * @param rentalCarListDTO
     * @return
     */
    @PostMapping(value = "/getMy")
    public String getMy(@RequestBody RentalCarListDTO rentalCarListDTO) {
        rentalCarListDTO.convertSQLForm();
        List<RentalCarMyQueryVO> carList = rentalCarMyService.getMy(rentalCarListDTO);
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("rows", carList);
        return Result.success(jMap).toJSON();
    }

    /**
     * 获取我的车辆列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getMyList")
    public String getMyList(@RequestBody RentalCarMyListDTO rentalCarMyListDTO) {
        List<RentalCarMiniVO> list = rentalCarMyService.getMyList(rentalCarMyListDTO);
        return Result.success(list).toJSON();
    }

    /**
     * 删除我的车辆
     *
     * @param
     * @return
     */
    @PostMapping(value = "/deleteMyCar")
    public String deleteMyCar(@RequestParam("carId") Integer carId,
                              @RequestParam("companyId") Integer companyId) {
        return Result.success(rentalCarMyService.deleteMyCar(carId, companyId)).toJSON();
    }

    /**
     * 新增我的车辆
     *
     * @param
     * @return
     */
    @PostMapping(value = "/addMyCar")
    public String addMyCar(@RequestParam("data") String data,
                           @RequestParam("companyId") Integer companyId) {
        return Result.success(rentalCarMyService.addMyCar(data, companyId)).toJSON();
    }

    /**
     * 根据状态查询车辆列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getCarListByStatus")
    public String getCarListByStatus(@RequestParam("companyId") Integer companyId,
                                     @RequestParam("status") Integer status) {
        return Result.success(rentalCarPublishService.getCarListByStatus(companyId, status)).toJSON();
    }

    /**
     * 发布车辆
     *
     * @param
     * @return
     */
    @PostMapping(value = "/publish")
    public String publish(@RequestParam("data") String data,
                          @RequestParam("companyId") Integer companyId) {
        return Result.success(rentalCarPublishService.publish(data, companyId)).toJSON();
    }
}
