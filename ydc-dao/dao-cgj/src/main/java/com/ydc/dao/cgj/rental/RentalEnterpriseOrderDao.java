package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalEnterpriseOrderDTO;
import com.ydc.commom.view.vo.cgj.rental.EnterpriseOrderDetailB2BDVO;
import com.ydc.commom.view.vo.cgj.rental.EnterpriseOrderDetailB2BRVO;
import com.ydc.commom.view.vo.cgj.rental.RentalEnterpriseOrderListB2BDVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.PCRentalEnterpriseOrderVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.StoreRentalEnterpriseOrderVO;
import com.ydc.commom.view.vo.cgj.rental.RentalEnterpriseOrderListB2BRVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.PCRentalEnterpriseDetailsBasicVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.PCRentalEnterpriseDetailsDepositVO;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.details.PCRentalEnterpriseDetailsFeeVoucherVO;
import com.ydc.model.cgj.rental.RentalEnterpriseOrder;

import java.util.List;

public interface RentalEnterpriseOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RentalEnterpriseOrder record);

    int insertSelective(RentalEnterpriseOrder record);

    RentalEnterpriseOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalEnterpriseOrder record);

    int updateByPrimaryKey(RentalEnterpriseOrder record);

    /**
     * 查询需求方租车订单列表
     * @param dto
     * @return
     */
    List<RentalEnterpriseOrderListB2BDVO> getEnterpriseOrderListB2BD(RentalEnterpriseOrderDTO dto);

    /**
     * 查询资源方订单列表
     * @param dto
     * @return
     */
    List<RentalEnterpriseOrderListB2BRVO> getEnterpriseOrderListB2BR(RentalEnterpriseOrderDTO dto);

    /**
     * 门店:企业订单
     * @param dto
     * @return
     */
    List<StoreRentalEnterpriseOrderVO> getStoreRentalEnterpriseOrderList(RentalEnterpriseOrderDTO dto);

    /**
     * 门店:企业订单详情
     * @param dto
     * @return
     */
    StoreRentalEnterpriseOrderVO getStoreRentalEnterpriseOrderByOrderId(RentalEnterpriseOrderDTO dto);

    /**
     * 获取车商端租车订单详情
     * @param orderId
     * @return
     */
    EnterpriseOrderDetailB2BDVO getEnterpriseOrderDetailB2BD(Integer orderId);

    /**
     * 后台:企业租车订单
     * @param dto
     * @return
     */
    List<PCRentalEnterpriseOrderVO> getPCRentalEnterpriseOrderList(RentalEnterpriseOrderDTO dto);

    /**
     * 获取车商端出租订单详情
     * @param orderId
     * @return
     */
    EnterpriseOrderDetailB2BRVO getEnterpriseOrderDetailB2BR(Integer orderId);

    /**
     * 基本信息
     * @param dto
     * @return
     */
    PCRentalEnterpriseDetailsBasicVO getPCRentalEnterpriseDetailsBasic(RentalEnterpriseOrderDTO dto);

    /**
     * 保证金信息
     * @param dto
     * @return
     */
    PCRentalEnterpriseDetailsDepositVO getPCRentalEnterpriseDetailsDeposit(RentalEnterpriseOrderDTO dto);

    /**
     * 押金及租金信息
     * @param dto
     * @return
     */
    List<PCRentalEnterpriseDetailsFeeVoucherVO>  getPCRentalEnterpriseDetailsFeeVoucher(RentalEnterpriseOrderDTO dto);
}