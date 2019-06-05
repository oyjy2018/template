package com.ydc.commom.view.dto.cgj;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class StoreReqDTO implements Serializable {
    private static final long serialVersionUID = 3236985917839411413L;
    private String delCommodityModelIds;

    private String commodityId;

    private String commodityModelId;

    private Map<String, String> commodity;

    private List<Map<String, String>> commodityModels;

    private List<Map<String, String>> commodityImgs;

    private String commodityIds;

    private String hasShoutui;

    private String releaseStatus;

    private String inventory;

    private String userId;

    private String userName;

    public String getDelCommodityModelIds() {
        return delCommodityModelIds;
    }

    public void setDelCommodityModelIds(String delCommodityModelIds) {
        this.delCommodityModelIds = delCommodityModelIds;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityModelId() {
        return commodityModelId;
    }

    public void setCommodityModelId(String commodityModelId) {
        this.commodityModelId = commodityModelId;
    }

    public Map<String, String> getCommodity() {
        return commodity;
    }

    public void setCommodity(Map<String, String> commodity) {
        this.commodity = commodity;
    }

    public List<Map<String, String>> getCommodityModels() {
        return commodityModels;
    }

    public void setCommodityModels(List<Map<String, String>> commodityModels) {
        this.commodityModels = commodityModels;
    }

    public List<Map<String, String>> getCommodityImgs() {
        return commodityImgs;
    }

    public void setCommodityImgs(List<Map<String, String>> commodityImgs) {
        this.commodityImgs = commodityImgs;
    }

    public String getCommodityIds() {
        return commodityIds;
    }

    public void setCommodityIds(String commodityIds) {
        this.commodityIds = commodityIds;
    }

    public String getHasShoutui() {
        return hasShoutui;
    }

    public void setHasShoutui(String hasShoutui) {
        this.hasShoutui = hasShoutui;
    }

    public String getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(String releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
