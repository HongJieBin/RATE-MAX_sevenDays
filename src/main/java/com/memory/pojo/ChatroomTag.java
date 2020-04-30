package com.memory.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName ChatroomTag
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "chatroom_tag")
@IdClass(ChatroomTagPK.class)
public class ChatroomTag {
    @Id
    @Column(name = "chatroom_id")
    private int chatroomId;

    @Id
    @Column(name = "tag_id")
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
        ChatroomTag that = (ChatroomTag) o;
        return chatroomId == that.chatroomId &&
                tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId, tagId);
    }

    @Override
    public String toString() {
        return "ChatroomTag{" +
                "chatroomId=" + chatroomId +
                ", tagId=" + tagId +
                '}';
    }
}
