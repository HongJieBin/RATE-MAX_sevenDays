package com.memory.dao;

import com.memory.pojo.Blacklist;
import com.memory.pojo.Friend;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName FriendDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class FriendDAOImpl implements FriendDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Friend friend) {
        hibernateTemplate.save(friend);
    }

    @Override
    public void update(Friend friend) {
        hibernateTemplate.update(friend);
    }

    @Override
    public void delete(Friend friend) {
        hibernateTemplate.delete(friend);
    }

    @Override
    public List<Friend> getByUserID(int userId) {
        String hql = "from Friend as f where f.userId = ?";
        return (List<Friend>) hibernateTemplate.find(hql, userId);
    }

    @Override
    public List<Friend> getByAddedID(int addedId) {
        String hql = "from Friend as f where f.addedId = ?";
        return (List<Friend>) hibernateTemplate.find(hql, addedId);
    }
}
