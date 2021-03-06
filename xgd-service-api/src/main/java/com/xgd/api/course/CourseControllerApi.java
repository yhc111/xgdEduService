package com.xgd.api.course;

import com.xgd.framework.domain.course.CoursePic;
import com.xgd.framework.domain.course.Teachplan;
import com.xgd.framework.domain.course.TeachplanMedia;
import com.xgd.framework.domain.course.ext.CourseInfo;
import com.xgd.framework.domain.course.ext.CourseView;
import com.xgd.framework.domain.course.ext.TeachplanNode;
import com.xgd.framework.domain.course.request.CourseListRequest;
import com.xgd.framework.domain.course.response.CoursePublishResult;
import com.xgd.framework.model.response.QueryResponseResult;
import com.xgd.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 * 课程管理相关接口
 */

@Api(value="课程管理接口",description = "课程管理接口，提供课程的增、删、改、查")
public interface CourseControllerApi {
    @ApiOperation("课程计划查询")
    public TeachplanNode findTeachplanList(String courseId);

    @ApiOperation("添加课程计划")
    public ResponseResult addTeachplan(Teachplan teachplan);

    @ApiOperation("添加课程图片")
    public ResponseResult addCoursePic(String courseId,String pic);

    @ApiOperation("查询我的课程列表")
    public QueryResponseResult<CourseInfo> findCourseList(
            int page,
            int size,
            CourseListRequest courseListRequest
    );

    @ApiOperation("查询课程图片")
    public CoursePic findCoursePic(String courseId);

    @ApiOperation("删除课程图片")
    public ResponseResult deleteCoursePic(String courseId);

    @ApiOperation("课程视图查询")
    public CourseView courseview(String id);

    @ApiOperation("课程预览")
    public CoursePublishResult preview(String id);

    @ApiOperation("课程发布")
    public CoursePublishResult publish(String id);

    @ApiOperation("保存课程计划与媒资文件关联信息")
    public ResponseResult savemedia(TeachplanMedia teachplanMedia);

}
