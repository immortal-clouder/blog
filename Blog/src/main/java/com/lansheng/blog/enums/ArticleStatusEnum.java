package com.lansheng.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 文章状态枚举
 * @author: 兰生
 * @date: 2022/07/14 21:19
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {
    /**
     * 公开
     */
    PUBLIC(1, "公开"),
    /**
     * 私密
     */
    SECRET(2, "私密"),
    /**
     * 草稿
     */
    DRAFT(3, "草稿");

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String desc;

}
