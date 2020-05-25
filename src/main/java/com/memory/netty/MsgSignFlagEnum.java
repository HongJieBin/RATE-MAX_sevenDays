package com.memory.netty;

import org.springframework.stereotype.Component;

public enum  MsgSignFlagEnum {
    UNSIGN(0, "未签收"),
    SIGN(1, "已签收");
    public final Integer type;
    public final String content;

    MsgSignFlagEnum(Integer type, String content) {
        this.type = type;
        this.content = content;
    }

    public Integer getType() {
        return type;
    }
}
