package com.ydc.service.order.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalAccidentMaintenanceVO;
import com.ydc.commom.view.vo.cgj.rental.RentalOrderMaintenanceVO;
import com.ydc.dao.cgj.car.CommCarDao;
import com.ydc.dao.cgj.car.CommCarOperLogDao;
import com.ydc.dao.cgj.rental.*;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.car.CommCarOperLog;
import com.ydc.model.cgj.rental.*;
import com.ydc.service.order.service.MaintenanceOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机务单
 *
 * @author
 * @create 2018-11-21 20:48
 **/
@Service
public class MaintenanceOrderServiceImpl implements MaintenanceOrderService {

    private Logger logger = LogManager.getLogger(MaintenanceOrderServiceImpl.class);

    @Resource
    private RentalOrderMaintenanceDao rentalOrderMaintenanceDao;
    @Resource
    private CommCarDao commCarDao;

    @Resource
    private RentalCarWashInfoDao rentalCarWashInfoDao;

    @Resource
    private RentalRefuelInfoDao rentalRefuelInfoDao;

    @Resource
    private RentalMaintenanceDao rentalMaintenanceDao;

    @Resource
    private RentalAccidentMaintenanceDao rentalAccidentMaintenanceDao;

    @Resource
    private RentalAccidentDao rentalAccidentDao;

    @Resource
    private RentalDispatchDAO rentalDispatchDAO;

    @Resource
    private CommCarOperLogDao commCarOperLogDao;

    @Resource
    private RentalViolationDao rentalViolationDao;


