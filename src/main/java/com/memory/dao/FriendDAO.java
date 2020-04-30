package com.memory.dao;

import com.memory.pojo.Friend;

import java.util.List;

/**
 * @InterfaceName FriendDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface FriendDAO {
    void add(Friend friend);
    void update(Friend friend);
    void delete(Friend friend);
    List<Friend> getByUserID(int userId);
    List<Friend> getByAddedID(int addedId);
}
