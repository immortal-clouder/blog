package com.lansheng.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lansheng.blog.dto.CategoryBackDTO;
import com.lansheng.blog.dto.CategoryDTO;
import com.lansheng.blog.dto.CategoryOptionDTO;
import com.lansheng.blog.entity.Category;
import com.lansheng.blog.vo.CategoryVO;
import com.lansheng.blog.vo.ConditionVO;
import com.lansheng.blog.vo.PageResult;

import java.util.List;


/**
 * @description: 分类服务
 * @author: 兰生
 * @date: 2022/07/17
 * @version: 1.0
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询分类列表
     *
     * @return 分类列表
     */
    PageResult<CategoryDTO> listCategories();

    /**
     * 查询后台分类
     *
     * @param conditionVO 条件
     * @return {@link PageResult<CategoryBackDTO>} 后台分类
     */
    PageResult<CategoryBackDTO> listBackCategories(ConditionVO conditionVO);

    /**
     * 搜索文章分类
     *
     * @param condition 条件
     * @return {@link List<CategoryOptionDTO>} 分类列表
     */
    List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO condition);

    /**
     * 删除分类
     *
     * @param categoryIdList 分类id集合
     */
    void deleteCategory(List<Integer> categoryIdList);

    /**
     * 添加或修改分类
     *
     * @param categoryVO 分类
     */
    void saveOrUpdateCategory(CategoryVO categoryVO);

}