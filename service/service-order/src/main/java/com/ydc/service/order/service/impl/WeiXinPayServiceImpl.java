package com.ydc.service.order.service.impl;

import com.ydc.beans.commom.OrderNoUtil;
import com.ydc.beans.config.OrderNoConfig;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.weiXinPay.WeiXinPay;
import com.ydc.beans.weiXinPay.WeiXinPayUtil;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.OrderConstant;
import com.ydc.commom.constant.PayConstant;
import com.ydc.commom.enums.cgj.IntegralDetailEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.NumberUtil;
import com.ydc.commom.view.dto.cgj.order.OrderCommodityDTO;
import com.ydc.commom.view.vo.cgj.order.AddOrderResponseVO;
import com.ydc.commom.view.vo.cgj.order.BuyRollParamVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import com.ydc.dao.cgj.order.OrderCommodityDao;
import com.ydc.dao.cgj.order.OrderDao;
import com.ydc.dao.cgj.store.CommodityDao;
import com.ydc.dao.cgj.store.CommodityModelDao;
import com.ydc.dao.cgj.user.IntegralDao;
import com.ydc.dao.cgj.user.MemberDao;
import com.ydc.dao.cgj.user.MemberWaterDao;
import com.ydc.model.cgj.*;
import com.ydc.service.order.mq.service.SendMessageService;
import com.ydc.service.order.service.PayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 微信支付
 */
@Service(value = PayConstant.PAY_WECHATPAY)
public class WeiXinPayServiceImpl implements PayService {
    private static Logger logger = LogManager.getLogger(WeiXinPayServiceImpl.class);

    @Resource
    private OrderNoConfig orderNoPayWaterConfig;
    @Resource
    private IntegralDao integralDao;
    @Resource
    private MemberDao memberDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private MemberWaterDao memberWaterDao;
    @Resource
    private CommodityDao commodityDao;
    @Resource
    private OrderNoConfig orderNoMerchantConfig;
    @Resource
    private OrderNoConfig orderNoMemberConfig;
    @Resource
    private OrderCommodityDao orderCommodityDao;
    @Resource
    private SendMessageService sendMessageService;
    @Resource
    private CommodityModelDao commodityModelDao;

