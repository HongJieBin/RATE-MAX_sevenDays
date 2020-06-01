package com.memory.pojo;

import javax.persistence.*;

@Entity
@Table(name = "chat_msg_relation")
public class ChatroomMsgRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private int relationId;

    @Column(name = "msg_id", nullable = false)
    private int msgId;

    @Column(name = "receive_id",nullable = false)
    private int receiveId;

    @Column(name = "msg_action")
    private int msgAction;

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public int getMsgAction() {
        return msgAction;
    }

    public void setMsgAction(int msgAction) {
        this.msgAction = msgAction;
    }

    @Override
    public String toString() {
        return "ChatroomMsgRelation{" +
                "relationId=" + relationId +
                ", msgId=" + msgId +
                ", receiveId=" + receiveId +
                ", msgAction=" + msgAction +
                '}';
    }
}
