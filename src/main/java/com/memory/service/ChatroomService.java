package com.memory.service;


import com.memory.controller.VO.ChatRoomVO;
import com.memory.controller.VO.ChatroomInfoVo;
import com.memory.pojo.Chatroom;
import com.memory.pojo.ChatroomTag;

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



    public Chatroom addChatroom(Chatroom chatroom);
    public boolean deleteChatroomById(int chatroomId, int userId);
    public List<ChatroomInfoVo> getMyCreatChatroomInfoList(int userId);
    public ChatroomInfoVo getChatroomInfoById(int chatroomId);
    public List<ChatroomInfoVo> getMyJoinChatroomList(int userId);
    public Chatroom updateChatroom(Chatroom chatroom);
    public List<ChatroomInfoVo> getBeforeChatroomList(int userId);
    public List<ChatroomTag> findById(int ChatroomId);

    void closeChatRoom(int chatRoomId) throws Exception;
    void openChatRoom(int chatRoomId) throws Exception;
    Chatroom get(int chatRoomId);
    List<Chatroom> getAll();



}
