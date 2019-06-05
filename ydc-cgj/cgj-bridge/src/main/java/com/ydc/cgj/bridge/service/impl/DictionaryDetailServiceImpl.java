package com.ydc.cgj.bridge.service.impl;

import com.ydc.cgj.bridge.feignService.IDictionaryDetailService;
import com.ydc.cgj.bridge.service.DictionaryDetailService;
import com.ydc.model.cgj.DictionaryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    @Autowired
    private IDictionaryDetailService dictionaryDetailService;

    @Override
    public List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode) {
        return dictionaryDetailService.getConfigInfoByParentDictCode(parentDictCode);
    }
}
