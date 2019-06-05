package com.ydc.cgj.web.service;

import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.model.cgj.DictionaryDetail;

/**
 * @author
 * @create 2018-10-22 16:27
 **/
public interface ServiceFuncService {

    /**
     * 新增serviceFunc
     * @param serviceFuncDTO
     * @return
     */
    String insertServiceFunc(DictionaryDetailDTO dictionaryDetailDTO);

    /**
     * 更新serviceFunc
     * @param serviceFuncDTO
     * @return
     */
    String updateServiceFunc(DictionaryDetailDTO dictionaryDetailDTO);

    /**
     * 更新serviceFunc
     * @param id
     * @return
     */
    String daleteServiceFunc(Integer id);

    /**
     * 查询serviceFuncList
     * @param dictionaryDetail
     * @return
     */
    String searchServiceFunc(DictionaryDetail dictionaryDetail);

    /**
     * 查询serviceFuncList
     * @param dictionaryDetail
     * @return
     */
    String searchAllServiceFunc(DictionaryDetail dictionaryDetail);

    String searchServiceFuncShowHome();

    String getEnumList();

    String writeRedis();
}
