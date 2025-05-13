package com.cjh.codeqna.user.service.impl;

import com.cjh.codeqna.model.dto.message.MessageDto;
import com.cjh.codeqna.model.entity.data.DtMessage;
import com.cjh.codeqna.model.vo.chat.ChatInfo;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.user.mapper.MessageMapper;
import com.cjh.codeqna.user.service.MessageService;
import com.cjh.codeqna.util.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cjh
 * @Description: x消息服务接口实现类
 * @Create: 2025-04-13 11:33
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    // 创建会话
    @Override
    public Long createChat(MessageDto messageDto) {
        messageMapper.createChat(messageDto);
        return messageDto.getId();
    }

    // 存储消息
    @Override
    public void storeMessage(MessageDto messageDto) {
        // 更新会话信息
        messageMapper.updateChat(messageDto.getId(), messageDto.getContent(), messageDto.getCreateTime());
        // 新增消息
        messageMapper.storeMessage(messageDto);
    }

    // 获取会话信息
    @Override
    public List<ChatInfo> getChatInfo() {
        // 获取用户id
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        if (userInfo == null) {
            return null;
        }
        return messageMapper.getChatInfo(userInfo.getId());
    }

    // 根据会话id获取消息集合
    @Override
    public List<DtMessage> getMessageList(Long id) {
        return messageMapper.getMessageList(id);
    }
}
