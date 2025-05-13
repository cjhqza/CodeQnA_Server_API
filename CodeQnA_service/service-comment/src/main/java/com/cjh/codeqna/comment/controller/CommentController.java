package com.cjh.codeqna.comment.controller;

import com.cjh.codeqna.comment.service.CommentService;
import com.cjh.codeqna.model.dto.comment.CommentDto;
import com.cjh.codeqna.model.vo.comment.CommentInfo;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 答复评论控制器
 * @Create: 2025-04-08 20:08
 */
@RestController
@RequestMapping(value = "/api/comment/commentInfo")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 根据知识id和分类获取答复评论数据
    @GetMapping (value = "/getCommentInfoByKnowledgeIdAndCategory/{knowledgeId}")
    public Result getCommentInfoByKnowledgeIdAndCategory(@PathVariable(value = "knowledgeId") Long knowledgeId, @RequestParam(value = "category") String category) {
        List<CommentInfo> list = commentService.getCommentInfoByKnowledgeIdAndCategory(knowledgeId, category);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 评论点赞功能实现
    @PostMapping (value = "/appreciate/{commentId}")
    public Result appreciate(@PathVariable(value = "commentId") Long commentId) {
        commentService.appreciate(commentId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 提交评论功能
    @PostMapping (value = "/submitComment")
    public Result submitComment(@RequestBody CommentDto commentDto) {
        commentService.submitComment(commentDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    // 远程调用：获取已有答复评论的知识id的集合
    @GetMapping (value = "/getKnowledgeIdOfComment")
    public List<Long> getKnowledgeIdOfComment() {
        return commentService.getKnowledgeIdOfComment();
    }

    // 远程调用：根据知识id获取答复数量不包括评论数量
    @GetMapping (value = "/getResponseCountByKnowledgeId/{id}")
    public Long getResponseCountByKnowledgeId(@PathVariable(value = "id") Long id) {
        return commentService.getResponseCountByKnowledgeId(id);
    }
}
