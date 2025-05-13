package com.cjh.codeqna.knowledge.mapper;

import com.cjh.codeqna.model.entity.data.DtKnowledge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 文章信息服务映射文件
 * @Create: 2025-04-09 16:16
 */
@Mapper
public interface ArticleInfoMapper {
    // 获取赞赏数高的文章信息的集合
    List<DtKnowledge> getAppreciateArticleInfoList();

    // 根据用户感兴趣标签集合获取文章集合
    List<DtKnowledge> getRelatedArticleInfoList(List<Long> tagIds);
}
