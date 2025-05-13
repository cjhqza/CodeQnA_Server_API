package com.cjh.codeqna.knowledge.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: cjh
 * @Description: 知识关注映射文件
 * @Create: 2025-05-08 22:26
 */
@Mapper
public interface KnowledgeFollowMapper {
    // 判断当前用户是否有关注该知识信息
    int findByUserIdAndKnowledgeId(Long userId, Long knowledgeId);

    // 执行取消关注/收藏操作
    void cancelFollow(Long userId, Long knowledgeId);

    // 执行关注/收藏操作
    void follow(Long userId, Long knowledgeId);
}
