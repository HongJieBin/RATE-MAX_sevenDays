package com.memory.dao;


import com.memory.pojo.ChatMsg;
import com.memory.pojo.ChatroomMsgRelation;
import com.memory.pojo.Msg;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public ChatroomMsgRelation get(int msgId, int receiveId) {
        String hql = "from ChatroomMsgRelation as cm where cm.msgId = ? and cm.receiveId= ?";
        List<ChatroomMsgRelation> msgIds = (List<ChatroomMsgRelation>)hibernateTemplate.find(hql,msgId,receiveId);
        if(msgIds.size() == 0)
            return null;
        return msgIds.get(0);
    }
}