    /**
     * 新增机务单（出车）
     *
     * @param rentalOrderMaintenanceInsertDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    public String saveMaintenanceOrder(RentalOrderMaintenanceInsertDTO rentalOrderMaintenanceInsertDTO) {
        //根据车辆的原出车类型判断是否可以出车
        CommCar car = commCarDao.selectByPrimaryKey(rentalOrderMaintenanceInsertDTO.getCarId()); //根据车辆id查询车辆信息
        if (car == null) {
            return Result.failure("根据车辆id未查询到车辆信息").toJSON();
        }
        if (!CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_0.getCode().equals(car.getTurnOutType())) {
            return Result.failure("此车辆不处于未出车状态，不能再次出车").toJSON();
        }
        if (rentalOrderMaintenanceInsertDTO.getComeWarehouseOilAmount() > car.getTankVolume()){
            return Result.failure("出车油量不能大于油箱容量").toJSON();
        }

        int effect = rentalOrderMaintenanceDao.insertMaintenanceOrder(rentalOrderMaintenanceInsertDTO);
        if (effect == 0) {
            return Result.failure("新增机务单失败").toJSON();
        }
        //修改车辆出车类型
        Map<String, Object> carMap = new HashMap<>();
        carMap.put("turnOutType", rentalOrderMaintenanceInsertDTO.getMaintenanceType());
        carMap.put("updateTime", new Date());
        carMap.put("updateBy", rentalOrderMaintenanceInsertDTO.getCreateBy());
        carMap.put("id", car.getId());
        effect = commCarDao.updateByPrimaryKeySelective(carMap);
        if (effect == 0) {
            //return Result.failure("修改车辆出车状态失败").toJSON();
            throw new ServiceRuntimeException("修改车辆出车状态失败");
        }

        //操作日志
        CommCarOperLog commCarOperLog = new CommCarOperLog();
        commCarOperLog.setCarId(car.getId());
        commCarOperLog.setOperContent("新增了机务单");
        commCarOperLog.setCreateBy(rentalOrderMaintenanceInsertDTO.getCreateBy());
        commCarOperLog.setCreateTime(new Date());
        commCarOperLog.setUpdateBy(rentalOrderMaintenanceInsertDTO.getCreateBy());
        commCarOperLog.setUpdateTime(new Date());
        commCarOperLog.setDeleteStatus(1);
        commCarOperLogDao.insert(commCarOperLog);

        return Result.success().toJSON();
    }

    @Override
    public List<RentalOrderMaintenanceVO> getMaintenanceOrderList(RentalOrderMaintenanceDTO rentalOrderMaintenanceDTO) {
        return PaginationUtil.paginationQuery(rentalOrderMaintenanceDTO,
                (tempRentalOrderMaintenanceDTO) -> rentalOrderMaintenanceDao.getMaintenanceOrderList(tempRentalOrderMaintenanceDTO));
    }

    /**
     * 修改机务单（还车）
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    public String updateMaintenanceOrder(Map<String, Object> param) {
        //获取机务单信息
        RentalOrderMaintenance rentalOrderMaintenance = JSONObject.parseObject(JSONObject.toJSONString(param.get("rentalOrderMaintenance")), RentalOrderMaintenance.class);
        //获取车辆信息
        CommCar car = commCarDao.selectByPrimaryKey(rentalOrderMaintenance.getCarId()); //根据车辆id查询车辆信息
        if (car == null) {
            return Result.failure("根据车辆id未查询到车辆信息").toJSON();
        }
        if (CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_0.getCode().equals(car.getTurnOutType())) {
            return Result.failure("机务单关联车辆未出车，不能还车").toJSON();
        }
        //判断出车油量是否超过重量
        if (Integer.valueOf(rentalOrderMaintenance.getComeWarehouseOilAmount()) > car.getTankVolume()){
            return Result.failure("出车油量不能大于油箱容量").toJSON();
        }
        //判断还车油量是否超过重量
        if (Integer.valueOf(rentalOrderMaintenance.getRepayCarOilAmount()) > car.getTankVolume()){
            return Result.failure("还车油量不能大于油箱容量").toJSON();
        }
        //查询老机务单是否还车
        RentalOrderMaintenance oldRentalOrderMaintenance = rentalOrderMaintenanceDao.selectByPrimaryKey(rentalOrderMaintenance.getId());
        if (oldRentalOrderMaintenance.getRepayCar() == 1){
            return Result.failure("该机务单已经还车,不能再次操作").toJSON();
        }
        //判断还车时  是否删除了出车时勾选的机务类型
        String oldMaintenanceType = oldRentalOrderMaintenance.getMaintenanceType();
        String maintenanceType = rentalOrderMaintenance.getMaintenanceType();
        for (String oneMaintenanceType : oldMaintenanceType.split(",")) {
            if(!maintenanceType.contains(oneMaintenanceType)){
                return Result.failure("还车时缺失出车已选机务类型："+ CommCarEnum.CommCarTurnOutTypeEnum.getCodeName(oneMaintenanceType)).toJSON();
            }
        }
        int effect = rentalOrderMaintenanceDao.updateByPrimaryKey(rentalOrderMaintenance);  //修改机务单表
        if (effect == 0) {
            return Result.failure("修改机务单失败").toJSON();
        }
        //获取洗车信息
        Map rentalCarWashInfoMap = (Map) param.get("rentalCarWashInfo");
        //不为空时添加
        if (rentalCarWashInfoMap != null) {
            effect = rentalCarWashInfoDao.insert(JSONObject.parseObject(JSONObject.toJSONString(rentalCarWashInfoMap), RentalCarWashInfo.class));
            if (effect == 0) {
                return Result.failure("补充洗车信息失败").toJSON();
            }
        }
        //获取加油信息
        Map rentalRefuelInfoMap = (Map) param.get("rentalRefuelInfo");
        //不为空时添加
        if (rentalRefuelInfoMap != null) {
            effect = rentalRefuelInfoDao.insert(JSONObject.parseObject(JSONObject.toJSONString(rentalRefuelInfoMap), RentalRefuelInfo.class));
            if (effect == 0) {
                throw new ServiceRuntimeException("补充加油信息失败");
            }

        }
        //获取维修保养信息
        Map rentalMaintenance = (Map) param.get("rentalMaintenance");
        //不为空时添加
        if (rentalMaintenance != null) {
            effect = rentalMaintenanceDao.insert(JSONObject.parseObject(JSONObject.toJSONString(rentalMaintenance), RentalMaintenance.class));
            if (effect == 0) {
               // return Result.failure("补充维修保养信息失败").toJSON();
                throw new ServiceRuntimeException("补充维修保养信息失败");
            }
        }
        //获取事故维修信息
        Map rentalAccidentMaintenance = (Map) param.get("rentalAccidentMaintenance");
        //不为空时添加
        if (rentalAccidentMaintenance != null) {
            effect = rentalAccidentMaintenanceDao.insert(JSONObject.parseObject(JSONObject.toJSONString(rentalAccidentMaintenance), RentalAccidentMaintenance.class));
            if (effect == 0) {
                //return Result.failure("补充事故维修信息失败").toJSON();
                throw new ServiceRuntimeException("补充事故维修信息失败");
            }
            String accidentId = param.get("accidentId").toString();
            String[] accidentIdArray = accidentId.split(",");
            for (String accidentIdStr : accidentIdArray) {
                //给事故表追加处理单id
                RentalAccident rentalAccident = rentalAccidentDao.selectByPrimaryKey(Integer.valueOf(accidentIdStr));
                if (rentalAccident ==null) {
                    //return Result.failure("事故id："+accidentIdStr+"未查询到事故").toJSON();
                    throw new ServiceRuntimeException("事故id："+accidentIdStr+"未查询到事故");
                }
                if ( !rentalAccident.getCarNumber().equals(rentalOrderMaintenance.getCarNumber())){
                   // return Result.failure("事故单和机务单不属于同一车辆").toJSON();
                    throw new ServiceRuntimeException("事故单和机务单不属于同一车辆");
                }
                String oldMaintenanceOrderId = rentalAccident.getMaintenanceOrderId();//原处理单id串
                StringBuffer newMaintenanceOrderId = new StringBuffer();//新处理单id串

                if (oldMaintenanceOrderId == null){
                    //原单无值直接赋值
                    newMaintenanceOrderId.append(rentalOrderMaintenance.getId());
                }else {
                    //新处理单id已在原处理单中存在 不需追加
                    if (oldMaintenanceOrderId.contains(rentalOrderMaintenance.getId().toString())){
                        continue;
                    }
                    //原单有值追加赋值
                    newMaintenanceOrderId.append(oldMaintenanceOrderId).append(",").append(rentalOrderMaintenance.getId());
                }
                //追加处理单id
                rentalAccident.setMaintenanceOrderId(newMaintenanceOrderId.toString());
                effect = rentalAccidentDao.updateByPrimaryKeySelective(rentalAccident);
                if (effect == 0) {
                    //return Result.failure("更新事故信息失败").toJSON();
                    throw new ServiceRuntimeException("更新事故信息失败");
                }
            }

        }
        //获取调度信息
        Map rentalDispatch = (Map) param.get("rentalDispatch");
        //不为空时添加
        if (rentalDispatch != null) {
            effect = rentalDispatchDAO.insert(JSONObject.parseObject(JSONObject.toJSONString(rentalDispatch), RentalDispatch.class));
            if (effect == 0) {
                //return Result.failure("补充调度信息失败").toJSON();
                throw new ServiceRuntimeException("补充调度信息失败");
            }
        }

        //修改车辆信息
        Map<String, Object> carMap = new HashMap<>();
        carMap.put("turnOutType", 0);  //出车类型改为未出车
        carMap.put("store", rentalOrderMaintenance.getRepayCarStoreName());  //门店改为还车门店
        carMap.put("storeId", rentalOrderMaintenance.getRepayCarStoreId());  //门店id改为还车门店id
        carMap.put("mileage", rentalOrderMaintenance.getRepayCarMileage());  //行驶里程改为还车里程数
        carMap.put("oilMass", rentalOrderMaintenance.getRepayCarOilAmount());  //剩余油量改为还车油量
        carMap.put("updateTime", new Date());
        carMap.put("updateBy", rentalOrderMaintenance.getUpdateBy());
        carMap.put("id", rentalOrderMaintenance.getCarId());
        effect = commCarDao.updateByPrimaryKeySelective(carMap);
        if (effect == 0) {
           // return Result.failure("修改车辆出车状态失败").toJSON();
            throw new ServiceRuntimeException("修改车辆出车状态失败");
        }

        //操作日志
        CommCarOperLog commCarOperLog = new CommCarOperLog();
        commCarOperLog.setCarId(rentalOrderMaintenance.getCarId());
        commCarOperLog.setOperContent("操作了机务还车");
        commCarOperLog.setCreateBy(rentalOrderMaintenance.getUpdateBy());
        commCarOperLog.setCreateTime(new Date());
        commCarOperLog.setUpdateBy(rentalOrderMaintenance.getUpdateBy());
        commCarOperLog.setUpdateTime(new Date());
        commCarOperLog.setDeleteStatus(1);
        commCarOperLogDao.insert(commCarOperLog);

        return Result.success().toJSON();
    }

    @Override
    public RentalOrderMaintenanceVO getMaintenanceOrderById(Integer maintenanceOrderId) {
        return rentalOrderMaintenanceDao.selectVOByPrimaryKey(maintenanceOrderId);
    }

    @Override
    public RentalCarWashInfo selectWashInfoByMaintenanceId(Integer maintenanceId) {
        return rentalCarWashInfoDao.selectWashInfoByMaintenanceId(maintenanceId);
    }

    @Override
    public RentalRefuelInfo selectRefuelByMaintenanceId(Integer maintenanceId) {
        return rentalRefuelInfoDao.selectRefuelByMaintenanceId(maintenanceId);
    }

    @Override
    public Map<String, Object> selectMaintenanceInfoByMaintenanceId(Integer maintenanceId) {
        return rentalMaintenanceDao.selectMaintenanceInfoByMaintenanceId(maintenanceId);
    }

    @Override
    public RentalAccidentMaintenanceVO selectAccidentMaintenanceByMaintenanceId(Integer maintenanceId) {
        RentalAccidentMaintenanceVO rentalAccidentMaintenanceVO =
                rentalAccidentMaintenanceDao.selectAccidentMaintenanceByMaintenanceId(maintenanceId);
        if (rentalAccidentMaintenanceVO == null) {
            return null;
        }
        rentalAccidentMaintenanceVO.setRentalAccidentList(
                rentalAccidentDao.selectAccidentByMaintenanceId(maintenanceId));
        return rentalAccidentMaintenanceVO;
    }

    @Override
    public RentalDispatch selectDispatchInfoByMaintenanceId(Integer maintenanceId) {
        return rentalDispatchDAO.selectDispatchInfoByMaintenanceId(maintenanceId);
    }

    /**
     * 出车详情
     *
     * @param id
     * @return
     */
    @Override
    public String getMaintenanceOrderDrawOut(Integer id) {
        Map<String, Object> maintenanceOrderDrawOut = rentalOrderMaintenanceDao.selectMaintenanceOrderDrawOut(id);
        if (maintenanceOrderDrawOut == null || maintenanceOrderDrawOut.size() == 0) {
            return Result.failure("根据机务单id未查询到机务信息").toJSON();
        }
        return Result.success(maintenanceOrderDrawOut).toJSON();
    }

