package com.ydc.cgj.rental.company.app.feignService;

import com.ydc.commom.view.dto.cgj.rental.AddRentalEnterpriseOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarSeriesDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarPublishDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "service-order")
public interface EnterpriseOrderFeignService {

    /**
     * 新增企业租车订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/addRentalOrder")
    public String addRentalOrder(@RequestBody AddRentalEnterpriseOrderDTO dto);

    /**
     * 取消订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/cancelOrder")
    public String cancelOrder(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 拒绝订单
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/refuseOrder")
    public String refuseOrder(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 查询需求方订单列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getEnterpriseOrderListB2BD")
    public String getEnterpriseOrderListB2BD(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 查询资源方订单列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getEnterpriseOrderListB2BR")
    public String getEnterpriseOrderListB2BR(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 查询车商端租车订单详情
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getEnterpriseOrderDetailB2BD")
    public String getEnterpriseOrderDetailB2BD(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 车商端出租订单详情
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getEnterpriseOrderDetailB2BR")
    public String getEnterpriseOrderDetailB2BR(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 获取节假日配置
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getHoliday")
    public String getHoliday();

    /**
     * 确认订单（资源方）
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/confirmOrder")
    public String confirmOrder(@RequestBody RentalEnterpriseOrderDTO dto);

    /**
     * 获取发布信息
     * @param dto
     * @return
     */
    @PostMapping(value = "/rentalEnterpriseOrder/getPublishInfo")
    public String getPublishInfo(@RequestBody RentalCarPublishDTO dto);

}
