package com.cjh.codeqna.chat.config;

import com.cjh.codeqna.chat.handler.TextWebSocketFrameHandler;
import com.cjh.codeqna.chat.manager.UserChannelManager;
import com.cjh.codeqna.chat.service.MessageService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cjh
 * @Description: Netty配置
 * @Create: 2025-04-13 12:23
 */
@Configuration
public class NettyConfig {
    @Value("${netty.port}")
    private int port;

    @Bean
    public ServerBootstrap serverBootstrap(ChannelGroup channelGroup, UserChannelManager userChannelManager, MessageService messageService) {
        return new ServerBootstrap()
                .group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new HttpServerCodec()) // HTTP编解码器
                                .addLast(new HttpObjectAggregator(65535)) // HTTP消息聚合器
                                .addLast(new WebSocketServerProtocolHandler("/ws")) // WebSocket协议处理器
                                .addLast(new TextWebSocketFrameHandler(userChannelManager, messageService));
                    }
                });

    }

    // 启动Netty服务
    @Bean
    public ApplicationRunner nettyRunner(ServerBootstrap serverBootstrap) {
        return args -> {
            System.out.println("Netty服务启动");
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        };
    }

    // 线程池配置
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    // 新增ChannelGroup Bean
    @Bean(destroyMethod = "close")
    public ChannelGroup channelGroup() {
        // 使用全局事件执行器，保证线程安全
        return new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }
}
