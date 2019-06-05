package com.ydc.dao.cgj.rental;

import com.ydc.commom.view.dto.cgj.rental.RentalCompanyStoreUpdateDTO;
import com.ydc.commom.view.dto.cgj.rental.RentalStoreDTO;
import com.ydc.commom.view.vo.cgj.rental.RentalCompanyStoreQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreBQueryVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreListVO;
import com.ydc.commom.view.vo.cgj.rental.RentalStoreVO;
import com.ydc.model.cgj.Pagination;
import com.ydc.model.cgj.rental.RentalStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RentalStoreDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    int insert(RentalStore record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    int insertSelective(RentalStore record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    RentalStoreVO getRentalStoreByStoreId(@Param("storeId") Integer storeId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-11-16 18:18:18
     */
    int updateByPrimaryKeySelective(RentalStore record);

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
    int updateRentalStoreStatus(@Param("rentalStoreDTO") RentalStoreDTO rentalStoreDTO);

    /**
     * 查询所有门店
     * @param
     * @return
     */
    List<Map<String,Object>> getAllRentalStore();

    //车商端编辑门店
    int rentalCompanyStoreUpdate(RentalCompanyStoreUpdateDTO rentalCompanyStoreUpdateDTO);

    //车商端查询我的门店列表(未分页)
    List<RentalCompanyStoreQueryVO> rentalCompanyStoreList(Integer theirEnterpriseId);

    //查询所有公司和门店
    List<Map<String, Object>> getCompanyAndStore();

    //通过id串查询门店列表
    List<Map<String, Object>> getRentalStoreByIds(Map param);

    //查询所有机构
    List<Map<String, Object>> getOrgList();

    //我负责的门店
    List<RentalStoreBQueryVO> myResponsibleStoreList(Map param);
}