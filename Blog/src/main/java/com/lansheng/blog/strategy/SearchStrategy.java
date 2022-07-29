package com.lansheng.blog.strategy;

import com.lansheng.blog.dto.ArticleSearchDTO;

import java.util.List;

/**
 * @description: 搜索策略
 * @author: 兰生
 * @date: 2022/07/23
 * @version: 1.0
 */
public interface SearchStrategy {

    /**
     * 搜索文章
     *
     * @param keywords 关键字
     * @return {@link List<ArticleSearchDTO>} 文章列表
     */
    List<ArticleSearchDTO> searchArticle(String keywords);

}
