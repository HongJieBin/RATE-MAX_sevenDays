package com.memory.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName ChatroomUserPK
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


public class ChatroomUserPK implements Serializable {
    @Column(name = "chatroom_id")
    @Id
    private int chatroomId;

    @Column(name = "user_id")
    @Id
    private int userId;


    public int getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatroomUserPK that = (ChatroomUserPK) o;
        return chatroomId == that.chatroomId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId, userId);
    }

    @Override
    public String toString() {
        return "ChatroomUserPK{" +
                "chatroomId=" + chatroomId +
                ", userId=" + userId +
                '}';
    }
}
