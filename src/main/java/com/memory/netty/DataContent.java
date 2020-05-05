package com.memory.netty;

import com.memory.pojo.ChatMsg;

import java.io.Serializable;

public class DataContent implements Serializable {

    private static final long serialVersionUID = 8021381444738260454L;

    private Integer action;		// 动作类型
    private Msg msg;	// 用户的聊天内容entity
    private String extend;		// 扩展字段

    public DataContent(Integer action, Msg msg, String extend) {
        this.action = action;
        this.msg = msg;
        this.extend = extend;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "DataContent{" +
                "action=" + action +
                ", msg=" + msg +
                ", extend='" + extend + '\'' +
                '}';
    }
}
