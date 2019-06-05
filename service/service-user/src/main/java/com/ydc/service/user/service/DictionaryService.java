package com.ydc.service.user.service;

import com.ydc.model.cgj.Dictionary;

import java.util.List;

/**
 * 数据字典
 *
 * @author gongjin
 * @create 2018-09-13 14:44
 **/
public interface DictionaryService {



    /**
     * 查询有效字典
     * @return
     */
    List<Dictionary> getValidDictionary();
}
