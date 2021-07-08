package com.xgd.framework.exception;

import com.xgd.framework.model.response.ResultCode;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/19 14:11
 * 自定义异常类型建议继承RuntimeException，这样使得代码没有侵入性，使用其他异常需要try/catch捕获，有一定的侵入性
 */
public class CustomException extends RuntimeException {

    // 错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    // 拿到错误代码信息
    public ResultCode getResultCode() {
        return resultCode;
    }

}
