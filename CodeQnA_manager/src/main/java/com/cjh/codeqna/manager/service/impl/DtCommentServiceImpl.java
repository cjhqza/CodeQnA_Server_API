package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.manager.mapper.DtCommentMapper;
import com.cjh.codeqna.manager.service.DtCommentService;
import com.cjh.codeqna.model.dto.data.DtCommentDto;
import com.cjh.codeqna.model.vo.data.DtCommentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 评论管理服务接口实现类
 * @Create: 2025-02-17 17:20
 */
@Service
public class DtCommentServiceImpl implements DtCommentService {
    @Autowired
    private DtCommentMapper dtCommentMapper;

    // 评论列表
    @Override
    public PageInfo<DtCommentVo> findByPage(Integer pageNum, Integer pageSize, DtCommentDto dtCommentDto) {
        PageHelper.startPage(pageNum, pageSize);
        List<DtCommentVo> list = dtCommentMapper.findByPage(dtCommentDto);
        return new PageInfo<>(list);
    }

    // 删评
    @Override
    public void deleteDtComment(Long id) {
        dtCommentMapper.delete(id);
    }
}
