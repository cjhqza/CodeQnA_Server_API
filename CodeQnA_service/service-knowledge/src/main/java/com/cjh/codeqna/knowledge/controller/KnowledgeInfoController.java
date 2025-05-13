package com.cjh.codeqna.knowledge.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cjh.codeqna.knowledge.service.KnowledgeInfoService;
import com.cjh.codeqna.model.dto.knowledge.KnowledgeSearchDto;
import com.cjh.codeqna.model.dto.knowledge.KnowledgeUserDto;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: cjh
 * @Description: 知识信息服务控制器
 * @Create: 2025-04-09 20:02
 */
@RestController
@RequestMapping(value = "/api/knowledge/knowledgeInfo")
public class KnowledgeInfoController {
    @Autowired
    private KnowledgeInfoService knowledgeInfoService;

    // 赞赏功能实现
    // @SentinelResource(value = "appreciate")
    @PostMapping(value = "/auth/appreciate/{knowledgeId}")
    public Result appreciate(@PathVariable(value = "knowledgeId") Long knowledgeId) {
        knowledgeInfoService.appreciate(knowledgeId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 关注/收藏功能实现
    @PostMapping(value = "/auth/follow/{knowledgeId}")
    public Result follow(@PathVariable(value = "knowledgeId") Long knowledgeId) {
        knowledgeInfoService.follow(knowledgeId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 远程调用：根据标签id获取对应的问答贴数量
    @GetMapping(value = "/getPostNumByTagId/{tagId}")
    public Long getPostNumByTagId(@PathVariable(value = "tagId") Long tagId) {
        return knowledgeInfoService.getPostNumByTagId(tagId);
    }

    // 远程调用：根据标签id获取对应的文章数量
    @GetMapping(value = "/getArticleNumByTagId/{tagId}")
    public Long getArticleNumByTagId(@PathVariable(value = "tagId") Long tagId) {
        return knowledgeInfoService.getArticleNumByTagId(tagId);
    }

    // 知识信息搜索
    @PostMapping(value = "/getKnowledgeInfoListByKnowledgeSearchDto")
    public Result getKnowledgeInfoListByKnowledgeSearchDto(@RequestBody KnowledgeSearchDto knowledgeSearchDto) {
        PageInfo<KnowledgeInfo> pageInfo =  knowledgeInfoService.getKnowledgeInfoListByKnowledgeSearchDto(knowledgeSearchDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 根据知识id获取知识信息
    @PostMapping(value = "/getKnowledgeInfoById/{knowledgeId}")
    public Result getKnowledgeInfoById(@PathVariable(value = "knowledgeId") Long knowledgeId) {
        KnowledgeInfo knowledgeInfo = knowledgeInfoService.getKnowledgeInfoById(knowledgeId);
        return Result.build(knowledgeInfo, ResultCodeEnum.SUCCESS);
    }

    // 远程调用：根据用户id获取总的点赞数
    @GetMapping(value = "/getAppreciateCountByUserId/{userId}")
    public Long getAppreciateCountByUserId(@PathVariable(value = "userId") Long userId) {
        return knowledgeInfoService.getAppreciateCountByUserId(userId);
    }

    // 根据用户id获取对应发布的知识信息集合
    @PostMapping(value = "/getKnowledgeInfoListByUserIdByRelease")
    public Result getKnowledgeInfoListByUserIdByRelease(@RequestBody KnowledgeUserDto knowledgeUserDto) {
        PageInfo<KnowledgeInfo> pageInfo = knowledgeInfoService.getKnowledgeInfoListByUserIdByRelease(knowledgeUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 根据用户id获去对应赞赏过的知识信息集合
    @PostMapping(value = "/getKnowledgeInfoListByUserIdByAppreciate")
    public Result getKnowledgeInfoListByUserIdByAppreciate(@RequestBody KnowledgeUserDto knowledgeUserDto) {
        PageInfo<KnowledgeInfo> pageInfo = knowledgeInfoService.getKnowledgeInfoListByUserIdByAppreciate(knowledgeUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 根据用户id获取关注/收藏的知识信息集合
    @PostMapping(value = "/getKnowledgeInfoListByUserIdByFollow")
    public Result getKnowledgeInfoListByUserIdByFollow(@RequestBody KnowledgeUserDto knowledgeUserDto) {
        PageInfo<KnowledgeInfo> pageInfo = knowledgeInfoService.getKnowledgeInfoListByUserIdByFollow(knowledgeUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 撤贴
    @PostMapping(value = "/auth/cancelKnowledge/{knowledgeId}")
    public Result cancelKnowledge(@PathVariable(value = "knowledgeId") Long knowledgeId) {
        knowledgeInfoService.cancelKnowledge(knowledgeId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
