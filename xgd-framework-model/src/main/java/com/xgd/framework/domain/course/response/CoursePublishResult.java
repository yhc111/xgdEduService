package com.xgd.framework.domain.course.response;

import com.xgd.framework.model.response.ResponseResult;
import com.xgd.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Administrator
 * @version 1.0
 **/
@Data
@ToString
@NoArgsConstructor
public class CoursePublishResult extends ResponseResult {

    String previewUrl; // 页面预览的url，必须得到页面id才可以拼装

    public CoursePublishResult(ResultCode resultCode, String previewUrl) {
        super(resultCode);
        this.previewUrl = previewUrl;
    }

}
