package com.cjh.codeqna.knowledge.service;

import com.cjh.codeqna.model.vo.knowledge.KnowledgeDraftInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识创作服务接口
 * @Create: 2025-04-11 9:50
 */
public interface CreateService {
    // 根据用户id和知识类型获取草稿内容
    List<KnowledgeDraftInfo> getDraftKnowledge(Integer type);

    // 保存已有的草稿
    void saveDraftKnowledge(KnowledgeDraftInfo knowledgeDraftInfo);

    // 新建草稿
    Long addDraftKnowledge(KnowledgeDraftInfo knowledgeDraftInfo);

    // 发布问答
    void publishKnowledge(KnowledgeDraftInfo knowledgeDraftInfo);
}
