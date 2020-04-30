package com.memory.dao;

import com.memory.pojo.Admin;

/**
 * @InterfaceName AdminDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface AdminDAO {
    void add(Admin admin);
    void update(Admin admin);
    void delete(Admin admin);
    Admin get(int id);
}
