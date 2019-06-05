package com.ydc.service.ydhc.user.service.impl;

import com.ydc.beans.mq.sms.service.SMSSendMessageService;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.constant.ValidateCodeConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.*;
import com.ydc.dao.ydhc.YdhcUserDao;
import com.ydc.dao.ydhc.YdhcValidateCodeDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.ydhc.YdhcUser;
import com.ydc.model.ydhc.YdhcValidateCode;
import com.ydc.service.ydhc.user.service.DictionaryDetailService;
import com.ydc.service.ydhc.user.service.YdhcValidateCodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@Service
public class YdhcValidateCodeServiceImpl implements YdhcValidateCodeService {
    private static final Logger logger = LogManager.getLogger(YdhcValidateCodeServiceImpl.class);

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    @Autowired
    private YdhcValidateCodeDao ydhcValidateCodeDao;

    @Autowired
    private SMSSendMessageService smsSendMessageService;

    @Autowired
    private YdhcUserDao ydhcUserDao;

    @Override
    public Result sendValidateCode(String mobilePhone) throws Exception {
        logger.info("发送短信验证码;mobilePhone:"+mobilePhone);
        // 参数验证
        if (StringUtil.isEmpty(mobilePhone)) {
            return Result.getResult(ResultConstant.RESULT_CODE_FAILURE, "手机号码不能为空");
        }
        // 发送上限
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSSX_CH, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSSX_CH, DictionaryConstant.DICT_CODE_DXFW));
        // 同一个手机号一天发送上限
        int maxSend = Integer.valueOf(dictionaryDetail.getDictValue());
        // 查同一个手机号一天发送的次数
        Integer cot = ydhcValidateCodeDao.selectSendCount(mobilePhone, new Date());
        if (cot == null || cot < maxSend) {
            DictionaryDetail sxsjdictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_YZMSXSJ_CH, DictionaryConstant.DICT_CODE_DXFW)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_YZMSXSJ_CH, DictionaryConstant.DICT_CODE_DXFW));
            // 失效时间（单位分）
            int timeout = Integer.valueOf(sxsjdictionaryDetail.getDictValue());
            String code = NumberUtil.getNumberCheckCode(6); // 手机验证码
            Date now = new Date();
            YdhcValidateCode ydhcValidateCode = new YdhcValidateCode();
            ydhcValidateCode.setMobilePhone(mobilePhone);
            ydhcValidateCode.setCode(code);
            ydhcValidateCode.setHasUsed(CodeConstant.NOT_USED);
            ydhcValidateCode.setTimeOut(DateUtil.dateAdd(now, Calendar.MINUTE, timeout));
            ydhcValidateCode.setStatus(CodeConstant.NORMAL_STATUS);
            ydhcValidateCode.setCreateTime(now);
            ydhcValidateCode.setUpdateTime(now);
            ydhcValidateCodeDao.insert(ydhcValidateCode);
            //存入redis
            RedisUtil.redisSet(RedisConstant.YDHC_VALIDATECODE_KEY.concat(mobilePhone), code, (long) (timeout * 60));


            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)));
            if(dic.isPresent() && dic.get().getDictValue().equals("1")){
                // 发送阿里云短信
                smsSendMessageService.sendSMSMessage(mobilePhone,code,AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_LOGIN.getValidateCode());
            }else{
                DictionaryDetail alydx = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX)
                        .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX));
                // 发送阿里云短信
                AliyunSmsUtil.sendValidateCode(mobilePhone, code, AliyunSmsUtil.ValidateCodeEnum.VALIDATE_CODE_LOGIN.getValidateCode(), alydx.getDictValue(), alydx.getRemark1(), alydx.getRemark2());
            }

            // 信信客平台
            //SMSUtil.send(mobilePhone, code);
        } else {
            return Result.getResult(ResultConstant.MAX_SEND_FAILURE, "短信验证码发送次数超过一天最大限制，请明天再试");
        }
        Map<String, Object> jMap = new HashMap<>();
        YdhcUser ydhcUser = ydhcUserDao.getUserByMobilePhone(mobilePhone);
        if(ydhcUser == null || ydhcUser.getId() == 0 || StringUtil.isEmpty(ydhcUser.getOpenid())){
            jMap.put("hasRegister",0);
        }else{
            jMap.put("hasRegister",1);
        }
        return Result.success("短信发送成功",jMap);
    }

    @Override
    public Result checkValidateCode(String mobilePhone, String code) throws Exception {
        Object preCode = RedisUtil.redisGet(RedisConstant.YDHC_VALIDATECODE_KEY.concat(mobilePhone));
        if (preCode != null){
            if(ValidateCodeConstant.INVALID_VALIDATE_CODE.equals(preCode)){
                return Result.failure("验证码已失效，请重新获取");
            }
            if (!code.equals(preCode)){
                return Result.failure("验证码错误");
            }
            Long expire = RedisUtil.getExpireTimeType(RedisConstant.YDHC_VALIDATECODE_KEY.concat(mobilePhone));
            RedisUtil.redisSet(RedisConstant.YDHC_VALIDATECODE_KEY.concat(mobilePhone), ValidateCodeConstant.INVALID_VALIDATE_CODE, expire);
            return Result.success("验证码正确");
        }
        //如果缓存没有，则校验数据库
        YdhcValidateCode ydhcValidateCode = ydhcValidateCodeDao.selectValidateCode(mobilePhone);
        if (ydhcValidateCode == null) {
            return Result.failure("请先获取验证码");
        }
        if (ydhcValidateCode.getHasUsed() == CodeConstant.HAS_USED ||
                ydhcValidateCode.getTimeOut().getTime() < new Date().getTime()){
            return Result.failure("验证码已失效，请重新获取");
        }
        if (!code.equals(ydhcValidateCode.getCode())) {
            return Result.failure("验证码错误");
        }
        ydhcValidateCode.setHasUsed(CodeConstant.HAS_USED);
        ydhcValidateCodeDao.updateByPrimaryKeySelective(ydhcValidateCode);
        return Result.success("验证码正确");
    }
}
