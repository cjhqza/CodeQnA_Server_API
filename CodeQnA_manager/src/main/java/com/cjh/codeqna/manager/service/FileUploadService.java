package com.cjh.codeqna.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: cjh
 * @Description: 文件上传服务接口
 * @Create: 2024-12-29 21:37
 */
public interface FileUploadService {
    // 文件上传
    String fileUpload(MultipartFile file);
}
