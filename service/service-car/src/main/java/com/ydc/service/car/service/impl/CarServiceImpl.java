package com.ydc.service.car.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.commom.enums.common.CommonEnum;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.CommCarQueryDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateOperationStatusDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarUpdateUseStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.CommCarQueryVO;
import com.ydc.commom.view.vo.cgj.rental.CommCarSimpleVO;
import com.ydc.dao.cgj.car.CommCarDao;
import com.ydc.dao.cgj.car.CommCarImgDao;
import com.ydc.dao.cgj.car.CommCarOperLogDao;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.car.CommCarImg;
import com.ydc.model.cgj.car.CommCarOperLog;
import com.ydc.service.car.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    @Resource
    private CommCarDao commCarDao;
    @Resource
    private CommCarImgDao commCarImgDao;
    @Resource
    private CommCarOperLogDao commCarOperLogDao;

    private Logger logger = LogManager.getLogger(CarServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "cgjTransactionManager")
    public Integer saveOrUpdateCar(Map<String, Object> req) {
        //CommCar car = new CommCar((Map<String, Object>) req.get("car"));
        CommCar car = JsonUtil.jsonToBean(JsonUtil.jsonToStr(req.get("car")),CommCar.class);
        List<CommCarImg> carImgList = new ArrayList<>();
        List<Map<String, Object>> carImgs = null;
        if (req.get("carImgs") != null) {
            carImgs = (List<Map<String, Object>>) req.get("carImgs");
            for (Map<String, Object> carImg : carImgs) {
                carImgList.add(new CommCarImg(carImg));
            }
        }
        Integer userId = Integer.valueOf(req.get("userId").toString());
        Date sysDate = new Date();

        if (car.getId() == null) {//新增
            logger.info("新增车辆信息");

            car.setUseStatus(String.valueOf(CommonEnum.DeleteStatusEnum.STATUS_0.getCode()));  //启用状态 默认为禁用
            car.setOperationStatus(String.valueOf(CommCarEnum.CommCarOperationStatusEnum.OPERATION_STATUS_0.getCode())); //运营状态默认为待检
            car.setTurnOutType(CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_0.getCode());  //出车类型 默认为未出车
            car.setCreateBy(userId);
            car.setCreateTime(sysDate);
            car.setUpdateBy(userId);
            car.setUpdateTime(sysDate);
            //车辆新增操作
            commCarDao.insert(car);

            //车辆图片
            for (CommCarImg carImg : carImgList) {
                carImg.setCarId(car.getId());
                carImg.setCreateBy(userId);
                carImg.setCreateTime(sysDate);
                carImg.setStatus(1);
            }
            commCarImgDao.batchInsert(carImgList);

            //操作日志
            CommCarOperLog commCarOperLog = new CommCarOperLog();
            commCarOperLog.setCarId(car.getId());
            commCarOperLog.setOperContent("新增了车辆");
            commCarOperLog.setCreateBy(userId);
            commCarOperLog.setCreateTime(new Date());
            commCarOperLog.setUpdateBy(userId);
            commCarOperLog.setUpdateTime(new Date());
            commCarOperLog.setDeleteStatus(1);

            commCarOperLogDao.insert(commCarOperLog);
        } else {//修改
            logger.info("修改车辆信息");
            car.setUpdateTime(sysDate);
            car.setUpdateBy(userId);
            List<CommCarImg> newCommCarImgList = new ArrayList<>();  //新提交的图片集合
            logger.info("carImgList.size()=" + carImgList.size());
            StringBuffer keepOldImgIdsSb = new StringBuffer();
            if (carImgList.size() > 0) {
                for (CommCarImg carImg : carImgList) {
                    if (!StringUtil.isEmpty(carImg.getId())) {  //存在id的为老图片 记录下来;
                        keepOldImgIdsSb.append(carImg.getId()).append(",");
                        continue;
                    }
                    carImg.setCarId(car.getId());
                    carImg.setCreateBy(userId);
                    carImg.setCreateTime(sysDate);
                    carImg.setStatus(1);
                    newCommCarImgList.add(carImg);//加入新图片集合
                }
            }

            int updateResult = commCarDao.updateByPrimaryKey(car); //更新车辆信息

           /* //删除被删除的老图片
            if (StringUtil.isNotEmpty(req.get("carImgIds"))) {
                String carImgIds = req.get("carImgIds").toString();
                logger.info("删除ID = " + carImgIds);
                if (carImgIds.length() > 0)
                    commCarImgDao.deleteByCarImgIds(carImgIds);
            }*/
            //删除 没有提交的老照片（软删）
            logger.info("subject:{},keepOldImgIdsSb:{}", "删除没有提交的老照片（软删）", keepOldImgIdsSb);
            String keepOldImgIds = keepOldImgIdsSb.toString();
            logger.info("subject:{},keepOldImgIds:{}", "删除没有提交的老照片（软删）", keepOldImgIds);
            if (StringUtil.isNotEmpty(keepOldImgIds) && keepOldImgIds.length() > 1) {
                keepOldImgIds = keepOldImgIds.substring(0, keepOldImgIds.length() - 1); //去除最后的逗号
            }
            Map param = new HashMap();
            param.put("carId", car.getId());
            param.put("carImgIds", keepOldImgIds);
            int effect = commCarImgDao.deleteByCarIdAndNotCarImgIds(param);
            logger.info("软删照片" + effect + "张");

            logger.info("newCommCarImgList.size()=" + newCommCarImgList.size());
            if (newCommCarImgList.size() > 0) {
                commCarImgDao.batchInsert(newCommCarImgList);//将新图片入库
            }
            //操作日志
            CommCarOperLog commCarOperLog = new CommCarOperLog();
            commCarOperLog.setCarId(car.getId());
            commCarOperLog.setOperContent("编辑了车辆");
            commCarOperLog.setCreateBy(userId);
            commCarOperLog.setCreateTime(new Date());
            commCarOperLog.setUpdateBy(userId);
            commCarOperLog.setUpdateTime(new Date());
            commCarOperLog.setDeleteStatus(1);

            commCarOperLogDao.insert(commCarOperLog);
        }
        return car.getId();
    }

    //查询车辆列表
    @Override
    public List<CommCarQueryVO> getCarList(CommCarQueryDTO commCarQueryDTO) {
        List<CommCarQueryVO> carList = PaginationUtil.paginationQuery(commCarQueryDTO, (carQueryDTO) -> commCarDao.getCarList(carQueryDTO));
       /* for (Map<String, Object> car : carList) {
            car.put("useStatusDesc", CommCarEnum.getValueByParentCodeAndKey(DictionaryConstant.DICT_CODE_USE_STATUS_CONFIG, car.get("useStatus").toString()));
            car.put("operationStatusDesc", CommCarEnum.getValueByParentCodeAndKey(DictionaryConstant.DICT_CODE_OPERATION_STATUS_CONFIG, car.get("operationStatus").toString()));
            car.put("source", CommCarEnum.getValueByParentCodeAndKey(DictionaryConstant.DICT_CODE_SOURCE_CONFIG, car.get("source").toString()));
            String turnOutType = car.get("turnOutType") == null ? "" : car.get("turnOutType").toString();
            car.put("turnOutType", CommCarEnum.transferTurnOutType(turnOutType));
            //能否还车标示
            car.put("enableReturn", (turnOutType.equals(CommCarEnum.TURN_OUT_TYPE_0.getKey()) || turnOutType.equals(CommCarEnum.TURN_OUT_TYPE_8.getKey())) ? false : true);
        }*/
        return carList;
    }


    //查询车辆列表总数
    @Override
    public Map<String, Object> getCarListCount(Map<String, Object> req) {
        return commCarDao.getCarListCount(req);
    }

    //启用禁用车辆
    @Override
    public String updateCarUseStatusById(CommCarUpdateUseStatusDTO commCarUpdateUseStatusDTO) {
        //根据id查询车辆信息
        CommCar car = commCarDao.selectByPrimaryKey(commCarUpdateUseStatusDTO.getId());
        if (car == null) {
            return Result.failure("车辆id对应车辆不存在").toJSON();
        }
        String newUseStatus = commCarUpdateUseStatusDTO.getUseStatus(); //将要修改的启用状态
        String oldUseStatus = car.getUseStatus(); //原来的启用状态
        if (newUseStatus.equals(oldUseStatus)) {
            return Result.failure("修改后状态不会改变，无需修改").toJSON();
        }
        String operationStatus = car.getOperationStatus(); //运营状态

        //运营状态为出租（2）时不能修改启用状态
        if (CommCarEnum.CommCarOperationStatusEnum.OPERATION_STATUS_2.getCode().toString().equals(operationStatus)) {
            return Result.failure("运营状态为已租时不能修改启用状态").toJSON();
        }
        int effect = commCarDao.updateUseStatus(commCarUpdateUseStatusDTO);

        if (effect > 0) {
            //操作日志
            CommCarOperLog commCarOperLog = new CommCarOperLog();
            commCarOperLog.setCarId(car.getId());
            commCarOperLog.setOperContent(String.valueOf(CommonEnum.DeleteStatusEnum.STATUS_1.getCode()).equals(newUseStatus) ? "将车辆启用状态由【禁用】改为【启用】" : "将车辆启用状态由【启用】改为【禁用】");
            commCarOperLog.setCreateBy(commCarUpdateUseStatusDTO.getUpdateBy());
            commCarOperLog.setCreateTime(new Date());
            commCarOperLog.setUpdateBy(commCarUpdateUseStatusDTO.getUpdateBy());
            commCarOperLog.setUpdateTime(new Date());
            commCarOperLog.setDeleteStatus(1);
            commCarOperLogDao.insert(commCarOperLog);

            return Result.success().toJSON();
        } else {
            return Result.failure("修改失败").toJSON();
        }

    }

    //查询车辆详情
    @Override
    public Map<String, Object> getCarInfo(Integer id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("car", commCarDao.selectByPrimaryKey(id));
        List<CommCarImg> carImgs = commCarImgDao.selectByCarId(id);
        if (carImgs != null && carImgs.size() > 0) {
            carImgs.forEach(carImg -> {
                try {
                    carImg.setViewFileUrl(FileUtil.getBrowseFile(carImg.getFileUrl(), carImg.getFileName()));
                    carImg.setViewLittleFileUrl(FileUtil.getBrowseFile(carImg.getLittleFileUrl(), carImg.getLittleFileName()));
                } catch (Exception e) {
                    logger.info("加密图片路径异常", e);
                }
            });
        }
        resultMap.put("carImgs", carImgs);
        return resultMap;
    }

    /**
     * 根据车牌 车架号 发动机号查询车辆信息
     *
     * @param keyWord
     * @return
     */
    @Override
    public CommCar selectByPlateOrVinOrEngineNo(String keyWord) {
        return commCarDao.selectByPlateOrVinOrEngineNo(keyWord);
    }

    /**
     * 查询车辆少量信息
     *
     * @param id
     * @return
     */
    @Override
    public CommCarSimpleVO getCarInfoSimple(Integer id) {
        return commCarDao.getCarInfoSimpleById(id);
    }

    /**
     * 修改运营状态
     *
     * @param commCarUpdateOperationStatusDTO
     * @return
     */
    @Override
    public String updateCarOperationStatusById(CommCarUpdateOperationStatusDTO commCarUpdateOperationStatusDTO) {
        //根据id查询车辆信息
        CommCar car = commCarDao.selectByPrimaryKey(commCarUpdateOperationStatusDTO.getId());
        if (car == null) {
            return Result.failure("车辆id对应车辆不存在").toJSON();
        }
        String newOperationStatus = commCarUpdateOperationStatusDTO.getOperationStatus(); //将要修改的运营状态
        String oldOperationStatus = car.getOperationStatus(); //原来的运营状态
        //运营状态为出租（2）时不能修改启用状态
        if (CommCarEnum.CommCarOperationStatusEnum.OPERATION_STATUS_2.getCode().toString().equals(oldOperationStatus)) {
            return Result.failure("运营状态为已租时不能修改").toJSON();
        }
        if (newOperationStatus.equals(oldOperationStatus)) {
            return Result.failure("状态相同，无需修改").toJSON();
        }

        //进行更新操作
        int effect = commCarDao.updateOperationStatus(commCarUpdateOperationStatusDTO);//更新操作
        if (effect == 0) {
            return Result.failure("状态更新失败").toJSON();
        }

        //操作日志
        CommCarOperLog commCarOperLog = new CommCarOperLog();
        commCarOperLog.setCarId(car.getId());
        commCarOperLog.setOperContent(String.valueOf(CommCarEnum.CommCarOperationStatusEnum.OPERATION_STATUS_0.getCode()).equals(newOperationStatus) ? "将车辆运营状态由【待租】改为【待检】" : "将车辆运营状态由【待检】改为【待租】");
        commCarOperLog.setCreateBy(commCarUpdateOperationStatusDTO.getUpdateBy());
        commCarOperLog.setCreateTime(new Date());
        commCarOperLog.setUpdateBy(commCarUpdateOperationStatusDTO.getUpdateBy());
        commCarOperLog.setUpdateTime(new Date());
        commCarOperLog.setDeleteStatus(1);
        commCarOperLogDao.insert(commCarOperLog);

        return Result.success().toJSON();
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("");

    }

}
