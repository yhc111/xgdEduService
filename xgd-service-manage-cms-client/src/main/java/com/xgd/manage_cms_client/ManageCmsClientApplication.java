package com.xgd.manage_cms_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * @version 1.0
 * 页面发布消费方，将Cms Client部署在多个服务器上，负责接收到页面发布的消息后
 * 从GridFS中下载文件在本地保存
 **/
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms")//扫描实体类
@ComponentScan(basePackages={"com.xgd.framework"})//扫描common包下的类
@ComponentScan(basePackages={"com.xgd.manage_cms_client"})//扫描本项目下的所有类
public class ManageCmsClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsClientApplication.class,args);
    }
}
