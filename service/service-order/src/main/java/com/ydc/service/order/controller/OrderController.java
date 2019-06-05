package com.ydc.service.order.controller;


import com.aliyuncs.exceptions.ClientException;
import com.ydc.beans.mq.sms.service.SMSSendMessageService;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.OrderConstant;
import com.ydc.commom.constant.PayConstant;
import com.ydc.commom.constant.RollConstant;
import com.ydc.commom.enums.cgj.OrderEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.AliyunSmsUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.dto.cgj.order.*;
import com.ydc.commom.view.vo.cgj.order.AddOrderResponseVO;
import com.ydc.commom.view.vo.cgj.order.MyOrderVO;
import com.ydc.commom.view.vo.cgj.order.OrderLogisticsVO;
import com.ydc.commom.view.vo.cgj.shopCart.BuyerOrderCommodityParamVO;
import com.ydc.commom.view.vo.cgj.shopCart.CreateOrderParamVO;
import com.ydc.commom.view.vo.cgj.shopCart.ShopCartParamVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.Order;
import com.ydc.model.cgj.OrderCommodity;
import com.ydc.model.cgj.Pagination;
import com.ydc.service.order.mq.service.SendMessageService;
import com.ydc.service.order.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderCommodityService orderCommodityService;
    @Autowired
    OrderLogisticsService orderLogisticsService;

    @Autowired
    SendMessageService sendMessageService;

    @Autowired
    private MemberWaterService memberWaterService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    @Autowired
    private SMSSendMessageService smsSendMessageService;


    @ResponseBody
    @RequestMapping("/user")
    public Object queryUserList() {
        return orderService.queryUserList();
    }

    /**
     * 获取用户订单列表
     *
     * @param orderReqDTO
     * @return
     */
    @RequestMapping(value = "/getOrderSonList", method = RequestMethod.POST)
    public String getOrderSonList(@RequestBody OrderReqDTO orderReqDTO) {
        logger.info("获取用户订单列表,orderReqDTO:"+JsonUtil.gsonStr(orderReqDTO));
        List<Map<String, Object>> ret = orderService.getOrderList(orderReqDTO);
        Map<String, Object> retConut = orderService.getOrderListCount(orderReqDTO);
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("totalCount", retConut.get("cnt"));
        jMap.put("rows", ret);
        return Result.success(jMap).toJSON();
    }

    /**
     * 获取商品订单列表
     *
     * @param orderReqDTO
     * @return
     */
    @RequestMapping(value = "/getOrderCommodityList", method = RequestMethod.POST)
    public String getOrderCommodityList(@RequestBody OrderReqDTO orderReqDTO) {
        List<Map<String, Object>> ret = orderCommodityService.getOrderCommodityList(orderReqDTO);
        Map<String, Object> retConut = orderCommodityService.getOrderCommodityListCount(orderReqDTO);
        Map<String, Object> jMap = new HashMap<>();
        jMap.put("totalCount", retConut.get("cnt"));
        jMap.put("rows", ret);
        return Result.success(jMap).toJSON();
    }


    /**
     * H5我的订单
     * @param myOrderDTO
     * @return
     */
    @PostMapping(value = "/getOrderList")
    public String getOrderList(@RequestBody MyOrderDTO myOrderDTO) {
        logger.info("H5查询订单详情,myOrderDTO:"+JsonUtil.gsonStr(myOrderDTO));
        try{
            List<MyOrderVO> myOrderVOS = orderService.logicalGetOrderList(myOrderDTO);
            return Result.success(myOrderVOS).toJSON();
        }catch (Exception e){
            logger.error("H5我的订单异常",e);
            return Result.failure("查询我的订单异常").toJSON();
        }
    }


    /**
     * H5查询订单详情
     * @param orderDetailsDTO
     * @return
     */
    @PostMapping(value = "/getOrderCommodityDetail")
    public String getOrderCommodityDetail(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        logger.info("H5查询订单详情,orderDetailsDTO:"+JsonUtil.gsonStr(orderDetailsDTO));
        try {
            MyOrderVO myOrderVO = orderService.getOrderByMemberId(orderDetailsDTO);
            if(myOrderVO == null)return Result.failure("查询订单信息不存在").toJSON();
            MyOrderVO ret = orderService.logicalGetOrderCommodity(orderDetailsDTO,myOrderVO);
            return Result.success(ret).toJSON();
        }catch (Exception e){
            logger.error("H5查询订单详情异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * H5取消订单
     * @param cancelOrderDTO
     * @return
     */
    @PostMapping(value = "/cancelOrder")
    public String cancelOrder(@RequestBody CancelOrderDTO cancelOrderDTO) {
        logger.info("H5取消订单,cancelOrderDTO:"+JsonUtil.gsonStr(cancelOrderDTO));
        try {
            orderService.cancelOrder(cancelOrderDTO);
            return Result.success().toJSON();
        } catch (Exception e) {
            logger.error("H5取消订单异常",e);
            return Result.failure().toJSON();
        }
    }

    /**
     * 查看物流
     * @param orderId
     * @return
     */
    @PostMapping(value = "/getOrderLogistics")
    public String getOrderLogistics(Integer orderId) {
        logger.info("查看物流,orderId:"+orderId);
        try{
            List<OrderLogisticsVO> orderLogisticsVOS = orderLogisticsService.getOrderLogistics(orderId);
            boolean status = false;
            for (OrderLogisticsVO item : orderLogisticsVOS) {
                if (StringUtil.isNotEmpty(item.getLogisticsUrl())) {
                    status = true;
                }
            }
            logger.info("subject:{},status:{},orderLogisticsVOS:{}","查看物流",status,JsonUtil.gsonStr(orderLogisticsVOS));
            if(!status){
                return Result.failure("虚拟商品直接派发到账户").toJSON();
            }

            return Result.success(orderLogisticsVOS).toJSON();
        }catch (Exception e){
            logger.error("查看物流异常",e);
            return Result.failure("查看物流").toJSON();
        }

    }

    /**
     * 确认收货
     * @param orderId
     * @param memberId
     * @return
     */
    @PostMapping(value = "/notarizeReceiving")
    public String notarizeReceiving(Integer orderId ,Integer memberId) {
        logger.info("查看物流,orderId:"+orderId+";memberId:"+memberId);
        orderService.notarizeReceiving(orderId,memberId);
        return Result.success().toJSON();
    }

    /**
     * 查询订单环节数量
     * @param memberId
     * @return
     */
    @PostMapping(value = "/getOrderStatusNum")
    public String getOrderStatusNum(Integer memberId){
        return Result.success(orderService.getOrderStatusNum(memberId)).toJSON();
    }

    /**
     * 全部发货
     * @return
     */
    @RequestMapping(value = "/allShipments", method = RequestMethod.POST)
    public String allShipments(@RequestBody OrderReqDTO ord){
        logger.info("全部发货,orderId:"+ord.getOrderId());
        try{
            Integer orderId = Integer.valueOf(ord.getOrderId());
            Order order = orderService.selectByPrimaryKey(orderId);
            if(order.getLogisticsStatus() != 0){
                return Result.failure("订单状态必须为【待发货】才可操作全部发货").toJSON();
            }
            orderLogisticsService.insertBySonOrderId(ord);
            sendMessageService.sendOrderAutoConfirmReceiptMessage(orderId);
            return Result.success("全部发货成功").toJSON();
        }catch (Exception e){
            logger.error("全部发货异常",e);
            return Result.failure("全部发货异常").toJSON();
        }
    }

    /**
     * 部分发货
     * @param ord
     * @return
     */
    @RequestMapping(value = "/portionShipments", method = RequestMethod.POST)
    public String portionShipments(@RequestBody OrderReqDTO ord){
        logger.info("部分发货,orderCommodityIds:"+ord.getOrderCommodityIds());
        try{
            List<OrderCommodity> orderCommodityList = orderCommodityService.getOrderCommodityByOrderCommodityIds(ord.getOrderCommodityIds());
            if(orderCommodityList != null && orderCommodityList.size() > 0){
                return Result.failure("存在【"+orderCommodityList.size()+"】条已发货的商品订单，请重新选择").toJSON();
            }
            orderLogisticsService.insertByOrderCommodityIds(ord);
            Order order = orderService.getOrderByOrderCommodityIds(ord.getOrderCommodityIds());
            if(order.getStatus() == OrderConstant.ORDER_STATUS_SHIPPED){
                sendMessageService.sendOrderAutoConfirmReceiptMessage(order.getId());
            }
            return Result.success("更新物流成功").toJSON();
        }catch (Exception e){
            logger.error("更新物流异常",e);
            return Result.failure("更新物流异常").toJSON();
        }
    }

    /**
     *  创建订单
     *
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result createOrder(@RequestBody CreateOrderParamVO createOrderParamVO,
                              @RequestParam("memberId") Integer memberId){
        try {
            final  String orderSerial="ORDER:CREATE:SERIAL:"+createOrderParamVO.getSerial();
            if ( null!=RedisUtil.redisGet(orderSerial)){
                return Result.failure("请勿重复提交订单");
            }
            RedisUtil.redisSet(orderSerial,createOrderParamVO.getSerial(),10L);
            Set<Integer> ids=new HashSet<>();
            //选择出购物车ID和数量
            List<BuyerOrderCommodityParamVO> buyerOrderCommodityParamVOS=createOrderParamVO.getBuyerOrderCommodityParamVOS();
            for (BuyerOrderCommodityParamVO buyerOrderCommodityParamVO:buyerOrderCommodityParamVOS){
                List<ShopCartParamVO> shopCartParamVOS=buyerOrderCommodityParamVO.getShopCartParamVOS();
               for (ShopCartParamVO shopCartParamVO:shopCartParamVOS){
                   if (shopCartParamVO !=null){
                       if (StringUtil.isNotEmpty(shopCartParamVO.getId())){
                           ids.add(shopCartParamVO.getId());
                       }
                   }
               }
            }
            //创建订单信息
           Result<List<OrderAndOrderCommodityListDTO>>  resultList=orderService.createOrder(createOrderParamVO,memberId);
            if (resultList.getCode()!=ResultConstant.RESULT_CODE_SUCCESS){
                return  resultList;
            }
            List<OrderAndOrderCommodityListDTO> orderAndOrderCommodityListDTOList=resultList.getData();
            if (null==orderAndOrderCommodityListDTOList || orderAndOrderCommodityListDTOList.isEmpty()){
                return Result.failure("购买人数过多，请稍后重试");
            }
            //减去库存
            AddOrderResponseVO addOrderResponseVO=orderService.updateCommodityNumAndOrderStatus(orderAndOrderCommodityListDTOList);
            if (null==addOrderResponseVO){
                logger.info("库存更新失败");
                return Result.failure("库存不足，请重新提交订单");
            }
            if (!ids.isEmpty()){
                //更新购物车状态
                orderService.updateOrderShopCartStatus(ids,memberId);
            }
            //发送mq 半个小时内支付
            sendMessageService.sendPayMessage(addOrderResponseVO.getOrderIdList(),memberId);
            return Result.success(addOrderResponseVO);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return Result.failure("请稍后下单");
        }
    }

    /**
     * 微信支付成功回调通知
     * @param payWater
     */
    @PostMapping(value = "/doWeiXinPayNotify")
    public Result doWeiXinPayNotify(@RequestParam(value = "payWater") String payWater, @RequestParam("transactionId") String transactionId){
        logger.info("subject: {}, payWater: {}, transactionId: {}", "微信支付成功回调通知", payWater, transactionId);
        if (memberWaterService.updateWaterStatus(payWater, PayConstant.WATER_STATUS_DONE, PayConstant.WATER_STATUS_ING, transactionId) <= 0){
            return Result.failure("流水不存在或已处理");
        }
        if (orderService.updateOrderStatusByWater(payWater, OrderEnum.OrderStatusEnum.ORDER_STATUS_2.getCode(), OrderEnum.OrderStatusEnum.ORDER_STATUS_1.getCode(), new Date()) <= 0){
            return Result.failure("订单不存在或已处理");
        }

        //获取流水的订单
        List<Map<String, Object>> list = orderService.getRollNumInOrder(payWater);
        if (list == null || list.size() <= 0){
            return Result.success(orderService.getRollNumInOrder(payWater));
        }

        String orderNos = list.parallelStream().map(map -> (String) map.get("orderNo")).collect(Collectors.joining(","));
        String mobilePhone = memberService.getMemberById((Integer) list.get(0).get("memberId")).getMobilePhone();

        //发送短信
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW));
        if (dictionaryDetail != null && ("1").equals(dictionaryDetail.getDictValue())) {
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)));
            if(dic.isPresent() && dic.get().getDictValue().equals("1")){
                smsSendMessageService.sendSMSMessage(mobilePhone,orderNos, AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode());
            }else{
                new Thread(() -> {
                    try {
                        // 阿里云短信平台
                        DictionaryDetail alydx = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX)
                                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX));
                        // 发送阿里云短信
                        AliyunSmsUtil.sendValidateCode(mobilePhone, orderNos, AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode(), alydx.getDictValue(), alydx.getRemark1(), alydx.getRemark2());
                    } catch (ClientException e) {
                        logger.info("发送短信失败 手机号："+ mobilePhone+"_订单号：__"+orderNos+" 短信模板："+AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode()+"___异常"+e.getMessage());
                    }
                }).start();
            }
        }

        //查询订单中的券信息返回
        return Result.success(list.parallelStream().filter(map -> RollConstant.ROLL_COMMODITY_CODE.equals(map.get("mainClassifyCode"))));
    }

    /**
     * 获取订单中的用户id
     * @param payWater
     * @return
     */
    @PostMapping(value = "/getOrderMemberId")
    public Integer getOrderMemberId(@RequestParam String payWater){
        return orderService.getOrderMemberId(payWater);
    }

    /**
     * 配发券之后更新订单状态
     * @param orderList
     * @return
     */
    @PostMapping(value = "/updateSendRollOrderStatus")
    public void updateSendRollOrderStatus(@RequestBody List<String> orderList){
        logger.info("subject: {}, orderList: {}", "配发券之后更新订单状态", orderList);
        orderService.updateSendRollOrderStatus(orderList);
    }
    /**
     * 查询交易流水列表
     * @author: hejiangping
     * @date: 2019/1/15
     */
    @RequestMapping(value = "/getMemberSaters", method = RequestMethod.POST)
    public String getMemberSaters(@RequestBody MemberWaterDTO memberWaterDTO) {
        logger.info("subject:{},param:{}","查询交易流水列表",JsonUtil.gsonStr(memberWaterDTO));
        try{
            List<Map<String, Object>> ret = memberWaterService.getMemberSaters(memberWaterDTO);
            Map<String, Object> jMap = new HashMap<>();
            jMap.put("totalCount", PaginationUtil.pageTotalQuery(ret));
            jMap.put("rows", ret);
            return Result.success(jMap).toJSON();
        }catch (Exception e){
            logger.error("查询交易流水列表异常",e);
            return Result.failure().toJSON();
        }
    }
}
