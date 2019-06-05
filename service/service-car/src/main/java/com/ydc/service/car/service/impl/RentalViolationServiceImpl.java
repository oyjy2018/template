package com.ydc.service.car.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.constant.rental.RentalDepositConstant;
import com.ydc.commom.enums.rental.RentalDepositEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.RentalViolationDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalViolationUpdateDealStatusDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalViolationVO;
import com.ydc.dao.cgj.rental.RentalDepositDao;
import com.ydc.dao.cgj.rental.RentalPayWatercourseDao;
import com.ydc.dao.cgj.rental.RentalSettlementDao;
import com.ydc.dao.cgj.rental.RentalViolationDao;
import com.ydc.dao.cgj.user.UserDao;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalPayWatercourse;
import com.ydc.model.cgj.rental.RentalSettlement;
import com.ydc.model.cgj.rental.RentalViolation;
import com.ydc.service.car.service.RentalViolationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("rentalViolationService")
public class RentalViolationServiceImpl implements RentalViolationService {

    @Resource
    private RentalViolationDao rentalViolationDao;

    @Resource
    private RentalDepositDao rentalDepositDao;

    @Resource
    private RentalSettlementDao rentalSettlementDao;

    @Resource
    private RentalPayWatercourseDao rentalPayWatercourseDao;

    @Resource
    private UserDao userDao;

    private Logger logger = LogManager.getLogger(RentalViolationServiceImpl.class);

    @Override
    public List<RentalViolationVO> getRentalViolationList(RentalViolationDTO rentalViolationDTO) {
        return PaginationUtil.paginationQuery(rentalViolationDTO,
                (tempRentalViolationDTO) -> rentalViolationDao.getRentalViolationList(tempRentalViolationDTO));
    }

    @Override
    public RentalViolationVO getRentalViolationById(int id) {
        RentalViolationVO rentalViolationVO = rentalViolationDao.getRentalViolationById(id);
        String fileUrl = rentalViolationVO.getFileUrl();
        String fileName = rentalViolationVO.getFileName();
        if (StringUtil.isNotEmpty(fileUrl) && StringUtil.isNotEmpty(fileName)) {
            try {
                rentalViolationVO.setViewFileUrl(FileUtil.getBrowseFile(fileUrl, fileName));
            } catch (Exception e) {
                logger.info("加密图片路径异常", e);
            }
        }
        String dealChargeFileUrl = rentalViolationVO.getDealChargeFileUrl();
        String dealChargeFileName = rentalViolationVO.getDealChargeFileName();
        if (StringUtil.isNotEmpty(dealChargeFileUrl) && StringUtil.isNotEmpty(dealChargeFileName)) {
            try {
                rentalViolationVO.setViewDealChargeFileUrl(FileUtil.getBrowseFile(dealChargeFileUrl, dealChargeFileName));
            } catch (Exception e) {
                logger.info("加密图片路径异常", e);
            }
        }
        //翻译提交人姓名
        String dealCommitBy = rentalViolationVO.getDealCommitBy();
        if (StringUtil.isNotEmpty(dealCommitBy)) {
            User user = userDao.selectByPrimaryKey(Integer.parseInt(dealCommitBy));
            if (user != null) {
                rentalViolationVO.setDealCommitBy(user.getUserName());
            }
        }
        return rentalViolationVO;
    }

    @Override
    public int insertRentalViolation(RentalViolationVO rentalViolationVO) {
        rentalViolationVO.setStatus(1);//新增添加默认有效状态
        RentalViolation rentalViolation = changeRental(rentalViolationVO);
        return rentalViolationDao.insertSelective(rentalViolation);
    }

    @Override
    public int updateRentalViolation(RentalViolationVO rentalViolationVO) {
        if (rentalViolationVO.getId() == null) {
            throw new ServiceRuntimeException("违章id不能为空");
        }
        RentalViolation rentalViolation = changeRental(rentalViolationVO);
        return rentalViolationDao.updateByPrimaryKeySelective(rentalViolation);
    }

