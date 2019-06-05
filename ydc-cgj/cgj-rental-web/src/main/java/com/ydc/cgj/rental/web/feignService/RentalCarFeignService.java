package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-car")
public interface RentalCarFeignService {

    /**
     * 查询车辆列表
     *
     * @param rentalCarQueryDTO
     * @return
     */
    @PostMapping(value = "/rentalCar/getList")
    public String getList(@RequestBody RentalCarQueryDTO rentalCarQueryDTO);

    /**
     * 查询车辆列表查询条件
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalCar/getCondition")
    String getCondition(@RequestParam("companyId") Integer companyId);

    /**
     * 根据车辆品牌查询车系
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalCar/getSeriesByBrand")
    String getSeriesByBrand(@RequestParam("brand") String brand);

    /**
     * 根据车系查询车型
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalCar/getModelBySeries")
    String getModelBySeries(@RequestParam("series") String series);

    /**
     * 下架车辆
     * @param data
     * @return
     */
    @PostMapping(value = "/rentalCar/removeCar")
    String removeCar(@RequestParam("data") String data);

    /**
     * 获取车辆详情
     *
     * @param carId
     * @return
     */
    @PostMapping(value = "/rentalCar/getDetail")
    String getDetail(@RequestParam("carId") int carId);

    /**
     * 通过企业名称查询名店名称
     * @param companyName
     */
    @PostMapping(value = "/rentalCar/getStoreNameByCompanyName")
    String getStoreNameByCompanyName(@RequestParam("companyName") String companyName);
}
