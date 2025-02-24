package com.cjh.codeqna.common.log.service;

import com.cjh.codeqna.model.entity.log.SysOperLog;

/**
 * @Author: cjh
 * @Description: 管理员操作日志服务接口
 * @Create: 2025-02-24 15:38
 */
public interface SysOperLogService {

    // 保存日志信息
    void insert(SysOperLog sysOperLog);
}
