package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.manager.mapper.DtUserMapper;
import com.cjh.codeqna.manager.service.DtUserService;
import com.cjh.codeqna.model.dto.data.DtUserDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 用户管理接口服务实现类
 * @Create: 2025-01-15 14:56
 */
@Service
public class DtUserServiceImpl implements DtUserService {
    @Autowired
    private DtUserMapper dtUserMapper;

    // 用户列表
    @Override
    public PageInfo<DtUser> findByPage(Integer pageNum, Integer pageSize, DtUserDto dtUserDto){
        // 使用分页插件
        PageHelper.startPage(pageNum, pageSize);
        List<DtUser> list = dtUserMapper.findByPage(dtUserDto);
        return new PageInfo<>(list);
    }

    // 修改用户状态
    @Override
    public void editDtUser(Long id) {
        dtUserMapper.edit(id);
    }

    // 用户删除
    @Override
    public void deleteDtUser(Long dtUserId) {
        dtUserMapper.delete(dtUserId);
    }
}
