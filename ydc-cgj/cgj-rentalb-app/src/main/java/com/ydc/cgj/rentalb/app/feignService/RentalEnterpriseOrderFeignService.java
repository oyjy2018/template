package com.ydc.cgj.rentalb.app.feignService;

import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
     * 查询外部订单列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalOrder/getStoreRentalEnterpriseOrderList")
    String getStoreRentalEnterpriseOrderList(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 外部订单:查看详情
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalOrder/getStoreRentalEnterpriseOrderId")
    String getStoreRentalEnterpriseOrderId(@RequestBody RentalEnterpriseOrderDTO dto);


    /**
     * 外部订单:取消订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalOrder/cancelRentalEnterpriseOrder")
    String cancelRentalEnterpriseOrder(@RequestBody RentalEnterpriseOrderDTO dto);


    /**
     * 外部订单:拒绝订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalOrder/rejectRentalEnterpriseOrder")
    String rejectRentalEnterpriseOrder(@RequestBody RentalEnterpriseOrderDTO dto);


    /**
     * 外部订单:确认订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalOrder/notarizeRentalEnterpriseOrder")
    String notarizeRentalEnterpriseOrder(@RequestBody RentalEnterpriseOrderDTO dto);


    /**
     * 外部订单:订单id查询车辆信息
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalCheckCarByOrderId")
    String getRentalCheckCarByOrderId(@RequestBody RentalCheckCarDTO dto);
}
