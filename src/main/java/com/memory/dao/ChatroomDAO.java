package com.memory.dao;

import com.memory.pojo.Bug;
import com.memory.pojo.Chatroom;

/**
 * @InterfaceName ChatroomDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface ChatroomDAO {
    void add(Chatroom chatroom);
    void update(Chatroom chatroom);
    void delete(Chatroom chatroom);
    Chatroom get(int id);
}