    @Override
    @Transactional(value="cgjTransactionManager")
    public Result updateOrderPay(PayOrderParamVO payOrderParamVO, Integer memberId, List<Order> orderList) {
        Date currentDate = new Date();
        AddOrderResponseVO addOrderResponseVO = new AddOrderResponseVO();
        List<String> orderNoS = new ArrayList<>();
        List<Integer> orderIds = new ArrayList<>();

        String payWater = OrderNoUtil.getOrderNo(orderNoPayWaterConfig);
        Member member = memberDao.selectByPrimaryKey(memberId);
        Integral integral = integralDao.getIntegralByMumberId(memberId);
        //支付
        String prepayId; //微信支付流水
        try {
        switch (payOrderParamVO.getPayEnvType()){
            case PayConstant.IN_WEIXIN_PAY:
                //微信内支付
                prepayId = payJS(payOrderParamVO.getCreateIp(), payOrderParamVO.getOpenId(), payWater, NumberUtil.getWeiXinPayAmount(payOrderParamVO.getSellPrice()));
                addOrderResponseVO.setPayParams(WeiXinPayUtil.getJSPayParams(prepayId, PayConstant.IN_WEIXIN_PAY));
                break;
            case PayConstant.IN_H5_PAY:
                //微信外支付
                String payUrl = payH5(payOrderParamVO.getCreateIp(), payWater, NumberUtil.getWeiXinPayAmount(payOrderParamVO.getSellPrice()), PayConstant.TRADE_TYPE_H5);
                addOrderResponseVO.setWeixinPayUrl(payUrl);
                prepayId = WeiXinPayUtil.getPrepayIdByUrl(payUrl);
                break;
            case PayConstant.IN_APP_PAY:
                //app内支付
                prepayId = payAPP(payOrderParamVO.getCreateIp(), payWater, NumberUtil.getWeiXinPayAmount(payOrderParamVO.getSellPrice()), PayConstant.TRADE_TYPE_APP);
                addOrderResponseVO.setPayParams(WeiXinPayUtil.getAPPPayParams(prepayId, PayConstant.IN_APP_PAY));
                break;
            default:
                logger.info("subject: {}, payEnvType: {}", "微信支付环境参数异常", payOrderParamVO.getPayEnvType());
                throw new ServiceRuntimeException("payEnvType is invalid");
            }
        }catch (ServiceRuntimeException e){
            logger.info("支付异常",e.getMessage());
            throw e;
        }catch (Exception e){
            logger.info("支付异常",e.getMessage());
            throw new ServiceRuntimeException("请稍后重试",ResultConstant.RESULT_CODE_FAILURE);
        }

        //添加流水
        if (createMemberWater(member, integral, payOrderParamVO.getSellPrice(), payWater, prepayId)!=1){
            logger.info("数据库生成流水错误");
            return  Result.failure("下单人数过多，请稍后重试");
        }

        //更新订单
        Map<String, Object> param = new HashMap<>();
        param.put("orderNos", payOrderParamVO.getOrderNOList());
        param.put("oldStatus", OrderConstant.ORDER_STATUS_UNPAID);
        param.put("newStatus", OrderConstant.ORDER_STATUS_UNPAID);
        param.put("updateTime", currentDate);
//        param.put("paymentTime", currentDate);
        param.put("logisticsStatus", 0);
        param.put("payWater", payWater);
        param.put("paymentMethod", PayConstant.PAY_WECHATPAY);
        param.put("paymentCurrency", PayConstant.PaymentCurrencyEnum.getPaymentCurrencyByMethod(PayConstant.PAY_WECHATPAY));
        int count = orderDao.updateBatchByStatus(param);
        if (payOrderParamVO.getOrderNOList().size() != count) {
            throw new ServiceRuntimeException("请重新刷新订单");
        }
        for (Order order : orderList) {
            orderNoS.add(order.getOrderNo());
            orderIds.add(order.getId());
        }
        addOrderResponseVO.setOrderNOList(orderNoS);
        addOrderResponseVO.setOrderIdList(orderIds);
        addOrderResponseVO.setPayType(payOrderParamVO.getPaymentMethod());
        addOrderResponseVO.setPayEnvType(payOrderParamVO.getPayEnvType());
        addOrderResponseVO.setPrepayId(prepayId);
        return Result.success(addOrderResponseVO);
    }

    /**
     * 微信内支付
     * @param ip
     * @param openId
     * @param payWater
     * @param amount
     * @return 支付id
     * @throws Exception
     */
    private String payJS(String ip, String openId, String payWater, String amount) {
        if (openId == null){
            throw new ServiceRuntimeException("授权失败,请退出重新登录",ResultConstant.NOT_LOGIN_FAILURE);
        }
        Map<String, String> params = WeiXinPay.getJSPayParams(ip, payWater, amount, openId);
        return WeiXinPayUtil.payThenDealResultJS(params, SystemPropertiesConfig.WEIXIN_PAY_NOTIFYURL, PayConstant.IN_WEIXIN_PAY);
    }

    /**
     * 微信外支付
     * @param ip
     * @param payWater
     * @param amount
     * @return 支付链接
     * @throws Exception
     */
    private String payH5(String ip, String payWater, String amount, String trade_type) throws Exception {
        Map<String, String> params = WeiXinPay.getH5PayParams(ip, payWater, amount, trade_type);
        return WeiXinPayUtil.payThenDealResultH5(params, SystemPropertiesConfig.WEIXIN_PAY_NOTIFYURL, PayConstant.IN_H5_PAY);
    }

