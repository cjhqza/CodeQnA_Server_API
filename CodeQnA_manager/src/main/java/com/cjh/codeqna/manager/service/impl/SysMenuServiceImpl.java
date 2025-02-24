package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.manager.mapper.SysMenuMapper;
import com.cjh.codeqna.manager.mapper.SysMenuRoleMapper;
import com.cjh.codeqna.manager.service.SysMenuService;
import com.cjh.codeqna.manager.utils.TreeHelper;
import com.cjh.codeqna.model.dto.system.AssignMenuDto;
import com.cjh.codeqna.model.entity.system.SysMenu;
import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.system.SysMenuVo;
import com.cjh.codeqna.util.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
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
        List<SysMenu> treeList = TreeHelper.buildMenuTree(sysMenuList);
        return treeList;
    }

    // 菜单添加
    @Transactional
    @Override
    public void addSysMenu(SysMenu sysMenu) {
        sysMenuMapper.add(sysMenu);
        // 如果新添加子菜单，那么需要把父菜单的isHalf半开状态 1
        updateSysRoleMenu(sysMenu);
    }

    // 新添加子菜单，将父菜单的isHalf半开状态 1
    private void updateSysRoleMenu(SysMenu sysMenu) {
        // 获取当前添加菜单的父菜单
        SysMenu parentMenu = sysMenuMapper.findParentMenu(sysMenu.getParentId());
        if (parentMenu != null) {
            // 把父菜单的isHalf改为半开状态 1
            sysMenuRoleMapper.updateParentMenuIsHalf(parentMenu.getId());
            // 递归调用
            updateSysRoleMenu(parentMenu);
        }
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
            // throw new CodeQnAException(ResultCodeEnum.NODE_ERROR);
            throw new RuntimeException();
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
    @Transactional
    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {
        // 删除角色之前分配过的菜单数据
        sysMenuRoleMapper.deleteByRoleId(assignMenuDto.getRoleId());
        // 保存分配数据
        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        if (menuInfo != null && menuInfo.size() > 0) {  // 角色分配了菜单
            sysMenuRoleMapper.doAssign(assignMenuDto);
        }
    }

    // 查询管理员可以操作的菜单
    @Override
    public List<SysMenuVo> findMenusByUserId() {
        // 获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();
        // 根据userId查询可以操作菜单
        List<SysMenu> sysMenuList = sysMenuMapper.findMenusByUserId(userId);
        // 封装要求数据格式，返回
        List<SysMenu> sysMenuVoList = TreeHelper.buildMenuTree(sysMenuList);
        return this.buildMenus(sysMenuVoList);
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
