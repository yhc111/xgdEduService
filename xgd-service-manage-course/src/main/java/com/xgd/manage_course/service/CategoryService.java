package com.xgd.manage_course.service;

import com.xgd.framework.domain.course.ext.CategoryNode;
import com.xgd.manage_course.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    //查询分类
    public CategoryNode findList(){
        return categoryMapper.selectList();
    }
}
