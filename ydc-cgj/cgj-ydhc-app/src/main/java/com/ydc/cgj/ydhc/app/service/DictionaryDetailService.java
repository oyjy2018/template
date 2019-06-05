package com.ydc.cgj.ydhc.app.service;

import com.ydc.model.cgj.DictionaryDetail;

import java.util.List;

public interface DictionaryDetailService {

    /**
     * 根据parentCode获取DictionaryDetail
     *
     * @param parentDictCode
     * @return
     */
    List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode);
}
