package com.cjh.codeqna.knowledge.mapper;

import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeDraftInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识创作服务映射文件
 * @Create: 2025-04-11 9:51
 */
@Mapper
public interface CreateMapper {
    // 根据用户id和知识类型获取草稿内容
    List<DtKnowledge> getDraftKnowledge(Long userId, Integer type);

    // 保存已有的知识数据
    void saveDtKnowledge(DtKnowledge dtKnowledge);

    // 新建知识数据
    void addDtKnowledge(DtKnowledge dtKnowledge);

    // 发布(将status状态转换为0)
    void publishKnowledge(Long knowledgeId);
}
