package com.memory.dao;

import com.memory.pojo.Admin;
import com.memory.pojo.ChatMsg;
import com.memory.pojo.Msg;

public interface ChatmsgDAO {
    void add(ChatMsg chatMsg);
    void update(ChatMsg chatMsg);
    void delete(ChatMsg chatMsg);
    ChatMsg get(int id);
    Integer save(ChatMsg chatMsg);
}
