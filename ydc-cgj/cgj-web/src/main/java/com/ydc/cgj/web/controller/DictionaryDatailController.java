package com.ydc.cgj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.cgj.web.service.DictionaryDetailService;
import com.ydc.commom.urlHttp.UrlHttpUtil;
import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.model.cgj.DictionaryDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dictionaryDetail")
public class DictionaryDatailController {

    private static final Logger logger = LogManager.getLogger(MemberController.class);

    @Autowired
    DictionaryDetailService dictionaryDetailService;

    /**
     * 新增服务类型
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/insertDictionaryDatail")
    public String insertDictionaryDatail(@RequestParam("data") String data) {
        logger.info("subject:{},DictionaryDatail:{}", "新增服务类型", data);
        DictionaryDetailDTO dictionaryDetail = JSONObject.parseObject(data,DictionaryDetailDTO.class);
        Integer managerId = WebShiroTokenManager.getUser().getId();

        dictionaryDetail.setCreateBy(managerId);
//        dictionaryDetail.setCreateBy(112);
        return dictionaryDetailService.insertDictionaryDatail(dictionaryDetail);
    }

    /**
     * 更新服务类型
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/updateDictionaryDatail")
    public String updateDictionaryDatail(@RequestParam("data") String data) {
        logger.info("subject:{},DictionaryDatail:{}", "更新服务类型", data);
        DictionaryDetailDTO dictionaryDetail = JSONObject.parseObject(data,DictionaryDetailDTO.class);
        Integer managerId = WebShiroTokenManager.getUser().getId();

        dictionaryDetail.setUpdateBy(managerId);
        return dictionaryDetailService.updateDictionaryDatail(dictionaryDetail);
    }


    /**
     * 更新服务类型
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/getDictionaryDatail")
    public String getDictionaryDatail(@RequestParam("data") String data) {
        logger.info("subject:{},DictionaryDatail:{}", "得到服务类型", data);
        DictionaryDetail dictionaryDetail = JSONObject.parseObject(data,DictionaryDetail.class);
        return dictionaryDetailService.getDictionaryDatail(dictionaryDetail);
    }

    /**
     * 更新服务类型
     *
     * @param data
     * @return
     */
    @PostMapping(value = "/delDictionaryDatail")
    public String delDictionaryDatail(@RequestParam("data") String data) {
        logger.info("subject:{},DictionaryDatail:{}", "得到服务类型", data);
        DictionaryDetail dictionaryDetail = JSONObject.parseObject(data,DictionaryDetail.class);
        Integer managerId = WebShiroTokenManager.getUser().getId();

        dictionaryDetail.setUpdateBy(managerId);
        return dictionaryDetailService.delDictionaryDatail(dictionaryDetail);
    }
}
