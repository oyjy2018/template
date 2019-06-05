package com.ydc.cgj.rental.company.app.controller;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.cgj.rental.company.app.service.EnterpriseOrderService;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.rental.RentalFeeUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCommonDTO;
import com.ydc.model.cgj.common.Holiday;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class CommonController {

    private static final Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    EnterpriseOrderService enterpriseOrderService;

    /**
     * 实时计算订单基础费用
     * @param data
     * @return
     */
    @PostMapping("/getOverheadChargeDetail")
    public String getOverheadChargeDetail(@RequestParam String data){
        RentalCommonDTO dto = JsonUtil.jsonToBean(data, RentalCommonDTO.class);
        logger.info("subject:{},dto:{}","实时计算订单基础费用",JsonUtil.gsonStr(dto));
        Map<String, Holiday> holidayMap = null;
        Object o = RedisUtil.redisHashGet(RedisConstant.RENTAL_HOLIDAY_KEY);
        if(o == null){
            Map<String, Object> result = JsonUtil.jsonToMap(enterpriseOrderService.getHoliday());
            if(!result.get("code").toString().equals(ResultConstant.RESULT_CODE_SUCCESS)){
                return Result.getResult(Integer.valueOf(result.get("code").toString()), result.get("message").toString()).toJSON();
            }
            holidayMap = (Map<String, Holiday>)result.get("data");
        }else{
            holidayMap = (Map<String, Holiday>) o;
        }
        Map<String, Object> overheadChargeDetail = RentalFeeUtil.overheadChargeDetailFormula(dto.getWorkdayRent(),dto.getWeekendRent(),
                dto.getHolidayRent(),dto.getDayServiceCharge(),dto.getCashPledge(),dto.getReserveCount(), DateUtil.parseDateAndTime(dto.getRentCarStartTime()),
                DateUtil.parseDateAndTime(dto.getRentCarEndTime()),holidayMap);
        logger.info("subject:{},overheadChargeDetail:{}","实时计算订单基础费用",JsonUtil.gsonStr(overheadChargeDetail));
        return Result.success(overheadChargeDetail).toJSON();
    }

}
