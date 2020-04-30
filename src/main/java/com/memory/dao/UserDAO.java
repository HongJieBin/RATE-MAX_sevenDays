package com.memory.dao;

import com.memory.pojo.User;

import java.util.List;

/**
 * @InterfaceName UserDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/21
 * @Version 1.0
 **/
public interface UserDAO {
    void add(User user);    //添加用户
    void update(User user); //更新用户信息
    void delete(User user); //删除用户（注销)
    User get(int id);       //根据用户ID返回用户类
    List<User> get(String param, String value);  //根据用户昵称返回用户类
}
