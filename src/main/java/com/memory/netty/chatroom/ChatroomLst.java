package com.memory.netty.chatroom;

import com.memory.pojo.Chatroom;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理所有chatroom和其对应id的关系的容器
 */
public class ChatroomLst {
    private static ConcurrentHashMap<Integer, Chatroom> relation = new ConcurrentHashMap<>();

    public static void put(Integer chatroomId, Chatroom chatroom) {
        relation.put(chatroomId, chatroom);
    }

    public static Chatroom get(Integer chatroomId) {
        return relation.get(chatroomId);
    }

    public static boolean hasChatroom(Integer chatroomId) {
        return relation.containsKey(chatroomId);
    }
}
