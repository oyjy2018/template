package com.ydc.service.user.service.impl;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.view.dto.cgj.VehicleDTO;
import com.ydc.commom.view.vo.cgj.VehicleVO;
import com.ydc.dao.cgj.user.VehicleDao;
import com.ydc.model.cgj.Vehicle;
import com.ydc.service.user.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2018-10-30 12:08
 **/
@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    VehicleDao vehicleDao;

    @Override
    public List<Map<String, Object>> getVehicleList(VehicleDTO vehicleDTO) {
        return vehicleDao.getVehicleList(vehicleDTO);
    }

    @Override
    public Vehicle selectByPrimaryKey(Integer id) {
        return vehicleDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKey(Vehicle record) {
        return vehicleDao.updateByPrimaryKey(record);
    }

    @Override
    public Integer insert(Vehicle record) {
        return vehicleDao.insert(record);
    }

    @Override
    public Vehicle selectByCarPlateAndMemberId(String carPlate,Integer memberId) {
        return vehicleDao.selectByCarPlateAndMemberId(carPlate,memberId);
    }

    @Override
    public Vehicle selectByCarPlate(String carPlate) {
        return vehicleDao.selectByCarPlate(carPlate);
    }

    @Override
    public VehicleVO getVehicleVOById(Integer vehicleId) {
        return vehicleDao.getVehicleVOById(vehicleId);
    }
}
