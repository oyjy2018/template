package com.ydc.quartz.service.init;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DdConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.vo.cgj.car.BrandVO;
import com.ydc.commom.view.vo.cgj.car.ModelVO;
import com.ydc.commom.view.vo.cgj.car.SeriesVO;
import com.ydc.dao.cgj.car.VehicleBrandDao;
import com.ydc.dao.cgj.common.DictionaryDao;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.dao.cgj.common.HolidayDao;
import com.ydc.dao.cgj.user.DdDao;
import com.ydc.model.cgj.Dd;
import com.ydc.model.cgj.Dictionary;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.common.Holiday;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 初始化缓存数据
 *
 * @author gongjin
 * @create 2018-09-19 16:30
 **/
@Component
public class InitCache {


    private static final Logger logger = LogManager.getLogger(InitCache.class);

    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @Autowired
    DictionaryDetailDao dictionaryDetailDao;
    @Autowired
    DictionaryDao dictionaryDao;
    @Autowired
    DdDao ddDao;
    @Autowired
    InitScheduler initScheduler;
    @Autowired
    VehicleBrandDao vehicleBrandDao;
    @Autowired
    HolidayDao holidayDao;

    /**
     * 初始化任务
     * @return
     */
    public String initRun(){
        try{
            //初始化定时任务
            initScheduler.initQuartz();
            //初始化数据字典
            this.initDictionary();
            //初始化钉钉配置
            this.initDdConfig();
            //初始化H5数据配置
            this.initH5Config();
            //初始化车管家车辆配置
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    initCGJVehicleBrand();
                }
            });

            return Result.success("初始化任务成功").toJSON();
        }catch (Exception e){
            logger.error("初始化任务异常",e);
            return Result.failure("初始化任务异常").toJSON();
        }

    }

    /**
     * 初始化数据字典
     */
    public void initDictionary(){
        logger.info("开始初始化数据字典");
        try {
            Map<String, List<DictionaryDetail>> dictionaryMaps = findDictionaryDetail().stream().collect(Collectors.groupingBy(DictionaryDetail::getParentDictCode));
            logger.info("subject:{},dictionaryMaps:{}","初始化数据字典",JsonUtil.gsonStr(dictionaryMaps));
            List<Dictionary> dictionaries = dictionaryDao.getValidDictionary();
            logger.info("subject:{},dictionaries:{}","初始化数据字典父类",JsonUtil.gsonStr(dictionaries));
            dictionaries.forEach(item ->{
                List<DictionaryDetail> dictionaryDetails = dictionaryMaps.get(item.getDictCode());
                dictionaryDetails = dictionaryDetails.stream().sorted(Comparator.comparing(DictionaryDetail::getSort)).collect(Collectors.toList());
                if(dictionaryDetails != null && dictionaryDetails.size() > 0){
                    RedisUtil.redisSet(item.getDictCode(), dictionaryDetails, null);
                }
            });
        }catch (Exception e){
            logger.error("初始化数据字典异常",e);
        }
        logger.info("结束初始化数据字典");

    }

    public List<DictionaryDetail> findDictionaryDetail() {
        return dictionaryDetailDao.findDictionaryDetail();
    }

    public List<DictionaryDetail> getH5Config(){
        return dictionaryDetailDao.getH5Config();
    }
    /**
     * 初始化H5配置
     */
    public void initH5Config(){
        logger.info("开始初始化H5配置");
        try {
            Map<String, List<DictionaryDetail>> dictionaryMaps = getH5Config().stream().collect(Collectors.groupingBy(DictionaryDetail::getParentDictCode));
            RedisUtil.redisSet(RedisConstant.H5_CONFIG_KEY,dictionaryMaps,null);
        }catch (Exception e){
            logger.error("初始化H5配置异常",e);
        }
        logger.info("结束初始化H5配置");
    }

    /**
     * 初始化钉钉配置
     */
    public void initDdConfig(){
        logger.info("开始初始化钉钉配置");
        try {
            List<Dd> ddList = getDdConfig();
            Map<String, List<Dd>> ddMaps = ddList.stream().collect(Collectors.groupingBy(Dd::getServiceIdentifying));
            ddList.stream().forEach(item -> RedisUtil.redisSet(DdConstant.DINGTALK_PARAM+item.getServiceIdentifying(),ddMaps.get(item.getServiceIdentifying()),null));
            logger.info("subject:{},ddMaps:{}","初始化钉钉配置",JsonUtil.gsonStr(ddMaps));
        }catch (Exception e){
            logger.error("初始化钉钉配置异常",e);
        }

        logger.info("结束初始化钉钉配置");
    }

    public List<Dd> getDdConfig() {
        return ddDao.getDdConfig();
    }

    /**
     * 车管家车牌信息配置
     */
    public void initCGJVehicleBrand(){
        logger.info("开始车管家初始化车辆品牌");
        try {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info("subject:{}","开始执行车品牌缓存");
                    List<BrandVO> brandVOS = vehicleBrandDao.getBrandVOList();
                    RedisUtil.getRedisTemplate().delete(RedisConstant.CAR_BRAND_KEY);
                    RedisUtil.getRedisTemplate().opsForList().leftPushAll(RedisConstant.CAR_BRAND_KEY,brandVOS);
//                    brandVOS.forEach(item ->{
//                        RedisUtil.getRedisTemplate().opsForList().leftPush(RedisConstant.CAR_BRAND_KEY,item);
//                    });
                    logger.info("subject:{}","结束执行车品牌缓存");
                }
            });

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info("subject:{}","开始执行车系缓存");
                    List<SeriesVO> seriesVOS = vehicleBrandDao.getSeriesVOList();
                    Map<String, List<SeriesVO>> seriesMap = seriesVOS.stream().collect(Collectors.groupingBy(SeriesVO::getBrand));
                    RedisUtil.getRedisTemplate().opsForHash().putAll(RedisConstant.CAR_SERIES_KEY,seriesMap);
