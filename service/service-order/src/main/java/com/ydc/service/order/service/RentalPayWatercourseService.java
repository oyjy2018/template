package com.ydc.service.order.service;

import com.ydc.commom.view.dto.cgj.rental.RentalPayWatercourseDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalPayWatercourseVO;
import com.ydc.model.cgj.rental.RentalPayWatercourse;

import java.util.List;

/**
 * @author
 * @create 2018-11-26 15:57
 **/
public interface RentalPayWatercourseService {
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-26 15:39:35
     */
    int insert(RentalPayWatercourse record);

    /**
     * 结算流水
     * @param rentalPayWatercourseDTO
     * @return
     */
    List<RentalPayWatercourseVO> getPayWatercourseByOrderId(RentalPayWatercourseDTO rentalPayWatercourseDTO);

    RentalPayWatercourse getRentalPayWatercourseByOrderNoAndDepositType(String orderNo,Integer depositType);


    List<RentalPayWatercourse> getRentalPayWatercourse(String  orderNo,String thirdParthOrderNo);

    /**
     * 根据属性查询
     * @param record
     * @return
     */
    List<RentalPayWatercourse> selectByRentalPayWatercourse(RentalPayWatercourse record);
}
