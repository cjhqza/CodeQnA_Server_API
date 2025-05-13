package com.cjh.codeqna.knowledge.controller;

import com.cjh.codeqna.knowledge.service.PostInfoService;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 问答贴信息控制器
 * @Create: 2025-04-07 11:11
 */
@RestController
@RequestMapping(value = "/api/knowledge/postInfo")
public class PostInfoController {
    @Autowired
    private PostInfoService postInfoService;

    // 根据分类获取问答贴信息
    @GetMapping (value = "/getPostInfoByPage/{pageNum}/{pageSize}")
    public Result getPostInfoByPage(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize, @RequestParam(value = "category") String category) {
        System.out.println("category:" + category);
        PageInfo<KnowledgeInfo> pageInfo =  postInfoService.getPostInfoByPage(pageNum, pageSize, category);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 获取用户可能感兴趣的问答贴信息（需要登录）
    @GetMapping (value = "/auth/getPostInfoByInterest/{pageNum}/{pageSize}")
    public Result getPostInfoByInterest(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<KnowledgeInfo> pageInfo = postInfoService.getPostInfoByInterest(pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
