package com.memory.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName ChatroomTagPK
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


public class ChatroomTagPK implements Serializable {
    @Column(name = "chatroom_id")
    @Id
    private int chatroomId;

    @Column(name = "tag_id")
    @Id
    private int tagId;


    public int getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatroomTagPK that = (ChatroomTagPK) o;
        return chatroomId == that.chatroomId &&
                tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId, tagId);
    }

    @Override
    public String toString() {
        return "ChatroomTagPK{" +
                "chatroomId=" + chatroomId +
                ", tagId=" + tagId +
                '}';
    }
}
