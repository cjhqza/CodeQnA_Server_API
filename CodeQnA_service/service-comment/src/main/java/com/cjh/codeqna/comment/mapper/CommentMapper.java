package com.cjh.codeqna.comment.mapper;

import com.cjh.codeqna.model.dto.comment.CommentDto;
import com.cjh.codeqna.model.entity.data.DtComment;
import com.cjh.codeqna.model.vo.comment.CommentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 答复评论服务映射文件
 * @Create: 2025-04-08 20:11
 */
@Mapper
public interface CommentMapper {
    // 远程调用：获取已有答复评论的知识id的集合
    List<Long> getKnowledgeIdOfComment();

    // 远程调用：根据知识id获取答复数量不包括评论数量
    Long getResponseCountByKnowledgeId(Long knowledgeId);

    // 按照最近时间排序先查找父级评论
    List<CommentInfo> getParentCommentByLatest(Long knowledgeId, Long userId);

    // 再按照最近时间排序查找非父级评论评论
    List<CommentInfo> getChildrenCommentByIds(Long knowledgeId, long userId);

    // 按照赞赏数排序先查找父级评论
    List<CommentInfo> getParentCommentByMore(Long knowledgeId, long userId);

    // 查询当前用户是否有点赞该评论
    int findComment(Long userId, Long commentId);

    // 取消点赞
    void cancelAppreciate(Long commentId, Long userId);

    // 点赞
    void appreciate(Long commentId, Long userId);

    // submitComment
    void submitComment(CommentDto commentDto);
}
