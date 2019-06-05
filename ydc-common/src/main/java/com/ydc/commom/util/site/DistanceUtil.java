package com.ydc.commom.util.site;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 通过经纬度获取距离
 *
 * @author
 * @create 2018-10-31 11:13
 **/
public class DistanceUtil {

    private static final Logger logger = LogManager.getLogger(DistanceUtil.class);

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double splitAndRound(double a, int n) {
        a = a * Math.pow(10, n);
        return (Math.round(a)) / (Math.pow(10, n));
    }

    /**
     * 通过经纬度获取距离(单位：千米)
     * @param lat1 纬度
     * @param lng1 经度
     * @param lat2 纬度
     * @param lng2 经度
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = splitAndRound(s,2);
        logger.info("subject:{},lat1:{},lng1:{},lat2:{},lng2:{}","通过经纬度获取距离(单位：千米)",lat1,lng1,lat2,lng2);
        return s;
    }

    /**
     * 先计算查询点的经纬度范围
     * @param longitude
     * @param latitude
     * @return
     */
    public static Position findNeighPosition(double longitude,double latitude,double dis){
        double r = 6371;//地球半径千米
//        double dis = 3;//0.5千米距离
        double dLng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dLng = dLng*180/Math.PI;//角度转为弧度
        double dLat = dis/r;
        dLat = dLat*180/Math.PI;
        double minLat =latitude - dLat;
        double maxLat = latitude + dLat;
        double minLng = longitude - dLng;
        double maxLng = longitude + dLng;
        Position position = new Position(minLat, maxLat, minLng, maxLng);
       logger.info("minLat:"+minLat+";maxLat:"+maxLat+";minLng:"+minLng+";maxLng:"+maxLng);
        return position;
    }
}
