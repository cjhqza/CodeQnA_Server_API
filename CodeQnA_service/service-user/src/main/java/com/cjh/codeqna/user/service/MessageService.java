package com.cjh.codeqna.user.service;

import com.cjh.codeqna.model.dto.message.MessageDto;
import com.cjh.codeqna.model.entity.data.DtMessage;
import com.cjh.codeqna.model.vo.chat.ChatInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 消息服务接口
 * @Create: 2025-04-13 11:33
 */
public interface MessageService {
    // 创建会话
    Long createChat(MessageDto messageDto);

    // 存储消息
    void storeMessage(MessageDto messageDto);

    // 获取会话信息
    List<ChatInfo> getChatInfo();

    // 根据会话id获取消息集合
    List<DtMessage> getMessageList(Long id);
}
