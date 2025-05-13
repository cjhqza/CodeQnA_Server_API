package com.cjh.codeqna.knowledge.service.impl;

import com.cjh.codeqna.feign.comment.CommentFeignClient;
import com.cjh.codeqna.feign.tag.TagFeignClient;
import com.cjh.codeqna.feign.user.UserFeignClient;
import com.cjh.codeqna.knowledge.mapper.*;
import com.cjh.codeqna.knowledge.service.KnowledgeInfoService;
import com.cjh.codeqna.model.dto.knowledge.KnowledgeSearchDto;
import com.cjh.codeqna.model.dto.knowledge.KnowledgeUserDto;
import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeInfo;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeRecordsInfo;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.util.AuthContextUtil;
import com.cjh.codeqna.util.PageInfoUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: cjh
 * @Description: 知识信息服务接口实现类
 * @Create: 2025-05-09 20:04
 */
@Service
public class KnowledgeInfoServiceImpl implements KnowledgeInfoService {
    @Autowired
    private KnowledgeInfoMapper knowledgeInfoMapper;
    @Autowired
    private KnowledgeRecordsMapper knowledgeRecordsMapper;
    @Autowired
    private KnowledgeAppreciateMapper knowledgeAppreciateMapper;
    @Autowired
    private KnowledgeFollowMapper knowledgeFollowMapper;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private TagFeignClient tagFeignClient;
    @Autowired
    private CommentFeignClient commentFeignClient;
    @Autowired
    private KnowledgeTagMapper knowledgeTagMapper;

    // 赞赏功能实现
    @Override
    public void appreciate(Long knowledgeId) {
        // 获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        // 查询当前是否已对该知识id点赞
        int appreciateCount = knowledgeAppreciateMapper.findByUserIdAndKnowledgeId(userId, knowledgeId);
        if (appreciateCount > 0) {
            // 执行取消赞赏操作
            knowledgeAppreciateMapper.cancelAppreciate(userId, knowledgeId);
            // 更新赞赏记录数
            knowledgeRecordsMapper.updateAppreciateCount(knowledgeId, -1);
        } else {
            // 执行赞赏操作
            knowledgeAppreciateMapper.appreciate(userId, knowledgeId);
            // 检查当前知识是否有记录
            int recordsCount = knowledgeRecordsMapper.findByKnowledgeId(knowledgeId);
            if (recordsCount > 0) {
                // 更新记录
                knowledgeRecordsMapper.updateAppreciateCount(knowledgeId, 1);
            } else {
                // 新增记录（赞赏数初始化为1）
                knowledgeRecordsMapper.addKnowledgeRecordsForAppreciate(knowledgeId);
            }
        }
    }

    // 关注/收藏功能实现
    @Override
    public void follow(Long knowledgeId) {
        // 获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        // 查询当前是否已对该知识id关注/收藏
        int followCount = knowledgeFollowMapper.findByUserIdAndKnowledgeId(userId, knowledgeId);
        if (followCount > 0) {
            // 执行取消关注/收藏操作
            knowledgeFollowMapper.cancelFollow(userId, knowledgeId);
            // 更新关注/收藏记录数
            knowledgeRecordsMapper.updateFollowCount(knowledgeId, -1);
        } else {
            // 执行关注/收藏操作
            knowledgeFollowMapper.follow(userId, knowledgeId);
            // 检查当前知识是否有记录
            int recordsCount = knowledgeRecordsMapper.findByKnowledgeId(knowledgeId);
            if (recordsCount > 0) {
                // 更新记录
                knowledgeRecordsMapper.updateFollowCount(knowledgeId, 1);
            } else {
                // 新增记录（关注/收藏数初始化为1）
                knowledgeRecordsMapper.addKnowledgeRecordsForFollow(knowledgeId);
            }
        }
    }

    // 远程调用：根据标签id获取对应的问答贴数量
    @Override
    public Long getPostNumByTagId(Long tagId) {
        return knowledgeInfoMapper.getPostNumByTagId(tagId);
    }

