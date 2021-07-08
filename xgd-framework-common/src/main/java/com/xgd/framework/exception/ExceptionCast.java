package com.xgd.framework.exception;

import com.xgd.framework.model.response.ResultCode;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/19 14:18
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
