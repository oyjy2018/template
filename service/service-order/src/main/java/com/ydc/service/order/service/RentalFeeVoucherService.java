package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.RentalFeeVoucherDTO;
import com.ydc.model.cgj.rental.RentalFeeVoucher;

import java.util.List;

/**
 * @author
 * @create 2019-01-05 18:34
 **/
public interface RentalFeeVoucherService {

    /**
     * 根据企业订单ID获取租车凭证信息
     * @param record
     * @return
     */
    List<RentalFeeVoucher> getRentalFeeVoucherByOrderId(RentalFeeVoucher record);

    /**
     * 保存
     * @param record
     * @return
     */
    int insert(RentalFeeVoucher record);

    /**
     * 录入押金退还信息
     * @param list
     */
    void batchInsert(List<RentalFeeVoucherDTO> list) throws Exception;
}
