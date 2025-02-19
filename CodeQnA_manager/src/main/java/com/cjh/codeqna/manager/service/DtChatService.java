package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.entity.data.DtMessage;
import com.cjh.codeqna.model.vo.data.DtChatVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 聊天管理服务接口
 * @Create: 2025-02-19 16:20
 */
public interface DtChatService {
    // 会话列表
    PageInfo<DtChatVo> findByPage(Integer pageNum, Integer pageSize);

    // 会话消息记录
    List<DtMessage> getMessageByChatId(Long chatId);
}
