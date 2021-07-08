package com.xgd.manage_cms.dao;

import com.xgd.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/13 10:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }

    // 分页查询
    @Test
    public void testFindPage(){
        // 分页参数
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }

    // 自定义条件查询测试
    @Test
    public void testFindAllByExample(){
        // 分页参数
        int page = 0; // 从0开始
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        // 条件值对象
        CmsPage cmsPage = new CmsPage();
        // 要查询5a751fab6abb5044e0d19ea1站点的页面
        // cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        // 设置模板id条件
        // cmsPage.setTemplateId("5ad9a24d68db5239b8fef199");
        // 设置页面别名
        cmsPage.setPageAliase("轮播");
        // 条件匹配器
        // 可以定义很多类型匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        // 定义Example，存放条件的类型
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        List<CmsPage> content = all.getContent();
        System.out.println(content);
    }

    // 修改
    @Test
    public void testUpdate(){

        // 查询对象
        Optional<CmsPage> byId = cmsPageRepository.findById("5adaa4d868db526288d3df3b");
        if (byId.isPresent()) {
            CmsPage cmsPage = byId.get();
            // 设置要修改的值
            cmsPage.setPageAliase("test01");
            // ...
            // 修改
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);
        }
    }

    // 根据页面名称查询
    @Test
    public void testfindByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("测试页面");
        System.out.println(cmsPage);
    }

}



