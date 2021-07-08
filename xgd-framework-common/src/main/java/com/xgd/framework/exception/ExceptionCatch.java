package com.xgd.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xgd.framework.model.response.CommonCode;
import com.xgd.framework.model.response.ResponseResult;
import com.xgd.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/19 14:20
 */
@ControllerAdvice // 通过控制器增强方法捕获异常
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    // 定义map，配置异常类型所对应的错误代码
    // 谷歌工具包中的一个类型，数据一旦放进去是不可更改的，只读，线程安全
    // key异常类型，value对应错误代码
    //使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点的一旦创建不可改变，并且线程安全
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //使用builder来构建一个异常类型和错误代码的异常
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder =
            ImmutableMap.builder();

    // 捕获代码CustomException此类异常
    // 加上此注解，并指定捕获哪一类异常，那么就可以捕获到
    @ExceptionHandler(CustomException.class)
    // 转成json字符串
    @ResponseBody
    public ResponseResult customException(CustomException customException) {
        // 记录日志
        LOGGER.error("catch exception:{}",customException.getMessage());
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }

    // 转成json字符串
    @ResponseBody
    // 加上此注解，并指定捕获哪一类异常，那么就可以捕获到
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception exception) {
        // 记录日志
        LOGGER.error("catch exception:{}",exception.getMessage());
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build(); // EXCEPTIONS构建成功，不能更改
        }
        // 从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到响应99999异常
        final ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        final ResponseResult responseResult;
        if (resultCode != null) {
            responseResult = new ResponseResult(resultCode);
        } else {
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return responseResult;
    }

    static {
        // 定义异常类型所对应的错误代码
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }

}
