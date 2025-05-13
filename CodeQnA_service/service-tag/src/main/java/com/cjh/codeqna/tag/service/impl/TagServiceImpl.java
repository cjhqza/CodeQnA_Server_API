package com.cjh.codeqna.tag.service.impl;

import com.cjh.codeqna.feign.knowledge.KnowledgeFeignClient;
import com.cjh.codeqna.model.dto.tag.TagSearchDto;
import com.cjh.codeqna.model.entity.data.DtTag;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.cjh.codeqna.model.vo.tag.TagSearchInfo;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.tag.mapper.TagMapper;
import com.cjh.codeqna.tag.mapper.TagRecordsMapper;
import com.cjh.codeqna.tag.mapper.UserTagMapper;
import com.cjh.codeqna.tag.service.TagService;
import com.cjh.codeqna.util.AuthContextUtil;
import com.cjh.codeqna.util.PageInfoUtils;
import com.cjh.codeqna.util.TreeHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签服务接口实现类
 * @Create: 2025-04-07 15:29
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private KnowledgeFeignClient knowledgeFeignClient;
    @Autowired
    private TagRecordsMapper tagRecordsMapper;

    // 远程调用：根据标签id集合获取标签基本信息对象集合
    @Override
    public List<TagBaseInfo> getTagBaseInfoListByIds(List<Long> tagIds) {
        if (tagIds == null || tagIds.size() == 0) {
            return new ArrayList<>();
        }
        return tagMapper.getTagBaseInfoListByIds(tagIds);
    }

    // 远程调用：根据用户id获取标签id集合
    @Override
    public List<Long> getTagIdsByUserId(Long userId) {
        return userTagMapper.getTagIdsByUserId(userId);
    }

    // 标签搜索
    @Override
    public PageInfo<TagSearchInfo> getTagBaseInfoListByTagSearchInfo(TagSearchDto tagSearchDto) {
        String category = tagSearchDto.getCategory();
        String input = tagSearchDto.getInput();
        Integer pageNum = tagSearchDto.getPageNum();
        Integer pageSize = tagSearchDto.getPageSize();
        switch  (category) {
            case "hot":
                return getHotTagBaseInfoList(input, pageNum, pageSize);
            case "most":
                return getMostTagBaseInfoList(input, pageNum, pageSize);
            default:
                return null;
        }
    }

    // 关注功能实现
    @Override
    public void follow(Long tagId) {
        // 获取当前用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        // 查看当前用户是否有关注该标签
        int followCount = userTagMapper.findByUserIdAndTagId(userId, tagId);
        if (followCount > 0) {
            // 执行取消关注操作
            userTagMapper.cancelFollow(userId, tagId);
            // 更新标签记录表
            tagRecordsMapper.updateFollowCount(tagId, -1);
        } else {
            // 执行关注操作
            userTagMapper.follow(userId, tagId);
            // 查看标签记录表是否有该标签记录
            int recordCount = tagRecordsMapper.findByTagId(tagId);
            if (recordCount > 0) {
                // 更新标签记录表
                tagRecordsMapper.updateFollowCount(tagId, 1);
            } else {
                // 新建标签记录（关注/收藏数记录为1）
                tagRecordsMapper.addTagRecordsForFollow(tagId);
            }

        }
    }

    // 获取标签树
    @Override
    public List<DtTag> getTagTree() {
        // 获取所有标签数据
        List<DtTag> dtTagList = tagMapper.getTagList();

        return TreeHelper.buildTagTree(dtTagList);
    }

    // 根据热门搜索（最多人关注排序）
    private PageInfo<TagSearchInfo> getHotTagBaseInfoList(String input, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<DtTag> taglist = tagMapper.getHotTagBaseInfoList(input);

        return packPageInfo(taglist);
    }

    // 根据篇数多搜索（问答贴数量+文章数量）
    private PageInfo<TagSearchInfo> getMostTagBaseInfoList(String input, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<DtTag> tagList = tagMapper.getMostTagBaseInfoList(input);

        return packPageInfo(tagList);
    }

    private PageInfo<TagSearchInfo> packPageInfo(List<DtTag> tagList) {
        // 获取当前用户信息
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        // 创建返回的集合
        List<TagSearchInfo> list = new ArrayList<>();
        for (DtTag tag : tagList) {
            // 创建TagSearchInfo对象
            TagSearchInfo tagSearchInfo = new TagSearchInfo();
            tagSearchInfo.setDtTag(tag);

            // 远程调用获取问答贴数量
            tagSearchInfo.setPostNum(knowledgeFeignClient.getPostNumByTagId(tag.getId()));
            // 远程调用获取文章数量
            tagSearchInfo.setArticleNum(knowledgeFeignClient.getArticleNumByTagId(tag.getId()));

            // 判断当前用户是否关注该标签
            if (userInfo != null) {
                int followCount = userTagMapper.findByUserIdAndTagId(userInfo.getId(), tag.getId());
                tagSearchInfo.setIsFollow(followCount > 0);
            }

            list.add(tagSearchInfo);
        }

        PageInfo<DtTag> tagPageInfo = new PageInfo<>(tagList);
        return PageInfoUtils.copyListPage(tagPageInfo, list);
    }
}
