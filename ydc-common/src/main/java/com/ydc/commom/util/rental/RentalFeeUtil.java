package com.ydc.commom.util.rental;

import com.ydc.commom.enums.common.HolidayEnum;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.common.Holiday;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;

public class RentalFeeUtil {

    private static Logger logger = LogManager.getLogger(RentalFeeUtil.class);

    /**
     * 租金总费用公式与总额
     * @param workdayRent 工作日租金
     * @param weekendRent 周末租金
     * @param holidayRent 节假日租金
     * @param reserveCount 预约数量
     * @param dateList 使用日期集合
     * @param holidayMap 节假日配置
     * @return
     */
    public static Map<String, Object> rentTotalFeeFormula(BigDecimal workdayRent, BigDecimal weekendRent, BigDecimal holidayRent,
                                             Integer reserveCount, List<Date> dateList, Map<String, Holiday> holidayMap){
        Map<String, Object> result = new HashMap<>();
        StringBuffer sb = new StringBuffer(reserveCount+" * (");
        BigDecimal totalFee = null;

        //租期大于28天则以工作日租金*租期计算
        if(dateList.size() > 28){
            sb.append(workdayRent.toString()).append(" * ").append(dateList.size());
            totalFee = workdayRent.multiply(BigDecimal.valueOf(dateList.size()));
        }else{
            int workdayRentCount = 0;
            int weekendRentCount = 0;
            int holidayRentCount = 0;
            for(Date date: dateList){
                if(holidayMap.containsKey(DateUtil.formatDate(date))){
                    if(holidayMap.get(DateUtil.formatDate(date)).getHolidayType().intValue() == HolidayEnum.HolidayType.STATUS1.getStatus()){
                        holidayRentCount++;
                    }else{
                        workdayRentCount++;
                    }
                }else{
                    if(DateUtil.hasWeekend(date)){
                        weekendRentCount++;
                    }else{
                        workdayRentCount++;
                    }
                }
            }

            if(workdayRentCount > 0){
                sb.append(workdayRent.toString() + " * " + workdayRentCount);
            }
            if(weekendRentCount > 0){
                sb.append(" + ");
                sb.append(weekendRent.toString() + " * " + weekendRentCount);
            }
            if(holidayRentCount > 0){
                sb.append(" + ");
                sb.append(holidayRent.toString() + " * " + holidayRentCount);
            }
            totalFee = workdayRent.multiply(BigDecimal.valueOf(workdayRentCount))
                    .add(weekendRent.multiply(BigDecimal.valueOf(weekendRentCount)))
                    .add(holidayRent.multiply(BigDecimal.valueOf(holidayRentCount)));
            logger.info("single car fee = {}",totalFee.toString());
            totalFee =  totalFee.multiply(BigDecimal.valueOf(reserveCount)); //乘以车数量
            logger.info("all car fee：{}{} = {}",sb.toString(),")",totalFee.toString());
        }

        sb.append(") = ¥ " + totalFee.toString());
        result.put("formula", sb.toString());
        result.put("totalFee", totalFee);
        return result;
    }

    /**
     * 日服务费公式与总额
     * @param dayServiceCharge 日服务费
     * @param reserveCount 预约数量
     * @param days 预约天数
     * @return
     */
    public static Map<String, Object> dayServiceChargeFormula(BigDecimal dayServiceCharge, Integer reserveCount, Integer days){
        Map<String, Object> result = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        BigDecimal totalFee = dayServiceCharge.multiply(BigDecimal.valueOf(reserveCount))
                .multiply(BigDecimal.valueOf(days));
        sb.append(reserveCount).append(" * ")
                            .append(dayServiceCharge.toString())
                            .append(" * ")
                            .append(days)
                            .append(" = ")
                            .append("¥ ").append(totalFee.toString());

        result.put("formula", sb.toString());
        result.put("totalFee", totalFee);
        return result;
    }

