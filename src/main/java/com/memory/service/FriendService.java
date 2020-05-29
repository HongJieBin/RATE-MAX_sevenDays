package com.memory.service;

import com.memory.controller.VO.*;
import com.memory.dao.FriendDAO;
import com.memory.pojo.Friend;
import com.memory.pojo.User;
import com.memory.pojo.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface FriendService {


    /**
     * @Description: 查询好友列表
     */
    public List<User> queryFriendsList(int userId);
    public List<FriendInfoVO> getFriendsList(int userId);
    public boolean isExitUser(int userId);
    public void saveFriends(Integer myUserId, Integer friendUserId);
    public void deleteFriend(Integer myUserId, Integer friendUserId);
    public List<RecommendFriendInfoVO> recommendFriends(Integer myUserId);
    public int getRandomId(int myUserId);
    public List<User> queryBlackList(int userId);
    public boolean isExitFriend(int userId,int friendId);
    public Friend saveLevel(int userId,int friendId);
    public Friend addRemark(int userId,int friendId,String remark);
    public TrustInfoVO getTrustedInfo(int userId,int friendId);
    public UntrustInfoVO getUntrustedInfo(int userId, int friendId);
    public Object getInformation(int userId,int friendId);
    public TagSortVO TagSort(Integer userId);
    public RecommendFriendInfoVO addByUserID(Integer userID);
    public boolean isMatching(Integer userId,Integer friendId);
}
