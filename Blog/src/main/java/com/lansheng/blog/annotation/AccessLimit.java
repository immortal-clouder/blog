package com.lansheng.blog.annotation;

import java.lang.annotation.*;

/**
 * @description: redis限流
 * @author: 兰生
 * @date: 2022/07/14 20:22
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /**
     * 单位时间（秒）
     *
     * @return int
     */
    int seconds();

    /**
     * 单位时间最大请求次数
     *
     * @return int
     */
    int maxCount();
}