    /**
     * 押金公式与总额
     * @param cashPledge 押金
     * @param reserveCount 预约天数
     * @return
     */
    public static Map<String, Object> cashPledgeFormula(BigDecimal cashPledge, Integer reserveCount){
        Map<String, Object> result = new HashMap<>();
        BigDecimal totalFee = cashPledge.multiply(BigDecimal.valueOf(reserveCount));
        StringBuffer sb = new StringBuffer();
        sb.append(reserveCount)
          .append(" * ")
          .append(cashPledge.toString())
          .append(" = ")
          .append("¥ ")
          .append(totalFee.toString());
        result.put("formula", sb.toString());
        result.put("totalFee", totalFee);
        return result;
    }

    /**
     * 基本费用公式与总额
     * @param workdayRent 工作日租金
     * @param weekendRent 周末租金
     * @param holidayRent 节假日租金
     * @param dayServiceCharge 日服务费
     * @param cashPledge 押金
     * @param reserveCount 预约数量
     * @param startTime 预约开始时间
     * @param endTime 预约结束时间
     * @param holidayMap 节假日配置
     * @return
     */
    public static Map<String, Object> overheadChargeDetailFormula(BigDecimal workdayRent, BigDecimal weekendRent, BigDecimal holidayRent,
                                                 BigDecimal dayServiceCharge, BigDecimal cashPledge, Integer reserveCount,
                                                 Date startTime, Date endTime, Map<String, Holiday> holidayMap){
        logger.info("subject:{}," +
                "workdayRent:{},weekendRent:{}," +
                "holidayRent:{},dayServiceCharge:{}," +
                "cashPledge:{},reserveCount:{}," +
                "startTime:{},endTime:{},holidayMap:{}","基本费用公式与总额" ,workdayRent,  weekendRent,  holidayRent,
                 dayServiceCharge,  cashPledge,  reserveCount,
                JsonUtil.gsonStr(startTime),  JsonUtil.gsonStr(endTime),  holidayMap);

        List<Date> dateList = DateUtil.getDateLists(startTime,endTime);
        Collections.sort(dateList);
        Map<String, Object> result = new HashMap<>();
        BigDecimal totalFee = BigDecimal.ZERO;
        //租金总费用
        Map<String, Object> formulaRet = rentTotalFeeFormula(workdayRent, weekendRent, holidayRent, reserveCount, dateList, holidayMap);
        result.put("rentFormula", formulaRet.get("formula"));
        result.put("rentTotalFee", formulaRet.get("totalFee"));
        totalFee = totalFee.add((BigDecimal)formulaRet.get("totalFee"));
        //日服务费总费用
        formulaRet = dayServiceChargeFormula(dayServiceCharge, reserveCount, dateList.size());
        result.put("dayServiceFormula", formulaRet.get("formula"));
        result.put("dayServiceTotalFee", formulaRet.get("totalFee"));
        totalFee = totalFee.add((BigDecimal)formulaRet.get("totalFee"));
        //押金总费用
        formulaRet = cashPledgeFormula(cashPledge, reserveCount);
        result.put("cashPledgeFormula", formulaRet.get("formula"));
        result.put("cashPledgeTotalFee", formulaRet.get("totalFee"));
        totalFee = totalFee.add((BigDecimal)formulaRet.get("totalFee"));
        //合计
        result.put("totalFeeFormula", "¥ " + totalFee.toString());
        result.put("totalFee", totalFee);

        return result;
    }

    public static void main(String[] arg){
//        Map<String, Object> map = overheadChargeDetailFormula(BigDecimal.valueOf(200), BigDecimal.valueOf(220), BigDecimal.valueOf(300),
//                BigDecimal.valueOf(40), BigDecimal.valueOf(5000), 10, "2019-01-09", "2019-01-31", null
//                );
        List<Date> dateList = DateUtil.getDateLists(DateUtil.parseDate("2018-10-01"), DateUtil.parseDate("2018-10-31"));
        Collections.sort(dateList);
        dateList.forEach(o -> {
            System.out.println(DateUtil.formatDate(o));
        });

        System.out.println(DateUtil.getWeek(new Date()));
    }

}
