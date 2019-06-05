package com.ydc.dao.cgj.car;

import com.ydc.model.cgj.car.CommCarImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommCarImgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CommCarImg record);

    int insertSelective(CommCarImg record);

    CommCarImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommCarImg record);

    int updateByPrimaryKey(CommCarImg record);

    void batchInsert(List<CommCarImg> carImgList);

    // 批量删除图片，软删
    int deleteByCarImgIds(String carImgIds);

    //根据车辆id获取图片
    List<CommCarImg> selectByCarId(Integer id);

    //根据车辆id  非图片ids 删除  软删
    int deleteByCarIdAndNotCarImgIds(Map param);
}