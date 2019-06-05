package com.ydc.cgj.app.service;

import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.model.cgj.DictionaryDetail;

/**
 * @author
 * @create 2018-10-22 16:27
 **/
public interface ServiceFuncService {

    /**
     * 查询serviceFuncList
     * @param
     * @return
     */
    String searchServiceFunc(String data);

    String searchServiceFuncShowHome();
}
