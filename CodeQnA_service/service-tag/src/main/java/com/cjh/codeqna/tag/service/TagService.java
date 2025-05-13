package com.cjh.codeqna.tag.service;

import com.cjh.codeqna.model.dto.tag.TagSearchDto;
import com.cjh.codeqna.model.entity.data.DtTag;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.cjh.codeqna.model.vo.tag.TagSearchInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签服务接口
 * @Create: 2025-04-07 15:29
 */
public interface TagService {
    // 远程调用：根据标签id集合获取标签基本信息对象集合
    List<TagBaseInfo> getTagBaseInfoListByIds(List<Long> tagIds);

    // 远程调用：根据用户id获取标签id集合
    List<Long> getTagIdsByUserId(Long userId);

    // 标签搜索
    PageInfo<TagSearchInfo> getTagBaseInfoListByTagSearchInfo(TagSearchDto tagSearchDto);

    // 关注功能实现
    void follow(Long tagId);

    // 获取标签树
    List<DtTag> getTagTree();
}
