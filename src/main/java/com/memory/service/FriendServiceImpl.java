package com.memory.service;

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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional()
public class FriendServiceImpl implements FriendService{
    @Autowired
    private FriendDAO friendDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BlacklistDAO blacklistDAO;

    @Resource
    private HibernateTemplate hibernateTemplate;


    /**
     * @Description: 查询好友列表
     */
    @Override
    public List<User> queryFriendsList(int userId) {
        String hql1 = "select addedId from Friend f where f.userId= ? ";
        List<Integer> add_id= (List<Integer>) hibernateTemplate.find(hql1,userId);
        String hql2 = "select userId from Friend f where f.addedId= ? ";
        add_id.addAll((List<Integer>)hibernateTemplate.find(hql2,userId));
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql3 = "from User as user where user.userId in (:list)";
        if(add_id.isEmpty()){
            return null;
        }
        List<User> users= (List<User>)session.createQuery(hql3).setParameterList("list",add_id).list();

        return users;
        /*for(String string:users){
            System.out.println(string);
        }
        //List<User> users =  hibernateTemplate.find(hql3).set(1,add_id);
        //List<User> users = (List<User>) hibernateTemplate.find(hql3,values);
        /*for(Integer id:add_id){
            System.out.println(id);
        }*/
        //return  null;
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
    public List<User> recommendFriends(Integer myUserId){
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
        List<User> recommendUsers = new ArrayList<>();
        User u1 = userDAO.get(randomId1);
        User u2 = userDAO.get(randomId2);
        recommendUsers.add(u1);
        recommendUsers.add(u2);
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
}
