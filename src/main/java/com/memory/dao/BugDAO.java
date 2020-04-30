package com.memory.dao;

import com.memory.pojo.Bug;

/**
 * @InterfaceName BugDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface BugDAO {
    void add(Bug bug);
    void update(Bug bug);
    void delete(Bug bug);
    Bug get(int id);
}
