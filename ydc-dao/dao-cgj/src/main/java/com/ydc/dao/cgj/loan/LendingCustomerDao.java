package com.ydc.dao.cgj.loan;

import com.ydc.commom.view.dto.cgj.LendingCustomerDTO;
import com.ydc.commom.view.dto.rcs.LendingDataDTO;
import com.ydc.model.cgj.LendingCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LendingCustomerDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    int insert(LendingCustomer record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    int insertSelective(LendingCustomer record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    LendingCustomer selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    int updateByPrimaryKeySelective(LendingCustomer record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-10-26 14:59:13
     */
    int updateByPrimaryKey(LendingCustomer record);

    /**
     * 批量新增放款客户数据
     * @param list
     */
    void insertLendingCustomerBatch(List<LendingDataDTO> list);

    /**
     * 放款客户数据
     * @param lendingCustomerDTO
     * @return
     */
    List<Map<String,Object>> getLendingCustomerList(@Param("lendingCustomerDTO") LendingCustomerDTO lendingCustomerDTO);

    /**
     * 更新借款单派完状态
     * @param loanIds
     * @return
     */
    Integer updateCustomerRollOver(@Param("loanIds") List<Integer> loanIds);

    /**
     * 根据loanId获取LendingCustomer对象
     * @param loanId
     * @return
     */
    LendingCustomer getLendingCustomerByLoanId(Integer loanId);
}