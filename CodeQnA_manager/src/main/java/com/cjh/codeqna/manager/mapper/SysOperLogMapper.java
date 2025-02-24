package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.entity.log.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: cjh
 * @Description: 管理员操作日志映射文件
 * @Create: 2025-02-24 15:51
 */
@Mapper
public interface SysOperLogMapper {
    // 保存日志信息
    void insert(SysOperLog sysOperLog);
}
