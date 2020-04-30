package com.memory.dao;

import com.memory.pojo.Chatroom;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName ChatroomDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class ChatroomDAOImpl implements ChatroomDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Chatroom chatroom) {
        hibernateTemplate.save(chatroom);
    }

    @Override
    public void update(Chatroom chatroom) {
        hibernateTemplate.update(chatroom);
    }

    @Override
    public void delete(Chatroom chatroom) {
        hibernateTemplate.delete(chatroom);
    }

    @Override
    public Chatroom get(int id) {
        return hibernateTemplate.get(Chatroom.class, id);
    }
}
