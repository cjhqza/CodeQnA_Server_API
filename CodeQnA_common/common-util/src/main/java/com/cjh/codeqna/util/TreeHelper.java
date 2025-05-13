package com.cjh.codeqna.util;

import com.cjh.codeqna.model.entity.data.DtTag;
import com.cjh.codeqna.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cjh
 * @Description: 树型结构封装工具类
 * @Create: 2025-01-06 21:57
 */
public class TreeHelper {
    // 封装菜单树型数据
    public static List<SysMenu> buildMenuTree(List<SysMenu> sysMenuList) {
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
                trees.add(findMenuChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    // 递归菜单查找下层菜单
    private static SysMenu findMenuChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<>());
        // 递归查询
        // sysMenu的id值 和 sysMenuList中parentId相同
        for (SysMenu s : sysMenuList) {
            if (sysMenu.getId().longValue() == s.getParentId().longValue()) {
                sysMenu.getChildren().add(s);
                findMenuChildren(s, sysMenuList);
            }
        }
        return sysMenu;
    }

    // 封装菜单树型数据
    public static List<DtTag> buildTagTree(List<DtTag> dtTagList) {
        // 完成封装过程
        // 创建list集合，用于封装最终的数据
        List<DtTag> trees = new ArrayList<>();
        // 遍历所有菜单集合
        for (DtTag dtTag : dtTagList) {
            // 知道递归操作入口，第一层菜单
            // 条件：parent_id=0
            if (dtTag.getParentId().longValue() == 0) {
                // 根据第一层，找下层数据，使用递归完成
                // 写方法实现找下层过程，方法里面传递两个参数：第一层菜单 和 菜单集合
                trees.add(findTagChildren(dtTag, dtTagList));
            }
        }
        return trees;
    }

    // 递归菜单查找下层菜单
    private static DtTag findTagChildren(DtTag dtTag, List<DtTag> dtTagList) {
        dtTag.setChildren(new ArrayList<>());
        // 递归查询
        // sysMenu的id值 和 sysMenuList中parentId相同
        for (DtTag d : dtTagList) {
            if (dtTag.getId().longValue() == d.getParentId().longValue()) {
                dtTag.getChildren().add(d);
                findTagChildren(d, dtTagList);
            }
        }
        return dtTag;
    }
}
