package com.ydc.service.user.controller;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.commom.view.vo.cgj.DictionaryDedailVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.service.user.service.DictionaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author gongjin
 * @create 2018-09-15 9:53
 **/
@RestController
@RequestMapping(value = "/dictionaryDetail")
public class DictionaryDetailController {

    @Autowired
    DictionaryDetailService dictionaryDetailService;

    /**
     * 获取数据集
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/getConfigInfoByParentDictCode")
    public List<DictionaryDetail> getConfigInfoByParentDictCode(@RequestParam("parentDictCode") String parentDictCode){
        List<DictionaryDetail> dictionaryDetailList = dictionaryDetailService.getConfigInfoByParentDictCode(parentDictCode);
        return dictionaryDetailList;
    }

    /**
     * 获取数据集
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/getDictionaryDetailByDictKey")
    public DictionaryDetail getDictionaryDetailByDictKey(@RequestParam("dictKey") String dictKey, @RequestParam("parentDictCode") String parentDictCode){
        DictionaryDetail dictionaryDetail = dictionaryDetailService.getDictionaryDetailByDictKey(dictKey,parentDictCode);
        return dictionaryDetail;
    }

    /**
     * 获取数据集
     * @param parentDictCode
     * @return
     */
    @PostMapping(value = "/getDictionaryDetailByDictValue")
    public DictionaryDetail getDictionaryDetailByDictValue(@RequestParam("dictValue") String dictValue,@RequestParam("parentDictCode") String parentDictCode){
        DictionaryDetail dictionaryDetail = dictionaryDetailService.getDictionaryDetailByDictValue(dictValue,parentDictCode);
        return dictionaryDetail;
    }


    /**
     * 获取H5数据配置
     * @return
     */
    @PostMapping(value = "/getH5Config")
    public Map<String,List<DictionaryDetail>> getH5Config(){
        return dictionaryDetailService.getH5Config();
    }



    /**
     * 保存服务类别
     * @param serviceFuncDTO
     * @return
     */
    @PostMapping(value = "/insertDictionaryDatail")
    public String insertDictionaryDatail(@RequestBody DictionaryDetailDTO dictionaryDetailDTO){
        String s = null;
        return dictionaryDetailService.insertDictionaryDatail(dictionaryDetailDTO)  <= 0 ? Result.failure("新增失败").toJSON() : Result.success("成功").toJSON();
    }

    /**
     * 更新服务类别
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/updateDictionaryDatail")
    public String updateDictionaryDatail(@RequestBody DictionaryDetailDTO dictionaryDetailDTO){
        return dictionaryDetailService.updateDictionaryDatail(dictionaryDetailDTO)  <= 0 ? Result.failure("更新失败").toJSON() : Result.success("成功").toJSON();
    }

    /**
     * 更新服务类别
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/getDictionaryDatail")
    public String getDictionaryDatail(@RequestBody DictionaryDetail dictionaryDetail){
        DictionaryDedailVO detail = dictionaryDetailService.getDictionaryDatail(dictionaryDetail);
        return Result.success(detail).toJSON();
    }


    /**
     * 更新服务类别
     * @param dictionaryDetail
     * @return
     */
    @PostMapping(value = "/delDictionaryDatail")
    public String delDictionaryDatail(@RequestBody DictionaryDetail dictionaryDetail){
        return dictionaryDetailService.delDictionaryDatail(dictionaryDetail)  <= 0 ? Result.failure("id不存在").toJSON() : Result.success("成功").toJSON();
    }
}