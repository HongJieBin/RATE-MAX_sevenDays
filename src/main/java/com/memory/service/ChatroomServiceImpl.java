package com.memory.service;

import com.memory.dao.ChatroomDAO;
import com.memory.pojo.Chatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
}