    /**
     * APP支付
     * @param ip
     * @param payWater
     * @param amount
     * @param trade_type
     * @return
     * @throws Exception
     */
    private String payAPP(String ip, String payWater, String amount, String trade_type) throws Exception {
        Map<String, String> params = WeiXinPay.getH5PayParams(ip, payWater, amount, trade_type);
        return WeiXinPayUtil.payThenDealResultJS(params, SystemPropertiesConfig.WEIXIN_PAY_NOTIFYURL, PayConstant.IN_APP_PAY);
    }

    @Override
    @Transactional(value="cgjTransactionManager")
    public Result buyRoll(BuyRollParamVO buyRollParamVO, Integer memberId)  {
        Map<String, Object> map = new HashMap<>(2);
        map.put("commodityId", buyRollParamVO.getCommodityId());
        map.put("modelId", buyRollParamVO.getCommodityModelId());
        List<OrderCommodityDTO> orderCommodityDTOList = commodityDao.getOrderCommodityDTO(map);
        if (orderCommodityDTOList == null || orderCommodityDTOList.size() == 0)
            throw new ServiceRuntimeException("商品不存在");

        OrderCommodityDTO orderCommodityDTO = orderCommodityDTOList.get(0);
        Map<String, Object> resultMap = new HashMap<>();
        BigDecimal amount = orderCommodityDTO.getSellPrice().multiply(BigDecimal.valueOf(buyRollParamVO.getCommodityNum()));
        String payWater = OrderNoUtil.getOrderNo(orderNoPayWaterConfig);
        String orderUserNo=OrderNoUtil.getOrderNo(orderNoMemberConfig);
        String orderNo=OrderNoUtil.getOrderNo(orderNoMerchantConfig);
        Member member = memberDao.selectByPrimaryKey(memberId);
        Integral integral = integralDao.getIntegralByMumberId(memberId);
        Commodity commodity = commodityDao.selectByPrimaryKey(buyRollParamVO.getCommodityId());
        commodity.setSoldNumber(commodity.getSoldNumber() + buyRollParamVO.getCommodityNum());
        commodityDao.updateByPrimaryKeySelective(commodity);

        //支付
        String prepayId; //微信支付流水
        try {
        switch (buyRollParamVO.getPayEnvType()){
            case PayConstant.IN_WEIXIN_PAY:
                //微信内支付
                prepayId = payJS(buyRollParamVO.getCreateIp(), buyRollParamVO.getOpenId(), payWater, NumberUtil.getWeiXinPayAmount(amount));
                resultMap.put("payParams", WeiXinPayUtil.getJSPayParams(prepayId, PayConstant.IN_WEIXIN_PAY));
                break;
            case PayConstant.IN_H5_PAY:
                //微信外支付
                String payUrl = payH5(buyRollParamVO.getCreateIp(), payWater, NumberUtil.getWeiXinPayAmount(amount), PayConstant.TRADE_TYPE_H5);
                resultMap.put("weixinPayUrl", payUrl);
                prepayId = WeiXinPayUtil.getPrepayIdByUrl(payUrl);
                break;
            case PayConstant.IN_APP_PAY:
                //app内支付
                prepayId = payAPP(buyRollParamVO.getCreateIp(), payWater, NumberUtil.getWeiXinPayAmount(amount), PayConstant.TRADE_TYPE_APP);
                resultMap.put("payParams", WeiXinPayUtil.getAPPPayParams(prepayId, PayConstant.IN_APP_PAY));
                break;
            default:
                logger.info("subject: {}, payEnvType: {}", "微信支付环境参数异常", buyRollParamVO.getPayEnvType());
                throw new ServiceRuntimeException("payEnvType is invalid");
        }

        }catch (ServiceRuntimeException e){
            throw  e;
        }catch (Exception e){
            throw  new ServiceRuntimeException("请稍后重试",ResultConstant.RESULT_CODE_FAILURE);
        }

        //创建总订单
        orderNoMerchantConfig.setOrderNoSuffix(orderCommodityDTO.getSupplierCode());
        Order order=new Order();
        order.setMemberId(memberId);
        order.setOrderNo(orderNo);
        order.setStatus(OrderConstant.ORDER_STATUS_UNPAID);
        order.setSupplierCode(orderCommodityDTO.getSupplierCode());
        order.setSupplierName(orderCommodityDTO.getSupplierName());
        order.setOrderAmount(amount);
        order.setCommodityCount(buyRollParamVO.getCommodityNum());
        order.setLogisticsStatus(0);
        order.setCreateBy(memberId);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setUpdateBy(memberId);
        order.setOrderNumber(orderUserNo);
        order.setPayWater(payWater);
        order.setPaymentCurrency(PayConstant.PAYMENT_CURRENCY_CNY);
        order.setPaymentMethod(PayConstant.PAY_WECHATPAY);
        int countOrder=orderDao.insert(order);
        if (countOrder!=1){
            logger.info("创建主订单为空");
            throw new ServiceRuntimeException("下单人数过多，请稍后重试");
        }
        //创建订单商品
        OrderCommodity orderCommodity = orderCommodityDTO.createOrderCommodity();
        orderCommodity.setMemberId(memberId);
        orderCommodity.setOrderNo(orderNo);
        orderCommodity.setOrderId(order.getId());
        orderCommodity.setSellPrice(amount);
        orderCommodity.setCommodityModelNumber(buyRollParamVO.getCommodityNum());
        orderCommodity.setCreateBy(memberId);
        orderCommodity.setCreateTime(new Date());
        orderCommodity.setUpdateTime(new Date());
        orderCommodity.setUpdateBy(memberId);
        orderCommodity.setLogisticsStatus(0);
        if (orderCommodityDao.insert(orderCommodity)!=1){
            logger.info("数据库生成订单商品错误");
            throw new ServiceRuntimeException("下单人数过多，请稍后重试");
        }

        //添加流水
        if (createMemberWater(member, integral, amount, payWater, prepayId)!=1){
            logger.info("数据库生成流水错误");
            throw new ServiceRuntimeException("下单人数过多，请稍后重试");
        }

        resultMap.put("payEnvType", buyRollParamVO.getPayEnvType());
        resultMap.put("prepayId", prepayId);

        //发送mq 半个小时内支付
        List<Integer> orderIds = new ArrayList<>();
        orderIds.add(order.getId());
        sendMessageService.sendPayMessage(orderIds,memberId);
        return Result.success(resultMap);
    }

