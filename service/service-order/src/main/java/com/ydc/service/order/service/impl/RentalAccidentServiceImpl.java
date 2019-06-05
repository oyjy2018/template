package com.ydc.service.order.service.impl;

import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.view.dto.cgj.rental.RentalAccidentQueryDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalAccidentQueryVO;
import com.ydc.dao.cgj.rental.RentalAccidentDao;
import com.ydc.dao.cgj.rental.RentalOrderDao;
import com.ydc.dao.cgj.rental.RentalOrderMaintenanceDao;
import com.ydc.dao.cgj.user.UserDao;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.rental.RentalAccident;
import com.ydc.service.order.service.RentalAccidentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentalAccidentServiceImpl implements RentalAccidentService {

    @Resource
    RentalAccidentDao rentalAccidentDao;

    @Resource
    UserDao userDao;

    @Resource
    RentalOrderDao rentalOrderDao;

    @Resource
    RentalOrderMaintenanceDao rentalOrderMaintenanceDao;


    @Override
    public int insertRentalAccidentInfo(RentalAccident rentalAccident) {
        User user = userDao.selectByPrimaryKey(rentalAccident.getUserId());
        if (user != null) {
            rentalAccident.setUserName(user.getUserName());
        }
        return rentalAccidentDao.insert(rentalAccident);
    }

    @Override
    public Map<String, Object> queryRentalAccidentInfoById(Integer id) {
        Map<String, Object> rentalAccident = rentalAccidentDao.getRentalAccidentById(id);
        if (rentalAccident != null) {
            Map<String, Object> carInfo = new HashMap<>();
            Integer orderType = Integer.valueOf(rentalAccident.get("orderType").toString());
            if (orderType == 0) { //租车单id查询
                carInfo = rentalOrderDao.getCarInfo(Integer.valueOf(rentalAccident.get("orderId").toString()));
            } else if (orderType == 1) { // 机务单id查询
                carInfo = rentalOrderMaintenanceDao.getCarInfo(Integer.valueOf(rentalAccident.get("orderId").toString()));
            }
            if (carInfo != null) {
                rentalAccident.putAll(carInfo);
            }
        }
        return rentalAccident;
    }

    @Override
    public List<RentalAccidentQueryVO> queryRentalAccidentListInfo(RentalAccidentQueryDTO rentalAccidentQueryDTO) {
        return PaginationUtil.paginationQuery(rentalAccidentQueryDTO, (x) -> rentalAccidentDao.getRentalAccidentList(x));
    }

    @Override
    public int updateRentalAccidentInfo(RentalAccident rentalAccident) {
        User user = userDao.selectByPrimaryKey(rentalAccident.getUserId());
        if (user != null) {
            rentalAccident.setUserName(user.getUserName());
        }
        return rentalAccidentDao.updateByPrimaryKeySelective(rentalAccident);
    }

    @Override
    public int deleteRentalAccidentInfo(Integer id) {
        return rentalAccidentDao.deleteRentalAccident(id);
    }

    @Override
    public Map<String, String> queryRentalOrderOrMaintenance(RentalAccident rentalAccident) throws Exception {
        Map<String, String> resultMap = null;
        Integer orderType = rentalAccident.getOrderType();
        if (orderType == 0) { //租车单id查询
            resultMap = rentalOrderDao.getCarInfo(rentalAccident.getOrderId());
        } else if (orderType == 1) { // 机务单id查询
            resultMap = rentalOrderMaintenanceDao.getCarInfo(rentalAccident.getOrderId());
        }
        return resultMap;
    }
}
