package com.lansheng.blog.strategy;

import com.lansheng.blog.dto.UserInfoDTO;

/**
 * @description: 第三方登录
 * @author: 兰生
 * @date: 2022/07/25
 * @version: 1.0
 */
public interface SocialLoginStrategy {

    /**
     * 登录
     *
     * @param data 数据
     * @return {@link UserInfoDTO} 用户信息
     */
    UserInfoDTO login(String data);

}
