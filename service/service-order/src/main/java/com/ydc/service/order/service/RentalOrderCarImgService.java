package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.ComeCarOrderImgDTO;
import com.ydc.commom.view.dto.cgj.rental.RepayCarOrderImgDTO;
import com.ydc.model.cgj.rental.RentalOrderCarImg;

import java.util.List;

/**
 * 租车订单图片
 *
 * @author
 * @create 2018-11-22 18:55
 **/
public interface RentalOrderCarImgService {


    /**
     * 批量新增出车租车订单图片
     * @param list
     */
    void insertComeCarImgBatch(List<ComeCarOrderImgDTO> list);


    /**
     * 批量新增出车租车订单图片
     * @param list
     */
    void insertRepayCarImgBatch(List<RepayCarOrderImgDTO> list);

    /**
     * 根据
     * @param orderId
     * @return
     */
    List<RentalOrderCarImg> getRentalOrderCarImgByOrderId(Integer orderId);


    /**
     * 出车图片集合
     * @param orderId
     * @return
     */
    List<ComeCarOrderImgDTO> getComeCarOrderImgData(Integer orderId);


    /**
     * 更新租车图片状态
     * @param orderId
     * @param describeType
     * @return
     */
    Integer updateRentalCarImg(Integer orderId , Integer describeType);
}
