package com.cjh.codeqna.manager.controller;

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
    @GetMapping("/findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 菜单添加
    @PostMapping("/addSysMenu")
    public Result addSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.addSysMenu(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 菜单修改
    @PutMapping("/editSysMenu")
    public Result editSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.editSysMenu(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 菜单删除
    @DeleteMapping("/deleteSysMenuById/{id}")
    public Result deleteSysMenuById(@PathVariable("id") Long id) {
        sysMenuService.deleteSysMenuById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
