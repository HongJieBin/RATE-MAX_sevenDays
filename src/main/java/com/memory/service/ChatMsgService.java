package com.memory.service;

import com.memory.pojo.Msg;

import java.util.List;

public interface ChatMsgService {


    /**
     * 获取未查看（签收）的信息
     */
    public List<Msg> getUnReadMsgList(int receiveId);

    /**
     * 查看与一个好友的所有聊天记录
     * @param userId
     * @param receiveId
     * @return
     */
    public List<Msg> getAllMsgList(int userId,int receiveId);


}
