package com.ydc.service.car.service.impl.ydhc;

import com.ydc.commom.util.StringUtil;
import com.ydc.dao.ydhc.YdhcVehicleBrandDao;
import com.ydc.dao.ydhc.YdhcVehicleModelDao;
import com.ydc.dao.ydhc.YdhcVehicleSeriesDao;
import com.ydc.model.ydhc.YdhcVehicleBrand;
import com.ydc.service.car.service.ydhc.YdhcVehicleBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class YdhcVehicleBrandServiceImpl implements YdhcVehicleBrandService {

    @Autowired
    private YdhcVehicleBrandDao ydhcVehicleBrandDao;
    @Autowired
    private YdhcVehicleSeriesDao ydhcVehicleSeriesDao;
    @Autowired
    private YdhcVehicleModelDao ydhcVehicleModelDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ydhcVehicleBrandDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YdhcVehicleBrand record) {
        return ydhcVehicleBrandDao.insert(record);
    }

    @Override
    public int insertSelective(YdhcVehicleBrand record) {
        return ydhcVehicleBrandDao.insertSelective(record);
    }

    @Override
    public YdhcVehicleBrand selectByPrimaryKey(Integer id) {
        return ydhcVehicleBrandDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcVehicleBrand record) {
        return ydhcVehicleBrandDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcVehicleBrand record) {
        return ydhcVehicleBrandDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> getAllBrand() {
        return ydhcVehicleBrandDao.getAllBrand();
    }

    @Override
    public List<Map<String, Object>> getSeriesByBrand(Map<String, Object> req) {
        String brand = StringUtil.isEmpty(req.get("brand"))?"":(String) req.get("brand");
        return ydhcVehicleSeriesDao.getSeriesByBrand(brand);
    }

    @Override
    public List<Map<String, Object>> getVersionBySeries(Map<String, Object> req) {
        String series = StringUtil.isEmpty(req.get("series"))?"":(String) req.get("series");
        return ydhcVehicleModelDao.getVersionBySeries(series);
    }
}
