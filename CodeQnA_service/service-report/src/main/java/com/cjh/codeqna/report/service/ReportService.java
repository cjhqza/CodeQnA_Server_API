package com.cjh.codeqna.report.service;

import com.cjh.codeqna.model.dto.approval.AprvSugDto;
import com.cjh.codeqna.model.dto.report.ReportDto;
import com.cjh.codeqna.model.vo.approval.SimpleReportVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 举报服务接口
 * @Create: 2025-04-12 12:33
 */
public interface ReportService {
    // 提交举报
    void submitReport(ReportDto reportDto);

    // 获取举报回执
    List<SimpleReportVo> getReportReceipt(Long userId);

    // 上传建议信
    void submitSuggestion(AprvSugDto aprvSugDto);
}
