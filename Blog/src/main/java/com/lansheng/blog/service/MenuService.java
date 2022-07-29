package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.dto.LabelOptionDTO;
import com.lansheng.blog.dto.MenuDTO;
import com.lansheng.blog.dto.UserMenuDTO;
import com.lansheng.blog.entity.Menu;
import com.lansheng.blog.vo.ConditionVO;
import com.lansheng.blog.vo.MenuVO;

import java.util.List;

/**
 * @description: 菜单服务
 * @author: 兰生
 * @date: 2022/07/20
 * @version: 1.0
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查看菜单列表
     *
     * @param conditionVO 条件
     * @return 菜单列表
     */
    List<MenuDTO> listMenus(ConditionVO conditionVO);

    /**
     * 新增或修改菜单
     *
     * @param menuVO 菜单信息
     */
    void saveOrUpdateMenu(MenuVO menuVO);

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     */
    void deleteMenu(Integer menuId);

    /**
     * 查看角色菜单选项
     *
     * @return 角色菜单选项
     */
    List<LabelOptionDTO> listMenuOptions();

    /**
     * 查看用户菜单
     *
     * @return 菜单列表
     */
    List<UserMenuDTO> listUserMenus();

}
