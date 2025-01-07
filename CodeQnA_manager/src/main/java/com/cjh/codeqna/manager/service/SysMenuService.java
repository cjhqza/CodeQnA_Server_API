package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.system.AssignMenuDto;
import com.cjh.codeqna.model.entity.system.SysMenu;

import java.util.List;
import java.util.Map;

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

    // 查询所有菜单以及根据角色id查找对应的菜单id
    Map<String, Object> findMenuIdByRoleId(Long roleId);

    // 分配菜单提交
    void doAssign(AssignMenuDto assignMenuDto);
}
