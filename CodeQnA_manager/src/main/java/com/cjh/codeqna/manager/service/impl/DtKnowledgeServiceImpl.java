package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.manager.mapper.DtKnowledgeMapper;
import com.cjh.codeqna.manager.mapper.DtKnowledgeTagMapper;
import com.cjh.codeqna.manager.mapper.DtTagMapper;
import com.cjh.codeqna.manager.mapper.DtUserMapper;
import com.cjh.codeqna.manager.service.DtKnowledgeService;
import com.cjh.codeqna.model.dto.data.DtKnowledgeDto;
import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: cjh
 * @Description: 知识管理服务接口实现类
 * @Create: 2025-01-25 23:26
 */
@Service
public class DtKnowledgeServiceImpl implements DtKnowledgeService {
    @Autowired
    private DtKnowledgeMapper dtKnowledgeMapper;

    // 知识列表
    @Override
    public PageInfo<DtKnowledgeVo> findByPage(Integer pageNum, Integer pageSize, DtKnowledgeDto dtKnowledgeDto) {
        PageHelper.startPage(pageNum, pageSize);
        List<DtKnowledgeVo> list = dtKnowledgeMapper.findByPage(dtKnowledgeDto);
        return new PageInfo<>(list);
    }

    // 修改知识状态
    @Override
    public void editDtKnowledge(Long id) {
        dtKnowledgeMapper.edit(id);
    }

    // 获取待审批的知识列表
    @Override
    public PageInfo<DtKnowledgeVo> findByPageByOrder(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DtKnowledgeVo> list = dtKnowledgeMapper.findByPageByOrder();
        return new PageInfo<>(list);
    }

    // 审批后的知识状态
    @Override
    public void processDtKnowledge(Long id, Integer status) {
        dtKnowledgeMapper.process(id, status);
    }
}
