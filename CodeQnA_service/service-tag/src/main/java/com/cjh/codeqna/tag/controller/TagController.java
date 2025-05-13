package com.cjh.codeqna.tag.controller;

import com.cjh.codeqna.model.dto.tag.TagSearchDto;
import com.cjh.codeqna.model.entity.data.DtTag;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.cjh.codeqna.model.vo.tag.TagSearchInfo;
import com.cjh.codeqna.tag.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签服务控制器
 * @Create: 2025-04-07 15:26
 */
@RestController
@RequestMapping(value = "/api/tag/tagInfo")
public class TagController {
    @Autowired
    private TagService tagService;

    // 远程调用：根据标签id集合获取标签基本信息对象集合
    @PostMapping(value = "/getTagBaseInfoListByIds")
    public List<TagBaseInfo> getTagBaseInfoListByIds(@RequestBody List<Long> tagIds) {
        return tagService.getTagBaseInfoListByIds(tagIds);
    }

    // 远程调用：根据用户id获取标签id集合
    @GetMapping(value = "/getTagIdsByUserId/{userId}")
    public List<Long> getTagIdsByUserId(@PathVariable(value = "userId") Long userId) {
        return tagService.getTagIdsByUserId(userId);
    }

    // 标签搜索
    @PostMapping(value = "/getTagBaseInfoListByTagSearchInfo")
    public Result getTagBaseInfoListByTagSearchInfo(@RequestBody TagSearchDto tagSearchDto) {
        PageInfo<TagSearchInfo> pageInfo = tagService.getTagBaseInfoListByTagSearchInfo(tagSearchDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 关注功能实现
    @PostMapping(value = "/auth/follow/{tagId}")
    public Result follow(@PathVariable(value = "tagId") Long tagId) {
        tagService.follow(tagId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 获取标签树
    @GetMapping(value = "/getTagTree")
    public Result getTagTree() {
        List<DtTag> tagTreeList = tagService.getTagTree();
        return Result.build(tagTreeList, ResultCodeEnum.SUCCESS);
    }
}
