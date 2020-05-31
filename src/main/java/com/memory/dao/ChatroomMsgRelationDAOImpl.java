package com.memory.dao;


import com.memory.pojo.ChatMsg;
import com.memory.pojo.ChatroomMsgRelation;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
@Transactional
public class ChatroomMsgRelationDAOImpl implements ChatroomMsgRelationDAO {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(ChatroomMsgRelation chatroomMsgRelation) {
        hibernateTemplate.save(chatroomMsgRelation);
    }

    @Override
    public void update(ChatroomMsgRelation chatroomMsgRelation) {
        hibernateTemplate.update(chatroomMsgRelation);
    }

    @Override
    public void delete(ChatroomMsgRelation chatroomMsgRelation) {
        hibernateTemplate.delete(chatroomMsgRelation);
    }

    @Override
    public ChatroomMsgRelation get(int id) {
        return hibernateTemplate.get(ChatroomMsgRelation.class, id);
    }

    @Override
    public Integer save(ChatroomMsgRelation chatroomMsgRelation) {
        hibernateTemplate.save(chatroomMsgRelation);
        return chatroomMsgRelation.getRelationId();
    }
}
