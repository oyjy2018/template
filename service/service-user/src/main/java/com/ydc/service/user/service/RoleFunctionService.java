package com.ydc.service.user.service;

public interface RoleFunctionService {
    /**
     * 获取功能树
     *
     * @param serviceIdentifying
     * @param functionName
     * @return
     */
    String getFunctionTree(String serviceIdentifying, String functionName);
}
