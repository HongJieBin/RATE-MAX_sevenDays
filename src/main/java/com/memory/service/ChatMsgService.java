package com.memory.service;

import com.memory.controller.VO.ChatRoomMsgVO;
import com.memory.pojo.Msg;

import java.util.List;
import java.util.Map;

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

    public Map<Integer,Integer> save(int userId, int chatroomId, String content);

    public List<Integer> getUserListInChatroom(int chatroomId);

    public List<ChatRoomMsgVO> getUnReadChatMsgList(int acceptUserId);

    public List<ChatRoomMsgVO> getAllChatMsgList(int acceptUserId, int chatroomId);

    void updateChatMsgSigned(List<Integer> msgIdList);


}
