package com.memory.service;

import com.memory.controller.VO.ChatroomInfoVo;
import com.memory.dao.ChatroomDAO;
import com.memory.pojo.Chatroom;
import com.memory.pojo.Friend;
import com.memory.pojo.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * @ClassName ChatroomServiceImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/26
 * @Version 1.0
 **/

@Service
@Transactional()
public class ChatroomServiceImpl implements ChatroomService{

    @Autowired
    private ChatroomDAO chatroomDAO;

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addChatroom(Chatroom chatroom) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        chatroom.setChatroomStart(timestamp);

        if (chatroom.getChatroomEnd() == null){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, 1);
            date = calendar.getTime();
            timestamp = new Timestamp(date.getTime());
            chatroom.setChatroomEnd(timestamp);
        }

        chatroomDAO.add(chatroom);
    }

    @Override
    public boolean deleteChatroomById(int chatroomId) {
        Chatroom chatroom = chatroomDAO.get(chatroomId);
        if (chatroom == null){
            return false;
        }
        chatroomDAO.delete(chatroom);
        return true;
    }

    @Override
    public List<ChatroomInfoVo> getMyCreatChatroomInfoList(int userId) {
        String hql1 = "from Chatroom c where c.userId= ? and c.chatroomStatement=0";
        List<Chatroom> roomList= (List<Chatroom>) hibernateTemplate.find(hql1,userId);
        List<ChatroomInfoVo> chatroomInfoList = new ArrayList<ChatroomInfoVo>();
        for (Chatroom chatroom: roomList) {
            ChatroomInfoVo chatroomInfoVo = new ChatroomInfoVo();
            chatroomInfoVo.setChatroomInfo(chatroom);
            chatroomInfoList.add(chatroomInfoVo);
        }
        return chatroomInfoList;
    }


    @Override
    public ChatroomInfoVo getChatroomInfoById(int chatroomId) {
        Chatroom chatroom = chatroomDAO.get(chatroomId);
        ChatroomInfoVo chatroomInfoVo = new ChatroomInfoVo();
        chatroomInfoVo.setChatroomInfo(chatroom);
        return chatroomInfoVo;
    }

    @Override
    public List<ChatroomInfoVo> getMyJoinChatrommList(int userId) {
        String hql1 = "select chatroomId from ChatroomUser cu where cu.userId= ? ";
        List<Integer> roomIdList= (List<Integer>) hibernateTemplate.find(hql1,userId);
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql3 = "from Chatroom as c where c.chatroomId in (:list) and c.chatroomStatement=0";
        if(roomIdList.isEmpty()){
            return null;
        }
        List<Chatroom> roomList = (List<Chatroom>)session.createQuery(hql3).setParameterList("list",roomIdList).list();
        List<ChatroomInfoVo> chatroomInfoList = new ArrayList<ChatroomInfoVo>();
        for (Chatroom chatroom: roomList) {
            ChatroomInfoVo chatroomInfoVo = new ChatroomInfoVo();
            chatroomInfoVo.setChatroomInfo(chatroom);
            chatroomInfoList.add(chatroomInfoVo);
        }
        return chatroomInfoList;
    }
}
