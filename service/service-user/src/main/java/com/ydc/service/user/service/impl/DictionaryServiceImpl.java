package com.ydc.service.user.service.impl;

import com.ydc.dao.cgj.common.DictionaryDao;
import com.ydc.model.cgj.Dictionary;
import com.ydc.service.user.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gongjin
 * @create 2018-09-13 14:45
 **/
@Service
public class DictionaryServiceImpl implements DictionaryService {


    @Autowired
    DictionaryDao dictionaryDao;

    @Override
    public List<Dictionary> getValidDictionary() {
        return dictionaryDao.getValidDictionary();
    }
}
