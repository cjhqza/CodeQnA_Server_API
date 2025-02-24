package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.common.log.annotation.Log;
import com.cjh.codeqna.common.log.enums.OperatorType;
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
    @Log(title = "角色管理:列表", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @RequestBody SysRoleDto sysRoleDto) {
        // pageHelper插件实现分页
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 角色添加
    @Log(title = "角色管理:新增", businessType = 1, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/addSysRole")
    public Result addSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.addSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 角色修改
    @Log(title = "角色管理:修改", businessType = 2, operatorType = OperatorType.MANAGE)
    @PutMapping(value = "/editSysRole")
    public Result editSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.editSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 角色删除
    @Log(title = "角色管理:删除", businessType = 3, operatorType = OperatorType.MANAGE)
    @DeleteMapping(value = "/deleteSysRoleById/{roleId}")
    public Result deleteSysRoleById(@PathVariable("roleId") Long roleId) {
        sysRoleService.deleteSysRoleById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 角色集合
    @Log(title = "角色管理:人员ID查找角色", businessType = 0, operatorType = OperatorType.MANAGE)
    @GetMapping(value = "/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable("userId") Long userId) {
        Map<String, Object> map = sysRoleService.findAllRoles(userId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    // 查询所有菜单以及根据角色id查找对应的菜单id
    @Log(title = "角色管理:角色ID查找菜单", businessType = 0, operatorType = OperatorType.MANAGE)
    @GetMapping(value = "/findMenuIdByRoleId/{roleId}")
    public Result findMenuIdByRoleId(@PathVariable("roleId") Long roleId) {
        Map<String, Object> map = sysMenuService.findMenuIdByRoleId(roleId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    // 分配菜单提交
    @Log(title = "角色管理:分配菜单", businessType = 1, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/doAssign")
    public Result doAssign(@RequestBody AssignMenuDto assignMenuDto) {
        sysMenuService.doAssign(assignMenuDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
