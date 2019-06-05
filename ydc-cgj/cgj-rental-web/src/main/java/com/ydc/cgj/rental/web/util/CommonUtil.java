package com.ydc.cgj.rental.web.util;

import com.ydc.beans.redis.DictionaryUtil;
import com.ydc.cgj.rental.web.service.DictionaryDetailService;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.MapUtil;
import com.ydc.model.cgj.DictionaryDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommonUtil {

    public static List<Map<String, Object>> getOptionListCH(String parentDictCode, Boolean nuulOption, DictionaryDetailService dictionaryDetailService){
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(parentDictCode)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(parentDictCode)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(parentDictCode));
            }
            ddList.addAll(MapUtil.packParamOptionCH(dictionaryDetailList));
            return ddList;
        }
        return new ArrayList<Map<String, Object>>();
    }

    public static List<Map<String, Object>> getOptionList(String parentDictCode, Boolean nuulOption, DictionaryDetailService dictionaryDetailService){
        Optional<List<DictionaryDetail>> optional = Optional.ofNullable(DictionaryUtil.getDictionaryDetailByParentDictCode(parentDictCode)
                .orElseGet(() -> dictionaryDetailService.getConfigInfoByParentDictCode(parentDictCode)));
        if (optional.isPresent()) {
            List<DictionaryDetail> dictionaryDetailList = optional.get();
            List<Map<String, Object>> ddList = new ArrayList<>();
            if (nuulOption != null && nuulOption) {
                ddList.add(MapUtil.getAllOption(parentDictCode));
            }
            ddList.addAll(MapUtil.packParamOption(dictionaryDetailList));
            return ddList;
        }
        return new ArrayList<Map<String, Object>>();
    }

}
