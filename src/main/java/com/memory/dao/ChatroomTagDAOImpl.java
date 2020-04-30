package com.memory.dao;

import com.memory.pojo.ChatroomUser;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName ChatroomTagDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class ChatroomTagDAOImpl implements ChatroomUserDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(ChatroomUser chatroomUser) {
        hibernateTemplate.save(chatroomUser);
    }

    @Override
    public void update(ChatroomUser chatroomUser) {
        hibernateTemplate.update(chatroomUser);
    }

    @Override
    public void delete(ChatroomUser chatroomUser) {
        hibernateTemplate.delete(chatroomUser);
    }

    @Override
    public ChatroomUser get(int id) {
        return hibernateTemplate.get(ChatroomUser.class, id);
    }
}
