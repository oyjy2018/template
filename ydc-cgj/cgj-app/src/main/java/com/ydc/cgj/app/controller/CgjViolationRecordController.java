package com.ydc.cgj.app.controller;

import com.ydc.cgj.app.service.CgjViolationRecordService;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.CgjViolationRecordQueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 违章查询
 */
@RestController
@RequestMapping(value = "/violation")
public class CgjViolationRecordController {
    @Autowired
    private CgjViolationRecordService cgjViolationRecordService;
    /**
     * 获取违章记录列表
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getRecordList")
    public String getCarList(@RequestParam("data") String data) {
        CgjViolationRecordQueDTO cgjViolationRecordQueDTO = JsonUtil.jsonToBean(data,CgjViolationRecordQueDTO.class);
        if (StringUtil.isBlank(cgjViolationRecordQueDTO.getLsprefix())) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车牌前缀").toJSON();
        }
        if (StringUtil.isBlank(cgjViolationRecordQueDTO.getLsnum())) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车牌号码").toJSON();
        }
        if (StringUtil.isBlank(cgjViolationRecordQueDTO.getFrameno())) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车架号").toJSON();
        }
        if (StringUtil.isBlank(cgjViolationRecordQueDTO.getEngineno())) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少发动机号").toJSON();
        }

        return cgjViolationRecordService.getRecordList(data);

    }
}
