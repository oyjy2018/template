package com.ydc.service.store.service;

import com.ydc.commom.view.dto.cgj.rental.CommCarDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreInsertDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreUpdateDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalStoreDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyStoreQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreListVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreVO;
import com.ydc.model.cgj.car.CommCar;
import com.ydc.model.cgj.rental.RentalStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 租车-门店
 *
 * @author
 * @create 2018-11-16 18:23
 **/
public interface RentalStoreService {

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    int insert(RentalStore record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    RentalStoreVO getRentalStoreByStoreId(Integer storeId);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    int updateRentalStore(RentalStore record);


    /**
     * 门店列表
     * @param rentalStoreDTO
     * @return
     */
    List<RentalStoreListVO> getRentalStoreList(@Param("rentalStoreDTO") RentalStoreDTO rentalStoreDTO);

    /**
     * 更新门店状态
     * @param rentalStoreDTO
     * @return
     */
    int updateRentalStoreStatus(RentalStoreDTO rentalStoreDTO);

    /**
     * 查询所有门店
     * @param
     * @return
     */
    String getAllRentalStore();

    /**
     * 查询门店中启用的车辆
     * @param commCarDTO
     * @return
     */
    List<CommCar> getEnabledCarByStoreId(CommCarDTO commCarDTO);

    //车商端新增门店
    int rentalCompanyStoreInsert(RentalCompanyStoreInsertDTO rentalCompanyStoreInsertDTO);

    //车商端编辑门店
    int rentalCompanyStoreUpdate(RentalCompanyStoreUpdateDTO rentalCompanyStoreUpdateDTO);

    //车商端查询我的门店列表
    List<RentalCompanyStoreQueryVO> rentalCompanyStoreList(Integer theirEnterpriseId);

    //车商端我的门店详情
    RentalCompanyStoreQueryVO rentalCompanyStoreDetail(Integer id);

    /**
     * 获取门店树结构
     * @return
     */
    String getStoreTree();

    /**
     * 我负责的门店
     * @param userId
     * @return
     */
    String myResponsibleStoreList(Integer userId);
}
