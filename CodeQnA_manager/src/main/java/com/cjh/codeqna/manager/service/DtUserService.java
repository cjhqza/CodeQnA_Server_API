package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.data.DtUserDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.github.pagehelper.PageInfo;

/**
 * @Author: cjh
 * @Description: 用户管理服务接口
 * @Create: 2025-01-15 14:56
 */
public interface DtUserService {
    // 用户列表
    PageInfo<DtUser> findByPage(Integer pageNum, Integer pageSize, DtUserDto dtUserDto);

    // 修改用户状态
    void editDtUser(Long id);

    // 用户删除
    void deleteDtUser(Long dtUserId);
}
