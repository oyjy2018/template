package com.ydc.commom.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ydc.model.annotation.Attribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ParamVaildateUtil {

    private static Logger logger = LogManager.getLogger(ParamVaildateUtil.class);

    /***
     * 验证类所有属性并转换
     * data 源数据
     * cls  目标对象
     * @param data
     * @return
     */
    public static Map<String, Object> vaildateAndTransfer(String data, Class cls) {
        Map<String, Object> vailMap = vaildate(data, cls); //验证结果集
        if (vailMap.get("code").equals("0")) {
            //转为目标对象 放到结果集
            vailMap.put("object", JSONObject.parseObject(JSONObject.toJSONString(vailMap.get("dataMap")), cls));
        }
        return vailMap;
    }

    /***
     * 验证类所有属性并转换
     * data 源数据
     * cls  目标对象
     * @param data
     * @return
     */
    public static Map<String, Object> vaildateMapAndTransfer(Map data, Class cls) {
        Map<String, Object> vailMap = vaildateMap(data, cls); //验证结果集
        if (vailMap.get("code").equals("0")) {
            //转为目标对象 放到结果集
            vailMap.put("object", JSONObject.parseObject(JSONObject.toJSONString(data), cls));
        }
        return vailMap;
    }

    /***
     * 验证类所有属性
     * data 源数据
     * cls  目标对象
     * @return
     */
    public static Map<String, Object> vaildate(String data, Class cls) {
        Map<String, Object> vailMap = new HashMap<String, Object>(); //验证结果集
        vailMap.put("code", "0");
        vailMap.put("message", "验证成功");
        if (data == null) {
            vailMap.put("code", "1");
            vailMap.put("message", "验证的数据为空，请检查");
            return vailMap;
        }
        Map dataMap = new HashMap();
        try {
            dataMap = JSONObject.parseObject(data, Map.class);
        } catch (Exception e) {
            vailMap.put("code", "1");
            vailMap.put("message", "json数据格式错误");
            return vailMap;
        }
        vailMap = vaildateMap(dataMap, cls);
        return vailMap;
    }

    /***
     * 验证类所有属性
     * dataMap 源数据
     * cls  目标对象
     * @return
     */
    public static Map<String, Object> vaildateMap(Map dataMap, Class cls) {
        Map<String, Object> vailMap = new HashMap<>(); //验证结果集
        vailMap.put("code", "0");
        vailMap.put("message", "验证成功");
        if (dataMap == null || dataMap.size() == 0) {
            vailMap.put("code", "1");
            vailMap.put("message", "验证的集合为空，请检查");
            return vailMap;
        }
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field field : fields) {
                // 排除static属性
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Attribute att = field.getAnnotation(Attribute.class);
                // 排除没有注解的属性
                if (att == null) {
                    continue;
                }
                Object value = dataMap.get(field.getName());
                String showName = "".equals(att.name()) ? field.getName() : att.name(); //属性名称
                // 必填校验
                if (att.required() && value == null) {
                    vailMap.put("code", "1");
                    vailMap.put("message", showName + "不允许为空，请检查参数");
                    break;
                }

                //根据前置属性判断是否非空
                if (StringUtil.isNotEmpty(att.preAttr()) && StringUtil.isNotEmpty(att.preAttrVal())){
                    if(att.preAttrVal().equals(dataMap.get(att.preAttr()).toString()) && value == null){
                        vailMap.put("code", "1");
                        vailMap.put("message", showName + "不允许为空，请检查参数");
                        break;
                    }
                }

                //是否去空格
                if (att.isStringTrim() && value != null && value instanceof String) {
                    value =  value.toString().trim();
                    dataMap.put(field.getName(),value);
                }
                //是否空串验证
                if (att.emptyStringVerify() && value != null && "".equals(value)) {
                    vailMap.put("code", "1");
                    vailMap.put("message", showName + "为空，请检查参数");
                    break;
                }
                // 整数校验
                if (value != null && att.isNum()) {
                    try {
                        Integer.parseInt(value.toString());
                    } catch (Exception e) {
                        vailMap.put("code", "1");
                        vailMap.put("message", showName + "必须为整数，请检查参数");
                        break;
                    }
                }
                // 数字校验
                if (value != null && att.isDigit()) {
                    try {
                        String regex = "^\\d+(\\.\\d+)?$";
                        if (!value.toString().matches(regex)) {
                            vailMap.put("code", "1");
                            vailMap.put("message", showName + "必须为数字，请检查参数");
                            break;
                        }
                    } catch (Exception e) {
                        vailMap.put("code", "1");
                        vailMap.put("message", showName + "必须为数字，请检查参数");
                        break;
                    }

                    //整数长度校验
                    if(att.intLength() > 0){
                        if(!NumberUtil.hasNumber(value.toString(), att.intLength(), att.decimalLength())){
                            vailMap.put("code", "1");
                            vailMap.put("message", ""+field.getAnnotation(Attribute.class).name() + "信息的格式错误，请输入整数位长度为"
                                    + att.intLength()
                                    + ",小数位长度为" + att.decimalLength() + "的数字");
                            break;
                        }
                    }
                }
                // 固定长度校验
                if (value != null && att.length() > 0) {
                    try {
                        int length = value.toString().length();
                        if (length != att.length()) {
                            vailMap.put("code", "1");
                            vailMap.put("message", showName + "固定长度限制为" + att.length() + "，实际长度为" + value.toString().length() + "，请检查参数");
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        vailMap.put("code", "1");
                        vailMap.put("message", showName + "固定长度校验时发生异常");
                        break;
                    }

                }
                // 最大长度校验
                if (value != null && att.maxLength() > 0) {
                    try {
                        int length = value.toString().length();
                        if (length > att.maxLength()) {
                            vailMap.put("code", "1");
                            vailMap.put("message", showName + "最大长度限制为" + att.maxLength() + "，实际长度为" + value.toString().length() + "，请检查参数");
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        vailMap.put("code", "1");
                        vailMap.put("message", showName + "最大长度校验时发生异常");
                        break;
                    }

                }
                // 日期类型校验
                if (value != null && !"".equals(value) && att.dateFormat().length() > 0 && value instanceof String) {
                    SimpleDateFormat sdf = new SimpleDateFormat(att.dateFormat());
                    try {
                        sdf.parse(value.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                        vailMap.put("code", "1");
                        vailMap.put("message", showName + "日期类型限制为" + att.dateFormat() + "格式，请检查参数");
                        break;
                    }
                }
                // 赋值限制列表校验
                if (value != null && att.valueSetLimit().length > 0) {
                    boolean limitFlag = false;
                    for (int index = 0; index < att.valueSetLimit().length; index++) {
                        if (value.equals(att.valueSetLimit()[index])) {
                            limitFlag = true;
                            continue;
                        }
                    }
                    if (!limitFlag) {
                        vailMap.put("code", "1");
                        vailMap.put("message", field.getAnnotation(Attribute.class).name() + "（" + field.getName() + "）赋值限制列表为" + Arrays.toString(att.valueSetLimit()) + "，当前设值为'" + value + "'不符合规范，请检查参数");
                        break;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            vailMap.put("code", "1");
            vailMap.put("message", e.getMessage());
        } catch (SecurityException e) {
            e.printStackTrace();
            vailMap.put("code", "1");
            vailMap.put("message", e.getMessage());
        }
        vailMap.put("dataMap",dataMap);
        return vailMap;
    }
}
