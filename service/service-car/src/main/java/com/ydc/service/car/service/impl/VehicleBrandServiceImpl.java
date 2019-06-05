package com.ydc.service.car.service.impl;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.view.vo.cgj.car.BrandVO;
import com.ydc.commom.view.vo.cgj.car.ModelVO;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import com.ydc.dao.cgj.car.VehicleBrandDao;
import com.ydc.model.ydhc.YdhcVehicleBrand;
import com.ydc.service.car.service.VehicleBrandService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class VehicleBrandServiceImpl implements VehicleBrandService {

    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private static final Logger logger = LogManager.getLogger(VehicleBrandService.class);

    @Autowired
    private VehicleBrandDao vehicleBrandDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return vehicleBrandDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YdhcVehicleBrand record) {
        return vehicleBrandDao.insert(record);
    }

    @Override
    public void batchInsert(List<Map<String, Object>> list) {
        vehicleBrandDao.batchInsert(list);
    }

    @Override
    public int insertSelective(YdhcVehicleBrand record) {
        return vehicleBrandDao.insertSelective(record);
    }

    @Override
    public YdhcVehicleBrand selectByPrimaryKey(Integer id) {
        return vehicleBrandDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcVehicleBrand record) {
        return vehicleBrandDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(YdhcVehicleBrand record) {
        return vehicleBrandDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> getAllBrand() {
        return vehicleBrandDao.getAllBrand();
    }

    @Override
    public List<Map<String, Object>> getAllBrandCH() {
        return vehicleBrandDao.getAllBrandCH();
    }

    @Override
    public List<Map<String, Object>> getBrandList() {
        return vehicleBrandDao.getBrandList();
    }

    @Override
    public List<BrandVO> getBrandVOList() {
        List<BrandVO> brandVOS = vehicleBrandDao.getBrandVOList();
        try{
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    RedisUtil.getRedisTemplate().delete(RedisConstant.CAR_BRAND_KEY);
                    RedisUtil.getRedisTemplate().opsForList().leftPushAll(RedisConstant.CAR_BRAND_KEY,brandVOS);
                }
            });
        }catch (Exception e){
            logger.error("subject:{},e:{}","执行车品牌缓存异常",e);
        }
        return brandVOS;
    }

    @Override
    public Map<String, List<SeriesVO>> getSeriesVOList() {
        List<SeriesVO> seriesVOS = vehicleBrandDao.getSeriesVOList();
        Map<String, List<SeriesVO>> seriesMap = seriesVOS.stream().collect(Collectors.groupingBy(SeriesVO::getBrand));
        try{
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    RedisUtil.getRedisTemplate().opsForHash().putAll(RedisConstant.CAR_SERIES_KEY,seriesMap);
                }
            });
        }catch (Exception e){
            logger.error("subject:{},e:{}","执行车系缓存异常",e);
        }
        return seriesMap;
    }

    @Override
    public List<ModelVO> getModelVOList(String series) {
        List<ModelVO> modelVOS = vehicleBrandDao.getModelVOList();
        Map<String, List<ModelVO>> modelMap = modelVOS.stream().collect(Collectors.groupingBy(ModelVO::getSeries));
        try{
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    RedisUtil.getRedisTemplate().opsForHash().put(RedisConstant.CAR_MODEL_KEY,series,modelMap.get(series));
                }
            });
        }catch (Exception e){
            logger.error("subject:{},e:{}","执行车型缓存异常",e);
        }
        return modelMap.get(series);
    }

}
