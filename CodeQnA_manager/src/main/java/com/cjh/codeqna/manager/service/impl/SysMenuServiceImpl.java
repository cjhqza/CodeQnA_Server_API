package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.manager.mapper.SysMenuMapper;
import com.cjh.codeqna.manager.service.SysMenuService;
import com.cjh.codeqna.manager.utils.MenuHelper;
import com.cjh.codeqna.model.entity.system.SysMenu;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 菜单管理服务接口实现类
 * @Create: 2025-01-06 21:42
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    // 菜单列表
    @Override
    public List<SysMenu> findNodes() {
        // 查询所有菜单
        List<SysMenu> sysMenuList = sysMenuMapper.findAll();
        // 判断是否有菜单
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }
        // 将集合封装所需的树型数据格式
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);
        System.out.println(treeList);
        return treeList;
    }

    // 菜单添加
    @Override
    public void addSysMenu(SysMenu sysMenu) {
        sysMenuMapper.add(sysMenu);
    }

    // 菜单修改
    @Override
    public void editSysMenu(SysMenu sysMenu) {
        sysMenuMapper.edit(sysMenu);
    }

    // 菜单删除
    @Override
    public void deleteSysMenuById(Long id) {
        // 根据当前菜单id，查询当前包含子菜单的数量
        int count = sysMenuMapper.findChildrenById(id);
        // count大于0，说明有子菜单，那么不建议删除
        if (count > 0) {
            throw new CodeQnAException(ResultCodeEnum.NODE_ERROR);
        }
        // count等于0，直接删除
        sysMenuMapper.delete(id);
    }
}
