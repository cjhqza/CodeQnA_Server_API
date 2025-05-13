package com.cjh.codeqna.knowledge.service.impl;

import com.cjh.codeqna.feign.tag.TagFeignClient;
import com.cjh.codeqna.knowledge.mapper.CreateMapper;
import com.cjh.codeqna.knowledge.mapper.KnowledgeTagMapper;
import com.cjh.codeqna.knowledge.service.CreateService;
import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeDraftInfo;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.cjh.codeqna.util.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: cjh
 * @Description: 知识创作服务接口实现类
 * @Create: 2025-04-11 9:51
 */
@Service
public class CreateServiceImpl implements CreateService {
    @Autowired
    private CreateMapper createMapper;
    @Autowired
    private KnowledgeTagMapper knowledgeTagMapper;
    @Autowired
    private TagFeignClient tagFeignClient;

    // 根据用户id和知识类型获取草稿内容
    @Override
    public List<KnowledgeDraftInfo> getDraftKnowledge(Integer type) {
        // 获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<DtKnowledge> dtKnowledgeList = createMapper.getDraftKnowledge(userId, type);

        List<KnowledgeDraftInfo> list = new ArrayList<>();

        for (DtKnowledge dtKnowledge : dtKnowledgeList) {
            KnowledgeDraftInfo knowledgeDraftInfo = new KnowledgeDraftInfo();
            knowledgeDraftInfo.setDtKnowledge(dtKnowledge);
            // 获取问答贴对应标签号集合
            List<Long> tagIds = knowledgeTagMapper.getTagIdsByKnowledgeId(dtKnowledge.getId());
            // 远程调用标签服务方法获取标签基本信息对象
            List<TagBaseInfo> tagBaseInfoList = tagFeignClient.getTagBaseInfoListByIds(tagIds);
            knowledgeDraftInfo.setTagBaseInfoList(tagBaseInfoList);
            list.add(knowledgeDraftInfo);
        }

        return list;
    }

    // 保存已有的草稿
    @Override
    public void saveDraftKnowledge(KnowledgeDraftInfo knowledgeDraftInfo) {
        DtKnowledge dtKnowledge = knowledgeDraftInfo.getDtKnowledge();
        List<TagBaseInfo> tagBaseInfoList = knowledgeDraftInfo.getTagBaseInfoList();
        List<Long> tagIds = tagBaseInfoList.stream().map(TagBaseInfo::getId).collect(Collectors.toList());
        // 保存已有的知识数据
        createMapper.saveDtKnowledge(dtKnowledge);
        // 删除已有对应知识id的数据
        knowledgeTagMapper.deleteByKnowledgeId(dtKnowledge.getId());
        // 插入新的知识标签数据
        knowledgeTagMapper.saveKnowledgeTag(dtKnowledge.getId(), tagIds);
    }

    // 新建草稿
    @Override
    public Long addDraftKnowledge(KnowledgeDraftInfo knowledgeDraftInfo) {
        DtKnowledge dtKnowledge = knowledgeDraftInfo.getDtKnowledge();
        List<TagBaseInfo> tagBaseInfoList = knowledgeDraftInfo.getTagBaseInfoList();
        List<Long> tagIds = tagBaseInfoList.stream().map(TagBaseInfo::getId).collect(Collectors.toList());
        dtKnowledge.setUserId(AuthContextUtil.getUserInfo().getId());
        // 新建知识数据
        createMapper.addDtKnowledge(dtKnowledge);
        System.out.println(dtKnowledge.getId());
        // 插入新的知识标签数据
        knowledgeTagMapper.saveKnowledgeTag(dtKnowledge.getId(), tagIds);

        return dtKnowledge.getId();
    }

    // 发布问答
    @Override
    public void publishKnowledge(KnowledgeDraftInfo knowledgeDraftInfo) {
        DtKnowledge dtKnowledge = knowledgeDraftInfo.getDtKnowledge();
        // 判断是否是已存在的草稿
        if (dtKnowledge.getId() == -1) {
            // 不是则需要新建草稿
            addDraftKnowledge(knowledgeDraftInfo);
        }
        System.out.println(dtKnowledge.getId());
        // 再进行发布(将status状态转换为0)
        createMapper.publishKnowledge(dtKnowledge.getId());
    }
}