    // 远程调用：根据标签id获取对应的文章数量
    @Override
    public Long getArticleNumByTagId(Long tagId) {
        return knowledgeInfoMapper.getArticleNumByTagId(tagId);
    }

    // 知识信息搜索
    @Override
    public PageInfo<KnowledgeInfo> getKnowledgeInfoListByKnowledgeSearchDto(KnowledgeSearchDto knowledgeSearchDto) {
        switch (knowledgeSearchDto.getCategory()) {
            case "latest":
                return getKnowledgeInfoListForLatest(knowledgeSearchDto);
            case "popular":
                return getKnowledgeInfoListForPopular(knowledgeSearchDto);
            case "quality":
                return getKnowledgeInfoListForQuality(knowledgeSearchDto);
            case "follow":
                return getKnowledgeInfoListForFollow(knowledgeSearchDto);
            default:
                return null;
        }
    }

    // 根据知识id获取知识信息
    @Override
    public KnowledgeInfo getKnowledgeInfoById(Long knowledgeId) {
        DtKnowledge dtKnowledge = knowledgeInfoMapper.getKnowledgeInfoById(knowledgeId);
        // 创建知识信息对象
        KnowledgeInfo knowledgeInfo = new KnowledgeInfo();
        BeanUtils.copyProperties(dtKnowledge, knowledgeInfo);
        // 远程调用用户服务方法获取用户对象
        DtUser dtUser = userFeignClient.getUserById(dtKnowledge.getUserId());
        knowledgeInfo.setUserName(dtUser.getUserName());
        knowledgeInfo.setHeadImgUrl(dtUser.getHeadImgUrl());
        // 获取问答贴对应标签号集合
        List<Long> tagIds = knowledgeTagMapper.getTagIdsByKnowledgeId(dtKnowledge.getId());
        // 远程调用标签服务方法获取标签基本信息对象
        List<TagBaseInfo> tagBaseInfoList = tagFeignClient.getTagBaseInfoListByIds(tagIds);
        knowledgeInfo.setTagBaseInfoList(tagBaseInfoList);

        KnowledgeRecordsInfo knowledgeRecordsInfo = new KnowledgeRecordsInfo();
        // 获取该知识的阅读数
        Long readCount = knowledgeRecordsMapper.getReadCount(dtKnowledge.getId());
        knowledgeRecordsInfo.setReadCount((readCount == null ? 0L : readCount));
        // 远程调用答复评论服务获取该知识的答复数（parentId = 0）
        Long responseCount = commentFeignClient.getResponseCountByKnowledgeId(dtKnowledge.getId());
        knowledgeRecordsInfo.setResponseCount(responseCount == null ? 0L : responseCount);

        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        if (userInfo != null) {
            // 判断当前用户是否有赞赏该知识信息
            int appreciateCount = knowledgeAppreciateMapper.findByUserIdAndKnowledgeId(userInfo.getId(), dtKnowledge.getId());
            knowledgeRecordsInfo.setIsAppreciate(appreciateCount > 0);
            // 判断当前用户是否有关注该知识信息
            int followCount = knowledgeFollowMapper.findByUserIdAndKnowledgeId(userInfo.getId(), dtKnowledge.getId());
            knowledgeRecordsInfo.setIsFollow(followCount > 0);
        }
        knowledgeInfo.setKnowledgeRecordsInfo(knowledgeRecordsInfo);

        return knowledgeInfo;
    }

    // 远程调用：根据用户id获取总的点赞数
    @Override
    public Long getAppreciateCountByUserId(Long userId) {
        return knowledgeInfoMapper.getAppreciateCountByUserId(userId);
    }

    // 根据用户id获取对应发布的知识信息集合
    @Override
    public PageInfo<KnowledgeInfo> getKnowledgeInfoListByUserIdByRelease(KnowledgeUserDto knowledgeUserDto) {
        Long userId = knowledgeUserDto.getUserId();
        Integer type = knowledgeUserDto.getType();

        PageHelper.startPage(knowledgeUserDto.getPageNum(), knowledgeUserDto.getPageSize());

        // 获取用户发布的类型为type的知识数据
        List<DtKnowledge> list = knowledgeInfoMapper.getKnowledgeListByUserIdByRelease(userId, type);

        return packPageInfo(list);
    }

