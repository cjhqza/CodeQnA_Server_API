package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.common.log.annotation.Log;
import com.cjh.codeqna.common.log.enums.OperatorType;
import com.cjh.codeqna.manager.service.DtTagService;
import com.cjh.codeqna.model.entity.data.DtTag;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签管理控制器
 * @Create: 2025-01-16 22:14
 */
@RestController
@RequestMapping(value = "/admin/data/dtTag")
public class DtTagController {
    @Autowired
    private DtTagService dtTagService;

    // 标签列表
    @Log(title = "标签管理:列表", businessType = 0, operatorType = OperatorType.MANAGE)
    @GetMapping(value = "/findAll")
    public Result findAll() {
        List<DtTag> list = dtTagService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 标签添加
    @Log(title = "标签管理:新增", businessType = 1, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/addDtTag")
    public Result addDtTag(@RequestBody DtTag dtTag) {
        dtTagService.addDtTag(dtTag);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 标签修改
    @Log(title = "标签管理:修改", businessType = 2, operatorType = OperatorType.MANAGE)
    @PutMapping(value = "/editDtTag")
    public Result editDtTag(@RequestBody DtTag dtTag) {
        dtTagService.editDtTag(dtTag);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 标签删除
    @Log(title = "标签管理:删除", businessType = 3, operatorType = OperatorType.MANAGE)
    @DeleteMapping(value = "/deleteDtTag/{dtTagId}")
    public Result deleteDtTag(@PathVariable(value = "dtTagId") Long dtTagId) {
        dtTagService.deleteDtTag(dtTagId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
