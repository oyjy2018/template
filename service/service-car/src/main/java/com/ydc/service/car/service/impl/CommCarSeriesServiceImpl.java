package com.ydc.service.car.service.impl;

import com.ydc.beans.file.FileUtil;
import com.ydc.commom.result.Result;
import com.ydc.commom.util.LoggerUtil;
import com.ydc.commom.view.vo.cgj.rental.CommCarSeriesVO;
import com.ydc.dao.cgj.car.CommCarSeriesDAO;
import com.ydc.model.cgj.car.CommCarSeries;
import com.ydc.service.car.service.CommCarSeriesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommCarSeriesServiceImpl implements CommCarSeriesService {

    private static Logger logger = LogManager.getLogger(CommCarSeriesServiceImpl.class);

    @Autowired
    private CommCarSeriesDAO commCarSeriesDAO;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commCarSeriesDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CommCarSeries record) {
        return commCarSeriesDAO.insert(record);
    }

    @Override
    public int insertSelective(CommCarSeries record) {
        return commCarSeriesDAO.insertSelective(record);
    }

    @Override
    public CommCarSeries selectByPrimaryKey(Integer id) {
        CommCarSeries ccs = commCarSeriesDAO.selectByPrimaryKey(id);
        try {
            ccs.setViewMainImgUrl(FileUtil.getBrowseFile(ccs.getMainImgUrl(), ccs.getMainImgName()));
        } catch (Exception e) {
            LoggerUtil.error(logger, e, "获取车系车型图片在线浏览地址异常");
        }
        return ccs;
    }

    @Override
    public int updateByPrimaryKeySelective(CommCarSeries record) {
        return commCarSeriesDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommCarSeries record) {
        return commCarSeriesDAO.updateByPrimaryKey(record);
    }

    @Override
    public CommCarSeries saveOrUpdate(Map<String, Object> req) {
        Map<String, String> commCarSeries = (Map<String, String>)req.get("commCarSeries");
        CommCarSeries ccs = new CommCarSeries(commCarSeries);
        Integer userId = Integer.valueOf(req.get("userId").toString());
        Date sysDate = new Date();

        ccs.setUpdateBy(userId);
        if(ccs.getId() == null || ccs.getId() == 0){
            ccs.setCreateBy(userId);
            ccs.setCreateTime(sysDate);
            insertSelective(ccs);
        }else{
            updateByPrimaryKeySelective(ccs);
        }
        return ccs;
    }

    @Override
    public List<CommCarSeriesVO> getCommCarSeriesList(Map<String, Object> param) {
        List<CommCarSeriesVO> commCarSeriesList = commCarSeriesDAO.getCommCarSeriesList(param);
        for(CommCarSeriesVO commCarSeries:commCarSeriesList){
            try {
                commCarSeries.setViewMainImgUrl(FileUtil.getBrowseFile(commCarSeries.getMainImgUrl(), commCarSeries.getMainImgName()));
            } catch (Exception e) {
                LoggerUtil.error(logger, e, "获取图片在线浏览地址异常");
            }
        }
        return commCarSeriesList;
    }

    @Override
    public Map<String, Object> getCommCarSeriesCount(Map<String, Object> param) {
        return commCarSeriesDAO.getCommCarSeriesCount(param);
    }

    @Override
    public int updateHasEnabledById(Integer commCarSeriesId, Integer hasEnabled, Integer updateBy) {
        return commCarSeriesDAO.updateHasEnabledById(commCarSeriesId,hasEnabled,updateBy);
    }

    /**
     * 查询所有启用车辆品牌
     * @return
     */
    @Override
    public String getAllEnableBrand() {
        List<Map<String,Object>> enableBrandList = commCarSeriesDAO.getAllEnableBrand();
        return Result.success(enableBrandList).toJSON();
    }

    /**
     * 通过品牌查询启用车系
     *
     * @param req
     * @return
     */
    @Override
    public String getEnableSeriesByBrand(Map<String, Object> req) {
        return Result.success(commCarSeriesDAO.getEnableSeriesByBrand(req)).toJSON();
    }

    /**
     * 通过车系查询启用车型
     *
     * @param req
     * @return
     */
    @Override
    public String getEnableModelBySeries(Map<String, Object> req) {
        return Result.success(commCarSeriesDAO.getEnableModelBySeries(req)).toJSON();
    }

    @Override
    public List<CommCarSeries> getCarSeriesByParam(Map<String, Object> param) {
        return commCarSeriesDAO.getCarSeriesByParam(param);
    }

    /**
     * 通过车型id获取邮箱容量
     * @param modelId
     * @return
     */
    @Override
    public String getTankVolumeByModelId(Integer modelId) {
        Map retMap = commCarSeriesDAO.getTankVolumeByModelId(modelId);
        if (retMap == null) {
            return Result.failure("未查询到车型信息").toJSON();
        }
        return Result.success(retMap).toJSON();
    }
}
