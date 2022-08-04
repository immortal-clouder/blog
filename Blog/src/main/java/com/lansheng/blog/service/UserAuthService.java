package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.dto.UserAreaDTO;
import com.lansheng.blog.dto.UserBackDTO;
import com.lansheng.blog.dto.UserInfoDTO;
import com.lansheng.blog.entity.UserAuth;
import com.lansheng.blog.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
    /**
     * 获取用户区域分布
     *
     * @param conditionVO 条件签证官
     * @return {@link List <UserAreaDTO>} 用户区域分布
     */
    List<UserAreaDTO> listUserAreas(ConditionVO conditionVO);

    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    void register(UserVO user);

    /**
     * qq登录
     *
     * @param qqLoginVO qq登录信息
     * @return 用户登录信息
     */
    UserInfoDTO qqLogin(QQLoginVO qqLoginVO);

    /**
     * 微博登录
     *
     * @param weiboLoginVO 微博登录信息
     * @return 用户登录信息
     */
    UserInfoDTO weiboLogin(WeiboLoginVO weiboLoginVO);

    /**
     * 修改密码
     *
     * @param user 用户对象
     */
    void updatePassword(UserVO user);

    /**
     * 修改管理员密码
     *
     * @param passwordVO 密码对象
     */
    void updateAdminPassword(PasswordVO passwordVO);

    /**
     * 查询后台用户列表
     *
     * @param condition 条件
     * @return 用户列表
     */
    PageResult<UserBackDTO> listUserBackDTO(ConditionVO condition);

}
