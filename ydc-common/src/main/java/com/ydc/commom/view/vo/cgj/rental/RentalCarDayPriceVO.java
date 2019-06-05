package com.ydc.commom.view.vo.cgj.rental;

/**
 * 车辆每日价格
 */
public class RentalCarDayPriceVO {
    /**
     * 一周中的第几天
     */
    private Integer dayOfWeek;
    /**
     * 一个月中的第几天
     */
    private Integer dayOfMonth;
    /**
     * 价格
     */
    private Integer charge;
    /**
     * 日期
     */
    private String dayStr;

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    @Override
    public String toString() {
        return "RentalCarDayPriceVO{" +
                "dayOfWeek=" + dayOfWeek +
                ", dayOfMonth=" + dayOfMonth +
                ", charge=" + charge +
                ", dayStr=" + dayStr +
                '}';
    }
}