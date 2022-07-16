package com.lansheng.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lansheng.blog.entity.UserInfo;
import org.springframework.stereotype.Repository;
/**
 * @description: 用户信息
 * @author: 兰生
 * @date: 2022/07/16 11:08
 * @version: 1.0
 */
@Repository
public interface UserInfoDao extends BaseMapper<UserInfo> {

}
