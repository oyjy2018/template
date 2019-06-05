package com.ydc.beans.redis;

import com.ydc.commom.constant.DdConstant;
import com.ydc.commom.util.JsonUtil;
import com.ydc.model.cgj.Dd;
import com.ydc.model.cgj.DictionaryDetail;
import com.ydc.model.ydhc.YdhcDd;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class DictionaryUtil {
    private static final Logger logger = LogManager.getLogger(DictionaryUtil.class);
    /**
     * 字典表类型转换
     *
     * @param dictKey
     * @param parentDictCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Optional<DictionaryDetail> getDictionaryDetailByDictKey(String dictKey, String parentDictCode) {
        Object o = RedisUtil.redisGet(parentDictCode);
        if (o != null) {
            List<DictionaryDetail> dictionaryList = (List<DictionaryDetail>) o;
            return dictionaryList.stream().filter(dictionary -> dictionary.getDictKey().equals(dictKey)).findAny();
        }
        return Optional.empty();
    }

    /**
     * 字典表类型转换
     *
     * @param dictValue
     * @param parentDictCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Optional<DictionaryDetail> getDictionaryDetailByDictValue(String dictValue, String parentDictCode) {
        Object o = RedisUtil.redisGet(parentDictCode);
        if (o != null) {
            List<DictionaryDetail> dictionaryList = (List<DictionaryDetail>) o;
            return dictionaryList.stream().filter(dictionary -> dictionary.getDictValue().equals(dictValue)).findAny();
        }
        return Optional.empty();
    }

    /**
     * 根据父类code获取集合
     *
     * @param parentDictCode
     * @return
     */
    public static Optional<List<DictionaryDetail>> getDictionaryDetailByParentDictCode(String parentDictCode) {
        Object o = RedisUtil.redisGet(parentDictCode);
        logger.info("subject:{},parentDictCode:{},o:{}","根据父类code获取集合",parentDictCode,JsonUtil.gsonStr(o));
        if (o != null) {
            List<DictionaryDetail> dictionaryDetails = (List<DictionaryDetail>) o;
            return Optional.ofNullable(dictionaryDetails);
        }
        return Optional.empty();
    }

    /**
     * 获取钉钉配置
     * @return
     */
    public static Optional<YdhcDd> getYdhcDdConfig(String key){
        Object o = RedisUtil.redisGet(key);
        if(o != null){
            YdhcDd dd = JsonUtil.jsonToBean(o.toString(), YdhcDd.class);
            return Optional.ofNullable(dd);
        }
        return Optional.empty();
    }

    /**
     * 获取H5数据配置
     * @return
     */
    public static Optional<Map<String,List<DictionaryDetail>>> getH5Config(String key){
        Object o = RedisUtil.redisGet(key);
        logger.info("subject:{},key:{},o:{}","获取H5数据配置",key,o);
        if(o != null){
            Map<String,List<DictionaryDetail>> h5Config = (Map<String,List<DictionaryDetail>>) o;
            return Optional.ofNullable(h5Config);
        }
        return Optional.empty();
    }

    /**
     * 获取钉钉配置
     *
     * @param serviceIdentifying 服务标识
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Optional<Dd> getdingtalkParamByServiceIdentifying(String serviceIdentifying) {
        Object o = RedisUtil.redisGet(DdConstant.DINGTALK_PARAM+serviceIdentifying);
        logger.info("subject:{},serviceIdentifying:{},o:{}","获取钉钉配置",serviceIdentifying,JsonUtil.gsonStr(o));
        if (o != null) {
            List<Dd> ddList = (List<Dd>) o;
            return ddList.stream().filter(dictionary -> dictionary.getServiceIdentifying().equals(serviceIdentifying)).findAny();
        }
        return Optional.empty();
    }
}
