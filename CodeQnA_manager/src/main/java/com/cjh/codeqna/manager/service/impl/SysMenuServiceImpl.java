package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.manager.mapper.SysMenuMapper;
import com.cjh.codeqna.manager.mapper.SysMenuRoleMapper;
import com.cjh.codeqna.manager.service.SysMenuService;
import com.cjh.codeqna.manager.utils.MenuHelper;
import com.cjh.codeqna.model.dto.system.AssignMenuDto;
import com.cjh.codeqna.model.entity.system.SysMenu;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cjh
 * @Description: 菜单管理服务接口实现类
 * @Create: 2025-01-06 21:42
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;

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

    // 查询所有菜单以及根据角色id查找对应的菜单id
    @Override
    public Map<String, Object> findMenuIdByRoleId(Long roleId) {
        // 查询所有菜单
        List<SysMenu> sysMenuList = findNodes();
        //  查询角色分配过菜单id列表
        List<Long> menuIds = sysMenuRoleMapper.findMenuIdByRoleId(roleId);

        Map<String, Object> map = new HashMap<>();
        map.put("sysMenuList", sysMenuList);
        map.put("menuIds", menuIds);

        return map;
    }

    // 分配菜单提交
    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {
        // 删除角色之前分配过的菜单数据
        sysMenuRoleMapper.deleteByRoleId(assignMenuDto.getRoleId());
        // 保存分配数据
        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        System.out.println("@@@@@@@@@@@@@@@" + menuInfo);
        if (menuInfo != null && menuInfo.size() > 0) {  // 角色分配了菜单
            sysMenuRoleMapper.doAssign(assignMenuDto);
        }
    }
}
