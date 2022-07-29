package com.lansheng.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lansheng.blog.dao.UserAuthDao;
import com.lansheng.blog.dto.EmailDTO;
import com.lansheng.blog.entity.UserAuth;
import com.lansheng.blog.exception.BizException;
import com.lansheng.blog.service.RedisService;
import com.lansheng.blog.service.UserAuthService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lansheng.blog.constant.MQPrefixConst.EMAIL_EXCHANGE;
import static com.lansheng.blog.constant.RedisPrefixConst.CODE_EXPIRE_TIME;
import static com.lansheng.blog.constant.RedisPrefixConst.USER_CODE_KEY;
import static com.lansheng.blog.utils.CommonUtils.checkEmail;
import static com.lansheng.blog.utils.CommonUtils.getRandomCode;

/**
 * @description: 用户账号服务
 * @author: 兰生
 * @date: 2022/07/17
 * @version: 1.0
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthDao, UserAuth> implements UserAuthService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisService redisService;


    @Override
    public void sendCode(String username) {
        // 使用公共工具类的checkEmail()方法校验账号是否合法
        if (!checkEmail(username)) {
            throw new BizException("请输入正确邮箱");
        }
        // 然后生成六位随机验证码用于发送
        String code = getRandomCode();
        // 将用于发送的信息封装进EmailDTO对象中（包括要发往的邮箱账号、要发送的验证码，邮件主题、邮件内容）
        EmailDTO emailDTO = EmailDTO.builder()
                .email(username)
                .subject("验证码")
                .content("您的验证码为 " + code + " 有效期15分钟，请不要告诉他人哦！")
                .build();
        //将emailDTO推送到rabbitMQ服务器上，等待匹配的消费者消费，获取emailDTO
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        // 将验证码存入redis，设置过期时间为15分钟
        redisService.set(USER_CODE_KEY + username, code, CODE_EXPIRE_TIME);
    }




}
