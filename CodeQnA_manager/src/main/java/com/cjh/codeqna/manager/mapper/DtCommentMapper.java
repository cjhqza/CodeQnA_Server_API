package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.data.DtCommentDto;
import com.cjh.codeqna.model.vo.data.DtCommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 评论管理服务映射文件
 * @Create: 2025-02-17 17:21
 */
@Mapper
public interface DtCommentMapper {
    // 评论列表
    List<DtCommentVo> findByPage(DtCommentDto dtCommentDto);

    // 删评
    void delete(Long id);

    // 评论类举报信息
    DtCommentVo findById(Long targetId);

    // 根据评论者id找到对应用户名
    String findUserNameById(Long targetId);
}
