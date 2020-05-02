package com.memory.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.web.HttpRequestHandler;

import java.nio.charset.Charset;


/**
 **初始化时增加处理类到channelPipeline中
 */
public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {


    /**
     ** 初始化channel和chatServer
     **为Server约束各种处理类
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //http解码器
        pipeline.addLast(new HttpServerCodec());
        // 对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        // 几乎在netty中的编程，都会使用到此hanler
        pipeline.addLast(new HttpObjectAggregator(64*1024));
        //大数据流
        pipeline.addLast(new ChunkedWriteHandler());

        //支持httpWebsocket websocket 服务器处理的协议，用于指定给客户端连接访问的路由 : /ws
        //本handler会帮你处理一些繁重的复杂的事 会帮你处理握手动作： handshaking（close, ping, pong） ping + pong = 心跳
        //pipeline.addLast(new HttpRequestHandler("/ws"));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自己的handler
        pipeline.addLast(new ChatServerHandler());

        pipeline.addLast(new StringEncoder(Charset.forName("UTF-8")));
        pipeline.addLast(new StringDecoder(Charset.forName("UTF-8")));

        /* 增加心跳支持 */
        pipeline.addLast(new IdleStateHandler(8, 10, 12));
        pipeline.addLast(new HeartBeatHandler());

    }
}
