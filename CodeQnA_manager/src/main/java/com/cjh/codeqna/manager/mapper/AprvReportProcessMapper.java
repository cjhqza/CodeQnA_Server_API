package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.entity.approval.AprvReportProcess;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: cjh
 * @Description: 举报审批处理服务映射文件
 * @Create: 2025-02-23 10:42
 */
@Mapper
public interface AprvReportProcessMapper {
    // 提交举报处理
    void insert(AprvReportProcess aprvReportProcess);

    // 获取举报处理结果
    AprvReportProcess findByReportId(Long reportId);
}
