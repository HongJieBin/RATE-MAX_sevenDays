package com.memory.netty;


import com.alibaba.druid.support.json.JSONUtils;
import com.memory.dao.MsgDAO;
import com.memory.pojo.ChatMsg;
import com.memory.pojo.Msg;
import com.memory.service.ChatMsgService;
import com.memory.service.MsgService;
import com.memory.utils.JsonUtils;
import com.memory.utils.SpringUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ChatServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    public  static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String content = textWebSocketFrame.text();
        System.out.println(content);
        Channel currentChannel = ctx.channel();

        // 获取发送的消息
        DataContent dataContent = JsonUtils.toBean(content, DataContent.class);
        Integer action = dataContent.getAction();

        if (action==MsgActionEnum.CONNECT.type) {
            // 建立连接的时候加入到channelRel
            com.memory.netty.Msg msg = dataContent.getMsg();
            // 获取发送人的id,和channel
            Integer senderId = msg.getSenderId();
            UserChannelRelation.add(senderId, currentChannel);
        }
        else if (action==MsgActionEnum.CHAT.type) {
            // 如果是聊天消息
            com.memory.netty.Msg msg = dataContent.getMsg();
            String msgContent = msg.getContent();
            int senderId = msg.getSenderId();
            int receiverId = msg.getReceiverId();
            System.out.println("dataContent为:" + dataContent);
            // 保存消息到数据库,
            MsgService msgService = (MsgService) SpringUtils.getBean("msgServiceImpl");
            Integer msgId = msgService.save(msg);
            msg.setMsgId(msgId);
            System.out.println("保存的msgId为:" + msgId);
            // 创建需要转发的dataContent
            DataContent dataContent1 = new DataContent(null, msg, null);
            // 发送消息
            Channel reveicerChannel = UserChannelRelation.get(receiverId);
            if (reveicerChannel == null) {
                // 推送
            }
            else {
                Channel findChannel = channels.find(reveicerChannel.id());
                if (findChannel!=null) {
                    reveicerChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.toJSON(dataContent1)));
                }
                else {
                    // 用户离线,推送
                    System.out.println("用户处于离线状态");
                }
            }
        }

        else if(action==MsgActionEnum.CHATROOM.type){
            com.memory.netty.Msg msg = dataContent.getMsg();
            String msgContent = msg.getContent();
            int senderId = msg.getSenderId();
            int chatRoomId = msg.getChatRoomId();
            System.out.println("dataContent为:" + dataContent);
            // 保存消息到数据库,
            ChatMsgService chatmsgService = (ChatMsgService) SpringUtils.getBean("chatmsgServiceImpl");
            //relation<int,int>  前一个存得是用户id,后一个存得是消息id
            Map<Integer,Integer> relation = chatmsgService.save(msg.getChatRoomId(),msg.getChatRoomId(),msg.getContent());
            for (Map.Entry<Integer,Integer> entry : relation.entrySet()) {
                msg.setReceiverId(entry.getKey());
                msg.setMsgId(entry.getValue());
                DataContent dataContent1 = new DataContent(null, msg, null);
                // 发送消息
                Channel reveicerChannel = UserChannelRelation.get(entry.getValue());
                if (reveicerChannel == null) {
                    // 推送
                }
                else {
                    Channel findChannel = channels.find(reveicerChannel.id());
                    if (findChannel!=null) {
                        reveicerChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.toJSON(dataContent1)));
                    }
                    else {
                        // 用户离线,推送
                        System.out.println("用户处于离线状态");
                    }
                }
            }

        }
        else if (action == MsgActionEnum.SIGNED.type) {
            // 消息签收
            String extand = dataContent.getExtend();
            // 分割需要签收消息的id
            String[] msgIdStr = extand.split(",");
            System.out.println("dataContent为:" + dataContent);
            List<Integer> msgList = new ArrayList<>();
            for (String mid : msgIdStr) {
                if ( StringUtils.isNoneBlank(mid)) {
                    int i = Integer.parseInt(mid);
                    msgList.add(i);
                }
            }

            System.out.println(msgList.toString());
            // 更新需要签收消息的msg_action
            if (msgList!=null && !msgList.isEmpty() && msgList.size()>0) {
                MsgService msgService = (MsgService) SpringUtils.getBean("msgServiceImpl");
                msgService.updateMsgSigned(msgList);

            }
        }
        else if (action==MsgActionEnum.KEEPALIVE.type) {
            System.out.println("收到来自为[" + currentChannel + "]的心跳包");
        }

    }
    //每当从服务端收到新的客户端连接时，客户端的 Channel 存入ChannelGroup列表中  并通知列表中的其他客户端 Channel
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel in = ctx.channel();
        System.out.println("CLient:"+in.remoteAddress()+"进入");
        channels.add(ctx.channel());
    }
    //每当从服务端收到客户端断开时，客户端的 Channel 移除 ChannelGroup 列表中,并通知列表中的其他客户端 Channel
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Channel in = ctx.channel();
        System.out.println("CLient:"+in.remoteAddress()+"离开");
        channels.remove(ctx.channel());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel in = ctx.channel();
        System.out.println("handler:"+in.remoteAddress()+"加入");
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel in = ctx.channel();
        System.out.println("handler:"+in.remoteAddress()+"离开");
    }


    //服务端监听到客户端活动
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"在线");
    }

    //服务端监听到客户端不活动
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"掉线");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        incoming.close();
        channels.remove(incoming);
    }
}
