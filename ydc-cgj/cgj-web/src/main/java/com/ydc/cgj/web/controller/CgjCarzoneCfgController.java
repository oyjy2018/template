package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.CgjCarzoneCfgService;
import com.ydc.cgj.web.service.DictionaryDetailService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.CommonReqDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgQueDTO;
import com.ydc.commom.view.dto.cgj.sys.CgjCarzoneCfgSaveDTO;
import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.model.cgj.CgjCarzoneCfg;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.java2d.pipe.AlphaPaintPipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商城
 */
@RestController
@RequestMapping(value = "/carzone")
public class CgjCarzoneCfgController {

    private static final Logger logger = LogManager.getLogger(CgjCarzoneCfgController.class);

    @Autowired
    private CgjCarzoneCfgService cgjCarzoneCfgService;

    @Autowired
    private DictionaryDetailService dictionaryDetailService;
    /**
     * 获取车圈配置列表
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/queryCarzoneCfgList", method = RequestMethod.POST)
    public String queryCarzoneCfgList(@RequestParam("data") String data) {
        logger.info("subject:{},cgjCarzoneCfgQueDTO:{}", "获取车圈配置列表", data);
        CgjCarzoneCfgQueDTO cgjCarzoneCfgQueDTO = JSONObject.parseObject(data,CgjCarzoneCfgQueDTO.class);
        return cgjCarzoneCfgService.queryCarzoneCfgList(cgjCarzoneCfgQueDTO);
    }

    /**
     * 获取车圈配置详情
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/queryCarzoneCfgDetails", method = RequestMethod.POST)
    public String queryCarzoneCfgDetails(@RequestParam("data") String data) {
        logger.info("subject:{},cgjCarzoneCfgQueDTO:{}", "获取车圈配置详情", data);
        CgjCarzoneCfg cgjCarzoneCfg = JSONObject.parseObject(data,CgjCarzoneCfg.class);
        return cgjCarzoneCfgService.queryCarzoneCfgDetails(cgjCarzoneCfg.getId());
    }

    /**
     * 保存或修改车圈配置信息
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateCarzoneCfg", method = RequestMethod.POST)
    public String saveOrUpdateCarzoneCfg(@RequestParam("data") String data) {
        logger.info("subject:{},cgjCarzoneCfgSaveDTO:{}", "保存或修改车圈配置信息", data);
        CgjCarzoneCfgSaveDTO cgjCarzoneCfgSaveDTO = JSONObject.parseObject(data,CgjCarzoneCfgSaveDTO.class);
        //校验车圈配置信息
        //校验车圈配置信息
        if (cgjCarzoneCfgSaveDTO.getCgjCarzoneCfg() == null) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少车圈配置参数").toJSON();
        }
        if (cgjCarzoneCfgSaveDTO.getCgjCarzoneCfg().getTitle() == null) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少文章标题").toJSON();
        }
        if (cgjCarzoneCfgSaveDTO.getCgjCarzoneCfg().getArticleUrl() == null) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少跳转链接").toJSON();
        }
        if (cgjCarzoneCfgSaveDTO.getCommImg() == null) {
            return Result.getResult(ResultConstant.RESULT_CODE_PARAM_FAILURE, "缺少文章图标").toJSON();
        }
        return cgjCarzoneCfgService.saveOrUpdateCarzoneCfg(cgjCarzoneCfgSaveDTO);
    }


    /**
     * 删除车圈配置详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCarzoneCfg", method = RequestMethod.POST)
    public String deleteCarzoneCfg(@RequestParam("data") String data) {
        logger.info("subject:{},cgjCarzoneCfgQueDTO:{}", "删除车圈配置", data);
        CgjCarzoneCfg cgjCarzoneCfg = JSONObject.parseObject(data,CgjCarzoneCfg.class);
        return cgjCarzoneCfgService.deleteCarzoneCfg(cgjCarzoneCfg.getId());
    }


    /**
     * 更改是否展示状态
     *
     * @param showStatus
     * @return
     */
    @RequestMapping(value = "/updateShowStatus", method = RequestMethod.POST)
    public String updateShowStatus(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}", "更改是否展示状态", data);
        CgjCarzoneCfg cgjCarzoneCfg = JSONObject.parseObject(data,CgjCarzoneCfg.class);
        return cgjCarzoneCfgService.updateShowStatus(cgjCarzoneCfg);
    }


    /**
     * 获取车圈配置类别下拉框
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCarzoneOptionList", method = RequestMethod.POST)
    public String getCarzoneOptionList() {
        logger.info("subject:{},data:{}","获取事故列表相关下拉框选值","null");
        Map<String, Object> jMap = new HashMap<>();
        List<DictionaryDetail> dictionaryDetailList = dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_CGJ_CARZONE);
        if (dictionaryDetailList == null||dictionaryDetailList.size()<1) {
            return Result.failure("请先新增一个类别。").toJSON();
        }
        jMap.put("carzoneOption",dictionaryDetailList);
        return Result.success(jMap).toJSON();
    }

    /**
     * 新增类别
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/saveCarzoneOption", method = RequestMethod.POST)
    public String saveCarzoneOption(@RequestParam("data") String data) {
        logger.info("subject:{},data:{}","新增类别",data);
        DictionaryDetailDTO dictionaryDetail = JsonUtil.jsonToBean(data, DictionaryDetailDTO.class);
        if (StringUtil.isBlank(dictionaryDetail.getDictValue())) {
            return Result.failure("新增分类名称不能为空。").toJSON();
        }
        List<DictionaryDetail> detailList = dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_CGJ_CARZONE);
        for (DictionaryDetail detail : detailList) {
            if (dictionaryDetail.getDictValue().replaceAll(" ","").equals(detail.getDictValue())) {
                return Result.failure("对不起，新建的分类名称已经存在，请重新填写。").toJSON();
            }
        }
        Integer managerId = WebShiroTokenManager.getUser().getId();
//        Integer managerId =7;
        dictionaryDetail.setCreateBy(managerId);
        dictionaryDetail.setUpdateBy(managerId);
        dictionaryDetail.setRemark3("APP");
        dictionaryDetail.setParentDictCode(DictionaryConstant.DICT_CODE_CGJ_CARZONE);
        return dictionaryDetailService.insertDictionaryDatail(dictionaryDetail);
    }
}
