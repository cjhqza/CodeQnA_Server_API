package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.entity.approval.AprvReport;
import com.cjh.codeqna.model.vo.approval.AprvReportVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 举报审批服务映射文件
 * @Create: 2025-02-22 11:40
 */
@Mapper
public interface AprvReportMapper {
    // 举报列表
    List<AprvReportVo> findByPage(Integer status);
}
