package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 菜单管理服务映射文件
 * @Create: 2025-01-06 21:43
 */
@Mapper
public interface SysMenuMapper {
    // 查询所有菜单
    List<SysMenu> findAll();

    // 菜单添加
    void add(SysMenu sysMenu);

    // 菜单修改
    void edit(SysMenu sysMenu);

    // 根据当前菜单id，查询当前包含子菜单的数量
    int findChildrenById(Long id);

    // 菜单删除
    void delete(Long id);

    // 根据userId查询可以操作菜单
    List<SysMenu> findMenusByUserId(Long userId);

    // 获取当前添加菜单的父菜单
    SysMenu findParentMenu(Long parentId);
}
