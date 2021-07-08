package com.xgd.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/13 9:32
 */
@EnableDiscoveryClient // 表示一个EurekaClient客户端从EurekaServer发现服务
@SpringBootApplication
// 扫描其他的包中在本模块中的重要组件
@EntityScan("com.xuecheng.framework.domain.cms") // 扫描实体类
@ComponentScan(basePackages = {"com.xgd.api"}) // 扫描接口
@ComponentScan(basePackages = {"com.xgd.framework"}) // 扫描common包下的类
@ComponentScan(basePackages = {"com.xgd.manage_cms"}) // 扫描本项目下的所有类
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class, args);
    }

    // 远程请求接口，第四天内容
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
