package com.cjh.codeqna.knowledge.service;

import com.cjh.codeqna.model.dto.knowledge.KnowledgeSearchDto;
import com.cjh.codeqna.model.dto.knowledge.KnowledgeUserDto;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeInfo;
import com.github.pagehelper.PageInfo;

/**
 * @Author: cjh
 * @Description: 知识信息服务接口
 * @Create: 2025-04-09 20:03
 */
public interface KnowledgeInfoService {
    // 赞赏功能实现
    void appreciate(Long knowledgeId);

    // 关注/收藏功能实现
    void follow(Long knowledgeId);

    // 远程调用：根据标签id获取对应的问答贴数量
    Long getPostNumByTagId(Long tagId);

    // 远程调用：根据标签id获取对应的文章数量
    Long getArticleNumByTagId(Long tagId);

    // 知识信息搜索
    PageInfo<KnowledgeInfo> getKnowledgeInfoListByKnowledgeSearchDto(KnowledgeSearchDto knowledgeSearchDto);

    // 根据知识id获取知识信息
    KnowledgeInfo getKnowledgeInfoById(Long knowledgeId);

    // 远程调用：根据用户id获取总的点赞数
    Long getAppreciateCountByUserId(Long userId);

    // 根据用户id获取对应发布的知识信息集合
    PageInfo<KnowledgeInfo> getKnowledgeInfoListByUserIdByRelease(KnowledgeUserDto knowledgeUserDto);

    // 根据用户id获去对应赞赏过的知识信息集合
    PageInfo<KnowledgeInfo> getKnowledgeInfoListByUserIdByAppreciate(KnowledgeUserDto knowledgeUserDto);

    // 根据用户id获取关注/收藏的知识信息集合
    PageInfo<KnowledgeInfo> getKnowledgeInfoListByUserIdByFollow(KnowledgeUserDto knowledgeUserDto);

    // 撤贴
    void cancelKnowledge(Long knowledgeId);
}
