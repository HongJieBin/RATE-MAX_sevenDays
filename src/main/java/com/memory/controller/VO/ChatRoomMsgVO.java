package com.memory.controller.VO;

import java.sql.Timestamp;

public class ChatRoomMsgVO {
    private int chatroomId;
    private int cmsgId;
    private int msgAction;
    private int senderId;
    private Timestamp cmsgDatetime;
    private String cmsgContent;

    public int getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    public int getCmsgId() {
        return cmsgId;
    }

    public void setCmsgId(int cmsgId) {
        this.cmsgId = cmsgId;
    }

    public int getMsgAction() {
        return msgAction;
    }

    public void setMsgAction(int msgAction) {
        this.msgAction = msgAction;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public Timestamp getCmsgDatetime() {
        return cmsgDatetime;
    }

    public void setCmsgDatetime(Timestamp cmsgDatetime) {
        this.cmsgDatetime = cmsgDatetime;
    }

    public String getCmsgContent() {
        return cmsgContent;
    }

    public void setCmsgContent(String cmsgContent) {
        this.cmsgContent = cmsgContent;
    }
}
