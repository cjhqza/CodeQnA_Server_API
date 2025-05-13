package com.cjh.codeqna.knowledge.mapper;

import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识服务映射接口
 * @Create: 2025-04-09 20:04
 */
@Mapper
public interface KnowledgeInfoMapper {
    // 远程调用：根据标签id获取对应的问答贴数量
    Long getPostNumByTagId(Long tagId);

    // 远程调用：根据标签id获取对应的文章数量
    Long getArticleNumByTagId(Long tagId);

    // 按照最近时间获取知识信息
    List<DtKnowledge> getKnowledgeInfoListForLatest(String input, Integer type, List<Long> tagIds, Integer size);

    // 按阅读数(热门的)获取知识信息
    List<DtKnowledge> getKnowledgeInfoListForPopular(String input, Integer type, List<Long> tagIds, int size);

    // 按赞赏数（质量）获取知识信息
    List<DtKnowledge> getKnowledgeInfoListForQuality(String input, Integer type, List<Long> tagIds, int size);

    // 按关注数（收藏）获取知识信息
    List<DtKnowledge> getKnowledgeInfoListForFollow(String input, Integer type, List<Long> tagIds, int size);

    // 根据知识id获取知识信息
    DtKnowledge getKnowledgeInfoById(Long knowledgeId);

    // 远程调用：根据用户id获取总的点赞数
    Long getAppreciateCountByUserId(Long userId);

    // 获取用户发布的类型为type的知识数据
    List<DtKnowledge> getKnowledgeListByUserIdByRelease(Long userId, Integer type);

    // 根据用户id获去对应赞赏过的知识信息集合
    List<DtKnowledge> getKnowledgeListByUserIdByAppreciate(Long userId, Integer type);

    // 根据用户id获取关注/收藏的知识信息集合
    List<DtKnowledge> getKnowledgeInfoListByUserIdByFollow(Long userId, Integer type);

    // 撤贴
    void cancelKnowledge(Long knowledgeId);
}
