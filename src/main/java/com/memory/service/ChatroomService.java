package com.memory.service;

import com.memory.controller.VO.ChatRoomVO;
import com.memory.pojo.Chatroom;

import java.util.List;

public interface ChatroomService {

    public String searchById(int chatroomId);
    public String searchByTag(String chatroomTag);
    public String searchByName(String chatroomName);
    public void addChatRoom(int userid,int chatroomId);
    public boolean isExistChatRoom(int chatroomId);
    public boolean isInChatRoom(int userId, int chatroomId);
    public List<ChatRoomVO> recommendChatroom(int userId);
    public ChatRoomVO addByChatroom(Chatroom chatroom);
}
