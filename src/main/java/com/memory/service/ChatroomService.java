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

import com.memory.controller.VO.ChatroomInfoVo;
import com.memory.pojo.Chatroom;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @InterfaceName ChatroomService
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/26
 * @Version 1.0
 **/
public interface ChatroomService {
    public Chatroom addChatroom(Chatroom chatroom);
    public boolean deleteChatroomById(int chatroomId, int userId);
    public List<ChatroomInfoVo> getMyCreatChatroomInfoList(int userId);
    public ChatroomInfoVo getChatroomInfoById(int chatroomId);
    public List<ChatroomInfoVo> getMyJoinChatrommList(int userId);
    public Chatroom updateChatroom(Chatroom chatroom);

}
