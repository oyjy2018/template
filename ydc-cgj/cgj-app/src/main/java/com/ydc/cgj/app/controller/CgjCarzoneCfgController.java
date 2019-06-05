package com.ydc.cgj.app.controller;


import com.google.gson.Gson;
import com.ydc.cgj.app.service.CgjCarzoneCfgService;
import com.ydc.cgj.app.service.DictionaryDetailService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车圈文章展示模块
 */
@RestController
@RequestMapping(value = "/app-carzone")
public class CgjCarzoneCfgController {

    @Autowired
    private CgjCarzoneCfgService cgjCarzoneCfgService;

    @Autowired
    private DictionaryDetailService dictionaryDetailService;
    @PostMapping(value = "/getCarzoneListByType")
    public String getCarzoneListByType(@RequestParam("data")String data){
        CgjCarzoneCfgQueDTO cgjViolationRecordQueDTO = JsonUtil.jsonToBean(data,CgjCarzoneCfgQueDTO.class);
        cgjViolationRecordQueDTO.setShow(1);
        return cgjCarzoneCfgService.getCarzoneListByType(cgjViolationRecordQueDTO);
    }

    /**
     * 获取车圈配置类别下拉框
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getCarzoneOptionList", method = RequestMethod.POST)
    public String getCarzoneOptionList() {
        Map<String, Object> jMap = new HashMap<>();
        List<DictionaryDetail> dictionaryDetailList = dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_CGJ_CARZONE);
        if (dictionaryDetailList == null||dictionaryDetailList.size()<1) {
            return Result.failure("请先新增一个类别。").toJSON();
        }
        jMap.put("carzoneOption",dictionaryDetailList);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        CgjCarzoneCfgQueDTO cgjViolationRecordQueDTO = new CgjCarzoneCfgQueDTO();
        cgjViolationRecordQueDTO.setRows(10);
        cgjViolationRecordQueDTO.setPage(1);
        cgjViolationRecordQueDTO.setShow(1);
        cgjViolationRecordQueDTO.setRecommend(1);
        String carzoneListByType = cgjCarzoneCfgService.getCarzoneListByType(cgjViolationRecordQueDTO);
        map = gson.fromJson(carzoneListByType, map.getClass());
        jMap.put("carzoneList",map.get("data"));
        return Result.success(jMap).toJSON();
    }
}
