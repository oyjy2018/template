package com.ydc.service.sys.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.HomeModuleQueDTO;
import com.ydc.commom.view.vo.cgj.DictionaryDedailVO;
import com.ydc.commom.view.vo.cgj.ServiceFuncVO;
import com.ydc.dao.cgj.common.DictionaryDao;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.dao.cgj.service.ServiceFuncDao;
import com.ydc.dao.cgj.sys.CommImgDao;
import com.ydc.dao.cgj.sys.HomeModuleDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.ServiceFunc;
import com.ydc.model.cgj.sys.CommImg;
import com.ydc.model.cgj.sys.HomeModule;
import com.ydc.service.sys.controller.HomeModuleController;
import com.ydc.service.sys.service.HomeModuleService;
import com.ydc.service.sys.service.ServiceFuncService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hejiangping
 * @date 2018/12/27
 */
@Service
public class HomeModuleServiceImpl implements HomeModuleService {
    private static Logger logger = LogManager.getLogger(HomeModuleServiceImpl.class);
    @Autowired
    HomeModuleDao homeModuleDao;
    @Autowired
    DictionaryDetailDao dictionaryDetailDao;
    @Autowired
    DictionaryDao dictionaryDao;
    @Autowired
    ServiceFuncDao serviceFuncDao;
    @Autowired
    ServiceFuncService serviceFuncService;
    @Autowired
    CommImgDao commImgDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return homeModuleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(HomeModule record) {
        return homeModuleDao.insert(record);
    }

    @Override
    public int insertSelective(HomeModule record) {
        return homeModuleDao.insertSelective(record);
    }

    @Override
    public HomeModule selectByPrimaryKey(Integer id) {
        return homeModuleDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(HomeModule record) {
        return homeModuleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(HomeModule record) {
        return homeModuleDao.updateByPrimaryKey(record);
    }

    @Override
    public List<Map<String, Object>> getHomeModuleList(HomeModuleQueDTO homeModuleQueDTO) {
        return PaginationUtil.paginationQuery(homeModuleQueDTO, (temp) -> homeModuleDao.getHomeModuleList(temp));
    }

    @Override
    public Map<String, Object> getHomeModuleDetail(Integer id) {
        return homeModuleDao.getHomeModuleDetail(id);
    }

    @Override
    public List<Map<String, Object>> queryHomeModules(Integer sonModuleId) {
        return homeModuleDao.queryHomeModules(sonModuleId);
    }

    @Override
    public void writeRedis() {
        //获取服务列表
        try {
            List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(DictionaryConstant.SERVICE_CONFIG);
            //通过服务列表得到对应的功能列表
            List<DictionaryDetail> detailList = new ArrayList<>();
            detailList.addAll(dictionaryDetails);
            if(dictionaryDetails != null && dictionaryDetails.size() > 0){
                for ( DictionaryDetail detail : dictionaryDetails){
                    List<DictionaryDetail> details = dictionaryDetailDao.getConfigInfoByParentDictCode(detail.getDictKey());
                    detailList.addAll(details);
                }
            }
            List<ServiceFuncVO> detailVOList = new ArrayList<>();
            detailVOList = writeDictionaryDedailVO(detailList);

            logger.info("查询首页模块");

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
            jMap.put("serviceFuncVOList", detailVOList);
            reMap.put("data",jMap);
            String version = "V" + String.valueOf(DateUtil.timeStamp());
            reMap.put("version", version);
            RedisUtil.redisSet(RedisConstant.CGJ_SERVICE_VERSION, reMap, null);
            serviceFuncService.putVesionToRedis(2,version,true);
        }catch (Exception e){
            logger.error("subject:{},e:{}","返回APP或H5服务数据存redis失败",e);
        }
    }

    private List<ServiceFuncVO> writeDictionaryDedailVO(List<DictionaryDetail> detailList) {

        List<ServiceFuncVO> serviceList = new ArrayList<>();
        if (detailList != null && detailList.size() > 0) {
            for (DictionaryDetail detail : detailList) {
                Map<String, Object> map = new HashMap<>();
                ServiceFuncVO serviceFuncVO = new ServiceFuncVO();
                serviceFuncVO.setDictValue(detail.getDictValue());
                serviceFuncVO.setDictKey(detail.getDictKey());
                serviceFuncVO.setParentDictCode(detail.getParentDictCode());

                List<DictionaryDetail> details = dictionaryDetailDao.getConfigInfoByParentDictCode(detail.getDictKey());
                List<DictionaryDedailVO> dictionaryDetailList = new ArrayList<>();
                for (DictionaryDetail ddetail : details) {
                    //remark3 是否显示开关 1 显示
                    if (!"1".equals(ddetail.getRemark3())) {
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
                    dedailVO.setId(ddetail.getId());
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
        return serviceList;

    }

}
