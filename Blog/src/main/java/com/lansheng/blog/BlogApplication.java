package com.lansheng.blog;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 启动类
 * @author: 兰生
 * @date: 2022/07/13 19:17
 * @version: 1.0
 */
@MapperScan("com.lansheng.blog.dao")
@SpringBootApplication
@EnableScheduling  //定时任务在配置类上添加@EnableScheduling开启对定时任务的支持，在相应的方法上添加@Scheduled声明需要执行的定时任务
public class BlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
