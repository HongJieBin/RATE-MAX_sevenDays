package com.memory.dao;

import com.memory.pojo.Chatroom;
import com.memory.pojo.ChatroomTag;

/**
 * @InterfaceName ChatroomTagDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface ChatroomTagDAO {
    void add(ChatroomTag chatroomTag);
    void update(ChatroomTag chatroomTag);
    void delete(ChatroomTag chatroomTag);
    ChatroomTag get(int id);
}