    // 根据用户id获去对应赞赏过的知识信息集合
    @Override
    public PageInfo<KnowledgeInfo> getKnowledgeInfoListByUserIdByAppreciate(KnowledgeUserDto knowledgeUserDto) {
        Long userId = knowledgeUserDto.getUserId();
        Integer type = knowledgeUserDto.getType();

        PageHelper.startPage(knowledgeUserDto.getPageNum(), knowledgeUserDto.getPageSize());

        List<DtKnowledge> list = knowledgeInfoMapper.getKnowledgeListByUserIdByAppreciate(userId, type);

        return packPageInfo(list);
    }

    // 根据用户id获取关注/收藏的知识信息集合
    @Override
    public PageInfo<KnowledgeInfo> getKnowledgeInfoListByUserIdByFollow(KnowledgeUserDto knowledgeUserDto) {
        Long userId = knowledgeUserDto.getUserId();
        Integer type = knowledgeUserDto.getType();

        PageHelper.startPage(knowledgeUserDto.getPageNum(), knowledgeUserDto.getPageSize());

        List<DtKnowledge> list = knowledgeInfoMapper.getKnowledgeInfoListByUserIdByFollow(userId, type);

        return packPageInfo(list);
    }

    // 撤贴
    @Override
    public void cancelKnowledge(Long knowledgeId) {
        knowledgeInfoMapper.cancelKnowledge(knowledgeId);
    }

    // 按关注数（收藏）获取知识信息
    private PageInfo<KnowledgeInfo> getKnowledgeInfoListForFollow(KnowledgeSearchDto knowledgeSearchDto) {
        String input = knowledgeSearchDto.getInput();
        Integer type = knowledgeSearchDto.getType();
        List<TagBaseInfo> tagBaseInfoList = knowledgeSearchDto.getTagBaseInfoList();
        // 提取id集合
        List<Long> tagIds = tagBaseInfoList.stream().map(TagBaseInfo::getId).collect(Collectors.toList());
        int size = tagIds.size();

        PageHelper.startPage(knowledgeSearchDto.getPageNum(), knowledgeSearchDto.getPageSize());

        List<DtKnowledge> knowledgeList = knowledgeInfoMapper.getKnowledgeInfoListForFollow(input, type, tagIds, size);

        return packPageInfo(knowledgeList);
    }

    // 按赞赏数（质量）获取知识信息
    private PageInfo<KnowledgeInfo> getKnowledgeInfoListForQuality(KnowledgeSearchDto knowledgeSearchDto) {
        String input = knowledgeSearchDto.getInput();
        Integer type = knowledgeSearchDto.getType();
        List<TagBaseInfo> tagBaseInfoList = knowledgeSearchDto.getTagBaseInfoList();
        // 提取id集合
        List<Long> tagIds = tagBaseInfoList.stream().map(TagBaseInfo::getId).collect(Collectors.toList());
        int size = tagIds.size();

        PageHelper.startPage(knowledgeSearchDto.getPageNum(), knowledgeSearchDto.getPageSize());

        List<DtKnowledge> knowledgeList = knowledgeInfoMapper.getKnowledgeInfoListForQuality(input, type, tagIds, size);

        return packPageInfo(knowledgeList);
    }

    // 按阅读数(热门的)获取知识信息
    private PageInfo<KnowledgeInfo> getKnowledgeInfoListForPopular(KnowledgeSearchDto knowledgeSearchDto) {
        String input = knowledgeSearchDto.getInput();
        Integer type = knowledgeSearchDto.getType();
        List<TagBaseInfo> tagBaseInfoList = knowledgeSearchDto.getTagBaseInfoList();
        // 提取id集合
        List<Long> tagIds = tagBaseInfoList.stream().map(TagBaseInfo::getId).collect(Collectors.toList());
        int size = tagIds.size();

        PageHelper.startPage(knowledgeSearchDto.getPageNum(), knowledgeSearchDto.getPageSize());

        List<DtKnowledge> knowledgeList = knowledgeInfoMapper.getKnowledgeInfoListForPopular(input, type, tagIds, size);

        return packPageInfo(knowledgeList);
    }

