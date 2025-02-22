package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识标签服务映射文件
 * @Create: 2025-01-25 23:53
 */
@Mapper
public interface DtKnowledgeTagMapper {
    // 根据标签id查找所有关联的知识id
    List<Long> findKnowledgeIdByTagId(Long tagId);

    // 根据知识id查找对应的所有标签id集合
    List<Long> findTagIdByKnowledgeId(Long knowledgeId);
}
