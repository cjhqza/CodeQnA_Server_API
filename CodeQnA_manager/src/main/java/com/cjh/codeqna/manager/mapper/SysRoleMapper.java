package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.system.SysRoleDto;
import com.cjh.codeqna.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 角色管理服务映射文件
 * @Create: 2024-12-27 15:27
 */
@Mapper
public interface SysRoleMapper {
    // 查询符合条件的所有角色数据
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    // 角色添加
    void add(SysRole sysRole);

    // 角色修改
    void edit(SysRole sysRole);

    // 角色删除
    void delete(Long roleId);
}
