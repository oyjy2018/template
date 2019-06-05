package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.ydc.commom.result.Result;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author
 * @create 2019-01-05 11:30
 **/
public class PCRentalEnterpriseOrderDetailsVO implements Serializable {
    private static final long serialVersionUID = 4915142177100110862L;

    //基本信息
    private PCRentalEnterpriseDetailsBasicVO rentalEnterpriseDetailsBasicVO;

    //租金及押金信息
    private List<PCRentalEnterpriseDetailsFeeVoucherVO> rentalEnterpriseDetailsFeeVoucherVOS;

    //保证金
    private PCRentalEnterpriseDetailsDepositVO rentalEnterpriseDetailsDepositVO;

    //出车还车信息
    private List<PCRentalEnterpriseDetailsCarVO> rentalEnterpriseDetailsCarVOList;

    //结算信息
    private PCRentalEnterpriseDetailsSettlementVO rentalEnterpriseDetailsSettlementVO;

    //订单资料
    private List<PCRentalEnterpriseDetailsFileVO> rentalEnterpriseDetailsFileVOS;

    public static void main(String[] args) {
        PCRentalEnterpriseOrderDetailsVO pcRentalEnterpriseOrderDetailsVO = new PCRentalEnterpriseOrderDetailsVO();

        PCRentalEnterpriseDetailsBasicVO rentalEnterpriseDetailsBasicVO = new PCRentalEnterpriseDetailsBasicVO();
        rentalEnterpriseDetailsBasicVO.setFetchCarMode(1);
        rentalEnterpriseDetailsBasicVO.setRepayCarMode(1);
        rentalEnterpriseDetailsBasicVO.setStatus(1);
        rentalEnterpriseDetailsBasicVO.setFlowOneStatus(0);
        rentalEnterpriseDetailsBasicVO.setFlowTwoStatus(0);
        rentalEnterpriseDetailsBasicVO.setFlowThreeStatus(0);
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsBasicVO(rentalEnterpriseDetailsBasicVO);

        List<PCRentalEnterpriseDetailsDepositVO> rentalEnterpriseDetailsDepositVOList = Lists.newArrayList();
        PCRentalEnterpriseDetailsDepositVO rentalEnterpriseDetailsDepositVO = new PCRentalEnterpriseDetailsDepositVO();
        rentalEnterpriseDetailsDepositVO.setPaymentStatus(1);
        rentalEnterpriseDetailsDepositVO.setPaymentMode(1);
        rentalEnterpriseDetailsDepositVO.setActualAmount(BigDecimal.valueOf(100));
        rentalEnterpriseDetailsDepositVOList.add(rentalEnterpriseDetailsDepositVO);
        rentalEnterpriseDetailsDepositVO = new PCRentalEnterpriseDetailsDepositVO();
        rentalEnterpriseDetailsDepositVO.setPaymentStatus(2);
        rentalEnterpriseDetailsDepositVO.setPaymentMode(2);
        rentalEnterpriseDetailsDepositVO.setActualAmount(BigDecimal.valueOf(300));
        rentalEnterpriseDetailsDepositVOList.add(rentalEnterpriseDetailsDepositVO);
//        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsDepositVOList(rentalEnterpriseDetailsDepositVOList);

        List<PCRentalEnterpriseDetailsCarVO> rentalEnterpriseDetailsCarVOList = Lists.newArrayList();
        PCRentalEnterpriseDetailsCarVO pcRentalEnterpriseDetailsCarVO = new PCRentalEnterpriseDetailsCarVO();
        pcRentalEnterpriseDetailsCarVO.setCarNumber("粤B12345");
        rentalEnterpriseDetailsCarVOList.add(pcRentalEnterpriseDetailsCarVO);
        pcRentalEnterpriseDetailsCarVO = new PCRentalEnterpriseDetailsCarVO();
        pcRentalEnterpriseDetailsCarVO.setCarNumber("粤B12343");
        rentalEnterpriseDetailsCarVOList.add(pcRentalEnterpriseDetailsCarVO);
        pcRentalEnterpriseDetailsCarVO = new PCRentalEnterpriseDetailsCarVO();
        pcRentalEnterpriseDetailsCarVO.setCarNumber("粤B12346");
        rentalEnterpriseDetailsCarVOList.add(pcRentalEnterpriseDetailsCarVO);
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsCarVOList(rentalEnterpriseDetailsCarVOList);


        PCRentalEnterpriseDetailsSettlementVO rentalEnterpriseDetailsSettlementVO = new PCRentalEnterpriseDetailsSettlementVO();
        rentalEnterpriseDetailsSettlementVO.setSettleMoney(BigDecimal.valueOf(1254));
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsSettlementVO(rentalEnterpriseDetailsSettlementVO);


        List<PCRentalEnterpriseDetailsFileVO> rentalEnterpriseDetailsFileVOS = Lists.newArrayList();
        PCRentalEnterpriseDetailsFileVO pcRentalEnterpriseDetailsFileVO = new PCRentalEnterpriseDetailsFileVO();
        pcRentalEnterpriseDetailsFileVO.setFileId(1);
        pcRentalEnterpriseDetailsFileVO.setFileCode("xxx1");
        rentalEnterpriseDetailsFileVOS.add(pcRentalEnterpriseDetailsFileVO);
        pcRentalEnterpriseDetailsFileVO = new PCRentalEnterpriseDetailsFileVO();
        pcRentalEnterpriseDetailsFileVO.setFileId(2);
        pcRentalEnterpriseDetailsFileVO.setFileCode("xxx2");
        rentalEnterpriseDetailsFileVOS.add(pcRentalEnterpriseDetailsFileVO);
        pcRentalEnterpriseDetailsFileVO = new PCRentalEnterpriseDetailsFileVO();
        pcRentalEnterpriseDetailsFileVO.setFileId(3);
        pcRentalEnterpriseDetailsFileVO.setFileCode("xxx3");
        rentalEnterpriseDetailsFileVOS.add(pcRentalEnterpriseDetailsFileVO);
        pcRentalEnterpriseOrderDetailsVO.setRentalEnterpriseDetailsFileVOS(rentalEnterpriseDetailsFileVOS);

        System.out.println(Result.success(pcRentalEnterpriseOrderDetailsVO).toJSON());


    }

