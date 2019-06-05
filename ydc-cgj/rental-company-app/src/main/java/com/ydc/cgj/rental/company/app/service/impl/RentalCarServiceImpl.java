package com.ydc.cgj.rental.company.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ydc.cgj.rental.company.app.feignService.RentalCarFeignService;
import com.ydc.cgj.rental.company.app.service.RentalCarService;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalCarListDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCarMyListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalCarServiceImpl implements RentalCarService {

    @Autowired
    private RentalCarFeignService rentalCarFeignService;

    @Override
    public String getMy(RentalCarListDTO rentalCarListDTO) {
        return rentalCarFeignService.getMy(rentalCarListDTO);
    }

    @Override
    public String getMyList(RentalCarMyListDTO rentalCarMyListDTO) {
        return rentalCarFeignService.getMyList(rentalCarMyListDTO);
    }

    @Override
    public String getMyCarDetail(Integer carId) {
        return rentalCarFeignService.getMyCarDetail(carId);
    }

    @Override
    public String deleteMyCar(Integer carId, Integer companyId) {
        return rentalCarFeignService.deleteMyCar(carId, companyId);
    }

    @Override
    public String addMyCar(String data, Integer companyId) {
        return rentalCarFeignService.addMyCar(data, companyId);
    }

    @Override
    public String checkData(String data) {
        //必填字段检测
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        if (dataJSON.getInteger("carSeriesId") == null) {
            return "车型id不能为空";
        }
        if (dataJSON.getInteger("storeId") == null) {
            return "门店id不能为空";
        }
        if (StringUtil.isEmpty(dataJSON.getString("carSeat"))) {
            return "车座位数不能为空";
        }
        if (StringUtil.isEmpty(dataJSON.getString("carStructure"))) {
            return "车结构不能为空";
        }
        JSONArray carList = dataJSON.getJSONArray("carList");
        StringBuilder carPlateSb = new StringBuilder();
        if (carList.size() > 0) {
            for (int i = 0; i < carList.size(); i++) {
                JSONObject carJSON = carList.getJSONObject(i);
                String carPlate = carJSON.getString("carPlate");
                if (StringUtil.isEmpty(carJSON.getString("carPlate"))) {
                    return "车牌号不能为空";
                }
                if (carPlateSb.toString().contains(carPlate)) {
                    return String.format("车牌号[%s]重复了", carPlate);
                }
                carPlateSb.append(carPlate).append("#");
                if (StringUtil.isEmpty(carJSON.getString("vin"))) {
                    return "车架号不能为空";
                }
                if (StringUtil.isEmpty(carJSON.getString("upPlateDate"))) {
                    return "上牌日期不能为空";
                }
                if (StringUtil.isEmpty(carJSON.getString("carLevel"))) {
                    return "车辆等级不能为空";
                }
                if (StringUtil.isEmpty(carJSON.getString("drivingLicenseImgName"))) {
                    return "行驶证照片文件名不能为空";
                }
                if (StringUtil.isEmpty(carJSON.getString("drivingLicenseImgUrl"))) {
                    return "行驶证照片文件路径不能为空";
                }
            }
        } else {
            return "车辆信息不能为空";
        }
        return null;
    }

    @Override
    public String getCarListByStatus(Integer companyId, Integer status) {
        return rentalCarFeignService.getCarListByStatus(companyId, status);
    }

    @Override
    public String checkPublishData(String data) {
        JSONObject dataJSON = (JSONObject) JSONObject.parse(data);
        Integer workdayRent = dataJSON.getInteger("workdayRent");
        if (workdayRent == null || workdayRent == 0) {
            return "工作日租金不能为空";
        }
        Integer weekendRent = dataJSON.getInteger("weekendRent");
        if (weekendRent == null || weekendRent == 0) {
            return "周末日租金不能为空";
        }
        Integer holidayRent = dataJSON.getInteger("holidayRent");
        if (holidayRent == null || holidayRent == 0) {
            return "节假日租金不能为空";
        }
        Object[] carIdsArray = dataJSON.getJSONArray("carList").toArray();
        if (carIdsArray.length == 0) {
            return "车辆信息不能为空";
        }
        for(Object carObject : carIdsArray) {
            JSONObject carJSON = (JSONObject) carObject;
            if(carJSON.getInteger("carId") == null) {
                return "车辆id不能为空";
            }
            if(carJSON.getInteger("storeId") == null) {
                return "门店id不能为空";
            }
            if(carJSON.getInteger("seriesId") == null) {
                return "车系id不能为空";
            }
        }
        return null;
    }

    @Override
    public String publish(String data, Integer companyId) {
        return rentalCarFeignService.publish(data, companyId);
    }

    @Override
    public String getAddBrandList() {
        return rentalCarFeignService.getAddBrandList();
    }

    @Override
    public String getAddSeriesByBrand(String brand) {
        return rentalCarFeignService.getAddSeriesByBrand(brand);
    }

    @Override
    public String getAddModelBySeries(String series) {
        return rentalCarFeignService.getAddModelBySeries(series);
    }

    @Override
    public String removeCar(String data) {
        return rentalCarFeignService.removeCar(data);
    }

}
