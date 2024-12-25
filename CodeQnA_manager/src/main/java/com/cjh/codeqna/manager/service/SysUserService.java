package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.system.LoginDto;
import com.cjh.codeqna.model.vo.system.LoginVo;

/**
 * @Author: cjh
 * @Description: 系统用户服务类接口
 * @Create: 2024-12-25 17:24
 */
public interface SysUserService {
    // 管理员用户登录
    LoginVo login(LoginDto loginDto);
}
