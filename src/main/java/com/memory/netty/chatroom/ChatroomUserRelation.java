package com.memory.netty.chatroom;

import com.memory.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用来存储聊天室和用户id关系的容器,每一个聊天室内id都对印了一个用户id列表,
 * 转发的时候就转发到对应的channel里面去
 */
public class ChatroomUserRelation {
    private static ConcurrentHashMap<Integer, List<Integer>> relation = new ConcurrentHashMap<>();

    /**
     * 由聊天室id得到用户id列表
     * @param chatroomId
     * @return
     */
    public static List<Integer> getUserList(Integer chatroomId) {
        return relation.get(chatroomId);
    }

    /**
     * 判断一个用户是否在一个聊天室中
     * @param chatroomId
     * @param userId
     * @return
     */
    public static boolean queryUserIsInChatroom(Integer chatroomId, Integer userId) {
        if (!relation.containsKey(chatroomId)) {
            return false;
        }
        else {
            List<Integer> userList = relation.get(chatroomId);
            if (!userList.contains(userId)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     */
    public static void addChatroom(Integer chatroomId) {
        if (!relation.containsKey(chatroomId)) {
            relation.put(chatroomId, new ArrayList<>());
        }
    }
    /**
     *
     * @param chatroomId
     * @param userId
     */
    public static void addUserInChatroom(Integer chatroomId, Integer userId) {
        if (relation.containsKey(chatroomId)) {
            List<Integer> userList = relation.get(chatroomId);
            if (!userList.contains(userId) && userList.size()<50) {
                userList.add(userId);
            }
            relation.put(chatroomId, userList);
        }
    }

    /**
     *
     * @return
     */
    public static void removeChatroom(Integer chatroomId) {
        if (relation.containsKey(chatroomId)) {
            relation.remove(chatroomId);
        }
    }

    /**
     * 根据聊天室id和用户id移除聊天室
     * @param chatroomId
     * @param userId
     */
    public void  removeUserFromChatroom(Integer chatroomId, Integer userId) {
        List<Integer> userList = null;
        if (relation.containsKey(chatroomId)) {
             userList = relation.get(chatroomId);
        }

        userList.remove(userId);
    }
}
