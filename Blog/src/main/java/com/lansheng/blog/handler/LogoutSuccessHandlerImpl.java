package com.lansheng.blog.handler;

import com.alibaba.fastjson.JSON;
import com.lansheng.blog.vo.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lansheng.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * @description: 安全注销处理器
 * @author: 兰生
 * @date: 2022/07/15
 * @version: 1.0
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.ok()));
    }

}
