package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.common.log.service.SysOperLogService;
import com.cjh.codeqna.manager.mapper.SysOperLogMapper;
import com.cjh.codeqna.model.entity.log.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cjh
 * @Description: 管理员操作日志服务实现类
 * @Create: 2025-02-24 15:49
 */
@Service
public class SysOperLogServiceImpl implements SysOperLogService {
    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    // 保存日志信息
    @Override
    public void insert(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
