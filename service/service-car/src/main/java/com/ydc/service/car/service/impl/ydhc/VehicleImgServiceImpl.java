package com.ydc.service.car.service.impl.ydhc;

import com.ydc.dao.ydhc.YdhcVehicleImgDao;
import com.ydc.model.ydhc.YdhcVehicleImg;
import com.ydc.service.car.service.ydhc.VehicleImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleImgServiceImpl implements VehicleImgService {

    @Autowired
    private YdhcVehicleImgDao ydhcVehicleImgDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ydhcVehicleImgDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YdhcVehicleImg record) {
        return ydhcVehicleImgDao.insert(record);
    }

    @Override
    public int insertSelective(YdhcVehicleImg record) {
        return ydhcVehicleImgDao.insertSelective(record);
    }

    @Override
    public YdhcVehicleImg selectByPrimaryKey(Integer id) {
        return ydhcVehicleImgDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcVehicleImg record) {
        return ydhcVehicleImgDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcVehicleImg record) {
        return ydhcVehicleImgDao.updateByPrimaryKey(record);
    }

    @Override
    public void batchInsert(List<YdhcVehicleImg> list) {
        ydhcVehicleImgDao.batchInsert(list);
    }

    @Override
    public List<YdhcVehicleImg> selectByVehicleId(Integer vehicleId) {
        return ydhcVehicleImgDao.selectByVehicleId(vehicleId);
    }

    @Override
    public int deleteById(Integer id) {
        return ydhcVehicleImgDao.deleteById(id);
    }
}
