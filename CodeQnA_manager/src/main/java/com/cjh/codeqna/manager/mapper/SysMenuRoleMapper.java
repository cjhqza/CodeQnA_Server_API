package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 角色管理分配菜单服务映射文件
 * @Create: 2025-01-07 12:17
 */
@Mapper
public interface SysMenuRoleMapper {
    // 查询角色分配过菜单id列表
    List<Long> findMenuIdByRoleId(Long roleId);

    // 删除角色之前分配过的菜单数据
    void deleteByRoleId(Long roleId);

    // 分配菜单提交
    void doAssign(AssignMenuDto assignMenuDto);

    // 把父菜单的isHalf改为半开状态
    void updateParentMenuIsHalf(Long menuId);
}
