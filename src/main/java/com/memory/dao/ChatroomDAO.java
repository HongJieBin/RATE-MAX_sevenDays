package com.memory.dao;

import com.memory.pojo.Bug;
import com.memory.pojo.Chatroom;

import java.util.List;

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
    List<Chatroom> getAll();
    int getCount(); //获取表中数据个数
    int getOpenCount(); //获取表中开放聊天室个数
}
