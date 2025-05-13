package com.cjh.codeqna.tag.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 用户标签关联服务映射文件
 * @Create: 2025-05-08 21:19
 */
@Mapper
public interface UserTagMapper {
    // 远程调用：根据用户id获取标签id集合
    List<Long> getTagIdsByUserId(Long userId);

    // 判断当前用户是否关注该标签
    int findByUserIdAndTagId(Long userId, Long tagId);

    // 执行取消关注操作
    void cancelFollow(Long userId, Long tagId);

    // 执行关注操作
    void follow(Long userId, Long tagId);
}
