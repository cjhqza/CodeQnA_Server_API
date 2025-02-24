package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.common.log.annotation.Log;
import com.cjh.codeqna.common.log.enums.OperatorType;
import com.cjh.codeqna.manager.service.AprvReportService;
import com.cjh.codeqna.model.entity.approval.AprvReportProcess;
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
    @Log(title = "举报审批:列表", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}/{status}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @PathVariable("status") Integer status) {
        PageInfo<AprvReportVo> list = aprvReportService.findByPage(pageNum, pageSize, status);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 查看举报对象信息
    @Log(title = "举报审批:查找举报对象消息", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/findTargetByTypeAndId/{targetType}/{targetId}")
    public Result findTargetByTypeAndId(@PathVariable("targetType") Integer targetType, @PathVariable("targetId") Long targetId) {
        if (targetType == 0) {
            DtUser dtUser = aprvReportService.findTargetFromUser(targetId);
            return Result.build(dtUser, ResultCodeEnum.SUCCESS);
        } else if (targetType == 1) {
            DtKnowledgeVo dtKnowledgeVo = aprvReportService.findTargetFromKnowledge(targetId);
            return Result.build(dtKnowledgeVo, ResultCodeEnum.SUCCESS);
        }
        DtCommentVo dtCommentVo = aprvReportService.findTargetFromComment(targetId);
        return Result.build(dtCommentVo, ResultCodeEnum.SUCCESS);
    }

    // 获取举报用户名
    @Log(title = "举报审批:查找举报用户名", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/findReportedUserByTypeAndId/{targetType}/{targetId}")
    public Result findReportedUserByTypeAndId(@PathVariable("targetType") Integer targetType, @PathVariable("targetId") Long targetId) {
        String reportedUserName = aprvReportService.findReportedUserByTypeAndId(targetType, targetId);
        return Result.build(reportedUserName, ResultCodeEnum.SUCCESS);
    }

    // 提交举报处理
    @Log(title = "举报审批:提交举报处理", businessType = 2, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/processReport/{status}")
    public Result processReport(@PathVariable("status") Integer status, AprvReportProcess aprvReportProcess) {
        aprvReportService.processReport(status, aprvReportProcess);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 获取举报处理结果
    @Log(title = "举报审批:获取举报处理结果", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/getResolvedReport/{reportId}")
    public Result getResolvedReport(@PathVariable("reportId") Long reportId) {
        AprvReportProcess aprvReportProcess = aprvReportService.getResolvedReport(reportId);
        return Result.build(aprvReportProcess, ResultCodeEnum.SUCCESS);
    }
}
