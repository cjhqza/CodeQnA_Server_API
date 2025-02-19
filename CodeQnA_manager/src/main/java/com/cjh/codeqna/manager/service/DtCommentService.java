package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.data.DtCommentDto;
import com.cjh.codeqna.model.vo.data.DtCommentVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 评论管理服务接口
 * @Create: 2025-02-17 17:20
 */
public interface DtCommentService {
    // 评论列表
    PageInfo<DtCommentVo> findByPage(Integer pageNum, Integer pageSize, DtCommentDto dtCommentDto);

    // 删评
    void deleteDtComment(Long id);
}
