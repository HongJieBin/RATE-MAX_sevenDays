package com.memory.service;

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
    public void addChatroom(Chatroom chatroom);
    public boolean deleteChatroomById(int chatroomId);
    public List<ChatroomInfoVo> getChatroomInfoList();
}
