package com.ydc.cgj.rental.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.IdemPotenceUtil;
import com.ydc.cgj.rental.web.service.RentalEnterpriseOrderService;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.ObjectUtil;
import com.ydc.commom.util.ParamVaildateUtil;
import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.model.cgj.rental.RentalCar;
import com.ydc.model.cgj.rental.RentalOrderFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2019-01-04 16:37
 **/
@RestController
@RequestMapping(value = "/rentalEnterpriseOrder")
public class RentalEnterpriseOrderController {

    private final Logger logger = LogManager.getLogger(RentalEnterpriseOrderController.class);

    @Autowired
    RentalEnterpriseOrderService rentalEnterpriseOrderService;

    /**
     * 企业租车后台列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getPCRentalEnterpriseOrderList")
    public String getPCRentalEnterpriseOrderList(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","企业租车后台列表",data);
        RentalEnterpriseOrderDTO dto = JSONObject.parseObject(data,RentalEnterpriseOrderDTO.class);
        RentalEnterpriseOrderEnum.QueryStatusEnum.queryStatusSet(dto);
        return rentalEnterpriseOrderService.getPCRentalEnterpriseOrderList(dto);
    }

    /**
     * 企业租车后台列表:车辆车型
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarSeries")
    public String getCarSeries(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","企业租车后台列表:车辆车型",data);
        CommCarSeriesDTO dto = JSONObject.parseObject(data,CommCarSeriesDTO.class);
        return rentalEnterpriseOrderService.getCarSeries(dto);
    }

    /**
     * 企业租车后台列表:车辆车系
     * @param data
     * @return
     */
    @PostMapping(value = "/getCarModel")
    public String getCarModel(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","企业租车后台列表:车辆车系",data);
        CommCarSeriesDTO dto = JSONObject.parseObject(data,CommCarSeriesDTO.class);
        return rentalEnterpriseOrderService.getCarModel(dto);
    }


    /**
     * 企业租车后台列表:订单详情
     * @param data
     * @return
     */
    @PostMapping(value = "/getPCRentalEnterpriseOrderId")
    public String getPCRentalEnterpriseOrderId(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","企业租车后台列表:订单详情",data);
        RentalEnterpriseOrderDTO dto = JSONObject.parseObject(data,RentalEnterpriseOrderDTO.class);
        return rentalEnterpriseOrderService.getPCRentalEnterpriseOrderId(dto);
    }

    /**
     * 企业租车后台列表:上传订单资料
     * @param data
     * @return
     */
    @PostMapping(value = "/insertPCRentalEnterpriseOrderFile")
    public String insertPCRentalEnterpriseOrderFile(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","企业租车后台列表:上传订单资料",data);
        Map map = ParamVaildateUtil.vaildateAndTransfer(data, RentalOrderFile.class);
        if ("1".equals(map.get("code"))){
            return Result.failure(map.get("message").toString()).toJSON();
        }
        RentalOrderFile dto = (RentalOrderFile)map.get("object");
        return rentalEnterpriseOrderService.insertPCRentalEnterpriseOrderFile(dto);
    }

    /**
     * 企业租车后台列表:查询初始化参数
     * @return
     */
    @PostMapping(value = "/getPCRentalEnterpriseOrderInit")
    public String getPCRentalEnterpriseOrderInit(){
        logger.info("subject:{}","企业租车后台列表:查询初始化参数");
        return rentalEnterpriseOrderService.getPCRentalEnterpriseOrderInit();
    }

    /**
     * 企业租车后台列表:查询订单资料
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalOrderFiles")
    public String getRentalOrderFiles(@RequestParam("data") String data){
        logger.info("subject:{}","企业租车后台列表:查询订单资料");
        RentalOrderFile rentalOrderFile = JSONObject.parseObject(data,RentalOrderFile.class);
        return rentalEnterpriseOrderService.getRentalOrderFiles(rentalOrderFile);
    }

    /**
     * 企业租车后台列表:删除订单资料
     * @param data
     * @return
     */
    @PostMapping(value = "/deleteRentalEnterpriseOrderFile")
    public String deleteRentalEnterpriseOrderFile(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","企业租车后台列表:上传订单资料",data);
        RentalOrderFile rentalOrderFile = JSONObject.parseObject(data,RentalOrderFile.class);
        return rentalEnterpriseOrderService.deleteRentalEnterpriseOrderFile(rentalOrderFile);
    }

