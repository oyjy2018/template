package com.ydc.cgj.rental.web.feignService;

import com.ydc.commom.view.dto.cgj.rental.AddRentalOrderPCDTO;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalAccidentQueryDTO;
import com.ydc.model.cgj.rental.RentalAccident;
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
     * 查询车辆等级
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/getCarLevelGroup")
    String getCarLevelGroup();


    /**
     * 查询车辆品牌
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/getBrandByCarLevel")
    String getBrandByCarLevel(@RequestBody CommCarDTO commCarDTO);


    /**
     * 查询车辆车系
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/getSeriesByBrand")
    String getSeriesByBrand(@RequestBody CommCarDTO commCarDTO);


    /**
     * 查询车辆车型
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/getModelBySeries")
    String getModelBySeries(@RequestBody CommCarDTO commCarDTO);


    /**
     * 新增租车订单
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/insertOrder")
    String insertOrder(@RequestBody AddRentalOrderPCDTO addRentalOrderPCDTO);

    /**
     * 查询门店集合
     *
     * @param
     * @return
     */
    @PostMapping(value = "/rentalOrder/getAllStore")
    String getAllStore();

    /**
     * 获取订单列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderList")
    String getRentalOrderList(@RequestBody Map<String, Object> req);


    /**
     * 会员id查询租车订单
     *
     * @param memberId
     * @return
     */
    @PostMapping(value = "/getRentalOrderByMemberId")
    RentalOrder getRentalOrderByMemberId(@RequestParam("memberId") Integer memberId);

    /**
     * 查询订单详情
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderDetailsPC")
    String getRentalOrderDetailsPC(@RequestBody Map<String, Object> req);

    /**
     * 新增事故信息
     *
     * @param rentalAccident
     * @return
     */
    @PostMapping(value = "/accident/insertRentalAccidentInfo")
    String insertRentalAccidentInfo(RentalAccident rentalAccident);

    /**
     * 根据事故单ID获取事故详情
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/accident/queryRentalAccidentInfoById")
    String queryRentalAccidentInfoById(@RequestParam("id") Integer id);

    /**
     * 获取事故信息列表
     *
     * @param rentalAccidentQueryDTO
     * @return
     */
    @PostMapping(value = "/accident/queryRentalAccidentListInfo")
    String queryRentalAccidentListInfo(RentalAccidentQueryDTO rentalAccidentQueryDTO);

    /**
     * 编辑事故信息
     *
     * @param rentalAccident
     * @return
     */
    @PostMapping(value = "/accident/updateRentalAccidentInfo")
    String updateRentalAccidentInfo(RentalAccident rentalAccident);

    /**
     * 删除事故信息
     *
     * @param rentalAccident
     * @return
     */
    @PostMapping(value = "/accident/deleteRentalAccidentInfo")
    String deleteRentalAccidentInfo(RentalAccident rentalAccident);

    /**
     * 修改订单流程状态
     *
     * @param req
     * @return
     */
    @PostMapping(value = "/rentalOrder/updateRentalOrderFlowStatus")
    String updateRentalOrderFlowStatus(@RequestBody Map<String, Object> req);


    /**
     * 订单id查询详情
     *
     * @param orderId
     * @return
     */
    @PostMapping(value = "/rentalOrder/getRentalOrderByOrderId")
    RentalOrder getRentalOrderByOrderId(@RequestParam("orderId") Integer orderId);

    /**
     * 查询租车订单或机务单信息
     *
     * @param rentalAccident
     * @return
     */
    @PostMapping(value = "/accident/queryRentalOrderOrMaintenance")
    String queryRentalOrderOrMaintenance(RentalAccident rentalAccident);

}
