package com.ydc.cgj.rentalb.app.feignService;

import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RepayCarOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.UpdateRentalOrderDTO;
import com.ydc.model.cgj.rental.RentalOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

/**
 * @author
 * @create 2018-11-22 12:57
 **/
@Service
@FeignClient(value = "service-order")
public interface OrderFeignService {

    /**
     * 获取出车信息
     * @param commCarDTO
     * @return
     */
    @PostMapping(value = "/rentalOrder/getComeCarData")
    String getComeCarData(@RequestBody CommCarDTO commCarDTO);

    /**
     * 出车更新订单信息
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/updateComeCarOrder")
    String updateComeCarOrder(@RequestBody ComeCarOrderDTO comeCarOrderDTO);

    /**
     * 获取还车信息
     * @param commCarDTO
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRepayCarData")
    String getRepayCarData(@RequestBody CommCarDTO commCarDTO);

    /**
     * 还车更新订单信息
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/updateRepayCarOrder")
    String updateRepayCarOrder(@RequestBody RepayCarOrderDTO repayCarOrderDTO);

    /**
     * 根据门店ID查询订单列表(B端)
     * @param req
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderListBByStoreId")
    String getRentalOrderListBByStoreId(@RequestBody Map<String, Object> req);

    /**
     * 查询订单详情(APP B端)
     * @param req
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderDetailsAPPB")
    String getRentalOrderDetailsAPPB(@RequestBody Map<String, Object> req);

    /**
     * 订单id查询详情
     * @param orderId
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderByOrderId")
    RentalOrder getRentalOrderByOrderId(@RequestParam("orderId") Integer orderId);


    /**
     * 取消用车
     * @param updateRentalOrderDTO
     * @return
     */
    @PostMapping(value = "/rentalOrder/cancelUseCarOrder")
    String cancelUseCarOrder(@RequestBody UpdateRentalOrderDTO updateRentalOrderDTO);

    /**
     * 根据车辆id查询最后一次还车里程
     * @param carId
     * @return
     */
    @PostMapping(value = "/rentalOrder/getCarOilDesc")
    String getCarOilDesc(@RequestParam("carId") Integer carId);
}
