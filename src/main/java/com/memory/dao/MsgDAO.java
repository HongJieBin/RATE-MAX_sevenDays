package com.memory.dao;

import com.memory.pojo.Msg;
import com.memory.pojo.Tag;


/**
 * @InterfaceName TagDAO
 * @Description TODO
 * @Author Hkb
 * @Date 2020/4/23
 * @Version 1.0
 **/

public interface MsgDAO {
    void add(Msg msg);
    void update(Msg msg);
    void delete(Msg msg);
    Msg get(int id);
}
