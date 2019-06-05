package com.ydc.cgj.rental.company.app.feignService;

import com.ydc.commom.view.dto.cgj.rental.RentalCarListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMyListDTO;
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
     * 查询我的车辆
     *
     * @param rentalCarListDTO
     * @return
     */
    @PostMapping(value = "/rentalCarMy/getMy")
    public String getMy(@RequestBody RentalCarListDTO rentalCarListDTO);

    /**
     * 查询我的车辆详情
     *
     * @param rentalCarMyListDTO
     * @return
     */
    @PostMapping(value = "/rentalCarMy/getMyList")
    String getMyList(@RequestBody RentalCarMyListDTO rentalCarMyListDTO);

    /**
     * 删除我的车辆
     *
     * @param carId
     * @return
     */
    @PostMapping(value = "/rentalCarMy/deleteMyCar")
    String deleteMyCar(@RequestParam("carId") Integer carId, @RequestParam("companyId") Integer companyId);

    /**
     * 删除我的车辆
     *
     * @param data
     * @param companyId
     * @return
     */
    @PostMapping(value = "/rentalCarMy/addMyCar")
    String addMyCar(@RequestParam("data") String data, @RequestParam("companyId") Integer companyId);

    /**
     * 根据状态查询车辆列表
     *
     * @param companyId
     * @return
     */
    @PostMapping(value = "/rentalCarMy/getCarListByStatus")
    String getCarListByStatus(@RequestParam("companyId") Integer companyId, @RequestParam("status") Integer status);

    /**
     * 发布车辆
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/rentalCarMy/publish")
    String publish(@RequestParam("data") String data, @RequestParam("companyId") Integer companyId);

    /**
     * 新增车辆-获取品牌列表
     * @return
     */
    @PostMapping(value = "/rentalCarMy/getAddBrandList")
    String getAddBrandList();

    /**
     * 新增车辆-通过品牌查询车系
     * @param brand
     * @return
     */
    @PostMapping(value = "/rentalCarMy/getAddSeriesByBrand")
    String getAddSeriesByBrand(@RequestParam("brand") String brand);

    /**
     * 通过车系查询车型
     * @param series
     * @return
     */
    @PostMapping(value = "/rentalCarMy/getAddModelBySeries")
    String getAddModelBySeries(@RequestParam("series") String series);

    /**
     * 获取我的车辆详情
     *
     * @param carId
     * @return
     */
    @PostMapping(value = "/rentalCar/getDetail")
    String getMyCarDetail(@RequestParam("carId") Integer carId);

    /**
     * 下架车辆
     * @param data
     * @return
     */
    @PostMapping(value = "/rentalCar/removeCar")
    String removeCar(@RequestParam("data") String data);
}
