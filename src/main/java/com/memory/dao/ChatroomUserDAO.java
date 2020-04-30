package com.memory.dao;

import com.memory.pojo.Chatroom;
import com.memory.pojo.ChatroomUser;

/**
 * @InterfaceName ChatroomUserDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface ChatroomUserDAO {
    void add(ChatroomUser chatroomUser);
    void update(ChatroomUser chatroomUser);
    void delete(ChatroomUser chatroomUser);
    ChatroomUser get(int id);
}
