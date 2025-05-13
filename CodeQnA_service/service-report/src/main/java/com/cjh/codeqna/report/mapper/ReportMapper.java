package com.cjh.codeqna.report.mapper;

import com.cjh.codeqna.model.dto.approval.AprvSugDto;
import com.cjh.codeqna.model.dto.report.ReportDto;
import com.cjh.codeqna.model.vo.approval.SimpleReportVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 举报服务映射文件
 * @Create: 2025-04-12 12:34
 */
@Mapper
public interface ReportMapper {
    // 提交举报
    void submitReport(ReportDto reportDto);

    // 获取举报回执
    List<SimpleReportVo> getReportReceipt(Long userId);

    // 上传建议信
    void submitSuggestion(AprvSugDto aprvSugDto);
}
