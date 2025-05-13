package com.cjh.codeqna.upload.controller;

import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: cjh
 * @Description: 文件上传控制器
 * @Create: 2025-04-11 19:12
 */
@RestController
@RequestMapping(value = "/api/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    // 文件上传
    @PostMapping(value = "/fileUpload")
    public Result fileUpload(MultipartFile file) {
        String url = uploadService.fileUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
