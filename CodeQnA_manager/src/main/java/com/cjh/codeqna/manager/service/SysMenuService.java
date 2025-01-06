package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.entity.system.SysMenu;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 菜单管理服务接口
 * @Create: 2025-01-06 21:41
 */
public interface SysMenuService {
    // 菜单列表
    List<SysMenu> findNodes();

    // 菜单添加
    void addSysMenu(SysMenu sysMenu);

    // 菜单修改
    void editSysMenu(SysMenu sysMenu);

    // 菜单删除
    void deleteSysMenuById(Long id);
}
