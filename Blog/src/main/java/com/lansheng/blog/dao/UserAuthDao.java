package com.lansheng.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lansheng.blog.dto.UserBackDTO;
import com.lansheng.blog.entity.UserAuth;
import com.lansheng.blog.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 用户账号
 * @author: 兰生
 * @date: 2022/07/16 11:02
 * @version: 1.0
 */
@Repository
public interface UserAuthDao extends BaseMapper<UserAuth> {
    /**
     * 查询后台用户列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List <UserBackDTO>} 用户列表
     */
    List<UserBackDTO> listUsers(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 查询后台用户数量
     *
     * @param condition 条件
     * @return 用户数量
     */
    Integer countUser(@Param("condition") ConditionVO condition);
}
