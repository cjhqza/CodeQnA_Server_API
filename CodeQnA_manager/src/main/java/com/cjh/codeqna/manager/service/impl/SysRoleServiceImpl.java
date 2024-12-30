package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.manager.mapper.SysRoleMapper;
import com.cjh.codeqna.manager.mapper.SysRoleUserMapper;
import com.cjh.codeqna.manager.service.SysRoleService;
import com.cjh.codeqna.model.dto.system.SysRoleDto;
import com.cjh.codeqna.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cjh
 * @Description: 角色管理服务接口实现类
 * @Create: 2024-12-27 15:27
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    // 角色列表
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询符合条件的所有角色数据
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        // 封装pageInfo对象
        return new PageInfo<>(list);
    }

    // 角色添加
    @Override
    public void addSysRole(SysRole sysRole) {
        sysRoleMapper.add(sysRole);
    }

    // 角色修改
    @Override
    public void editSysRole(SysRole sysRole) {
        sysRoleMapper.edit(sysRole);
    }

    // 角色删除
    @Override
    public void deleteSysRoleById(Long roleId) {
        sysRoleMapper.delete(roleId);
    }

    // 角色集合
    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        // 查询所有角色
        List<SysRole> roles = sysRoleMapper.findAll();
        // 根据userId查询当前人员已分配过的角色集合
        List<Long> roleSelectedIds = sysRoleUserMapper.findRoleIdsByUserId(userId);
        // 将查询得到的数据存到Map中
        Map<String, Object> map = new HashMap<>();
        map.put("roles", roles);
        map.put("roleSelectedIds", roleSelectedIds);
        return map;
    }
}
