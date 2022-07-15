package com.lansheng.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lansheng.blog.entity.OperationLog;
import org.springframework.stereotype.Repository;

/**
 * @description: 操作日志
 * @author: 兰生
 * @date: 2022/07/15 21:21
 * @version: 1.0
 */
@Repository
public interface OperationLogDao extends BaseMapper<OperationLog> {
}
