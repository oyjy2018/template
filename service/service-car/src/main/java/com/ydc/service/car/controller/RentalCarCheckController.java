package com.ydc.service.car.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarCheckQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarCheckQueryDetailVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarCheckQueryVO;
import com.ydc.service.car.service.RentalCarCheckService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外部车辆审核服务
 */
@RestController
@RequestMapping(value = "/rentalCarCheck")
public class RentalCarCheckController {
    private static final Logger logger = LogManager.getLogger(RentalCarCheckController.class);

    @Autowired
    private RentalCarCheckService rentalCarCheckService;

    /**
     * 查询外部车辆列表查询条件
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getCondition")
    public String getCondition() {
        return Result.success(rentalCarCheckService.getCondition()).toJSON();
    }

    /**
     * 根据品牌查询车系
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getSeriesByBrand")
    public String getCarSeriesByBrand(@RequestParam("brand") String brand) {
        logger.info("brand: " + brand);
        return Result.success(rentalCarCheckService.getSeriesByBrand(brand)).toJSON();
    }

    /**
     * 通过企业名称查询门店列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getStoreNameByCompanyName")
    public String getStoreNameByCompanyName(@RequestParam("companyName") String companyName) {
        return Result.success(rentalCarCheckService.getStoreNameByCompanyName(companyName)).toJSON();
    }

    /**
     * 根据车系查询车型
     *
     * @param
     * @return
     */
    @PostMapping(value = "/getModelBySeries")
    public String getRentalCarCheckModelBySeries(@RequestParam("series") String series) {
        return Result.success(rentalCarCheckService.getModelBySeries(series)).toJSON();
    }

    /**
     * 查询外部车辆列表
     *
     * @param rentalCarCheckQueryDTO
     * @return
     */
    @PostMapping(value = "/getList")
    public String getRentalCarCheckList(@RequestBody RentalCarCheckQueryDTO rentalCarCheckQueryDTO) {
        logger.info("getList: {}", rentalCarCheckQueryDTO);
        List<RentalCarCheckQueryVO> carList = rentalCarCheckService.getList(rentalCarCheckQueryDTO);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("totalCount", PaginationUtil.pageTotalQuery(carList));
        retMap.put("rows", carList);
        return Result.success(retMap).toJSON();
    }

    /**
     * 查询车辆详情
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getDetail")
    public String getDetail(@RequestParam("carId") Integer id) throws Exception {
        RentalCarCheckQueryDetailVO rentalCarQueryDetailVO = rentalCarCheckService.getCheckDetail(id);
        return Result.success(rentalCarQueryDetailVO).toJSON();
    }

    /**
     * 审核
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/check")
    public String check(@RequestParam("data") String data,
                        @RequestParam("userId") Integer userId) {
        return Result.success(rentalCarCheckService.check(data, userId)).toJSON();
    }

}
