package com.ydc.cgj.rental.web.service;

import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.model.cgj.rental.RentalCar;
import com.ydc.model.cgj.rental.RentalOrderFile;

import java.util.List;

/**
 * @author
 * @create 2019-01-04 16:36
 **/
public interface RentalEnterpriseOrderService {

    /**
     * 企业租车后台列表
     * @param dto
     * @return
     */
    String getPCRentalEnterpriseOrderList(RentalEnterpriseOrderDTO dto);

    /**
     * 企业租车后台列表:车辆车型
     * @param dto
     * @return
     */
    String getCarSeries( CommCarSeriesDTO dto);

    /**
     * 企业租车后台列表:车辆车系
     * @param dto
     * @return
     */
    String getCarModel(CommCarSeriesDTO dto);

    /**
     * 企业租车后台列表:订单详情
     * @param dto
     * @return
     */
    String getPCRentalEnterpriseOrderId(RentalEnterpriseOrderDTO dto);


    /**
     * 企业租车后台列表:上传订单资料
     * @param rentalOrderFile
     * @return
     */
    String insertPCRentalEnterpriseOrderFile(RentalOrderFile rentalOrderFile);

    /**
     * 企业租车后台列表:查询初始化参数
     * @return
     */
    String getPCRentalEnterpriseOrderInit();

    /**
     * 企业租车后台列表:查询订单资料
     * @param rentalOrderFile
     * @return
     */
    String getRentalOrderFiles(RentalOrderFile rentalOrderFile);

    /**
     * 企业租车后台列表:删除订单资料
     * @param rentalOrderFile
     * @return
     */
    String deleteRentalEnterpriseOrderFile(RentalOrderFile rentalOrderFile);


    /**
     * 企业租车后台列表:已交保证金：配置信息
     * @return
     */
    String paymentDepositConfiguration();

    /**
     * 企业租车后台列表:已交保证金
     * @param dto
     * @return
     */
    String paymentDeposit(RentalDepositDTO dto);

    /**
     * 企业租车后台列表:保证金已退还：配置信息
     * @return
     */
    String refundDepositConfiguration();

    /**
     * 企业租车后台列表:保证金已退还
     * @param dto
     * @return
     */
    String refundDeposit(RentalDepositDTO dto);


    /**
     * 企业租车后台列表:录入租金支付信息
     * @return
     */
    String insertRentPayment(RentalFeeVoucherDTO dto);

    /**
     * 企业租车后台列表:录入出车信息
     * @return
     */
    String insertRentalCheckComeCar(List<RentalCheckCarDTO> list);

    /**
     * 企业租车后台列表:录入租金转账信息
     * @return
     */
    String insertRentTransfer(RentalFeeVoucherDTO dto);

    /**
     * 企业租车后台列表:获取还车信息
     * @param dto
     * @return
     */
    String getRentalCheckRepayCar(RentalCheckCarDTO dto);

    /**
     * 企业租车后台列表:录入还车信息
     * @return
     */
    String insertRentalCheckRepayCar(List<RentalCheckCarDTO> list);

    /**
     * 企业租车后台列表:录入结算信息配置信息
     * @return
     */
    String enterpriseSettlementConfiguration();

    /**
     * 企业租车后台列表:录入结算信息
     * @return
     */
    String insertEnterpriseSettlement(RentalEnterpriseSettlementDTO dto);

    /**
     * 企业租车后台列表:录入押金退还信息
     * @return
     */
    String insertDepositRefund(List<RentalFeeVoucherDTO> list);

    /**
     * 手动解绑支付宝
     * @param dto
     * @return
     */
    public String unfreezeAlipay(RentalEnterpriseOrderDTO dto);


    /**
     * 企业租车后台列表:上传资料列表
     * @return
     */
    String getRentalEnterpriseOrderFile(RentalEnterpriseOrderDTO dto);


    /**
     * 企业租车后台列表:获取已发布车辆
     * @param rentalCar
     * @return
     */
    String getRentalCar(RentalCar rentalCar);
}
