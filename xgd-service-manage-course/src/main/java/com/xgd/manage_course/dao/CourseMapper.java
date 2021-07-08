package com.xgd.manage_course.dao;

import com.github.pagehelper.Page;
import com.xgd.framework.domain.course.CourseBase;
import com.xgd.framework.domain.course.ext.CourseInfo;
import com.xgd.framework.domain.course.request.CourseListRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator.
 */
@Mapper
@Repository("courseMapper")
public interface CourseMapper {

   CourseBase findCourseBaseById(String id);

   Page<CourseBase> findCourseList();

   Page<CourseInfo> findCourseListPage(CourseListRequest courseListRequest);

}
