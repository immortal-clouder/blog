package com.lansheng.blog.consumer;

import com.alibaba.fastjson.JSON;
import com.lansheng.blog.dto.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.lansheng.blog.constant.MQPrefixConst.EMAIL_QUEUE;

/**
 * @description: 通知邮箱
 * @author: 兰生
 * @date: 2022/07/16
 * @version: 1.0
 */
@Component
@RabbitListener(queues = EMAIL_QUEUE)
public class EmailConsumer {

    /**
     * 读取配置文件中的邮箱账号
     */
    @Value("${spring.mail.username}")
    private String email;

    //spring提供的发送邮件的接口：JavaMailSender；通过JavaMailSender可以实现后端发送邮件
    @Resource
    private JavaMailSender javaMailSender;

    //通过模板发送邮件
    @RabbitHandler
    public void process(byte[] data) {
        EmailDTO emailDTO = JSON.parseObject(new String(data), EmailDTO.class);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(emailDTO.getEmail());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getContent());
        javaMailSender.send(message);
    }

}
