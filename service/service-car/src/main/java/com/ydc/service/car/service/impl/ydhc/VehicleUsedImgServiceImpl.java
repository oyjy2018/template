package com.ydc.service.car.service.impl.ydhc;

import com.ydc.dao.ydhc.YdhcVehicleUsedImgDao;
import com.ydc.model.ydhc.YdhcVehicleUsedImg;
import com.ydc.service.car.service.ydhc.VehicleUsedImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleUsedImgServiceImpl implements VehicleUsedImgService {

    @Autowired
    private YdhcVehicleUsedImgDao ydhcVehicleUsedImgDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ydhcVehicleUsedImgDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YdhcVehicleUsedImg record) {
        return ydhcVehicleUsedImgDao.insert(record);
    }

    @Override
    public int insertSelective(YdhcVehicleUsedImg record) {
        return ydhcVehicleUsedImgDao.insertSelective(record);
    }

    @Override
    public YdhcVehicleUsedImg selectByPrimaryKey(Integer id) {
        return ydhcVehicleUsedImgDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcVehicleUsedImg record) {
        return ydhcVehicleUsedImgDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcVehicleUsedImg record) {
        return ydhcVehicleUsedImgDao.updateByPrimaryKey(record);
    }

    @Override
    public void batchInsert(List<YdhcVehicleUsedImg> list) {
        ydhcVehicleUsedImgDao.batchInsert(list);
    }

    @Override
    public List<YdhcVehicleUsedImg> selectByVehicleId(Integer vehicleId) {
        return ydhcVehicleUsedImgDao.selectByVehicleId(vehicleId);
    }

    @Override
    public int deleteById(Integer id) {
        return ydhcVehicleUsedImgDao.deleteById(id);
    }
}
