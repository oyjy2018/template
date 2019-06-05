package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalFeeVoucherDTO;
import com.ydc.model.cgj.rental.RentalFeeVoucher;

import java.util.List;

public interface RentalFeeVoucherDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalFeeVoucher record);

    int insertSelective(RentalFeeVoucher record);

    RentalFeeVoucher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalFeeVoucher record);

    int updateByPrimaryKey(RentalFeeVoucher record);

    /**
     * 根据企业订单ID获取租车凭证信息
     * @param record
     * @return
     */
    List<RentalFeeVoucher> getRentalFeeVoucherByOrderId(RentalFeeVoucher record);

    /**
     * 录入押金退还信息
     * @param list
     */
    void batchInsert(List<RentalFeeVoucherDTO> list);
}