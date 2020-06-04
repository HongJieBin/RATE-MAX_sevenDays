package com.memory.service;

import com.memory.controller.VO.ChatRoomMsgVO;
import com.memory.controller.VO.ChatroomInfoVo;
import com.memory.dao.*;
import com.memory.pojo.*;
import org.hibernate.Session;
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


    //私聊未读消息
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

    //私聊所有消息
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
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setSendUser(senderUser);
        chatMsg.setCmsgDatetime(new Timestamp((new Date()).getTime()));
        chatMsg.setCmsgContent(content);
        chatMsg.setChatroom(chatroom);
        chatmsgDAO.save(chatMsg);
        for (Integer user:userIds) {
            if(user != userId) {
                ChatroomMsgRelation chatroomMsgRelation =new ChatroomMsgRelation();
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

    @Override
    public List<ChatRoomMsgVO> getUnReadChatMsgList(int acceptUserId) {
        String hql = "select msgId from ChatroomMsgRelation as cm where cm.receiveId = ? and cm.msgAction=0";
        List<Integer> msgIds = (List<Integer>)hibernateTemplate.find(hql,acceptUserId);
        if(msgIds.isEmpty()) {
            System.out.println(11);
            return null;
        }
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql3 = "from ChatMsg as cm where cm.cmsgId in (:list) ";
        List<ChatMsg> chatmsgList = (List<ChatMsg>)session.createQuery(hql3).setParameterList("list",msgIds).list();
        if(chatmsgList.isEmpty()){
            System.out.println(22);
            return null;
        }
        List<ChatRoomMsgVO> chatroomMsgList = new ArrayList<ChatRoomMsgVO>();
        for (ChatMsg chatMsg: chatmsgList) {
            ChatRoomMsgVO chatRoomMsgVO = new ChatRoomMsgVO();
            chatRoomMsgVO.setChatroomId(chatMsg.getChatroom().getChatroomId());
            chatRoomMsgVO.setCmsgContent(chatMsg.getCmsgContent());
            chatRoomMsgVO.setCmsgDatetime(chatMsg.getCmsgDatetime());
            chatRoomMsgVO.setCmsgId(chatMsg.getCmsgId());
            chatRoomMsgVO.setSenderId(chatMsg.getSendUser().getUserId());
            chatroomMsgList.add(chatRoomMsgVO);
        }
        return chatroomMsgList;
    }

    @Override
    public List<ChatRoomMsgVO> getAllChatMsgList(int acceptUserId, int chatroomId) {
        String hql = "select msgId from ChatroomMsgRelation as cm where cm.receiveId = ?";
        List<Integer> msgIds = (List<Integer>)hibernateTemplate.find(hql,acceptUserId);
        if(msgIds.isEmpty()) {
            System.out.println(11);
            return null;
        }
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql3 = "from ChatMsg as cm where cm.cmsgId in (:list) and cm.chatroom.chatroomId ="+chatroomId;
        List<ChatMsg> chatmsgList = (List<ChatMsg>)session.createQuery(hql3).setParameterList("list",msgIds).list();
        if(chatmsgList.isEmpty()){
            System.out.println(22);
            return null;
        }
        List<ChatRoomMsgVO> chatroomMsgList = new ArrayList<ChatRoomMsgVO>();
        for (ChatMsg chatMsg: chatmsgList) {
            ChatRoomMsgVO chatRoomMsgVO = new ChatRoomMsgVO();
            chatRoomMsgVO.setChatroomId(chatroomId);
            chatRoomMsgVO.setCmsgContent(chatMsg.getCmsgContent());
            chatRoomMsgVO.setCmsgDatetime(chatMsg.getCmsgDatetime());
            chatRoomMsgVO.setCmsgId(chatMsg.getCmsgId());
            chatRoomMsgVO.setSenderId(chatMsg.getSendUser().getUserId());
            chatroomMsgList.add(chatRoomMsgVO);
        }
        return chatroomMsgList;
    }
}
