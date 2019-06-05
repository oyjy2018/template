package com.ydc.service.car.service.impl.ydhc;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.YdhcVehicleConstant;
import com.ydc.commom.util.ObjectUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.ydhc.VehicleDto;
import com.ydc.commom.view.vo.ydhc.VehicleVo;
import com.ydc.commom.view.vo.ydhc.YdhcVehicleVO;
import com.ydc.dao.ydhc.YdhcVehicleDao;
import com.ydc.dao.ydhc.YdhcVehicleImgDao;
import com.ydc.model.ydhc.YdhcVehicle;
import com.ydc.model.ydhc.YdhcVehicleImg;
import com.ydc.service.car.service.ydhc.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class VehicleServiceImpl implements VehicleService {
    private static Logger logger = LogManager.getLogger(VehicleServiceImpl.class);

    @Autowired
    private YdhcVehicleDao ydhcVehicleDao;
    @Autowired
    private YdhcVehicleImgDao ydhcVehicleImgDao;

    @Override
    public int insert(YdhcVehicle record) {
        return ydhcVehicleDao.insert(record);
    }

    @Override
    public int insertSelective(YdhcVehicle record) {
        return ydhcVehicleDao.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(YdhcVehicle record) {
        return ydhcVehicleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer saveOrUpdateVehicle(Map<String, Object> req) {
        YdhcVehicle ydhcVehicle = new YdhcVehicle((Map<String, String>)req.get("ydhcVehicle"));
        List<YdhcVehicleImg> ydhcVehicleImgList = new ArrayList<>();
        List<Map<String, String>> ydhcVehicleImgs = null;
        if(req.get("ydhcVehicleImgs") != null){
            ydhcVehicleImgs = (List<Map<String, String>>)req.get("ydhcVehicleImgs");
            for(Map<String, String> ydhcVehicleImg:ydhcVehicleImgs){
                ydhcVehicleImgList.add(new YdhcVehicleImg(ydhcVehicleImg));
            }
        }

        Integer userId = Integer.valueOf(req.get("userId").toString());
        String userName = req.get("userName").toString();
        Date sysDate = new Date();

        //将单位万元转化为元
        ydhcVehicle.setPrice(ydhcVehicle.getPrice().multiply(BigDecimal.valueOf(10000)));
        if(ydhcVehicle.getId() == null || ydhcVehicle.getId() == 0){//新增
            logger.info("新增车辆信息");
            ydhcVehicle.setReleaseStatus(1);
            ydhcVehicle.setReleaseDate(sysDate);
            ydhcVehicle.setReleasePerson(userName);
            ydhcVehicle.setReleasePersonId(userId);
            ydhcVehicle.setCreateBy(userId);
            ydhcVehicle.setCreateTime(sysDate);
            ydhcVehicle.setUpdateBy(userId);
            ydhcVehicle.setUpdateTime(sysDate);
            ydhcVehicleDao.insert(ydhcVehicle);
            for(YdhcVehicleImg ydhcVehicleImg:ydhcVehicleImgList){
                ydhcVehicleImg.setVehicleId(ydhcVehicle.getId());
                ydhcVehicleImg.setCreateBy(userId);
                ydhcVehicleImg.setCreateTime(sysDate);
                ydhcVehicleImg.setStatus(1);
            }
            ydhcVehicleImgDao.batchInsert(ydhcVehicleImgList);
        }else{//修改
            logger.info("修改车辆信息");
            ydhcVehicle.setUpdateTime(sysDate);
            ydhcVehicle.setUpdateBy(userId);

            if(ydhcVehicleImgList.size() > 0){
                for(YdhcVehicleImg ydhcVehicleImg:ydhcVehicleImgList){
                    ydhcVehicleImg.setVehicleId(ydhcVehicle.getId());
                    ydhcVehicleImg.setCreateBy(userId);
                    ydhcVehicleImg.setCreateTime(sysDate);
                    ydhcVehicleImg.setStatus(1);
                }
                ydhcVehicleImgDao.batchInsert(ydhcVehicleImgList);
            }
            if(StringUtil.isNotEmpty(req.get("vehicleImgIds"))){
                String vehicleImgIds = req.get("vehicleImgIds").toString();
                if(vehicleImgIds.length() > 0)
                ydhcVehicleImgDao.deleteByVehicleImgIds(vehicleImgIds);
            }
            int updateResult = updateByPrimaryKeySelective(ydhcVehicle);

            //删除缓存中的车辆
            if (updateResult > 0) {
                RedisUtil.redisHashDel(YdhcVehicleConstant.YDHC_VEHICLE_REDIS_KEY, String.valueOf(ydhcVehicle.getId()));
            }
        }
        return ydhcVehicle.getId();
    }

    @Override
    public int updateReleaseStatusByIds(Map<String, String> req) {
        Date releaseDate = null;
        Date shelveTime = null;
        String vehicleIds = req.get("vehicleIds");
        Integer releaseStatus = Integer.valueOf(req.get("releaseStatus"));
        Integer userId = Integer.valueOf(req.get("userId"));
        String userName = req.get("userName");
        if (releaseStatus == 1) {
            releaseDate = new Date();
        } else if (releaseStatus == 2) {
            shelveTime = new Date();
        }
        return ydhcVehicleDao.updateReleaseStatusByIds(releaseStatus,releaseDate,shelveTime,userName,userId,vehicleIds);
    }

    @Override
    public YdhcVehicle selectByPrimaryKey(Integer id) {
        return ydhcVehicleDao.selectByPrimaryKey(id);
    }

    @Override
    public YdhcVehicle selectByTitle(String title) {
        return ydhcVehicleDao.selectByTitle(title);
    }

    @Override
    public List<YdhcVehicleVO> getYdhcVehicleList(Map<String, Object> param) {
        List<YdhcVehicleVO> vehicleInfoList = ydhcVehicleDao.getYdhcVehicleList(param);
        if(vehicleInfoList != null && vehicleInfoList.size() > 0){
            for(YdhcVehicleVO vehicleInfo:vehicleInfoList){
                try {
                    vehicleInfo.setViewFileUrl(FileUtil.getBrowseFile(vehicleInfo.getFileUrl(), vehicleInfo.getFileName()));
                    vehicleInfo.setViewLittleFileUrl(FileUtil.getBrowseFile(vehicleInfo.getLittleFileUrl(),
                            vehicleInfo.getLittleFileName()));
                } catch (Exception e) {
                    logger.info("加密图片路径异常",e);
                }
            }
        }
        return vehicleInfoList;
    }

    @Override
    public Map<String, Object> getYdhcVehicleListCount(Map<String, Object> param) {
        return ydhcVehicleDao.getYdhcVehicleListCount(param);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<VehicleDto> getVehicleList(VehicleVo vehicleVo) {
        List<Integer> vehicleIds = ydhcVehicleDao.getVehicleIdList(vehicleVo);
        if (vehicleIds == null){
            return null;
        }
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(); //存入缓存的map
        Map<String, VehicleDto> redisCommodity = (Map<String, VehicleDto>)RedisUtil.redisHashGet(YdhcVehicleConstant.YDHC_VEHICLE_REDIS_KEY); //redis中的商品
        vehicleIds.forEach(vehicleId -> {
            VehicleDto vehicleDto = redisCommodity.get(String.valueOf(vehicleId));
            //缓存没有就从数据库里取
            if (vehicleDto == null) {
                vehicleDto = getVehicleDtoDetail(vehicleId);
                map.put(String.valueOf(vehicleId), vehicleDto);
            }
            vehicleDtos.add(vehicleDto);
        });
        //更新缓存
        if (map.size() > 0) {
            RedisUtil.redisHashPut(YdhcVehicleConstant.YDHC_VEHICLE_REDIS_KEY, map);
        }
        return vehicleDtos;
    }

    @Override
    public VehicleDto getVehicleDetail(Integer vehicleId) {
        if (vehicleId == null){
            return null;
        }
        //从缓存中取，缓存中有就直接返回
        VehicleDto vehicleDto = (VehicleDto) RedisUtil.redisHashGet(YdhcVehicleConstant.YDHC_VEHICLE_REDIS_KEY, String.valueOf(vehicleId));
        if (vehicleDto != null){
            return vehicleDto;
        }
        //缓存中没有，就从数据库中取
        vehicleDto = getVehicleDtoDetail(vehicleId);
        if(vehicleDto != null){
            RedisUtil.redisHashPut(YdhcVehicleConstant.YDHC_VEHICLE_REDIS_KEY, String.valueOf(vehicleId), vehicleDto);
        }
        return vehicleDto;
    }

    //获取数据库中的车辆信息
    private VehicleDto getVehicleDtoDetail(Integer vehicleId){
        YdhcVehicle ydhcVehicle = ydhcVehicleDao.selectByPrimaryKey(vehicleId);
        if (ydhcVehicle == null){
            return null;
        }
        VehicleDto vehicleDto = new VehicleDto();
        ObjectUtil.copyObject(ydhcVehicle, vehicleDto);
        //车辆图片
        List<YdhcVehicleImg> vehicleImgs = ydhcVehicleImgDao.selectByVehicleId(vehicleId);
        if (vehicleImgs != null){
            vehicleDto.setMainPic(getMapPic(vehicleImgs, YdhcVehicleConstant.VEHICLE_IMG_TYPE_1));
            vehicleDto.setDescriptionPic(getMapPic(vehicleImgs, YdhcVehicleConstant.VEHICLE_IMG_TYPE_2));
        }
        return vehicleDto;
    }

    //车辆图片的路径处理
    private List<Map<String, String>> getMapPic(List<YdhcVehicleImg> vehicleImgs, int imgType) {
        List<Map<String, String>> list = new ArrayList<>();
        vehicleImgs.stream()
                .filter(vehicleImg -> imgType == vehicleImg.getImgType())
                .forEach(vehicleImg -> {
                    try {
                        Map<String, String> map = new HashMap<>();
                        map.put(YdhcVehicleConstant.VEHICLE_IMG_NORMAL, FileUtil.getBrowseFile(vehicleImg.getFileUrl(), vehicleImg.getFileName()));
                        map.put(YdhcVehicleConstant.VEHICLE_IMG_LITTLE, FileUtil.getBrowseFile(vehicleImg.getLittleFileUrl(), vehicleImg.getLittleFileName()));
                        list.add(map);
                    }catch (Exception e){
                        logger.error("获取图片在线浏览地址异常", e);
                    }
                });
        return list;
    }
}
