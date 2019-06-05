package com.ydc.cgj.ydhc.app.service.impl;

import com.ydc.cgj.ydhc.app.feignService.IDictionaryDetailService;
import com.ydc.cgj.ydhc.app.service.DictionaryDetailService;
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
