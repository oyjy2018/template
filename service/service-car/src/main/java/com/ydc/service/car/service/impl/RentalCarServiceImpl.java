package com.ydc.service.car.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarConditionMiddleVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarConditionVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarQueryDetailVO;
import com.ydc.commom.view.vo.cgj.rental.RentalCarQueryVO;
import com.ydc.dao.cgj.rental.RentalCarDao;
import com.ydc.dao.cgj.rental.RentalCarPublishDao;
import com.ydc.model.cgj.rental.RentalCar;
import com.ydc.model.cgj.rental.RentalCarPublish;
import com.ydc.service.car.service.RentalCarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalCarServiceImpl implements RentalCarService {

    private static final String SUCCESS_RESULT = "SUCCESS";

    @Resource
    private RentalCarDao rentalCarDao;
    @Resource
    private RentalCarPublishDao rentalCarPublishDao;

    private Logger logger = LogManager.getLogger(RentalCarServiceImpl.class);

    @Override
    public List<RentalCarQueryVO> getList(RentalCarQueryDTO rentalCarQueryDTO) {
        List<RentalCarQueryVO> carList = PaginationUtil.paginationQuery(rentalCarQueryDTO, (carQueryDTO) -> rentalCarDao.getList(carQueryDTO));
        carList.forEach(car -> {
            if (car.getStatus() != CommCarEnum.RentalCarStatusEnum.PUBLISH_SUCCESS.getCode() && car.getStatus() != CommCarEnum.RentalCarStatusEnum.RENT.getCode()) {
                car.setAvgRent(null);
            }
        });
        return carList;
    }

    @Override
    public RentalCarConditionVO getCondition() {
        List<RentalCarConditionMiddleVO> result = rentalCarDao.getCondition();
        if (!CollectionUtils.isEmpty(result)) {
            List<String> companyNameList = new ArrayList<>();
            List<String> brandList = new ArrayList<>();
            for (RentalCarConditionMiddleVO miniVo : result) {
                if (miniVo == null) {
                    continue;
                }
                if (StringUtil.isNotEmpty(miniVo.getCompanyName()) && !companyNameList.contains(miniVo.getCompanyName())) {
                    companyNameList.add(miniVo.getCompanyName());
                }
                if (StringUtil.isNotEmpty(miniVo.getBrand()) && !brandList.contains(miniVo.getBrand())) {
                    brandList.add(miniVo.getBrand());
                }
            }
            RentalCarConditionVO resultVo = new RentalCarConditionVO();
            resultVo.setCompanyNameList(companyNameList);
            resultVo.setBrandList(brandList);
            return resultVo;
        }
        return null;
    }

    @Override
    public RentalCarQueryDetailVO getDetail(Integer id) throws Exception {
        RentalCarQueryDetailVO rentalCarQueryDetailVO = rentalCarDao.getDetail(id);
        rentalCarQueryDetailVO.setDrivingLicenseImg(FileUtil.getBrowseFile(rentalCarQueryDetailVO.getDrivingLicenseImgUrl(), rentalCarQueryDetailVO.getDrivingLicenseImgName()));
        rentalCarQueryDetailVO.setModelImg(FileUtil.getBrowseFile(rentalCarQueryDetailVO.getModelImgUrl(), rentalCarQueryDetailVO.getModelImgName()));
        return rentalCarQueryDetailVO;
    }

    @Override
    public List<String> getSeriesByBrand(String brand) {
        return rentalCarDao.getSeriesByBrand(brand);
    }

    @Override
    public List<String> getModelBySeries(String series) {
        return rentalCarDao.getModelBySeries(series);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String removeRentalCar(JSONArray carIds, int userId) {
        for (Object obj : carIds) {
            int carId = (int) obj;
            //检测车辆状态是否是己发布
            RentalCar rentalCar = rentalCarDao.selectByPrimaryKey(carId);
            if (rentalCar.getStatus() != CommCarEnum.RentalCarStatusEnum.PUBLISH_SUCCESS.getCode()) {
                throw new ServiceRuntimeException("该车辆不是己发布状态，无法下架");
            }
            rentalCar.setId(carId);
            this.updateRentalCarStatus(userId, rentalCar, CommCarEnum.RentalCarStatusEnum.TO_PUBLISH);
            //更新发布表包含该车辆的最新发布记录的publishNum
            RentalCarPublish rentalCarPublish = rentalCarPublishDao.getLatestPublishData(carId);
            if (rentalCarPublish != null) {
                rentalCarPublish.setPublishNum(rentalCarPublish.getPublishNum() - 1);
                rentalCarPublish.setUpdateBy(userId);
                rentalCarPublish.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                rentalCarPublishDao.updateByPrimaryKeySelective(rentalCarPublish);
            }
            //更新t_rental_car_publish_num数量
            rentalCarPublishDao.updateMiddleNum(rentalCarPublish.getId(), rentalCar.getCarSeriesId(), rentalCar.getCarLevel());
        }
        return SUCCESS_RESULT;
    }

    @Override
    public List<String> getStoreNameByCompanyName(String companyName) {
        return rentalCarDao.getStoreNameByCompanyName(companyName);
    }

    /**
     * 更新车辆状态
     */
    private void updateRentalCarStatus(int companyId, RentalCar rentalCar, CommCarEnum.RentalCarStatusEnum publishSuccess) {
        rentalCar.setStatus(publishSuccess.getCode());
        rentalCar.setUpdateBy(companyId);
        rentalCar.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        rentalCarDao.updateByPrimaryKeySelective(rentalCar);
    }

}
