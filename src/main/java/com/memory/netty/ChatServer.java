package com.memory.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServer {
    private static class SingletionServer {
        static final ChatServer instance = new ChatServer();
    }

    public static ChatServer getInstance() {
        return SingletionServer.instance;
    }

    EventLoopGroup bossGroup = null;
    EventLoopGroup worker = null;
    ServerBootstrap serverBootstrap = null;
    ChannelFuture channelFuture = null;

    public ChatServer() {
        bossGroup = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChatServerInitializer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    public void start() {
        channelFuture = serverBootstrap.bind(7888);
        System.out.println("netty服务器启动了");
    }
}
