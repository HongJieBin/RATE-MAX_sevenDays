package com.memory.pojo;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Msg
 * @Description TODO
 * @Author SupreacyXXXXX Hkb
 * @Date 2020/4/25
 * @Version 1.1
 **/


@Entity
@Table(name = "msg")
@DynamicInsert
public class Msg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private int msgId;

    @Column(name = "msg_content", nullable = false, length = 128)
    private String msgContent;
    @Column(name = "msg_datetime", nullable = false)
    private Timestamp msgDatetime;

    @Column(name = "msg_action")
    private int msgAction;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "send_id")
    private User sendUser;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "receive_id")
    private User receiveUser;

    @Override
    public String toString() {
        return "Msg{" +
                "msgId=" + msgId +
                ", msgContent='" + msgContent + '\'' +
                ", msgDatetime=" + msgDatetime +
                ", msgAction=" + msgAction +
                ", sendUser=" + sendUser +
                ", receiveUser=" + receiveUser +
                '}';
    }

    public int getMsgId() {
        return msgId;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }


    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }


    public Timestamp getMsgDatetime() {
        return msgDatetime;
    }

    public void setMsgDatetime(Timestamp msgDatetime) {
        this.msgDatetime = msgDatetime;
    }

    public int getMsgAction() {
        return msgAction;
    }

    public void setMsgAction(int msgAction) {
        this.msgAction = msgAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Msg msg = (Msg) o;
        return msgId == msg.msgId &&
                Objects.equals(msgContent, msg.msgContent) &&
                Objects.equals(msgDatetime, msg.msgDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msgId, msgContent, msgDatetime);
    }
}
