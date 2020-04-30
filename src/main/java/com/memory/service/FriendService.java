package com.memory.service;

import com.memory.dao.FriendDAO;
import com.memory.pojo.Friend;
import com.memory.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface FriendService {


    /**
     * @Description: 查询好友列表
     */
    public List<User> queryFriendsList(int userId);
    public boolean isExitUser(int userId);
    public void saveFriends(Integer myUserId, Integer friendUserId);
    public void deleteFriend(Integer myUserId, Integer friendUserId);
}
