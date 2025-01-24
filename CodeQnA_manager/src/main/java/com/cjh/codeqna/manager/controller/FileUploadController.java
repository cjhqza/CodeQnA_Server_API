package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.FileUploadService;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: cjh
 * @Description: 文件上传控制器
 * @Create: 2024-12-29 21:36
 */
@RestController
@RequestMapping(value = "/admin")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    // 系统人员头像文件上传
    @PostMapping(value = "/system/avatarFileUpload")
    public Result avatarFileUpload(MultipartFile file) {
        String url = fileUploadService.fileUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

    // 数据标签代表图文件上传
    @PostMapping(value = "/data/tagImgfileUpload")
    public Result tagImgfileUpload(MultipartFile file) {
        String url = fileUploadService.fileUpload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
