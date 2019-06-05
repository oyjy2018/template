package com.ydc.service.order.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.rental.AddRentalEnterpriseOrderDTO;
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
import java.util.Map;

public interface RentalEnterpriseOrderService {

    int deleteByPrimaryKey(Integer id);

    int insert(RentalEnterpriseOrder record);

    int insertSelective(RentalEnterpriseOrder record);

    RentalEnterpriseOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentalEnterpriseOrder record);

    int updateByPrimaryKey(RentalEnterpriseOrder record);

    /**
     * 新增企业租车订单
     * @param dto
     * @return
     */
    public Result addRentalOrder(AddRentalEnterpriseOrderDTO dto);

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
     * 门店企业订单
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
     * 押金及租金信息
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

    /**
     * 获取当前年份的节假日
     * @return
     */
    Map<String, Object> getHoliday();

    /**
     * 更新预授权状态
     * @param orderId
     * @param flowTwoStatus 将要改变的第二流程状态
     */
    Result updateAuthorizationStatus(Integer orderId, Integer flowTwoStatus);

    /**
     * 企业租车后台列表:订单详情
     * @param dto
     * @return
     */
    String getPCRentalEnterpriseOrderId(RentalEnterpriseOrderDTO dto) throws Exception;

    /**
     * 外部订单:查看详情
     * @param dto
     * @return
     */
    String getStoreRentalEnterpriseOrderId(RentalEnterpriseOrderDTO dto) throws Exception;

    /**
     * 确认订单
     * @param dto
     * @return
     */
    Result confirmOrder(RentalEnterpriseOrderDTO dto);
}
