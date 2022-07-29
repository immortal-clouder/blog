package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.dto.RoleDTO;
import com.lansheng.blog.dto.UserRoleDTO;
import com.lansheng.blog.entity.Role;
import com.lansheng.blog.vo.ConditionVO;
import com.lansheng.blog.vo.PageResult;
import com.lansheng.blog.vo.RoleVO;

import java.util.List;

/**
 * @description: 角色服务
 * @author: 兰生
 * @date: 2022/07/22
 * @version: 1.0
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取用户角色选项
     *
     * @return 角色
     */
    List<UserRoleDTO> listUserRoles();

    /**
     * 查询角色列表
     *
     * @param conditionVO 条件
     * @return 角色列表
     */
    PageResult<RoleDTO> listRoles(ConditionVO conditionVO);

    /**
     * 保存或更新角色
     *
     * @param roleVO 角色
     */
    void saveOrUpdateRole(RoleVO roleVO);

    /**
     * 删除角色
     * @param roleIdList 角色id列表
     */
    void deleteRoles(List<Integer> roleIdList);

}
