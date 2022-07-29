package com.lansheng.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lansheng.blog.dto.ResourceRoleDTO;
import com.lansheng.blog.dto.RoleDTO;
import com.lansheng.blog.entity.Role;
import com.lansheng.blog.entity.UserRole;
import com.lansheng.blog.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 角色
 * @author: 兰生
 * @date: 2022/07/16 11:14
 * @version: 1.0
 */
@Repository
public interface RoleDao extends BaseMapper<Role> {
    /**
     * @description: 根据用户id查询用户的角色标签
     * @author: 兰生
     * @date: 2022/07/16
     * @param: userInfoId 用户id
     * @return: 角色标签
     **/
    List<String> listRolesByUserInfoId(Integer userInfoId);


    /**
     * @description: 查询路由角色列表
     * @author: 兰生
     * @date: 2022/07/16
     * @param:
     * @return: ResourceRoleDTO
     **/
    List<ResourceRoleDTO> listResourceRoles();

    /**
     * 查询角色列表
     *
     * @param current     页码
     * @param size        条数
     * @param conditionVO 条件
     * @return 角色列表
     */
    List<RoleDTO> listRoles(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);
}
