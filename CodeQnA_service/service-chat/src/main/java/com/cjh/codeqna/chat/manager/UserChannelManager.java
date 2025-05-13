package com.cjh.codeqna.chat.manager;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: cjh
 * @Description: 用户通道管理器
 * @Create: 2025-04-13 14:00
 */
@Component
public class UserChannelManager {
    // 使用 ConcurrentHashMap 保证线程安全
    private static final ConcurrentHashMap<Long, Channel> userChannelMap = new ConcurrentHashMap<>();

    // 绑定用户id和channel
    public void bind(Long userId, Channel channel) {
        userChannelMap.put(userId, channel);
    }

    // 获取用户Channel
    public Channel getChannel(Long userId) {
        return userChannelMap.get(userId);
    }

    // 解绑用户
    public void unbind(Channel channel) {
        userChannelMap.entrySet().removeIf(entry -> entry.getValue() == channel);
    }
}
