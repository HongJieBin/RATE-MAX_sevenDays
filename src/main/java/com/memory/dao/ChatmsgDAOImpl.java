package com.memory.dao;

import com.memory.pojo.Admin;
import com.memory.pojo.ChatMsg;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
@Transactional
public class ChatmsgDAOImpl implements ChatmsgDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(ChatMsg chatMsg) {
        hibernateTemplate.save(chatMsg);
    }

    @Override
    public void update(ChatMsg chatMsg) {
        hibernateTemplate.update(chatMsg);
    }

    @Override
    public void delete(ChatMsg chatMsg) {
        hibernateTemplate.delete(chatMsg);
    }

    @Override
    public ChatMsg get(int id) {
        return hibernateTemplate.get(ChatMsg.class, id);
    }

    @Override
    public Integer save(ChatMsg chatMsg) {
        hibernateTemplate.save(chatMsg);
        return chatMsg.getCmsgId();
    }
}
