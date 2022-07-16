package com.lansheng.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lansheng.blog.entity.UserAuth;
import org.springframework.stereotype.Repository;
/**
 * @description: 用户账号
 * @author: 兰生
 * @date: 2022/07/16 11:02
 * @version: 1.0
 */
@Repository
public interface UserAuthDao extends BaseMapper<UserAuth> {

}
