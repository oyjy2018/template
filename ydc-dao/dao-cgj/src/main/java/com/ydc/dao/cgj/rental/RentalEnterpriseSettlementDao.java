package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseSettlementDTO;
import com.ydc.model.cgj.rental.RentalEnterpriseSettlement;

public interface RentalEnterpriseSettlementDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalEnterpriseSettlement record);

    int insertSelective(RentalEnterpriseSettlement record);

    RentalEnterpriseSettlement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalEnterpriseSettlement record);

    int updateByPrimaryKey(RentalEnterpriseSettlement record);

    /**
     * 订单id查询结算信息
     * @param dto
     * @return
     */
    RentalEnterpriseSettlement getEnterpriseSettlement(RentalEnterpriseSettlementDTO dto);
}