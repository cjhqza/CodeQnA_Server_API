package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.AprvReportService;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.approval.AprvReportVo;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.data.DtCommentVo;
import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cjh
 * @Description: 举报审批控制器
 * @Create: 2025-02-22 11:36
 */
@RestController
@RequestMapping(value = "/admin/approval/aprvReport")
public class AprvReportController {
    @Autowired
    private AprvReportService aprvReportService;

    // 举报列表
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}/{status}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @PathVariable("status") Integer status) {
        PageInfo<AprvReportVo> list = aprvReportService.findByPage(pageNum, pageSize, status);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 查看举报对象信息
    @PostMapping(value = "/findTargetByTypeAndId/{targetType}/{targetId}")
    public Result findTargetByTypeAndId(@PathVariable("targetType") Integer targetType, @PathVariable("targetId") Long targetId) {
        System.out.println(targetType + " " + targetId);
        if (targetType == 0) {
            DtUser dtUser = aprvReportService.findTargetFromUser(targetId);
            return Result.build(dtUser, ResultCodeEnum.SUCCESS);
        } else if (targetType == 1) {
            System.out.println(111);
            DtKnowledgeVo dtKnowledgeVo = aprvReportService.findTargetFromKnowledge(targetId);
            return Result.build(dtKnowledgeVo, ResultCodeEnum.SUCCESS);
        }
        DtCommentVo dtCommentVo = aprvReportService.findTargetFromComment(targetId);
        return Result.build(dtCommentVo, ResultCodeEnum.SUCCESS);
    }
}
