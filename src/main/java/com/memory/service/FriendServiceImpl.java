package com.memory.service;

import com.memory.controller.VO.FriendInfoVO;
import com.memory.controller.VO.RecommendFriendInfoVO;
import com.memory.controller.VO.TrustInfoVO;
import com.memory.controller.VO.UntrustInfoVO;
import com.memory.dao.BlacklistDAO;
import com.memory.dao.FriendDAO;
import com.memory.dao.UserDAO;
import com.memory.pojo.Friend;
import com.memory.pojo.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{
    @Autowired
    private FriendDAO friendDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BlacklistDAO blacklistDAO;


    @Resource
    private HibernateTemplate hibernateTemplate;

    private TrustInfoVO trustInfoVO = new TrustInfoVO() ;
    private UntrustInfoVO untrustInfoVO = new UntrustInfoVO();


    /**
     * @Description: 获取所有好友列表得实体类信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> queryFriendsList(int userId) {
        String hql1 = "select addedId from Friend f where f.userId= ? ";
        System.out.println(hql1);
        List<Integer> add_id= (List<Integer>) hibernateTemplate.find(hql1,userId);
        System.out.println(add_id);
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql3 = "from User as user where user.userId in (:list)";
        if(add_id.isEmpty()){
            return null;
        }
        List<User> users= (List<User>)session.createQuery(hql3).setParameterList("list",add_id).list();
        return users;
    }


    /**
     * @Description: 获取所有好友指定数据格式列表
     */
    @Override
    public List<FriendInfoVO> getFriendsList(int userId) {
        List<FriendInfoVO> friends = new ArrayList<FriendInfoVO>();
        List<User> users = queryFriendsList(userId);
        for (User user: users) {
            FriendInfoVO friendInfoVO = new FriendInfoVO();
            friendInfoVO.setUserId(user.getUserId());
            friendInfoVO.setIcon(user.getIcon());
            Friend friend = new Friend();
            friend.setUserId(userId);
            friend.setAddedId(user.getUserId());
            friendInfoVO.setLevel(friend.getLevel());
            friendInfoVO.setNickname(friend.getRemark());
            friends.add(friendInfoVO);
        }

        return friends;
    }

    public boolean isExitUser(int userId) {
        String hql = "from User u where u.userId=?";
        List<User> users=(List<User>) hibernateTemplate.find(hql,userId);
        if(users.isEmpty()) return false;
        else return true;

    }

    @Override
    public void saveFriends(Integer myUserId, Integer friendUserId) {
        Friend friend = new Friend();
        friend.setUserId(myUserId);
        friend.setAddedId(friendUserId);
        friend.setLevel(0);
        friendDAO.add(friend);
    }

    @Override
    public void deleteFriend(Integer myUserId, Integer friendUserId) {
        Friend friend = new Friend();
        friend.setUserId(myUserId);
        friend.setAddedId(friendUserId);
        friend.setLevel(0);
        friendDAO.delete(friend);
    }

    @Override
    public List<RecommendFriendInfoVO> recommendFriends(Integer myUserId){
        int myId = myUserId;
        int cnt = userDAO.getCount();
        int randomId1 = 0;
        int randomId2 = 0;
        User tmp = null;
        List myFriends = queryFriendsList(myUserId);
        List myBlackList = queryBlackList(myUserId);
        int flag1 = 0;
        int flag2 = 0;

        while (flag1 != 4){
            flag1 = 0;
            randomId1 = getRandomId(cnt);
            tmp = userDAO.get(randomId1);
            if(myId != randomId1){
                flag1++;
            }
            if(tmp != null){
                flag1++;
            }
            if (myFriends == null || myFriends.isEmpty()){
                flag1++;
            }else if (!myFriends.contains(tmp)){
                flag1++;
            }
            if (myBlackList == null || myBlackList.isEmpty() ){
                flag1++;
            } else if(!myBlackList.contains(tmp)){
                flag1++;
            }
        }
        while (flag2 != 5){
            flag2 = 0;
            randomId2 = getRandomId(cnt);
            tmp = userDAO.get(randomId2);
            if(randomId1 != randomId2){
                flag2++;
            }
            if(myId != randomId2){
                flag2++;
            }
            if(tmp != null){
                flag2++;
            }
            if (myFriends == null || myFriends.isEmpty()){
                flag2++;
            }else if(!myFriends.contains(tmp)){
                flag2++;
            }
            if (myBlackList == null || myBlackList.isEmpty()) {
                flag2++;
            }else if(!myBlackList.contains(tmp)){
                flag2++;
            }
        }
        List<RecommendFriendInfoVO> recommendUsers = new ArrayList<>();
        User u1 = userDAO.get(randomId1);
        User u2 = userDAO.get(randomId2);
        RecommendFriendInfoVO rf1 = new RecommendFriendInfoVO();
        rf1.setUserId(randomId1);
        rf1.setIcon(u1.getIcon());
        rf1.setNickname(u1.getNickname());

        RecommendFriendInfoVO rf2 = new RecommendFriendInfoVO();
        rf2.setUserId(randomId2);
        rf2.setIcon(u2.getIcon());
        rf2.setNickname(u2.getNickname());

        recommendUsers.add(rf1);
        recommendUsers.add(rf2);
        return recommendUsers;
    }

    @Override
    public int getRandomId(int cnt){
        Random random = new Random();
        return (random.nextInt(cnt)+1);
    }

    @Override
    public List<User> queryBlackList(int userId) {
        String hql1 = "select addedId from Blacklist b where b.addedId= ? ";
        List<Integer> add_id= (List<Integer>) hibernateTemplate.find(hql1,userId);
        String hql2 = "select userId from Blacklist b where b.userId= ? ";
        add_id.addAll((List<Integer>)hibernateTemplate.find(hql2,userId));
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql3 = "from User as user where user.userId in (:list)";
        if (add_id.isEmpty()){
            return null;
        }
        List<User> users= (List<User>)session.createQuery(hql3).setParameterList("list",add_id).list();

        return users;
    }

    @Override
    public boolean isExitFriend(int userId,int friendId) {
        String hql = "from Friend f where f.userId = ? and f.addedId = ?";
        List<User> users=(List<User>) hibernateTemplate.find(hql,userId,friendId);
        if(users.isEmpty()) return false;
        else return true;
    }

    @Override
    public Friend saveLevel(int userId, int friendId) {
        Friend friend = friendDAO.get(userId,friendId);
        friend.setLevel(friend.getLevel()==1?0:1);
        friendDAO.update(friend);
        return friend;
    }

    @Override
    public Friend addRemark(int userId, int friendId, String remark) {
        Friend friend = friendDAO.get(userId,friendId);
        friend.setRemark(remark);
        friendDAO.update(friend);
        return friend;
    }

    @Override
    public TrustInfoVO getTrustedInfo(int userId, int friendId) {
        Friend friend = friendDAO.get(userId,friendId);
        User friendInfo = userDAO.get(friendId);

        trustInfoVO.setGender(friendInfo.getGender());
        trustInfoVO.setIcon(friendInfo.getIcon());
        trustInfoVO.setNickname(friendInfo.getNickname());
        trustInfoVO.setProfile(friendInfo.getProfile());
        trustInfoVO.setThisWeekTag(friendInfo.getThisWeekTag());
        trustInfoVO.setRemark(friend.getRemark());
        return trustInfoVO;
    }


    @Override
    public UntrustInfoVO getUntrustedInfo(int userId, int friendId) {
        Friend friend = friendDAO.get(userId,friendId);
        User friendInfo = userDAO.get(friendId);
        untrustInfoVO.setIcon(friendInfo.getIcon());
        untrustInfoVO.setNickname(friendInfo.getNickname());
        untrustInfoVO.setProfile(friendInfo.getProfile());
        untrustInfoVO.setRemark(friend.getRemark());
        return untrustInfoVO;
    }

    @Override
    public Object getInformation(int userId, int friendId) {
        Friend friend = friendDAO.get(userId,friendId);
        if(friend.getLevel()==1) {
            return getTrustedInfo(userId,friendId);
        } else {
            return getUntrustedInfo(userId,friendId);
        }
    }
}
