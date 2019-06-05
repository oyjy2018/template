package com.ydc.service.car.service.impl;

import com.ydc.dao.cgj.car.VehicleSeriesDao;
import com.ydc.model.ydhc.YdhcVehicleSeries;
import com.ydc.service.car.service.VehicleSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VehicleSeriesServiceImpl implements VehicleSeriesService {

    @Autowired
    private VehicleSeriesDao vehicleSeriesDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return vehicleSeriesDao.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAll() {
        vehicleSeriesDao.deleteAll();
    }

    @Override
    public int insert(YdhcVehicleSeries record) {
        return vehicleSeriesDao.insert(record);
    }

    @Override
    public int batchInsert(List<Map<String, Object>> list) {
        return vehicleSeriesDao.batchInsert(list);
    }

    @Override
    public int insertSelective(YdhcVehicleSeries record) {
        return vehicleSeriesDao.insertSelective(record);
    }

    @Override
    public YdhcVehicleSeries selectByPrimaryKey(Integer id) {
        return vehicleSeriesDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcVehicleSeries record) {
        return vehicleSeriesDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcVehicleSeries record) {
        return vehicleSeriesDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> getSeriesList() {
        return vehicleSeriesDao.getSeriesList();
    }

    @Override
    public List<Map<String, Object>> getSeriesByBrand(String brand) {
        return vehicleSeriesDao.getSeriesByBrand(brand);
    }

    @Override
    public List<Map<String, Object>> getSeriesByBrandCH(String brand) {
        return vehicleSeriesDao.getSeriesByBrandCH(brand);
    }
}
