package com.cjh.codeqna.knowledge.service.impl;

import com.cjh.codeqna.feign.comment.CommentFeignClient;
import com.cjh.codeqna.feign.tag.TagFeignClient;
import com.cjh.codeqna.feign.user.UserFeignClient;
import com.cjh.codeqna.knowledge.mapper.*;
import com.cjh.codeqna.knowledge.service.PostInfoService;
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

/**
 * @Author: cjh
 * @Description: 问答贴信息服务实现类
 * @Create: 2025-05-07 11:21
 */
@Service
public class PostInfoServiceImpl implements PostInfoService {
    @Autowired
    private PostInfoMapper postInfoMapper;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private KnowledgeTagMapper knowledgeTagMapper;
    @Autowired
    private TagFeignClient tagFeignClient;
    @Autowired
    private CommentFeignClient commentFeignClient;
    @Autowired
    private KnowledgeRecordsMapper knowledgeRecordsMapper;
    @Autowired
    private KnowledgeAppreciateMapper knowledgeAppreciateMapper;
    @Autowired
    private KnowledgeFollowMapper knowledgeFollowMapper;


    // 根据分类获取问答贴信息
    @Override
    public PageInfo<KnowledgeInfo> getPostInfoByPage(Integer pageNum, Integer pageSize, String category) {
        switch (category) {
            case "latest":
                return getPostInfoByLatest(pageNum, pageSize);
            case "popular":
                return getPostInfoByPopular(pageNum, pageSize);
            case "quality":
                return getPostInfoByQuality(pageNum, pageSize);
            case "follow":
                return getPostInfoByFollow(pageNum, pageSize);
            case "unsolved":
                return getPostInfoByUnsolved(pageNum, pageSize);
            default:
                return null;
        }
    }

    // 按最近的时间(最近的)获取问答贴信息
    private PageInfo<KnowledgeInfo> getPostInfoByLatest(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 按时间排序获取问答贴信息
        List<DtKnowledge> postList = postInfoMapper.getPostInfoByLatest();

        return packPageInfo(postList);
    }

    // 按阅读数(热门的)获取问答贴信息
    private PageInfo<KnowledgeInfo> getPostInfoByPopular(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 获取根据知识记录表按照阅读数排序的知识表中的问答贴集合
        List<DtKnowledge> postList = postInfoMapper.getPostInfoByPopular();

        return packPageInfo(postList);
    }

    // 按赞赏数(高质量的)获取问答贴信息
    private PageInfo<KnowledgeInfo> getPostInfoByQuality(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 获取根据知识记录表按照赞赏数排序的知识表中的问答贴集合
        List<DtKnowledge> postList = postInfoMapper.getPostInfoByQuality();

        return packPageInfo(postList);
    }

    // 按关注数(关注高的)获取问答贴信息
    private PageInfo<KnowledgeInfo> getPostInfoByFollow(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 获取根据知识记录表按照关注数排序的知识表中的问答贴集合
        List<DtKnowledge> postList = postInfoMapper.getPostInfoByFollow();

        return packPageInfo(postList);
    }

    // 获取未得到回答(待求解的)的问答贴信息
    private PageInfo<KnowledgeInfo> getPostInfoByUnsolved(Integer pageNum, Integer pageSize) {
        // 远程调用获取答复评论服务中已有答复评论数据对应的知识id集合（去重）
        List<Long> knowledgeIds = commentFeignClient.getKnowledgeIdOfComment();
        PageHelper.startPage(pageNum, pageSize);
        // 根据获取的知识id集合获取除集合内id外的其他知识数据集合
        List<DtKnowledge> postList = postInfoMapper.getPostInfoByUnsolved(knowledgeIds);

        return packPageInfo(postList);
    }

    // 获取用户可能感兴趣的问答贴信息（需要登录）
    public PageInfo<KnowledgeInfo> getPostInfoByInterest(Integer pageNum, Integer pageSize) {
        // 获取用户id
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        // 远程调用标签服务获取用户感兴趣标签集合
        List<Long> tagIds = tagFeignClient.getTagIdsByUserId(userInfo.getId());

        if (tagIds == null || tagIds.size() == 0) {
            return new PageInfo<>(new ArrayList<KnowledgeInfo>());
        }

        PageHelper.startPage(pageNum, pageSize);
        // 根据用户感兴趣标签集合获取问答贴集合
        List<DtKnowledge> postList = postInfoMapper.getPostInfoByInterest(tagIds);

        return packPageInfo(postList);
    }

    private PageInfo<KnowledgeInfo> packPageInfo(List<DtKnowledge> postList) {
        // 获取当前用户信息
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        // 创建返回的集合
        List<KnowledgeInfo> list = new ArrayList<>();
        for (DtKnowledge post : postList) {
            System.out.println("post的id为：" + post.getId());

            // 创建KnowledgeInfo对象
            KnowledgeInfo postVo = new KnowledgeInfo();
            BeanUtils.copyProperties(post, postVo);
            // 远程调用用户服务方法获取用户对象
            DtUser dtUser = userFeignClient.getUserById(post.getUserId());
            postVo.setUserName(dtUser.getUserName());
            postVo.setHeadImgUrl(dtUser.getHeadImgUrl());
            // 获取问答贴对应标签号集合
            List<Long> tagIds = knowledgeTagMapper.getTagIdsByKnowledgeId(post.getId());
            // 远程调用标签服务方法获取标签基本信息对象
            List<TagBaseInfo> tagBaseInfoList = tagFeignClient.getTagBaseInfoListByIds(tagIds);
            postVo.setTagBaseInfoList(tagBaseInfoList);

            KnowledgeRecordsInfo knowledgeRecordsInfo = new KnowledgeRecordsInfo();
            // 获取该知识的阅读数
            Long readCount = knowledgeRecordsMapper.getReadCount(post.getId());
            knowledgeRecordsInfo.setReadCount((readCount == null ? 0L : readCount));
            // 远程调用答复评论服务获取该知识的答复数（parentId = 0）
            Long responseCount = commentFeignClient.getResponseCountByKnowledgeId(post.getId());
            knowledgeRecordsInfo.setResponseCount(responseCount == null ? 0L : responseCount);
            if (userInfo != null) {
                // 判断当前用户是否有赞赏该知识信息
                int appreciateCount = knowledgeAppreciateMapper.findByUserIdAndKnowledgeId(userInfo.getId(), post.getId());
                knowledgeRecordsInfo.setIsAppreciate(appreciateCount > 0);
                // 判断当前用户是否有关注该知识信息
                int followCount = knowledgeFollowMapper.findByUserIdAndKnowledgeId(userInfo.getId(), post.getId());
                knowledgeRecordsInfo.setIsFollow(followCount > 0);
            }
            postVo.setKnowledgeRecordsInfo(knowledgeRecordsInfo);

            list.add(postVo);
        }

        PageInfo<DtKnowledge> postPageInfo = new PageInfo<>(postList);
        return PageInfoUtils.copyListPage(postPageInfo, list);
    }
}
