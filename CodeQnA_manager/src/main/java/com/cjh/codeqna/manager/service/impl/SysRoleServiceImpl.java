package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.manager.mapper.SysRoleMapper;
import com.cjh.codeqna.manager.service.SysRoleService;
import com.cjh.codeqna.model.dto.system.SysRoleDto;
import com.cjh.codeqna.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 角色管理服务接口实现类
 * @Create: 2024-12-27 15:27
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

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
}
