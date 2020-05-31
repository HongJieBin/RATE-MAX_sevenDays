package com.memory.service;

import com.memory.pojo.Chatroom;

import java.util.List;

public interface ChatRoomService {
    void closeChatRoom(int chatRoomId) throws Exception;
    void openChatRoom(int chatRoomId) throws Exception;
    Chatroom get(int chatRoomId);
    List<Chatroom> getAll();
}