    /**
     * 企业租车后台列表:已交保证金：配置信息
     * @return
     */
    @PostMapping(value = "/paymentDepositConfiguration")
    public String paymentDepositConfiguration(){
        logger.info("subject:{}","企业租车后台列表:已交保证金：配置信息");
        return rentalEnterpriseOrderService.paymentDepositConfiguration();
    }

    /**
     * 企业租车后台列表:已交保证金
     * @param data
     * @return
     */
    @PostMapping(value = "/paymentDeposit")
    public String paymentDeposit(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","企业租车后台列表:已交保证金",data);
        Map map = ParamVaildateUtil.vaildateAndTransfer(data, PaymentDepositDTO.class);
        if ("1".equals(map.get("code"))){
            return Result.failure(map.get("message").toString()).toJSON();
        }
        PaymentDepositDTO paymentDepositDTO = (PaymentDepositDTO)map.get("object");
        RentalDepositDTO dto = new RentalDepositDTO();
        ObjectUtil.copyProperties(dto,paymentDepositDTO);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_PAYMENT_DEPOSIT.getPrefix(),dto.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.paymentDeposit(dto);
    }

    /**
     * 企业租车后台列表:保证金已退还：配置信息
     * @return
     */
    @PostMapping(value = "/refundDepositConfiguration")
    public String refundDepositConfiguration(){
        logger.info("subject:{}","企业租车后台列表:保证金已退还：配置信息");
        return rentalEnterpriseOrderService.refundDepositConfiguration();
    }

    /**
     * 企业租车后台列表:保证金已退还
     * @param data
     * @return
     */
    @PostMapping(value = "/refundDeposit")
    public String refundDeposit(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:保证金已退还", JsonUtil.gsonStr(data));
        Map map = ParamVaildateUtil.vaildateAndTransfer(data, RefundDepositDTO.class);
        if ("1".equals(map.get("code"))){
            return Result.failure(map.get("message").toString()).toJSON();
        }
        RefundDepositDTO refundDepositDTO = (RefundDepositDTO)map.get("object");
        RentalDepositDTO dto = new RentalDepositDTO();
        ObjectUtil.copyProperties(dto,refundDepositDTO);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_REFUND_DEPOSIT.getPrefix(),dto.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.refundDeposit(dto);
    }

    /**
     * 企业租车后台列表:录入租金支付信息
     * @return
     */
    @PostMapping(value = "/insertRentPayment")
    public String insertRentPayment(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:录入租金支付信息", JsonUtil.gsonStr(data));
        Map map = ParamVaildateUtil.vaildateAndTransfer(data,InsertRentPaymentDTO.class);
        if ("1".equals(map.get("code"))){
            return Result.failure(map.get("message").toString()).toJSON();
        }
        InsertRentPaymentDTO insertRentPaymentDTO = (InsertRentPaymentDTO)map.get("object");
        RentalFeeVoucherDTO dto = new RentalFeeVoucherDTO();
        ObjectUtil.copyProperties(dto,insertRentPaymentDTO);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_RENT_PAYMENT.getPrefix(),dto.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.insertRentPayment(dto);
    }

