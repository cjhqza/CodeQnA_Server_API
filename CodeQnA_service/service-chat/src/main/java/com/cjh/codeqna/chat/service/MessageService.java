package com.cjh.codeqna.chat.service;

import com.cjh.codeqna.feign.user.UserFeignClient;
import com.cjh.codeqna.model.dto.message.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: cjh
 * @Description: 消息服务类
 * @Create: 2025-04-13 10:58
 */
@Component
public class MessageService {

    @Autowired
    private UserFeignClient userFeignClient;

    public Long valiateToken(String token) {
        return userFeignClient.validateToken(token);
    }

    public void storeMessage(MessageDto msg) {
        // 获取会话id
        Long chatId = msg.getId();
        if (chatId == null || chatId == 0) {
            // 如果没有，则创建会话并获取会话id
            chatId = userFeignClient.createChat(msg);
        }
        msg.setId(chatId);

        // 将消息存储到数据库中
        userFeignClient.storeMessage(msg);
    }
}
