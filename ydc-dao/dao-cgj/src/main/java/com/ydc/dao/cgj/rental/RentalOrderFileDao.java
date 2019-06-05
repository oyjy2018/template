package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.vo.cgj.rentalEnterprise.RentalOrderFileVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.PCRentalEnterpriseDetailsFileVO;
import com.ydc.model.cgj.rental.RentalOrderFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentalOrderFileDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalOrderFile record);

    int insertSelective(RentalOrderFile record);

    RentalOrderFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalOrderFile record);

    int updateByPrimaryKey(RentalOrderFile record);

    /**
     * 企业订单：资料
     * @return
     */
    List<PCRentalEnterpriseDetailsFileVO> getRentalEnterpriseOrderFile(@Param("orderId") Integer orderId);

    /**
     * 查询
     * @param rentalOrderFile
     * @return
     */
    List<RentalOrderFileVO> getRentalOrderFiles(RentalOrderFile rentalOrderFile);
}