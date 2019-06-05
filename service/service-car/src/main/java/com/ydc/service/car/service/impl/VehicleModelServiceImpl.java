package com.ydc.service.car.service.impl;

import com.ydc.dao.cgj.car.VehicleModelDao;
import com.ydc.model.ydhc.YdhcVehicleModel;
import com.ydc.service.car.service.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    @Autowired
    private VehicleModelDao vehicleModelDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return vehicleModelDao.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAll() {
        vehicleModelDao.deleteAll();
    }

    @Override
    public int insert(YdhcVehicleModel record) {
        return vehicleModelDao.insert(record);
    }

    @Override
    public void batchInsert(List<Map<String, Object>> list) {
        vehicleModelDao.batchInsert(list);
    }

    @Override
    public int insertSelective(YdhcVehicleModel record) {
        return vehicleModelDao.insertSelective(record);
    }

    @Override
    public YdhcVehicleModel selectByPrimaryKey(Integer id) {
        return vehicleModelDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcVehicleModel record) {
        return vehicleModelDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcVehicleModel record) {
        return vehicleModelDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> getVersionBySeries(String series) {
        return vehicleModelDao.getVersionBySeries(series);
    }

    @Override
    public List<Map<String, Object>> getVersionBySeriesCH(String series) {
        return vehicleModelDao.getVersionBySeriesCH(series);
    }

    @Override
    public List<Map<String, Object>> getModelList() {
        return vehicleModelDao.getModelList();
    }
}
