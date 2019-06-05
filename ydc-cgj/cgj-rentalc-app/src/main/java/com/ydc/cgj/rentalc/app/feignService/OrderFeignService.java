package com.ydc.cgj.rentalc.app.feignService;

import com.ydc.commom.result.Result;
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
 * @create 2018-11-22 9:38
 **/
@Service
@FeignClient(value = "service-order")
public interface OrderFeignService {

    /**
     * 根据会员ID查询订单列表(C端)
     * @param req
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderListCByMemberId")
    String getRentalOrderListCByMemberId(@RequestBody Map<String, Object> req);

    /**
     * 查询订单详情(APP C端)
     * @param req
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderDetailsAPPC")
     String getRentalOrderDetailsAPPC(@RequestBody Map<String, Object> req);

    /**
     * 更新订单预授权
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/consent/update")
    Result updateConsentAuthorization(@RequestParam(value = "memberId") Integer memberId,
                                      @RequestParam(value = "orderId") Integer orderId,
                                      @RequestParam(value = "status") String status);

    /**
     * 取消用车
     * @param updateRentalOrderDTO
     * @return
     */
    @PostMapping(value = "/rentalOrder/cancelUseCarOrder")
    String cancelUseCarOrder(@RequestBody UpdateRentalOrderDTO updateRentalOrderDTO);

    /**
     * 订单id查询详情
     * @param orderId
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderByOrderId")
    RentalOrder getRentalOrderByOrderId(@RequestParam("orderId") Integer orderId);
}
