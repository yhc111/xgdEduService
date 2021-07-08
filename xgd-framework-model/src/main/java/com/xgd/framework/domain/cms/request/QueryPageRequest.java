package com.xgd.framework.domain.cms.request;

import com.xgd.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/13 8:40
 */
@Data
public class QueryPageRequest extends RequestData {
    // 接受页面查询的查询条件
    // 站点id
    @ApiModelProperty("站点id")
    private String siteId;
    // 页面id
    @ApiModelProperty("页面ID")
    private String pageId;
    // 页面名称
    @ApiModelProperty("页面名称")
    private String pageName;
    // 别名
    @ApiModelProperty("页面别名")
    private String pageAliase;
    // 模版id
    @ApiModelProperty("模版id")
    private String templateId;
}
