package com.cjh.codeqna.knowledge.service.impl;

import com.cjh.codeqna.feign.comment.CommentFeignClient;
import com.cjh.codeqna.feign.tag.TagFeignClient;
import com.cjh.codeqna.feign.user.UserFeignClient;
import com.cjh.codeqna.knowledge.mapper.*;
import com.cjh.codeqna.knowledge.service.ArticleInfoService;
import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeInfo;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeRecordsInfo;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.util.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cjh
 * @Description: 文章信息服务接口实现类
 * @Create: 2025-04-09 16:16
 */
@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {
    @Autowired
    private ArticleInfoMapper articleInfoMapper;
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

    // 获取赞赏数高的文章信息的集合
    @Override
    public List<KnowledgeInfo> getAppreciateArticleInfoList() {
        List<DtKnowledge> articleList = articleInfoMapper.getAppreciateArticleInfoList();
        return packList(articleList);
    }

    // 获取用户关注标签相关的文章信息集合（需要登录）
    @Override
    public List<KnowledgeInfo> getRelatedArticleInfoList() {
        // 获取用户id
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        // 远程调用标签服务获取用户感兴趣标签集合
        List<Long> tagIds = tagFeignClient.getTagIdsByUserId(userInfo.getId());

        if (tagIds == null || tagIds.size() == 0) {
            return new ArrayList<KnowledgeInfo>();
        }

        // 根据用户感兴趣标签集合获取文章集合
        List<DtKnowledge> articleList = articleInfoMapper.getRelatedArticleInfoList(tagIds);

        return packList(articleList);
    }

    private List<KnowledgeInfo> packList(List<DtKnowledge> articleList) {
        // 创建返回的集合
        List<KnowledgeInfo> list = new ArrayList<>();
        for (DtKnowledge post : articleList) {
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
            // 获取当前用户信息
            UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
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

        return list;
    }
}
