package com.cjh.codeqna.tag.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: cjh
 * @Description: 标签记录服务映射文件
 * @Create: 2025-04-10 12:03
 */
@Mapper
public interface TagRecordsMapper {

    // 更新标签记录表
    void updateFollowCount(Long tagId, int i);

    // 查看标签记录表是否有该标签记录
    int findByTagId(Long tagId);

    // 新建标签记录（关注/收藏数记录为1）
    void addTagRecordsForFollow(Long tagId);
}
