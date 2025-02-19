package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.entity.data.DtMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 数据聊天信息接口映射文件
 * @Create: 2025-02-19 21:03
 */
@Mapper
public interface DtMessageMapper {
    // 会话消息记录
    List<DtMessage> getMessageByChatId(Long chatId);
}
