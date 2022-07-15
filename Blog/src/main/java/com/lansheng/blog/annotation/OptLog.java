package com.lansheng.blog.annotation;

import java.lang.annotation.*;

/**
 * @description: 操作日志注解
 * @author: 兰生
 * @date: 2022/07/14 20:59
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    /**
     * @return 操作类型
     */
    String optType() default "";

}
