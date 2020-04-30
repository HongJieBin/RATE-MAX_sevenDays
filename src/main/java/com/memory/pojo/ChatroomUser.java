package com.memory.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName ChatroomUser
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "chatroom_user")
@IdClass(ChatroomUserPK.class)
public class ChatroomUser {
    @Id
    @Column(name = "chatroom_id")
    private int chatroomId;

    @Id
    @Column(name = "user_id")
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
        ChatroomUser that = (ChatroomUser) o;
        return chatroomId == that.chatroomId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId, userId);
    }

    @Override
    public String toString() {
        return "ChatroomUser{" +
                "chatroomId=" + chatroomId +
                ", userId=" + userId +
                '}';
    }
}
