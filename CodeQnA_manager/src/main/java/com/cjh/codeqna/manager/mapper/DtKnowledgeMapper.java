package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.data.DtKnowledgeDto;
import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.vo.data.DtKnowledgeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识管理服务映射文件
 * @Create: 2025-01-25 23:26
 */
@Mapper
public interface DtKnowledgeMapper {
    // 根据标题和userIds查找DtKnowledge
    List<DtKnowledge> findByTitleAndUserIds(String title, List<Long> userIds);

    // 分页查询
    List<DtKnowledgeVo> findByPage(DtKnowledgeDto dtKnowledgeDto);

    // 修改知识状态
    void edit(Long id);

    // 获取待审批的知识列表
    List<DtKnowledgeVo> findByPageByOrder();

    // 审批后的知识状态
    void process(Long id, Integer status);

    // 知识类举报信息
    DtKnowledgeVo findById(Long targetId);

    // 根据创作者id找到对应用户名
    String findUserNameById(Long targetId);
}
