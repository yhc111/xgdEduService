package com.xgd.framework.domain.learning.respones;

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
// 如果要进行远程调用需要一个无参的构造方法
@NoArgsConstructor
public class GetMediaResult extends ResponseResult {
    //视频播放地址
    String fileUrl;
    public GetMediaResult(ResultCode resultCode,String fileUrl){
        // resultCode的值给父类
        super(resultCode);
        this.fileUrl = fileUrl;
    }
}
