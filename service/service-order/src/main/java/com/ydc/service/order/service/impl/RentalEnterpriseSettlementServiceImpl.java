package com.ydc.service.order.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.enums.rental.RentalSettlementEnum;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseSettlementDTO;
import com.ydc.dao.cgj.rental.RentalEnterpriseOrderDao;
import com.ydc.dao.cgj.rental.RentalEnterpriseSettlementDao;
import com.ydc.model.cgj.rental.RentalEnterpriseOrder;
import com.ydc.model.cgj.rental.RentalEnterpriseSettlement;
import com.ydc.service.order.service.RentalEnterpriseSettlementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @create 2019-01-05 13:05
 **/
@Service
public class RentalEnterpriseSettlementServiceImpl implements RentalEnterpriseSettlementService {

    private final Logger logger = LogManager.getLogger(RentalEnterpriseSettlementService.class);

    @Autowired
    RentalEnterpriseSettlementDao rentalEnterpriseSettlementDao;
    @Autowired
    RentalEnterpriseOrderDao rentalEnterpriseOrderDao;

    @Override
    public RentalEnterpriseSettlement getEnterpriseSettlement(RentalEnterpriseSettlementDTO dto) {
        RentalEnterpriseSettlement rentalEnterpriseSettlement = rentalEnterpriseSettlementDao.getEnterpriseSettlement(dto);
        if(rentalEnterpriseSettlement == null)return rentalEnterpriseSettlement;
        try {
            rentalEnterpriseSettlement.setVoucherImgBrowse(FileUtil.getBrowseFile(rentalEnterpriseSettlement.getVoucherImgUrl(),rentalEnterpriseSettlement.getVoucherImgName()));
            rentalEnterpriseSettlement.setSettleWayCH(RentalSettlementEnum.getRentalSettlementEnum(rentalEnterpriseSettlement.getSettleWay()));
        } catch (Exception e) {
            logger.error("subject:{},e:{}","结算凭证异常",e);
        }
        return rentalEnterpriseSettlement;
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public void insertEnterpriseSettlement(RentalEnterpriseSettlement rentalEnterpriseSettlement){
        int resultCount;
        resultCount = rentalEnterpriseSettlementDao.insert(rentalEnterpriseSettlement);
        if(resultCount == 0)throw new RuntimeException("新增租车结算信息失败");
        //更新订单状态
        RentalEnterpriseOrder rentalEnterpriseOrder = new RentalEnterpriseOrder();
        rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS80.getRealStatus());
        rentalEnterpriseOrder.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS80.getFlowOneStatus());
        rentalEnterpriseOrder.setFlowTwoStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS80.getFlowTwoStatus());
        rentalEnterpriseOrder.setFlowThreeStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS80.getFlowThreeStatus());
        rentalEnterpriseOrder.setId(rentalEnterpriseSettlement.getOrderId());
        rentalEnterpriseOrder.setUpdateBy(rentalEnterpriseSettlement.getCreateBy());
        resultCount = rentalEnterpriseOrderDao.updateByPrimaryKeySelective(rentalEnterpriseOrder);
        if(resultCount == 0)throw new RuntimeException("更新租车订单失败");
    }
}
