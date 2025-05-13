package com.cjh.codeqna.chat.handler;

import com.cjh.codeqna.chat.manager.UserChannelManager;
import com.cjh.codeqna.chat.service.MessageService;
import com.cjh.codeqna.feign.user.UserFeignClient;
import com.cjh.codeqna.model.dto.message.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: cjh
 * @Description: WebSocket消息处理器
 * @Create: 2025-04-13 13:44
 */
@Component
@ChannelHandler.Sharable
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ObjectMapper objectMapper = new ObjectMapper();  // 添加JSON处理器

    private final UserChannelManager userChannelManager;

    private final MessageService messageService;

    public TextWebSocketFrameHandler(UserChannelManager userChannelManager, MessageService messageService) {
        this.userChannelManager = userChannelManager;
        this.messageService = messageService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String request = msg.text();

        try {
            // 解析 JSON 请求
            JsonNode jsonNode = new ObjectMapper().readTree(request);

            // 处理不同类型的消息
            if (jsonNode.has("type")) {
                String type = jsonNode.get("type").asText();
                switch (type) {
                    case "auth":    // 认证请求
                        handleAuth(ctx.channel(), jsonNode);
                        break;
                    case "message": // 消息请求
                        handleMessage(ctx.channel(), jsonNode);
                        break;
                }
            }
        } catch (JsonProcessingException e) {
            System.out.println("第一层服务异常");
            throw new RuntimeException(e);
        }
    }

    // 处理聊天消息
    private void handleMessage(Channel channel, JsonNode jsonNode) {
        ((ObjectNode) jsonNode).remove("type");


        MessageDto messageDto = null;
        try {
            messageDto = convertToMessage(jsonNode.get("data"));
        } catch (JsonProcessingException e) {
            System.out.println("第二层服务消息处理异常");
            throw new RuntimeException(e);
        }
        Long receiverId = messageDto.getReceiverId();
        // 查找目标用户的 Channel
        Channel receiverChannel = userChannelManager.getChannel(receiverId);
        if (receiverChannel != null && receiverChannel.isActive()) {
            // 将消息转发给目标用户
            receiverChannel.writeAndFlush(new TextWebSocketFrame("{\"type\":\"message\",\"data\":" + jsonNode.toString() + "}"));
        } else {
            System.out.println("对方不在线");
        }

        messageService.storeMessage(messageDto);
    }

    private MessageDto convertToMessage(JsonNode jsonNode) throws JsonProcessingException {
        return objectMapper.treeToValue(jsonNode, MessageDto.class);
    }

    // 处理用户认证
    private void handleAuth(Channel channel, JsonNode jsonNode) {
        String token = jsonNode.get("token").asText();

        // 远程调用用户服务验证token
        // Long userId = messageService.valiateToken(token);
        Long userId = messageService.valiateToken(token);

        if (userId != null) {
            // 绑定用户 id 和 channel
            userChannelManager.bind(userId, channel);
            // 发送认证成功响应
            channel.writeAndFlush(new TextWebSocketFrame("{\"type\":\"AUTH_SUCCESS\",\"userId\":" + userId + "}"));
            System.out.println(userId + " 认证成功");
        } else {
            System.out.println("认证失败");
            // 认证失败关闭连接
            channel.close();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 用户断开连接，从 userChannelManager 中移除
        userChannelManager.unbind(ctx.channel());
        super.channelInactive(ctx);
    }
}
