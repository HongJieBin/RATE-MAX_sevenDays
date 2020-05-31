package com.memory.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName Chatroom
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "chatroom")
@DynamicInsert
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private int chatroomId;

    @Column(name = "chatroom_name", nullable = false, length = 64)
    private String chatroomName;

    @Column(name = "chatroom_tag", nullable = false, length = 64)
    private String chatroomTag;

    @Column(name = "chatroom_number")
    @ColumnDefault("0")
    private int chatroomNumber;

    @Column(name = "chatroom_statement")
    @ColumnDefault("0")
    private int chatroomStatement;

    @Column(name = "chatroom_hot")
    @ColumnDefault("0")
    private int chatroomHot;

    @Column(name = "chatroom_start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date chatroomStart;

    @Column(name = "chatroom_end", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date chatroomEnd;


    @Column(name = "user_id", nullable = false)
    private int userId;



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public String getChatroomTag() {
        return chatroomTag;
    }

    public void setChatroomTag(String chatroomTag) {
        this.chatroomTag = chatroomTag;
    }

    public int getChatroomNumber() {
        return chatroomNumber;
    }

    public void setChatroomNumber(int chatroomNumber) {
        this.chatroomNumber = chatroomNumber;
    }

    public int getChatroomStatement() {
        return chatroomStatement;
    }

    public void setChatroomStatement(int chatroomStatement) {
        this.chatroomStatement = chatroomStatement;
    }

    public int getChatroomHot() {
        return chatroomHot;
    }

    public void setChatroomHot(int chatroomHot) {
        this.chatroomHot = chatroomHot;
    }

    public Date getChatroomStart() {
        return chatroomStart;
    }

    public void setChatroomStart(Date chatroomStart) {
        this.chatroomStart = chatroomStart;
    }

    public Date getChatroomEnd() {
        return chatroomEnd;
    }

    public void setChatroomEnd(Date chatroomEnd) {
        this.chatroomEnd = chatroomEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return chatroomId == chatroom.chatroomId &&
                chatroomNumber == chatroom.chatroomNumber &&
                chatroomStatement == chatroom.chatroomStatement &&
                chatroomHot == chatroom.chatroomHot &&
                userId == chatroom.userId &&
                Objects.equals(chatroomName, chatroom.chatroomName) &&
                Objects.equals(chatroomTag, chatroom.chatroomTag) &&
                Objects.equals(chatroomStart, chatroom.chatroomStart) &&
                Objects.equals(chatroomEnd, chatroom.chatroomEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatroomId, chatroomName, chatroomTag, chatroomNumber, chatroomStatement, chatroomHot, chatroomStart, chatroomEnd, userId);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "chatroomId=" + chatroomId +
                ", chatroomName='" + chatroomName + '\'' +
                ", chatroomTag='" + chatroomTag + '\'' +
                ", chatroomNumber=" + chatroomNumber +
                ", chatroomStatement=" + chatroomStatement +
                ", chatroomHot=" + chatroomHot +
                ", chatroomStart=" + chatroomStart +
                ", chatroomEnd=" + chatroomEnd +
                ", userId=" + userId +
                '}';
    }


}