    // 按照最近时间获取知识信息
    private PageInfo<KnowledgeInfo> getKnowledgeInfoListForLatest(KnowledgeSearchDto knowledgeSearchDto) {
        String input = knowledgeSearchDto.getInput();
        Integer type = knowledgeSearchDto.getType();
        List<TagBaseInfo> tagBaseInfoList = knowledgeSearchDto.getTagBaseInfoList();
        // 提取id集合
        List<Long> tagIds = tagBaseInfoList.stream().map(TagBaseInfo::getId).collect(Collectors.toList());
        int size = tagIds.size();

        PageHelper.startPage(knowledgeSearchDto.getPageNum(), knowledgeSearchDto.getPageSize());

        List<DtKnowledge> knowledgeList = knowledgeInfoMapper.getKnowledgeInfoListForLatest(input, type, tagIds, size);

        return packPageInfo(knowledgeList);
    }

    private PageInfo<KnowledgeInfo> packPageInfo(List<DtKnowledge> knowledgeList) {
        // 获取当前用户信息
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        // 创建返回的集合
        List<KnowledgeInfo> list = new ArrayList<>();
        for (DtKnowledge knowledge : knowledgeList) {
            System.out.println("post的id为：" + knowledge.getId());

            // 创建KnowledgeInfo对象
            KnowledgeInfo knowledgeVo = new KnowledgeInfo();
            BeanUtils.copyProperties(knowledge, knowledgeVo);
            // 远程调用用户服务方法获取用户名
            DtUser dtUser = userFeignClient.getUserById(knowledge.getUserId());
            knowledgeVo.setUserName(dtUser.getUserName());
            knowledgeVo.setHeadImgUrl(dtUser.getHeadImgUrl());
            // 获取问答贴对应标签号集合
            List<Long> tagIds = knowledgeTagMapper.getTagIdsByKnowledgeId(knowledge.getId());
            // 远程调用标签服务方法获取标签基本信息对象
            List<TagBaseInfo> tagBaseInfoList = tagFeignClient.getTagBaseInfoListByIds(tagIds);
            knowledgeVo.setTagBaseInfoList(tagBaseInfoList);

            KnowledgeRecordsInfo knowledgeRecordsInfo = new KnowledgeRecordsInfo();
            // 获取该知识的阅读数
            Long readCount = knowledgeRecordsMapper.getReadCount(knowledge.getId());
            knowledgeRecordsInfo.setReadCount((readCount == null ? 0L : readCount));
            // 远程调用答复评论服务获取该知识的答复数（parentId = 0）
            Long responseCount = commentFeignClient.getResponseCountByKnowledgeId(knowledge.getId());
            knowledgeRecordsInfo.setResponseCount(responseCount == null ? 0L : responseCount);
            if (userInfo != null) {
                // 判断当前用户是否有赞赏该知识信息
                int appreciateCount = knowledgeAppreciateMapper.findByUserIdAndKnowledgeId(userInfo.getId(), knowledge.getId());
                knowledgeRecordsInfo.setIsAppreciate(appreciateCount > 0);
                // 判断当前用户是否有关注该知识信息
                int followCount = knowledgeFollowMapper.findByUserIdAndKnowledgeId(userInfo.getId(), knowledge.getId());
                knowledgeRecordsInfo.setIsFollow(followCount > 0);
            }
            knowledgeVo.setKnowledgeRecordsInfo(knowledgeRecordsInfo);

            list.add(knowledgeVo);
        }

        PageInfo<DtKnowledge> postPageInfo = new PageInfo<>(knowledgeList);

        return PageInfoUtils.copyListPage(postPageInfo, list);
    }
}
