package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.dto.MessageBackDTO;
import com.lansheng.blog.dto.MessageDTO;
import com.lansheng.blog.entity.Message;
import com.lansheng.blog.vo.ConditionVO;
import com.lansheng.blog.vo.MessageVO;
import com.lansheng.blog.vo.PageResult;
import com.lansheng.blog.vo.ReviewVO;

import java.util.List;

/**
 * @description: 留言服务
 * @author: 兰生
 * @date: 2022/07/20
 * @version: 1.0
 */
public interface MessageService extends IService<Message> {

    /**
     * 添加留言弹幕
     *
     * @param messageVO 留言对象
     */
    void saveMessage(MessageVO messageVO);

    /**
     * 查看留言弹幕
     *
     * @return 留言列表
     */
    List<MessageDTO> listMessages();

    /**
     * 审核留言
     *
     * @param reviewVO 审查签证官
     */
    void updateMessagesReview(ReviewVO reviewVO);

    /**
     * 查看后台留言
     *
     * @param condition 条件
     * @return 留言列表
     */
    PageResult<MessageBackDTO> listMessageBackDTO(ConditionVO condition);

}
