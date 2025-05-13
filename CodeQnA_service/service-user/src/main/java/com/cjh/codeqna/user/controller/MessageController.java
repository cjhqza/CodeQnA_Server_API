package com.cjh.codeqna.user.controller;

import com.cjh.codeqna.model.dto.message.MessageDto;
import com.cjh.codeqna.model.entity.data.DtMessage;
import com.cjh.codeqna.model.vo.chat.ChatInfo;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 消息控制器
 * @Create: 2025-04-13 11:31
 */
@RestController
@RequestMapping("/api/user/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    // 远程调用：创建会话
    @PostMapping(value = "/createChat")
    public Long createChat(@RequestBody MessageDto messageDto) {
        return messageService.createChat(messageDto);
    }

    // 远程调用：存储消息
    @PostMapping(value = "/storeMessage")
    public void storeMessage(@RequestBody MessageDto messageDto) {
        messageService.storeMessage(messageDto);
    }

    // 获取会话信息
    @GetMapping(value = "/getChatInfo")
    public Result getChatInfo() {
        List<ChatInfo> list = messageService.getChatInfo();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 根据会话id获取消息集合
    @GetMapping(value = "/getMessageList/{id}")
    public Result getMessageList(@PathVariable("id") Long id) {
        List<DtMessage> list = messageService.getMessageList(id);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
