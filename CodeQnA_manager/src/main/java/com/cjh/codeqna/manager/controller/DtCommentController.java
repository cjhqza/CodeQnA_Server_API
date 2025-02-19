package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.DtCommentService;
import com.cjh.codeqna.model.dto.data.DtCommentDto;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.data.DtCommentVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 评论管理控制器
 * @Create: 2025-02-17 17:19
 */
@RestController
@RequestMapping(value = "/admin/data/dtComment")
public class DtCommentController {
    @Autowired
    private DtCommentService dtCommentService;

    // 评论列表
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize, DtCommentDto dtCommentDto) {
        PageInfo<DtCommentVo> list = dtCommentService.findByPage(pageNum, pageSize, dtCommentDto);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 删评
    @DeleteMapping(value = "/deleteDtComment/{id}")
    public Result deleteDtComment(@PathVariable(value = "id") Long id) {
        dtCommentService.deleteDtComment(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
