package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.system.SysRoleDto;
import com.cjh.codeqna.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

/**
 * @Author: cjh
 * @Description: 角色管理服务接口
 * @Create: 2024-12-27 15:26
 */
public interface SysRoleService {
    // 角色列表
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);
}
