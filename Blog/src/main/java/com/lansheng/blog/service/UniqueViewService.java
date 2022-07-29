package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.dto.UniqueViewDTO;
import com.lansheng.blog.entity.UniqueView;

import java.util.List;

/**
 * @description: 用户量统计
 * @author: 兰生
 * @date: 2022/07/17
 * @version: 1.0
 */
public interface UniqueViewService extends IService<UniqueView> {

    /**
     * 获取7天用户量统计
     *
     * @return 用户量
     */
    List<UniqueViewDTO> listUniqueViews();

}
