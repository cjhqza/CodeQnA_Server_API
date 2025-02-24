package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.common.log.annotation.Log;
import com.cjh.codeqna.common.log.enums.OperatorType;
import com.cjh.codeqna.manager.service.SysUserService;
import com.cjh.codeqna.model.dto.system.AssignRoleDto;
import com.cjh.codeqna.model.dto.system.SysUserDto;
import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: cjh
 * @Description: 人员管理控制器
 * @Create: 2024-12-29 14:57
 */
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    // 管理员列表
    // pageNum：当前页数
    // pageSize：每页显示记录数
    // SysUserDto：条件人员传输数据对象
    @Log(title = "人员管理:列表", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysUser>> findByPage(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize, SysUserDto sysUserDto) {
        // pageHelper插件实现分页
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(pageNum , pageSize, sysUserDto) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    // 人员添加
    @Log(title = "人员管理:新增", businessType = 1, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/addSysUser")
    public Result addSysUser(@RequestBody SysUser sysUser) {
        sysUserService.addSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 人员修改
    @Log(title = "人员管理:修改", businessType = 2, operatorType = OperatorType.MANAGE)
    @PutMapping(value = "/editSysUser")
    public Result editSysUser(@RequestBody SysUser sysUser) {
        sysUserService.editSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 人员删除
    @Log(title = "人员管理:删除", businessType = 3, operatorType = OperatorType.MANAGE)
    @DeleteMapping(value = "/deleteSysUserById/{sysUserId}")
    public Result deleteSysUserById(@PathVariable("sysUserId") Long sysUserId) {
        sysUserService.deleteSysUserById(sysUserId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 人员分配角色
    @Log(title = "人员管理:分配角色", businessType = 1, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/assignRole")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto) {
        sysUserService.assignRole(assignRoleDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 人员查找
    @Log(title = "人员管理:人员ID号查找人员", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/getSysUserById/{processorId}")
    public Result getSysUserById(@PathVariable("processorId") Long processorId) {
        SysUser sysUser = sysUserService.getSysUserById(processorId);
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }
}
