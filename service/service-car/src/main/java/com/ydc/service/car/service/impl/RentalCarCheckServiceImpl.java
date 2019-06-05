package com.ydc.service.car.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.beans.file.FileUtil;
import com.ydc.commom.enums.rental.CommCarEnum;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarCheckQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.*;
import com.ydc.dao.cgj.rental.RentalCarCheckDao;
import com.ydc.dao.cgj.rental.RentalCarDao;
import com.ydc.model.cgj.rental.RentalCar;
import com.ydc.model.cgj.rental.RentalCarCheck;
import com.ydc.service.car.service.RentalCarCheckService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentalCarCheckServiceImpl implements RentalCarCheckService {

    private static final String SUCCESS_RESULT = "SUCCESS";

    @Resource
    private RentalCarCheckDao rentalCarCheckDao;
    @Resource
    private RentalCarDao rentalCarDao;

    private Logger logger = LogManager.getLogger(RentalCarCheckServiceImpl.class);

    @Override
    public List<RentalCarCheckQueryVO> getList(RentalCarCheckQueryDTO rentalCarCheckQueryDTO) {
        if (StringUtil.isNotEmpty(rentalCarCheckQueryDTO.getStartTime())) {
            rentalCarCheckQueryDTO.setStartTime(rentalCarCheckQueryDTO.getStartTime() + " 00:00:00");
        }
        if (StringUtil.isNotEmpty(rentalCarCheckQueryDTO.getEndTime())) {
            rentalCarCheckQueryDTO.setEndTime(rentalCarCheckQueryDTO.getEndTime() + "23:59:59");
        }
        List<RentalCarCheckQueryVO> carList = PaginationUtil.paginationQuery(rentalCarCheckQueryDTO, (carQueryDTO) -> rentalCarCheckDao.getList(carQueryDTO));
        return carList;
    }

    @Override
    public RentalCarConditionVO getCondition() {
        List<RentalCarConditionMiddleVO> result = rentalCarCheckDao.getCondition();
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
    public RentalCarCheckQueryDetailVO getCheckDetail(Integer id) throws Exception {
        RentalCarCheckQueryDetailVO rentalCarCheckQueryDetailVO = rentalCarCheckDao.getDetail(id);
        rentalCarCheckQueryDetailVO.setModelImg(FileUtil.getBrowseFile(rentalCarCheckQueryDetailVO.getModelImgUrl(), rentalCarCheckQueryDetailVO.getModelImgName()));
        List<RentalCarCheckMiniDetailVo> carList = rentalCarCheckQueryDetailVO.getCarList();
        carList.forEach(u -> {
            try {
                u.setDrivingLicenseImg(FileUtil.getBrowseFile(u.getDrivingLicenseImgUrl(), u.getDrivingLicenseImgName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return rentalCarCheckQueryDetailVO;
    }

    @Override
    public List<String> getSeriesByBrand(String brand) {
        return rentalCarCheckDao.getSeriesByBrand(brand);
    }

    @Override
    public List<String> getModelBySeries(String series) {
        return rentalCarCheckDao.getModelBySeries(series);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String check(String checkResult, Integer userId) {
        //检查状态
        JSONObject resultJSON = JSONObject.parseObject(checkResult);
        Integer checkId = resultJSON.getInteger("checkId");
        RentalCarCheck rentalCarCheck = rentalCarCheckDao.selectByPrimaryKey(checkId);
        if (rentalCarCheck == null) {
            throw new ServiceRuntimeException(String.format("找不到该条记录：%s", checkId));
        }
        if (rentalCarCheck.getCheckStatus() == CommCarEnum.RentalCarCheckStatusEnum.CHECKED.getCode()) {
            throw new ServiceRuntimeException("该条记录己审核");
        }
        Boolean allDeny = resultJSON.getBoolean("allDeny");
        String remark = resultJSON.getString("remark");
        //更新审核表
        this.updateCheckTable(checkId, remark, userId);
        if (allDeny) {
            //全部不通过
            rentalCarCheckDao.denyAll(checkId);
            //更新车辆状态为审核失败
            JSONArray carCheckResults = resultJSON.getJSONArray("carCheckResults");
            carCheckResults.forEach(u -> {
                JSONObject carObject = (JSONObject) u;
                Integer carId = carObject.getInteger("id");
                this.updateRentalCarStatus(userId, carId, CommCarEnum.RentalCarStatusEnum.CHECK_FAIL.getCode());
            });
        } else {
            //部分不通过
            this.denySome(resultJSON, checkId, userId);
        }
        return SUCCESS_RESULT;
    }

    @Override
    public List<String> getStoreNameByCompanyName(String companyName) {
        return rentalCarCheckDao.getStoreNameByCompanyName(companyName);
    }

    private void updateRentalCarStatus(Integer userId, Integer carId, int status) {
        RentalCar rentalCar = new RentalCar();
        rentalCar.setId(carId);
        rentalCar.setUpdateBy(userId);
        rentalCar.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        rentalCar.setStatus(status);
        rentalCarDao.updateByPrimaryKeySelective(rentalCar);
    }

    private void denySome(JSONObject resultJSON, Integer checkId, Integer userId) {
        JSONArray carCheckResults = resultJSON.getJSONArray("carCheckResults");
        if (carCheckResults != null && carCheckResults.size() > 0) {
            for (int i = 0; i < carCheckResults.size(); i++) {
                JSONObject jsonObject = carCheckResults.getJSONObject(i);
                Integer carCheckResult = jsonObject.getInteger("checkResult");
                Integer carId = jsonObject.getInteger("id");
                String refuseReason = jsonObject.getString("refuseReason");
                Map<String, Object> params = new HashMap<>();
                params.put("checkId", checkId);
                params.put("carId", carId);
                params.put("refuseReason", refuseReason);
                params.put("checkResult", carCheckResult);
                //更新审核中间表
                rentalCarCheckDao.updateCheckDetail(params);
                //更新车辆状态
                int status = carCheckResult == CommCarEnum.RentalCarCheckResultEnum.UN_PASS.getCode() ?
                        CommCarEnum.RentalCarStatusEnum.CHECK_FAIL.getCode() : CommCarEnum.RentalCarStatusEnum.TO_PUBLISH.getCode();
                this.updateRentalCarStatus(userId, carId, status);
            }
        }
    }

    private void updateCheckTable(Integer checkId, String remark, Integer userId) {
        RentalCarCheck rentalCarCheck = new RentalCarCheck();
        rentalCarCheck.setId(checkId);
        rentalCarCheck.setRemark(remark);
        rentalCarCheck.setUpdateBy(userId);
        rentalCarCheck.setCheckStatus(CommCarEnum.RentalCarCheckStatusEnum.CHECKED.getCode());
        rentalCarCheck.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //更新审核表
        rentalCarCheckDao.updateByPrimaryKeySelective(rentalCarCheck);
    }
}
