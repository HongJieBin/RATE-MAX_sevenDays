package com.memory.service;

import com.memory.dao.ChatroomDAO;
import com.memory.pojo.Chatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private static long ONEDAY = 24*60*60*1000;

    @Autowired
    private ChatroomDAO chatroomDAO;

    @Override
    public Chatroom get(int chatRoomId) {
        return chatroomDAO.get(chatRoomId);
    }

    @Override
    public List<Chatroom> getAll() {
        return chatroomDAO.getAll();
    }

    @Override
    public void closeChatRoom(int chatRoomId) throws Exception{
        Chatroom chatroom = chatroomDAO.get(chatRoomId);
        if(chatroom == null)
            throw new Exception("该聊天室不存在！");
        if(chatroom.getChatroomStatement() == 1)
            throw new Exception("该聊天室已关闭！");
        chatroom.setChatroomEnd(new Timestamp(new Date().getTime()));
        chatroom.setChatroomStatement(1);
        chatroomDAO.update(chatroom);
    }

    @Override
    public void openChatRoom(int chatRoomId) throws Exception{
        Chatroom chatroom= chatroomDAO.get(chatRoomId);
        if (chatroom == null)
            throw new Exception("该聊天室不存在！");
        if(chatroom.getChatroomStatement() == 0)
            throw new Exception("该聊天室已打开！");
        chatroom.setChatroomEnd(new Timestamp(new Date().getTime() + ONEDAY));
        chatroom.setChatroomStatement(0);
        chatroomDAO.update(chatroom);
    }
}