    public PCRentalEnterpriseDetailsBasicVO getRentalEnterpriseDetailsBasicVO() {
        return rentalEnterpriseDetailsBasicVO;
    }

    public void setRentalEnterpriseDetailsBasicVO(PCRentalEnterpriseDetailsBasicVO rentalEnterpriseDetailsBasicVO) {
        this.rentalEnterpriseDetailsBasicVO = rentalEnterpriseDetailsBasicVO;
    }

    public PCRentalEnterpriseDetailsDepositVO getRentalEnterpriseDetailsDepositVO() {
        return rentalEnterpriseDetailsDepositVO;
    }

    public void setRentalEnterpriseDetailsDepositVO(PCRentalEnterpriseDetailsDepositVO rentalEnterpriseDetailsDepositVO) {
        this.rentalEnterpriseDetailsDepositVO = rentalEnterpriseDetailsDepositVO;
    }

    public List<PCRentalEnterpriseDetailsCarVO> getRentalEnterpriseDetailsCarVOList() {
        return rentalEnterpriseDetailsCarVOList;
    }

    public void setRentalEnterpriseDetailsCarVOList(List<PCRentalEnterpriseDetailsCarVO> rentalEnterpriseDetailsCarVOList) {
        this.rentalEnterpriseDetailsCarVOList = rentalEnterpriseDetailsCarVOList;
    }

    public PCRentalEnterpriseDetailsSettlementVO getRentalEnterpriseDetailsSettlementVO() {
        return rentalEnterpriseDetailsSettlementVO;
    }

    public void setRentalEnterpriseDetailsSettlementVO(PCRentalEnterpriseDetailsSettlementVO rentalEnterpriseDetailsSettlementVO) {
        this.rentalEnterpriseDetailsSettlementVO = rentalEnterpriseDetailsSettlementVO;
    }

    public List<PCRentalEnterpriseDetailsFileVO> getRentalEnterpriseDetailsFileVOS() {
        return rentalEnterpriseDetailsFileVOS;
    }

    public void setRentalEnterpriseDetailsFileVOS(List<PCRentalEnterpriseDetailsFileVO> rentalEnterpriseDetailsFileVOS) {
        this.rentalEnterpriseDetailsFileVOS = rentalEnterpriseDetailsFileVOS;
    }

    public List<PCRentalEnterpriseDetailsFeeVoucherVO> getRentalEnterpriseDetailsFeeVoucherVOS() {
        return rentalEnterpriseDetailsFeeVoucherVOS;
    }

    public void setRentalEnterpriseDetailsFeeVoucherVOS(List<PCRentalEnterpriseDetailsFeeVoucherVO> rentalEnterpriseDetailsFeeVoucherVOS) {
        this.rentalEnterpriseDetailsFeeVoucherVOS = rentalEnterpriseDetailsFeeVoucherVOS;
    }
}
