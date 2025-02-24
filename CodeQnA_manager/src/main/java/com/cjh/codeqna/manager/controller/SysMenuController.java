package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.common.log.annotation.Log;
import com.cjh.codeqna.common.log.enums.OperatorType;
import com.cjh.codeqna.manager.service.SysMenuService;
import com.cjh.codeqna.model.entity.system.SysMenu;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 菜单管理控制器
 * @Create: 2025-01-06 21:40
 */
@RestController
@RequestMapping(value = "/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    // 菜单列表
    @Log(title = "菜单管理:列表", businessType = 0, operatorType = OperatorType.MANAGE)
    @GetMapping(value = "/findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 菜单添加
    @Log(title = "菜单管理:新增", businessType = 1, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/addSysMenu")
    public Result addSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.addSysMenu(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 菜单修改
    @Log(title = "菜单管理:修改", businessType = 2, operatorType = OperatorType.MANAGE)
    @PutMapping(value = "/editSysMenu")
    public Result editSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.editSysMenu(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 菜单删除
    @Log(title = "菜单管理:删除", businessType = 3, operatorType = OperatorType.MANAGE)
    @DeleteMapping(value = "/deleteSysMenuById/{id}")
    public Result deleteSysMenuById(@PathVariable("id") Long id) {
        sysMenuService.deleteSysMenuById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
