package com.lansheng.blog.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.lansheng.blog.enums.ZoneEnum.SHANGHAI;
/**
 * @description: 自动填充时间信息
 * @author: 兰生
 * @date: 2022/07/14 22:27
 * @version: 1.0
 */
@Log4j2 //lombok的注解，用于打印日志
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //创建时间自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())));
    }
    //更新时间自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())));
    }

}