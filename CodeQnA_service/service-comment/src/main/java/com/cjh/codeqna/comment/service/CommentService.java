package com.cjh.codeqna.comment.service;

import com.cjh.codeqna.model.dto.comment.CommentDto;
import com.cjh.codeqna.model.vo.comment.CommentInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 答复评论服务接口
 * @Create: 2025-05-08 20:10
 */
public interface CommentService {
    // 远程调用：获取已有答复评论的知识id的集合
    List<Long> getKnowledgeIdOfComment();

    // 远程调用：根据知识id获取答复数量不包括评论数量
    Long getResponseCountByKnowledgeId(Long id);

    // 根据知识id和分类获取答复评论数据
    List<CommentInfo> getCommentInfoByKnowledgeIdAndCategory(Long knowledgeId, String category);

    // 评论点赞功能实现
    void appreciate(Long commentId);

    // 提交评论功能
    void submitComment(CommentDto commentDto);
}