    /**
     * 企业租车后台列表:录入出车信息
     * @return
     */
    @PostMapping(value = "/insertRentalCheckComeCar")
    public String insertRentalCheckComeCar(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:录入出车信息", JsonUtil.gsonStr(data));
        List<RentalCheckCarDTO> list = JsonUtil.jsonToList(JsonUtil.jsonToMap(data).get("rentalCheckCarDTO").toString(),RentalCheckCarDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_COME_CAR.getPrefix(),list.get(0).getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.insertRentalCheckComeCar(list);
    }

    /**
     * 企业租车后台列表:录入租金转账信息
     * @return
     */
    @PostMapping(value = "/insertRentTransfer")
    public String insertRentTransfer(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:录入租金转账信息", JsonUtil.gsonStr(data));
        Map map = ParamVaildateUtil.vaildateAndTransfer(data,InsertRentTransferDTO.class);
        if ("1".equals(map.get("code"))){
            return Result.failure(map.get("message").toString()).toJSON();
        }
        InsertRentTransferDTO insertRentTransferDTO = (InsertRentTransferDTO)map.get("object");
        RentalFeeVoucherDTO dto = new RentalFeeVoucherDTO();
        ObjectUtil.copyProperties(dto,insertRentTransferDTO);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_RENT_TRANSFER.getPrefix(),dto.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.insertRentTransfer(dto);
    }

    /**
     * 企业租车后台列表:获取还车信息
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalCheckRepayCar")
    public String getRentalCheckRepayCar(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:获取还车信息", JsonUtil.gsonStr(data));
        RentalCheckCarDTO dto = JsonUtil.jsonToBean(data, RentalCheckCarDTO.class);
        return rentalEnterpriseOrderService.getRentalCheckRepayCar(dto);
    }

    /**
     * 企业租车后台列表:录入还车信息
     * @return
     */
    @PostMapping(value = "/insertRentalCheckRepayCar")
    public String insertRentalCheckRepayCar(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:录入出车信息", JsonUtil.gsonStr(data));
        List<RentalCheckCarDTO> list = JsonUtil.jsonToList(JsonUtil.jsonToMap(data).get("rentalCheckCarDTO").toString(),RentalCheckCarDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_REPAY_CAR.getPrefix(),list.get(0).getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.insertRentalCheckRepayCar(list);
    }

    /**
     * 企业租车后台列表:录入结算信息：配置信息
     * @return
     */
    @PostMapping(value = "/enterpriseSettlementConfiguration")
    public String enterpriseSettlementConfiguration(){
        logger.info("subject:{}","企业租车后台列表:录入结算信息：配置信息");
        return rentalEnterpriseOrderService.enterpriseSettlementConfiguration();
    }

    /**
     * 企业租车后台列表:录入结算信息
     * @return
     */
    @PostMapping(value = "/insertEnterpriseSettlement")
    public String insertEnterpriseSettlement(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:录入结算信息", JsonUtil.gsonStr(data));
        Map map = ParamVaildateUtil.vaildateAndTransfer(data,RentalEnterpriseSettlementDTO.class);
        if ("1".equals(map.get("code"))){
            return Result.failure(map.get("message").toString()).toJSON();
        }
        RentalEnterpriseSettlementDTO dto = (RentalEnterpriseSettlementDTO)map.get("object");
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_SETTLEMENT.getPrefix(),dto.getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.insertEnterpriseSettlement(dto);
    }

    /**
     * 企业租车后台列表:录入押金退还信息
     * @return
     */
    @PostMapping(value = "/insertDepositRefund")
    public String insertDepositRefund(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:录入押金退还信息", JsonUtil.gsonStr(data));
        List<RentalFeeVoucherDTO> list = JsonUtil.jsonToList(JsonUtil.jsonToMap(data).get("rentalFeeVoucherDTO").toString(),RentalFeeVoucherDTO.class);
        return IdemPotenceUtil.preventRepetitionSubmit(IdemPotenceUtil.IdemPotenceEnmu.RENTAL_ENTERPRISE_ORDER_DEPOSIT_REFUND.getPrefix(),list.get(0).getOrderId().toString(),Long.valueOf(10)) ?
                Result.failure("请勿重复提交").toJSON() : rentalEnterpriseOrderService.insertDepositRefund(list);
    }

    /**
     * 手动解绑支付宝
     * @param data
     * @return
     */
    @PostMapping(value = "/unfreezeAlipay")
    public String unfreezeAlipay(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","手动解绑支付宝", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JsonUtil.jsonToBean(data, RentalEnterpriseOrderDTO.class);
        return rentalEnterpriseOrderService.unfreezeAlipay(dto);
    }


    /**
     * 企业租车后台列表:上传资料列表
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalEnterpriseOrderFile")
    public String getRentalEnterpriseOrderFile(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:上传资料列表", JsonUtil.gsonStr(data));
        RentalEnterpriseOrderDTO dto = JSONObject.parseObject(data,RentalEnterpriseOrderDTO.class);
        return rentalEnterpriseOrderService.getRentalEnterpriseOrderFile(dto);
    }

    /**
     * 企业租车后台列表:获取已发布车辆
     * @param data
     * @return
     */
    @PostMapping(value = "/getRentalCar")
    public String getRentalCar(@RequestParam("data") String data){
        logger.info("subject:{},dto:{}","企业租车后台列表:获取已发布车辆", JsonUtil.gsonStr(data));
        RentalCar rentalCar = JSONObject.parseObject(data,RentalCar.class);
        return rentalEnterpriseOrderService.getRentalCar(rentalCar);
    }
}
