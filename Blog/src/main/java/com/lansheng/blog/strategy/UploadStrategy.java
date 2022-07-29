package com.lansheng.blog.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 上传策略
 * @author: 兰生
 * @date: 2022/07/21
 * @version: 1.0
 */
public interface UploadStrategy {

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 上传路径
     * @return {@link String} 文件地址
     */
    String uploadFile(MultipartFile file, String path);

}
