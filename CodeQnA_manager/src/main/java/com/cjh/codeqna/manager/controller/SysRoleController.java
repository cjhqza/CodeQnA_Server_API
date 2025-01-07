package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.SysMenuService;
import com.cjh.codeqna.manager.service.SysRoleService;
import com.cjh.codeqna.model.dto.system.AssignMenuDto;
import com.cjh.codeqna.model.dto.system.SysRoleDto;
import com.cjh.codeqna.model.entity.system.SysRole;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @Autowired
    private SysMenuService sysMenuService;

    // 角色列表
    // pageNum：当前页数
    // pageSize：每页显示记录数
    // SysRoleDto：条件角色名称对象
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}")
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
    @PutMapping(value = "/editSysRole")
    public Result editSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.editSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 角色删除
    @DeleteMapping(value = "/deleteSysRoleById/{roleId}")
    public Result deleteSysRoleById(@PathVariable("roleId") Long roleId) {
        sysRoleService.deleteSysRoleById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 角色集合
    @GetMapping(value = "/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable("userId") Long userId) {
        Map<String, Object> map = sysRoleService.findAllRoles(userId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    // 查询所有菜单以及根据角色id查找对应的菜单id
    @GetMapping(value = "/findMenuIdByRoleId/{roleId}")
    public Result findMenuIdByRoleId(@PathVariable("roleId") Long roleId) {
        Map<String, Object> map = sysMenuService.findMenuIdByRoleId(roleId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    // 分配菜单提交
    @PostMapping(value = "/doAssign")
    public Result doAssign(@RequestBody AssignMenuDto assignMenuDto) {
        System.out.println("123456" + assignMenuDto);
        sysMenuService.doAssign(assignMenuDto);
        System.out.println("@@@@@@@@@@");
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
