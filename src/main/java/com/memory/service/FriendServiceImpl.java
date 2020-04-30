package com.memory.service;

import com.memory.dao.FriendDAO;
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
import java.util.List;

@Service
@Transactional()
public class FriendServiceImpl implements FriendService{
    @Autowired
    private FriendDAO friendDAO;

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
}
