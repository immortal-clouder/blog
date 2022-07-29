package com.lansheng.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lansheng.blog.dao.ArticleTagDao;
import com.lansheng.blog.entity.ArticleTag;
import com.lansheng.blog.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @description: 文章标签服务
 * @author: 兰生
 * @date: 2022/07/17
 * @version: 1.0
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagDao, ArticleTag> implements ArticleTagService {

}
