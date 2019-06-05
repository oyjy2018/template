package com.ydc.service.order.controller;

import com.ydc.beans.rocketMQ.ProducerClient;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.vo.cgj.order.BuyRollParamVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import com.ydc.model.cgj.CommodityModel;
import com.ydc.service.order.mq.OrderMQConfig;
import com.ydc.service.order.service.CommodityModelService;
import com.ydc.service.order.service.PayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/pay")
public class PayController {

    private static  final Logger logger = LogManager.getLogger(PayController.class);
    @Resource
    private OrderMQConfig orderPayMQConfig;

    @Resource
    private ProducerClient producerClient;

    @Autowired
    private PayService payService;

    @Autowired
    private CommodityModelService commodityModelService;


    /**
     * 订单支付
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Result payOrder(@RequestBody PayOrderParamVO payOrderParamVO, @RequestParam("memberId") Integer memberId){
        try {
            return payService.updateOrderPay(payOrderParamVO, memberId, null);
        }catch (ServiceRuntimeException e){
            logger.error("subject: {}", "订单支付异常", e);
            return   Result.getResult(e.getCode(),e.getMessage());
        }catch (Exception e){
            logger.error("subject: {}", "订单支付异常", e);
//            logger.info(e.getMessage());
            return  Result.failure("请重新刷新");
        }

    }


  /*  @RequestMapping(value = "/order/test", method = RequestMethod.POST)
    public String testOrder(@RequestBody List<String> orderNos){
        try {
            orderService.updateOrderStatus(orderNos);
            return "成功";
        }catch (ServiceRuntimeException e){
            return e.getMessage();
        }

    }*/

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Result testRocketMq(@RequestParam("content") Integer content){
            //
       logger.info("++++++++++++++++++++++++++++++++++++++++++++++++");
        logger.info("content info:"+content);
        logger.debug("content debug:"+content);
        logger.warn("content warn:"+content);
        logger.trace("content trace:"+content);
        logger.error("content error:"+content);
        logger.debug(content.toString());
        Message message=new Message();
        try {
            message.setTopic(orderPayMQConfig.getTopic());
            message.setTags("TagTest");
            message.setDelayTimeLevel(content.intValue());
            long string=System.currentTimeMillis();
           logger.info(string);
            message.setBody(Long.valueOf(string).toString().getBytes());
            producerClient.sendMessage(message);
        }catch (Exception e){
            logger.error("e:{}",e);
        }
        return Result.success();

    }

    /**
     * 购买券
     * @param buyRollParamVO
     * @param memberId
     * @return
     */
    @PostMapping(value = "/buyRoll")
    public Result buyRoll(@RequestBody BuyRollParamVO buyRollParamVO, @RequestParam("memberId") Integer memberId){
        logger.info("subject: {}, buyRollParamVO: {}, memberId: {}", "商城购买券", JsonUtil.gsonStr(buyRollParamVO), memberId);
        try {
            synchronized (this) {
                //判断库存
                CommodityModel commodityModel = commodityModelService.selectByPrimaryKey(buyRollParamVO.getCommodityModelId());
                if (commodityModel == null || buyRollParamVO.getCommodityNum() > commodityModel.getInventory()) {
                    return Result.failure("超过最大可购买券数");
                }
                //更新库存
                commodityModel.setInventory(commodityModel.getInventory() - buyRollParamVO.getCommodityNum());
                logger.info("subject: {}, clazz: {}, Thread: {}, version: {}", "更新库存的锁", this.hashCode(), Thread.currentThread().getName(), commodityModel.getVersion());
                if (commodityModelService.updateByIdAndVersion(commodityModel) <= 0) {
                    logger.info("更新库存失败");
                    return Result.failure("下单人数过多，请稍后重试");
                }
            }

            return payService.buyRoll(buyRollParamVO, memberId);
        }catch (ServiceRuntimeException e){
//            logger.error("subject: {}, memberId: {}", "用户购买券异常", memberId, e);
            recoverInventory(buyRollParamVO.getCommodityModelId(), buyRollParamVO.getCommodityNum());
            return   Result.getResult(e.getCode(),e.getMessage());
        }catch (Exception e){
            logger.error("subject: {}, memberId: {}", "用户购买券异常", memberId, e);
            recoverInventory(buyRollParamVO.getCommodityModelId(), buyRollParamVO.getCommodityNum());
            return  Result.failure("请重新刷新");
        }
    }

    private synchronized int recoverInventory(Integer commodityModelId, int commodityNum){
        CommodityModel commodityModel = commodityModelService.selectByPrimaryKey(commodityModelId);
        commodityModel.setInventory(commodityModel.getInventory() + commodityNum);
        return commodityModelService.updateByIdAndVersion(commodityModel);
    }
}
