package com.ydc.commom.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ydc.commom.view.dto.cgj.rental.RentalCheckCarDTO;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * JSON工具类
 *
 * @author huyueming
 * @date 2017年7月22日 下午2:14:15
 */
public class JsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder().disableHtmlEscaping().create();
        }
    }

    /**
     * 转成json字符串
     *
     * @param obj
     * @return
     * @author huyueming
     * @date 2017年7月22日
     */
    public static String jsonStr(Object obj) {
        return JSONObject.toJSONString(obj);
    }

    //SerializerFeature是个枚举类型。消除循环引用  避免 $ref对象重复引用问题
    public static String jsonToStr(Object obj) {
        return JSONObject.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 转成json字符串
     *
     * @param obj
     * @return
     * @author huyueming
     * @date 2017年7月22日
     */
    public static String gsonStr(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * json转换bean对象
     *
     * @param jsonStr
     * @param cls
     * @return
     * @author huyueming
     * @date 2017年7月22日
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(jsonStr, cls);
        }
        return t;
    }

    /**
     * json转换List集合
     *
     * @param jsonStr
     * @param cls
     * @return
     * @author huyueming
     * @date 2017年7月22日
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> cls) {
        return JSONObject.parseArray(jsonStr, cls);
    }

    /**
     * json转换MAP对象
     *
     * @param jsonStr
     * @return
     * @author huyueming
     * @date 2017年7月22日
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> jsonToMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, Map.class);
    }

    /**
     * json转换List<Map<String, T>>集合
     *
     * @param jsonStr
     * @return
     * @author huyueming
     * @date 2017年7月22日
     */
    @SuppressWarnings("unchecked")
    public static <T> List<Map<String, T>> jsonToListMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, List.class);
    }

    /**
     * json转换TreeMap对象
     *
     * @param jsonStr
     * @return
     * @author hejiangping
     * @date 2018年7月20日
     */
    @SuppressWarnings("unchecked")
    public static <T> TreeMap<String, T> jsonToTreeMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, TreeMap.class);
    }

    /**
     * json转换List<TreeMap<String, T>>集合
     *
     * @author hejiangping
     * @date 2018年8月14日
     */
    @SuppressWarnings("unchecked")
    public static <T> List<TreeMap<String, T>> jsonToListTreeMap(String jsonStr) {
        return JSONObject.parseObject(jsonStr, List.class);
    }

    /**
     * json转listmap
     * @param jsonStr
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> parseToListMap(String jsonStr){
       return JSON.parseObject(jsonStr, new TypeReference<List<Map<String, T>>>(){});
    }

    public static void main(String[] args) {
//        System.out.println(JsonUtil.jsonToListMap("[{customer_name:\"广州科贤电子有限公司\",id_card:\"420325198810014657\",mobile_phone:\"13800138000\",email:\"qaa@qq.com\",province:\"广东省\",city:\"广州市\",region:\"番禺区\",address:\"桥南街陈涌村第二工业区兴业大道西五横路4号\",relation:\"公司法人\"},{customer_name:\"徐绮华\",id_card:\"420325198810014657\",mobile_phone:\"13800138000\",email:\"qaa@qq.com\",province:\"广东省\",city:\"广州市\",region:\"番禺区\",address:\"市桥街东城区东沙园一街二座\",relation:\"配偶\"}]"));
        String data = "{\n" +
                "\"rentalCheckCarDTO\": [\n" +
                "\t{\n" +
                "\t\"orderId\": 1,\n" +
                "\t\"carNumber\": \"777777\",\n" +
                "\t\"resourceSideComeCarImgName\": \"6e7250b2-a112-4609-868b-d845f717888a_file.jpg\",\n" +
                "\t\"resourceSideComeCarImgUrl\": \"/usr/local/upload/ydzc/cfd97003-d8ab-4d40-8073-ac544b0a8648\",\n" +
                "\t\"demandSideComeCarImgName\": \"c2798bce-6687-45e3-a2d5-433ddbc335ca_file.jpg\",\n" +
                "\t\"demandSideComeCarImgUrl\": \"/usr/local/upload/ydzc/194f07de-db9a-41a6-d19f-be562e31631c\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\"orderId\": 1,\n" +
                "\t\"carNumber\": \"888888\",\n" +
                "\t\"resourceSideComeCarImgName\": \"42727be0-bca2-4950-bafb-2cfad0b57327_file.jpg\",\n" +
                "\t\"resourceSideComeCarImgUrl\": \"/usr/local/upload/ydzc/bc74a90a-6240-479f-8ef5-de4be9151980\",\n" +
                "\t\"demandSideComeCarImgName\": \"cd7c7593-d437-4a3a-97fa-55848dc0ef20_file.jpg\",\n" +
                "\t\"demandSideComeCarImgUrl\": \"/usr/local/upload/ydzc/f6822f5f-e9ed-4b95-e48e-341ca331def1\"\n" +
                "\t}\n" +
                "]\n" +
                "}";

        List<RentalCheckCarDTO> list = JsonUtil.jsonToList(jsonToMap(data).get("rentalCheckCarDTO").toString(),RentalCheckCarDTO.class);
        System.out.println(jsonStr(list));

    }
}
