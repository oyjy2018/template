package com.ydc.cgj.rental.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.commom.CrackCreditUtil;
import com.ydc.beans.commom.tembin.ResponseData;
import com.ydc.beans.commom.tembin.TembinUtil;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.rental.web.feignService.OrderFeignService;
import com.ydc.cgj.rental.web.service.DictionaryDetailService;
import com.ydc.cgj.rental.web.service.MemberService;
import com.ydc.cgj.rental.web.service.RentalOrderService;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.DeleteStatusConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.TianchengConstant;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.constant.rental.RentalDictionaryConstant;
import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalOrderStatusOneEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.util.rental.RentalOrderNoUtil;
import com.ydc.commom.view.dto.cgj.rental.AddRentalOrderPCDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.vo.cgj.MemberApplicationVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalOrder;
import com.ydc.commom.util.AnnotationDealUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author
 * @create 2018-11-22 13:01
 **/
@Service
public class RentalOrderServiceImpl implements RentalOrderService {


    private static final Logger logger = LogManager.getLogger(RentalOrderService.class);


    @Autowired
    OrderFeignService orderFeignService;
    @Autowired
    MemberService memberService;
    @Autowired
    DictionaryDetailService dictionaryDetailService;


    @Override
    public String getCarLevelGroup() {
        return orderFeignService.getCarLevelGroup();
    }

    @Override
    public String getBrandByCarLevel(CommCarDTO commCarDTO) {
        return orderFeignService.getBrandByCarLevel(commCarDTO);
    }

    @Override
    public String getSeriesByBrand(CommCarDTO commCarDTO) {
        return orderFeignService.getSeriesByBrand(commCarDTO);
    }

    @Override
    public String getModelBySeries(CommCarDTO commCarDTO) {
        return orderFeignService.getModelBySeries(commCarDTO);
    }

