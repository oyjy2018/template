package com.ydc.service.car.service.impl.ydhc;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.YdhcVehicleUsedConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.dao.ydhc.YdhcVehicleUsedDao;
import com.ydc.dao.ydhc.YdhcVehicleUsedImgDao;
import com.ydc.model.ydhc.YdhcVehicleUsed;
import com.ydc.model.ydhc.YdhcVehicleUsedImg;
import com.ydc.service.car.service.ydhc.VehicleUsedImgService;
import com.ydc.service.car.service.ydhc.VehicleUsedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class VehicleUsedServiceImpl implements VehicleUsedService {
    private static Logger logger = LogManager.getLogger(VehicleUsedServiceImpl.class);

    @Resource
    private YdhcVehicleUsedDao ydhcVehicleUsedDao;
    @Resource
    private YdhcVehicleUsedImgDao ydhcVehicleUsedImgDao;
    @Autowired
    private VehicleUsedImgService vehicleUsedImgService;


    /**
     * 查询二手车信息列表(包括模板，在售，待售)
     *
     * @param param
     * @return
     */
    @Override
    public List<Map<String, Object>> getYdhcVehicleUsedList(Map<String, Object> param) {
        List<Map<String, Object>> vehicleInfoList = ydhcVehicleUsedDao.getYdhcVehicleUsedList(param);
        if (vehicleInfoList != null && vehicleInfoList.size() > 0) {
            for (Map<String, Object> vehicleInfo : vehicleInfoList) {
                try {
                    vehicleInfo.put("viewFileUrl", FileUtil.getBrowseFile(vehicleInfo.get("fileUrl").toString(),
                            vehicleInfo.get("fileName").toString()));
                    vehicleInfo.put("viewLittleFileUrl", FileUtil.getBrowseFile(vehicleInfo.get("littleFileUrl").toString(),
                            vehicleInfo.get("littleFileName").toString()));
                } catch (Exception e) {
                    logger.info("加密图片路径异常", e);
                }
            }
        }
        return vehicleInfoList;
    }

    /**
     * 获取列表总数
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getYdhcVehicleUsedListCount(Map<String, Object> param) {
        return ydhcVehicleUsedDao.getYdhcVehicleUsedListCount(param);
    }

    /**
     * 更新二手车上下架状态
     *
     * @param req
     * @return
     */
    @Override
    public int updateReleaseStatusByIds(Map<String, String> req) {
        Date releaseDate = null;
        Date shelveTime = null;
        String ids = req.get("ids");
        Integer releaseStatus = Integer.valueOf(req.get("releaseStatus"));
        Integer userId = Integer.valueOf(req.get("userId"));
        String userName = req.get("userName");
        if (releaseStatus == 1) {
            releaseDate = new Date();
        } else if (releaseStatus == 2) {
            shelveTime = new Date();
        }
        return ydhcVehicleUsedDao.updateReleaseStatusByIds(releaseStatus, releaseDate, shelveTime, userName, userId, ids);
    }

    @Override
    public List<Map<String, Object>> getVehicleUsedListApp(Map<String, Object> param) {
        List<Integer> vehicleUsedIdList = ydhcVehicleUsedDao.getVehicleUsedListApp(param);
        if (vehicleUsedIdList == null) {
            return null;
        }

        List<Map<String, Object>> vehicleUsedList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> redisVehicleUsed = (Map<String, Object>) RedisUtil.redisHashGet(YdhcVehicleUsedConstant.YDHC_VEHICLE_USED_REDIS_KEY);
        vehicleUsedIdList.forEach(vehicleUsedId -> {
            Map<String, Object> vehicleUsed = (Map<String, Object>) redisVehicleUsed.get(String.valueOf(vehicleUsedId));
            if (vehicleUsed == null) {
                vehicleUsed = getVehicleUsedInfoApp(vehicleUsedId);
                map.put(String.valueOf(vehicleUsedId), vehicleUsed);
            }
            vehicleUsedList.add(vehicleUsed);
        });

        //更新缓存
        if (map.size() > 0) {
            RedisUtil.redisHashPut(YdhcVehicleUsedConstant.YDHC_VEHICLE_USED_REDIS_KEY, map);
        }
        return vehicleUsedList;
    }

    @Override
    public Map<String, Object> getVehicleUsedDetailApp(Integer vehicleUsedId) {
        if (vehicleUsedId == null) return null;
        //从缓存中取，缓存中有就直接返回
        Map<String, Object> vehicleUsed = (Map<String, Object>) RedisUtil.redisHashGet(YdhcVehicleUsedConstant.YDHC_VEHICLE_USED_REDIS_KEY, String.valueOf(vehicleUsedId));
        if (vehicleUsed != null) return vehicleUsed;
        //缓存中没有，就从数据库中取
        vehicleUsed = getVehicleUsedInfoApp(vehicleUsedId);
        if (vehicleUsed != null) {
            RedisUtil.redisHashPut(YdhcVehicleUsedConstant.YDHC_VEHICLE_USED_REDIS_KEY, String.valueOf(vehicleUsedId), vehicleUsed);
        }
        return vehicleUsed;
    }

    /**
     * 封装二手车相关信息
     *
     * @param vehicleUsedId
     * @return
     */
    private Map<String, Object> getVehicleUsedInfoApp(Integer vehicleUsedId) {
        Map<String, Object> vehicleUsed = ydhcVehicleUsedDao.getVehicleUsedDetailApp(vehicleUsedId);
        List<YdhcVehicleUsedImg> vehicleUserImgList = ydhcVehicleUsedImgDao.selectByVehicleId(vehicleUsedId);
        if (vehicleUserImgList != null) {
            List<Map<String, Object>> mainImgList = new ArrayList<>();//主图
            List<Map<String, Object>> carouselImgList = new ArrayList<>();//轮播图
            List<Map<String, Object>> liveShotImgList = new ArrayList<>();//实拍图
            vehicleUserImgList.forEach(img -> {
                Map<String, Object> imgMap = new HashMap<>();
                imgMap.put("imgType", img.getImgType());
                imgMap.put("describeType", img.getDescribeType());
                imgMap.put("imgDescribe", img.getImgDescribe());
                try {
                    imgMap.put("viewFileUrl", FileUtil.getBrowseFile(img.getFileUrl(), img.getFileName()));
                    imgMap.put("viewLittleFileUrl", FileUtil.getBrowseFile(img.getLittleFileUrl(), img.getLittleFileName()));
                } catch (Exception e) {
                    logger.error("加密图片访问地址异常", e);
                }
                if (YdhcVehicleUsedConstant.VEHICLE_USED_IMG_TYPE_1 == img.getImgType()) {
                    mainImgList.add(imgMap);
                }
                if (YdhcVehicleUsedConstant.VEHICLE_USED_IMG_TYPE_1 != img.getImgType()) {
                    liveShotImgList.add(imgMap);
                }
                if (YdhcVehicleUsedConstant.VEHICLE_USED_IMG_TYPE_1 == img.getImgType()
                        || YdhcVehicleUsedConstant.VEHICLE_USED_IMG_TYPE_2 == img.getImgType()) {
                    //多个集合引用同一个对象，会引发fastjson内存对象重复引用的json错误
                    carouselImgList.add((Map<String, Object>) ((HashMap<String, Object>) imgMap).clone());
                }
            });
            vehicleUsed.put("mainImgList", mainImgList);
            vehicleUsed.put("carouselImgList", carouselImgList);
            vehicleUsed.put("liveShotImgList", liveShotImgList);
        }
        return vehicleUsed;
    }

    /**
     * 查询二手车信息
     *
     * @param req
     * @return
     */
    @Override
    public String getVehicleUsedInfo(Map<String, String> req) {
        YdhcVehicleUsed vehicle = ydhcVehicleUsedDao.selectByPrimaryKey(Integer.valueOf(req.get("vehicleId")));
        if (vehicle == null) {
            return Result.failure("二手车信息不存在或丢失").toJSON();
        }
        vehicle.setPrice(vehicle.getPrice().divide(BigDecimal.valueOf(10000), BigDecimal.ROUND_HALF_UP, 2));
        vehicle.setNewCarPrice(vehicle.getNewCarPrice().divide(BigDecimal.valueOf(10000), BigDecimal.ROUND_HALF_UP, 2));
        List<YdhcVehicleUsedImg> vehicleImgList = vehicleUsedImgService.selectByVehicleId(vehicle.getId());
        for (YdhcVehicleUsedImg vehicleImg : vehicleImgList) {
            try {
                vehicleImg.setViewFileUrl(FileUtil.getBrowseFile(vehicleImg.getFileUrl(), vehicleImg.getFileName()));
                vehicleImg.setViewLittleFileUrl(FileUtil.getBrowseFile(vehicleImg.getLittleFileUrl(), vehicleImg.getLittleFileName()));
            } catch (Exception e) {
                logger.info("加密图片路径异常", e);
            }
        }
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("vehicleUsed", vehicle);
        jMap.put("vehicleUsedImgList", vehicleImgList);
        return Result.success(jMap).toJSON();
    }

    @Override
    public YdhcVehicleUsed selectByPrimaryKey(Integer id) {
        return ydhcVehicleUsedDao.selectByPrimaryKey(id);
    }

    /**
     * 保存或更新二手车信息
     *
     * @param req
     * @return
     */
    @Override
    public Integer saveOrUpdateVehicleUsed(Map<String, Object> req) {
        Map<String, Object> ydhcVehicleUsedMap =(Map<String, Object>) req.get("ydhcVehicleUsed");
        YdhcVehicleUsed ydhcVehicle = JsonUtil.jsonToBean(JsonUtil.jsonStr(ydhcVehicleUsedMap),YdhcVehicleUsed.class);
        List<YdhcVehicleUsedImg> ydhcVehicleImgList = new ArrayList<>();
        List<Map<String, Object>> ydhcVehicleImgs = null;
        if (req.get("ydhcVehicleUsedImgs") != null) {
            ydhcVehicleImgs = (List<Map<String, Object>>) req.get("ydhcVehicleUsedImgs");
            for (Map<String, Object> ydhcVehicleImg : ydhcVehicleImgs) {
                if (ydhcVehicleImg == null || ydhcVehicleImg.size() == 0) {
                    continue;
                }
                ydhcVehicleImgList.add(new YdhcVehicleUsedImg(ydhcVehicleImg));
            }
        }

        Integer userId = Integer.valueOf(req.get("userId").toString());
        String userName = req.get("userName").toString();
        Date sysDate = new Date();

        //将单位万元转化为元
        ydhcVehicle.setPrice(ydhcVehicle.getPrice().multiply(BigDecimal.valueOf(10000)));
        ydhcVehicle.setNewCarPrice(ydhcVehicle.getNewCarPrice().multiply(BigDecimal.valueOf(10000)));
        if (ydhcVehicle.getId() == null || ydhcVehicle.getId() == 0) {//新增
            logger.info("新增车辆信息");
            ydhcVehicle.setReleaseStatus(1);
            ydhcVehicle.setReleaseDate(sysDate);
            ydhcVehicle.setReleasePerson(userName);
            ydhcVehicle.setReleasePersonId(userId);
            ydhcVehicle.setCreateBy(userId);
            ydhcVehicle.setCreateTime(sysDate);
            ydhcVehicle.setUpdateBy(userId);
            ydhcVehicle.setUpdateTime(sysDate);
            ydhcVehicleUsedDao.insert(ydhcVehicle);
            for (YdhcVehicleUsedImg ydhcVehicleUsedImg : ydhcVehicleImgList) {
                ydhcVehicleUsedImg.setVehicleId(ydhcVehicle.getId());
                ydhcVehicleUsedImg.setCreateBy(userId);
                ydhcVehicleUsedImg.setCreateTime(sysDate);
                ydhcVehicleUsedImg.setStatus(1);
            }
            ydhcVehicleUsedImgDao.batchInsert(ydhcVehicleImgList);
        } else {//修改
            logger.info("修改车辆信息");
            ydhcVehicle.setUpdateTime(sysDate);
            ydhcVehicle.setUpdateBy(userId);
            List<YdhcVehicleUsedImg> newVehicleUsedImgList = new ArrayList<>();  //新提交的图片集合
            List<YdhcVehicleUsedImg> oldVehicleUsedImgList = new ArrayList<>();  //提交的原先存在的图片集合  需要更新
            logger.info("ydhcVehicleImgList.size()=" + ydhcVehicleImgList.size());
            if (ydhcVehicleImgList.size() > 0) {
                for (YdhcVehicleUsedImg ydhcVehicleUsedImg : ydhcVehicleImgList) {
                    if (!StringUtil.isEmpty(ydhcVehicleUsedImg.getId())) {  //存在id的为老图片
                        oldVehicleUsedImgList.add(ydhcVehicleUsedImg);
                        continue;
                    }
                    ydhcVehicleUsedImg.setVehicleId(ydhcVehicle.getId());
                    ydhcVehicleUsedImg.setCreateBy(userId);
                    ydhcVehicleUsedImg.setCreateTime(sysDate);
                    ydhcVehicleUsedImg.setStatus(1);
                    newVehicleUsedImgList.add(ydhcVehicleUsedImg);//加入新图片集合
                }
                logger.info("newVehicleUsedImgList.size()=" + newVehicleUsedImgList.size());
                if (newVehicleUsedImgList.size() > 0) {
                    ydhcVehicleUsedImgDao.batchInsert(newVehicleUsedImgList);//将新图片入库
                }
                logger.info("oldVehicleUsedImgList.size()=" + oldVehicleUsedImgList.size());
                if (oldVehicleUsedImgList.size() > 0) {
                    ydhcVehicleUsedImgDao.batchUpdateImgDescribe(oldVehicleUsedImgList);//将老图片更新
                }
            }

            //删除被删除的老图片
            if (StringUtil.isNotEmpty(req.get("vehicleUsedImgIds"))) {
                String vehicleUsedImgIds = req.get("vehicleUsedImgIds").toString();
                logger.info("删除ID = " + vehicleUsedImgIds);
                if (vehicleUsedImgIds.length() > 0)
                    ydhcVehicleUsedImgDao.deleteByVehicleImgIds(vehicleUsedImgIds);
            }
            int updateResult = updateByPrimaryKeySelective(ydhcVehicle);
            if(updateResult > 0){
                RedisUtil.redisHashDel(YdhcVehicleUsedConstant.YDHC_VEHICLE_USED_REDIS_KEY, String.valueOf(ydhcVehicle.getId()));
            }
        }
        return ydhcVehicle.getId();
    }

    /**
     * 根据标题查询二手车
     *
     * @param title
     * @return
     */
    @Override
    public YdhcVehicleUsed selectByTitle(String title) {
        return ydhcVehicleUsedDao.selectByTitle(title);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcVehicleUsed record) {
        return ydhcVehicleUsedDao.updateByPrimaryKeySelective(record);
    }
}
