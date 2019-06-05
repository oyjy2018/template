package com.ydc.service.order.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.ydc.beans.commom.OrderNoUtil;
import com.ydc.beans.config.OrderNoConfig;
import com.ydc.beans.file.FileUtil;
import com.ydc.beans.mq.sms.service.SMSSendMessageService;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.*;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.*;
import com.ydc.commom.view.dto.cgj.CommodityShowDTO;
import com.ydc.commom.view.dto.cgj.OrderReqDTO;
import com.ydc.commom.view.dto.cgj.order.*;
import com.ydc.commom.view.vo.cgj.order.*;
import com.ydc.commom.view.vo.cgj.shopCart.BuyerOrderCommodityParamVO;
import com.ydc.commom.view.vo.cgj.shopCart.CreateOrderParamVO;
import com.ydc.commom.view.vo.cgj.shopCart.ShopCartParamVO;
import com.ydc.dao.cgj.order.OrderCommodityDao;
import com.ydc.dao.cgj.order.OrderDao;
import com.ydc.dao.cgj.order.OrderDeliveryAddressDao;
import com.ydc.dao.cgj.store.CommodityDao;
import com.ydc.dao.cgj.store.CommodityModelDao;
import com.ydc.dao.cgj.store.StoreShopCartCommodityDao;
import com.ydc.dao.cgj.user.*;
import com.ydc.model.cgj.*;
import com.ydc.service.order.service.DictionaryDetailService;
import com.ydc.service.order.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LogManager.getLogger(OrderService.class);

    @Resource
    OrderDao orderDao;
    @Resource
    OrderCommodityDao orderCommodityDao;
    @Resource
    CommodityDao commodityDao;
    @Resource
    StoreShopCartCommodityDao storeShopCartCommodityDao;
    @Resource
    CommodityModelDao commodityModelDao;
    @Resource
    IntegralDao integralDao;
    @Resource
    MemberDao memberDao;
    @Resource
    IntegralDetailDao integralDetailDao;
    @Resource
    OrderDeliveryAddressDao orderDeliveryAddressDao;
    @Resource
    MemberDeliveryAddressDao memberDeliveryAddressDao;
    @Resource
    DictionaryDetailService dictionaryDetailService;
    @Resource
    OrderNoConfig orderNoMerchantConfig;
    @Resource
    OrderNoConfig orderNoMemberConfig;
    @Resource
    OrderNoConfig orderNoPayWaterConfig;
    @Autowired
    SMSSendMessageService smsSendMessageService;
    @Resource
    MemberWaterDao memberWaterDao;



    @Override
    public Order selectByPrimaryKey(Integer id) {
        return orderDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> queryUserList() {
        return null;

    }

    @Override
    public List<Map<String, Object>> getOrderList(OrderReqDTO orderReqDTO) {
        return orderDao.getOrderList(orderReqDTO);
    }

    @Override
    public Map<String, Object> getOrderListCount(OrderReqDTO orderReqDTO) {
        return orderDao.getOrderListCount(orderReqDTO);
    }

    @Override
    public List<MyOrderVO> logicalGetOrderList(MyOrderDTO myOrderDTO) {
        List<MyOrderVO> orderList = getOrdersByMemberId(myOrderDTO);
        List<MyOrderVO> myOrderVOList = new ArrayList<>();
        if(orderList == null || orderList.isEmpty())return myOrderVOList;
        for(MyOrderVO orM : orderList){
            List<MyOrderCommodityVO> myOrderCommodityVOList = new ArrayList<>();
            MyOrderCommodityVO myOrderCommodityVO = null;
            String browseHomeLittleFileUrl = null;
            String browseHomeFileUrl = null;
            List<OrderCommodity> orderCommodityList = orderCommodityDao.getOrderCommodity(myOrderDTO.getMemberId(),orM.getOrderId(),orM.getSupplierCode());
            for(OrderCommodity orc : orderCommodityList){
                myOrderCommodityVO = new MyOrderCommodityVO();
                myOrderCommodityVO.setCommodityId(orc.getCommodityId());
                myOrderCommodityVO.setCommodityName(orc.getCommodityName());
                myOrderCommodityVO.setCommodityModelId(orc.getCommodityModelId());
                myOrderCommodityVO.setCommodityModelName(orc.getCommodityModelName());
                myOrderCommodityVO.setCommodityModelNumber(orc.getCommodityModelNumber());
                myOrderCommodityVO.setSinglePrice(orc.getSinglePrice());
                myOrderCommodityVO.setSellPrice(orc.getSellPrice());
                myOrderCommodityVO.setHomeLittleFileName(orc.getHomeLittleFileName());
                myOrderCommodityVO.setHomeFileName(orc.getHomeFileName());
                myOrderCommodityVO.setHomePageImgId(orc.getHomePageImgId());
                try {
                        browseHomeLittleFileUrl = FileUtil.getBrowseFile(orc.getHomeLittleFileUrl(), orc.getHomeLittleFileName());
                        browseHomeFileUrl = FileUtil.getBrowseFile(orc.getHomeFileUrl(), orc.getHomeFileName());
                    } catch (Exception e) {
                        logger.error("获取图片访问地址异常订单号【" + orM.getOrderId() + "】，订单商品code【"+orM.getSupplierCode()+"】,图片ID【" + orc.getHomePageImgId() + "】",e);
                    }
                myOrderCommodityVO.setBrowseHomeLittleFileUrl(browseHomeLittleFileUrl);
                myOrderCommodityVO.setBrowseHomeFileUrl(browseHomeFileUrl);
                myOrderCommodityVOList.add(myOrderCommodityVO);
            }
            orM.setMyOrderCommodityVOList(myOrderCommodityVOList);
            myOrderVOList.add(orM);
        }
        logger.info("H5我的订单:"+JsonUtil.gsonStr(myOrderVOList));
        return myOrderVOList;
    }

    @Override
    public MyOrderVO logicalGetOrderCommodity(OrderDetailsDTO orderDetailsDTO,MyOrderVO myOrderVO) {
//        MyOrderVO myOrderVO = getOrderByMemberId(orderDetailsDTO);
        OrderDeliveryAddress orderDeliveryAddress = orderDeliveryAddressDao.getOrderDeliveryAddress(orderDetailsDTO.getOrderId());
        if (orderDeliveryAddress != null){
            logger.info("订单收货地址:"+JsonUtil.gsonStr(orderDeliveryAddress));
            //MyOrderVO myOrderVO = new MyOrderVO();
            myOrderVO.setAddressee(orderDeliveryAddress.getAddressee());
            myOrderVO.setPhoneNumber(orderDeliveryAddress.getPhoneNumber());
            myOrderVO.setConsigneeAddress(orderDeliveryAddress.getConsigneeAddress());
        }
        Integer orderId = orderDetailsDTO.getOrderId();
        if(OrderConstant.ORDER_STATUS_UNPAID == myOrderVO.getOrderStatus()){
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDGBDD, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_KEY_ZDGBDD, DictionaryConstant.DICT_PARENT_DICT_CODE_MQDELAYTIMELEVEL)));
            Long l = Long.valueOf(Integer.parseInt(dic.get().getRemark1()) * 60 * 1000);
            myOrderVO.setCountdown(l - (new Date().getTime() - DateUtil.parseDate(myOrderVO.getCreateTime(),DateUtil.DATATIMEF_STR).getTime()));
        }else{
            myOrderVO.setCountdown((long) 0);
        }
        List<MyOrderCommodityVO> myOrderCommodityVOList = new ArrayList<>();
        MyOrderCommodityVO myOrderCommodityVO = null;
        String browseHomeLittleFileUrl = null;
        String browseHomeFileUrl = null;
        List<OrderCommodity> orderCommodityList = orderCommodityDao.getOrderCommodity(orderDetailsDTO.getMemberId(),orderId,myOrderVO.getSupplierCode());
        for(OrderCommodity orc : orderCommodityList){
            myOrderCommodityVO = new MyOrderCommodityVO();
            myOrderCommodityVO.setCommodityId(orc.getCommodityId());
            myOrderCommodityVO.setCommodityName(orc.getCommodityName());
            myOrderCommodityVO.setCommodityModelId(orc.getCommodityModelId());
            myOrderCommodityVO.setCommodityModelName(orc.getCommodityModelName());
            myOrderCommodityVO.setCommodityModelNumber(orc.getCommodityModelNumber());
            myOrderCommodityVO.setSinglePrice(orc.getSinglePrice());
            myOrderCommodityVO.setSellPrice(orc.getSellPrice());
            myOrderCommodityVO.setHomeLittleFileName(orc.getHomeLittleFileName());
            myOrderCommodityVO.setHomeFileName(orc.getHomeFileName());
            myOrderCommodityVO.setHomePageImgId(orc.getHomePageImgId());
            try {
                browseHomeLittleFileUrl = FileUtil.getBrowseFile(orc.getHomeLittleFileUrl(), orc.getHomeLittleFileName());
                browseHomeFileUrl = FileUtil.getBrowseFile(orc.getHomeFileUrl(), orc.getHomeFileName());
            } catch (Exception e) {
                logger.error("获取图片访问地址异常订单号【" + orderId + "】，订单商品code【"+myOrderVO.getSupplierCode()+"】,图片ID【" + orc.getHomePageImgId() + "】",e);
            }
            myOrderCommodityVO.setBrowseHomeLittleFileUrl(browseHomeLittleFileUrl);
            myOrderCommodityVO.setBrowseHomeFileUrl(browseHomeFileUrl);
            myOrderCommodityVOList.add(myOrderCommodityVO);
        }
        myOrderVO.setMyOrderCommodityVOList(myOrderCommodityVOList);
        logger.info("H5我的订单:"+JsonUtil.gsonStr(myOrderVO));
        return myOrderVO;
    }

    @Override
    public Integer cancelOrder(Integer status, Integer updateBy, Integer orderId,String cancelReason,String cancelTime,Integer oldStatus) {
        return orderDao.cancelOrder(status,updateBy,orderId,cancelReason,cancelTime,oldStatus);
    }

    @Override
    public Integer notarizeReceiving(Integer status, Integer memberId, Integer orderId) {
        return orderDao.notarizeReceiving(status,memberId,orderId);
    }

    @Override
    public   Result<List<OrderAndOrderCommodityListDTO>> createOrder(CreateOrderParamVO createOrderParamVO, Integer memberId) {
        Member member= memberDao.selectByPrimaryKey(memberId);
        if (null == member){
            return Result.failure("用户未登录");
        }
        List<BuyerOrderCommodityParamVO> buyerOrderCommodityParamVOS=createOrderParamVO.getBuyerOrderCommodityParamVOS();
        if (null==buyerOrderCommodityParamVOS){
            return Result.failure("参数不能为空");
        }
        List<OrderAndOrderCommodityListDTO> orderAndOrderCommodityListDTOList=new ArrayList<>();
        String orderUserNo=OrderNoUtil.getOrderNo(orderNoMemberConfig);
         for (BuyerOrderCommodityParamVO buyerOrderCommodityParamVO:buyerOrderCommodityParamVOS){

            List<ShopCartParamVO> shopCartParamVOS=buyerOrderCommodityParamVO.getShopCartParamVOS();
            if (null==shopCartParamVOS){
                logger.info("商品参数不存在");
                return  Result.failure("参数不能为空");
            }
            List<OrderCommodity> orderCommodityList=new ArrayList<>();
            BigDecimal totalPrice=BigDecimal.ZERO;
            int totalNumber=0;

            String supplierCode=null,supplierName=null;
            for (ShopCartParamVO shopCartParamVO:shopCartParamVOS){
                OrderCommodity  orderCommodityTemp= createOrderCommodity(shopCartParamVO);
                if ( null==orderCommodityTemp){
                    logger.info("生产订单商品失败");
                    return   Result.failure("库存不足");
                }
                orderCommodityList.add(orderCommodityTemp);
                totalPrice=totalPrice.add(orderCommodityTemp.getSellPrice());
                totalNumber=totalNumber+orderCommodityTemp.getCommodityModelNumber().intValue();
                supplierCode=orderCommodityTemp.getSupplierCode();
                supplierName=orderCommodityTemp.getSupplierName();
            }
            if (StringUtil.isEmpty(supplierCode) ){

                return Result.failure("商家已下架");
            }else {
                //创建总订单
                logger.info("商家编号 {}",supplierCode);
                orderNoMerchantConfig.setOrderNoSuffix(supplierCode);
            }
            String orderNo=OrderNoUtil.getOrderNo(orderNoMerchantConfig);
            Order order=new Order();
            order.setMemberId(memberId);
            order.setOrderNo(orderNo);
            order.setStatus(OrderConstant.ORDER_STATUS_CREATE);
            order.setSupplierCode(supplierCode);
            order.setSupplierName(supplierName);
            order.setBuyerMessage(StringUtil.removeNonBmpUnicode(buyerOrderCommodityParamVO.getBuyerMessage()));
            order.setLogisticsStatus(9);
            order.setOrderAmount(totalPrice);
            order.setCommodityCount(totalNumber);
            order.setLogisticsStatus(0);
            order.setCreateBy(memberId);
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            order.setUpdateBy(memberId);
            order.setOrderNumber(orderUserNo);
          /*   String payWater=OrderNoUtil.getOrderNo("ORDER:PAY:NO");
             order.setPayWater(payWater);*/
            int countOrder=orderDao.insert(order);
            if (countOrder!=1){
                logger.info("创建主订单为空");
                return  Result.failure("下单人数过多，请稍后重试");
            }

            //创建订单商品
            for (OrderCommodity orderCommodity:orderCommodityList){
                orderCommodity.setMemberId(memberId);
                orderCommodity.setOrderNo(orderNo);
                orderCommodity.setOrderId(order.getId());
                orderCommodity.setCreateBy(memberId);
                orderCommodity.setCreateTime(new Date());
                orderCommodity.setUpdateTime(new Date());
                orderCommodity.setUpdateBy(memberId);
                orderCommodity.setLogisticsStatus(0);
               if (orderCommodityDao.insert(orderCommodity)!=1){
                   logger.info("数据库生成订单商品错误");
                   return  Result.failure("下单人数过多，请稍后重试");
               }
            }
            //创建物流
             MemberDeliveryAddress memberDeliveryAddress= memberDeliveryAddressDao.selectByPrimaryKey(createOrderParamVO.getMemberDeliveryAddressId());
            OrderDeliveryAddress orderDeliveryAddress=new OrderDeliveryAddress();
             orderDeliveryAddress.setOrderId(order.getId());
             StringBuilder sb=new StringBuilder();
             if (StringUtil.isNotEmpty(memberDeliveryAddress.getAddressProvince())){
                 sb.append(memberDeliveryAddress.getAddressProvince()).append(" ");
             }
             if (StringUtil.isNotEmpty(memberDeliveryAddress.getAddressCity())){
                 sb.append(memberDeliveryAddress.getAddressCity()).append(" ");
             }
             if (StringUtil.isNotEmpty(memberDeliveryAddress.getAddressRegion())){
                 sb.append(memberDeliveryAddress.getAddressRegion()).append(" ");
             }
             if (StringUtil.isNotEmpty(memberDeliveryAddress.getAddressDetail())){
                 sb.append(memberDeliveryAddress.getAddressDetail());
             }

             orderDeliveryAddress.setConsigneeAddress(sb.toString());
             orderDeliveryAddress.setAddressee(memberDeliveryAddress.getLinkName());
             orderDeliveryAddress.setPhoneNumber(memberDeliveryAddress.getLinkPhone());
             orderDeliveryAddress.setCreateTime(new Date());
             orderDeliveryAddress.setUpdateTime(new Date());
             orderDeliveryAddressDao.insert(orderDeliveryAddress);

             OrderAndOrderCommodityListDTO orderAndOrderCommodityListDTO=new OrderAndOrderCommodityListDTO();
             orderAndOrderCommodityListDTO.setOrder(order);
             orderAndOrderCommodityListDTO.setOrderCommodityList(orderCommodityList);
             orderAndOrderCommodityListDTOList.add(orderAndOrderCommodityListDTO);
        }
        if (null==member.getFirstOrderDate()){
             member.setFirstOrderDate(new Date());
             memberDao.updateByPrimaryKeySelective(member);
        }
        return  Result.success(orderAndOrderCommodityListDTOList);
    }



    @Override
    @Transactional(value = "cgjTransactionManager")
    public AddOrderResponseVO updateCommodityNumAndOrderStatus(List<OrderAndOrderCommodityListDTO> orderAndOrderCommodityListDTOList) {
        Date date=new Date();
        List<String> orderNoS=new ArrayList<>();
        List<Integer> orderIds=new ArrayList<>();
        BigDecimal totalPrice=BigDecimal.ZERO;
        for (OrderAndOrderCommodityListDTO orderAndOrderCommodityListDTO:orderAndOrderCommodityListDTOList) {
            for (OrderCommodity orderCommodity : orderAndOrderCommodityListDTO.getOrderCommodityList()) {
                //更新商品模型表
                if (!updateCommodityModelNum(orderCommodity.getCommodityId(), orderCommodity.getCommodityModelId(), orderCommodity.getCommodityModelNumber(),"-")) {
                    //回滚
                    throw new NullPointerException();
                }
                //更新商品表
                updateCommodityNum(orderCommodity.getCommodityId(), orderCommodity.getCommodityModelNumber(),"-");
            }
            //生成支付流水
            Order orderTemp = orderDao.selectByPrimaryKey(orderAndOrderCommodityListDTO.getOrder().getId());
            orderTemp.setStatus(OrderConstant.ORDER_STATUS_UNPAID);
            orderTemp.setUpdateTime(date);
            if (orderDao.updateByPrimaryKeySelective(orderTemp) != 1) {
                //回滚
                throw new ServiceRuntimeException();
            }
            totalPrice=totalPrice.add(orderTemp.getOrderAmount());
            orderNoS.add(orderTemp.getOrderNo());
            orderIds.add(orderTemp.getId());
        }
        AddOrderResponseVO addOrderResponseVO=new AddOrderResponseVO();
        addOrderResponseVO.setSellPrice(totalPrice);
        addOrderResponseVO.setCreateTime(date);
        addOrderResponseVO.setOrderNOList(orderNoS);
        addOrderResponseVO.setOrderIdList(orderIds);
        return addOrderResponseVO;
    }

    @Override
    public void updateOrderShopCartStatus(Set<Integer> ids, Integer memberId) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("memberId",memberId);
        paramMap.put("ids",ids);
        paramMap.put("deleteStatus",2);
        storeShopCartCommodityDao.updateByIds(paramMap);
    }


    @Override
    @Transactional(value="cgjTransactionManager")
    public Result updateOrderPay(PayOrderParamVO payOrderParamVO, Integer memberId) throws Exception {
        Date currentDate=new Date();
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
            if (DateUtil.compareSubDate(currentDate,order.getCreateTime(),DateUtil.DATE_LONG_30) >=0 ){
                //订单超时
                CancelOrderDTO cancelOrderDTO=new CancelOrderDTO();
                cancelOrderDTO.setOrderId(order.getId());
                cancelOrderDTO.setCancelReason("订单超时未支付");
                cancelOrderDTO.setMemberId(memberId);
                this.cancelOrder(cancelOrderDTO);
                return  Result.failure("订单超时");
            }
            totalPrice=totalPrice.add(order.getOrderAmount());
            orderList.add(order);
        }
        if (totalPrice.compareTo(payOrderParamVO.getSellPrice())!=0){
            logger.info("支付金额错误 应付金额："+totalPrice+"实际金额+："+payOrderParamVO.getSellPrice());
            throw  new ServiceRuntimeException("支付金额错误");
        }
        AddOrderResponseVO addOrderResponseVO=new AddOrderResponseVO();
        List<String> orderNoS=new ArrayList<>();
        List<Integer> orderIds=new ArrayList<>();


        String payWater=OrderNoUtil.getOrderNo(orderNoPayWaterConfig);
        //扣除用户积分
        if ( !updateMemberIntegral(memberId,totalPrice,0,payWater)){
            throw  new ServiceRuntimeException("余额扣除失败");
        }

        StringBuilder orderNoSB=new StringBuilder();
        int size=orderList.size();

        Map<String,Object> param=new HashMap<>();
        param.put("orderNos",payOrderParamVO.getOrderNOList());
        param.put("oldStatus",OrderConstant.ORDER_STATUS_UNPAID);
        param.put("newStatus",OrderConstant.ORDER_STATUS_PAID_UNDELIVERED);
        param.put("updateTime",currentDate);
        param.put("paymentTime",currentDate);
        param.put("logisticsStatus",0);
        param.put("payWater",payWater);
        param.put("paymentMethod",payOrderParamVO.getPaymentMethod());
        param.put("paymentCurrency","integral");
        int count=orderDao.updateBatchByStatus(param);
        if (payOrderParamVO.getOrderNOList().size()!=count){
            throw  new ServiceRuntimeException("请重新刷新订单");
        }
        for (int i=0;i<size;i++){
            Order order=orderList.get(i);
            orderNoS.add(order.getOrderNo());
            orderIds.add(order.getId());
            orderNoSB.append(order.getOrderNo());
            if (i!=size-1){
                orderNoSB.append(",");
            }
        }
        addOrderResponseVO.setOrderNOList(orderNoS);
        addOrderResponseVO.setOrderIdList(orderIds);

        //发送短信验证码是否打开
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW));
        if (dictionaryDetail != null && ("1").equals(dictionaryDetail.getDictValue())) {
            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)));
            if(dic.isPresent() && dic.get().getDictValue().equals("1")){
                //发送短信
                Member member = memberDao.selectByPrimaryKey(memberId);
                smsSendMessageService.sendSMSMessage(member.getMobilePhone(),orderNoSB.toString(), AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode());
            }else{
                sendMessage(memberId,orderNoSB.toString());
            }
        }
        return Result.success(addOrderResponseVO);
    }

    private void sendMessage(Integer memberId,String orderNOs){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Member member= memberDao.selectByPrimaryKey(memberId);
                try {
                    // 阿里云短信平台
                    DictionaryDetail alydx = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX)
                            .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX));
                    // 发送阿里云短信
                    AliyunSmsUtil.sendValidateCode(member.getMobilePhone(), orderNOs, AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode(), alydx.getDictValue(), alydx.getRemark1(), alydx.getRemark2());
                } catch (ClientException e) {
                    logger.info("发送短信失败 手机号："+member.getMobilePhone()+"_订单号：__"+orderNOs+" 短信模板："+AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_PAY.getValidateCode()+"___异常"+e.getMessage());
                }
            }
        }).start();

    }

    @Override
    public List<MyOrderVO> getOrdersByMemberId(MyOrderDTO myOrderDTO) {
        return PaginationUtil.paginationQuery(myOrderDTO ,(tempMyOrderDTO) -> orderDao.getOrdersByMemberId(myOrderDTO));
    }

    @Override
    public MyOrderVO getOrderByMemberId(OrderDetailsDTO orderDetailsDTO) {
        return orderDao.getOrderByMemberId(orderDetailsDTO);
    }

    @Transactional(value="cgjTransactionManager")
    @Override
    public void cancelOrder(CancelOrderDTO cancelOrderDTO){
        Integer updateRow = cancelOrder(OrderConstant.ORDER_STATUS_CLOSED, cancelOrderDTO.getMemberId(), cancelOrderDTO.getOrderId(),cancelOrderDTO.getCancelReason(),DateUtil.format(new Date(),DateUtil.DATATIMEF_STR),OrderConstant.ORDER_STATUS_UNPAID);
        if(updateRow != null && updateRow > 0){
            List<OrderCommodity> orderCommodityList = orderCommodityDao.getOrderCommodityByOrderId(cancelOrderDTO.getOrderId());
            for(OrderCommodity orc : orderCommodityList){
                //更新商品模型表
                if (!updateCommodityModelNum(orc.getCommodityId(), orc.getCommodityModelId(), orc.getCommodityModelNumber(),"+")) {
                    //回滚
                    logger.error("更新商品模型表失败");
                    throw new NullPointerException();
                }
                //更新商品表
                updateCommodityNum(orc.getCommodityId(), orc.getCommodityModelNumber(),"+");
                if("先放入购物车".equals(cancelOrderDTO.getCancelReason())){
                    Map<String,Object> paramMap=new HashMap<>();
                    paramMap.put("memberId",cancelOrderDTO.getMemberId());
                    paramMap.put("updateBy",cancelOrderDTO.getMemberId());
                    paramMap.put("deleteStatus",DeleteStatusConstant.STATUS_NOT_DELETE);
                    paramMap.put("commodityId",orc.getCommodityId());
                    paramMap.put("commodityModelId",orc.getCommodityModelId());
                    storeShopCartCommodityDao.updateDeleteStatus(paramMap);
                }
            }
        }
        try {
            Order order= orderDao.selectByPrimaryKey(cancelOrderDTO.getOrderId());
            logger.info("subject:{},updateRow:{},order:{}","超时未支付关闭订单",updateRow,JsonUtil.gsonStr(order));
            //更新订单流水
            memberWaterDao.updateWaterStatus(order.getPayWater(), PayConstant.WATER_STATUS_CANCEL,PayConstant.WATER_STATUS_ING, null);
        }catch (Exception e){
            logger.error("subject:{},e:{}","更新订单流水异常 订单号:"+cancelOrderDTO.getOrderId(),e);
        }

    }

    @Override
    public void notarizeReceiving(Integer orderId, Integer memberId){
        this.notarizeReceiving(OrderConstant.ORDER_STATUS_RECEIVED, memberId, orderId);
    }

    @Override
    public List<Map<String, Object>> getOrderStatusNum(Integer memberId) {
        List<OrderStatusNumVO> orderStatusNumVOS = orderDao.getOrderStatusNum(memberId);
        if(orderStatusNumVOS == null || orderStatusNumVOS.isEmpty())return Lists.newArrayList();
        Map<Integer, List<OrderStatusNumVO>> listMap = orderStatusNumVOS.stream().collect(Collectors.groupingBy(OrderStatusNumVO::getStatus));
        List<Integer> statusList = Arrays.asList(OrderConstant.ORDER_STATUS_UNPAID,OrderConstant.ORDER_STATUS_PAID_UNDELIVERED
                ,OrderConstant.ORDER_STATUS_SHIPPED,OrderConstant.ORDER_STATUS_RECEIVED);
        List<Map<String, Object>> voList = new ArrayList<>();
        Map<String, Object> map;
        for(Integer integer : statusList){
            map = new HashMap<>();
            map.put("status",integer);
            if(StringUtil.isEmpty(listMap.get(integer))){
              map.put("num",0);
            }else {
              map.put("num",listMap.get(integer).get(0).getNum());
            }
            voList.add(map);
        }
        return voList;
    }

    @Override
    public Integer updateAutoConfirmReceipt(Integer orderId) {
        return orderDao.updateAutoConfirmReceipt(orderId);
    }

    @Override
    public Order getOrderByOrderCommodityIds(String orderCommodityIds) {
        return orderDao.getOrderByOrderCommodityIds(orderCommodityIds);
    }

    @Transactional(value="cgjTransactionManager")
    @Override
    public Boolean updateOrderStatus(List<String> orderNos) {

        if ( !updateMemberIntegral(1,new BigDecimal(100.00),0,"11111")){
          logger.info("积分扣除失败");
            return false;
        }
        String payWater=OrderNoUtil.getOrderNo(orderNoPayWaterConfig);
        Map<String,Object> param=new HashMap<>();
        param.put("orderNos",orderNos);
        param.put("oldStatus",OrderConstant.ORDER_STATUS_UNPAID);
        param.put("newStatus",OrderConstant.ORDER_STATUS_PAID_UNDELIVERED);
        param.put("updateTime",new Date());
        param.put("paymentTime",new Date());
        param.put("logisticsStatus",0);
        param.put("payWater",payWater);
        param.put("paymentMethod","integral");
        param.put("paymentCurrency","integral");
        int count=orderDao.updateBatchByStatus(param);
        if (orderNos.size()!=count){
            throw  new ServiceRuntimeException("付款失败");
        }
        return true;
    }

    //扣除用户积分
    @Override
    public boolean updateMemberIntegral(Integer memberId,BigDecimal number,Integer integralTypeConsume,String payWater){
        Integral integral= integralDao.getIntegralByMumberId(memberId);
        if (null==integral || StringUtil.isEmpty(integral.getUsableIntegral())
                || integral.getUsableIntegral().compareTo(number)<0){
            logger.info("用户积分不够" );
            return false;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("usableIntegral", number);
        param.put("integralId", integral.getId());
        param.put("version", integral.getVersion());
        param.put("updateBy", memberId);
        Integer updateRow = integralDao.updateIntegral(param);
        logger.info("第一次扣除积分 memberId："+memberId+" number:"+number );
        if (updateRow == null || updateRow.intValue()<=0 ) {
            integral= integralDao.getIntegralByMumberId(memberId);
            param.put("version", integral.getVersion());
            updateRow=integralDao.updateIntegral(param);
            if (updateRow == null || updateRow.intValue() <= 0){
                logger.info("第二次扣除积分 memberId："+memberId+" number:"+number );
                return false;
            }
        }
        try {
            Member member = memberDao.selectByPrimaryKey(memberId);
            Date date = new Date();
            //新增积分明细表
            IntegralDetail integralDetail = new IntegralDetail();
            integralDetail.setMemberId(member.getId());
            integralDetail.setMemberName(member.getMemberName());
            integralDetail.setMobilePhone(member.getMobilePhone());
            integralDetail.setPayTime(date);
            integralDetail.setIntegralPay(new StringBuffer().append("-").append(String.valueOf(number)).toString());
            integralDetail.setPayType(1);
            integralDetail.setChangeIntegralBalance(integral.getUsableIntegral().subtract(number));
            integralDetail.setIntegralTypeConsume(integralTypeConsume);
            integralDetail.setPayWater(payWater);
            integralDetail.setCreateTime(date);
            integralDetail.setCreateBy(memberId);
            integralDetailDao.insert(integralDetail);
        }catch (Exception e){
            logger.info("用户积分明细添加失败 memberId:"+memberId+" number"+number+" 异常："+e.getMessage());
        }
        return  true;
    }

    // 创建单个商品
    private OrderCommodity createOrderCommodity(ShopCartParamVO shopCartParamVO){
        Integer commodityId=shopCartParamVO.getCommodityId(),
                modelId=shopCartParamVO.getModelId();
        if (StringUtil.isNotEmpty(shopCartParamVO.getId())){
            //从购物车来的
            Map<String,Object> shopCartMap=new HashMap<>();
            shopCartMap.put("id",shopCartParamVO.getId());
            shopCartMap.put("memberId",shopCartParamVO.getMemberId());
            shopCartMap.put("commodityId",shopCartParamVO.getCommodityId());
            shopCartMap.put("commodityModelId",shopCartParamVO.getModelId());
            shopCartMap.put("deleteStatus",1);
            logger.info("subject: {}, shopCartMap: {}", "下单查询购物车", shopCartMap);
            //判断
            List<StoreShopCartCommodity> storeShopCartCommodities= storeShopCartCommodityDao.selectStoreShopCart(shopCartMap);
            StoreShopCartCommodity storeShopCartCommodity=storeShopCartCommodities.get(0);
            if (null==storeShopCartCommodity){
                logger.info("购物车商品错误");
                return null;
            }
            commodityId=storeShopCartCommodity.getCommodityId();
            modelId=storeShopCartCommodity.getCommodityModelId();
        }
        Map<String,Object> commodityParamMap=new HashMap<>();
        commodityParamMap.put("commodityId",commodityId);
        commodityParamMap.put("modelId",modelId);
        logger.info("查询商品 commodityId{}， modelId{}",commodityId,modelId);
        List<OrderCommodityDTO> orderCommodityDTOS= commodityDao.getOrderCommodityDTO(commodityParamMap);
        if (null!=orderCommodityDTOS && !orderCommodityDTOS.isEmpty()){

            OrderCommodityDTO orderCommodityDTO=orderCommodityDTOS.get(0);
            logger.info( "查询商品数量{}, 商品编号{}",orderCommodityDTOS.size(),orderCommodityDTO.getSupplierCode());
            if (orderCommodityDTO.getInventory().compareTo(shopCartParamVO.getNumber())>=0){
                //创建订单商品
                OrderCommodity orderCommodity =orderCommodityDTO.createOrderCommodity();
                //计算总价格
                BigDecimal sellPrice=orderCommodityDTO.getSellPrice(),
                        number=new BigDecimal(shopCartParamVO.getNumber());
                sellPrice=sellPrice.multiply(number);
                orderCommodity.setSellPrice(sellPrice);
                orderCommodity.setCommodityModelNumber(shopCartParamVO.getNumber());
                return orderCommodity;
            }
            logger.info("库存不足");
            return null;
        }
        logger.info("商品不存在");
        return null;
    }


    /**
     *
     * @param commodityId
     * @param commodityModelId
     * @param number
     * @param type + "表示添加库存" - 表示扣除库存
     * @return
     */
    private boolean updateCommodityModelNum(Integer commodityId,Integer commodityModelId, Integer number,String type){

        if (StringUtil.isEmpty(type)){
            return false;
        }
        CommodityModel commodityModel= commodityModelDao.selectByPrimaryKey(commodityModelId);
        if ( null==commodityModel ||null==commodityModel.getInventory() || commodityModel.getInventory().intValue()<0){
            return false;
        }
        Integer soldNumber=commodityModel.getSoldNumber(),
                inventory=commodityModel.getInventory();
        if ( StringUtil.isEmpty(soldNumber)){
            soldNumber=0;
        }

        if("-".equals(type)){
            if (inventory.compareTo(number)<0){
                return  false;
            }
            inventory=inventory.intValue()-number.intValue();
            soldNumber=soldNumber+number.intValue();

        }else if ("+".equals(type)){
            inventory=inventory.intValue()+number.intValue();
            soldNumber=soldNumber-number.intValue();
        }
        commodityModel.setInventory(inventory);
        commodityModel.setSoldNumber(soldNumber);
        int count=commodityModelDao.updateByIdAndVersion(commodityModel);
        if (count==1){
            return true;
        }
        return false;
    }

    /**
     *
     * @param commodityId
     * @param number
     * @param type + "表示添加库存" - 表示扣除库存
     * @return
     */
    private boolean updateCommodityNum(Integer commodityId, Integer number,String type){
        //更新商品主表
        Commodity commodity=commodityDao.selectByPrimaryKey(commodityId);
        if (commodity == null)return false;
        Integer soldNumber=commodity.getSoldNumber(),
                totalInventory=commodity.getTotalInventory();

        if (StringUtil.isEmpty(soldNumber)){
            soldNumber=0;
        }
        if (StringUtil.isEmpty(totalInventory)){
            totalInventory=0;
        }
        final int tempTotalInventory = totalInventory;

        if("-".equals(type)){
            totalInventory=totalInventory.intValue()-number.intValue();
            soldNumber=soldNumber.intValue()+number.intValue();

        }else if ("+".equals(type)){
            totalInventory=totalInventory.intValue()+number.intValue();
            soldNumber=soldNumber.intValue()-number.intValue();
        }
        commodity.setTotalInventory(totalInventory);
        commodity.setSoldNumber(soldNumber);
        commodity.setUpdateTime(new Date());
       if (commodityDao.updateByPrimaryKeySelective(commodity)==1){
           logger.info("更新商品缓存, commodityId: " + commodityId);
           //如果原本库存为0或者更新库存为0，则更新缓存
           if(tempTotalInventory == 0 || commodity.getTotalInventory() == 0){
               new Thread(() ->{
                    Object object = RedisUtil.redisHashGet(CommodityConstant.COMMODITY_REDIS_KEY, String.valueOf(commodity.getId()));
                    if(object != null){
                        CommodityShowDTO commodityShowDTO = (CommodityShowDTO) object;
                        commodityShowDTO.setSoldNumber(commodity.getSoldNumber());
                        commodityShowDTO.setTotalInventory(commodity.getTotalInventory());
                        RedisUtil.redisHashPut(CommodityConstant.COMMODITY_REDIS_KEY, String.valueOf(commodity.getId()), commodityShowDTO);
                    }
               }).start();
           }
            return true;
       }
        return false;
    }

    @Override
    public int updateOrderStatusByWater(String payWater, int newStatus, Integer oldStatus, Date paymentTime) {
        return orderDao.updateOrderStatusByWater(payWater, newStatus, oldStatus, paymentTime);
    }

    @Override
    public List<Map<String, Object>> getRollNumInOrder(String payWater) {
        return orderDao.getRollNumInOrder(payWater);
    }

    @Override
    public Integer getOrderMemberId(String payWater) {
        return orderDao.getOrderMemberId(payWater);
    }

    @Override
    public int updateSendRollOrderStatus(List<String> orderNoList) {
        return orderDao.updateSendRollOrderStatus(orderNoList);
    }
}
