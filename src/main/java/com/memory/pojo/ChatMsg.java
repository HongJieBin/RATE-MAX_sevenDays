package com.memory.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName ChatMsg
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "chat_msg", schema = "hebdo", catalog = "")
public class ChatMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmsg_id")
    private int cmsgId;

    @Column(name = "cmsg_content", nullable = false, length = 128)
    private String cmsgContent;

    @Column(name = "cmsg_datetime", nullable = false)
    private Timestamp cmsgDatetime;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "send_id")
    private User sendUser;

    @OneToOne(targetEntity = Chatroom.class)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public int getCmsgId() {
        return cmsgId;
    }

    public void setCmsgId(int cmsgId) {
        this.cmsgId = cmsgId;
    }

    public String getCmsgContent() {
        return cmsgContent;
    }

    public void setCmsgContent(String cmsgContent) {
        this.cmsgContent = cmsgContent;
    }

    public Timestamp getCmsgDatetime() {
        return cmsgDatetime;
    }

    public void setCmsgDatetime(Timestamp cmsgDatetime) {
        this.cmsgDatetime = cmsgDatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMsg chatMsg = (ChatMsg) o;
        return cmsgId == chatMsg.cmsgId &&
                Objects.equals(cmsgContent, chatMsg.cmsgContent) &&
                Objects.equals(cmsgDatetime, chatMsg.cmsgDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmsgId, cmsgContent, cmsgDatetime);
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "cmsgId=" + cmsgId +
                ", cmsgContent='" + cmsgContent + '\'' +
                ", cmsgDatetime=" + cmsgDatetime +
                ", sendUser=" + sendUser +
                ", chatroom=" + chatroom +
                '}';
    }
}
