package com.memory.controller.VO;

public class ChatMsgRequestVO {
    private int acceptUserId;
    private int chatroomId;

    public int getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(int acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public int getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }
}
