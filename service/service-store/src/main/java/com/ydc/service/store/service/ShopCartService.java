package com.ydc.service.store.service;

import com.ydc.commom.result.Result;
import com.ydc.commom.view.dto.cgj.shopCart.MemberShopCartDTO;

import java.util.List;

public interface ShopCartService {
    /**
     *  根据会员ID 查询用户购物车
     */

    MemberShopCartDTO getMemberShopCartDTO(Integer memberId);

    /**
     *   更新会员购物车
     *
     */
    int updateMemberShopCart();

    /**
     *  删除会员 购物车商品
     *
     */
    int deleteMemberShopCart(Integer memberId, List<Integer> commodityIds);

    /**
     *  加入购物车
     * @param memberId
     * @param commodityId
     * @param modelId
     * @param number
     * @param type "+"  在原来的库存上添加
     * @return
     */
    Result addMemberShopCart(Integer memberId, Integer commodityId, Integer modelId, Integer number, String type);


    /**
     * 更新 购物车商品数量
     * @param id
     * @param number
     * @return
     */
    Result updateMemberShopCartNumber(Integer id, Integer memberId,Integer number);

    /**
     *  获取
     * @param memberId
     * @return
     */
    int getMemberShopCartCount(Integer memberId);


}
