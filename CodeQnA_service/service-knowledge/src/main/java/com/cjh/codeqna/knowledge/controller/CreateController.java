package com.cjh.codeqna.knowledge.controller;

import com.cjh.codeqna.knowledge.service.CreateService;
import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeDraftInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识创作控制器
 * @Create: 2025-04-11 9:48
 */
@RestController
@RequestMapping(value = "/api/knowledge/create")
public class CreateController {
    @Autowired
    private CreateService createService;

    // 根据用户id和知识类型获取草稿内容
    @GetMapping(value = "/getDraftKnowledge/{type}")
    public Result getDraftKnowledge(@PathVariable(value = "type") Integer type) {
        List<KnowledgeDraftInfo> list = createService.getDraftKnowledge(type);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 保存已有的草稿
    @PostMapping(value = "/saveDraftKnowledge")
    public Result saveDraftKnowledge(@RequestBody KnowledgeDraftInfo knowledgeDraftInfo) {
        createService.saveDraftKnowledge(knowledgeDraftInfo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 新建草稿
    @PostMapping(value = "/addDraftKnowledge")
    public Result addDraftKnowledge(@RequestBody KnowledgeDraftInfo knowledgeDraftInfo) {
        Long id = createService.addDraftKnowledge(knowledgeDraftInfo);
        return Result.build(id, ResultCodeEnum.SUCCESS);
    }

    // 发布问答
    @PostMapping(value = "/publishKnowledge")
    public Result publishKnowledge(@RequestBody KnowledgeDraftInfo knowledgeDraftInfo) {
        createService.publishKnowledge(knowledgeDraftInfo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
