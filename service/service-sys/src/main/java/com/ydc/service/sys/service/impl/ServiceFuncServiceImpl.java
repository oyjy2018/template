package com.ydc.service.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.beans.shiro.web.WebShiroTokenManager;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.ServiceFuncDTO;
import com.ydc.commom.view.vo.cgj.DictionaryDedailVO;
import com.ydc.commom.view.vo.cgj.ServiceConfigVO;
import com.ydc.commom.view.vo.cgj.ServiceFuncVO;
import com.ydc.dao.cgj.common.DictionaryDao;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.dao.cgj.service.ServiceFuncDao;
import com.ydc.dao.cgj.sys.CommImgDao;
import com.ydc.dao.cgj.sys.HomeModuleDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.ServiceFunc;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.sys.CommImg;
import com.ydc.service.sys.service.HomeModuleService;
import com.ydc.service.sys.service.ServiceFuncService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ServiceFuncServiceImpl implements ServiceFuncService {

    private static Logger logger = LogManager.getLogger(ServiceFuncServiceImpl.class);
    @Autowired
    CommImgDao commImgDao;
    @Autowired
    ServiceFuncDao serviceFuncDao;
    @Autowired
    DictionaryDetailDao dictionaryDetailDao;
    @Autowired
    DictionaryDao dictionaryDao;
    @Autowired
    HomeModuleDao homeModuleDao;
    @Autowired
    HomeModuleService homeModuleService;

    /**
     * 新增服务功能
     *
     * @param serviceFuncDTO
     */
    @Override
    public void insertServiceFunc(ServiceFuncDTO serviceFuncDTO) {
        ServiceFunc serviceFunc = new ServiceFunc();

        serviceFunc.setCreateBy(serviceFuncDTO.getCreateBy());
        serviceFunc.setUpdateBy(serviceFuncDTO.getUpdateBy());
        serviceFunc.setFuncName(serviceFuncDTO.getFuncName());
        serviceFunc.setDeleteStatus(1);
        serviceFunc.setUrl(serviceFuncDTO.getUrl());
        serviceFunc.setServiceId(serviceFuncDTO.getServiceId());
        serviceFunc.setRemark(serviceFuncDTO.getRemark());
        serviceFunc.setShowType(serviceFuncDTO.getShowType());
        serviceFunc.setCreateTime(DateUtil.getNewTimestamp());
        serviceFunc.setUpdateTime(DateUtil.getNewTimestamp());
        CommImg commImg = serviceFuncDTO.getFile();
        Integer id = serviceFuncDao.insert(serviceFunc);
        commImg.setCommId(id);
        commImg.setImgType(2);
        commImg.setStatus(1);
        commImg.setCreateBy(serviceFuncDTO.getCreateBy());
        commImg.setUpdateBy(serviceFuncDTO.getUpdateBy());
        commImg.setCreateTime(DateUtil.getNewTimestamp());
        commImg.setUpdateTime(DateUtil.getNewTimestamp());
        commImgDao.insert(commImg);
        homeModuleService.writeRedis();
    }


    /**
     * 更新服务功能
     *
     * @param serviceFuncDTO
     */
    @Override
    public void updateServiceFunc(ServiceFuncDTO serviceFuncDTO) {
        ServiceFunc serviceFunc = new ServiceFunc();
        User user = WebShiroTokenManager.getUser();
        serviceFunc.setUpdateBy(user.getId());
        serviceFunc.setFuncName(serviceFuncDTO.getFuncName());
        serviceFunc.setUrl(serviceFuncDTO.getUrl());
        serviceFunc.setServiceId(serviceFuncDTO.getServiceId());
        serviceFunc.setRemark(serviceFuncDTO.getRemark());
        serviceFunc.setShowType(serviceFuncDTO.getShowType());
        serviceFunc.setUpdateTime(DateUtil.getNewTimestamp());
        serviceFuncDao.updateByPrimaryKey(serviceFunc);
        CommImg commImg = serviceFuncDTO.getFile();
        if (commImg == null) {
            commImg.setUpdateBy(serviceFuncDTO.getUpdateBy());
            commImg.setUpdateTime(DateUtil.getNewTimestamp());
            commImgDao.updateByPrimaryKey(commImg);
        }
        homeModuleService.writeRedis();
    }


    /**
     * 删除服务功能
     *
     * @param id
     */
    @Override
    public void deleteServiceFunc(Integer id) {
        ServiceFunc serviceFunc = new ServiceFunc();
        User user = WebShiroTokenManager.getUser();
        serviceFunc.setUpdateBy(user.getId());
        serviceFunc.setUpdateTime(DateUtil.getNewTimestamp());
        serviceFunc.setId(id);
        serviceFunc.setDeleteStatus(0);
        serviceFuncDao.updateByPrimaryKey(serviceFunc);
        homeModuleService.writeRedis();
    }


    /**
     * 查询服务列表
     *
     * @param dictionaryDetail
     * @return
     */
    @Override
    public List<ServiceConfigVO> searchAllServiceFunc(DictionaryDetail dictionaryDetail) {
        if (StringUtil.isEmpty(dictionaryDetail.getParentDictCode())) {
            dictionaryDetail.setParentDictCode(DictionaryConstant.SERVICE_CONFIG);
        }
        //获取服务列表
        List<DictionaryDetail> dictionaryDetails = new ArrayList<DictionaryDetail>();
        List<ServiceConfigVO> configVOS = new ArrayList<>();
        if (StringUtil.isEmpty(dictionaryDetail.getDictKey()) || "".equals(dictionaryDetail.getDictKey())) {
            dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(dictionaryDetail.getParentDictCode());
        } else {
            DictionaryDetail detail = dictionaryDetailDao.getDictionaryDetailByDictKey(dictionaryDetail.getDictKey(), dictionaryDetail.getParentDictCode());
            dictionaryDetails.add(detail);
        }
        //通过服务列表得到对应的功能列表
        List<DictionaryDetail> detailList = new ArrayList<>();
        detailList.addAll(dictionaryDetails);
        if (dictionaryDetails != null && dictionaryDetails.size() > 0) {
            for (DictionaryDetail detail : dictionaryDetails) {
                List<DictionaryDetail> details = dictionaryDetailDao.getConfigInfoByParentDictCode(detail.getDictKey());
                if (details != null && details.size() > 0) {
                    for (DictionaryDetail ddetail : details) {
                        ServiceConfigVO configVO = new ServiceConfigVO();
                        //                    //  存备注
                        //                    dietail.setRemark1(dictionaryDetailDTO.getRemark1());
                        //                    //存跳转链接
                        //                    dietail.setRemark2(dictionaryDetailDTO.getRemark2());
                        //                    //存是否显示
                        //                    dietail.setRemark3(dictionaryDetailDTO.getRemark3());
                        configVO.setFuncId(ddetail.getId());
                        configVO.setFunckey(ddetail.getDictKey());
                        configVO.setFuncName(ddetail.getDictValue());
                        configVO.setFuncRemark(ddetail.getRemark1());
                        configVO.setAppUrl(ddetail.getRemark2());
                        configVO.setH5Url(ddetail.getRemark6());
                        configVO.setShowType(ddetail.getRemark3());
                        configVO.setFuncParkey(detail.getDictKey());
                        configVO.setServerSide("APP");
                        configVO.setServicekey(detail.getDictKey());
                        configVO.setServiceName(detail.getDictValue());
                        configVO.setServiceParkey(DictionaryConstant.SERVICE_CONFIG);
                        configVO.setModel("服务");
                        configVO.setServiceId(detail.getId());
                        CommImg commImg = commImgDao.selectByCommIdAndType(ddetail.getId(), 2);
                        if (commImg != null) {

                            try {
                                configVO.setFuncImg(FileUtil.getBrowseFile(commImg.getFileUrl(), commImg.getFileName()));
                                configVO.setImgId(commImg.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        configVO.setFuncSort(ddetail.getSort());
                        configVO.setServiceParkey(detail.getParentDictCode());
                        configVOS.add(configVO);
                    }
                } else {
                    ServiceConfigVO configVO = new ServiceConfigVO();
                    configVO.setServerSide("APP");
                    configVO.setServicekey(detail.getDictKey());
                    configVO.setServiceName(detail.getDictValue());
                    configVO.setServiceParkey(DictionaryConstant.SERVICE_CONFIG);
                    configVO.setModel("服务");
                    configVO.setServiceId(detail.getId());
                    configVOS.add(configVO);
                }
            }
        }
//        List<DictionaryDedailVO> detailVOList = new ArrayList<>();
//        detailVOList = writeDictionaryDedailVO(detailList);
        return configVOS;
    }

    @Override
    public Map<String, Object> searchServiceFunc(String data) {
//        if(StringUtil.isEmpty(dictionaryDetail.getParentDictCode())){
//            dictionaryDetail.setParentDictCode(DictionaryConstant.SERVICE_CONFIG);
//        }
        JSONObject json = JSONObject.parseObject(data);
        if (json != null && json.containsKey("version") && StringUtil.isNotEmpty(json.get("version")) && !"0".equals(json.get("version"))) {
            String appVersion = json.get("version").toString();
            Object preCode = RedisUtil.redisGet(RedisConstant.CGJ_SERVICE_VERSION);
            if (preCode != null) {
                Map<String, Object> map = (Map<String, Object>) preCode;
                if (appVersion.equals(map.get("version")) && StringUtil.isNotEmpty(map.get("version"))) {
                    return null;
                } else if (StringUtil.isNotEmpty(map.get("version"))) {
                    putVesionToRedis(2, map.get("version").toString(), false);
                    return map;
                }

            }
        }
        //获取服务列表
        List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(DictionaryConstant.SERVICE_CONFIG);

        //通过服务列表得到对应的功能列表
//        List<DictionaryDetail> detailList = new ArrayList<>();
//        detailList.addAll(dictionaryDetails);
        List<ServiceFuncVO> serviceList = new ArrayList<>();
        if (dictionaryDetails != null && dictionaryDetails.size() > 0) {

            for (DictionaryDetail detail : dictionaryDetails) {


                Map<String, Object> map = new HashMap<>();
                ServiceFuncVO serviceFuncVO = new ServiceFuncVO();
                serviceFuncVO.setDictValue(detail.getDictValue());
                serviceFuncVO.setDictKey(detail.getDictKey());
                serviceFuncVO.setParentDictCode(detail.getParentDictCode());

                List<DictionaryDetail> details = dictionaryDetailDao.getConfigInfoByParentDictCode(detail.getDictKey());
                List<DictionaryDedailVO> dictionaryDetailList = new ArrayList<>();
                for (DictionaryDetail ddetail : details) {
                    //remark3 是否显示开关 1 显示
                    if(!"1".equals(ddetail.getRemark3())){
                        continue;
                    }
                    DictionaryDedailVO dedailVO = new DictionaryDedailVO();
                    dedailVO.setDictValue(ddetail.getDictValue());
                    dedailVO.setParentDictCode(ddetail.getParentDictCode());
                    dedailVO.setStatus(ddetail.getStatus());
                    dedailVO.setSort(ddetail.getSort());
                    dedailVO.setRemark1(ddetail.getRemark1());
                    dedailVO.setRemark2(ddetail.getRemark2());
                    dedailVO.setRemark3(ddetail.getRemark3());
                    dedailVO.setRemark4(ddetail.getRemark4());
                    dedailVO.setRemark5(ddetail.getRemark5());
                    dedailVO.setRemark6(ddetail.getRemark6());
                    dedailVO.setDictKey(ddetail.getDictKey());

                    CommImg commimg = commImgDao.selectByCommIdAndType(ddetail.getId(), 2);
                    if (commimg != null) {
                        try {
                            dedailVO.setImgType(commimg.getImgType());
                            dedailVO.setFileUrl(FileUtil.getBrowseFile(commimg.getFileUrl(), commimg.getFileName()));
                            dedailVO.setFileName(commimg.getFileName());

                            dedailVO.setLittleFileUrl(FileUtil.getBrowseFile(commimg.getLittleFileUrl(), commimg.getLittleFileName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dedailVO.setLittleFileName(commimg.getLittleFileName());
                    }

                    dictionaryDetailList.add(dedailVO);
                }
//                map.put("serviceFuncList",dictionaryDetailList);
                serviceFuncVO.setServiceFuncList(dictionaryDetailList);
                serviceList.add(serviceFuncVO);
            }
        }
//        List<DictionaryDedailVO> detailVOList = new ArrayList<>();
//        detailVOList = writeDictionaryDedailVO(detailList);

        // 服务模块homeModuleDao.queryHomeModules(sonModuleId)
        List<Map<String, Object>> serviceModules = homeModuleDao.queryHomeModules(DictionaryConstant.HOME_MODULE_1);
        for (Map<String, Object> img : serviceModules) {
            try {
                if (StringUtil.isNotEmpty(img.get("fileName"))) {
                    img.put("fileUrl", FileUtil.getBrowseFile(img.get("fileUrl").toString(), img.get("fileName").toString()));
                }
            } catch (Exception e) {
                logger.error("subject:{},e:{}", "获取图片地址异常", e);
            }
        }
        // 产品模块
        List<Map<String, Object>> productModules = homeModuleDao.queryHomeModules(DictionaryConstant.HOME_MODULE_2);
        for (Map<String, Object> img : productModules) {
            try {
                if (StringUtil.isNotEmpty(img.get("fileName"))) {
                    img.put("fileUrl", FileUtil.getBrowseFile(img.get("fileUrl").toString(), img.get("fileName").toString()));
                }
            } catch (Exception e) {
                logger.error("subject:{},e:{}", "获取图片地址异常", e);
            }
        }

        Map<String, Object> jMap = new HashMap<>();
        Map<String, Object> reMap = new HashMap<>();
        jMap.put("serviceModules", serviceModules);
        jMap.put("productModules", productModules);
        jMap.put("serviceFuncVOList", serviceList);
        reMap.put("data", jMap);
        String version = "V" + String.valueOf(DateUtil.timeStamp());
        reMap.put("version", version);
        RedisUtil.redisSet(RedisConstant.CGJ_SERVICE_VERSION, reMap, null);
        putVesionToRedis(2, version, true);
        return reMap;
    }

    @Override
    public List<ServiceFuncVO> searchServiceFuncShowHome() {
//        //获取服务列表
//        List<DictionaryDetail> dictionaryDetails = new ArrayList<DictionaryDetail>();
//        DictionaryDetail detail= dictionaryDetailDao.getDictionaryDetailByDictKey("1",DictionaryConstant.DICT_CODE_HOME_MODULE);
//        DictionaryDetail detail1= dictionaryDetailDao.getDictionaryDetailByDictKey("2",DictionaryConstant.DICT_CODE_HOME_MODULE);
//        dictionaryDetails.add(detail);
//        dictionaryDetails.add(detail1);
//        //通过服务列表得到对应的功能列表
//        List<ServiceFuncVO> serviceFuncVOList = new ArrayList<>();
//        if(dictionaryDetails != null && dictionaryDetails.size() > 0){
//            for ( DictionaryDetail dictionaryDetail :dictionaryDetails){
//                ServiceFuncVO serviceFuncVO = new ServiceFuncVO();
//                serviceFuncVO.setDictKey(dictionaryDetail.getDictKey());
//                serviceFuncVO.setDictValue(dictionaryDetail.getDictValue());
//                serviceFuncVO.setParentDictCode(dictionaryDetail.getParentDictCode());
//                List<ServiceFunc> serviceFuncList = serviceFuncDao.getServiceFuncListByServiceId(dictionaryDetail.getId());
//                serviceFuncVO.setServiceFuncList(serviceFuncList);
//                serviceFuncVOList.add(serviceFuncVO);
//            }
//        }
        return null;
    }

    @Override
    public void putVesionToRedis(Integer type, String vesion, boolean isUpdateMainVersion) {
        Map<String, Object> commonVersion = (Map<String, Object>) RedisUtil.redisGet(RedisConstant.CGJ_COMMON_VERSION);
        String version = null;
        if (isUpdateMainVersion) {
            version = "V" + String.valueOf(DateUtil.timeStamp());
        }
        if (commonVersion == null) {
            version = "V" + String.valueOf(DateUtil.timeStamp());
            commonVersion = new HashMap<>();
        }
        if (StringUtil.isNotEmpty(version)) {
            commonVersion.put("version", version);
        }
        Map<String, Object> versionData = (Map<String, Object>) commonVersion.get("versionData");
        if (versionData == null) {
            versionData = new HashMap<>();
        }
        // 1：广告；2：服务
        if (type.equals(RedisConstant.CGJ_VERSION_TYPE_1)) {
            versionData.put("advertVersion", vesion);
        } else if (type.equals(RedisConstant.CGJ_VERSION_TYPE_2)) {
            versionData.put("serviceVersion", vesion);
        }
        commonVersion.put("versionData", versionData);
        RedisUtil.redisSet(RedisConstant.CGJ_COMMON_VERSION, commonVersion, null);
    }

    @Override
    public List<Map<String, String>> getEnumList() {
        List<Map<String, String>> resList = new ArrayList<>();
        List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(DictionaryConstant.SERVICE_CONFIG);
        for (DictionaryDetail dictionary : dictionaryDetails) {
            Map<String, String> typeMap = new HashMap<>();
            typeMap.put("dictKey", dictionary.getDictKey());
            typeMap.put("dictValue", dictionary.getDictValue());
            resList.add(typeMap);
        }
        return resList;
    }



}
