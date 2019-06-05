package com.ydc.service.order.controller;


import com.github.pagehelper.PageInfo;
import com.ydc.commom.constant.rental.RentalConstant;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalOrderMaintenanceInsertDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalOrderMaintenanceVO;
import com.ydc.service.order.service.MaintenanceOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 机务单
 *
 * @author
 * @create 2018-11-21 20:51
 **/
@RestController
@RequestMapping(value = "/maintenanceOrder")
public class MaintenanceOrderController {

    private final Logger logger = LogManager.getLogger(MaintenanceOrderController.class);

    @Autowired
    private MaintenanceOrderService maintenanceOrderService;

    /**
     * 新增机务单（出车）
     *
     * @param rentalOrderMaintenanceInsertDTO
     * @return
     */
    @PostMapping(value = "/saveMaintenanceOrder")
    public String saveMaintenanceOrder(@RequestBody RentalOrderMaintenanceInsertDTO rentalOrderMaintenanceInsertDTO) {
        return maintenanceOrderService.saveMaintenanceOrder(rentalOrderMaintenanceInsertDTO);
    }


    /**
     * 机务单列表
     *
     * @param rentalOrderMaintenanceDTO
     * @return
     */
    @PostMapping(value = "/getMaintenanceOrderList")
    public Result getMaintenanceOrderList(@RequestBody RentalOrderMaintenanceDTO rentalOrderMaintenanceDTO) {
        List<RentalOrderMaintenanceVO> list =  maintenanceOrderService.getMaintenanceOrderList(rentalOrderMaintenanceDTO);
        Result result = Result.success();
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("rows", getResultList(list));
        resultData.put("totalCount", list == null ? 0 : new PageInfo<>(list).getTotal());
        result.setData(resultData);
        return result;
    }

    /**
     * 转换成前端需要的list
     * @param list
     * @return
     */
    private List<RentalOrderMaintenanceVO> getResultList(List<RentalOrderMaintenanceVO> list){
        if (list == null || list.size() <= 0){
            return new ArrayList<>();
        }
        list.parallelStream().forEach(rentalOrderMaintenanceVO -> {
            rentalOrderMaintenanceVO.setMaintenanceType(CommCarEnum.CommCarTurnOutTypeEnum.transferTurnOutType(rentalOrderMaintenanceVO.getMaintenanceType()));
            rentalOrderMaintenanceVO.setConsumingTime(
                    DateUtil.diffTimeInMinutesAndHours(rentalOrderMaintenanceVO.getComeCarTime(), rentalOrderMaintenanceVO.getRepayCarTime()));
        });
        return list;
    }

    /**
     * 修改机务单（还车）
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/updateMaintenanceOrder")
    public String updateMaintenanceOrder(@RequestBody Map<String, Object> param) {
        return maintenanceOrderService.updateMaintenanceOrder(param);
    }

    /**
     * 机务单详情
     * @param maintenanceOrderId
     * @return
     */
    @GetMapping(value = "/getMaintenanceOrderDetail")
    public RentalOrderMaintenanceVO getMaintenanceOrderDetail(@RequestParam(value = "maintenanceOrderId") Integer maintenanceOrderId){
        RentalOrderMaintenanceVO rentalOrderMaintenanceVO = maintenanceOrderService.getMaintenanceOrderById(maintenanceOrderId);
        if (rentalOrderMaintenanceVO == null) return null;
        //机务单类别详情
        rentalOrderMaintenanceVO.setMaintenanceMap(
                getMaintenanceDetailMap(rentalOrderMaintenanceVO.getMaintenanceType(), maintenanceOrderId));
        return rentalOrderMaintenanceVO;
    }

    //机务单类别详情
    private Map<String, Object> getMaintenanceDetailMap(String maintenanceType, Integer maintenanceOrderId){
        if (maintenanceType == null || ("").equals(maintenanceType)) return null;
        return getMaintenanceDetailMap(Arrays.asList(maintenanceType.split(",")), maintenanceOrderId);
    }

    //机务单类别详情
    private Map<String, Object> getMaintenanceDetailMap(List<String> typeList, Integer maintenanceOrderId){
        if (typeList == null || typeList.size() <= 0) {
            return null;
        }
        Map<String, Object> maintenanceMap = new HashMap<>();
        typeList.parallelStream().forEach(type ->{
            switch (type.trim()){
                case "1":
                    //洗车
                    maintenanceMap.put(RentalConstant.MAINTENANCE_WASH,
                            maintenanceOrderService.selectWashInfoByMaintenanceId(maintenanceOrderId));
                    break;
                case "2":
                    //加油
                    maintenanceMap.put(RentalConstant.MAINTENANCE_REFUEL,
                            maintenanceOrderService.selectRefuelByMaintenanceId(maintenanceOrderId));
                    break;
                case "3":
                    //维修保养
                    maintenanceMap.put(RentalConstant.MAINTENANCE,
                            maintenanceOrderService.selectMaintenanceInfoByMaintenanceId(maintenanceOrderId));
                    break;
                case "4":
                    //事故
                    maintenanceMap.put(RentalConstant.MAINTENANCE_ACCIDENT,
                            maintenanceOrderService.selectAccidentMaintenanceByMaintenanceId(maintenanceOrderId));
                    break;
                case "5":
                    //调度
                    maintenanceMap.put(RentalConstant.MAINTENANCE_DISPATCH,
                            maintenanceOrderService.selectDispatchInfoByMaintenanceId(maintenanceOrderId));
                    break;
//                case "6":
//                    //公务
//                    maintenanceMap.put(RentalConstant.MAINTENANCE_DISPATCH,
//                            maintenanceOrderService.selectDispatchInfoByMaintenanceId(maintenanceOrderId));
//                    break;
                default:
                    break;
            }
        });
        return maintenanceMap;
    }
    /**
     * 出车详情
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getMaintenanceOrderDrawOut")
    public String getMaintenanceOrderDrawOut(@RequestParam("id") Integer id){
        return maintenanceOrderService.getMaintenanceOrderDrawOut(id);
    }

    /**
     * 根据车辆id获取机务单id
     *
     * @param carId
     * @return
     */
    @PostMapping(value = "/getMaintenanceOrderIdByCarId")
    public String getMaintenanceOrderIdByCarId(@RequestParam("carId") Integer carId){
        return maintenanceOrderService.getMaintenanceOrderIdByCarId(carId);
    }

    /**
     * 删除机务单(软删)
     *
     * @param id
     * @param userId
     * @return
     */
    @PostMapping(value = "/deleteMaintenanceOrderById")
    String deleteMaintenanceOrderById(@RequestParam("id") Integer id, @RequestParam("userId") Integer userId){
        return maintenanceOrderService.deleteMaintenanceOrderById(id,userId);
    }
}
