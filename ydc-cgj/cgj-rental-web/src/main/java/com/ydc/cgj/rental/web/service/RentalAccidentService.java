package com.ydc.cgj.rental.web.service;

import com.ydc.commom.view.dto.cgj.rental.RentalAccidentQueryDTO;
import com.ydc.model.cgj.rental.RentalAccident;

/**
 * 事故服务
 * @author  lshuang
 */
public interface RentalAccidentService {

    /**
     * 新增事故信息
     * @param rentalAccident
     * @return
     */
    String insertRentalAccidentInfo(RentalAccident rentalAccident);

    /**
     * 查询事故单详情by id
     * @param id
     * @return
     */
    String queryRentalAccidentInfoById(Integer id);

    /**
     * 获取事故信息列表
     * @param rentalAccidentQueryDTO
     * @return
     */
    String queryRentalAccidentListInfo(RentalAccidentQueryDTO rentalAccidentQueryDTO);

    /**
     * 编辑事故信息
     * @param rentalAccident
     * @return
     */
    String  updateRentalAccidentInfo(RentalAccident rentalAccident);

    /**
     * 删除事故信息
     * @param rentalAccident
     * @return
     */
    String deleteRentalAccidentInfo(RentalAccident rentalAccident);

    /**
     * 查询租车订单或者机务单
     * @param rentalAccident
     * @return
     */
    String queryRentalOrderOrMaintenance(RentalAccident rentalAccident);
}
