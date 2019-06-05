package com.ydc.cgj.web.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * excel模板导入
 *
 * @author gongjin
 * @create 2018-09-19 9:59
 **/
public interface ImportService {

    /**
     * 会员积分导入
     *
     * @param fileName
     * @param file
     */
    String integralImport(String fileName, MultipartFile file, Integer userId) throws Exception;
}
