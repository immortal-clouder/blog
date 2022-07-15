package com.lansheng.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 说说状态枚举
 * @author: 兰生
 * @date: 2022/07/14 22:39
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum TalkStatusEnum {
    /**
     * 公开
     */
    PUBLIC(1, "公开"),
    /**
     * 私密
     */
    SECRET(2, "私密");

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String desc;

}
