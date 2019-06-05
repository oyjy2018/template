package com.ydc.service.ydhc.user.controller;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.service.ydhc.user.service.DictionaryDetailService;
import com.ydc.service.ydhc.user.service.YdhcValidateCodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ydhcValidateCode")
public class YdhcValidateCodeController {
    private static final Logger logger = LogManager.getLogger(YdhcValidateCodeController.class);

    @Autowired
    private YdhcValidateCodeService ydhcValidateCodeService;

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @return
     */
    @PostMapping("/sendValidateCode")
    public Result sendValidateCode(String mobilePhone) throws Exception {
        //发送短信验证码是否打开
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.YDHC_DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.YDHC_DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW));
        //是否是体验账号
        DictionaryDetail testAccount = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.TEST_ACCOUNT, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.TEST_ACCOUNT, DictionaryConstant.DICT_CODE_DXFW));
        logger.info("subject:{},mobilePhone:{},dictionaryDetail:{}","发送验证码",mobilePhone,JsonUtil.gsonStr(dictionaryDetail));
        if ((dictionaryDetail == null || !("1").equals(dictionaryDetail.getDictValue())) ||
                (testAccount != null && testAccount.getDictValue() != null && testAccount.getDictValue().contains(mobilePhone))) {
            return Result.getResult(ResultConstant.RESULT_CODE_SUCCESS, "短信发送成功");
        }
        //发送短信
        return ydhcValidateCodeService.sendValidateCode(mobilePhone);
    }

    /**
     * 检验验证码
     *
     * @param mobilePhone
     * @param validateCode
     * @return
     * @throws Exception
     */
    @GetMapping("/checkValidateCode")
    public Result checkValidateCode(String mobilePhone, String validateCode) throws Exception {
        DictionaryDetail dictionaryDetail = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.YDHC_DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.YDHC_DICT_CODE_FSGN, DictionaryConstant.DICT_CODE_DXFW));
        //是否是体验账号
        DictionaryDetail testAccount = DictionaryUtil.getDictionaryDetailByDictKey(DictionaryConstant.TEST_ACCOUNT, DictionaryConstant.DICT_CODE_DXFW)
                .orElseGet(() ->dictionaryDetailService.getDictionaryDetailByDictKey(DictionaryConstant.TEST_ACCOUNT, DictionaryConstant.DICT_CODE_DXFW));
        if ((dictionaryDetail == null || !("1").equals(dictionaryDetail.getDictValue())) ||
                (testAccount != null && testAccount.getDictValue() != null && testAccount.getDictValue().contains(mobilePhone))){
            return Result.success("验证码正确");
        }
        return ydhcValidateCodeService.checkValidateCode(mobilePhone, validateCode);
    }
}
