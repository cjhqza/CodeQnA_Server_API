package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.manager.mapper.*;
import com.cjh.codeqna.manager.service.AprvReportService;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.approval.AprvReportVo;
import com.cjh.codeqna.model.vo.data.DtCommentVo;
import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 举报审批服务实现类
 * @Create: 2025-02-22 11:38
 */
@Service
public class AprvReportServiceImpl implements AprvReportService {
    @Autowired
    private AprvReportMapper aprvReportMapper;
    @Autowired
    private DtUserMapper dtUserMapper;
    @Autowired
    private DtKnowledgeMapper dtKnowledgeMapper;
    @Autowired
    private DtCommentMapper dtCommentMapper;

    // 举报列表
    @Override
    public PageInfo<AprvReportVo> findByPage(Integer pageNum, Integer pageSize, Integer status) {
        PageHelper.startPage(pageNum, pageSize);
        List<AprvReportVo> list = aprvReportMapper.findByPage(status);
        return new PageInfo<>(list);
    }

    // 用户类举报信息
    @Override
    public DtUser findTargetFromUser(Long targetId) {
        return dtUserMapper.findById(targetId);
    }

    // 知识类举报信息
    @Override
    public DtKnowledgeVo findTargetFromKnowledge(Long targetId) {
        System.out.println("knowledge");
        return dtKnowledgeMapper.findById(targetId);
    }

    // 评论类举报信息
    @Override
    public DtCommentVo findTargetFromComment(Long targetId) {
        return dtCommentMapper.findById(targetId);
    }
}
