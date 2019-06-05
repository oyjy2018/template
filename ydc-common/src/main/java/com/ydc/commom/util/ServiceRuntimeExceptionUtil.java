package com.ydc.commom.util;

import com.ydc.commom.exception.ServiceRuntimeException;

/**
 * 异常
 *
 * @create 2018-09-11 15:29
 **/
public class ServiceRuntimeExceptionUtil {

    public static void serviceRuntimeException(Object key,String error) throws ServiceRuntimeException {
        if(StringUtil.isEmpty(key))
            throw new ServiceRuntimeException(error);
    }
}
