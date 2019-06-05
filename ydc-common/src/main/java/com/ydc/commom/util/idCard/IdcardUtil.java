package com.ydc.commom.util.idCard;


import com.ydc.commom.util.DateUtil;

/**
 * idcard工具类
 *
 * @author gongjin
 * @create 2017-05-08 13:14
 **/
public class IdcardUtil {

    public static final IdcardValidator iv = new IdcardValidator();

    /**
     * 检查身份证号码
     * @param idCard 身份证号码
     * @return
     */
    public static boolean checkIdcard(String idCard){
        return iv.isValidatedAllIdcard(idCard);
    }

    /**
     * 根据身份证号码获得年龄
     * @param idCard 身份证号码
     * @return
     */
    public static int getAgeByIdcard(String idCard){
        IdcardInfoExtractor idcardInfo = new IdcardInfoExtractor(idCard);
        return idcardInfo.getAge();
    }

    /**
     * 根据身份证号码获得性别
     * @param idCard 身份证号码
     * @return 1，男；2，女
     */
    public static String getSexByIdcard(String idCard){
        IdcardInfoExtractor idcardInfo = new IdcardInfoExtractor(idCard);
        String gender = idcardInfo.getGender();
        if("男".equals(gender) || "女".equals(gender)){
        	return gender;
        }
        return "0";
    }

    public static void main(String[] args) {
        String idCard = "610900198804307999";
        System.out.println(IdcardUtil.checkIdcard(idCard));
        System.out.println(IdcardUtil.getAgeByIdcard(idCard));
        System.out.println(IdcardUtil.getSexByIdcard(idCard));
        IdcardInfoExtractor idcardInfo = new IdcardInfoExtractor(idCard);
        System.out.println(DateUtil.formatDate(idcardInfo.getBirthday()));
    }
}
