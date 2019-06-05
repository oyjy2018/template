package com.ydc.commom.view.dto.cgj.shopCart;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 会员购物车 DTO
 */
public class  MemberShopCartDTO implements Serializable {

    private Integer memberId;//会员id

    private int totalCommodityNum;//总数量

    private BigDecimal totalSellPrice;//总销售价格

    private BigDecimal totalMarketPrice;//总商品价格

    private List<ShopCartCommodityDTO> shopCartCommodityDTOS;//有效商品

    private List<ShopCartCommodityDTO> invalidationShopCartCommodityDTOS;//失效商品


    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }



    public int getTotalCommodityNum() {
        return totalCommodityNum;
    }

    public void setTotalCommodityNum(int totalCommodityNum) {
        this.totalCommodityNum = totalCommodityNum;
    }

    public BigDecimal getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(BigDecimal totalSellPrice) {
        this.totalSellPrice = totalSellPrice;
    }

    public BigDecimal getTotalMarketPrice() {
        return totalMarketPrice;
    }

    public void setTotalMarketPrice(BigDecimal totalMarketPrice) {
        this.totalMarketPrice = totalMarketPrice;
    }

    public List<ShopCartCommodityDTO> getShopCartCommodityDTOS() {
        return shopCartCommodityDTOS;
    }

    public void setShopCartCommodityDTOS(List<ShopCartCommodityDTO> shopCartCommodityDTOS) {
        this.shopCartCommodityDTOS = shopCartCommodityDTOS;
    }

    public List<ShopCartCommodityDTO> getInvalidationShopCartCommodityDTOS() {
        return invalidationShopCartCommodityDTOS;
    }

    public void setInvalidationShopCartCommodityDTOS(List<ShopCartCommodityDTO> invalidationShopCartCommodityDTOS) {
        this.invalidationShopCartCommodityDTOS = invalidationShopCartCommodityDTOS;
    }
}
