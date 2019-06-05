package com.ydc.service.order.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.ydc.beans.commom.OrderNoUtil;
import com.ydc.beans.config.OrderNoConfig;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.mq.sms.service.SMSSendMessageService;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.OrderConstant;
import com.ydc.commom.constant.PayConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.urlHttp.DefaultCallBack;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.util.AliyunSmsUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.vo.cgj.order.AddOrderResponseVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import com.ydc.dao.cgj.order.OrderDao;
import com.ydc.dao.cgj.user.IntegralDao;
import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.dao.cgj.user.MemberWaterDao;
import com.ydc.model.cgj.*;
import com.ydc.service.order.service.DictionaryDetailService;
import com.ydc.service.order.service.OrderService;
import com.ydc.service.order.service.PayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 积分支付
 */
@Service(value = PayConstant.PAY_INTEGRAL)
public class IntegralPayServiceImpl implements PayService {
    private static Logger logger = LogManager.getLogger(IntegralPayServiceImpl.class);

    @Resource
    private OrderNoConfig orderNoPayWaterConfig;
    @Resource
    private IntegralDao integralDao;
    @Resource
    private MemberDao memberDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderService orderService;
    @Resource
    private MemberWaterDao memberWaterDao;
    @Resource
    private DictionaryDetailService dictionaryDetailService;
    @Resource
    private SMSSendMessageService smsSendMessageService;

    @Override
    @Transactional(value="cgjTransactionManager")
    public Result updateOrderPay(PayOrderParamVO payOrderParamVO, Integer memberId, List<Order> orderList) throws Exception {
        Date currentDate = new Date();
        AddOrderResponseVO addOrderResponseVO = new AddOrderResponseVO();
        List<String> orderNoS = new ArrayList<>();
        List<Integer> orderIds = new ArrayList<>();
        String payWater = OrderNoUtil.getOrderNo(orderNoPayWaterConfig);
        //扣除用户积分
        if (!orderService.updateMemberIntegral(memberId, payOrderParamVO.getSellPrice(), 0, payWater)) {
            throw new ServiceRuntimeException("余额扣除失败");
        }
        //添加流水
        Member member = memberDao.selectByPrimaryKey(memberId);
        Integral integral = integralDao.getIntegralByMumberId(member.getId());
        MemberWater memberWater = new MemberWater();
        memberWater.setMemberId(member.getId());
        memberWater.setMemberName(member.getMemberName());
        memberWater.setMobilePhone(member.getMobilePhone());
        memberWater.setIntegralAmount(payOrderParamVO.getSellPrice());
        memberWater.setCashAmount(BigDecimal.ZERO);
        memberWater.setTransactionMethod(1);
        memberWater.setTransactionType(1);
        memberWater.setWaterType(1);
        memberWater.setWaterStatus(2);
        memberWater.setChangeIntegralBalance(integral.getUsableIntegral().subtract(payOrderParamVO.getSellPrice()));
        memberWater.setChangeCashBalance(BigDecimal.ZERO);
        memberWater.setConsumeType(0);
        memberWater.setPayWater(payWater);
        memberWater.setStatus(CodeConstant.NORMAL_STATUS);
        memberWater.setCreateTime(new Date());
        memberWater.setCreateBy(1);
        memberWater.setUpdateTime(new Date());
        memberWater.setUpdateBy(1);
        memberWaterDao.insertSelective(memberWater);

        StringBuilder orderNoSB = new StringBuilder();
        int size = orderList.size();
        //更新订单
        Map<String, Object> param = new HashMap<>();
        param.put("orderNos", payOrderParamVO.getOrderNOList());
        param.put("oldStatus", OrderConstant.ORDER_STATUS_UNPAID);
        param.put("newStatus", OrderConstant.ORDER_STATUS_PAID_UNDELIVERED);
        param.put("updateTime", currentDate);
        param.put("paymentTime", currentDate);
        param.put("logisticsStatus", 0);
        param.put("payWater", payWater);
        param.put("paymentMethod", payOrderParamVO.getPaymentMethod());
        param.put("paymentCurrency", PayConstant.PaymentCurrencyEnum.getPaymentCurrencyByMethod(payOrderParamVO.getPaymentMethod()));
        int count = orderDao.updateBatchByStatus(param);
        if (payOrderParamVO.getOrderNOList().size() != count) {
            throw new ServiceRuntimeException("请重新刷新订单");
        }
        for (int i = 0; i < size; i++) {
            Order order = orderList.get(i);
            orderNoS.add(order.getOrderNo());
            orderIds.add(order.getId());
            orderNoSB.append(order.getOrderNo());
            if (i != size - 1) {
                orderNoSB.append(",");
            }
        }
        addOrderResponseVO.setOrderNOList(orderNoS);
        addOrderResponseVO.setOrderIdList(orderIds);
        addOrderResponseVO.setPayType(payOrderParamVO.getPaymentMethod());

        //发送短信验证码是否打开
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW));
        if (dictionaryDetail != null && ("1").equals(dictionaryDetail.getDictValue())) {
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)));
            if (dic.isPresent() && dic.get().getDictValue().equals("1")) {
                //发送短信
                smsSendMessageService.sendSMSMessage(member.getMobilePhone(), orderNoSB.toString(), AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode());
            } else {
                new Thread(() -> {
                    try {
                        // 阿里云短信平台
                        DictionaryDetail alydx = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX)
                                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX));
                        // 发送阿里云短信
                        AliyunSmsUtil.sendValidateCode(member.getMobilePhone(), orderNoSB.toString(), AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode(), alydx.getDictValue(), alydx.getRemark1(), alydx.getRemark2());
                    } catch (ClientException e) {
                        logger.info("发送短信失败 手机号："+ member.getMobilePhone()+"_订单号：__"+orderNoSB.toString()+" 短信模板："+AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode()+"___异常"+e.getMessage());
                    }
                }).start();
            }
        }

        //自动派发订单券
        List<Map<String, Object>> rollSizeList = orderDao.getRollNumInOrder(payWater);
        if (rollSizeList != null && rollSizeList.size() > 0) {
            Map<String, Object> sendRollParams = new HashMap<>(2);
            sendRollParams.put("payWater", payWater);
            sendRollParams.put("rollSize", JsonUtil.gsonStr(rollSizeList));
            UrlHttpUtil.post(SystemPropertiesConfig.PAY_SEND_ROLL_URL, sendRollParams, new DefaultCallBack() {
                @Override
                public void onResponse(String response) {
                    logger.info("自动派发订单券成功");
                }
            });
        }

        return Result.success(addOrderResponseVO);
    }

}
