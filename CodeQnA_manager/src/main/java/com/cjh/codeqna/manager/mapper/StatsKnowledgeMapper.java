package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.statistcs.StatsKnowledgeDto;
import com.cjh.codeqna.model.entity.statistcs.StatsKnowledge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识统计服务映射文件
 * @Create: 2025-02-24 9:24
 */
@Mapper
public interface StatsKnowledgeMapper {
    // 把统计结果加入到知识统计表中
    void insert(StatsKnowledge statsKnowledge);

    // 查询知识统计集合
    List<StatsKnowledge> selectList(StatsKnowledgeDto statsKnowledgeDto);
}
