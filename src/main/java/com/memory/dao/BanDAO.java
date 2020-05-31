package com.memory.dao;

import com.memory.pojo.Ban;
import com.memory.pojo.User;

import java.util.List;

/**
 * @InterfaceName BanDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface BanDAO {
    void add(Ban ban);
    void update(Ban ban);
    void delete(Ban ban);
    Ban get(int id);
    List<Ban> get(String param, int value);  //查询Ban
}
