package com.ydc.service.sys.service;

import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.commom.view.vo.cgj.DictionaryDedailVO;
import com.ydc.commom.view.vo.cgj.ServiceConfigVO;
import com.ydc.commom.view.vo.cgj.ServiceFuncVO;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.ServiceFunc;

import java.util.List;
import java.util.Map;

public interface ServiceFuncService {

    void insertServiceFunc(ServiceFuncDTO serviceFuncDTO);

    void updateServiceFunc(ServiceFuncDTO serviceFuncDTO);

    void deleteServiceFunc(Integer id);

    Map<String, Object> searchServiceFunc(String data);

    List<ServiceConfigVO> searchAllServiceFunc(DictionaryDetail dictionaryDetail);

    List<ServiceFuncVO> searchServiceFuncShowHome();
    /**
     * 更新版本号到公共缓存,
     * @param type 1：广告；2：服务
     * @param vesion 版本号
     * @param isUpdateMainVersion 是否更新主版本号
     * @author: hejiangping
     * @date: 2019/1/5
     */
    void putVesionToRedis(Integer type,String vesion,boolean isUpdateMainVersion);

    List<Map<String,String>> getEnumList();
}
