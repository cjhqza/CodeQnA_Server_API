package com.cjh.codeqna.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 人员分配角色服务接口
 * @Create: 2024-12-30 21:32
 */
@Mapper
public interface SysRoleUserMapper {
    // 根据userId查询当前人员已分配过的角色集合
    List<Long> findRoleIdsByUserId(Long userId);

    // 删除之前人员已分配到的角色
    void deleteByUserId(Long userId);

    // 分配角色
    void assign(Long userId, Long roleId);
}
