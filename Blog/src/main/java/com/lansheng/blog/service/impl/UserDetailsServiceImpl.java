package com.lansheng.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lansheng.blog.dao.RoleDao;
import com.lansheng.blog.dao.UserAuthDao;
import com.lansheng.blog.dao.UserInfoDao;
import com.lansheng.blog.dto.UserDetailDTO;
import com.lansheng.blog.entity.UserAuth;
import com.lansheng.blog.entity.UserInfo;
import com.lansheng.blog.exception.BizException;
import com.lansheng.blog.service.RedisService;
import com.lansheng.blog.utils.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.swing.StringUIClientPropertyKey;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import static com.lansheng.blog.constant.RedisPrefixConst.*;
import static com.lansheng.blog.enums.ZoneEnum.SHANGHAI;
/**
 * @description: 用户详细信息服务
 * @author: 兰生
 * @date: 2022/07/16 11:01
 * @version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    //用于获取用户账号信息，判断用户是否存在；不存在则报异常；
    // 存在则调用convertUserDetail方法将用户个人信息、用户角色信息、账号点赞信息及设备信息封装到UserDetailDTO中并返回
    private UserAuthDao userAuthDao;
    @Autowired
    //用于获取用户个人信息
    private UserInfoDao userInfoDao;
    @Autowired
    //用于获取用户角色信息
    private RoleDao roleDao;
    @Autowired
    //用户获取账号点赞信息
    private RedisService redisService;
    @Resource
    //用于获取设备信息
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)){
            throw new BizException("用户名不能为空");
        }
        //根据username（账号）查询用户是否存在
        UserAuth userAuth = userAuthDao.selectOne(new LambdaQueryWrapper<UserAuth>()
        .select(UserAuth::getId,UserAuth::getUserInfoId,UserAuth::getUsername,UserAuth::getPassword,UserAuth::getLoginType)
        .eq(UserAuth::getUsername,username));
        if (Objects.isNull(userAuth)){
            throw new BizException("用户名不存在");
        }

        //封装登录信息
        return convertUserDetail(userAuth, request);
    }

    /**
     * @description: 封装用户登录信息
     * @author: 兰生
     * @date: 2022/07/16
     * @param: userAuth 用户账号信息
     * @param: request 请求
     * @return: UserDetailDTO 用户登录信息
     **/
    public UserDetailDTO convertUserDetail(UserAuth userAuth,HttpServletRequest request){
        //通过账号信息中的userInfoId查询登录账号对应的用户信息
        UserInfo userInfo = userInfoDao.selectById(userAuth.getUserInfoId());
        //根据用户id查询用户的角色列表
        List<String> roleList = roleDao.listRolesByUserInfoId(userInfo.getId());

        //从redis中通过key获取用户账号点赞信息
        //点赞信息分三种，分别是文章点赞，评论点赞，说说点赞
        //这三种的可以的格式都是各自的常量名称+用户id
        Set<Object> articleLikeSet = redisService.sMembers(ARTICLE_USER_LIKE+userInfo.getId());
        Set<Object> commentLikeSet = redisService.sMembers(COMMENT_USER_LIKE+userInfo.getId());
        Set<Object> talkLikeSet = redisService.sMembers(TALK_USER_LIKE+userInfo.getId());

        //获取设备信息
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAgent userAgent = IpUtils.getUserAgent(request);

        // 封装权限集合
        return UserDetailDTO.builder()
                .id(userAuth.getId())
                .loginType(userAuth.getLoginType())
                .userInfoId(userInfo.getId())
                .username(userAuth.getUsername())
                .password(userAuth.getPassword())
                .email(userInfo.getEmail())
                .roleList(roleList)
                .nickname(userInfo.getNickname())
                .avatar(userInfo.getAvatar())
                .intro(userInfo.getIntro())
                .webSite(userInfo.getWebSite())
                .articleLikeSet(articleLikeSet)
                .commentLikeSet(commentLikeSet)
                .talkLikeSet(talkLikeSet)
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .isDisable(userInfo.getIsDisable())
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOperatingSystem().getName())
                .lastLoginTime(LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())))
                .build();
    }
}
