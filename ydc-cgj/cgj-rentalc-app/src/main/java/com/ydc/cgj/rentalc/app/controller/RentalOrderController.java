package com.ydc.cgj.rentalc.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.rentalc.app.common.SubjectUtil;
import com.ydc.cgj.rentalc.app.service.RentalOrderService;
import com.ydc.commom.enums.rental.RentalOrderEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import com.ydc.model.cgj.rental.RentalOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * 租车订单
 *
 * @author
 * @create 2018-11-22 13:55
 **/
@RestController
@RequestMapping(value = "/rentalOrder")
public class RentalOrderController {

    private static final Logger logger = LogManager.getLogger(RentalOrderController.class);

    @Autowired
    RentalOrderService rentalOrderService;

    /**
     * 根据会员ID查询订单列表(C端)
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalOrderListCByMemberId")
    public String getRentalOrderListCByMemberId(@RequestParam("data") String data){
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        req.put("memberId", SubjectUtil.getMemberId());
        logger.info("subject:{},req:{}","根据会员ID查询订单列表(C端)参数",JsonUtil.gsonStr(req));
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
        if(!req.containsKey("memberId")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少会员ID参数").toJSON();
        }
        if(!req.containsKey("page")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少页码参数").toJSON();
        }
        if(!req.containsKey("rows")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少展示行数参数").toJSON();
        }
        return rentalOrderService.getRentalOrderListCByMemberId(req);
    }



    /**
     *  用户同意授权
     * @param data
     * @return
     */
    @PostMapping(value = "/consent/update")
    public String  updateConsentAuthorization(@RequestParam("data") String data){
        logger.info("subject:{},req:{}","根据会员ID查询订单列表(C端)参数",data);
        Integer memberId,orderId;
        String status;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
            memberId=SubjectUtil.getMemberId();
            orderId=jsonObject.getInteger("orderId");
            status=jsonObject.getString("status");
            Result<RentalOrder> result=rentalOrderService.updateConsentAuthorization(memberId,orderId,status);
            return result.toJSON();
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
    }



    /**
     * 查询订单详情(APP)
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalOrderDetailsAPP")
    public String getRentalOrderDetailsAPP(@RequestParam("data") String data) {
        logger.info("subject:{},req:{}","查询订单详情参数(APP)",data);
        Map<String, Object> req = JsonUtil.jsonToMap(data);
        if(req == null){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少查询参数").toJSON();
        }
        if(!req.containsKey("rentalOrderId")){
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少订单ID参数").toJSON();
        }
        return rentalOrderService.getRentalOrderDetailsAPPC(req);
    }

    /**
     * 取消订单用车
     * @param data
     * @return
     */
    @PostMapping(value = "/cancelUseCarOrder")
    public String cancelUseCarOrder(@RequestParam("data") String data) {
        logger.info("subject:{},updateRentalOrderDTO:{}","取消用车",data);
        UpdateRentalOrderDTO updateRentalOrderDTO= JSONObject.parseObject(data,UpdateRentalOrderDTO.class);
        return rentalOrderService.cancelUseCarOrder(updateRentalOrderDTO);
    }
}
