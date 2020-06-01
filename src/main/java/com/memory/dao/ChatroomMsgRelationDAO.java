package com.memory.dao;

import com.memory.pojo.ChatMsg;
import com.memory.pojo.Chatroom;
import com.memory.pojo.ChatroomMsgRelation;

public interface ChatroomMsgRelationDAO {
    void add(ChatroomMsgRelation chatroomMsgRelation);
    void update(ChatroomMsgRelation chatroomMsgRelation);
    void delete(ChatroomMsgRelation chatroomMsgRelation);
    ChatroomMsgRelation get(int id);
    Integer save(ChatroomMsgRelation chatroomMsgRelation);
}
