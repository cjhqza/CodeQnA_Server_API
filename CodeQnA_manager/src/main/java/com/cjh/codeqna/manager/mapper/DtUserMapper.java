package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.data.DtUserDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 用户管理服务映射文件
 * @Create: 2025-01-15 14:57
 */
@Mapper
public interface DtUserMapper {
    // 用户列表
    List<DtUser> findByPage(DtUserDto dtUserDto);

    // 修改用户状态
    void edit(Long id);

    // 用户删除
    void delete(Long dtUserId);
}
