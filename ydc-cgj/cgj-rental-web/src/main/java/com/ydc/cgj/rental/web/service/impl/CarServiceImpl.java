package com.ydc.cgj.rental.web.service.impl;

import com.ydc.cgj.rental.web.feignService.CarFeignService;
import com.ydc.cgj.rental.web.service.CarService;
import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateOperationStatusDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateUseStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarFeignService carFeignService;

    @Override
    public String saveOrUpdateCar(Map<String, Object> req) {
        return carFeignService.saveOrUpdateCar(req);
    }

    @Override
    public String getCarList(CommCarQueryDTO commCarQueryDTO) {
        return carFeignService.getCarList(commCarQueryDTO);
    }

    @Override
    public String getBrandList(Map<String, Object> req) {
        return carFeignService.getBrandList(req);
    }

    @Override
    public String getSeriesList(Map<String, Object> req) {
        return carFeignService.getSeriesList(req);
    }

    @Override
    public String getModelList(Map<String, Object> req) {
        return carFeignService.getModelList(req);
    }

    @Override
    public String getCarSeriesDetails(Map<String, Object> req) {
        return carFeignService.getCarSeriesDetails(req);
    }

    /**
     * 查询车辆详情
     * @param id
     * @return
     */
    @Override
    public String getCarInfo(Integer id) {
        return carFeignService.getCarInfo(id);
    }

    @Override
    public String saveOrUpdateCarSeries(Map<String, Object> req) {
        return carFeignService.saveOrUpdateCarSeries(req);
    }

    @Override
    public String getCommCarSeriesList(Map<String, Object> req) {
        return carFeignService.getCommCarSeriesList(req);
    }

    @Override
    public String updateHasEnabled(Map<String, Object> req) {
        return carFeignService.updateHasEnabled(req);
    }

    /**
     * 启用/禁用车辆
     * @param commCarUpdateUseStatusDTO
     * @return
     */
    @Override
    public String updateCarUseStatusById(CommCarUpdateUseStatusDTO commCarUpdateUseStatusDTO) {
        return carFeignService.updateCarUseStatusById(commCarUpdateUseStatusDTO);
    }

    /**
     * 获取 全部启用品牌
     *
     * @return
     */
    @Override
    public String getAllEnableBrand() {
        return carFeignService.getAllEnableBrand();
    }

    /**
     * 通过品牌查询启用车系
     *
     * @param req
     * @return
     */
    @Override
    public String getEnableSeriesByBrand(Map<String, Object> req) {
        return carFeignService.getEnableSeriesByBrand(req);
    }

    /**
     * 通过品牌查询启用车系
     *
     * @param req
     * @return
     */
    @Override
    public String getEnableModelBySeries(Map<String, Object> req) {
        return carFeignService.getEnableModelBySeries(req);
    }

    /**
     * 获取车辆少量信息
     *
     * @param id
     * @return
     */
    @Override
    public String getCarInfoSimple(Integer id) {
        return carFeignService.getCarInfoSimple(id);
    }

    /**
     * 修改运营状态
     *
     * @param commCarUpdateOperationStatusDTO
     * @return
     */
    @Override
    public String updateCarOperationStatusById(CommCarUpdateOperationStatusDTO commCarUpdateOperationStatusDTO) {
        return carFeignService.updateCarOperationStatusById(commCarUpdateOperationStatusDTO);
    }

    /**
     * 通过车型id获取邮箱容量
     * @param modelId
     * @return
     */
    @Override
    public String getTankVolumeByModelId(Integer modelId) {
        return carFeignService.getTankVolumeByModelId(modelId);
    }
}
