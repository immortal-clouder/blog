package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.dto.OperationLogDTO;
import com.lansheng.blog.entity.OperationLog;
import com.lansheng.blog.vo.ConditionVO;
import com.lansheng.blog.vo.PageResult;

/**
 * @description: 操作日志服务
 * @author: 兰生
 * @date: 2022/07/17
 * @version: 1.0
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 查询日志列表
     *
     * @param conditionVO 条件
     * @return 日志列表
     */
    PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}
