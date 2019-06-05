package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseSettlementDTO;
import com.ydc.model.cgj.rental.RentalEnterpriseSettlement;

/**
 * @author
 * @create 2019-01-05 13:05
 **/
public interface RentalEnterpriseSettlementService {

    /**
     * 订单id查询结算信息
     * @param dto
     * @return
     */
    RentalEnterpriseSettlement getEnterpriseSettlement(RentalEnterpriseSettlementDTO dto);

    /**
     * 录入结算信息
     */
    void insertEnterpriseSettlement(RentalEnterpriseSettlement rentalEnterpriseSettlement) throws Exception;
}
