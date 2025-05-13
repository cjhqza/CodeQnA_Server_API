package com.cjh.codeqna.knowledge.mapper;

import com.cjh.codeqna.model.entity.data.DtKnowledge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 问答贴信息服务映射文件
 * @Create: 2025-04-07 11:31
 */
@Mapper
public interface PostInfoMapper {
    // 按时间排序获取问答贴信息
    List<DtKnowledge> getPostInfoByLatest();

    // 获取根据知识记录表按照阅读数排序的知识表中的问答贴集合
    List<DtKnowledge> getPostInfoByPopular();

    // 获取根据知识记录表按照赞赏数排序的知识表中的问答贴集合
    List<DtKnowledge> getPostInfoByQuality();

    // 获取根据知识记录表按照关注数排序的知识表中的问答贴集合
    List<DtKnowledge> getPostInfoByFollow();

    // 根据获取的知识id集合获取除集合内id外的其他知识数据集合
    List<DtKnowledge> getPostInfoByUnsolved(List<Long> knowledgeIds);

    // 根据用户感兴趣标签集合获取问答贴集合
    List<DtKnowledge> getPostInfoByInterest(List<Long> tagIds);
}
