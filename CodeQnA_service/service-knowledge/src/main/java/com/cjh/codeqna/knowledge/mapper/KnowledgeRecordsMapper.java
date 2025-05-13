package com.cjh.codeqna.knowledge.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: cjh
 * @Description: 知识记录映射文件
 * @Create: 2025-04-08 22:09
 */
@Mapper
public interface KnowledgeRecordsMapper {
    // 获取该知识的阅读数
    Long getReadCount(Long knowledgeId);

    // 更新赞赏记录数
    void updateAppreciateCount(Long knowledgeId, int i);

    // 检查当前知识是否有记录
    int findByKnowledgeId(Long knowledgeId);

    // 新增记录（赞赏数初始化为1）
    void addKnowledgeRecordsForAppreciate(Long knowledgeId);

    // 更新关注/收藏记录数
    void updateFollowCount(Long knowledgeId, int i);

    // 新增记录（关注/收藏数初始化为1）
    void addKnowledgeRecordsForFollow(Long knowledgeId);
}
