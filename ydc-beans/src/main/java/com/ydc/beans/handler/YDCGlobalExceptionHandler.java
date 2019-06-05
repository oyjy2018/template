package com.ydc.beans.handler;

import com.alibaba.fastjson.JSONObject;
import com.ydc.commom.exception.ServiceRuntimeException;
import com.ydc.commom.result.Result;
import com.ydc.commom.result.ResultConstant;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@ResponseBody
public class YDCGlobalExceptionHandler extends DefaultHandlerExceptionResolver {


        private static final String logExceptionFormat = "活捉一枚异常: 类名: %s 异常详细信息: %s";
        private static Logger log = LogManager.getLogger(YDCGlobalExceptionHandler.class);


        //运行时异常
        @ExceptionHandler(ServiceRuntimeException.class)
        public String serviceRuntimeException(ServiceRuntimeException ex) {
            ex.printStackTrace();
            log.error(String.format(logExceptionFormat,  ex.getClass(), ex.getMessage()),ex);
            if (ex.getCode()==0){
                return Result.exception(ResultConstant.RESULT_CODE_SERVICERUNTIMEEXCEPTION, ex.getMessage()).toJSON();
            }else {
                return Result.exception(ex.getCode(), ex.getMessage()).toJSON();
            }

        }
        //运行时异常
        @ExceptionHandler(RuntimeException.class)
        public String runtimeExceptionHandler(RuntimeException ex) {
            return resultFormat(ex);
        }

        //空指针异常
        @ExceptionHandler(NullPointerException.class)
        public String nullPointerExceptionHandler(NullPointerException ex) {
            return resultFormat(ex);
        }

        //类型转换异常
        @ExceptionHandler(ClassCastException.class)
        public String classCastExceptionHandler(ClassCastException ex) {
            return resultFormat( ex);
        }

        //IO异常
        @ExceptionHandler(IOException.class)
        public String iOExceptionHandler(IOException ex) {
            return resultFormat( ex);
        }

        //未知方法异常
        @ExceptionHandler(NoSuchMethodException.class)
        public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
            return resultFormat( ex);
        }

        //数组越界异常
        @ExceptionHandler(IndexOutOfBoundsException.class)
        public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
            return resultFormat( ex);
        }

        //400错误
        @ExceptionHandler({HttpMessageNotReadableException.class})
        public String requestNotReadable(HttpMessageNotReadableException ex) {
          log.error("400..requestNotReadable");
            return resultFormat( ex);
        }

        //400错误
        @ExceptionHandler({TypeMismatchException.class})
        public String requestTypeMismatch(TypeMismatchException ex) {
            log.error("400..TypeMismatchException");
            return resultFormat( ex);
        }

        //400错误
        @ExceptionHandler({MissingServletRequestParameterException.class})
        public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
            log.error("400..MissingServletRequest");
            return resultFormat( ex);
        }

        //405错误
        @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
        public String request405(HttpRequestMethodNotSupportedException ex) {
            return resultFormat(ex);
        }

        //406错误
        @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
        public String request406(HttpMediaTypeNotAcceptableException ex) {
            log.error("406...");
            return resultFormat( ex);
        }

        //500错误
        @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
        public String server500(RuntimeException ex) {
            log.error("500...");
            return resultFormat( ex);
        }

        //栈溢出
        @ExceptionHandler({StackOverflowError.class})
        public String requestStackOverflow(StackOverflowError ex) {
            return resultFormat( ex);
        }

        //其他错误
        @ExceptionHandler({Exception.class})
        public String exception(Exception ex) {
            return resultFormat( ex);
        }

        private <T extends Throwable> String resultFormat(int code, T ex) {
            ex.printStackTrace();
            log.error(String.format(logExceptionFormat,  ex.getClass(), ex.getMessage()),ex);
            return Result.exception(code, "请稍后重试").toJSON();
        }



        private <T extends Throwable> String resultFormat( T ex) {
            ex.printStackTrace();
            log.error(String.format(logExceptionFormat, ex.getClass(), ex.getMessage()),ex);
            return Result.exception(ResultConstant.RESULT_CODE_EXCEPTION,"请稍后重试").toJSON();
        }

        //没有权限访问
        @ExceptionHandler(value = AuthorizationException.class)
        public String authorizationException(HttpServletRequest request,HttpServletResponse servletResponse,Exception e){
           logger.info("该角色没有访问权限");
            log.error(String.format(logExceptionFormat, e.getClass(), e.getMessage()),e);
            return Result.exception(ResultConstant.RESULT_CODE_EXCEPTION,"该角色没有访问权限").toJSON();
        }

    }

