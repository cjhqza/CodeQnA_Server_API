package com.cjh.codeqna.report.service.impl;

import com.cjh.codeqna.model.dto.approval.AprvSugDto;
import com.cjh.codeqna.model.dto.report.ReportDto;
import com.cjh.codeqna.model.vo.approval.SimpleReportVo;
import com.cjh.codeqna.report.mapper.ReportMapper;
import com.cjh.codeqna.report.service.ReportService;
import com.cjh.codeqna.util.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 举报服务接口实现类
 * @Create: 2025-04-12 12:33
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;


    // 提交举报
    @Override
    public void submitReport(ReportDto reportDto) {
        // 获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        reportDto.setReporterId(userId);
        reportMapper.submitReport(reportDto);
    }

    // 获取举报回执
    @Override
    public List<SimpleReportVo> getReportReceipt(Long userId) {
        return reportMapper.getReportReceipt(userId);
    }

    // 上传建议信
    @Override
    public void submitSuggestion(AprvSugDto aprvSugDto) {
        reportMapper.submitSuggestion(aprvSugDto);
    }
}
