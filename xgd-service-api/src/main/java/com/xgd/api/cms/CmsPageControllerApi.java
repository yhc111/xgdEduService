package com.xgd.api.cms;

import com.xgd.framework.domain.cms.CmsPage;
import com.xgd.framework.domain.cms.request.QueryPageRequest;
import com.xgd.framework.domain.cms.response.CmsPageResult;
import com.xgd.framework.domain.course.response.CmsPostPageResult;
import com.xgd.framework.model.response.QueryResponseResult;
import com.xgd.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/13 8:50
 */
// swagger的一些相关注解
@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {

    // 分页查询页面列表
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
                    })

    // 页面查询
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    // 新增页面
    @ApiOperation("新增页面")
    public CmsPageResult add(CmsPage cmsPage);

    // 根据页面id查询页面信息
    @ApiOperation("根据页面id查询页面信息")
    public CmsPage findById(String id);

    // 修改页面
    @ApiOperation("修改页面")
    public CmsPageResult edit(String id, CmsPage cmsPage);

    // 修改页面
    @ApiOperation("修改页面")
    public ResponseResult delete(String id);

    // 页面发布
    @ApiOperation("页面发布")
    public ResponseResult post(String pageId);

    // 保存页面
    @ApiOperation("保存页面")
    public CmsPageResult save(CmsPage cmsPage);

    // 一键发布页面
    @ApiOperation("一键发布页面")
    public CmsPostPageResult postPageQuick(CmsPage cmsPage);

}
