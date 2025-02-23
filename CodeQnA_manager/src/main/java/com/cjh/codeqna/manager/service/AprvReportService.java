package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.entity.approval.AprvReportProcess;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.approval.AprvReportVo;
import com.cjh.codeqna.model.vo.data.DtCommentVo;
import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import com.github.pagehelper.PageInfo;


/**
 * @Author: cjh
 * @Description: 举报审批接口服务类
 * @Create: 2025-02-22 11:37
 */
public interface AprvReportService {
    // 举报列表
    PageInfo<AprvReportVo> findByPage(Integer pageNum, Integer pageSize, Integer status);

    // 用户类举报信息
    DtUser findTargetFromUser(Long targetId);

    // 知识类举报信息
    DtKnowledgeVo findTargetFromKnowledge(Long targetId);

    // 评论类举报信息
    DtCommentVo findTargetFromComment(Long targetId);

    // 获取举报用户名
    String findReportedUserByTypeAndId(Integer targetType, Long targetId);

    // 提交举报处理
    void processReport(Integer status, AprvReportProcess aprvReportProcess);

    // 获取举报处理结果
    AprvReportProcess getResolvedReport(Long reportId);
}
