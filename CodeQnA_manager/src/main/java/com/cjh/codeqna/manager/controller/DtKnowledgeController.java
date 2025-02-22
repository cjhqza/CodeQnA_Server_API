package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.DtKnowledgeService;
import com.cjh.codeqna.model.dto.data.DtKnowledgeDto;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: cjh
 * @Description: 知识管理控制器
 * @Create: 2025-01-25 23:24
 */
@RestController
@RequestMapping(value = "/admin/data/dtKnowledge")
public class DtKnowledgeController {
    @Autowired
    private DtKnowledgeService dtKnowledgeService;

    // 知识列表
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize, DtKnowledgeDto dtKnowledgeDto) {
        PageInfo<DtKnowledgeVo> pageInfo = dtKnowledgeService.findByPage(pageNum, pageSize, dtKnowledgeDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 修改知识状态
    @PutMapping(value = "/editDtKnowledge/{id}")
    public Result editDtKnowledge(@PathVariable("id") Long id) {
        dtKnowledgeService.editDtKnowledge(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 获取待审批的知识列表
    @PostMapping(value = "/findByPageByOrder/{pageNum}/{pageSize}")
    public Result findByPageByOrder(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<DtKnowledgeVo> pageInfo = dtKnowledgeService.findByPageByOrder(pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 审批后的知识状态
    @PutMapping(value = "/processDtKnowledge/{id}/{status}")
    public Result processDtKnowledge(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        dtKnowledgeService.processDtKnowledge(id, status);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
