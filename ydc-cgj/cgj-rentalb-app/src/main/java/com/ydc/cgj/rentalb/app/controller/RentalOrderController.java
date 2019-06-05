package com.ydc.cgj.rentalb.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.IdemPotenceUtil;
import com.ydc.cgj.rentalb.app.service.RentalOrderService;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RepayCarOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 租车订单
 *
 * @author
 * @create 2018-11-22 14:45
 **/
@RestController
@RequestMapping(value = "/rentalOrder")
public class RentalOrderController {

    private static final Logger logger = LogManager.getLogger(RentalOrderController.class);



    @Autowired
    RentalOrderService rentalOrderService;

    /**
     * 获取出车信息
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:comeCar"})
    @PostMapping(value = "/getComeCarData")
    public String getComeCarData(@RequestParam("data") String data){
        logger.info("subject:{},commCarDTO:{}","获取出车信息",data);
        CommCarDTO commCarDTO =  JSONObject.parseObject(data,CommCarDTO.class);
        return rentalOrderService.getComeCarData(commCarDTO);
    }

    /**
     * 出车更新订单信息
     * @param
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:comeCar"})
    @PostMapping(value = "/updateComeCarOrder")
    public String updateComeCarOrder(@RequestParam("data") String data ){
        logger.info("subject:{},comeCarOrderDTO:{}","出车更新订单信息",data);
        ComeCarOrderDTO comeCarOrderDTO = JSONObject.parseObject(data,ComeCarOrderDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.COME_CAR.getPrefix(),comeCarOrderDTO.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() :
                rentalOrderService.updateComeCarOrder(comeCarOrderDTO);
    }

    /**
     * 获取还车信息
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:repayCar"})
    @PostMapping(value = "/getRepayCarData")
    public String getRepayCarData(@RequestParam("data") String data){
        logger.info("subject:{},commCarDTO:{}","获取还车信息",data);
        CommCarDTO commCarDTO = JSONObject.parseObject(data,CommCarDTO.class);
        return rentalOrderService.getRepayCarData(commCarDTO);
    }

    /**
     * 还车更新订单信息
     * @param
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:repayCar"})
    @PostMapping(value = "/updateRepayCarOrder")
    public String updateRepayCarOrder(@RequestParam("data") String data){
        logger.info("subject:{},repayCarOrderDTO:{}","还车更新订单信息",data);
        RepayCarOrderDTO repayCarOrderDTO  = JSONObject.parseObject(data,RepayCarOrderDTO.class);
        return rentalOrderService.updateRepayCarOrder(repayCarOrderDTO);
    }

    /**
     * 还车的时候——更新出车订单信息
     * @param
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:repayCar"})
    @PostMapping(value = "/updateComeCarOrderInRepayCar")
    public String updateComeCarOrderInRepayCar(@RequestParam("data") String data ){
        logger.info("subject:{},comeCarOrderDTO:{}","还车的时候——更新出车订单信息",data);
        ComeCarOrderDTO comeCarOrderDTO = JSONObject.parseObject(data,ComeCarOrderDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.REPAY_CAR.getPrefix(),comeCarOrderDTO.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalOrderService.updateComeCarOrderInRepayCar(comeCarOrderDTO);
    }

    /**
     * 根据门店ID查询订单列表(B端)
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:query"})
    @PostMapping(value = "/getRentalOrderListBByStoreId")
    public String getRentalOrderListBByStoreId(@RequestParam("data") String data){
        logger.info("subject:{},req:{}","根据门店ID查询订单列表(B端)参数",data);
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if(req == null){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少列表参数").toJSON();
        }
        if(req.containsKey("status") && !"".equals(req.get("status").toString())){
            String status = req.get("status").toString();
            RentalOrderEnum.StatusReqEnum rose = RentalOrderEnum.StatusReqEnum.getEnumByStatus(status);
            if(rose == null){
                return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "此订单状态不存在").toJSON();
            }
            req.putAll(rose.getSearchParamMap());
        }
        if(!req.containsKey("page")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少页码参数").toJSON();
        }
        if(!req.containsKey("rows")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少展示行数参数").toJSON();
        }
        return rentalOrderService.getRentalOrderListBByStoreId(req);
    }

    /**
     * 查询订单详情(APP)
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:detail"})
    @PostMapping(value = "/getRentalOrderDetailsAPP")
    public String getRentalOrderDetailsAPP(@RequestParam("data") String data) {
        logger.info("subject:{},req:{}","查询订单详情参数(APP)",data);
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if(!req.containsKey("rentalOrderId")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少订单ID参数").toJSON();
        }
        return rentalOrderService.getRentalOrderDetailsAPPB(req);
    }

    /**
     * 取消订单用车
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:cancelOrder"})
    @PostMapping(value = "/cancelUseCarOrder")
    public String cancelUseCarOrder(@RequestParam("data") String data) {
        logger.info("subject:{},updateRentalOrderDTO:{}","取消用车",data);
        UpdateRentalOrderDTO updateRentalOrderDTO= JSONObject.parseObject(data,UpdateRentalOrderDTO.class);
        return rentalOrderService.cancelUseCarOrder(updateRentalOrderDTO);
    }

    /**
     * 根据车辆id查询最后一次还车里程
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:comeCar"})
    @PostMapping(value = "/getCarOilDesc")
    public String getCarOilDesc(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","根据车辆id查询最后一次还车里程",data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        return rentalOrderService.getCarOilDesc(jsonObject.getInteger("carId"));
    }
}
