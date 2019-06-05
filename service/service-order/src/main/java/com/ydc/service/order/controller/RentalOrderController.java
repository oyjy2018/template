package com.ydc.service.order.controller;

import com.ydc.commom.constant.rental.RentalEnterpriseOrderConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.enums.rental.RentalFeeVoucherEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.SystemUtil;
import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.*;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.StoreRentalEnterpriseOrderDepositVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.StoreRentalEnterpriseOrderDetailsVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.StoreRentalEnterpriseSettlementVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.StoreRentalFeeVoucherVO;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.rental.*;
import com.ydc.service.order.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租车订单
 *
 * @author
 * @create 2018-11-21 20:51
 **/
@RestController
@RequestMapping(value = "/rentalOrder")
public class RentalOrderController {

    private final Logger logger = LogManager.getLogger(RentalOrderController.class);

    @Autowired
    RentalOrderService rentalOrderService;
    @Autowired
    UserService userService;
    @Autowired
    RentalOrderCarImgService rentalOrderCarImgService;
    @Autowired
    RentalEnterpriseOrderService rentalEnterpriseOrderService;
    @Autowired
    RentalCheckCarService rentalCheckCarService;
    @Autowired
    DepositService depositService;
    @Autowired
    RentalFeeVoucherService rentalFeeVoucherService;
    @Autowired
    RentalEnterpriseSettlementService rentalEnterpriseSettlementService;

