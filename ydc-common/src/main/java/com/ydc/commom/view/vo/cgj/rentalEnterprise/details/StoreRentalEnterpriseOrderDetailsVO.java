package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.vo.cgj.rentalEnterprise.StoreRentalEnterpriseOrderVO;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/** 门店端企业详情
 * @author
 * @create 2019-01-07 9:44
 **/
public class StoreRentalEnterpriseOrderDetailsVO implements Serializable {
    private static final long serialVersionUID = -4263662764926667187L;


    //基本信息
    private StoreRentalEnterpriseOrderVO storeRentalEnterpriseOrderVO;

    //保证金
    private StoreRentalEnterpriseOrderDepositVO storeRentalEnterpriseOrderDepositVO;

    //租金及押金支付信息
    private List<StoreRentalFeeVoucherVO> storeRentalFeeVoucherVO;

    //结算信息
    private StoreRentalEnterpriseSettlementVO storeRentalEnterpriseSettlementVO;


    public static void main(String[] args) {
        StoreRentalEnterpriseOrderDetailsVO storeRentalEnterpriseOrderDetailsVO = new StoreRentalEnterpriseOrderDetailsVO();
        StoreRentalEnterpriseOrderVO storeRentalEnterpriseOrderVO = new StoreRentalEnterpriseOrderVO();
        storeRentalEnterpriseOrderVO.setCarLevel("豪华");
        storeRentalEnterpriseOrderVO.setFetchCarMode(1);
        storeRentalEnterpriseOrderVO.setRepayCarMode(1);
        storeRentalEnterpriseOrderVO.setStatus(1);
        storeRentalEnterpriseOrderVO.setFlowOneStatus(0);
        storeRentalEnterpriseOrderVO.setFlowTwoStatus(0);
        storeRentalEnterpriseOrderVO.setFlowThreeStatus(0);
        storeRentalEnterpriseOrderDetailsVO.setStoreRentalEnterpriseOrderVO(storeRentalEnterpriseOrderVO);

        StoreRentalEnterpriseOrderDepositVO storeRentalEnterpriseOrderDepositVO = new StoreRentalEnterpriseOrderDepositVO();
        storeRentalEnterpriseOrderDepositVO.setActualAmount(BigDecimal.valueOf(10000));
        storeRentalEnterpriseOrderDetailsVO.setStoreRentalEnterpriseOrderDepositVO(storeRentalEnterpriseOrderDepositVO);

        List<StoreRentalFeeVoucherVO> storeRentalFeeVoucherVO = Lists.newArrayList();
        StoreRentalFeeVoucherVO storeRentalFeeVoucherVO1 = new StoreRentalFeeVoucherVO();
        storeRentalFeeVoucherVO1.setOneMoney(BigDecimal.valueOf(100));
        storeRentalFeeVoucherVO1.setTwoMoney(BigDecimal.valueOf(200));
        storeRentalFeeVoucherVO.add(storeRentalFeeVoucherVO1);
        storeRentalFeeVoucherVO1 = new StoreRentalFeeVoucherVO();
        storeRentalFeeVoucherVO1.setOneMoney(BigDecimal.valueOf(300));
        storeRentalFeeVoucherVO1.setTwoMoney(BigDecimal.valueOf(400));
        storeRentalFeeVoucherVO.add(storeRentalFeeVoucherVO1);
        storeRentalEnterpriseOrderDetailsVO.setStoreRentalFeeVoucherVO(storeRentalFeeVoucherVO);


        StoreRentalEnterpriseSettlementVO storeRentalEnterpriseSettlementVO = new StoreRentalEnterpriseSettlementVO();
        storeRentalEnterpriseSettlementVO.setSettleMoney(BigDecimal.valueOf(10000));
        storeRentalEnterpriseOrderDetailsVO.setStoreRentalEnterpriseSettlementVO(storeRentalEnterpriseSettlementVO);

        System.out.println(Result.success(storeRentalEnterpriseOrderDetailsVO).toJSON());

    }

    public StoreRentalEnterpriseOrderVO getStoreRentalEnterpriseOrderVO() {
        return storeRentalEnterpriseOrderVO;
    }

    public void setStoreRentalEnterpriseOrderVO(StoreRentalEnterpriseOrderVO storeRentalEnterpriseOrderVO) {
        this.storeRentalEnterpriseOrderVO = storeRentalEnterpriseOrderVO;
    }

    public StoreRentalEnterpriseOrderDepositVO getStoreRentalEnterpriseOrderDepositVO() {
        return storeRentalEnterpriseOrderDepositVO;
    }

    public void setStoreRentalEnterpriseOrderDepositVO(StoreRentalEnterpriseOrderDepositVO storeRentalEnterpriseOrderDepositVO) {
        this.storeRentalEnterpriseOrderDepositVO = storeRentalEnterpriseOrderDepositVO;
    }

    public List<StoreRentalFeeVoucherVO> getStoreRentalFeeVoucherVO() {
        return storeRentalFeeVoucherVO;
    }

    public void setStoreRentalFeeVoucherVO(List<StoreRentalFeeVoucherVO> storeRentalFeeVoucherVO) {
        this.storeRentalFeeVoucherVO = storeRentalFeeVoucherVO;
    }

    public StoreRentalEnterpriseSettlementVO getStoreRentalEnterpriseSettlementVO() {
        return storeRentalEnterpriseSettlementVO;
    }

    public void setStoreRentalEnterpriseSettlementVO(StoreRentalEnterpriseSettlementVO storeRentalEnterpriseSettlementVO) {
        this.storeRentalEnterpriseSettlementVO = storeRentalEnterpriseSettlementVO;
    }
}
