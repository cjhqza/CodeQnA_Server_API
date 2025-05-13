package com.cjh.codeqna.knowledge.service;

import com.cjh.codeqna.model.vo.knowledge.KnowledgeInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 文章信息服务接口
 * @Create: 2025-04-09 16:15
 */
public interface ArticleInfoService {
    // 获取赞赏数高的文章信息的集合
    List<KnowledgeInfo> getAppreciateArticleInfoList();

    // 获取用户关注标签相关的文章信息集合（需要登录）
    List<KnowledgeInfo> getRelatedArticleInfoList();
}
