package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.statistcs.StatsKnowledgeDto;
import com.cjh.codeqna.model.vo.statistcs.StatsKnowledgeVo;

/**
 * @Author: cjh
 * @Description: 知识统计服务接口
 * @Create: 2025-02-24 9:22
 */
public interface StatsKnowledgeService {
    // 获取知识统计数据
    StatsKnowledgeVo getStatsKnowledgeData(StatsKnowledgeDto statsKnowledgeDto);
}
