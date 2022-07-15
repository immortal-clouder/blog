package com.lansheng.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * @description: 区域枚举
 * @author: 兰生
 * @date: 2022/07/14 23:09
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ZoneEnum {
    /**
     * 上海
     */
    SHANGHAI("Asia/Shanghai", "中国上海");

    /**
     * 时区
     */
    private final String zone;

    /**
     * 描述
     */
    private final String desc;


}
