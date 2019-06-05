package com.ydc.service.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.config.OrderNoConfig;
import com.ydc.beans.commom.OrderNoUtil;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.cgj.MemberAppointmentConstant;
import com.ydc.commom.constant.RollConstant;
import com.ydc.commom.enums.cgj.BOrderStatusEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.appointment.AppointmentCouponDTO;
import com.ydc.commom.view.dto.cgj.appointment.MemberAppointmentDTO;
import com.ydc.commom.view.dto.cgj.appointment.MemberAppointmentDetailDTO;
import com.ydc.commom.view.dto.cgj.appointment.ServiceReservationDTO;
import com.ydc.commom.view.dto.cgj.appointment.BOrderCouponResponseDTO;
import com.ydc.commom.view.dto.cgj.appointment.BOrderResponseDTO;
import com.ydc.commom.view.vo.cgj.ServiceReservationVO;
import com.ydc.commom.view.vo.cgj.order.CreateAppointmentParamVO;
import com.ydc.dao.cgj.service.BCarServiceShopDao;
import com.ydc.dao.cgj.user.*;
import com.ydc.model.cgj.*;
import com.ydc.service.order.mq.service.SendMessageService;
import com.ydc.service.order.service.AppointmentOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentOrderServiceImpl implements AppointmentOrderService {
    private final Logger logger = LogManager.getLogger(AppointmentOrderServiceImpl.class);
    @Resource
    private MemberRollDao memberRollDao;

    @Resource
    private BCarServiceShopDao bCarServiceShopDao;

    @Resource
    private MemberDao memberDao;

    @Resource
    private MemberAppointmentRollDao memberAppointmentRollDao;

    @Resource
    private MemberAppointmentDao memberAppointmentDao;

    @Resource
    private OrderNoConfig appointOrderNoConfig;

    @Autowired
    SendMessageService sendMessageService;

    @Resource
    private VehicleDao vehicleDao;

    @Override
    public Result getMemberAppointment(Integer memberId, Integer appointStatus, Pagination pagination) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("memberId", memberId);
        paramMap.put("appointStatus", appointStatus);
        pagination = pagination.changePage();
        paramMap.put("page", pagination.getPage());
        paramMap.put("rows", pagination.getRows());

        List<MemberAppointmentDTO> memberAppointmentDTOList = memberAppointmentDao.selectMemberAppointByStatus(paramMap);
      /*  List<MemberAppointmentDTO> newMemberAppointmentDTOList=new ArrayList<>();
        if(memberAppointmentDTOList != null || memberAppointmentDTOList.size()>0){
            for (MemberAppointmentDTO memberAppointmentDTO:memberAppointmentDTOList){
                MemberAppointmentDTO memberAppointmentDTOTemp=memberAppointmentDTO;
//                memberAppointmentDTOTemp.setStoreLogo(StringUtil.isEmpty(memberAppointmentDTO.getStoreLogo()) ? "" : ServiceUtil.getBPhotoServiceUrl()+""+memberAppointmentDTO.getStoreLogo());
                newMemberAppointmentDTOList.add(memberAppointmentDTOTemp);
            }
        }*/

        return Result.success(memberAppointmentDTOList);
    }

    //查询预约订单详细信息
    @Override
    public Result getMemberAppointmentDetail(Integer memberId, String orderNo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("memberId", memberId);
        paramMap.put("orderNo", orderNo);
        MemberAppointmentDetailDTO memberAppointmentDetailDTO = memberAppointmentDao.selectMemberAppointDetail(paramMap);
        if (null != memberAppointmentDetailDTO) {
//            memberAppointmentDetailDTO.setStoreLogo(StringUtil.isEmpty(memberAppointmentDetailDTO.getStoreLogo()) ? "" : ServiceUtil.getBPhotoServiceUrl()+""+memberAppointmentDetailDTO.getStoreLogo());

            paramMap.put("appointId", memberAppointmentDetailDTO.getOrderId());
            List<AppointmentCouponDTO> appointmentCouponDTOList = memberAppointmentRollDao.selectMemberAppointCouponByOrderId(paramMap);
            memberAppointmentDetailDTO.setAppointmentCouponDTOList(appointmentCouponDTOList);
        }
        return Result.success(memberAppointmentDetailDTO);
    }

    //取消用户预约订单
    @Override
    @Transactional(value = "cgjTransactionManager")
    public Result cancelMemberAppointment(Integer memberId, String orderNo, String reason) {
        MemberAppointment memberAppointment = memberAppointmentDao.selectByOrderNo(orderNo);
        if (null == memberAppointment) {
            return Result.failure("预约订单不存在");
        }
        if (memberAppointment.getMemberId().compareTo(memberId) != 0) {
            return Result.failure("无权操作");
        }
        if (memberAppointment.getAppointStatus().intValue() != MemberAppointmentConstant.APPOINT_STATUS_APPOINTMENT) {
            return Result.failure("暂时不能操作");
        }
        if (memberAppointment.getProcessStatus().intValue() == MemberAppointmentConstant.PROCESS_STATUS_PROCESSING) {
            return Result.failure("处理中....");
        }
        Date date = new Date();
        memberAppointment.setProcessStatus(MemberAppointmentConstant.PROCESS_STATUS_PROCESSING);
        memberAppointment.setReason(reason);
        memberAppointment.setUpdateBy(memberId);
        memberAppointment.setUpdateTime(date);
        memberAppointment.setCloseTime(date);
        if (memberAppointmentDao.updateByPrimaryKey(memberAppointment) != 1) {
            throw new ServiceRuntimeException("取消失败，刷新后重试");
        }
      /*  List<MemberAppointmentRoll> memberAppointmentRolls = memberAppointmentRollDao.selectByAppointId(memberAppointment.getId());
        if (null == memberAppointmentRolls || memberAppointmentRolls.isEmpty()) {
            throw new ServiceRuntimeException("无效预约");
        }
        memberAppointmentRolls.forEach(memberAppointmentRoll -> {
            cancelMemberRollStatus(memberAppointmentRoll, memberId, date);
        });*/
       /* for (MemberAppointmentRoll memberAppointmentRoll:memberAppointmentRolls){
            cancelMemberRollStatus( memberAppointmentRoll, memberId, date);
        }*/

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("memberId",memberId);
        jsonObject.put("orderNo",orderNo);
        jsonObject.put("bOrderNo",memberAppointment.getbOrderNo());
        jsonObject.put("orderStatus",MemberAppointmentConstant.B_APPOINT_STATUS_CANCEL_APPOINTMENT);
        jsonObject.put("updTime",DateUtil.getSecondTimeStamp(new Date()));
        jsonObject.put("url", SystemPropertiesConfig.B_SERVICE_URL +"store/editOrder");

        //给B端发送消息
        sendMessageService.sendCancelMemberAppointmentMessage(jsonObject.toString());
        return Result.success("更新成功");
    }

    @Override
    public Result queryCouponCode(Integer memberId, String orderNo) {
        MemberAppointment memberAppointment = memberAppointmentDao.selectByOrderNo(orderNo);
        try {
            if (null==memberAppointment
                    || memberAppointment.getMemberId().compareTo(memberId)!=0
                    || MemberAppointmentConstant.APPOINT_STATUS_WAIT_SERVICE != memberAppointment.getAppointStatus().intValue()){
                return   Result.failure("无权查看");
            }
        }catch (Exception e){
            logger.error("查看券码异常 memberId:{} ,order:{} ",memberId,orderNo,e);
            return   Result.failure("无权查看");
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appointId", memberAppointment.getId());
        List<AppointmentCouponDTO> appointmentCouponDTOList = memberAppointmentRollDao.selectMemberAppointCouponByOrderId(paramMap);
        return Result.success(appointmentCouponDTOList);
    }

    @Override
    public List<ServiceReservationVO> getServiceReservationList(ServiceReservationDTO serviceReservationDTO) {
        return PaginationUtil.paginationQuery(serviceReservationDTO,(tempServiceReservationDTO) -> memberAppointmentDao.getServiceReservationList(serviceReservationDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 6, value = "cgjTransactionManager")
    public MemberAppointment addMemberAppointment(CreateAppointmentParamVO createAppointmentParamVO, Integer memberId) throws Exception {
        List<String> rollCodeList = createAppointmentParamVO.getRollCodes();
        //校验
        List<MemberRoll> memberRollList = memberRollDao.getRollByCodeAndStatus(rollCodeList, memberId, RollConstant.RollStatusEnum.ROLL_STATUS_0.getKey());
        if (memberRollList == null || memberRollList.size() < rollCodeList.size()) {
            throw new ServiceRuntimeException("优惠券已失效，请重新选择");
        }
        BCarServiceShop bCarServiceShop = bCarServiceShopDao.selectByIdOrCode(createAppointmentParamVO.getStoreId(), createAppointmentParamVO.getStoreNo());

        if (bCarServiceShop == null) {
            throw new ServiceRuntimeException("门店不存在");
        }

        //更新券的状态
        rollCodeList.forEach(rollCode -> memberRollDao.updateRollStatusByStatus(rollCode,
                createAppointmentParamVO.getRollUsedTime() == null ? new Date() : createAppointmentParamVO.getRollUsedTime(),
                createAppointmentParamVO.getRollStatus() == null ? RollConstant.RollStatusEnum.ROLL_STATUS_1.getKey() : createAppointmentParamVO.getRollStatus()));
        //添加预约记录
        MemberAppointment memberAppointment = insertMemberAppointment(memberId, memberRollList, bCarServiceShop, createAppointmentParamVO);
        //添加预约和券的记录
        List<Integer> memberRollIds = memberRollList.stream().map(MemberRoll::getId).collect(Collectors.toList());
        insertAppointmentRoll(memberAppointment.getId(), memberRollIds, getAppointmentRollStatus(createAppointmentParamVO.getOrderStatus()));
        //更新会员首次预约时间
         memberDao.updateFirstAppointmentDate(memberId, new Date());
        logger.info("创建订单成功 订单号{}",createAppointmentParamVO.getbOrderNo());
        return memberAppointment;
    }

    @Override
  //  @Transactional(rollbackFor = Exception.class, timeout = 6, value = "cgjTransactionManager")
    public MemberAppointment addMemberAppointment(CreateAppointmentParamVO createAppointmentParamVO) throws Exception {
        Member member = memberDao.getMemberByMobilePhone(createAppointmentParamVO.getTelno());
        if (member == null){
            throw new ServiceRuntimeException("用户不存在");
        }
        return this.addMemberAppointment(createAppointmentParamVO, member.getId());
    }

    private MemberAppointment insertMemberAppointment(Integer memberId, List<MemberRoll> memberRollList, BCarServiceShop bCarServiceShop, CreateAppointmentParamVO createAppointmentParamVO) {
        Member member = memberDao.selectByPrimaryKey(memberId);
        Vehicle vehicle = vehicleDao.getVehicleByMemberId(memberId);

        //预约订单项目
        String items = memberRollList.stream()
                .map(memberRoll -> RollConstant.RollTypeTitleEnum.getTitleByType(memberRoll.getRollType()))
                .collect(Collectors.joining("&"));
        //预约订单金额
        BigDecimal appointMoney = memberRollList.stream()
                .map(MemberRoll::getRollAmount)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);

        MemberAppointment memberAppointment = new MemberAppointment();
        memberAppointment.setMemberId(memberId);
        memberAppointment.setMemberName(member.getMemberName());
        memberAppointment.setItems(items);
        memberAppointment.setAppointAmount(appointMoney);
        memberAppointment.setType(createAppointmentParamVO.getUseType() == null ? RollConstant.APPOINT_TYPE_1 : createAppointmentParamVO.getUseType());
        memberAppointment.setOrderNo(OrderNoUtil.getOrderNo(appointOrderNoConfig));
        memberAppointment.setAppointStatus(createAppointmentParamVO.getOrderStatus() == null ?
                RollConstant.AppointStatusEnum.APPOINT_STATUS_ENUM_1.getKey() : createAppointmentParamVO.getOrderStatus());
        memberAppointment.setProcessStatus(MemberAppointmentConstant.PROCESS_STATUS_SUCCESS);
        memberAppointment.setPlateNumbers(vehicle == null ? null : vehicle.getCarPlate());
        memberAppointment.setStoreId(bCarServiceShop.getId());
        memberAppointment.setStoreName(bCarServiceShop.getStoreName());
        memberAppointment.setStoreCode(bCarServiceShop.getStoreCode());
        memberAppointment.setStorePhone(bCarServiceShop.getServicePhone());
        memberAppointment.setStoreAddress((bCarServiceShop.getStoreRegisterCity() == null ? "" : bCarServiceShop.getStoreRegisterCity()) +
                (bCarServiceShop.getStoreRegisterRegion() == null ? "" : bCarServiceShop.getStoreRegisterRegion()) +
                (bCarServiceShop.getDetailsAddress() == null ? "" : bCarServiceShop.getDetailsAddress()));
        memberAppointment.setAppointTime(DateUtil.parseDateAndTime(createAppointmentParamVO.getAppointTime()));
        memberAppointment.setbAppointStatus(createAppointmentParamVO.getbOrderStatus());
        memberAppointment.setbOrderNo(createAppointmentParamVO.getbOrderNo());
        memberAppointment.setStatus(CodeConstant.NORMAL_STATUS);
        memberAppointment.setCreateTime(new Date());
        memberAppointment.setUpdateTime(new Date());
        memberAppointmentDao.insertSelective(memberAppointment);
        return memberAppointment;
    }

    private void cancelMemberRollStatus(MemberAppointmentRoll memberAppointmentRoll, Integer memberId, Date date) {
        memberAppointmentRoll.setRollStatus(MemberAppointmentConstant.ROLL_STATUS_SEND_BACK);
        memberAppointmentRoll.setUpdateBy(memberId);
        memberAppointmentRoll.setUpdateTime(date);
        memberAppointmentRollDao.updateByPrimaryKey(memberAppointmentRoll);
        MemberRoll memberRoll = memberRollDao.selectByPrimaryKey(memberAppointmentRoll.getRollId());
        memberRoll.setRollStatus(MemberAppointmentConstant.ROLL_STATUS_UNCONFIRMED);
        //判断用户券是否过了失效时间
        if (date.compareTo(memberRoll.getInvalidTime()) > -1) {
            memberRoll.setRollStatus(MemberAppointmentConstant.ROLL_STATUS_OVERDUE);
        }
        memberRoll.setUpdateBy(memberId);
        memberRoll.setUpdateTime(date);
        memberRollDao.updateByPrimaryKey(memberRoll);
    }

    private void insertAppointmentRoll(Integer memberAppointmentId, List<Integer> memberRollIds, Integer rollStatus) {
        List<MemberAppointmentRoll> list = new ArrayList<>(memberRollIds.size());
        memberRollIds.forEach(memberRollId -> {
            MemberAppointmentRoll memberAppointmentRoll = new MemberAppointmentRoll();
            memberAppointmentRoll.setAppointId(memberAppointmentId);
            memberAppointmentRoll.setRollId(memberRollId);
            memberAppointmentRoll.setRollStatus(rollStatus);
            memberAppointmentRoll.setStatus(CodeConstant.NORMAL_STATUS);
            memberAppointmentRoll.setCreateTime(new Date());
            memberAppointmentRoll.setUpdateTime(new Date());
            list.add(memberAppointmentRoll);
        });
        memberAppointmentRollDao.batchInsert(list);
    }

    @Override
    public Integer updateAppointBInfo(MemberAppointment memberAppointment) {
        return memberAppointmentDao.updateAppointBInfo(memberAppointment);
    }

    @Override
    @Transactional(value = "cgjTransactionManager")
    public Integer updateAppointStatusByBAppointStatus(List<BOrderResponseDTO> bOrderResponseDTOS, Integer memberId) {
        logger.info("根据B端订单状态，更新本地订单状态");
        Date date=new Date();
        for (BOrderResponseDTO bOrderResponseDTO:bOrderResponseDTOS){
            //查询订单状态
            MemberAppointment memberAppointment= memberAppointmentDao.selectByBOrderNo(bOrderResponseDTO.getOrderNo());
            if (null == memberAppointment){
                logger.info("订单不存在 订单号{}",bOrderResponseDTO.getOrderNo());
                throw  new ServiceRuntimeException("订单不存在");
            }
            memberAppointment.setbAppointStatus(bOrderResponseDTO.getOrderStatus());//设置B端状态
            memberAppointment.setProcessStatus(MemberAppointmentConstant.PROCESS_STATUS_SUCCESS);
            memberAppointment.setAppointStatus(BOrderStatusEnum.getValueByKey(bOrderResponseDTO.getOrderStatus()));//设置C端状态
            if (StringUtil.isNotEmpty(bOrderResponseDTO.getUpdTime())){
                try {
                    Date updateTime=DateUtil.timeStamp2Date(bOrderResponseDTO.getUpdTime());
                    if (bOrderResponseDTO.getOrderStatus().compareTo(MemberAppointmentConstant.B_APPOINT_STATUS_SUCCESS_CONSUMPTION)==0){
                        //服务服务
                        memberAppointment.setServiceTime(updateTime);
                    }else if (bOrderResponseDTO.getOrderStatus().compareTo(MemberAppointmentConstant.B_APPOINT_STATUS_STORE_ACCEPT)==0){
                        //确认时间
                        memberAppointment.setConfirmTime(updateTime);
                    }else if (memberAppointment.getAppointStatus().compareTo(MemberAppointmentConstant.APPOINT_STATUS_CLOSE)==0){
                        //关闭时间
                        memberAppointment.setCloseTime(updateTime);
                        memberAppointment.setbReason(bOrderResponseDTO.getMsg());
                        memberAppointment.setReason(bOrderResponseDTO.getMsg());
                    }
                }catch (Exception e){
                    logger.error("时间转换错误{}",bOrderResponseDTO.getUpdTime(),e);
                }
            }
            memberAppointment.setUpdateBy(memberId);
            memberAppointment.setUpdateTime(date);
            memberAppointmentDao.updateByPrimaryKey(memberAppointment);
            List<BOrderCouponResponseDTO> bOrderCouponResponseDTOS=bOrderResponseDTO.getCouponStatuss();
            for (BOrderCouponResponseDTO bOrderCouponResponseDTO:bOrderCouponResponseDTOS){
                Map<String,Object> param=new HashMap<>();
                param.put("appoint_id",memberAppointment.getId());
                param.put("roll_code",bOrderCouponResponseDTO.getCouponNo());
                //查询券
                 List<Map<String,Object>> memberRollMapList= memberAppointmentRollDao.selectMapByAppointIdAndCouponNo(param);
                 if (memberRollMapList == null || memberRollMapList.isEmpty()){
                    logger.info("查询券为空，appoint_id：{} roll_code：{}",memberAppointment.getId(),bOrderCouponResponseDTO.getCouponNo());
                 }
                 Map<String,Object> memberRollMap=memberRollMapList.get(0);
                 MemberRoll memberRoll=new MemberRoll(memberRollMap);
                 MemberAppointmentRoll memberAppointmentRoll=new MemberAppointmentRoll(memberRollMap);

                 memberRoll.setRollStatus(bOrderCouponResponseDTO.getStatus());
                 //判断券是否过期
                 if (bOrderCouponResponseDTO.getStatus().compareTo(MemberAppointmentConstant.ROLL_STATUS_UNCONFIRMED)==0){
                     //券过期
                     if (memberRoll.getInvalidTime().compareTo(date)<0){
                         memberRoll.setRollStatus(MemberAppointmentConstant.ROLL_STATUS_OVERDUE);
                     }
                     memberAppointmentRoll.setRollStatus(MemberAppointmentConstant.ROLL_STATUS_SEND_BACK);
                 }else {
                     memberAppointmentRoll.setRollStatus(bOrderCouponResponseDTO.getStatus());
                 }
                 memberRollDao.updateByPrimaryKeySelective(memberRoll);
                 memberAppointmentRollDao.updateByPrimaryKeySelective(memberAppointmentRoll);
                 }

            }

        return null;
    }

    //获取订单券对应的状态
    private Integer getAppointmentRollStatus(Integer appointmentStatus){
        if (appointmentStatus == null){
            return RollConstant.AppointRollStatusEnum.APPOINT_ROLL_STATUS_ENUM_0.getKey();
        }
        if (appointmentStatus.equals(RollConstant.AppointStatusEnum.APPOINT_STATUS_ENUM_3.getKey())){
            return RollConstant.AppointRollStatusEnum.APPOINT_ROLL_STATUS_ENUM_2.getKey();
        }
        return RollConstant.AppointRollStatusEnum.APPOINT_ROLL_STATUS_ENUM_0.getKey();
    }
}
