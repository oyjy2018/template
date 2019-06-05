package com.ydc.service.order.service;

import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalOrderFileVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.PCRentalEnterpriseDetailsFileVO;
import com.ydc.model.cgj.rental.RentalOrderFile;

import java.util.List;

/**
 * @author
 * @create 2019-01-05 16:24
 **/
public interface RentalOrderFileService {


    /**
     * 企业订单：资料
     * @return
     */
    List<PCRentalEnterpriseDetailsFileVO> getRentalEnterpriseOrderFile(Integer orderId);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(RentalOrderFile record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(RentalOrderFile record);

    /**
     * 查询
     * @param rentalOrderFile
     * @return
     */
    List<RentalOrderFileVO> getRentalOrderFiles(RentalOrderFile rentalOrderFile);
}
