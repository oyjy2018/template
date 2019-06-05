package com.ydc.cgj.rentalb.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.IdemPotenceUtil;
import com.ydc.cgj.rentalb.app.service.DepositService;
import com.ydc.cgj.rentalb.app.service.RentalSettlementService;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.view.dto.cgj.rental.RentalPayWatercourseDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalSettlementDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 租车结算
 *
 * @author
 * @create 2018-11-26 16:42
 **/
@RestController
@RequestMapping(value = "/rentalSettlement")
public class RentalSettlementController {

    private final Logger logger = LogManager.getLogger(RentalSettlementController.class);

    @Autowired
    RentalSettlementService rentalSettlementService;
    @Autowired
    DepositService depositService;

    /**
     * 返回结算需要订单信息
     * @param
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:settle"})
    @PostMapping(value = "/getRentalSettlement")
    public String getRentalSettlement(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","返回结算需要订单信息",data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        return rentalSettlementService.getRentalSettlement(jsonObject.getInteger("orderId"));
    }

    /**
     * 首次结算信息入库
     * @param
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:settle"})
    @PostMapping(value = "/saveRentalSettlement")
    public String saveRentalSettlement(@RequestParam("data") String data){
        logger.info("subject:{},rentalSettlementDTO:{}","首次结算信息入库",data);
        RentalSettlementDTO rentalSettlementDTO = JSONObject.parseObject(data,RentalSettlementDTO.class);
        if(IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.FIRST_SETTLEMENT.getPrefix(),rentalSettlementDTO.getOrderId().toString(),Long.valueOf(10))){
            return Result.failure("请勿重复提交").toJSON();
        }
        Result result= rentalSettlementService.saveRentalSettlement(rentalSettlementDTO);
        RentalDeposit rentalDeposit=null;
        if (result.getCode()==ResultConstant.RESULT_CODE_SUCCESS  ) {
            rentalDeposit= (RentalDeposit) result.getData();
            if ( RentalDepositConstant.PAYMENT_MODE_1.compareTo(rentalDeposit.getPaymentMode())==0){
                logger.info("调用支付宝结算");
                new Thread(()->
                        rentalSettlementService.updateSettleAliPayDeposit(rentalSettlementDTO.getOrderId(),RentalDepositConstant.DEPOSIT_TYPE_1.intValue())).start();
               }
       }
        return result.toJSON();

    }

    /**
     * 新增租车结算流水
     * @param data
     * @return
     */
    @RequiresPermissions(value = {"rentalB:order:manage:order:view:settle"})
    @PostMapping(value = "/saveRentalPayWatercourse")
    public String saveRentalPayWatercourse(@RequestParam("data") String data){
        logger.info("subject:{},rentalPayWatercourseDTO:{}","首次结算信息入库",data);
        RentalPayWatercourseDTO rentalPayWatercourseDTO = JSONObject.parseObject(data,RentalPayWatercourseDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.NEXT_SETTLEMENT.getPrefix(),rentalPayWatercourseDTO.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON():
                rentalSettlementService.saveRentalPayWatercourse(rentalPayWatercourseDTO);
    }
}
