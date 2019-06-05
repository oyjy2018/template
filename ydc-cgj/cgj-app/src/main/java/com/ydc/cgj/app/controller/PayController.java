package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.app.common.SubjectUtil;
import com.ydc.cgj.app.service.IntegralService;
import com.ydc.cgj.app.service.MemberService;
import com.ydc.cgj.app.service.PayService;
import com.ydc.commom.constant.PayConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.IPUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.vo.cgj.order.BuyRollParamVO;
import com.ydc.commom.view.vo.cgj.order.PayOrderParamVO;
import com.ydc.model.cgj.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/***
 * 支付
 */
@RestController
@RequestMapping(value = "/pay")
public class PayController {
    protected static final Logger logger = LogManager.getLogger(PayController.class);
    @Autowired
    private PayService payService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private MemberService memberService;

    /**
     * 订单支付
     */
    @ResponseBody
    @RequestMapping(value = "/order", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String payOrder(@RequestParam("data") String data, HttpServletRequest request) throws Exception {
        logger.info("subject:{},payOrderParamVO:{}","H5 订单支付",data);
        PayOrderParamVO payOrderParamVO = JSONObject.parseObject(data,PayOrderParamVO.class);
        if (payOrderParamVO == null) {
            return Result.failure("参数不能为空").toJSON();
        }
        if (StringUtil.isEmpty(payOrderParamVO.getPaymentMethod()) ||
                !(PayConstant.PAY_WECHATPAY.equals(payOrderParamVO.getPaymentMethod()) || PayConstant.PAY_INTEGRAL.equals(payOrderParamVO.getPaymentMethod()))) {
            return Result.failure("请选择正确的支付方式").toJSON();
        }
        if (null == payOrderParamVO.getSellPrice() || payOrderParamVO.getSellPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return Result.failure("参数错误").toJSON();
        }
        Member member = SubjectUtil.getMember();
        if (null == member) {
            return Result.failure("用户未登陆").toJSON();
        }
        Integer memberId = member.getId();

        //积分不足则使用微信支付
        String myIntegral = integralService.getMyIntegral(memberId);
        JSONObject integralResult = JSON.parseObject(myIntegral);
        Map<String, Object> map = (Map<String, Object>) integralResult.get("data");
        BigDecimal integralNum = (BigDecimal) map.get("usableIntegral");
        if (integralNum == null || payOrderParamVO.getSellPrice().compareTo(integralNum) > 0){
            payOrderParamVO.setPaymentMethod(PayConstant.PAY_WECHATPAY);
            payOrderParamVO.setCreateIp(IPUtil.getIpAddr(request));
        }

//        payOrderParamVO.setPayEnvType(PayConstant.IN_H5_PAY);
//        //微信内支付
//        if ((request.getHeader("user-agent").toLowerCase().contains("MicroMessenger".toLowerCase()))){
//            payOrderParamVO.setPayEnvType(PayConstant.IN_WEIXIN_PAY);
//            payOrderParamVO.setOpenId(memberService.getMemberById(memberId).getWeixinPayOpenId());
//        }
        if (PayConstant.IN_WEIXIN_PAY == payOrderParamVO.getPayEnvType()){
            payOrderParamVO.setOpenId(memberService.getMemberById(memberId).getWeixinPayOpenId());
        }
        Result result = payService.payOrder(payOrderParamVO, memberId);
        return result.toJSON();
    }

    /**
     * 购买券
     * @param data
     * @param request
     * @return
     */
    @PostMapping(value = "/buyRoll")
    public String buyRoll(@RequestParam("data") String data, HttpServletRequest request) throws Exception{
        logger.info("subject:{},buyRollParamVO:{}","购买券",data);
        BuyRollParamVO buyRollParamVO = JSONObject.parseObject(data,BuyRollParamVO.class);
        Member member = SubjectUtil.getMember();
        if (buyRollParamVO == null) {
            return Result.failure("参数不能为空").toJSON();
        }
        if (StringUtil.isEmpty(buyRollParamVO.getPaymentMethod()) ||
                !(PayConstant.PAY_WECHATPAY.equals(buyRollParamVO.getPaymentMethod()) || PayConstant.PAY_INTEGRAL.equals(buyRollParamVO.getPaymentMethod()))) {
            return Result.failure("请选择正确的支付方式").toJSON();

        }
        //现在默认是微信支付
        buyRollParamVO.setPaymentMethod(PayConstant.PAY_WECHATPAY);
        buyRollParamVO.setCreateIp(IPUtil.getIpAddr(request));
//        buyRollParamVO.setPayEnvType(PayConstant.IN_H5_PAY);
//        //微信内支付
//        if ((request.getHeader("user-agent").toLowerCase().contains("MicroMessenger".toLowerCase()))){
//            buyRollParamVO.setPayEnvType(PayConstant.IN_WEIXIN_PAY);
//            buyRollParamVO.setOpenId(memberService.getMemberById(member.getId()).getWeixinPayOpenId());
//        }
        if (PayConstant.IN_WEIXIN_PAY == buyRollParamVO.getPayEnvType()){
            buyRollParamVO.setOpenId(memberService.getMemberById(member.getId()).getWeixinPayOpenId());
        }
        return payService.buyRoll(buyRollParamVO, member.getId()).toJSON();
    }
}
