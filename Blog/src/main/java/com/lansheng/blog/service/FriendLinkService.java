package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.dto.FriendLinkBackDTO;
import com.lansheng.blog.dto.FriendLinkDTO;
import com.lansheng.blog.entity.FriendLink;
import com.lansheng.blog.vo.ConditionVO;
import com.lansheng.blog.vo.FriendLinkVO;
import com.lansheng.blog.vo.PageResult;

import java.util.List;

/**
 * @description: 友链服务
 * @author: 兰生
 * @date: 2022/07/19
 * @version: 1.0
 */
public interface FriendLinkService extends IService<FriendLink> {

    /**
     * 查看友链列表
     *
     * @return 友链列表
     */
    List<FriendLinkDTO> listFriendLinks();

    /**
     * 查看后台友链列表
     *
     * @param condition 条件
     * @return 友链列表
     */
    PageResult<FriendLinkBackDTO> listFriendLinkDTO(ConditionVO condition);

    /**
     * 保存或更新友链
     *
     * @param friendLinkVO 友链
     */
    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}
