package com.lansheng.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lansheng.blog.dao.OperationLogDao;
import com.lansheng.blog.dto.OperationLogDTO;
import com.lansheng.blog.entity.OperationLog;
import com.lansheng.blog.service.OperationLogService;
import com.lansheng.blog.utils.BeanCopyUtils;
import com.lansheng.blog.utils.PageUtils;
import com.lansheng.blog.vo.ConditionVO;
import com.lansheng.blog.vo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 操作日志服务
 * @author: 兰生
 * @date: 2022/07/17
 * @version: 1.0
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogDao, OperationLog> implements OperationLogService {

    @Override
    public PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO) {
        Page<OperationLog> page = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        // 查询日志列表
        Page<OperationLog> operationLogPage = this.page(page, new LambdaQueryWrapper<OperationLog>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), OperationLog::getOptModule, conditionVO.getKeywords())
                .or()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), OperationLog::getOptDesc, conditionVO.getKeywords())
                .orderByDesc(OperationLog::getId));
        List<OperationLogDTO> operationLogDTOList = BeanCopyUtils.copyList(operationLogPage.getRecords(), OperationLogDTO.class);
        return new PageResult<>(operationLogDTOList, (int) operationLogPage.getTotal());
    }

}
