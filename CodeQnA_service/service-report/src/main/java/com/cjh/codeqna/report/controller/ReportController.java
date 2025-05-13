package com.cjh.codeqna.report.controller;

import com.cjh.codeqna.model.dto.approval.AprvSugDto;
import com.cjh.codeqna.model.dto.report.ReportDto;
import com.cjh.codeqna.model.vo.approval.SimpleReportVo;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 举报控制器
 * @Create: 2025-04-12 12:32
 */
@RestController
@RequestMapping(value = "/api/report/reportInfo")
public class ReportController {
    @Autowired
    private ReportService reportService;

    // 提交举报
    @PostMapping(value = "/submitReport")
    public Result submitReport(@RequestBody ReportDto reportDto) {
        reportService.submitReport(reportDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 获取举报回执
    @GetMapping(value = "/getReportReceipt/{userId}")
    public Result getReportReceipt(@PathVariable(value = "userId") Long userId) {
        List<SimpleReportVo> list = reportService.getReportReceipt(userId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 上传建议信
    @PostMapping(value = "/submitSuggestion")
    public Result submitSuggestion(@RequestBody AprvSugDto aprvSugDto) {
        reportService.submitSuggestion(aprvSugDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
