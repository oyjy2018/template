package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.view.dto.cgj.rental.*;
import com.ydc.model.cgj.rental.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 租车企业订单
 *
 * @author
 * @create 2019-01-04 10:00
 **/
@Service
@FeignClient(value = "service-order")
public interface RentalEnterpriseOrderFeignService {


    /**
     * 企业租车后台列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getPCRentalEnterpriseOrderList")
    String getPCRentalEnterpriseOrderList(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 企业租车后台列表:车辆车型
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getCarSeries")
    String getCarSeries(@RequestBody CommCarSeriesDTO dto);

    /**
     * 企业租车后台列表:车辆车系
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getCarModel")
    String getCarModel(@RequestBody CommCarSeriesDTO dto);

    /**
     * 企业租车后台列表:订单详情
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getPCRentalEnterpriseOrderId")
    String getPCRentalEnterpriseOrderId(@RequestBody RentalEnterpriseOrderDTO dto);


    /**
     * 企业租车后台列表:上传订单资料
     * @param rentalOrderFile
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/insertPCRentalEnterpriseOrderFile")
    String insertPCRentalEnterpriseOrderFile(@RequestBody RentalOrderFile rentalOrderFile);

    /**
     * 企业租车后台列表:查询初始化参数
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getPCRentalEnterpriseOrderInit")
    String getPCRentalEnterpriseOrderInit();


    /**
     * 企业租车后台列表:查询订单资料
     * @param rentalOrderFile
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getRentalOrderFiles")
    String getRentalOrderFiles(@RequestBody RentalOrderFile rentalOrderFile);


    /**
     * 企业租车后台列表:删除订单资料
     * @param rentalOrderFile
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/deleteRentalEnterpriseOrderFile")
    String deleteRentalEnterpriseOrderFile(@RequestBody RentalOrderFile rentalOrderFile);

    /**
     * 企业租车后台列表:已交保证金
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/paymentDeposit")
    String paymentDeposit(@RequestBody RentalDepositDTO dto);

    /**
     * 企业租车后台列表:保证金已退还
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/refundDeposit")
    String refundDeposit(@RequestBody RentalDepositDTO dto);

    /**
     * 企业租车后台列表:录入租金支付信息
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/insertRentPayment")
    String insertRentPayment(@RequestBody RentalFeeVoucher record);


    /**
     * 企业租车后台列表:录入出车信息
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/insertRentalCheckComeCar")
    String insertRentalCheckComeCar(@RequestBody List<RentalCheckCarDTO> list);

    /**
     * 企业租车后台列表:录入租金转账信息
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/insertRentTransfer")
    String insertRentTransfer(@RequestBody RentalFeeVoucher record);

    /**
     * 企业租车后台列表:获取还车信息
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getRentalCheckRepayCar")
    String getRentalCheckRepayCar(@RequestBody RentalCheckCarDTO dto);

    /**
     * 企业租车后台列表:录入还车信息
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/insertRentalCheckRepayCar")
    String insertRentalCheckRepayCar(@RequestBody List<RentalCheckCarDTO> list);

    /**
     * 企业租车后台列表:录入结算信息
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/insertEnterpriseSettlement")
    String insertEnterpriseSettlement(@RequestBody RentalEnterpriseSettlement rentalEnterpriseSettlement);


    /**
     * 企业租车后台列表:录入押金退还信息
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/insertDepositRefund")
    String insertDepositRefund(@RequestBody List<RentalFeeVoucherDTO> list);

    /**
     * 手动解绑支付宝
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/unfreezeAlipay")
    String unfreezeAlipay(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 企业租车后台列表:上传资料列表
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getRentalEnterpriseOrderFile")
    String getRentalEnterpriseOrderFile(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 企业租车后台列表:获取已发布车辆
     * @param rentalCar
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getRentalCar")
    String getRentalCar(@RequestBody RentalCar rentalCar);

}
