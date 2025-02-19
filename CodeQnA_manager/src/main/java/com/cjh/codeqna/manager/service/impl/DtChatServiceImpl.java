package com.cjh.codeqna.manager.service.impl;

import com.cjh.codeqna.manager.mapper.DtChatMapper;
import com.cjh.codeqna.manager.mapper.DtMessageMapper;
import com.cjh.codeqna.manager.service.DtChatService;
import com.cjh.codeqna.model.entity.data.DtMessage;
import com.cjh.codeqna.model.vo.data.DtChatVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 聊天管理服务接口实现类
 * @Create: 2025-02-19 16:20
 */
@Service
public class DtChatServiceImpl implements DtChatService {
    @Autowired
    private DtChatMapper dtChatMapper;
    @Autowired
    private DtMessageMapper dtMessageMapper;

    // 会话列表
    @Override
    public PageInfo<DtChatVo> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DtChatVo> list = dtChatMapper.findByPage();
        return new PageInfo<>(list);
    }

    // 会话消息记录
    @Override
    public List<DtMessage> getMessageByChatId(Long chatId) {
        return dtMessageMapper.getMessageByChatId(chatId);
    }
}
