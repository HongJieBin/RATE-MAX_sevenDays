package com.memory.netty;

import org.springframework.stereotype.Component;

public enum MsgActionEnum {
    CONNECT(1, "第一次(重连)初始化连接"),
    CHAT(2, "聊天消息"),
    SIGNED(3, "消息签收"),
    KEEPALIVE(4, "客户端保持心跳"),
    PULL_FRIEND(5, "拉取好友"),
    CHATROOM(6,"聊天室消息"),
    USEROUT(7,"您因违规已被封禁"),
    CHATROOMOUT(8,"聊天室关闭退出"),
    MUTIUSER(9,"您已被异地登陆挤下线"),
    CHATROOMMSG_SIGNED(10,"聊天室消息签收");


    public final Integer type;
    public final String content;

    MsgActionEnum(Integer type, String content) {
        this.type = type;
        this.content = content;
    }
}
