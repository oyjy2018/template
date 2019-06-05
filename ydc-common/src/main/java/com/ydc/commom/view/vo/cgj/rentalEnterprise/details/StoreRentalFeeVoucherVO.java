package com.ydc.commom.view.vo.cgj.rentalEnterprise.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.ydc.commom.enums.rental.RentalFeeVoucherEnum;
import com.ydc.model.cgj.rental.RentalFeeVoucher;
import com.ydc.model.util.DateUtil;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 租车凭证
 *
 * @author
 * @create 2019-01-05 18:26
 **/
public class StoreRentalFeeVoucherVO implements Serializable {
    private static final long serialVersionUID = 4283961944806583398L;

    private BigDecimal oneMoney;//租金总额
    private BigDecimal twoMoney;//押金总额
    private Date payTime;//支付方支付时间
    private Integer voucherType;//订单类型（1：平台收取租金；2：租金转移至资源方；3：押金退还至平台；4：押金退还至需求方）
    private String voucherTypeName;

    public static List<StoreRentalFeeVoucherVO> getStoreRentalFeeVoucherVO(List<RentalFeeVoucher> rentalFeeVoucherList){
        List<StoreRentalFeeVoucherVO> storeRentalFeeVoucherVOS = Lists.newArrayList();
        if(rentalFeeVoucherList == null)return storeRentalFeeVoucherVOS;
        return rentalFeeVoucherList.stream().map(item -> {
            StoreRentalFeeVoucherVO storeRentalFeeVoucherVO = new StoreRentalFeeVoucherVO();
            storeRentalFeeVoucherVO.setOneMoney(item.getOneMoney());
            storeRentalFeeVoucherVO.setTwoMoney(item.getTwoMoney());
            storeRentalFeeVoucherVO.setPayTime(item.getPayTime());
            storeRentalFeeVoucherVO.setVoucherType(item.getVoucherType());
            return storeRentalFeeVoucherVO;
        }).collect(Collectors.toList());
    }

    public Integer getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Integer voucherType) {
        this.voucherType = voucherType;
    }

    public BigDecimal getOneMoney() {
        return oneMoney;
    }

    public void setOneMoney(BigDecimal oneMoney) {
        this.oneMoney = oneMoney;
    }

    public BigDecimal getTwoMoney() {
        return twoMoney;
    }

    public void setTwoMoney(BigDecimal twoMoney) {
        this.twoMoney = twoMoney;
    }
    @JSONField(format = DateUtil.DATAFORMAT_STR)
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getVoucherTypeName() {
        return RentalFeeVoucherEnum.VoucherType.getVoucherCH(this.voucherType);
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName;
    }
}
