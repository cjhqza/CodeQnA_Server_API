package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.vo.data.DtChatVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 聊天管理服务映射文件
 * @Create: 2025-02-19 16:21
 */
@Mapper
public interface DtChatMapper {
    // 会话列表
    List<DtChatVo> findByPage();
}
