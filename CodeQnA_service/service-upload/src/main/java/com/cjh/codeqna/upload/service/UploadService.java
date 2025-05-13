package com.cjh.codeqna.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: cjh
 * @Description: 文件上传服务接口
 * @Create: 2025-04-11 19:13
 */
public interface UploadService {
    // 文件上传
    String fileUpload(MultipartFile file);
}
