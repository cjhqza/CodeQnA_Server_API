package com.cjh.codeqna.knowledge.controller;

import com.cjh.codeqna.knowledge.service.ArticleInfoService;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 文章信息控制器
 * @Create: 2025-04-09 16:15
 */
@RestController
@RequestMapping(value = "/api/knowledge/articleInfo")
public class ArticleInfoController {
    @Autowired
    private ArticleInfoService articleInfoService;

    // 获取赞赏数高的文章信息的集合
    @GetMapping (value = "/getAppreciateArticleInfoList")
    public Result getAppreciateArticleInfoList() {
        List<KnowledgeInfo> list = articleInfoService.getAppreciateArticleInfoList();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 获取用户关注标签相关的文章信息集合（需要登录）
    @GetMapping (value = "/auth/getRelatedArticleInfoList")
    public Result getRelatedArticleInfoList() {
        List<KnowledgeInfo> list = articleInfoService.getRelatedArticleInfoList();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

}
