package com.cjh.codeqna.knowledge.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识标签服务映射文件
 * @Create: 2025-04-07 12:02
 */
@Mapper
public interface KnowledgeTagMapper {
    // 获取问答贴对应标签号集合
    List<Long> getTagIdsByKnowledgeId(Long id);

    // 删除已有对应知识id的数据
    void deleteByKnowledgeId(Long knowledgeId);

    // 插入新的知识标签数据
    void saveKnowledgeTag(Long knowledgeId, List<Long> tagIds);
}
