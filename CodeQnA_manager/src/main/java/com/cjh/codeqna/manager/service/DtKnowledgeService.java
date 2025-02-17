package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.data.DtKnowledgeDto;
import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author: cjh
 * @Description: 知识管理服务接口
 * @Create: 2025-01-25 23:25
 */
public interface DtKnowledgeService {
    // 知识列表
    PageInfo<DtKnowledgeVo> findByPage(Integer pageNum, Integer pageSize, DtKnowledgeDto dtKnowledgeDto);

    // 修改知识状态
    void editDtKnowledge(Long id);
}
