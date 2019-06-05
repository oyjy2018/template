package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.RentalAccidentQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalAccidentQueryVO;
import com.ydc.model.cgj.rental.RentalAccident;

import java.util.List;
import java.util.Map;

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
    int insertRentalAccidentInfo(RentalAccident rentalAccident);

    /**
     * 根据id查询事故详情
     * @param id
     * @return
     */
    Map<String, Object> queryRentalAccidentInfoById(Integer id);
    /**
     * 获取事故信息列表
     * @param rentalAccidentQueryDTO
     * @return
     */
    List<RentalAccidentQueryVO> queryRentalAccidentListInfo(RentalAccidentQueryDTO rentalAccidentQueryDTO);

    /**
     * 编辑事故信息
     * @param rentalAccident
     * @return
     */
    int  updateRentalAccidentInfo(RentalAccident rentalAccident);

    /**
     * 删除事故信息
     * @param id
     * @return
     */
    int deleteRentalAccidentInfo(Integer id);

    /**
     * 查询租车订单或机务单信息
     * @param rentalAccident
     * @return
     */
    Map<String,String> queryRentalOrderOrMaintenance(RentalAccident rentalAccident) throws  Exception;
}
