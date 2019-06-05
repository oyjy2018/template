package com.ydc.service.user.service.impl;

import com.ydc.beans.mq.sms.service.SMSSendMessageService;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.CodeConstant;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.MemberConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.*;
import com.ydc.dao.cgj.user.ValidateCodeDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.ValidateCode;
import com.ydc.service.user.service.DictionaryDetailService;
import com.ydc.service.user.service.ValidateCodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    private static final Logger logger = LogManager.getLogger(ValidateCodeServiceImpl.class);

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    @Autowired
    private ValidateCodeDao validateCodeDao;

    @Autowired
    private SMSSendMessageService smsSendMessageService;

    @Override
    public Result sendValidateCode(String mobilePhone, Integer validateType) throws Exception {
        logger.info("发送短信验证码;mobilePhone:"+mobilePhone+";validateType:"+validateType);
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
        Integer cot = validateCodeDao.selectSendCount(mobilePhone, new Date());
        if (cot == null || cot < maxSend) {
            DictionaryDetail sxsjdictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_YZMSXSJ_CH, DictionaryConstant.DICT_CODE_DXFW)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_YZMSXSJ_CH, DictionaryConstant.DICT_CODE_DXFW));
            // 失效时间（单位分）
            int timeout = Integer.valueOf(sxsjdictionaryDetail.getDictValue());
            String code = NumberUtil.getNumberCheckCode(6); // 手机验证码
            Date now = new Date();
            ValidateCode validateCode = new ValidateCode();
            validateCode.setMobilePhone(mobilePhone);
            validateCode.setValidateType(validateType);
            validateCode.setCode(code);
            validateCode.setTimeOut(DateUtil.dateAdd(now, Calendar.MINUTE, timeout));
            validateCode.setUsed(CodeConstant.NOT_USED);
            validateCode.setStatus(CodeConstant.NORMAL_STATUS);
            validateCode.setCreateTime(now);
            validateCode.setUpdateTime(now);
            validateCodeDao.insert(validateCode);
            //存入redis
            RedisUtil.redisSet(RedisConstant.VALIDATECODE_KEY.concat(mobilePhone).concat("_").concat(validateType.toString()), code, (long) (timeout * 60));

            Optional<DictionaryDetail> dic = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)
                    .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_CODE_FSDXFS, DictionaryConstant.PARENT_DICT_CODE_FSDXFS)));
            if(dic.isPresent() && dic.get().getDictValue().equals("1")){
                // 发送阿里云短信
                smsSendMessageService.sendSMSMessage(mobilePhone,code,AliyunSmsUtil.ValidateCodeEnum.getValidateCodeByType(validateType));
            }else{
                //企业注册登录 通过信信客平台发送短信
                if(validateType.equals(ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_5.getKey())||validateType.equals(ValidateCode.ValidateTypeEnum.VALIDATETYPE_ENUM_6.getKey())){
                    //发送短信
                    SMSUtil.send(mobilePhone, SMSUtil.YDZCEnum.getValidateCodeByType(validateType), code, SMSUtil.YDZC_SMS_SIGN);
                }else {
                    DictionaryDetail alydx = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX)
                            .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.DICT_KEY_YDKJ, DictionaryConstant.DICT_CODE_ALYDX));
                    // 发送阿里云短信
                    AliyunSmsUtil.sendValidateCode(mobilePhone, code, AliyunSmsUtil.ValidateCodeEnum.getValidateCodeByType(validateType), alydx.getDictValue(), alydx.getRemark1(), alydx.getRemark2());
                }
            }
            // 信信客平台
            //SMSUtil.send(mobilePhone, code);
        } else {
            return Result.getResult(ResultConstant.MAX_SEND_FAILURE, "短信验证码发送次数超过一天最大限制，请明天再试");
        }
        return Result.getResult(ResultConstant.RESULT_CODE_SUCCESS, "短信发送成功");
    }

    @Override
    public Result checkValidateCode(String mobilePhone, Integer validateType, String code) throws Exception {
        final String codeKey = RedisConstant.VALIDATECODE_KEY.concat(mobilePhone).concat("_").concat(validateType.toString());
        Object preCode = RedisUtil.redisGet(codeKey);
        if (preCode == null) {
            //如果缓存没有，则从数据库里面取值校验
            ValidateCode validateCode = validateCodeDao.selectValidateCode(mobilePhone, validateType);
            if (validateCode == null) {
                return Result.failure("请先获取验证码");
            }
            if (CodeConstant.HAS_USED == validateCode.getUsed() || validateCode.getTimeOut().getTime() < new Date().getTime()) {
                return Result.failure("验证码已失效，请重新获取");
            }
            preCode = validateCode.getCode();
        }
        if (!code.equals(preCode.toString())) {
            return Result.failure("验证码错误");
        }
        //验证完成后，将这条数据设置成失效
        new Thread(() -> {
            RedisUtil.remove(codeKey);
            validateCodeDao.updateValidateCodeUsed(mobilePhone, validateType);
        }).start();
        return Result.success("验证码正确");
    }
}
