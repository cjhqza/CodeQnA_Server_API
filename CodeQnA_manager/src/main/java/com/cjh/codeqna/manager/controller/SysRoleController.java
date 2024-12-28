package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.SysRoleService;
import com.cjh.codeqna.model.dto.system.SysRoleDto;
import com.cjh.codeqna.model.entity.system.SysRole;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: cjh
 * @Description: 角色管理控制器
 * @Create: 2024-12-27 15:25
 */
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    // 角色列表
    // pageNum：当前页数
    // pageSize：每页显示记录数
    // SysRoleDto：条件角色名称对象
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody SysRoleDto sysRoleDto) {
        // pageHelper插件实现分页
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 角色添加
    @PostMapping(value = "/addSysRole")
    public Result addSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.addSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 角色修改
    @PutMapping("/editSysRole")
    public Result editSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.editSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 角色删除
    @DeleteMapping("/deleteSysRoleById/{roleId}")
    public Result deleteSysRoleById(@PathVariable("roleId") Long roleId) {
        sysRoleService.deleteSysRoleById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
