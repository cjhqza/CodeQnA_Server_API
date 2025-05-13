package com.cjh.codeqna.comment.service.impl;

import com.cjh.codeqna.comment.mapper.CommentMapper;
import com.cjh.codeqna.comment.service.CommentService;
import com.cjh.codeqna.feign.user.UserFeignClient;
import com.cjh.codeqna.model.dto.comment.CommentDto;
import com.cjh.codeqna.model.entity.data.DtComment;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.comment.CommentInfo;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.util.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: cjh
 * @Description: 答复评论服务实现类
 * @Create: 2025-04-08 20:10
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserFeignClient userFeignClient;

    // 远程调用：获取已有答复评论的知识id的集合
    @Override
    public List<Long> getKnowledgeIdOfComment() {
        return commentMapper.getKnowledgeIdOfComment();
    }

    // 远程调用：根据知识id获取答复数量不包括评论数量
    @Override
    public Long getResponseCountByKnowledgeId(Long id) {
        return commentMapper.getResponseCountByKnowledgeId(id);
    }

    // 根据知识id和分类获取答复评论数据
    @Override
    public List<CommentInfo> getCommentInfoByKnowledgeIdAndCategory(Long knowledgeId, String category) {
        switch (category) {
            case "more":
                return getCommentInfoByMore(knowledgeId);
            case "latest":
                return getCommentInfoByLatest(knowledgeId);
            default:
                return null;
        }
    }

    // 评论点赞功能实现
    @Override
    public void appreciate(Long commentId) {
        // 获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        // 查询当前用户是否有点赞该评论
        int count = commentMapper.findComment(userId, commentId);
        if (count > 0) {
            // 如果有，则取消点赞
            commentMapper.cancelAppreciate(commentId, userId);
        } else {
            // 如果没有，则点赞
            commentMapper.appreciate(commentId, userId);
        }
    }

    // 提交评论功能
    @Override
    public void submitComment(CommentDto commentDto) {
        // 获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        commentDto.setUserId(userId);
        commentMapper.submitComment(commentDto);
    }

    // 按得票数获取答复评论数据
    private List<CommentInfo> getCommentInfoByMore(Long knowledgeId) {
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        // 按照赞赏数排序先查找父级评论
        List<CommentInfo> parentComments = commentMapper.getParentCommentByMore(knowledgeId, userInfo == null ? 0L : userInfo.getId());
        // 再按照最近时间排序查找非父级评论评论
        List<CommentInfo> childrenComments = commentMapper.getChildrenCommentByIds(knowledgeId, userInfo == null ? 0L : userInfo.getId());
        // 合并
        List<CommentInfo> commentList = Stream.concat(parentComments.stream(), childrenComments.stream()).collect(Collectors.toList());
        for (CommentInfo commentInfo : commentList) {
            // 远程调用用户服务根据用户id获取用户对象
            DtUser user = userFeignClient.getUserById(commentInfo.getUserId());
            commentInfo.setUserName(user.getUserName());
            commentInfo.setUserHeadImgUrl(user.getHeadImgUrl());
            if (commentInfo.getToUserId() != 0) {
                DtUser toUser = userFeignClient.getUserById(commentInfo.getToUserId());
                commentInfo.setToUserName(toUser.getUserName());
                commentInfo.setToUserHeadImgUrl(toUser.getHeadImgUrl());
            }
        }
        // 构建父子评论
        return packTwoLayersList(commentList);
    }

    // 按最近时间获取答复评论数据
    private List<CommentInfo> getCommentInfoByLatest(Long knowledgeId) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        // 按照最近时间排序先查找父级评论
        List<CommentInfo> parentComments = commentMapper.getParentCommentByLatest(knowledgeId, userId == null ? 0L : userId);
        // 再按照最近时间排序查找非父级评论评论
        List<CommentInfo> childrenComments = commentMapper.getChildrenCommentByIds(knowledgeId, userId == null ? 0L : userId);
        // 合并
        List<CommentInfo> commentList = Stream.concat(parentComments.stream(), childrenComments.stream()).collect(Collectors.toList());
        for (CommentInfo commentInfo : commentList) {
            // 远程调用用户服务根据用户id获取用户对象
            DtUser user = userFeignClient.getUserById(commentInfo.getUserId());
            commentInfo.setUserName(user.getUserName());
            commentInfo.setUserHeadImgUrl(user.getHeadImgUrl());
            if (commentInfo.getToUserId() != 0) {
                DtUser toUser = userFeignClient.getUserById(commentInfo.getToUserId());
                commentInfo.setToUserName(toUser.getUserName());
                commentInfo.setToUserHeadImgUrl(toUser.getHeadImgUrl());
            }
        }
        // 构建父子评论
        return packTwoLayersList(commentList);
    }

    private List<CommentInfo> packTwoLayersList(List<CommentInfo> commentList) {
        List<CommentInfo> list = new ArrayList<>();

        for (CommentInfo commentInfo : commentList) {
            if (commentInfo.getParentId() == 0L) {
                list.add(findCommentchildren(commentInfo, commentList));
            }
        }

        return list;
    }

    private CommentInfo findCommentchildren(CommentInfo commentInfo, List<CommentInfo> commentlist) {
        commentInfo.setChildren(new ArrayList<>());
        for (CommentInfo commentInfo1 : commentlist) {
            if (commentInfo.getId().longValue() == commentInfo1.getAncestorId().longValue()) {
                commentInfo.getChildren().add(commentInfo1);
            }
        }

        return commentInfo;
    }
}
