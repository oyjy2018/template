package com.ydc.cgj.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.weiXinPay.WeiXinPayUtil;
import com.ydc.cgj.app.service.DictionaryDetailService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.view.dto.cgj.CommonReqDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 公共控制器
 *
 * @author gongjin
 * @create 2018-09-18 16:50
 **/
@RestController
@RequestMapping(value = "/common")
public class CommonController {

    private static final Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    DictionaryDetailService dictionaryDetailService;

    /**
     * 获取指定字典表数据
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getDictionaryDetail")
    public String getDictionaryDetail(@RequestParam("data") String data) {
        logger.info("subject:{},crd:{}","H5 我的积分",data);
        CommonReqDTO crd = JSONObject.parseObject(data,CommonReqDTO.class);
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(crd.getParentDictCode())
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(crd.getParentDictCode())));
        return Result.success(optional.get()).toJSON();
    }

    /**
     * 获取:取消原因配置
     * @return
     */
    @PostMapping(value = "/getCancellationReasonsConfig")
    public String getCancellationReasonsConfig(){
        logger.info("subject:{}","H5 获取:取消原因配置");
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.PARENT_DICT_CODE_QXDD)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.PARENT_DICT_CODE_QXDD)));
        return Result.success(optional.get()).toJSON();
    }

    /**
     * 获取:支付方式配置
     * @return
     */
    @PostMapping(value = "/getPayWayConfig")
    public String getPayWayConfig(){
        logger.info("subject:{}","H5 获取:支付方式配置");
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.PARENT_DICT_CODE_PAYWAY)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.PARENT_DICT_CODE_PAYWAY)));
        return Result.success(optional.get()).toJSON();
    }


    /**
     * 获取H5数据配置
     *
     * @return
     */
    @PostMapping(value = "/getH5Config")
    public String getH5Config() {
        Optional<Map<String, List<DictionaryDetail>>> optional = Optional.ofNullable(DictionaryUtil.getH5Config(RedisConstant.H5_CONFIG_KEY)
                .orElseGet(() -> dictionaryDetailService.getH5Config(RedisConstant.H5_CONFIG_KEY)));
        return Result.success(optional.get()).toJSON();
    }

    /**
     * 获取活动时间
     * @param data
     * @return
     */
    @PostMapping(value = "/getActivityIsValid")
    public String getActivityIsValid(@RequestParam("data") String data){
        logger.info("subject:{},data:{}","获取活动是否在有效期内",data);
        String activityCode = JSON.parseObject(data).getString("activityCode");
        DictionaryDetail dictionaryDetail = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(activityCode, "activity")
                .orElseGet(() -> dictionaryDetailService.getDictionaryDetailByDictKey(activityCode, "activity"))).get();
        //开始时间小于当前时间
        if (DateUtil.diffTimeInMillis(DateUtil.parseDateAndTime(dictionaryDetail.getDictValue()), new Date()) <= 0){
            Result result = Result.success("活动未开始");
            result.setData(1);
            return result.toJSON();
        }
        //当前时间大于结束时间
        if (DateUtil.diffTimeInMillis(DateUtil.parseDateAndTime(dictionaryDetail.getRemark1()), new Date()) >= 0){
            Result result = Result.success("活动已结束");
            result.setData(2);
            return result.toJSON();
        }
        Result result = Result.success("活动进行中");
        result.setData(3);
        return result.toJSON();
    }
}
