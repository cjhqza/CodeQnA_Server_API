package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.system.AssignRoleDto;
import com.cjh.codeqna.model.dto.system.LoginDto;
import com.cjh.codeqna.model.dto.system.SysUserDto;
import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author: cjh
 * @Description: 系统用户服务类接口
 * @Create: 2024-12-25 17:24
 */
public interface SysUserService {
    // 管理员用户登录
    LoginVo login(LoginDto loginDto);

    // 根据token查询redis获取管理员用户信息
    SysUser getSysUserInfo(String token);

    // 管理员用户退出
    void logout(String token);

    // 管理员列表
    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    // 人员添加
    void addSysUser(SysUser sysUser);

    // 人员修改
    void editSysUser(SysUser sysUser);

    // 人员删除
    void deleteSysUserById(Long sysUserId);

    // 人员分配角色
    void assignRole(AssignRoleDto assignRoleDto);
}
