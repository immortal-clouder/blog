package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.entity.UserAuth;
import org.springframework.stereotype.Service;
/**
 * @description: 用户账号服务
 * @author: 兰生
 * @date: 2022/07/17
 * @version: 1.0
 */

public interface UserAuthService extends IService<UserAuth> {
    /**
     * @description: 发送邮箱验证码
     * @author: 兰生
     * @date: 2022/07/17
     * @param: username 邮箱账号
     * @return:
     **/
    void sendCode(String username);
}
