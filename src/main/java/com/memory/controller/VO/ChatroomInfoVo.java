package com.memory.controller.VO;

import com.memory.dao.UserDAO;
import com.memory.pojo.Chatroom;
import com.memory.service.MsgService;
import com.memory.utils.SpringUtils;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

public class ChatroomInfoVo {
    private int chatroomId;
    private String chatroomName;
    private String chatroomTag;
    private int chatroomNumber;
    private Date chatroomStart;
    private Date chatroomEnd;
    private String userName;

    public int getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public String getChatroomTag() {
        return chatroomTag;
    }

    public void setChatroomTag(String chatroomTag) {
        this.chatroomTag = chatroomTag;
    }

    public int getChatroomNumber() {
        return chatroomNumber;
    }

    public void setChatroomNumber(int chatroomNumber) {
        this.chatroomNumber = chatroomNumber;
    }

    public Date getChatroomStart() {
        return chatroomStart;
    }

    public void setChatroomStart(Date chatroomStart) {
        this.chatroomStart = chatroomStart;
    }

    public Date getChatroomEnd() {
        return chatroomEnd;
    }

    public void setChatroomEnd(Date chatroomEnd) {
        this.chatroomEnd = chatroomEnd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setChatroomInfo(Chatroom chatroom) {
        setChatroomId(chatroom.getChatroomId());
        setChatroomEnd(chatroom.getChatroomEnd());
        setChatroomName(chatroom.getChatroomName());
        setChatroomNumber(chatroom.getChatroomNumber());
        setChatroomStart(chatroom.getChatroomStart());
        setChatroomTag(chatroom.getChatroomTag());
        UserDAO userDAO = (UserDAO) SpringUtils.getBean("userDAOImpl");
        setUserName(userDAO.get(chatroom.getUserId()).getNickname());
    }
}

