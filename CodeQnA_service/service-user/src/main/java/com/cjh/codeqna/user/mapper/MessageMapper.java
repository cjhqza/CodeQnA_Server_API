package com.cjh.codeqna.user.mapper;

import com.cjh.codeqna.model.dto.message.MessageDto;
import com.cjh.codeqna.model.entity.data.DtMessage;
import com.cjh.codeqna.model.vo.chat.ChatInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 消息服务映射文件
 * @Create: 2025-05-13 11:34
 */
@Mapper
public interface MessageMapper {

    // 创建会话
    void createChat(MessageDto messageDto);

    // 更新会话信息
    void updateChat(Long id, String content, String createTime);

    // 新增消息
    void storeMessage(MessageDto messageDto);

    // 获取会话信息
    List<ChatInfo> getChatInfo(Long id);

    // 根据会话id获取消息集合
    List<DtMessage> getMessageList(Long id);
}
