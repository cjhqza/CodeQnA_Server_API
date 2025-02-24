package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.common.log.annotation.Log;
import com.cjh.codeqna.common.log.enums.OperatorType;
import com.cjh.codeqna.manager.service.DtChatService;
import com.cjh.codeqna.model.entity.data.DtMessage;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.data.DtChatVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 聊天管理控制器
 * @Create: 2025-02-19 16:19
 */
@RestController
@RequestMapping(value = "/admin/data/dtChat")
public class DtChatController {
    @Autowired
    private DtChatService dtChatService;

    // 会话列表
    @Log(title = "聊天管理:列表", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<DtChatVo> list = dtChatService.findByPage(pageNum, pageSize);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 会话消息记录
    @Log(title = "聊天管理:会话消息", businessType = 0, operatorType = OperatorType.MANAGE)
    @PostMapping(value = "/getMessageByChatId/{chatId}")
    public Result getMessageByChatId(@PathVariable("chatId") Long chatId) {
        List<DtMessage> list = dtChatService.getMessageByChatId(chatId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