    @Override
    public int updateRentalViolationStatus(int id) {
        return rentalViolationDao.updateRentalViolationStatus(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    public int updateViolationSettlement(Map<String, Object> req) {
        Integer orderId = (Integer) req.get("orderId");
        Integer updateBy = (Integer)req.get("updateBy");
        String updateMan = (String) req.get("updateMan");
        BigDecimal violationPayroll = new BigDecimal((String) req.get("violationPayroll"));//违章代付费用
        BigDecimal violationPreAuthorizationAmount = new BigDecimal((String) req.get("violationPreAuthorizationAmount"));//违章预授权额
        BigDecimal violationAuthRefundAmount = new BigDecimal((String) req.get("violationAuthRefundAmount")); //违章预授权退还金额
        if(violationPreAuthorizationAmount.compareTo(violationPayroll.add(violationAuthRefundAmount))!=0){
            throw new ServiceRuntimeException("违章预授权额 != 违章代付费用+违章预授权退还金额");
        }
        //先通过id获取租赁押金的全部信息
        RentalDeposit rentalDeposit = rentalDepositDao.selectByOrderId(orderId,
                RentalDepositConstant.DEPOSIT_TYPE_2.intValue(), RentalDepositEnum.OrderType.STATUS1.getStatus());
        //通过订单id获取租车结算表详情
        RentalSettlement rentalSettlement = rentalSettlementDao.selectByOrderId(orderId);
        if (rentalDeposit == null ) {
            return 0;
        }
        //支付方式 1：芝麻 2：信用卡 3：现金  如果支付方式不为1 则更新租赁押金表和结算表对应的状态 且新增支付流水表
        if(rentalDeposit.getPaymentMode() != 1){
            rentalDeposit.setPaymentStatus(RentalDepositConstant.PAYMENT_STATUS_3);//已退还
            rentalDeposit.setActualRefundTime(new Date());
            rentalSettlement.setRentalAuthStatus((byte) 10);//已结清
            //新增租赁支付流水表
            insrtRentalPayWatercourse(updateBy, updateMan, violationPayroll, rentalDeposit, rentalSettlement);
        }

        //更新租赁押金表
        rentalDeposit.setPayableRefundAmount(violationAuthRefundAmount);
        rentalDeposit.setActualRefundAmount(violationAuthRefundAmount);
        rentalDeposit.setUpdateBy(updateBy);
        rentalDeposit.setUpdateTime(new Date());
        int count = rentalDepositDao.updateByPrimaryKeySelective(rentalDeposit);
        //更新租车结算表
        updateRentalSettlement(rentalSettlement,orderId, updateBy, violationPayroll, violationAuthRefundAmount);
        return rentalDeposit.getPaymentMode();
    }

    /**
     * 更新租车结算表
     *
     * @param rentalSettlement
     * @param orderId
     * @param updateBy
     * @param violationPayroll
     * @param violationAuthRefundAmount
     * @return
     */
    private RentalSettlement updateRentalSettlement(RentalSettlement rentalSettlement, Integer orderId, Integer updateBy, BigDecimal violationPayroll, BigDecimal violationAuthRefundAmount) {
        rentalSettlement.setViolationAuthRefundAmount(violationAuthRefundAmount);
        rentalSettlement.setViolationPayroll(violationPayroll);
        rentalSettlement.setUpdateBy(updateBy);
        rentalSettlement.setUpdateTime(new Date());
        //更新租车结算表
        rentalSettlementDao.updateByPrimaryKeySelective(rentalSettlement);
        return rentalSettlement;
    }

    /**
     * 新增租赁支付流水表
     * @param updateBy
     * @param updateMan
     * @param violationPayroll
     * @param rentalDeposit
     * @param rentalSettlement
     */
    private void insrtRentalPayWatercourse(Integer updateBy, String updateMan, BigDecimal violationPayroll, RentalDeposit rentalDeposit, RentalSettlement rentalSettlement) {
        //新增租赁支付流水表
        RentalPayWatercourse rentalPayWatercourse = new RentalPayWatercourse();
        rentalPayWatercourse.setMemberId(rentalDeposit.getMemberId());
        rentalPayWatercourse.setOrderId(rentalDeposit.getOrderId());
        rentalPayWatercourse.setSettlementId(rentalSettlement.getId());//--结算id
        rentalPayWatercourse.setOrderNo(rentalDeposit.getViolationOrderNo());//--订单号
        rentalPayWatercourse.setPaymentMode(rentalDeposit.getPaymentMode());
        rentalPayWatercourse.setPayableAmount(violationPayroll);
        rentalPayWatercourse.setActualAmount(violationPayroll);
        rentalPayWatercourse.setDepositType((byte) 4);//违章押金结算
        rentalPayWatercourse.setOperationUserId(updateBy);
        rentalPayWatercourse.setOperationUserName(updateMan);
        rentalPayWatercourse.setOperationTime(new Date());
        rentalPayWatercourse.setDeleteStatus((byte) 1);//有效
        rentalPayWatercourse.setCreateBy(updateBy);
        rentalPayWatercourse.setCreateTime(new Date());
        rentalPayWatercourse.setUpdateBy(updateBy);
        rentalPayWatercourse.setUpdateTime(new Date());
        rentalPayWatercourseDao.insert(rentalPayWatercourse);
    }

    /**
     * 将rentalViolationVO 转为rentalViolation实体类
     *
     * @param rentalViolationVO
     * @return
     */
    private RentalViolation changeRental(RentalViolationVO rentalViolationVO) {
        RentalViolation rentalViolation = new RentalViolation();
        rentalViolation.setId(rentalViolationVO.getId());
        rentalViolation.setCarId(rentalViolationVO.getCarId());
        rentalViolation.setOrderType(rentalViolationVO.getOrderType());
        rentalViolation.setOrderId(rentalViolationVO.getOrderId());
        rentalViolation.setDisposeOrderId(rentalViolationVO.getDisposeOrderId());
        rentalViolation.setViolationTime(DateUtil.parseDateAndTime(rentalViolationVO.getViolationTime()));
        rentalViolation.setViolationPersionName(rentalViolationVO.getViolationPersionName());
        rentalViolation.setViolationPersionIdCard(rentalViolationVO.getViolationPersionIdCard());
        rentalViolation.setViolationType(rentalViolationVO.getViolationType());
        rentalViolation.setViolationPenalty(new BigDecimal(rentalViolationVO.getViolationPenalty()));
        rentalViolation.setViolationScore(rentalViolationVO.getViolationScore());
        rentalViolation.setViolationAtProvinceCode(rentalViolationVO.getViolationAtProvinceCode());
        rentalViolation.setViolationAtProvince(rentalViolationVO.getViolationAtProvince());
        rentalViolation.setViolationAtCityCode(rentalViolationVO.getViolationAtCityCode());
        rentalViolation.setViolationAtCity(rentalViolationVO.getViolationAtCity());
        rentalViolation.setViolationAtDistrictCode(rentalViolationVO.getViolationAtDistrictCode());
        rentalViolation.setViolationAtDistrict(rentalViolationVO.getViolationAtDistrict());
        rentalViolation.setViolationAtAddress(rentalViolationVO.getViolationAtAddress());
        rentalViolation.setStatus(rentalViolationVO.getStatus());
        rentalViolation.setCreateTime(rentalViolationVO.getCreateTime());
        rentalViolation.setCreateBy(rentalViolationVO.getCreateBy());
        rentalViolation.setUpdateBy(rentalViolationVO.getUpdateBy());
        rentalViolation.setUpdateTime(rentalViolationVO.getUpdateTime());
        rentalViolation.setFileName(rentalViolationVO.getFileName());
        rentalViolation.setFileUrl(rentalViolationVO.getFileUrl());
        return rentalViolation;
    }

    /**
     * 更新违章处理状态
     * @param rentalViolationUpdateDealStatusDTO
     * @return
     */
    @Override
    public String updateDealStatus(RentalViolationUpdateDealStatusDTO rentalViolationUpdateDealStatusDTO) {
        int effect = rentalViolationDao.updateDealStatus(rentalViolationUpdateDealStatusDTO);
        if (effect == 0) {
            return Result.failure("更新违章处理状态失败").toJSON();
        }
        return Result.success().toJSON();
    }

}
