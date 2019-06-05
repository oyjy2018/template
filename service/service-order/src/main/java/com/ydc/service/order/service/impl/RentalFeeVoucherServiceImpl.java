package com.ydc.service.order.service.impl;

import com.ydc.commom.enums.rental.RentalEnterpriseOrderEnum;
import com.ydc.commom.view.dto.cgj.rental.RentalFeeVoucherDTO;
import com.ydc.dao.cgj.rental.RentalEnterpriseOrderDao;
import com.ydc.dao.cgj.rental.RentalFeeVoucherDao;
import com.ydc.model.cgj.rental.RentalEnterpriseOrder;
import com.ydc.model.cgj.rental.RentalFeeVoucher;
import com.ydc.service.order.service.RentalFeeVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 * @create 2019-01-05 18:35
 **/
@Service
public class RentalFeeVoucherServiceImpl implements RentalFeeVoucherService {

    @Autowired
    RentalFeeVoucherDao rentalFeeVoucherDao;
    @Autowired
    RentalEnterpriseOrderDao rentalEnterpriseOrderDao;

    @Override
    public List<RentalFeeVoucher> getRentalFeeVoucherByOrderId(RentalFeeVoucher record) {
        return rentalFeeVoucherDao.getRentalFeeVoucherByOrderId(record);
    }

    @Override
    public int insert(RentalFeeVoucher record) {
        return rentalFeeVoucherDao.insert(record);
    }

    @Transactional(rollbackFor = Exception.class, value = "cgjTransactionManager")
    @Override
    public void batchInsert(List<RentalFeeVoucherDTO> list) {
        rentalFeeVoucherDao.batchInsert(list);
        //更新订单状态
        RentalEnterpriseOrder rentalEnterpriseOrder = new RentalEnterpriseOrder();
        rentalEnterpriseOrder.setStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS110.getRealStatus());
        rentalEnterpriseOrder.setFlowOneStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS110.getFlowOneStatus());
        rentalEnterpriseOrder.setFlowTwoStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS110.getFlowTwoStatus());
        rentalEnterpriseOrder.setFlowThreeStatus(RentalEnterpriseOrderEnum.OrderStatus.STATUS110.getFlowThreeStatus());
        rentalEnterpriseOrder.setId(list.get(0).getOrderId());
        rentalEnterpriseOrder.setUpdateBy(list.get(0).getCreateBy());
        int resultCount;
        resultCount = rentalEnterpriseOrderDao.updateByPrimaryKeySelective(rentalEnterpriseOrder);
        if(resultCount == 0)throw new RuntimeException("更新租车订单失败");
    }
}
