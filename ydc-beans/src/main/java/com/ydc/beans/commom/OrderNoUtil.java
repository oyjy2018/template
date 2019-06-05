package com.ydc.beans.commom;

import com.ydc.beans.config.OrderNoConfig;
import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 订单生产
 */
@Component
public class OrderNoUtil {
    private static Logger logger = LogManager.getLogger(OrderNoUtil.class);
    private final  static  String defaultDateFormat="yyyyMMddhhmmss"; //默认时间格式化
    private final  static  int  defaultIncrementLength=4;   //默认长度 4+1


    @Resource
    private RedisTemplate redisTemplate;

    private static RedisTemplate template;


    @PostConstruct
    public void init() {
        template = redisTemplate;

    }

    /**
     * 获取订单号
     *
     * @return 订单号
     */
    public static  String getOrderNo(OrderNoConfig orderNoConfig) {
        return getOrderNo(orderNoConfig, defaultDateFormat, defaultIncrementLength );
    }

    /**
     * 获取订单号
     * @param orderNoConfig    orderNoPrefix 订单号前缀  orderNoSuffix 订单号后缀
     * @param dateFormat  订单号时间格式换   例子:yyyyMMddhhmmss
     * @param incrementLength  订单号自增长度  4 （时间长度+1）
     * @return
     */
    public static  String getOrderNo(OrderNoConfig orderNoConfig,String dateFormat,int  incrementLength ) {
        String keyDateFormat="yyyyMMdd";
        String dateString =DateUtil.format(new Date(),keyDateFormat);

        logger.info("@@@@@获取订单号：订单类型{},前缀 {},后缀{}, 时间{}" ,  orderNoConfig.getKeyPrefix(),orderNoConfig.getOrderNoPrefix(),orderNoConfig.getOrderNoSuffix(),dateString);
        final String key= orderNoConfig.getKeyPrefix()+dateString;
        String orderNo = null;
        try {
            Object object=template.opsForList().leftPop(key,2000,TimeUnit.MILLISECONDS);
            if (null == object){
                //清除昨日的
                clearRedis( orderNoConfig.getKeyPrefix(),dateString);
                //添加
                List<String> orderArray= getOrderList(dateFormat,incrementLength);
                object=orderArray.remove(0);
                template.opsForList().rightPushAll(key,orderArray);
            }
            StringBuffer stringBuffer=new StringBuffer();
            if (StringUtil.isNotEmpty(orderNoConfig.getOrderNoPrefix())){
                stringBuffer.append(orderNoConfig.getOrderNoPrefix());
            }
            stringBuffer.append(object.toString());
            if (StringUtil.isNotEmpty(orderNoConfig.getOrderNoSuffix())){
                stringBuffer.append(orderNoConfig.getOrderNoSuffix());
            }
            orderNo=stringBuffer.toString();
            // 判断队列剩余订单数量大小
        } catch (Exception e) {
            logger.error("@@@@@获取订单号异常======" + e.getMessage());
        }
        return orderNo;
    }


    /**
     *
     * @param dateFormat  订单号时间格式换  yyyyMMddhhmmss
     * @param incrementLength  订单号自增长度  4 （时间长度+1）
     * @return
     */
    private static  List<String>  getOrderList(String dateFormat,int  incrementLength) {

        String prefix =DateUtil.format(new Date(),dateFormat);

        List<String> collection = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i <1000; i++) {
            StringBuilder finalOrderNo =new StringBuilder();
            String num = String.valueOf(i);
            for (int j = 0; j < incrementLength - num.length(); j++) {
                sb.append("0");
            }
            if (num.length() <= 4) {
                num = sb.append(num).toString();
            }
            finalOrderNo.append(prefix).append(num).append(random.nextInt(10));
            sb.setLength(0);
            collection.add(finalOrderNo.toString());
        }
        return collection;
    }



    static void clearRedis(String key,String type) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sdf.parse(type);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date time = calendar.getTime();
            String keySuffix=sdf.format(time);
            key=key+keySuffix;
            template.delete(key);
        } catch (Exception e) {
            logger.info("清除异常："+e.getMessage());
        }
    }

}
