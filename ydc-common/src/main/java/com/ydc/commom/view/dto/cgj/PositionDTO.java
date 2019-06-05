package com.ydc.commom.view.dto.cgj;

import com.ydc.model.cgj.Pagination;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @author
 * @create 2018-10-31 11:35
 **/
public class PositionDTO extends Pagination implements Serializable {
    private static final long serialVersionUID = 4598569838287194659L;

    private double minLat;//最小纬度
    private double maxLat;//最大纬度
    private double minLng;//最小经度
    private double maxLng;//最大经度
    private Double longitude; //会员经度
    private Double latitude;  //会员纬度
    private String searchContent;//搜索内容
    private String defaultLocation;//默认位置
    public PositionDTO() {
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMinLng() {
        return minLng;
    }

    public void setMinLng(double minLng) {
        this.minLng = minLng;
    }

    public double getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(double maxLng) {
        this.maxLng = maxLng;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }
}
