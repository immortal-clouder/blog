//package com.lansheng.blog.strategy.context;
//
//import com.lansheng.blog.dto.UserInfoDTO;
//import com.lansheng.blog.enums.LoginTypeEnum;
//import com.lansheng.blog.strategy.SocialLoginStrategy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//
///**
// * @description: 第三方登录策略上下文
// * @author: 兰生
// * @date: 2022/07/25
// * @version: 1.0
// */
//@Service
//public class SocialLoginStrategyContext {
//
//    @Autowired
//    private Map<String, SocialLoginStrategy> socialLoginStrategyMap;
//
//    /**
//     * 执行第三方登录策略
//     *
//     * @param data          数据
//     * @param loginTypeEnum 登录枚举类型
//     * @return {@link UserInfoDTO} 用户信息
//     */
//    public UserInfoDTO executeLoginStrategy(String data, LoginTypeEnum loginTypeEnum) {
//        return socialLoginStrategyMap.get(loginTypeEnum.getStrategy()).login(data);
//    }
//
//}
