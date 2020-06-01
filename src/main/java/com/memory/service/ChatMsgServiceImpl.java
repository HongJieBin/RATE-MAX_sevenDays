package com.memory.service;

import com.memory.dao.*;
import com.memory.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional()
public class ChatMsgServiceImpl implements ChatMsgService{
    @Autowired
    private MsgDAO msgDAO;
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private UserDAO userDao;
    @Autowired
    private ChatmsgDAO chatmsgDAO;
    @Autowired
    private ChatroomDAO chatroomDAO;
    @Autowired
    private ChatroomMsgRelationDAO chatroomMsgRelationDAO;


    @Override
    public List<Msg> getUnReadMsgList(int receiveId) {
        String hql = "from Msg as msg where msg.receiveUser.userId = ? and msg.msgAction = ?";
        List<Msg> msgs = (List<Msg>)hibernateTemplate.find(hql,receiveId,0);
        for(Msg msg :msgs) {
            System.out.println(msg);
        }
        if (msgs.isEmpty()) {
            return null;
        }
        return  msgs;
    }

    @Override
    public List<Msg> getAllMsgList(int userId, int receiveId) {
        String hql = "from Msg as msg where msg.sendUser.userId = ? and msg.receiveUser.userId = ?";
        List<com.memory.pojo.Msg> msgs = (List<com.memory.pojo.Msg>)hibernateTemplate.find(hql,userId,receiveId);
        return  msgs;
    }

    @Override
    public Map<Integer,Integer> save(int userId, int chatroomId, String content) {
        List<Integer> userIds = getUserListInChatroom(chatroomId);
        User senderUser = userDao.get(userId);
        Chatroom chatroom = chatroomDAO.get(chatroomId);
        ConcurrentHashMap<Integer,Integer> relation = new ConcurrentHashMap<>();
        for (Integer user:userIds) {
            if(user != userId) {
                ChatMsg chatMsg = new ChatMsg();
                ChatroomMsgRelation chatroomMsgRelation =new ChatroomMsgRelation();
                chatMsg.setSendUser(senderUser);
                chatMsg.setCmsgDatetime(new Timestamp((new Date()).getTime()));
                chatMsg.setCmsgContent(content);
                chatMsg.setChatroom(chatroom);
                chatmsgDAO.save(chatMsg);
                chatroomMsgRelation.setMsgAction(0);
                chatroomMsgRelation.setMsgId(chatMsg.getCmsgId());
                chatroomMsgRelation.setReceiveId(user);
                chatroomMsgRelationDAO.save(chatroomMsgRelation);
                relation.put(user,chatMsg.getCmsgId());
            }
        }
        return relation;
    }

    @Override
    public List<Integer> getUserListInChatroom(int chatroomId) {
        String hql = "select userId from ChatroomUser as cu where cu.chatroomId = ?";
        List<Integer> userIds = (List<Integer>)hibernateTemplate.find(hql,chatroomId);
        return userIds;
    }
}