    /**
     * 根据车辆id获取机务单id(最新的一条)
     *
     * @param carId
     * @return
     */
    @Override
    public String getMaintenanceOrderIdByCarId(Integer carId) {
        //根据id查询车辆信息
        CommCar car = commCarDao.selectByPrimaryKey(carId);
        if (car == null) {
            return Result.failure("车辆id对应车辆不存在").toJSON();
        }
        String turnOutType = car.getTurnOutType();
        //未出车的车辆不能还车
        if (CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_0.getCode().equals(turnOutType)) {
            return Result.failure("未出车的车辆不能还车").toJSON();
        }
        Map retMap = rentalOrderMaintenanceDao.getMaintenanceOrderIdByCarId(carId);
        return Result.success(retMap).toJSON();
    }

    /**
     * 删除机务单(软删)
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    public String deleteMaintenanceOrderById(Integer id, Integer userId) {
        //有事故的机务单不能删除，提示“该机务单有关联事故单，不可删除”
        RentalAccident rentalAccident = rentalAccidentDao.selectAccidentByMaintenanceOrderId(id); //查询事故单
        if (rentalAccident != null) {
            return Result.failure("该机务单有关联事故单，不可删除").toJSON();
        }
        //有违章的机务单不能删除，提示“该机务单有关联违章单，不可删除”
        RentalViolation rentalViolation = rentalViolationDao.selectViolationByMaintenanceOrderId(id); //查询违章单
        if (rentalViolation != null) {
            return Result.failure("该机务单有关联违章单，不可删除").toJSON();
        }
        //根据机务单id查询机务单信息
        RentalOrderMaintenance rentalOrderMaintenance = rentalOrderMaintenanceDao.selectByPrimaryKey(id);

        //修改状态
        rentalOrderMaintenance.setStatus((byte) 0);
        rentalOrderMaintenance.setUpdateBy(userId);
        rentalOrderMaintenance.setUpdateTime(new Date());

        //更新操作
        int effect = rentalOrderMaintenanceDao.updateByPrimaryKeySelective(rentalOrderMaintenance);
        if (effect == 0) {
            return Result.failure("删除机务单失败").toJSON();
        }

        //添加操作日志
        CommCarOperLog commCarOperLog = new CommCarOperLog();
        commCarOperLog.setCarId(rentalOrderMaintenance.getCarId());
        commCarOperLog.setOperContent("删除了机务单");
        commCarOperLog.setCreateBy(userId);
        commCarOperLog.setCreateTime(new Date());
        commCarOperLog.setUpdateBy(userId);
        commCarOperLog.setUpdateTime(new Date());
        commCarOperLog.setDeleteStatus(1);
        effect = commCarOperLogDao.insert(commCarOperLog);
        if (effect == 0) {
            return Result.failure("添加车辆操作日志失败").toJSON();
        }

        //将机务单对应车辆的出车状态改为未出车
        Map car = new HashMap();
        car.put("id",rentalOrderMaintenance.getCarId());
        car.put("turnOutType", CommCarEnum.CommCarTurnOutTypeEnum.TURN_OUT_TYPE_0.getCode());
        car.put("updateBy",userId);
        car.put("updateTime",new Date());
        effect = commCarDao.updateByPrimaryKeySelective(car);
        if (effect == 0) {
            return Result.failure("修改车辆出车状态失败").toJSON();
        }
        return Result.success().toJSON();
    }
}
