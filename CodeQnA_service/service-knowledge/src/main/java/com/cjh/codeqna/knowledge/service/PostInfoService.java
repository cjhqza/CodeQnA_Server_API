package com.cjh.codeqna.knowledge.service;

import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import com.cjh.codeqna.model.vo.knowledge.KnowledgeInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 问答贴信息服务接口
 * @Create: 2025-04-07 11:20
 */
public interface PostInfoService {

    // 根据分类获取问答贴信息
    PageInfo<KnowledgeInfo> getPostInfoByPage(Integer pageNum, Integer pageSize, String category);

    // 获取用户可能感兴趣的问答贴信息（需要登录）
    PageInfo<KnowledgeInfo> getPostInfoByInterest(Integer pageNum, Integer pageSize);
}
