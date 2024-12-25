package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.system.LoginDto;
import com.cjh.codeqna.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: cjh
 * @Description: 系统用户服务映射文件
 * @Create: 2024-12-25 17:25
 */
@Mapper
public interface SysUserMapper {
    // 根据管理员用户名从系统管理员用户表里查询获取得到其实体类
    SysUser findUserByUsername(String userName);
}
