package com.xgd.search.controller;

import com.xgd.api.search.EsCourseControllerApi;
import com.xgd.framework.domain.course.CoursePub;
import com.xgd.framework.domain.course.TeachplanMediaPub;
import com.xgd.framework.domain.search.CourseSearchParam;
import com.xgd.framework.model.response.QueryResponseResult;
import com.xgd.framework.model.response.QueryResult;
import com.xgd.search.service.EsCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/search/course")
public class EsCourseController implements EsCourseControllerApi {

    @Autowired
    EsCourseService esCourseService;

    @Override
    @GetMapping(value="/list/{page}/{size}")
    public QueryResponseResult<CoursePub> list(@PathVariable("page") int page,@PathVariable("size") int size, CourseSearchParam courseSearchParam) {
        return esCourseService.list(page,size,courseSearchParam);
    }

    @Override
    @GetMapping("/getall/{id}")
    public Map<String, CoursePub> getall(String id) {
        return esCourseService.getall(id);
    }

    @Override
    @GetMapping(value="/getmedia/{teachplanId}")
    public TeachplanMediaPub getmedia(@PathVariable("teachplanId") String teachplanId) {
        //将一个id加入数组，传给service方法
        String[] teachplanIds = new String[]{teachplanId};
        QueryResponseResult<TeachplanMediaPub> queryResponseResult = esCourseService.getmedia(teachplanIds);
        QueryResult<TeachplanMediaPub> queryResult = queryResponseResult.getQueryResult();
        if(queryResult != null){
            List<TeachplanMediaPub> list = queryResult.getList();
            if(list !=null && list.size()>0){
                return list.get(0);
            }
        }
        return new TeachplanMediaPub();
    }
}
