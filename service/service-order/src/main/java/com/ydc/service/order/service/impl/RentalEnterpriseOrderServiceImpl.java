package com.ydc.service.order.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.constant.rental.RentalEnterpriseOrderConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.enums.rental.RentalFeeVoucherEnum;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.*;
import com.ydc.commom.util.rental.RentalFeeUtil;
import com.ydc.commom.util.rental.RentalOrderNoUtil;
import com.ydc.commom.view.dto.cgj.rental.AddRentalEnterpriseOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseSettlementDTO;
import com.ydc.commom.view.vo.cgj.rental.EnterpriseOrderDetailB2BDVO;
import com.ydc.commom.view.vo.cgj.rental.EnterpriseOrderDetailB2BRVO;
import com.ydc.commom.view.vo.cgj.rental.RentalEnterpriseOrderListB2BDVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.PCRentalEnterpriseOrderVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.StoreRentalEnterpriseOrderVO;
import com.ydc.commom.view.vo.cgj.rental.RentalEnterpriseOrderListB2BRVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.*;
import com.ydc.dao.cgj.common.HolidayDao;
import com.ydc.dao.cgj.rental.*;
import com.ydc.model.annotation.Attribute;
import com.ydc.model.cgj.common.Holiday;
import com.ydc.model.cgj.rental.*;
import com.ydc.service.order.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RentalEnterpriseOrderServiceImpl implements RentalEnterpriseOrderService {

    private final Logger logger = LogManager.getLogger(RentalEnterpriseOrderServiceImpl.class);

    @Autowired
    RentalEnterpriseOrderDao rentalEnterpriseOrderDao;
    @Autowired
    RentalFeeVoucherDao rentalFeeVoucherDao;
    @Autowired
    RentalCheckCarDao rentalCheckCarDao;
    @Autowired
    RentalCarPublishDao rentalCarPublishDao;
    @Autowired
    HolidayDao holidayDao;
    @Autowired
    RentalCheckCarService rentalCheckCarService;
    @Autowired
    RentalEnterpriseSettlementService rentalEnterpriseSettlementService;
    @Autowired
    RentalOrderFileService rentalOrderFileService;
    @Autowired
    DepositService depositService;
    @Autowired
    RentalFeeVoucherService rentalFeeVoucherService;
    @Autowired
    RentalCarPublishNumDao rentalCarPublishNumDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return rentalEnterpriseOrderDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RentalEnterpriseOrder record) {
        return rentalEnterpriseOrderDao.insert(record);
    }

    @Override
    public int insertSelective(RentalEnterpriseOrder record) {
        return rentalEnterpriseOrderDao.insertSelective(record);
    }

    @Override
    public RentalEnterpriseOrder selectByPrimaryKey(Integer id) {
        return rentalEnterpriseOrderDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RentalEnterpriseOrder record) {
        return rentalEnterpriseOrderDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RentalEnterpriseOrder record) {
        return rentalEnterpriseOrderDao.updateByPrimaryKey(record);
    }

    @Override
    public Result addRentalOrder(AddRentalEnterpriseOrderDTO dto) {
        //通过版本号控制（乐观锁），获取真实的库存，并提前修改库存
//        RentalCarPublish carPublish = rentalCarPublishDao.selectByPrimaryKey(dto.getResourceInfoId());
        RentalCarPublishNum rentalCarPublishNum = new RentalCarPublishNum();
        rentalCarPublishNum.setPublishId(dto.getResourceInfoId());
        rentalCarPublishNum.setCarLevel(dto.getCarLevel());
        rentalCarPublishNum.setCarBrand(dto.getCarBrand());
        rentalCarPublishNum.setCarSeries(dto.getCarSeries());
        rentalCarPublishNum.setCarModel(dto.getCarModel());
        rentalCarPublishNum.setCarSeriesId(dto.getSeriesId());
        List<RentalCarPublishNum> rentalCarPublishNums = rentalCarPublishNumDao.getRentalCarPublishNum(rentalCarPublishNum);
        if(rentalCarPublishNums == null || rentalCarPublishNums.isEmpty())
            return Result.failure("车辆剩余库存不足");
        rentalCarPublishNum = rentalCarPublishNums.get(0);
        //判断下单车辆数量是否大于车辆发布剩余数量
        if(dto.getReserveCount().intValue() > rentalCarPublishNum.getCarNum().intValue())
            return Result.failure("车辆剩余库存不足");

        /*int i = rentalCarPublishDao.updatePublishNumByVersion(carPublish.getId(),
                carPublish.getPublishNum() - dto.getReserveCount(), carPublish.getVersion());
        if(i == 0){
            carPublish = rentalCarPublishDao.selectByPrimaryKey(dto.getResourceInfoId());
            //判断下单车辆数量是否大于车辆发布剩余数量
            if(dto.getReserveCount().intValue() > carPublish.getPublishNum().intValue())
                return Result.failure("车辆剩余库存不足");

            i = rentalCarPublishDao.updatePublishNumByVersion(carPublish.getId(),
                    carPublish.getPublishNum() - dto.getReserveCount(), carPublish.getVersion());
            if(i == 0) return Result.failure("业务繁忙请重试");
        }*/
        //保存
        try{
            Date sysDate = new Date();
            RentalEnterpriseOrder reo = new RentalEnterpriseOrder();
            dto.initRentalEnterpriseOrder(reo);

            //新增默认待确认订单状态
            reo.setOrderNo(RentalOrderNoUtil.getOrderNo());
            reo.setStatus(RentalEnterpriseOrderConstant.ORDER_STATUS_1);
            reo.setFlowOneStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setFlowTwoStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setFlowThreeStatus(RentalEnterpriseOrderConstant.ORDER_FLOW_STATUS_0);
            reo.setCreateBy(reo.getDemandSideId());
            reo.setCreateTime(sysDate);
            rentalEnterpriseOrderDao.insertSelective(reo);
            return Result.success(reo.getId());
        }catch (Exception e){
            /*logger.error("提交订单异常，开始恢复预定数量。。。。", e);
            carPublish = rentalCarPublishDao.selectByPrimaryKey(dto.getResourceInfoId());
            i = rentalCarPublishDao.updatePublishNumByVersion(carPublish.getId(),
                    carPublish.getPublishNum() + dto.getReserveCount(), carPublish.getVersion());
            if(i == 0){
                carPublish = rentalCarPublishDao.selectByPrimaryKey(dto.getResourceInfoId());
                i = rentalCarPublishDao.updatePublishNumByVersion(carPublish.getId(),
                        carPublish.getPublishNum() + dto.getReserveCount(), carPublish.getVersion());
                if(i == 0) {
                    logger.error("恢复预定数量失败，请进行人工恢复，发布信息ID：{}，需要恢复的数量：{}", carPublish.getId(), dto.getReserveCount());
                }
            }*/
            return Result.failure("提交订单失败");  //加个注释 测试
        }
    }

    @Override
    public List<RentalEnterpriseOrderListB2BDVO> getEnterpriseOrderListB2BD(RentalEnterpriseOrderDTO dto) {
        List<RentalEnterpriseOrderListB2BDVO> orderList = rentalEnterpriseOrderDao.getEnterpriseOrderListB2BD(dto);
        if(orderList == null || orderList.isEmpty()) return orderList;
        orderList.forEach(order -> {
            try {
                order.setViewCarModelImgUrl(FileUtil.getBrowseFile(order.getCarModelImgUrl(), order.getCarModelImgName()));
            } catch (Exception e) {
                logger.error("处理图片预览地址异常", e);
            }
        });
        return orderList;
    }

    @Override
    public List<RentalEnterpriseOrderListB2BRVO> getEnterpriseOrderListB2BR(RentalEnterpriseOrderDTO dto) {
        List<RentalEnterpriseOrderListB2BRVO> orderList = rentalEnterpriseOrderDao.getEnterpriseOrderListB2BR(dto);
        if(orderList == null || orderList.isEmpty()) return orderList;
        orderList.forEach(order -> {
            try {
                order.setViewCarModelImgUrl(FileUtil.getBrowseFile(order.getCarModelImgUrl(), order.getCarModelImgName()));
            } catch (Exception e) {
                logger.error("处理图片预览地址异常", e);
            }
        });
        return orderList;
    }

    @Override
    public List<StoreRentalEnterpriseOrderVO> getStoreRentalEnterpriseOrderList(RentalEnterpriseOrderDTO dto) {
        List<StoreRentalEnterpriseOrderVO> storeRentalEnterpriseOrderVOS = PaginationUtil.paginationQuery(dto,(tempDto) -> rentalEnterpriseOrderDao.getStoreRentalEnterpriseOrderList(dto));
        storeRentalEnterpriseOrderVOS.forEach(item -> {
            try {
                item.setCarModelImgBrowse(FileUtil.getBrowseFile(item.getCarModelImgUrl(),item.getCarModelImgName()));
            } catch (Exception e) {
                logger.error("subject:{},e:{}","企业订单列表，车辆图片浏览异常",e);
            }
        });
        return storeRentalEnterpriseOrderVOS;
    }

    @Override
    public StoreRentalEnterpriseOrderVO getStoreRentalEnterpriseOrderByOrderId(RentalEnterpriseOrderDTO dto) {
        return rentalEnterpriseOrderDao.getStoreRentalEnterpriseOrderByOrderId(dto);
    }

    @Override
    public EnterpriseOrderDetailB2BDVO getEnterpriseOrderDetailB2BD(Integer orderId) {
        EnterpriseOrderDetailB2BDVO orderDetail = rentalEnterpriseOrderDao.getEnterpriseOrderDetailB2BD(orderId);
        try {
            orderDetail.setViewCarModelImgUrl(FileUtil.getBrowseFile(orderDetail.getCarModelImgUrl(), orderDetail.getCarModelImgName()));
        } catch (Exception e) {
            logger.error("subject:{},e:{}","订单详情，车辆图片浏览异常",e);
        }
        RentalFeeVoucher record = new RentalFeeVoucher();
        record.setOrderId(orderId);
        List<RentalFeeVoucher> rentalFeeVoucherList = rentalFeeVoucherDao.getRentalFeeVoucherByOrderId(record);
        List<RentalCheckCar> rentalCheckCarList = rentalCheckCarDao.getRentalCheckCar(new RentalCheckCarDTO(orderId));
        rentalFeeVoucherList.forEach(rentalFeeVoucher -> {
            //封装需求方支付租金信息
            if(RentalFeeVoucherEnum.VoucherType.STATUS1.getStatus().intValue() == rentalFeeVoucher.getVoucherType().intValue()){
                //需求方支付租金总额
                orderDetail.setPayRentTotal(rentalFeeVoucher.getOneMoney());
                //需求方支付押金
                orderDetail.setPayCashPledge(rentalFeeVoucher.getTwoMoney());
                orderDetail.setRentPayDate(DateUtil.formatDate(rentalFeeVoucher.getPayTime()));
            }
            //封装退还至需求方的押金退还信息
            if(RentalFeeVoucherEnum.VoucherType.STATUS4.getStatus().intValue() == rentalFeeVoucher.getVoucherType().intValue()){
                //违章结算金额
                orderDetail.setViolationFee(rentalFeeVoucher.getOneMoney());
                //退还押金
                orderDetail.setReturnCashPledge(rentalFeeVoucher.getTwoMoney());
                orderDetail.setReturnDate(DateUtil.formatDate(rentalFeeVoucher.getAccountTime()));
            }
        });
        orderDetail.setCarNumberList(rentalCheckCarList.stream().map(rentalCheckCar -> rentalCheckCar.getCarNumber()).collect(Collectors.toList()));
        return orderDetail;
    }

    @Override
    public List<PCRentalEnterpriseOrderVO> getPCRentalEnterpriseOrderList(RentalEnterpriseOrderDTO dto) {
        return PaginationUtil.paginationQuery(dto,(tempDto) -> rentalEnterpriseOrderDao.getPCRentalEnterpriseOrderList(dto));
    }

    @Override
    public EnterpriseOrderDetailB2BRVO getEnterpriseOrderDetailB2BR(Integer orderId) {
        EnterpriseOrderDetailB2BRVO orderDetail = rentalEnterpriseOrderDao.getEnterpriseOrderDetailB2BR(orderId);
        try {
            orderDetail.setViewCarModelImgUrl(FileUtil.getBrowseFile(orderDetail.getCarModelImgUrl(), orderDetail.getCarModelImgName()));
        } catch (Exception e) {
            logger.error("subject:{},e:{}","订单详情，车辆图片浏览异常",e);
        }
        RentalFeeVoucher record = new RentalFeeVoucher();
        record.setOrderId(orderId);
        List<RentalFeeVoucher> rentalFeeVoucherList = rentalFeeVoucherDao.getRentalFeeVoucherByOrderId(record);
        List<RentalCheckCar> rentalCheckCarList = rentalCheckCarDao.getRentalCheckCar(new RentalCheckCarDTO(orderId));
        rentalFeeVoucherList.forEach(rentalFeeVoucher -> {
            //封装资源方租金到账信息
            if(RentalFeeVoucherEnum.VoucherType.STATUS2.getStatus().intValue() == rentalFeeVoucher.getVoucherType().intValue()){
                //资源方到账租金总额
                orderDetail.setAccountRentTotal(rentalFeeVoucher.getOneMoney());
                //资源方到账押金
                orderDetail.setAccountCashPledge(rentalFeeVoucher.getTwoMoney());
                //资源方到账时间
                orderDetail.setRentAccountDate(DateUtil.formatDate(rentalFeeVoucher.getAccountTime()));
            }
            //封装退还至平台的押金退还信息
            if(RentalFeeVoucherEnum.VoucherType.STATUS3.getStatus().intValue() == rentalFeeVoucher.getVoucherType().intValue()){
                //违章结算金额
                orderDetail.setViolationFee(rentalFeeVoucher.getOneMoney());
                //退还押金
                orderDetail.setReturnCashPledge(rentalFeeVoucher.getTwoMoney());
                orderDetail.setReturnDate(DateUtil.formatDate(rentalFeeVoucher.getPayTime()));
            }
        });
        orderDetail.setCarNumberList(rentalCheckCarList.stream().map(rentalCheckCar -> rentalCheckCar.getCarNumber()).collect(Collectors.toList()));
        return orderDetail;
    }

    @Override
    public PCRentalEnterpriseDetailsBasicVO getPCRentalEnterpriseDetailsBasic(RentalEnterpriseOrderDTO dto) {
        return rentalEnterpriseOrderDao.getPCRentalEnterpriseDetailsBasic(dto);
    }

    @Override
    public PCRentalEnterpriseDetailsDepositVO getPCRentalEnterpriseDetailsDeposit(RentalEnterpriseOrderDTO dto) {
        PCRentalEnterpriseDetailsDepositVO rentalEnterpriseDetailsDepositVO = rentalEnterpriseOrderDao.getPCRentalEnterpriseDetailsDeposit(dto);
        if(rentalEnterpriseDetailsDepositVO == null)return new PCRentalEnterpriseDetailsDepositVO();
        try {
            rentalEnterpriseDetailsDepositVO.setAccountVoucherImgBrowse(FileUtil.getBrowseFile(rentalEnterpriseDetailsDepositVO.getAccountVoucherImgUrl(),rentalEnterpriseDetailsDepositVO.getAccountVoucherImgName()));
            rentalEnterpriseDetailsDepositVO.setPayVoucherImgBrowse(FileUtil.getBrowseFile(rentalEnterpriseDetailsDepositVO.getPayVoucherImgUrl(),rentalEnterpriseDetailsDepositVO.getPayVoucherImgName()));
        } catch (Exception e) {
            logger.error("subject:{},e:{}","凭证地址异常",e);
        }
        return rentalEnterpriseDetailsDepositVO;
    }

    @Override
    public List<PCRentalEnterpriseDetailsFeeVoucherVO> getPCRentalEnterpriseDetailsFeeVoucher(RentalEnterpriseOrderDTO dto) {
        List<PCRentalEnterpriseDetailsFeeVoucherVO> rentalEnterpriseDetailsFeeVoucherVOS = rentalEnterpriseOrderDao.getPCRentalEnterpriseDetailsFeeVoucher(dto);
        rentalEnterpriseDetailsFeeVoucherVOS.forEach(item ->{
            try {
                item.setAccountVoucherImgBrowse(FileUtil.getBrowseFile(item.getAccountVoucherImgUrl(),item.getAccountVoucherImgName()));
                item.setPayVoucherImgBrowse(FileUtil.getBrowseFile(item.getPayVoucherImgUrl(),item.getPayVoucherImgName()));
            } catch (Exception e) {
                logger.error("subject:{},e:{}","凭证地址异常",e);
            }
        });
        return rentalEnterpriseDetailsFeeVoucherVOS;
    }

    @Override
    public Map<String, Object> getHoliday() {
        List<Holiday> holidayList = holidayDao.getAll();
        Map<String, Object> holidayMap = new HashMap<>();
        holidayList.forEach(holiday -> {
            holidayMap.put(DateUtil.formatDate(holiday.getHolidayDate()), holiday);
        });
        RedisUtil.redisHashPut(RedisConstant.RENTAL_HOLIDAY_KEY, holidayMap);
        return holidayMap;
    }

    @Override
    public Result updateAuthorizationStatus(Integer orderId, Integer flowTwoStatus) {
        RentalEnterpriseOrder reo = rentalEnterpriseOrderDao.selectByPrimaryKey(orderId);
        if(reo == null) return Result.failure("订单不存在");

        if(reo.getStatus().intValue() == RentalEnterpriseOrderEnum.OrderStatus.STATUS20.getRealStatus().intValue()
            && reo.getFlowOneStatus().intValue() == RentalEnterpriseOrderEnum.OrderStatus.STATUS20.getFlowOneStatus().intValue()){

            RentalCarPublish carPublish = rentalCarPublishDao.selectByPrimaryKey(reo.getResourceInfoId());
            if(carPublish == null) return Result.failure("订单来源丢失");

            Integer publishNum =
                    reo.getReserveCount() > carPublish.getPublishNum() ? 0:carPublish.getPublishNum() - reo.getReserveCount();
            int i = rentalCarPublishDao.updatePublishNumByVersion(carPublish.getId(),publishNum, carPublish.getVersion());
            if(i == 0){
                carPublish = rentalCarPublishDao.selectByPrimaryKey(reo.getResourceInfoId());
                //判断下单车辆数量是否大于车辆发布剩余数量
                publishNum = reo.getReserveCount() > carPublish.getPublishNum() ? 0:carPublish.getPublishNum() - reo.getReserveCount();

                i = rentalCarPublishDao.updatePublishNumByVersion(carPublish.getId(), publishNum, carPublish.getVersion());
                logger.info("subject:{},publishNum:{},i:{}","保证金授权成功,减车辆数量",publishNum,i);
                if(i == 0) logger.error("授权成功后更新发布信息数量失败，订单ID：{}", reo.getId());
            }
            reo.setFlowTwoStatus(flowTwoStatus);
            rentalEnterpriseOrderDao.updateByPrimaryKeySelective(reo);

            //更新车辆发布明细表
            RentalEnterpriseOrder rentalEnterpriseOrder = rentalEnterpriseOrderDao.selectByPrimaryKey(orderId);
            Integer reserveCount = StringUtil.isEmpty(rentalEnterpriseOrder.getReserveCount()) ? 0 : rentalEnterpriseOrder.getReserveCount();
            RentalCarPublishNum rentalCarPublishNum = new RentalCarPublishNum();
            rentalCarPublishNum.setPublishId(rentalEnterpriseOrder.getResourceInfoId());
            rentalCarPublishNum.setCarLevel(rentalEnterpriseOrder.getCarLevel());
            rentalCarPublishNum.setCarBrand(rentalEnterpriseOrder.getCarBrand());
            rentalCarPublishNum.setCarSeries(rentalEnterpriseOrder.getCarSeries());
            rentalCarPublishNum.setCarModel(rentalEnterpriseOrder.getCarModel());
            List<RentalCarPublishNum> rentalCarPublishNums = rentalCarPublishNumDao.getRentalCarPublishNum(rentalCarPublishNum);
            if(rentalCarPublishNums == null || rentalCarPublishNums.isEmpty())logger.error("车辆发布明细为空，订单ID：{}", reo.getId());
            rentalCarPublishNum = rentalCarPublishNums.get(0);
            Integer carNum = StringUtil.isEmpty(rentalCarPublishNum.getCarNum()) ? 0 : rentalCarPublishNum.getCarNum() ;
            rentalCarPublishNum.setCarNum(reserveCount > carNum ? 0 : (carNum - reserveCount));
            rentalCarPublishNumDao.updateByPrimaryKey(rentalCarPublishNum);
            if(i == 0)logger.error("更新车辆发布明细失败，订单ID：{}", reo.getId());
        }
        return Result.success(reo);
    }

    @Override
    public String getPCRentalEnterpriseOrderId(RentalEnterpriseOrderDTO dto) {
        PCRentalEnterpriseOrderDetailsVO pcRentalEnterpriseOrderDetailsVO = new PCRentalEnterpriseOrderDetailsVO();
        //基本信息
        PCRentalEnterpriseDetailsBasicVO rentalEnterpriseDetailsBasicVO = getPCRentalEnterpriseDetailsBasic(dto);

        RentalCarPublish rentalCarPublish = rentalCarPublishDao.selectByPrimaryKey(rentalEnterpriseDetailsBasicVO.getResourceInfoId());
        //金额公式
        Map<String, Holiday> holidayMap = (Map<String, Holiday>)RedisUtil.redisHashGet(RedisConstant.RENTAL_HOLIDAY_KEY);
        logger.info("subject:{},rentalCarPublish:{},holidayMap:{}","租车门店端获取金额公式",JsonUtil.gsonStr(rentalCarPublish),JsonUtil.gsonStr(holidayMap));
        Map<String, Object> map = RentalFeeUtil.overheadChargeDetailFormula(
                BigDecimal.valueOf(rentalCarPublish.getWorkdayRent()),
                BigDecimal.valueOf(rentalCarPublish.getWeekendRent()),
                BigDecimal.valueOf(rentalCarPublish.getHolidayRent()),
                BigDecimal.valueOf(rentalCarPublish.getDayServiceCharge()),
                rentalEnterpriseDetailsBasicVO.getCashPledge(),
                rentalEnterpriseDetailsBasicVO.getReserveCount(),
                DateUtil.parseDate(DateUtil.formatDateAndTime(rentalEnterpriseDetailsBasicVO.getAppointmentFetchCarTime())),
                DateUtil.parseDate(DateUtil.formatDateAndTime(rentalEnterpriseDetailsBasicVO.getAppointmentRepayCarTime())),
                holidayMap);
        logger.info("subject:{},map:{}","租车门店端获取金额公式",JsonUtil.gsonStr(map));
        rentalEnterpriseDetailsBasicVO.setCarRentalFeeFormat((String)map.get("rentFormula"));
        rentalEnterpriseDetailsBasicVO.setBaseServiceFeeFormat((String)map.get("dayServiceFormula"));
        rentalEnterpriseDetailsBasicVO.setCashPledgeFormat((String)map.get("cashPledgeFormula"));
        rentalEnterpriseDetailsBasicVO.setTotalFee(BigDecimal.valueOf(Double.valueOf(map.get("totalFee").toString())));

        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsBasicVO(rentalEnterpriseDetailsBasicVO);

        //保证金
        PCRentalEnterpriseDetailsDepositVO rentalEnterpriseDetailsDepositVO = getPCRentalEnterpriseDetailsDeposit(dto);
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsDepositVO(rentalEnterpriseDetailsDepositVO);


        //租金及押金信息
        List<PCRentalEnterpriseDetailsFeeVoucherVO> rentalEnterpriseDetailsFeeVoucherVOS = getPCRentalEnterpriseDetailsFeeVoucher(dto);
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsFeeVoucherVOS(rentalEnterpriseDetailsFeeVoucherVOS);


        //出车还车信息
        List<RentalCheckCar> rentalCheckCars = rentalCheckCarService.getRentalCheckCar(new RentalCheckCarDTO(dto.getOrderId()));
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsCarVOList(PCRentalEnterpriseDetailsCarVO.getCarVO(rentalCheckCars));
        //结算信息
        RentalEnterpriseSettlement rentalEnterpriseSettlement = rentalEnterpriseSettlementService.getEnterpriseSettlement(new RentalEnterpriseSettlementDTO(dto.getOrderId()));
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsSettlementVO(PCRentalEnterpriseDetailsSettlementVO.getPCRentalEnterpriseDetailsSettlementVO(rentalEnterpriseSettlement));
        //订单资料
        List<PCRentalEnterpriseDetailsFileVO> rentalEnterpriseDetailsFileVOS = rentalOrderFileService.getRentalEnterpriseOrderFile(dto.getOrderId());
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsFileVOS(rentalEnterpriseDetailsFileVOS);
        logger.info("subject:{},pcRentalEnterpriseOrderDetailsVO:{}","详情数据集",JsonUtil.gsonStr(pcRentalEnterpriseOrderDetailsVO));
        return Result.success(pcRentalEnterpriseOrderDetailsVO).toJSON();
    }

    @Override
    public String getStoreRentalEnterpriseOrderId(RentalEnterpriseOrderDTO dto)  {
        StoreRentalEnterpriseOrderDetailsVO storeRentalEnterpriseOrderDetailsVO = new StoreRentalEnterpriseOrderDetailsVO();
        //基本费用信息
        StoreRentalEnterpriseOrderVO storeRentalEnterpriseOrderVO = getStoreRentalEnterpriseOrderByOrderId(dto);
        RentalCarPublish rentalCarPublish = rentalCarPublishDao.selectByPrimaryKey(storeRentalEnterpriseOrderVO.getResourceInfoId());
        //金额公式
        Map<String, Holiday> holidayMap = (Map<String, Holiday>)RedisUtil.redisHashGet(RedisConstant.RENTAL_HOLIDAY_KEY);
        Map<String, Object> map = RentalFeeUtil.overheadChargeDetailFormula(
                BigDecimal.valueOf(rentalCarPublish.getWorkdayRent()),
                BigDecimal.valueOf(rentalCarPublish.getWeekendRent()),
                BigDecimal.valueOf(rentalCarPublish.getHolidayRent()),
                BigDecimal.valueOf(rentalCarPublish.getDayServiceCharge()),
                storeRentalEnterpriseOrderVO.getCashPledge(),
                storeRentalEnterpriseOrderVO.getReserveCount(),
                DateUtil.parseDate(DateUtil.formatDateAndTime(storeRentalEnterpriseOrderVO.getAppointmentFetchCarTime())),
                DateUtil.parseDate(DateUtil.formatDateAndTime(storeRentalEnterpriseOrderVO.getAppointmentRepayCarTime())),
                holidayMap);
        storeRentalEnterpriseOrderVO.setCarRentalFeeFormat((String)map.get("rentFormula"));
        storeRentalEnterpriseOrderVO.setBaseServiceFeeFormat((String)map.get("dayServiceFormula"));
        storeRentalEnterpriseOrderVO.setCashPledgeFormat((String)map.get("cashPledgeFormula"));
        storeRentalEnterpriseOrderVO.setTotalFee(BigDecimal.valueOf(Double.valueOf(map.get("totalFee").toString())));
        storeRentalEnterpriseOrderDetailsVO.setStoreRentalEnterpriseOrderVO(storeRentalEnterpriseOrderVO);
        //保证金
        RentalDeposit rentalDeposit= depositService.queryRentalDepositByOrderAndType(dto.getOrderId(),RentalDepositEnum.DepositTypeEnum.DEPOSIT_TYPE_1.getDepositType(),RentalDepositEnum.OrderType.STATUS2.getStatus());
        storeRentalEnterpriseOrderDetailsVO.setStoreRentalEnterpriseOrderDepositVO(StoreRentalEnterpriseOrderDepositVO.getStoreRentalEnterpriseOrderDepositVO(rentalDeposit));
        //租金及押金支付信息,押金退还信息
        RentalFeeVoucher record = new RentalFeeVoucher();
        record.setOrderId(dto.getOrderId());
//        record.setVoucherType(RentalFeeVoucherEnum.VoucherType.STATUS1.getStatus());
        List<RentalFeeVoucher> rentalFeeVoucherList = rentalFeeVoucherService.getRentalFeeVoucherByOrderId(record);
        storeRentalEnterpriseOrderDetailsVO.setStoreRentalFeeVoucherVO(StoreRentalFeeVoucherVO.getStoreRentalFeeVoucherVO(rentalFeeVoucherList));
        //结算信息
        RentalEnterpriseSettlement rentalEnterpriseSettlement = rentalEnterpriseSettlementService.getEnterpriseSettlement(new RentalEnterpriseSettlementDTO(dto.getOrderId()));
        storeRentalEnterpriseOrderDetailsVO.setStoreRentalEnterpriseSettlementVO(StoreRentalEnterpriseSettlementVO.getStoreRentalEnterpriseSettlementVO(rentalEnterpriseSettlement));
        logger.info("subject:{},storeRentalEnterpriseOrderDetailsVO:{}","外部订单:查看详情",JsonUtil.gsonStr(storeRentalEnterpriseOrderDetailsVO));
        return Result.success(storeRentalEnterpriseOrderDetailsVO).toJSON();
    }

    @Override
    public Result confirmOrder(RentalEnterpriseOrderDTO dto) {
        RentalEnterpriseOrder reo = selectByPrimaryKey(dto.getOrderId());
        if(reo == null) return Result.failure("订单不存在");

        Result result = RentalEnterpriseOrderEnum.OrderStatus.notarizeRentalEnterpriseOrder(reo.getStatus(),
                reo.getFlowOneStatus(),reo.getFlowTwoStatus(),reo.getFlowThreeStatus());
        if(result.getCode() == ResultConstant.RESULT_CODE_SUCCESS){
            reo.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS20.getFlowOneStatus());
            reo.setUpdateBy(dto.getUserId());
            updateByPrimaryKeySelective(reo);
            return Result.success(reo);
        }else{
            return result;
        }
    }

}
