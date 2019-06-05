package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.cgj.web.service.DictionaryDetailService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.CommonReqDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 公共控制器（可以用于获取公共数据，或缓存数据）
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    private static final Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    DictionaryDetailService dictionaryDetailService;

    /**
     * 获取指定字典表数据
     *
     * @param crd
     * @return
     */
    @RequestMapping(value = "/getDictionaryDetail", method = RequestMethod.POST)
    public String getDictionaryDetail(@RequestBody CommonReqDTO crd) {
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(crd.getParentDictCode())
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(crd.getParentDictCode())));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nullOption = Boolean.valueOf(crd.getNullOption());
            if (nullOption != null && nullOption) {
                DictionaryDetail dd = new DictionaryDetail();
                dd.setDictKey("全部");
                dd.setDictValue("");
                dd.setParentDictCode(crd.getParentDictCode());
                dd.setSort(0);
                dictionaryDetailList.add(0, dd);
            }
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取积分获取类型（jfhq）
     * @return
     */
    @PostMapping(value = "/getIncomeExpensesType")
    public String getIncomeExpensesType(){
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_JFHQ_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_JFHQ_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }




    /**
     * 获取供应商
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getSupplier", method = RequestMethod.POST)
    public String getSupplier(@RequestParam("data") String data) {
        logger.info("subject:{},crd:{}", "获取供应商", data);
        CommonReqDTO crd  = JSONObject.parseObject(data,CommonReqDTO.class);
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_GYS_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_GYS_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nullOption = Boolean.valueOf(crd.getNullOption());
            if (nullOption != null && nullOption) {
                DictionaryDetail dd = new DictionaryDetail();
                dd.setDictKey("全部");
                dd.setDictValue("");
                dd.setParentDictCode(DictionaryConstant.DICT_CODE_GYS_CONFIG);
                dd.setSort(0);
                dictionaryDetailList.add(0, dd);
            }
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取商品主分类
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCommodityMainClassify", method = RequestMethod.POST)
    public String getCommodityMainClassify(@RequestParam("data") String data) {
        logger.info("subject:{},crd:{}", "获取商品主分类", data);
        CommonReqDTO crd  = JSONObject.parseObject(data,CommonReqDTO.class);
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_C_MAIN_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_C_MAIN_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nullOption = Boolean.valueOf(crd.getNullOption());
            if (nullOption != null && nullOption) {
                DictionaryDetail dd = new DictionaryDetail();
                dd.setDictKey("全部");
                dd.setDictValue("");
                dd.setParentDictCode(DictionaryConstant.DICT_CODE_C_MAIN_CONFIG);
                dd.setSort(0);
                dictionaryDetailList.add(0, dd);
            }
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取商品子分类
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getCommoditySonClassify", method = RequestMethod.POST)
    public String getCommoditySonClassify(@RequestParam("data") String data) {
        logger.info("subject:{},crd:{}", "获取商品列表", data);
        CommonReqDTO crd  = JSONObject.parseObject(data,CommonReqDTO.class);
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_C_SON_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_C_SON_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            List<DictionaryDetail> sonClassifyList = new ArrayList<>();
            for (DictionaryDetail dd : dictionaryDetailList) {
                if (dd.getRemark1().equals(crd.getMainClassifyCode())) {
                    sonClassifyList.add(dd);
                }
            }
            Boolean nullOption = Boolean.valueOf(crd.getNullOption());
            if (nullOption != null && nullOption) {
                DictionaryDetail dd = new DictionaryDetail();
                dd.setDictKey("全部");
                dd.setDictValue("");
                dd.setParentDictCode(DictionaryConstant.DICT_CODE_C_SON_CONFIG);
                dd.setSort(0);
                sonClassifyList.add(0, dd);
            }
            return Result.success(sonClassifyList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }

    /**
     * 获取物流公司
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/getLogisticsCompany", method = RequestMethod.POST)
    public String getLogisticsCompany(@RequestParam("data") String data) {
        logger.info("subject:{},crd:{}", "获取物流公司", data);
        CommonReqDTO crd  = JSONObject.parseObject(data,CommonReqDTO.class);
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(DictionaryConstant.DICT_CODE_KDWL_CONFIG)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(DictionaryConstant.DICT_CODE_KDWL_CONFIG)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            Boolean nullOption = Boolean.valueOf(crd.getNullOption());
            if (nullOption != null && nullOption) {
                DictionaryDetail dd = new DictionaryDetail();
                dd.setDictKey("全部");
                dd.setDictValue("");
                dd.setParentDictCode(DictionaryConstant.DICT_CODE_KDWL_CONFIG);
                dd.setSort(0);
                dictionaryDetailList.add(0, dd);
            }
            return Result.success(dictionaryDetailList).toJSON();
        }
        return Result.success(new ArrayList<>()).toJSON();
    }
}
