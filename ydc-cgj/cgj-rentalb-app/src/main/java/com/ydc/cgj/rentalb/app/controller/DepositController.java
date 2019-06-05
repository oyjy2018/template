package com.ydc.cgj.rentalb.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.rentalb.app.common.UserUtil;
import com.ydc.cgj.rentalb.app.service.DepositService;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.BigDecimalUtil;
import com.ydc.commom.view.dto.cgj.rental.ManualFreezeRentalDepositDTO;
import com.ydc.model.cgj.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(value = "/deposit")
public class DepositController {


    private static final Logger logger = LogManager.getLogger(DepositController.class);

    @Autowired
    private DepositService depositService;
    /**
     *   生成押金二维码数据
     * @param data  type:1 租车  2：违章
     * @return
     */
    @PostMapping(value = "/qrcode/create")
    public String createDepositQrCode(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}","生成押金二维码数据",data);
        Integer orderId,type;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
             orderId=jsonObject.getInteger("orderId");
             type=jsonObject.getInteger("type");
        }catch (Exception e){
            return Result.failure("参数错误").toJSON();
        }
        return depositService.getOrderDepositQrCode(orderId,type).toJSON();
    }

    /**
     * 手动冻结押金
     * @param data
     *  depositType;//押金类型   1:表示租车冻结  2：表示违章冻结
     *  paymentMode: 2:信1·用卡  3：现金
     * @return
     */
    @PostMapping(value = "/freeze/manual/update")
    public String manualOrderDeposit(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}","手动冻结押金数据",data);
        Integer orderId,depositType,paymentMode;
        BigDecimal amount;Date paymentTime;
        try {
            JSONObject jsonObject=JSON.parseObject(data);
             orderId=jsonObject.getInteger("orderId");
             depositType=jsonObject.getInteger("depositType");
             paymentMode=jsonObject.getInteger("paymentMode");
             amount =jsonObject.getBigDecimal("amount");//押金金额
             paymentTime=jsonObject.getDate("paymentTime");
            if (depositType == null ||
                    !(depositType.byteValue()== RentalDepositConstant.DEPOSIT_TYPE_1.byteValue()
                            || depositType.byteValue()== RentalDepositConstant.DEPOSIT_TYPE_2.byteValue())){
                return  Result.failure("参数错误").toJSON();
            }
            if (paymentMode == null ||
                   !(paymentMode.byteValue()== RentalDepositConstant.PAYMENT_MODE_2.byteValue() ||
                    paymentMode.byteValue()== RentalDepositConstant.PAYMENT_MODE_3.byteValue())){
                return  Result.failure("参数错误").toJSON();
            }
            if (!BigDecimalUtil.between(amount,BigDecimal.ZERO,new BigDecimal(1000000))){
                return  Result.failure("参数错误").toJSON();
            }
        }catch (Exception e){
            return  Result.failure("参数错误").toJSON();
        }
        User user=UserUtil.getUser();
        ManualFreezeRentalDepositDTO manualFreezeRentalDepositDTO=new ManualFreezeRentalDepositDTO();
        manualFreezeRentalDepositDTO.setPaymentMode(paymentMode.byteValue());
        manualFreezeRentalDepositDTO.setOrderId(orderId);
        manualFreezeRentalDepositDTO.setAmount(amount);
        manualFreezeRentalDepositDTO.setDepositType(depositType.byteValue());
        manualFreezeRentalDepositDTO.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_2);
        manualFreezeRentalDepositDTO.setOperationMemberId(user.getId());
        manualFreezeRentalDepositDTO.setOperationMemberName(user.getUserName());
        manualFreezeRentalDepositDTO.setPaymentTime(paymentTime);
        return depositService.manualOrderDeposit(manualFreezeRentalDepositDTO).toJSON();
    }

}
