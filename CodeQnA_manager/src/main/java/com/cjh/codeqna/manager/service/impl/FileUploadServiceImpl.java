package com.cjh.codeqna.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.manager.properties.MinioProperties;
import com.cjh.codeqna.manager.service.FileUploadService;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: cjh
 * @Description: 文件上传服务接口实现类
 * @Create: 2024-12-29 21:38
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private MinioProperties minioProperties;

    // 系统人员头像文件上传
    @Override
    public String fileUpload(MultipartFile file) {
        try {
            // 插件minioclient对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperties.getEndpointUrl())
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                            .build();

            // 获取 'codeqna-bucket'桶
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {   // 如果不存在
                // 则创建'codeqna-bucket'桶.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                System.out.println("Bucket 'codeqna-bucket' already exists.");
            }

            // 获取上传文件名称
            // 让每个上传文件名称唯一    uuid生成
            // 根据当前日期对上传文件进行分组处理
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = dateDir + "/" + uuid + file.getOriginalFilename();
            // 文件上传
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("codeqna-bucket")
                            .object(filename)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build());
            // 获取上传文件在minio的路径
            String url = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CodeQnAException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
