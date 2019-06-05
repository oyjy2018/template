package com.ydc.service.store.service.impl;

import com.ydc.commom.result.Result;
import com.ydc.commom.util.JsonUtil;
import com.ydc.commom.util.PaginationUtil;
import com.ydc.commom.util.StringUtil;
import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreInsertDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreUpdateDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalStoreDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyStoreQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreBQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreListVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreVO;
import com.ydc.dao.cgj.car.CommCarDao;
import com.ydc.dao.cgj.rental.RentalStoreDao;
import com.ydc.dao.cgj.user.UserDao;
import com.ydc.model.cgj.User;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.rental.RentalStore;
import com.ydc.service.store.service.RentalStoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租车-门店
 *
 * @author
 * @create 2018-11-16 18:23
 **/
@Service
public class RentalStoreServiceImpl implements RentalStoreService {

    @Resource
    RentalStoreDao rentalStoreDao;
    @Resource
    CommCarDao commCarDao;
    @Resource
    UserDao userDao;

    private Logger logger = LogManager.getLogger(RentalStoreServiceImpl.class);

    @Override
    public int insert(RentalStore record) {
        return rentalStoreDao.insert(record);
    }

    @Override
    public RentalStoreVO getRentalStoreByStoreId(Integer storeId) {
        return rentalStoreDao.getRentalStoreByStoreId(storeId);
    }

    @Override
    public int updateRentalStore(RentalStore record) {
        return rentalStoreDao.updateRentalStore(record);
    }

    @Override
    public List<RentalStoreListVO> getRentalStoreList(RentalStoreDTO rentalStoreDTO) {
        return PaginationUtil.paginationQuery(rentalStoreDTO, (tempRentalStoreDTO) -> rentalStoreDao.getRentalStoreList(rentalStoreDTO));
    }

    @Override
    public int updateRentalStoreStatus(RentalStoreDTO rentalStoreDTO) {
        return rentalStoreDao.updateRentalStoreStatus(rentalStoreDTO);
    }

    /**
     * 查询所有门店
     *
     * @param
     * @return
     */
    @Override
    public String getAllRentalStore() {
        return Result.success(rentalStoreDao.getAllRentalStore()).toJSON();
    }

    @Override
    public List<CommCar> getEnabledCarByStoreId(CommCarDTO commCarDTO) {
        return commCarDao.getEnabledCarByStoreId(commCarDTO);
    }

    //车商端新增门店
    @Override
    public int rentalCompanyStoreInsert(RentalCompanyStoreInsertDTO rentalCompanyStoreInsertDTO) {
        RentalStore rentalStore = JsonUtil.jsonToBean(JsonUtil.gsonStr(rentalCompanyStoreInsertDTO), RentalStore.class);
        return rentalStoreDao.insert(rentalStore);
    }

    //车商端编辑门店
    @Override
    public int rentalCompanyStoreUpdate(RentalCompanyStoreUpdateDTO rentalCompanyStoreUpdateDTO) {
        return rentalStoreDao.rentalCompanyStoreUpdate(rentalCompanyStoreUpdateDTO);
    }

    //车商端查询我的门店列表
    @Override
    public List<RentalCompanyStoreQueryVO> rentalCompanyStoreList(Integer theirEnterpriseId) {
        return rentalStoreDao.rentalCompanyStoreList(theirEnterpriseId);
    }

    //车商端我的门店详情
    @Override
    public RentalCompanyStoreQueryVO rentalCompanyStoreDetail(Integer id) {
        RentalStoreVO rentalStoreVO = rentalStoreDao.getRentalStoreByStoreId(id);
        if (rentalStoreVO == null) {
            return null;
        }
        RentalCompanyStoreQueryVO rentalCompanyStoreQueryVO = JsonUtil.jsonToBean(JsonUtil.jsonStr(rentalStoreVO), RentalCompanyStoreQueryVO.class);
        rentalCompanyStoreQueryVO.setId(rentalStoreVO.getStoreId());
        return rentalCompanyStoreQueryVO;
    }

    /**
     * 获取门店树结构
     *
     * @return
     */
    @Override
    public String getStoreTree() {
        //查询所有机构和门店
        List<Map<String, Object>> companyAndStoreList = rentalStoreDao.getCompanyAndStore();
        if (companyAndStoreList == null || companyAndStoreList.isEmpty()) {
            return Result.failure("无机构数据").toJSON();
        }
        //返回结果集  一个机构对应其门店列表
        Map<String, List<Map<String, Object>>> retMap = new HashMap<>();
        for (Map<String, Object> companyAndStore : companyAndStoreList) {
            String companyName = (String) companyAndStore.get("companyName"); //机构名
            List<Map<String, Object>> list = retMap.get(companyName); //获取机构对应的门店列表
            //为空新建
            if (list == null) {
                list = new ArrayList<>();
            }
            //门店状态为非启用时 结束当次循环
            if (!"1".equals(companyAndStore.get("storeStatus")==null?null:companyAndStore.get("storeStatus").toString())){
                retMap.put(companyName,list); //companyName新增对应空门店列表或保持不变
                continue;
            }
            Map storeMap = new HashMap(); //门店map
            storeMap.put("storeId",companyAndStore.get("storeId"));
            storeMap.put("storeName",companyAndStore.get("storeName"));
            list.add(storeMap); //添加到门店列表
            retMap.put(companyName,list); //刷新机构对应的门店列表
        }
        return Result.success(retMap).toJSON();
    }

    /**
     * 我负责的门店
     * @param userId
     * @return
     */
    @Override
    public String myResponsibleStoreList(Integer userId) {
        //根据userId查询用户信息
        User user = userDao.selectByPrimaryKey(userId);
        if (user == null) {
            return Result.failure("未查询到用户信息").toJSON();
        }
        String viewOrgId = user.getViewOrgId();
        if (StringUtil.isEmpty(viewOrgId)) {
            return Result.failure("无负责的门店").toJSON();
        }
        Map param = new HashMap();
        param.put("viewOrgId",viewOrgId);
        List<RentalStoreBQueryVO> rentalStoreBQueryVOList = rentalStoreDao.myResponsibleStoreList(param);
        return Result.success(rentalStoreBQueryVOList).toJSON();
    }
}
