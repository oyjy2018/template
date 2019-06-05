package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.view.dto.cgj.rental.RentalCarCheckQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-car")
public interface RentalCarCheckFeignService {

    /**
     * 查询车辆审核列表
     *
     * @param rentalCarCheckQueryDTO
     * @return
     */
    @PostMapping(value = "/rentalCarCheck/getList")
    public String getList(@RequestBody RentalCarCheckQueryDTO rentalCarCheckQueryDTO);

    /**
     * 查询车辆列表查询条件
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalCarCheck/getCondition")
    String getCondition(@RequestParam("companyId") Integer companyId);

    /**
     * 根据车辆品牌查询车系
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalCarCheck/getSeriesByBrand")
    String getSeriesByBrand(@RequestParam("brand") String brand);

    /**
     * 根据车系查询车型
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalCarCheck/getModelBySeries")
    String getModelBySeries(@RequestParam("series") String series);

    /**
     * 获取审核详情
     *
     * @param carId
     * @return
     */
    @PostMapping(value = "/rentalCarCheck/getDetail")
    String getDetail(@RequestParam("carId") int carId);

    /**
     * 审核
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/rentalCarCheck/check")
    String check(@RequestParam("data") String data,
                 @RequestParam("userId") Integer userId);


    /**
     * 通过企业名称查询名店名称
     * @param companyName
     * @return
     */
    @PostMapping(value = "/rentalCarCheck/getStoreNameByCompanyName")
    String getStoreNameByCompanyName(@RequestParam("companyName") String companyName);
}
