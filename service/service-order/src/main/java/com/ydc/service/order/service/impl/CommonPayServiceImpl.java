package com.ydc.service.order.service.impl;

import com.ydc.commom.constant.OrderConstant;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.view.dto.cgj.order.CancelOrderDTO;
import com.ydc.commom.view.vo.cgj.order.BuyRollParamVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import com.ydc.dao.cgj.order.OrderDao;
import com.ydc.model.cgj.Order;
import com.ydc.service.order.service.OrderService;
import com.ydc.service.order.service.PayService;
import com.ydc.service.order.util.ServiceFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "payService")
public class CommonPayServiceImpl implements PayService {
    private static Logger logger = LogManager.getLogger(CommonPayServiceImpl.class);

    @Resource
    private OrderDao orderDao;
    @Autowired
    private OrderService orderService;

    @Override
    public Result updateOrderPay(PayOrderParamVO payOrderParamVO, Integer memberId, List<Order> orderList) throws Exception {
        if (orderList == null) orderList = getOrderList(payOrderParamVO, memberId);
        PayService payService = ServiceFactoryUtil.getBean(payOrderParamVO.getPaymentMethod());
        return payService.updateOrderPay(payOrderParamVO, memberId, orderList);
    }

    /**
     * 获取订单列表
     * @param payOrderParamVO
     * @param memberId
     * @return
     * @throws Exception
     */
    private List<Order> getOrderList(PayOrderParamVO payOrderParamVO, Integer memberId) throws Exception{
        BigDecimal totalPrice=BigDecimal.ZERO;
        List<Order> orderList=new ArrayList<>();
        if (payOrderParamVO.getOrderNOList().isEmpty()){
            logger.info("订单无法支付  订单号为空");
            throw  new ServiceRuntimeException("订单号为空");
        }
        for (String orderNo:payOrderParamVO.getOrderNOList()){
            //判断支付
            Order order=orderDao.selectByOrderNo(orderNo);
            if ( order==null || ! order.getStatus().equals(OrderConstant.ORDER_STATUS_UNPAID)){
                logger.info("订单无法支付  状态 ："+order.getStatus());
                throw  new ServiceRuntimeException("请重新刷新订单");
            }
            if (DateUtil.compareSubDate(new Date(),order.getCreateTime(),DateUtil.DATE_LONG_30) >=0 ){
                //订单超时
                CancelOrderDTO cancelOrderDTO=new CancelOrderDTO();
                cancelOrderDTO.setOrderId(order.getId());
                cancelOrderDTO.setCancelReason("订单超时未支付");
                cancelOrderDTO.setMemberId(memberId);
                orderService.cancelOrder(cancelOrderDTO);
                throw  new ServiceRuntimeException("订单超时");
            }
            totalPrice=totalPrice.add(order.getOrderAmount());
            orderList.add(order);
        }
        if (totalPrice.compareTo(payOrderParamVO.getSellPrice())!=0){
            logger.info("支付金额错误 应付金额："+totalPrice+"实际金额+："+payOrderParamVO.getSellPrice());
            throw  new ServiceRuntimeException("支付金额错误");
        }
        return orderList;
    }

    @Override
    public Result buyRoll(BuyRollParamVO buyRollParamVO, Integer memberId) throws Exception {
        PayService payService = ServiceFactoryUtil.getBean(buyRollParamVO.getPaymentMethod());
        return payService.buyRoll(buyRollParamVO, memberId);
    }
}