    /**
     * 查询车辆等级
     * @param
     * @return
     */
    @PostMapping(value = "/getCarLevelGroup")
    public String getCarLevelGroup(){
        logger.info("subject:{}","查询车辆等级");
        try{
            List<Map<String, Object>> ret = rentalOrderService.getCarLevelGroup();
            logger.info("subject:{},ret:{}","查询车辆等级",ret);
            return Result.success(ret).toJSON();
        }catch (Exception e){
            logger.error("查询车辆等级异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 查询车辆品牌
     * @param
     * @return
     */
    @PostMapping(value = "/getBrandByCarLevel")
    public String getBrandByCarLevel(@RequestBody CommCarDTO commCarDTO){
        logger.info("subject:{},commCarDTO:{}","查询车辆品牌",JsonUtil.gsonStr(commCarDTO));
        try{
            List<Map<String, Object>> ret = rentalOrderService.getBrandByCarLevel(commCarDTO);
            logger.info("subject:{},ret:{}","查询车辆品牌",ret);
            return Result.success(ret).toJSON();
        }catch (Exception e){
            logger.error("查询车辆品牌异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 查询车辆车系
     * @param
     * @return
     */
    @PostMapping(value = "/getSeriesByBrand")
    public String getSeriesByBrand(@RequestBody CommCarDTO commCarDTO){
        logger.info("subject:{},commCarDTO:{}","查询车辆车系",JsonUtil.gsonStr(commCarDTO));
        try{
            List<Map<String, Object>> ret = rentalOrderService.getSeriesByBrand(commCarDTO);
            logger.info("subject:{},ret:{}","查询车辆车系",ret);
            return Result.success(ret).toJSON();
        }catch (Exception e){
            logger.error("查询车辆车系异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 查询车辆车型
     * @param
     * @return
     */
    @PostMapping(value = "/getModelBySeries")
    public String getModelBySeries(@RequestBody CommCarDTO commCarDTO){
        logger.info("subject:{},commCarDTO:{}","查询车辆车型",JsonUtil.gsonStr(commCarDTO));
        try{
            List<Map<String, Object>> ret = rentalOrderService.getModelBySeries(commCarDTO);
            logger.info("subject:{},ret:{}","查询车辆车型",ret);
            return Result.success(ret).toJSON();
        }catch (Exception e){
            logger.error("查询车辆车型异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 获取出车信息
     * @param commCarDTO
     * @return
     */
    @PostMapping(value = "/getComeCarData")
    public String getComeCarData(@RequestBody CommCarDTO commCarDTO){
        logger.info("subject:{},commCarDTO:{}","获取出车信息",JsonUtil.gsonStr(commCarDTO));
        try{
            RentalOrder rentalOrder = rentalOrderService.getRentalOrderByOrderId(commCarDTO.getOrderId());
            RentalCarDataVO rentalCarDataVO = new RentalCarDataVO();
            commCarDTO.setCarLevel(rentalOrder.getCarLevel());
            commCarDTO.setBrand(rentalOrder.getBrand());
            commCarDTO.setSeries(rentalOrder.getSeries());
            commCarDTO.setModel(rentalOrder.getModel());
            rentalCarDataVO.setAppointmentStoreId(rentalOrder.getAppointmentStoreId());
            rentalCarDataVO.setAppointmentStoreName(rentalOrder.getAppointmentStoreName());
            //可选车辆集合
            rentalCarDataVO.setCarPlateVOS(rentalOrderService.getCarPlateList(commCarDTO));
            //用户集合
            rentalCarDataVO.setUsers(userService.getValidUser());
            //门店集合
            rentalCarDataVO.setRentalStoreVOS(RentalStoreVO.getRentalStoreVos(rentalOrderService.getAllRentalStore()));
            logger.info("subject:{},rentalCarDataVO:{}","获取出车信息",JsonUtil.gsonStr(rentalCarDataVO));
            return Result.success(rentalCarDataVO).toJSON();
        }catch (Exception e){
            logger.error("获取出车信息异常",e);
            return Result.failure().toJSON();
        }
    }


    /**
     * 新增租车订单
     * @param
     * @return
     */
    @PostMapping(value = "/insertOrder")
    public String insertOrder(@RequestBody AddRentalOrderPCDTO addRentalOrderPCDTO){
        logger.info("subject:{},addRentalOrderPCDTO:{}","新增租车订单",JsonUtil.gsonStr(addRentalOrderPCDTO));
        //同一用车时间段一个客户只能下一个订单
        Result result = rentalOrderService.verifyEnableInsert(addRentalOrderPCDTO); //验证是否能新增
        //验证不通过返回验证结果
        if (result.getCode() != 200) {
            return result.toJSON();
        }
        try{
            return rentalOrderService.insertOrder(addRentalOrderPCDTO) > 0 ? Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("新增租车订单异常",e);
            return Result.failure().toJSON();
        }
    }

//    /**
//     * 更新租车订单信息
//     * @param
//     * @return
//     */
//    @PostMapping(value = "/updateByOrderId")
//    public String updateByOrderId(@RequestBody RentalOrder record){
//        logger.info("subject:{},rentalOrder:{}","更新租车订单信息",JsonUtil.gsonStr(record));
//        try{
//            return rentalOrderService.updateByOrderId(record) > 0 ? Result.success("成功").toJSON() : Result.failure("失败").toJSON();
//        }catch (Exception e){
//            logger.error("更新租车订单信息异常",e);
//            return Result.failure().toJSON();
//        }
//    }

    /**
     * 出车更新订单信息
     * @param
     * @return
     */
    @PostMapping(value = "/updateComeCarOrder")
    public String updateComeCarOrder(@RequestBody ComeCarOrderDTO comeCarOrderDTO){
        logger.info("subject:{},comeCarOrderDTO:{}","出车更新订单信息",JsonUtil.gsonStr(comeCarOrderDTO));
        try{
            return rentalOrderService.updateComeCarOrder(comeCarOrderDTO) > 0 ? Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("出车更新订单信息异常",e);
            return Result.failure().toJSON();
        }
    }


    /**
     * 还车更新订单信息
     * @param
     * @return
     */
    @PostMapping(value = "/updateRepayCarOrder")
    public String updateRepayCarOrder(@RequestBody RepayCarOrderDTO repayCarOrderDTO){
        logger.info("subject:{},comeCarOrderDTO:{}","还车更新订单信息",JsonUtil.gsonStr(repayCarOrderDTO));
        try{
            return rentalOrderService.updateRepayCarOrder(repayCarOrderDTO) > 0 ? Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("还车更新订单信息异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 查询门店集合
     * @param
     * @return
     */
    @PostMapping(value = "/getAllStore")
    public String getAllStore(){
        logger.info("subject:{}","查询门店集合");
        try{
            List<RentalStoreVO> rentalStoreVOS = RentalStoreVO.getRentalStoreVos(rentalOrderService.getAllRentalStore());
            logger.info("subject:{},rentalStoreVOS:{}","查询门店集合",JsonUtil.gsonStr(rentalStoreVOS));
            return Result.success(rentalStoreVOS).toJSON();
        }catch (Exception e){
            logger.error("查询门店集合异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 获取还车信息
     * @param commCarDTO
     * @return
     */
    @PostMapping(value = "/getRepayCarData")
    public String getRepayCarData(@RequestBody CommCarDTO commCarDTO){
        logger.info("subject:{},commCarDTO:{}","还车更新订单信息",JsonUtil.gsonStr(commCarDTO));
        try{
            RentalOrder rentalOrder = rentalOrderService.getRentalOrderByOrderId(commCarDTO.getOrderId());
            RentalCarDataVO rentalCarDataVO = new RentalCarDataVO();
            rentalCarDataVO.setComeWarehouseStoreId(rentalOrder.getComeWarehouseStoreId());
            rentalCarDataVO.setComeWarehouseStoreName(rentalOrder.getComeWarehouseStoreName());
            rentalCarDataVO.setCarNumber(rentalOrder.getCarNumber());
            //可选车辆集合
            rentalCarDataVO.setCarPlateVOS(rentalOrderService.getCarPlateList(commCarDTO));
            //用户集合
            rentalCarDataVO.setUsers(userService.getValidUser());
            //出车信息
            ComeCarOrderVO comeCarOrderVO = rentalOrderService.getComeCarOrderData(commCarDTO.getOrderId());
            //出车图片集合
            comeCarOrderVO.setComeCarOrderImgDTOS(rentalOrderCarImgService.getComeCarOrderImgData(commCarDTO.getOrderId()));
            rentalCarDataVO.setComeCarOrderVO(comeCarOrderVO);
            //门店集合
            rentalCarDataVO.setRentalStoreVOS(RentalStoreVO.getRentalStoreVos(rentalOrderService.getAllRentalStore()));
            logger.info("subject:{},rentalCarDataVO:{}","获取出车信息",JsonUtil.gsonStr(rentalCarDataVO));
            return Result.success(rentalCarDataVO).toJSON();
        }catch (Exception e){
            logger.error("获取还车信息异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 获取订单列表
     * @param req
     * @return
     */
    @PostMapping(value = "/getRentalOrderList")
    public String getRentalOrderList(@RequestBody Map<String, Object> req){
        logger.info("subject:{},req:{}","获取订单列表参数",JsonUtil.gsonStr(req));
        try{
            SystemUtil.getRequestQueryLimit(req);
            if(req.containsKey("startAppointmentFetchCarTime") && StringUtil.isNotEmpty(req.get("startAppointmentFetchCarTime").toString())){
                req.put("startAppointmentFetchCarTime", DateUtil.jointMinSuffix(req.get("startAppointmentFetchCarTime").toString()));
            }
            if(req.containsKey("endAppointmentFetchCarTime") && StringUtil.isNotEmpty(req.get("endAppointmentFetchCarTime").toString())){
                req.put("endAppointmentFetchCarTime", DateUtil.jointMaxSuffix(req.get("endAppointmentFetchCarTime").toString()));
            }
            if(req.containsKey("startAppointmentRepayCarTime") && StringUtil.isNotEmpty(req.get("startAppointmentRepayCarTime").toString())){
                req.put("startAppointmentRepayCarTime", DateUtil.jointMinSuffix(req.get("startAppointmentRepayCarTime").toString()));
            }
            if(req.containsKey("endAppointmentRepayCarTime") && StringUtil.isNotEmpty(req.get("endAppointmentRepayCarTime").toString())){
                req.put("endAppointmentRepayCarTime", DateUtil.jointMaxSuffix(req.get("endAppointmentRepayCarTime").toString()));
            }
            List<RentalOrderListVO> ret = rentalOrderService.getRentalOrderList(req);
            Map<String, Object> retCount = rentalOrderService.getRentalOrderListCount(req);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", retCount.get("cnt"));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("获取订单信息异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 根据会员ID查询订单列表(C端)
     * @param req
     * @return
     */
    @PostMapping(value = "/getRentalOrderListCByMemberId")
    public String getRentalOrderListCByMemberId(@RequestBody Map<String, Object> req){
        logger.info("subject:{},req:{}","根据会员ID查询订单列表(C端)参数",JsonUtil.gsonStr(req));
        try{
            SystemUtil.getRequestQueryLimit(req);
            List<RentalOrderListCVO> ret = rentalOrderService.getRentalOrderListCByMemberId(req);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("获取查询订单列表(C端)信息异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 根据门店ID查询订单列表(B端)
     * @param req
     * @return
     */
    @PostMapping(value = "/getRentalOrderListBByStoreId")
    public String getRentalOrderListBByStoreId(@RequestBody Map<String, Object> req){
        logger.info("subject:{},req:{}","根据门店ID查询订单列表(B端)参数",JsonUtil.gsonStr(req));
        try{
            SystemUtil.getRequestQueryLimit(req);
            List<RentalOrderListBVO> ret = rentalOrderService.getRentalOrderListBByStoreId(req);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("获取查询订单列表(B端)信息异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 会员id查询租车订单
     * @param memberId
     * @return
     */
    @PostMapping(value = "/getRentalOrderByMemberId")
    public RentalOrder getRentalOrderByMemberId(@RequestParam("memberId") Integer memberId) {
        logger.info("subject:{},memberId:{}","会员id查询租车订单",memberId);
        try{
            return rentalOrderService.getRentalOrderByMemberId(memberId);
        }catch (Exception e){
            logger.error("subject:{},memberId:{},e:{}","会员id查询租车订单异常",memberId,e);
            return new RentalOrder();
        }
    }


    /**
     * 更新订单预授权
     * @param
     * @return
     */
    @PostMapping(value = "/consent/update")
    public Result updateConsentAuthorization(@RequestParam(value = "memberId") Integer memberId,
                                             @RequestParam(value = "orderId") Integer orderId,
                                             @RequestParam(value = "status") Integer status){
        logger.info(  "更新用户订单授权  memberId:{},orderId:{} ，status{}",memberId,orderId,status);
        try{
            return rentalOrderService.updateConsentAuthorization(orderId,status);
        }catch (Exception e){
            logger.error("还车更新订单信息异常",e);
            return Result.failure();
        }
    }



    /**
     * 查询订单详情(PC端)
     * @param req
     * @return
     */
    @PostMapping(value = "/getRentalOrderDetailsPC")
    public String getRentalOrderDetailsPC(@RequestBody Map<String, Object> req) {
        logger.info("subject:{},req:{}","查询订单详情参数(PC端)",JsonUtil.gsonStr(req));
        try{
            Integer rentalOrderId = Integer.valueOf(req.get("rentalOrderId").toString());
            RentalOrderDetailsVO rentalOrderDetails = rentalOrderService.getRentalOrderDetailsPC(rentalOrderId);
                if(rentalOrderDetails == null){
                return Result.failure("该订单不存在或丢失").toJSON();
            }
            return Result.success(rentalOrderDetails).toJSON();
        }catch (Exception e){
            logger.error("subject:{},req:{},e:{}","查询订单详情(PC端)异常",req,e);
            return Result.failure("查询订单详情异常").toJSON();
        }
    }

    /**
     * 查询订单详情(APP C端)
     * @param req
     * @return
     */
    @PostMapping(value = "/getRentalOrderDetailsAPPC")
    public String getRentalOrderDetailsAPPC(@RequestBody Map<String, Object> req) {
        logger.info("subject:{},req:{}","查询订单详情参数(APP C端)",JsonUtil.gsonStr(req));
        try{
            Integer rentalOrderId = Integer.valueOf(req.get("rentalOrderId").toString());
            RentalOrderDetailsAPPCVO rentalOrderDetails = rentalOrderService.getRentalOrderDetailsAPPC(rentalOrderId);
            if(rentalOrderDetails == null){
                return Result.failure("该订单不存在或丢失").toJSON();
            }
            return Result.success(rentalOrderDetails).toJSON();
        }catch (Exception e){
            logger.error("subject:{},req:{},e:{}","查询订单详情(APP C端)异常",req,e);
            return Result.failure("查询订单详情异常").toJSON();
        }
    }

    /**
     * 查询订单详情(APP B端)
     * @param req
     * @return
     */
    @PostMapping(value = "/getRentalOrderDetailsAPPB")
    public String getRentalOrderDetailsAPPB(@RequestBody Map<String, Object> req) {
        logger.info("subject:{},req:{}","查询订单详情参数(APP B端)",JsonUtil.gsonStr(req));
        try{
            Integer rentalOrderId = Integer.valueOf(req.get("rentalOrderId").toString());
            RentalOrderDetailsAPPBVO rentalOrderDetails = rentalOrderService.getRentalOrderDetailsAPPB(rentalOrderId);
            if(rentalOrderDetails == null){
                return Result.failure("该订单不存在或丢失").toJSON();
            }
            return Result.success(rentalOrderDetails).toJSON();
        }catch (Exception e){
            logger.error("subject:{},req:{},e:{}","查询订单详情(APP B端)异常",req,e);
            return Result.failure("查询订单详情异常").toJSON();
        }
    }

    /**
     * 订单id查询详情
     * @param orderId
     * @return
     */
    @PostMapping(value = "/getRentalOrderByOrderId")
    public RentalOrder getRentalOrderByOrderId(@RequestParam("orderId") Integer orderId) {
        logger.info("subject:{},orderId:{}","订单id查询详情",orderId);
        try{
            return rentalOrderService.getRentalOrderByOrderId(orderId);
        }catch (Exception e){
            logger.error("subject:{},memberId:{},e:{}","订单id查询详情异常",orderId,e);
            return new RentalOrder();
        }
    }


    /**
     * 取消订单
     * @param updateRentalOrderDTO
     * @return
     */
    @PostMapping(value = "/cancelUseCarOrder")
    public String cancelUseCarOrder(@RequestBody UpdateRentalOrderDTO updateRentalOrderDTO) {
        logger.info("subject:{},updateRentalOrderDTO:{}","取消用车订单",JsonUtil.gsonStr(updateRentalOrderDTO));
        try{
            return rentalOrderService.updateRentalOrderStatus(updateRentalOrderDTO) > 0 ? Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("取消订单异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 修改订单流程状态
     * @param req
     * @return
     */
    @PostMapping(value = "/updateRentalOrderFlowStatus")
    public String updateRentalOrderFlowStatus(@RequestBody Map<String, Object> req) {
        logger.info("subject:{},req:{}","修改订单流程状态",JsonUtil.gsonStr(req));
        try{
            String status = StringUtil.objToStr(req.get("status"));
            String flowOneStatus = StringUtil.objToStr(req.get("flowOneStatus"));
            String flowTwoStatus = StringUtil.objToStr(req.get("flowTwoStatus"));
            String flowThreeStatus = StringUtil.objToStr(req.get("flowThreeStatus"));
            Integer rentalOrderId = Integer.valueOf(StringUtil.objToStr(req.get("rentalOrderId")));
            return rentalOrderService.updateRentalOrderFlowStatusById(status, flowOneStatus, flowTwoStatus, flowThreeStatus, rentalOrderId) > 0 ? Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("修改订单流程状态异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 根据车辆id查询最后一次还车里程
     * @param carId
     * @return
     */
    @PostMapping(value = "/getCarOilDesc")
    public String getCarOilDesc(@RequestParam("carId") Integer carId){
        logger.info("subject:{},carId:{}","根据车辆id查询最后一次还车里程",carId);
        try {
            CommCar commCar = rentalOrderService.getCarInfoByCarId(carId);
            logger.info("subject:{},commCar:{}","根据车辆id查询最后一次还车里程结果",JsonUtil.gsonStr(commCar));
            Map<String,Object> data = new HashMap<>();
            data.put("repayCarMileage",commCar == null ? "" : commCar.getMileage());
            data.put("repayCarOilAmount",commCar == null ? "" : commCar.getOilMass());
            return Result.success(data).toJSON();
        }catch (Exception e){
            logger.error("根据车辆id查询最后一次还车里程异常",e);
            return Result.failure().toJSON();
        }
    }


    /**
     * 查询外部订单列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getStoreRentalEnterpriseOrderList")
    public String getStoreRentalEnterpriseOrderList(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},dto:{}","查询外部订单列表",JsonUtil.gsonStr(dto));
        try{

            List<StoreRentalEnterpriseOrderVO> storeRentalEnterpriseOrderVOS = rentalEnterpriseOrderService.getStoreRentalEnterpriseOrderList(dto);
            logger.info("subject:{},storeRentalEnterpriseOrderVOS:{}","订单数据集",JsonUtil.gsonStr(storeRentalEnterpriseOrderVOS));
            return Result.success(storeRentalEnterpriseOrderVOS).toJSON();
        }catch (Exception e){
            logger.error("查询外部订单列表异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 外部订单:查看详情
     * @param dto
     * @return
     */
    @PostMapping(value = "/getStoreRentalEnterpriseOrderId")
    public String getStoreRentalEnterpriseOrderId(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},dto:{}","外部订单:查看详情",JsonUtil.gsonStr(dto));
        try{
            return rentalEnterpriseOrderService.getStoreRentalEnterpriseOrderId(dto);
        }catch (Exception e){
            logger.error("查看详情异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 外部订单:取消订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/cancelRentalEnterpriseOrder")
    public String cancelRentalEnterpriseOrder(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},dto:{}","外部订单:取消订单",JsonUtil.gsonStr(dto));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(dto.getOrderId());
            if(rentalEnterpriseOrder.getStatus() == RentalEnterpriseOrderConstant.ORDER_STATUS_98) return Result.failure("订单已被取消，请勿重复操作").toJSON();
            Result result = RentalEnterpriseOrderEnum.OrderStatus.cancelRentalEnterpriseOrder(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalEnterpriseOrder.setCloseBeforeStatus(rentalEnterpriseOrder.getStatus());
            rentalEnterpriseOrder.setCloseDate(new Date());
            rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderConstant.ORDER_STATUS_98);
            rentalEnterpriseOrder.setCancelSide(RentalEnterpriseOrderEnum.CancelSide.STATUS2.getStatus());
            rentalEnterpriseOrder.setCancelPersonId(dto.getUserId());
            rentalEnterpriseOrder.setCancelPerson(dto.getUserName());
            rentalEnterpriseOrder.setUpdateBy(dto.getUserId());
            return rentalEnterpriseOrderService.updateByPrimaryKeySelective(rentalEnterpriseOrder) > 0 ?  Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("外部订单:取消订单异常",e);
            return Result.failure().toJSON();
        }
    }
    /**
     * 外部订单:拒绝订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/rejectRentalEnterpriseOrder")
    public String rejectRentalEnterpriseOrder(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},dto:{}","外部订单:拒绝订单",JsonUtil.gsonStr(dto));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(dto.getOrderId());
            if(rentalEnterpriseOrder.getStatus() == RentalEnterpriseOrderConstant.ORDER_STATUS_99) return Result.failure("订单已被拒绝，请勿重复操作").toJSON();
            Result result = RentalEnterpriseOrderEnum.OrderStatus.rejectRentalEnterpriseOrder(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalEnterpriseOrder.setCloseBeforeStatus(rentalEnterpriseOrder.getStatus());
            rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderConstant.ORDER_STATUS_99);
            rentalEnterpriseOrder.setCancelSide(RentalEnterpriseOrderEnum.CancelSide.STATUS2.getStatus());
            rentalEnterpriseOrder.setCloseDate(new Date());
            rentalEnterpriseOrder.setRefusePersonId(dto.getUserId());
            rentalEnterpriseOrder.setRefusePerson(dto.getUserName());
            rentalEnterpriseOrder.setUpdateBy(dto.getUserId());
            return rentalEnterpriseOrderService.updateByPrimaryKeySelective(rentalEnterpriseOrder) > 0 ?  Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("外部订单:拒绝订单异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 外部订单:确认订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/notarizeRentalEnterpriseOrder")
    public String notarizeRentalEnterpriseOrder(@RequestBody RentalEnterpriseOrderDTO dto){
        logger.info("subject:{},dto:{}","外部订单:确认订单",JsonUtil.gsonStr(dto));
        try{
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderService.selectByPrimaryKey(dto.getOrderId());
            Result result = RentalEnterpriseOrderEnum.OrderStatus.notarizeRentalEnterpriseOrder(rentalEnterpriseOrder.getStatus(),rentalEnterpriseOrder.getFlowOneStatus(),rentalEnterpriseOrder.getFlowTwoStatus(),rentalEnterpriseOrder.getFlowThreeStatus());
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
            rentalEnterpriseOrder.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS20.getFlowOneStatus());
            rentalEnterpriseOrder.setUpdateBy(dto.getUserId());
            return rentalEnterpriseOrderService.updateByPrimaryKeySelective(rentalEnterpriseOrder) > 0 ?  Result.success("成功").toJSON() : Result.failure("失败").toJSON();
        }catch (Exception e){
            logger.error("外部订单:确认订单异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 外部订单:订单id查询车辆信息
     * @param dto
     * @return
     */
    @PostMapping(value = "/getRentalCheckCarByOrderId")
    public String getRentalCheckCarByOrderId(@RequestBody RentalCheckCarDTO dto){
        logger.info("subject:{},dto:{}"," 外部订单:订单id查询车辆信息",JsonUtil.gsonStr(dto));
        try{
            List<RentalCheckCar> rentalCheckCars = rentalCheckCarService.getRentalCheckCar(dto);
            logger.info("subject:{},rentalCheckCars:{}"," 外部订单:订单id查询车辆信息数据集",JsonUtil.gsonStr(rentalCheckCars));
            return Result.success(RentalCheckCarOderIdVO.getRentalCheckCarOderIdVOs(rentalCheckCars)).toJSON();
        }catch (Exception e){
            logger.error("外部订单:订单id查询车辆信息",e);
            return Result.failure().toJSON();
        }
    }
}
