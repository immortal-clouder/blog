package com.lansheng.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 兰生
 * @date: 2022/07/16
 * @version: 1.0
 */
@Data
public class ResourceRoleDTO {

    /**
     * 资源id
     */
    private Integer id;

    /**
     * 路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 角色名
     */
    private List<String> roleList;

}