    @Override
    public String insertOrder(AddRentalOrderPCDTO addRentalOrderPCDTO) {
        if(addRentalOrderPCDTO == null)return Result.failure("新增订单信息不能为空").toJSON();
        Map<String, Object> checkResult = AnnotationDealUtil.validate(addRentalOrderPCDTO);
        logger.info("subject:{},e:{}","校验参数",checkResult);
        if(checkResult.get("result").equals(false)){
            return Result.failure(checkResult.get("message").toString()).toJSON();
        }
        if(addRentalOrderPCDTO.getFetchCarMode() == 1){
            if(StringUtil.isEmpty(addRentalOrderPCDTO.getAppointmentStoreId())){
                return Result.failure("appointmentStoreId不能为空").toJSON();
            }
            if(StringUtil.isEmpty(addRentalOrderPCDTO.getAppointmentStoreName())){
                return Result.failure("appointmentStoreName不能为空").toJSON();
            }
        }

        if(addRentalOrderPCDTO.getRepayCarMode() == 1){
            if(StringUtil.isEmpty(addRentalOrderPCDTO.getAppointmentRepayStoreId())){
                return Result.failure("appointmentRepayStoreId不能为空").toJSON();
            }
            if(StringUtil.isEmpty(addRentalOrderPCDTO.getAppointmentRepayStoreName())){
                return Result.failure("appointmentRepayStoreName不能为空").toJSON();
            }
        }


        Date data = new Date();
        User user = WebShiroTokenManager.getUser();
        MemberApplicationVO memberApplicationVO = new MemberApplicationVO();
        memberApplicationVO.setMobilePhone(addRentalOrderPCDTO.getMobilePhone());
        memberApplicationVO.setMemberName(addRentalOrderPCDTO.getName());
        memberApplicationVO.setIdCard(addRentalOrderPCDTO.getIdCard());
        memberApplicationVO.setApplication(MemberConstant.APPLICATION_RENTAL);
        memberApplicationVO.setMemberStatus(MemberConstant.MEMBER_NORMAL_STATUS);
        memberApplicationVO.setStatus(MemberConstant.MEMBER_NORMAL_STATUS);
        memberApplicationVO.setWhetherRealName(CodeConstant.NOT);
        memberApplicationVO.setCreateTime(data);
        memberApplicationVO.setUpdateTime(data);
        memberApplicationVO.setUpdateBy(user.getId());
        Result result = memberService.rentalOrderMember(memberApplicationVO);
        logger.info("subject:{},result:{}","会员信息校验",JsonUtil.gsonStr(result));
        if(result == null){
            return Result.failure("会员信息校验失败").toJSON();
        }
        if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
            return result.toJSON();
        }
        Integer memberId= (Integer) result.getData();
        //暂时屏蔽
//        RentalOrder rentalOrder = getRentalOrderByMemberId(member.getId());
//        if(rentalOrder != null && (rentalOrder.getStatus() != 7 || rentalOrder.getStatus() != 98 || rentalOrder.getStatus() != 99)){
//            return Result.failure("会员存在一笔未结算的单").toJSON();
//        }
        addRentalOrderPCDTO.setMemberId(memberId);
        addRentalOrderPCDTO.setCreateBy(user.getId());
        addRentalOrderPCDTO.setCreateTime(data);
        //调风控接空：校验录入的姓名和身份证号是否正确
        //调天秤校验身份证号
        addRentalOrderPCDTO.setStatus(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_1);
        addRentalOrderPCDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_0.getFlowOneStatus());
        if("on".equalsIgnoreCase(SystemPropertiesConfig.NEW_TIANCHENG_SWITCH) && result.getCode() == CodeConstant.NOT){
            ResponseData<String> responseData = TembinUtil.idCardVerification(addRentalOrderPCDTO.getName(),
                    addRentalOrderPCDTO.getIdCard());
            logger.info("subject:{},responseData:{}","天秤接口响应数据",JsonUtil.gsonStr(responseData));
            //调用成功
            if(TianchengConstant.IDENTITY_SUCCESS_CODE.equals(responseData.getHeader().getRtCode())){
                Map<String, Object> map = JsonUtil.jsonToMap(responseData.getBusinessData());
                if (!TianchengConstant.IDENTITY_BUSINESS_DATA_SUCCESS.equals(map.get(TianchengConstant.RESPONSE_RESULT))){
                    return Result.failure("认证失败，姓名和身份证号不匹配").toJSON();
                }else if(TianchengConstant.IDENTITY_ABNORMAL_CODE.equals(responseData.getHeader().getRtCode())
                        || TianchengConstant.IDENTITY_FAILING_CODE.equals(responseData.getHeader().getRtCode())){
                    addRentalOrderPCDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_9.getFlowOneStatus());
                }else{
                    addRentalOrderPCDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_10.getFlowOneStatus());
                }
            }
        }else{
            addRentalOrderPCDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_10.getFlowOneStatus());
        }
        if(result.getCode() != CodeConstant.HAD
                && addRentalOrderPCDTO.getFlowOneStatus() == RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_10.getFlowOneStatus()){
            memberApplicationVO = new MemberApplicationVO();
            memberApplicationVO.setId(memberId);
            memberApplicationVO.setWhetherRealName(CodeConstant.HAD);
            memberApplicationVO.setUpdateBy(user.getId());
            result = memberService.updateMemberWhetherRealNameStatus(memberApplicationVO);
            logger.info("subject:{},result:{}","会员实名认证处理",JsonUtil.gsonStr(result));
            if(result == null){
                return Result.failure("会员实名认证更新失败").toJSON();
            }
            if(result.getCode() == ResultConstant.RESULT_CODE_FAILURE){
                return result.toJSON();
            }
        }

        if("on".equalsIgnoreCase(SystemPropertiesConfig.ALIYUN_CRACKCREDIT_SWITCH)
                && addRentalOrderPCDTO.getFlowOneStatus() == RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_10.getFlowOneStatus()){
            //风控查询
            JSONObject jsonObject = CrackCreditUtil.getJSONObject(addRentalOrderPCDTO.getName(),addRentalOrderPCDTO.getIdCard());
            logger.info("subject:{},jsonObject:{}","风控查询结果",jsonObject);
            if(jsonObject.getInteger("code") == CrackCreditUtil.CrackCreditEnum.RESULT_CODE_0.getCode()){
                addRentalOrderPCDTO.setStatus(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_99);
                addRentalOrderPCDTO.setCloseTime(data);
                addRentalOrderPCDTO.setCloseCause("风控不通过");
                addRentalOrderPCDTO.setCloseBeforeStatus(addRentalOrderPCDTO.getStatus());
                addRentalOrderPCDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_0.getFlowOneStatus());
            }else if(jsonObject.getInteger("code") == CrackCreditUtil.CrackCreditEnum.RESULT_CODE_1008.getCode()){
                return Result.failure("输入的查询人的身份证号不正确").toJSON();
            }else if(jsonObject.getInteger("code") == CrackCreditUtil.CrackCreditEnum.RESULT_CODE_1100.getCode()){
                addRentalOrderPCDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.getFlowOneStatus());
            }else{
                addRentalOrderPCDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_96.getFlowOneStatus());
            }
        }else{
            addRentalOrderPCDTO.setFlowOneStatus(RentalOrderStatusOneEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.getFlowOneStatus());
        }
        addRentalOrderPCDTO.setOrderNo(RentalOrderNoUtil.getOrderNo());

        //租赁押金
        RentalDeposit rentalDeposit = new RentalDeposit();
        rentalDeposit.setMemberId(memberId);
        Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(RentalDictionaryConstant.RENTAL_CAR_AUTHORIZATION_AMOUNT, RentalDictionaryConstant.AUTHORIZATION_AMOUNT)
                .orElseGet(() -> dictionaryDetailService.getDictionaryDetailByDictKey(RentalDictionaryConstant.RENTAL_CAR_AUTHORIZATION_AMOUNT, RentalDictionaryConstant.AUTHORIZATION_AMOUNT)));
        if(addRentalOrderPCDTO.getRentalAccreditMoney()== null
                || addRentalOrderPCDTO.getRentalAccreditMoney().compareTo(BigDecimal.valueOf(0.0)) == 0){
            rentalDeposit.setPayableAmount(dic.isPresent() ? BigDecimal.valueOf(Double.valueOf(dic.get().getDictValue())) : BigDecimal.valueOf(5000));
        }else{
            rentalDeposit.setPayableAmount(addRentalOrderPCDTO.getRentalAccreditMoney());
        }
        rentalDeposit.setRentCarOrderNo(addRentalOrderPCDTO.getOrderNo());

        rentalDeposit.setPaymentMode(dic.isPresent() ? StringUtil.isEmpty(dic.get().getRemark2()) ? RentalDepositConstant.PAYMENT_MODE_1 : Integer.valueOf(dic.get().getRemark2()).byteValue() : RentalDepositConstant.PAYMENT_MODE_1);
        rentalDeposit.setOrderType(RentalDepositEnum.OrderType.STATUS1.getStatus());
        rentalDeposit.setDepositType(RentalDepositConstant.DEPOSIT_TYPE_1);
        rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_1);
        rentalDeposit.setSerialNum(UUID.randomUUID().toString());
        rentalDeposit.setCreateTime(data);
        rentalDeposit.setCreateBy(user.getId());
        rentalDeposit.setDeleteStatus(DeleteStatusConstant.STATUS_NOT_DELETE);
        addRentalOrderPCDTO.setRentalDeposit(rentalDeposit);
        return orderFeignService.insertOrder(addRentalOrderPCDTO);
    }

    @Override
    public String getAllStore() {
        return orderFeignService.getAllStore();
    }

    @Override
    public String getRentalOrderList(Map<String, Object> req) {
        return orderFeignService.getRentalOrderList(req);
    }

    @Override
    public RentalOrder getRentalOrderByMemberId(Integer memberId) {
        return orderFeignService.getRentalOrderByMemberId(memberId);
    }

    public static void main(String[] args) {
       logger.info(DateUtil.format(new Date(),DateUtil.NUM_YEAR_MONTH_TIME)+String.valueOf((int)((Math.random()*9+1)*100000)));
       logger.info((int)((Math.random()*9+1)*10000));
    }

    @Override
    public String getRentalOrderDetailsPC(Map<String, Object> req) {
        return orderFeignService.getRentalOrderDetailsPC(req);
    }

    @Override
    public String updateRentalOrderFlowStatus(Map<String, Object> req) {
        return orderFeignService.updateRentalOrderFlowStatus(req);
    }

    @Override
    public RentalOrder getRentalOrderByOrderId(Integer orderId) {
        return orderFeignService.getRentalOrderByOrderId(orderId);
    }
}
