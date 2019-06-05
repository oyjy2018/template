package com.ydc.service.user.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.beans.mq.cache.service.CacheSendMessageService;
import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.DictionaryConstant;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.sys.DictionaryDetailDTO;
import com.ydc.commom.view.vo.cgj.DictionaryDedailVO;
import com.ydc.dao.cgj.common.DictionaryDetailDao;
import com.ydc.dao.cgj.sys.CommImgDao;
import com.ydc.dao.cgj.sys.HomeModuleDao;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.cgj.sys.CommImg;
import com.ydc.service.user.service.DictionaryDetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据字典明细
 *
 * @author gongjin
 * @create 2018-09-04 11:32
 **/
@Service
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    private static final Logger logger = LogManager.getLogger(DictionaryDetailService.class);

    @Autowired
    DictionaryDetailDao dictionaryDetailDao;
    @Resource
    CacheSendMessageService cacheSendMessageService;
    @Autowired
    CommImgDao commImgDao;
    @Autowired
    HomeModuleDao homeModuleDao;


    @Override
    public List<DictionaryDetail> getConfigInfoByParentDictCode(String parentDictCode) {
        List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(parentDictCode);
        logger.info("subject:{},parentDictCode:{},dictionaryDetails:{}", "缓存数据查询db", parentDictCode, JsonUtil.gsonStr(dictionaryDetails));
        cacheSendMessageService.setCacheMessage(dictionaryDetails, parentDictCode);
        return dictionaryDetails;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictKey(String dictKey, String parentDictCode) {
        DictionaryDetail dictionaryDetail = dictionaryDetailDao.getDictionaryDetailByDictKey(dictKey, parentDictCode);
        logger.info("subject:{},dictKey：{},parentDictCode:{},dictionaryDetails:{}", "缓存数据查询db", dictKey, parentDictCode, JsonUtil.gsonStr(dictionaryDetail));
        List<DictionaryDetail> dictionaryDetails = Lists.newArrayList();
        dictionaryDetails.add(dictionaryDetail);
        cacheSendMessageService.setCacheMessage(dictionaryDetails, parentDictCode);
        return dictionaryDetail;
    }

    @Override
    public DictionaryDetail getDictionaryDetailByDictValue(String dictValue, String parentDictCode) {
        DictionaryDetail dictionaryDetail = dictionaryDetailDao.getDictionaryDetailByDictValue(dictValue, parentDictCode);
        logger.info("subject:{},dictValue：{},parentDictCode:{},dictionaryDetails:{}", "缓存数据查询db", dictValue, parentDictCode, JsonUtil.gsonStr(dictionaryDetail));
        List<DictionaryDetail> dictionaryDetails = Lists.newArrayList();
        dictionaryDetails.add(dictionaryDetail);
        cacheSendMessageService.setCacheMessage(dictionaryDetails, parentDictCode);
        return dictionaryDetail;
    }

    @Override
    public Map<String, List<DictionaryDetail>> getH5Config() {
        Map<String, List<DictionaryDetail>> dictionaryMaps = dictionaryDetailDao.getH5Config().stream().collect(Collectors.groupingBy(DictionaryDetail::getParentDictCode));
        return dictionaryMaps;
    }

    @Override
    public Integer insertDictionaryDatail(DictionaryDetailDTO dictionaryDetailDTO) {
        logger.info("dictionaryDetail", dictionaryDetailDTO);
        DictionaryDetail dietail = new DictionaryDetail();
        String parentDictCode;
        if (StringUtil.isEmpty(dictionaryDetailDTO.getParentDictCode())) {
            //服务添加
            parentDictCode = DictionaryConstant.SERVICE_CONFIG;
            List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(parentDictCode);
            if (dictionaryDetails == null || dictionaryDetails.size() == 0) {
                dietail.setDictKey(DictionaryConstant.SERVICE_TYPE + "1");
                dietail.setSort(1);
            } else {
                Integer num = dictionaryDetails.size() + 1;
                dietail.setDictKey(DictionaryConstant.SERVICE_TYPE + num);
                dietail.setSort(num);
            }
            dietail.setRemark4("APP");
            dietail.setParentDictCode(DictionaryConstant.SERVICE_CONFIG);

        } else {
            //功能添加

            parentDictCode = dictionaryDetailDTO.getParentDictCode();
            List<DictionaryDetail> dictionaryDetails = dictionaryDetailDao.getConfigInfoByParentDictCode(parentDictCode);
            if (parentDictCode.contains(DictionaryConstant.SERVICE_TYPE)) {
                if (dictionaryDetails == null || dictionaryDetails.size() == 0) {
                    dietail.setDictKey(DictionaryConstant.FUNCTION_TYPE + "1");
                    dietail.setSort(1);
                } else {
                    Integer num = dictionaryDetails.size() + 1;
                    dietail.setDictKey(DictionaryConstant.FUNCTION_TYPE + num);
                    dietail.setSort(num);
                }
                //  存备注
                dietail.setRemark1(dictionaryDetailDTO.getRemark1());
                //存app跳转链接
                dietail.setRemark2(dictionaryDetailDTO.getRemark2());
                //存H5跳转链接
                dietail.setRemark6(dictionaryDetailDTO.getRemark6());
                //存是否显示
                dietail.setRemark3(dictionaryDetailDTO.getRemark3());
                dietail.setParentDictCode(parentDictCode);
            }
            //车圈配置新增类别
            if (DictionaryConstant.DICT_CODE_CGJ_CARZONE.equals(dictionaryDetailDTO.getParentDictCode())) {
                if (dictionaryDetails == null || dictionaryDetails.size() == 0) {
                    dietail.setDictKey(DictionaryConstant.DICT_CODE_CGJ_CARZONE + "1");
                    dietail.setSort(1);
                } else {
                    Integer num = dictionaryDetails.size() + 1;
                    dietail.setDictKey(DictionaryConstant.DICT_CODE_CGJ_CARZONE + num);
                    dietail.setSort(num);
                }
                dietail.setRemark3(dictionaryDetailDTO.getRemark3());
                dietail.setParentDictCode(dictionaryDetailDTO.getParentDictCode());

            }

        }
        dietail.setCreateTime(DateUtil.getNewTimestamp());
        dietail.setUpdateTime(DateUtil.getNewTimestamp());
        dietail.setCreateBy(dictionaryDetailDTO.getCreateBy());
        dietail.setUpdateBy(dictionaryDetailDTO.getCreateBy());
        dietail.setDictValue(dictionaryDetailDTO.getDictValue());
        dietail.setRemark1(dictionaryDetailDTO.getDictValue());
        dietail.setStatus("1");
        Integer num = dictionaryDetailDao.insert(dietail);
        if (dictionaryDetailDTO.getCommImg() != null) {
            CommImg commImg = dictionaryDetailDTO.getCommImg();
            commImg.setCommId(dietail.getId());
            commImg.setImgType(2);
            commImg.setStatus(1);
            Integer imgNum = commImgDao.insert(commImg);
        }

        return num;
    }

    @Override
    public Integer updateDictionaryDatail(DictionaryDetailDTO dictionaryDetailDTO) {
        DictionaryDetail dictionaryDetail = new DictionaryDetail();
        dictionaryDetail.setUpdateTime(DateUtil.getNewTimestamp());
        dictionaryDetail.setUpdateBy(dictionaryDetailDTO.getUpdateBy());
        dictionaryDetail.setId(dictionaryDetailDTO.getId());
        dictionaryDetail.setRemark3(dictionaryDetailDTO.getRemark3());
        dictionaryDetail.setRemark4(dictionaryDetailDTO.getRemark4());
        dictionaryDetail.setRemark6(dictionaryDetailDTO.getRemark6());
        dictionaryDetail.setDictValue(dictionaryDetailDTO.getDictValue());
        dictionaryDetail.setDictKey(dictionaryDetailDTO.getDictKey());
        dictionaryDetail.setRemark1(dictionaryDetailDTO.getRemark1());
        dictionaryDetail.setRemark2(dictionaryDetailDTO.getRemark2());
        dictionaryDetail.setParentDictCode(dictionaryDetailDTO.getParentDictCode());

        Integer num = dictionaryDetailDao.updateByPrimaryKeySelective(dictionaryDetail);
        if (dictionaryDetailDTO.getCommImg() != null) {
            CommImg commImg = dictionaryDetailDTO.getCommImg();
            commImg.setStatus(1);
            commImgDao.updateByPrimaryKeySelective(dictionaryDetailDTO.getCommImg());
        }

        return num;
    }

    @Override
    public DictionaryDedailVO getDictionaryDatail(DictionaryDetail dictionaryDetail) {
        DictionaryDedailVO dedailVO = new DictionaryDedailVO();
        DictionaryDetail detail = dictionaryDetailDao.selectByPrimaryKey(dictionaryDetail.getId());
        CommImg commImg = commImgDao.selectByCommIdAndType(dictionaryDetail.getId(), 2);
        dedailVO.setDictKey(detail.getDictKey());
        dedailVO.setRemark6(detail.getRemark6());
        dedailVO.setRemark5(detail.getRemark5());
        dedailVO.setRemark4(detail.getRemark4());
        dedailVO.setRemark3(detail.getRemark3());
        dedailVO.setRemark2(detail.getRemark2());
        dedailVO.setRemark1(detail.getRemark1());
        dedailVO.setParentDictCode(detail.getParentDictCode());
        dedailVO.setDictValue(detail.getDictValue());
        dedailVO.setId(detail.getId());
        if (commImg != null) {
            try {

                dedailVO.setFileUrl(FileUtil.getBrowseFile(commImg.getFileUrl(), commImg.getFileName()));


                dedailVO.setLittleFileUrl(FileUtil.getBrowseFile(commImg.getLittleFileUrl(), commImg.getLittleFileName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            dedailVO.setImgId(commImg.getId());


        }
        return dedailVO;
    }



    @Override
    public Integer delDictionaryDatail(DictionaryDetail dictionaryDetail) {
        DictionaryDetail detail = dictionaryDetailDao.selectByPrimaryKey(dictionaryDetail.getId());
        if(detail == null){
            return -1;
        }
        dictionaryDetail.setStatus("0");
        dictionaryDetail.setUpdateTime(DateUtil.getNewTimestamp());
        Integer num = dictionaryDetailDao.updateByPrimaryKeySelective(dictionaryDetail);
        return num;
    }

}
