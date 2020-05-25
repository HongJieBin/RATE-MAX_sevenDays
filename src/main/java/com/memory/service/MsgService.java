package com.memory.service;

import java.util.List;

public interface MsgService {
    Integer save(com.memory.netty.Msg msg);
    void updateMsgSigned(List<Integer> msgIdList);
    List<com.memory.netty.Msg> getUnReadMsgList(Integer acceptUserId);
}
