package com.lansheng.blog.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: springboot跨域请求配置和拦截器配置
 * @author: 兰生
 * @date: 2022/07/14 18:32
 * @version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

//    @Bean
//    public WebSecurityHandler getWebSecurityHandler() {
//        return new WebSecurityHandler();
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //设置允许跨域的路径
                .allowCredentials(true) //是否允许证书 不再默认开启
                .allowedHeaders("*")    //设置头部允许的自定义参数
                .allowedOriginPatterns("*")  //设置允许跨域请求的域名
                .allowedMethods("*");   //设置允许的方法
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
        //设置登录拦截器，拦截顺序就是设置时的顺序
//        registry.addInterceptor(new PageableHandlerInterceptor());
//        registry.addInterceptor(getWebSecurityHandler());
//    }

}
