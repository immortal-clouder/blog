package com.lansheng.blog.controller;

import com.lansheng.blog.annotation.AccessLimit;
import com.lansheng.blog.service.UserAuthService;
import com.lansheng.blog.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户账号控制器
 * @author: 兰生
 * @date: 2022/07/17
 * @version: 1.0
 */
@Api(tags = "用户账号模块")
@RestController
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;

    /**
     * @description: 发送邮箱验证码
     * @author: 兰生
     * @date: 2022/07/17
     * @param: username 用户名（邮箱账号）
     * @return: {@link Result<>}
     **/
    @AccessLimit(seconds = 60, maxCount = 1)
    @ApiOperation(value = "发送邮箱验证码")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/users/code")
    public Result<?> sendCode(String username) {
        userAuthService.sendCode(username);
        return Result.ok();
    }
}
