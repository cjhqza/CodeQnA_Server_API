package com.cjh.codeqna.manager.utils;

import com.cjh.codeqna.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cjh
 * @Description: 菜单树型结构封装工具类
 * @Create: 2025-01-06 21:57
 */
public class MenuHelper {
    // 封装树型数据
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        // 完成封装过程
        // 创建list集合，用于封装最终的数据
        List<SysMenu> trees = new ArrayList<>();
        // 遍历所有菜单集合
        for (SysMenu sysMenu : sysMenuList) {
            // 知道递归操作入口，第一层菜单
            // 条件：parent_id=0
            if (sysMenu.getParentId().longValue() == 0) {
                // 根据第一层，找下层数据，使用递归完成
                // 写方法实现找下层过程，方法里面传递两个参数：第一层菜单 和 菜单集合
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    // 递归查找下层菜单
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<>());
        // 递归查询
        // sysMenu的id值 和 sysMenuList中parentId相同
        for (SysMenu s : sysMenuList) {
            if (sysMenu.getId().longValue() == s.getParentId().longValue()) {
                sysMenu.getChildren().add(s);
                findChildren(s, sysMenuList);
            }
        }
        return sysMenu;
    }
}