    /**
     * 添加流水
     * @param member
     * @param integral
     * @param amount
     * @param payWater
     * @param prepayId
     * @return
     */
    private int createMemberWater(Member member, Integral integral, BigDecimal amount, String payWater, String prepayId){
        //添加流水
        MemberWater memberWater = new MemberWater();
        memberWater.setMemberId(member.getId());
        memberWater.setMemberName(member.getMemberName());
        memberWater.setMobilePhone(member.getMobilePhone());
        memberWater.setIntegralAmount(BigDecimal.ZERO);
        memberWater.setCashAmount(amount);
        memberWater.setTransactionTime(new Date());
        memberWater.setTransactionMethod(PayConstant.TRANSACTION_METHOD_WEIXIN);
        memberWater.setTransactionType(PayConstant.TRANSACTION_TYPE_CASH);
        memberWater.setWaterType(PayConstant.WATER_TYPE_CONSUME);
        memberWater.setWaterStatus(PayConstant.WATER_STATUS_ING);
        memberWater.setChangeIntegralBalance(integral == null ? BigDecimal.ZERO : integral.getUsableIntegral());
        memberWater.setChangeCashBalance(BigDecimal.ZERO);
        memberWater.setConsumeType(IntegralDetailEnum.IntegralTypeConsumeEnum.integral_type_consume_0.getCode());
        memberWater.setPayWater(payWater);
        memberWater.setThirdPayWater(prepayId);
        memberWater.setStatus(CodeConstant.NORMAL_STATUS);
        memberWater.setCreateTime(new Date());
        memberWater.setCreateBy(1);
        memberWater.setUpdateTime(new Date());
        memberWater.setUpdateBy(1);
        return memberWaterDao.insertSelective(memberWater);
    }
}
