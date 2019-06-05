package com.ydc.service.order.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.RentalDepositDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import com.ydc.model.cgj.rental.RentalDeposit;
import com.ydc.model.cgj.rental.RentalFeeVoucher;

import java.util.List;

/**
 * @author
 * @create 2018-11-24 9:23
 **/
public interface RentalDepositService {


    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-24 08:56:06
     */
    int insert(RentalDeposit record);


    /**
     * 租车结算，更新押金状态
     * @param rentalDeposit
     * @return
     */
    int updatePaymentStatus(RentalDeposit rentalDeposit);

    /**
     * 根据属性查询
     * @param record
     * @return
     */
    List<RentalDeposit> selectRentalDeposit(RentalDeposit record);

    /**
     * 已交保证金
     * @param dto
     */
    void paymentDeposit(RentalDepositDTO dto) throws Exception;

    /**
     * 保证金退还
     * @param dto
     */
    void refundDeposit(RentalDepositDTO dto) throws Exception;

    /**
     * 企业租车后台列表:录入租金支付信息
     * @param record
     * @throws Exception
     */
    Result insertRentPayment(RentalFeeVoucher record) throws Exception;

    /**
     * 企业租车后台列表:录入租金转账信息
     * @param record
     * @throws Exception
     */
    void insertRentTransfer(RentalFeeVoucher record) throws Exception;

    /**
     * 解绑支付宝授权
     * @param dto
     * @return
     */
    Result unfreezeAlipay(RentalEnterpriseOrderDTO dto);
}
