package com.cjh.codeqna.knowledge.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: cjh
 * @Description: 知识赞赏映射文件
 * @Create: 2025-04-08 22:13
 */
@Mapper
public interface KnowledgeAppreciateMapper {
    // 判断当前用户是否有赞赏该知识信息
    int findByUserIdAndKnowledgeId(Long userId, Long knowledgeId);

    // 执行取消赞赏操作
    void cancelAppreciate(Long userId, Long knowledgeId);

    // 执行赞赏操作
    void appreciate(Long userId, Long knowledgeId);
}
