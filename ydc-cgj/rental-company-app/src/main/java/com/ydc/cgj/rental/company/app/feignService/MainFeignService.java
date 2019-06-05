package com.ydc.cgj.rental.company.app.feignService;

import com.ydc.commom.view.dto.cgj.rental.RentalCarMainDetailQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMainQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-car")
public interface MainFeignService {

    /**
     * 查询首页内容
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalCarMain/getMainContent")
    public String getMainContent();

    /**
     * 寻找车辆
     * @param rentalCarMainQueryDTO
     * @return
     */
    @PostMapping(value = "/rentalCarMain/queryPublishCar")
    String queryPublishCar(@RequestBody RentalCarMainQueryDTO rentalCarMainQueryDTO);

    /**
     * 查询首页的车辆详情
     * @param rentalCarMainDetailQueryDTO
     * @return
     */
    @PostMapping(value = "/rentalCarMain/getCarDetail")
    String getCarDetail(@RequestBody RentalCarMainDetailQueryDTO rentalCarMainDetailQueryDTO);


    /**
     * 品牌列表
     * @return
     */
    @PostMapping(value = "/rentalCarMain/getBrandList")
    String getBrandList();

    /**
     * 获取所有己城市(己发布+己出租)
     * @return
     */
    @PostMapping(value = "/rentalCarMain/getAllCities")
    String getAllCities();

    /**
     * 获取热门城市列表
     * @return
     */
    @PostMapping(value = "/rentalCarMain/getHotCitiesList")
    String getHotCitiesList(@RequestParam("companyId") Integer companyId);
}
