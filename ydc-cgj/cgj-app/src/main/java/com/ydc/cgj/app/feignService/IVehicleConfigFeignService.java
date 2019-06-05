package com.ydc.cgj.app.feignService;

import com.ydc.commom.view.vo.cgj.car.BrandVO;
import com.ydc.commom.view.vo.cgj.car.ModelVO;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 车牌配置
 *
 * @author
 * @create 2018-12-13 14:58
 **/
@Service
@FeignClient(value = "service-car")
public interface IVehicleConfigFeignService {


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
