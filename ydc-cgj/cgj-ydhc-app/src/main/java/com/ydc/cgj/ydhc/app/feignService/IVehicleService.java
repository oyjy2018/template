package com.ydc.cgj.ydhc.app.feignService;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.vo.cgj.car.BrandVO;
import com.ydc.commom.view.vo.cgj.car.ModelVO;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import com.ydc.commom.view.vo.ydhc.VehicleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
@FeignClient(value = "service-car")
public interface IVehicleService {

    /**
     * 获取车辆列表
     *
     * @param vehicleVo
     * @return
     */
    @PostMapping("/vehicle/getVehicleList")
    Result getVehicleList(@RequestBody VehicleVo vehicleVo);

    /**
     * 获取车辆详情
     *
     * @param vehicleId
     * @return
     */
    @PostMapping("/vehicle/getVehicleDetail")
    Result getVehicleDetail(@RequestParam("vehicleId") Integer vehicleId);

    /**
     * 车品牌
     * @return
     */
    @PostMapping(value = "/vehicle/getBrandVOList")
    List<BrandVO> getBrandVOList();


    /**
     * 车系
     * @return
     */
    @PostMapping(value = "/vehicle/getSeriesVOList")
    Map<String, List<SeriesVO>> getSeriesVOList();


    /**
     * 车型
     * @return
     */
    @PostMapping(value = "/vehicle/getModelVOList")
    List<ModelVO> getModelVOList(@RequestParam("series") String series);

}
