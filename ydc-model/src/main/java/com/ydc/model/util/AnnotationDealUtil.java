package com.ydc.model.util;

import com.ydc.model.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AnnotationDealUtil {

	private static final Logger logger = LogManager.getLogger(AnnotationDealUtil.class);

	/**
	 * 注解验证
	 * @author gongjin
	 * @param bean 验证的实体
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> validate(Object bean) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "验证通过");
		result.put("result", true);
		Class<?> cls = bean.getClass();
		// 检测field是否存在
		try {
			// 获取实体字段集合
			Field[] fields = cls.getDeclaredFields();
			for (Field f : fields) {
				// 通过反射获取该属性对应的值
				f.setAccessible(true);
				// 获取字段值
				Object value = f.get(bean);
				// 获取字段上的注解集合
				Annotation[] arrayAno = f.getAnnotations();
				for (Annotation annotation : arrayAno) {
					// 获取注解类型（注解类的Class）
					Class<?> clazz = annotation.annotationType();
					// 获取注解类中的方法集合
					Method[] methodArray = clazz.getDeclaredMethods();
					for (Method method : methodArray) {
						// 获取方法名
						String methodName = method.getName();
						// 过滤错误提示方法的调用
						if (methodName.equals("message")) {
							continue;
						}
						// 初始化注解验证的方法处理类 （我的处理方法写在本类中）
						Object obj = AnnotationDealUtil.class.newInstance();
						// 获取方法
						try {
							// 根据方法名获取该方法
							Method m = obj.getClass().getDeclaredMethod(methodName, Object.class, Field.class);
							// 调用该方法
							result = (Map<String, Object>) m.invoke(obj, value, f);
							/* 验证结果 有一处失败则退出 */
							if (result.get("result").equals(false)) {
								return result;
							}
						} catch (Exception e) {
							result.put("message", "找不到该方法:" + methodName);
							result.put("result", false);
							logger.error("subject:{},e:{]","找不到该方法:" + methodName,e);
						}
					}
				}
			}
		} catch (Exception e) {
			result.put("message", "验证出错");
			result.put("result", false);
			logger.error("subject:{},e:{]","验证出错:" ,e);
		}
		return result;
	}
	
	/**
	 * 验证是否空值
	 * 
	 * @author gongjin
	 * @param value 参数值
	 * @param field 字段
	 * @return
	 */
	public Map<String, Object> isEmpty(Object value, Field field) {
		Map<String, Object> validateResult = new HashMap<String, Object>();
		IsEmpty annotation = field.getAnnotation(IsEmpty.class);
		if(value == null || value.equals("")) {
			validateResult.put("message", field.getName() + annotation.message());
			validateResult.put("result", false);
		} else {
			validateResult.put("message", "验证通过");
			validateResult.put("result", true);
		}
		return validateResult;
	}
	
	/**
	 * 验证最小值
	 * 
	 * @author gongjin
	 * @param value 参数值
	 * @param field 字段
	 * @return
	 */
	public Map<String, Object> minLen(Object value, Field field) {
		Map<String, Object> validateResult = new HashMap<String, Object>();
		MinSize annotation = field.getAnnotation(MinSize.class);
		if(value != null && value.toString().trim().length() < annotation.minLen()) {
			validateResult.put("message", field.getName() + annotation.message());
			validateResult.put("result", false);
		} else {
			validateResult.put("message", "验证通过");
			validateResult.put("result", true);
		}
		return validateResult;
	}
	
	/**
	 * 验证最大值
	 * 
	 * @author gongjin
	 * @param value 参数值
	 * @param field 字段
	 * @return
	 */
	public Map<String, Object> max(Object value, Field field) {
		Map<String, Object> validateResult = new HashMap<String, Object>();
		MaxSize annotation = field.getAnnotation(MaxSize.class);
		if(value != null && value.toString().trim().length() > annotation.max()) {
			validateResult.put("message", field.getName() + annotation.message());
			validateResult.put("result", false);
		} else {
			validateResult.put("message", "验证通过");
			validateResult.put("result", true);
		}
		return validateResult;
	}

	/**
	 * 必须为整数
	 * @param value
	 * @param field
	 * @return
	 */
	public Map<String,Object> isNum(Object value, Field field){
		Map<String, Object> validateResult = new HashMap<String, Object>();
		IsNum annotation = field.getAnnotation(IsNum.class);
		if(value != null){
			try{
				Integer.valueOf(value.toString());
			}catch (Exception e){
				validateResult.put("message", field.getName()+annotation.message());
				validateResult.put("result", false);
			}
		} else {
			validateResult.put("message", "验证通过");
			validateResult.put("result", true);
		}
		return validateResult;
	}

	/**
	 * 金额不能为负数
	 * @param value
	 * @param field
	 * @return
	 */
	public Map<String,Object> bigDecimalFormat(Object value, Field field){
		Map<String, Object> validateResult = new HashMap<String, Object>();
		BigDecimalFormat annotation = field.getAnnotation(BigDecimalFormat.class);
		if(value != null){
			try{
				BigDecimal money = BigDecimal.valueOf(Double.valueOf(value.toString()));
				if(money != null && money.compareTo(BigDecimal.valueOf(0)) == -1){
					validateResult.put("message", field.getName()+annotation.message());
					validateResult.put("result", false);
				}
			}catch (Exception e){
				validateResult.put("message", field.getName()+"金额格式不正确");
				validateResult.put("result", false);
			}

		}else{
			validateResult.put("message", "验证通过");
			validateResult.put("result", true);
		}
		return validateResult;
	}


}