//                    for(String key : seriesMap.keySet()){
//                        RedisUtil.getRedisTemplate().opsForHash().put(RedisConstant.CAR_SERIES_KEY,key,seriesMap.get(key));
//                    }
                    logger.info("subject:{}","结束执行车系缓存");
                }
            });

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info("subject:{}","开始执行车型缓存");
                    List<ModelVO> modelVOS = vehicleBrandDao.getModelVOList();
                    Map<String, List<ModelVO>> modelMap = modelVOS.stream().collect(Collectors.groupingBy(ModelVO::getSeries));
                    RedisUtil.getRedisTemplate().opsForHash().putAll(RedisConstant.CAR_MODEL_KEY,modelMap);
//                    for(String key : modelMap.keySet()){
//                        RedisUtil.getRedisTemplate().opsForHash().put(RedisConstant.CAR_MODEL_KEY,key,modelMap.get(key));
//                    }
                    logger.info("subject:{}","结束执行车型缓存");
                }
            });
        }catch (Exception e){
            logger.error("车管家初始化车辆品牌异常",e);
        }
        logger.info("结束车管家初始化车辆品牌");
    }

    /**
     * 初始化节假日配置
     */
    public void initHoliday(){
        logger.info("初始化节假日配置");
        try {
            List<Holiday> holidayList = holidayDao.getAll();
            holidayList.forEach(holiday -> {
                RedisUtil.redisHashPut(RedisConstant.RENTAL_HOLIDAY_KEY, DateUtil.formatDate(holiday.getHolidayDate()), holiday);
            });
            logger.info("subject:{},holidayList:{}","初始化节假日配置",JsonUtil.gsonStr(holidayList));
        }catch (Exception e){
            logger.error("初始化节假日配置",e);
        }

        logger.info("初始化节假日配置");
    }
}
