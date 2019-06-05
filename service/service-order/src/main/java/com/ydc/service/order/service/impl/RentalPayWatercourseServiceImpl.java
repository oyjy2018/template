package com.ydc.service.order.service.impl;

import com.ydc.commom.constant.rental.RentalOrderStatusConstant;
import com.ydc.commom.constant.rental.RentalPayWatercourseConstant;
import com.ydc.commom.constant.rental.RentalSettlementConstant;
import com.ydc.commom.enums.rental.RentalOrderStatusFourEnum;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalPayWatercourseDTO;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalPayWatercourseVO;
import com.ydc.dao.cgj.rental.RentalPayWatercourseDao;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import com.ydc.model.cgj.rental.RentalSettlement;
import com.ydc.service.order.service.RentalOrderService;
import com.ydc.service.order.service.RentalPayWatercourseService;
import com.ydc.service.order.service.RentalSettlementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author
 * @create 2018-11-26 15:58
 **/
@Service
public class RentalPayWatercourseServiceImpl implements RentalPayWatercourseService {

    private final Logger logger = LogManager.getLogger(RentalPayWatercourseService.class);

    @Resource
    RentalPayWatercourseDao rentalPayWatercourseDao;
    @Autowired
    RentalSettlementService rentalSettlementService;
    @Autowired
    RentalOrderService rentalOrderService;

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public int insert(RentalPayWatercourse record) {
        RentalSettlement rentalSettlement = rentalSettlementService.getRentalSettlementByOrderId(record.getOrderId());
        record.setSettlementId(rentalSettlement.getId());
        //结算表：实际支付总额累加
        BigDecimal actualAmount = record.getActualAmount().add(rentalSettlement.getActualAmount());
        rentalSettlement.setActualAmount(actualAmount);
        UpdateRentalOrderDTO updateRentalOrderDTO = new UpdateRentalOrderDTO();
        logger.info("subject:{},rentalSettlement:{},record:{}","非首次结算",JsonUtil.gsonStr(rentalSettlement),JsonUtil.gsonStr(record));
        if(record.getDepositType().intValue() == RentalPayWatercourseConstant.DEPOSIT_TYPE_3){
            if(rentalSettlement.getShouldChargeTotal().compareTo(actualAmount) == 0
                    && rentalSettlement.getRentalAuthStatus().intValue() != RentalSettlementConstant.RENTAL_AUTH_STATUS_10){
                rentalSettlement.setRentalAuthStatus((byte)RentalSettlementConstant.RENTAL_AUTH_STATUS_10);
                updateRentalOrderDTO.setFlowOneStatus(RentalOrderStatusFourEnum.RentalOrderFlowOneStatusEnum.FLOW_ONE_STATUS_100.getFlowOneStatus());
            }
            updateRentalOrderDTO.setStatus(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_4);
        }else if(record.getDepositType().intValue() == RentalPayWatercourseConstant.DEPOSIT_TYPE_4
                    && rentalSettlement.getViolationAuthStatus().intValue() != RentalSettlementConstant.VIOLATION_AUTH_STATUS_10){
            rentalSettlement.setViolationPayroll(record.getActualAmount());
            rentalSettlement.setViolationAuthStatus((byte)RentalSettlementConstant.VIOLATION_AUTH_STATUS_10);
            updateRentalOrderDTO.setStatus(RentalOrderStatusConstant.RENTAL_ORDER_STATUS_100);
        }
        if(rentalSettlement.getRentalAuthStatus().compareTo((byte)RentalSettlementConstant.RENTAL_AUTH_STATUS_10) == 0
                && rentalSettlement.getViolationAuthStatus().compareTo((byte)RentalSettlementConstant.RENTAL_AUTH_STATUS_10) == 0){
            rentalSettlement.setSettleStatus((byte)RentalSettlementConstant.SETTLE_STATUS_10);
        }
        updateRentalOrderDTO.setOrderId(record.getOrderId());
        updateRentalOrderDTO.setUserId(record.getCreateBy());
        logger.info("subject:{},rentalSettlement:{},record:{}","非首次结算入库",JsonUtil.gsonStr(rentalSettlement),JsonUtil.gsonStr(record));
        rentalOrderService.updateRentalOrderStatus(updateRentalOrderDTO);
        rentalSettlement.setUpdateBy(record.getCreateBy());
        rentalSettlementService.updateByPrimaryKey(rentalSettlement);
        return rentalPayWatercourseDao.insert(record);
    }

    @Override
    public List<RentalPayWatercourseVO> getPayWatercourseByOrderId(RentalPayWatercourseDTO rentalPayWatercourseDTO) {
        return rentalPayWatercourseDao.getPayWatercourseByOrderId(rentalPayWatercourseDTO);
    }

    @Override
    public RentalPayWatercourse getRentalPayWatercourseByOrderNoAndDepositType(String orderNo,Integer depositType) {
        RentalPayWatercourse rentalPayWatercourse=new RentalPayWatercourse();
        rentalPayWatercourse.setOrderNo(orderNo);
        rentalPayWatercourse.setDepositType(depositType.byteValue());
        List<RentalPayWatercourse> rentalPayWatercourses=rentalPayWatercourseDao.selectByRentalPayWatercourse(rentalPayWatercourse);
        if (null ==rentalPayWatercourses || rentalPayWatercourses.isEmpty()){
            return null;
        }
        return rentalPayWatercourses.get(0);
    }

    @Override
    public List<RentalPayWatercourse> getRentalPayWatercourse(String orderNo, String thirdParthOrderNo) {
        RentalPayWatercourse rentalPayWatercourse=new RentalPayWatercourse();
        rentalPayWatercourse.setOrderNo(orderNo);
        rentalPayWatercourse.setThirdParthOrderNo(thirdParthOrderNo);
        List<RentalPayWatercourse> rentalPayWatercourses=rentalPayWatercourseDao.selectByRentalPayWatercourse(rentalPayWatercourse);
        return rentalPayWatercourses;
    }

    @Override
    public List<RentalPayWatercourse> selectByRentalPayWatercourse(RentalPayWatercourse record) {
        return rentalPayWatercourseDao.selectByRentalPayWatercourse(record);
    }
}
