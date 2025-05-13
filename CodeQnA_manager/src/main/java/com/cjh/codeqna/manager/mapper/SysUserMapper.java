package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.system.SysUserDto;
import com.cjh.codeqna.model.dto.system.SysUserExcelDto;
import com.cjh.codeqna.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 系统人员服务映射文件
 * @Create: 2024-12-25 17:25
 */
@Mapper
public interface SysUserMapper {
    // 根据管理员用户名从系统管理员用户表里查询获取得到其实体类
    SysUser findUserByUsername(String userName);

    // 管理员列表
    List<SysUser> findByPage(SysUserDto sysUserDto);

    // 人员添加
    void add(SysUser sysUser);

    // 人员修改
    void edit(SysUser sysUser);

    // 人员删除
    void delete(Long sysUserId);

    // 人员查找
    SysUser findById(Long processorId);

    // 获取所有的sysUser数据的集合
    List<SysUser> findAll();

    // 批量导入
    void batchInsert(List<SysUserExcelDto> cachedDataList);
}
