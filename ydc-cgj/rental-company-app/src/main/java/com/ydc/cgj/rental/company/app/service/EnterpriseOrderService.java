package com.ydc.cgj.rental.company.app.service;

import com.ydc.commom.view.dto.cgj.rental.AddRentalEnterpriseOrderDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarSeriesDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarPublishDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface EnterpriseOrderService {
    /**
     * 新增企业租车订单
     * @param dto
     * @return
     */
    public String addRentalOrder(AddRentalEnterpriseOrderDTO dto);

    /**
     * 取消订单
     * @param dto
     * @return
     */
    public String cancelOrder(RentalEnterpriseOrderDTO dto);

    /**
     * 拒绝订单
     * @param dto
     * @return
     */
    public String refuseOrder(RentalEnterpriseOrderDTO dto);

    /**
     * 查询需求方订单列表
     * @param dto
     * @return
     */
    public String getEnterpriseOrderListB2BD(RentalEnterpriseOrderDTO dto);

    /**
     * 查询资源方订单列表
     * @param dto
     * @return
     */
    public String getEnterpriseOrderListB2BR(RentalEnterpriseOrderDTO dto);

    /**
     * 查询车商端租车订单详情
     * @param dto
     * @return
     */
    public String getEnterpriseOrderDetailB2BD(RentalEnterpriseOrderDTO dto);

    /**
     * 车商端出租订单详情
     * @param dto
     * @return
     */
    public String getEnterpriseOrderDetailB2BR(RentalEnterpriseOrderDTO dto);

    /**
     * 获取节假日配置
     * @return
     */
    public String getHoliday();

    /**
     * 确认订单（资源方）
     * @param dto
     * @return
     */
    @PostMapping(value = "/confirmOrder")
    public String confirmOrder(RentalEnterpriseOrderDTO dto);

    /**
     * 获取发布信息
     * @param dto
     * @return
     */
    public String getPublishInfo(RentalCarPublishDTO dto);

}
