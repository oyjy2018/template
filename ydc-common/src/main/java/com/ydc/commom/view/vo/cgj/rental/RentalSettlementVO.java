package com.ydc.commom.view.vo.cgj.rental;



import com.alibaba.fastjson.JSONObject;
import org.assertj.core.util.Lists;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 租车结算信息
 *
 * @author
 * @create 2018-11-26 14:48
 **/
public class RentalSettlementVO implements Serializable {
    private static final long serialVersionUID = 6737011461282808623L;

    private Integer orderId;//订单号
    private String carNumber;//车牌号
    private String mobilePhone;//手机号
    private String name;//姓名
    private BigDecimal rentalAuthPayableAmount;//租车预授权额
    private String appointmentRepayCarTime;//预约还车时间
    private String comeCarTime;//出车时间
    private String repayCarTime;//还车时间
    private String realRentDays;//实际租车天数
    private String overdueDays;//超出天数
    private Integer comeWarehouseOilAmount;//出库油量
    private BigDecimal  violationAuthPayableAmount;//违章预授权额

    /**
     * 结算信息
     */
    private RentalSettlement2VO rentalSettlement2VO;

    public static void main(String[] args) {
        RentalSettlementVO rentalSettlementVO= new RentalSettlementVO();
        rentalSettlementVO.setOrderId(1);
        rentalSettlementVO.setCarNumber("粤B1223");
        rentalSettlementVO.setMobilePhone("1881187994");
        rentalSettlementVO.setName("李四");
//        rentalSettlementVO.setPayableAmount(BigDecimal.valueOf(5000));

        RentalSettlement2VO rentalSettlement2VO = new RentalSettlement2VO();
        rentalSettlement2VO.setOverdueDays(BigDecimal.valueOf(0.1));
        rentalSettlement2VO.setRealRentDays(BigDecimal.valueOf(1));
        rentalSettlement2VO.setRentTotal(BigDecimal.valueOf(50000));

        List<RentalPayWatercourseVO> rentalPayWatercourseVOS = Lists.newArrayList();
        RentalPayWatercourseVO rentalPayWatercourseVO = new RentalPayWatercourseVO();
        rentalPayWatercourseVO.setOperationTime("2018-01-10 14:22:22");
        rentalPayWatercourseVO.setActualAmount(BigDecimal.valueOf(1000));
        rentalPayWatercourseVO.setOperationUserName("李四");
        rentalPayWatercourseVOS.add(rentalPayWatercourseVO);
        rentalPayWatercourseVO = new RentalPayWatercourseVO();
        rentalPayWatercourseVO.setOperationTime("2018-01-13 13:23:23");
        rentalPayWatercourseVO.setActualAmount(BigDecimal.valueOf(2000));
        rentalPayWatercourseVO.setOperationUserName("王五");
        rentalPayWatercourseVOS.add(rentalPayWatercourseVO);
        rentalSettlement2VO.setRentalPayWatercourseVOS(rentalPayWatercourseVOS);

        rentalSettlementVO.setRentalSettlement2VO(rentalSettlement2VO);
        System.out.println(JSONObject.toJSON(rentalSettlementVO));
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRentalAuthPayableAmount() {
        return rentalAuthPayableAmount;
    }

    public void setRentalAuthPayableAmount(BigDecimal rentalAuthPayableAmount) {
        this.rentalAuthPayableAmount = rentalAuthPayableAmount;
    }

    public String getAppointmentRepayCarTime() {
        return appointmentRepayCarTime;
    }

    public void setAppointmentRepayCarTime(String appointmentRepayCarTime) {
        this.appointmentRepayCarTime = appointmentRepayCarTime;
    }

    public String getComeCarTime() {
        return comeCarTime;
    }

    public void setComeCarTime(String comeCarTime) {
        this.comeCarTime = comeCarTime;
    }

    public RentalSettlement2VO getRentalSettlement2VO() {
        return rentalSettlement2VO;
    }

    public void setRentalSettlement2VO(RentalSettlement2VO rentalSettlement2VO) {
        this.rentalSettlement2VO = rentalSettlement2VO;
    }

    public String getRealRentDays() {
        return realRentDays;
    }

    public void setRealRentDays(String realRentDays) {
        this.realRentDays = realRentDays;
    }

    public String getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(String overdueDays) {
        this.overdueDays = overdueDays;
    }

    public Integer getComeWarehouseOilAmount() {
        return comeWarehouseOilAmount;
    }

    public void setComeWarehouseOilAmount(Integer comeWarehouseOilAmount) {
        this.comeWarehouseOilAmount = comeWarehouseOilAmount;
    }

    public BigDecimal getViolationAuthPayableAmount() {
        return violationAuthPayableAmount;
    }

    public void setViolationAuthPayableAmount(BigDecimal violationAuthPayableAmount) {
        this.violationAuthPayableAmount = violationAuthPayableAmount;
    }

    public String getRepayCarTime() {
        return repayCarTime;
    }

    public void setRepayCarTime(String repayCarTime) {
        this.repayCarTime = repayCarTime;
    }
}
