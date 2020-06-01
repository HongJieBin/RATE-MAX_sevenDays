package com.memory.netty;

public class Msg {

    private Integer senderId;
    private Integer receiverId;
    private String content;
    private Integer msgId;
    private Integer chatRoomId;

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                ", msgId=" + msgId +
                '}';
    }
}
