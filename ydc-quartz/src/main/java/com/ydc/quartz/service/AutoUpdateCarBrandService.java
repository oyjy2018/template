package com.ydc.quartz.service;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.util.HttpUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.dao.ydhc.YdhcVehicleBrandDao;
import com.ydc.dao.ydhc.YdhcVehicleModelDao;
import com.ydc.dao.ydhc.YdhcVehicleSeriesDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("autoUpdateCarBrandService")
public class AutoUpdateCarBrandService {

    private static final Logger logger = LogManager.getLogger(AutoUpdateCarBrandService.class);

    @Value("${spi.api.service.url}")
    private String SPI_API_SERVICE_URL;

    @Autowired
    private YdhcVehicleBrandDao ydhcVehicleBrandDao;
    @Autowired
    private YdhcVehicleSeriesDao ydhcVehicleSeriesDao;
    @Autowired
    private YdhcVehicleModelDao ydhcVehicleModelDao;

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    public void job() throws Exception{
        //获取车辆品牌
        String ret = HttpUtil.get(SPI_API_SERVICE_URL+"/vehicle/brand/all");
        if(StringUtil.isEmpty(ret) || !ret.startsWith("[")) {
            logger.error("获取车辆品牌失败，错误："+ret);
            return;
        }
        List<Map<String, Object>> brandList = JsonUtil.jsonToListMap(ret);
        brandList.forEach(brand -> {
            if(brand.containsKey("icon") && brand.get("icon") != null){
                String icon = brand.get("icon").toString();
                try {
                    brand.put("icon", FileUtil.getBrowseFile(icon.substring(0,icon.lastIndexOf("/")), icon.substring(icon.lastIndexOf("/")+1,icon.length())));
                } catch (Exception e) {
                    logger.error("车辆品牌【"+brand.get("brand").toString()+"】图标地址加密异常",e);
                }
            }
        });

        //获取车辆车系
        ret = HttpUtil.get("http://39.108.124.252:8686/spi_api"+"/vehicle/series/all");
        if(StringUtil.isEmpty(ret) || !ret.startsWith("[")) {
            logger.error("获取车辆车系失败，错误："+ret);
            return;
        }
        List<Map<String, Object>> seriesList = JsonUtil.jsonToListMap(ret);

        //获取车辆车型
        ret = HttpUtil.get("http://39.108.124.252:8686/spi_api"+"/vehicle/model/all");
        if(StringUtil.isEmpty(ret) || !ret.startsWith("[")) {
            logger.error("获取车辆车型失败，错误："+ret);
            return;
        }
        List<Map<String, Object>>modelList = JsonUtil.jsonToListMap(ret);

        ydhcVehicleBrandDao.batchInsert(brandList);
        ydhcVehicleSeriesDao.batchInsert(seriesList);
        ydhcVehicleModelDao.batchInsert(modelList);

        List<Map<String, Object>> newBrandList = ydhcVehicleBrandDao.getBrandList();
        RedisUtil.redisSet(RedisConstant.YDHC_CAR_BRAND_KEY,newBrandList,null);
        List<Map<String, Object>> newSeriesList = ydhcVehicleSeriesDao.getSeriesList();
        Map<String, List<Map<String, Object>>> newSeries = new HashMap<>();
        newSeriesList.forEach(series -> {
            List<Map<String, Object>> temp = newSeries.get(series.get("brand").toString());
            if(temp == null){
                temp = new ArrayList<>();
            }
            temp.add(series);
            newSeries.put(series.get("brand").toString(), temp);
        });
        RedisUtil.redisSet(RedisConstant.YDHC_CAR_SERIES_KEY, newSeries, null);
    }

    public static void main(String[] args) {
        String s = "/usr/local/upload/carIcon/03386801-ca23-481e-85dc-d9459ee866cd_.png";
       logger.info(s.substring(0,s.lastIndexOf("/")));
    }
}
