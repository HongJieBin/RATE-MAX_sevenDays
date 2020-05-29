package com.memory.controller.VO;

public class ChatRoomVO {
    private int chatroomId;
    private String chatroomName;
    private String chatroomTag;

    public int getChatroomId() {
        return chatroomId;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public String getChatroomTag() {
        return chatroomTag;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public void setChatroomTag(String chatroomTag) {
        this.chatroomTag = chatroomTag;
    }
}
