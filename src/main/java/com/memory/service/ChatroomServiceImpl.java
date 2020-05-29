package com.memory.service;

import com.memory.controller.VO.ChatRoomVO;
import com.memory.dao.ChatroomUserDAO;
import com.memory.pojo.Chatroom;
import com.memory.pojo.ChatroomUser;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChatroomServiceImpl implements ChatroomService{


    @Autowired
    ChatroomUserDAO chatroomUserDAO;

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public String searchById(int chatroomId) {
        String hql = "from Chatroom c where c.chatroomId = ?";
        List<Chatroom> chatroom = (List<Chatroom>) hibernateTemplate.find(hql,chatroomId);
        ChatRoomVO chatRoomVO = new ChatRoomVO();
        if(chatroom.isEmpty()) return JsonUtils.toJSON(JsonResult.errorMsg("无法搜索到该聊天室"));
            else{
                chatRoomVO.setChatroomId(chatroom.get(0).getChatroomId());
                chatRoomVO.setChatroomName(chatroom.get(0).getChatroomName());
                chatRoomVO.setChatroomTag(chatroom.get(0).getChatroomTag());
                return JsonUtils.toJSON(JsonResult.ok(chatRoomVO));
            }

    }

    @Override
    public String searchByTag(String chatroomTag) {
        String hql = "from Chatroom c where c.chatroomTag = ?";

        List<Chatroom> chatroom = (List<Chatroom>) hibernateTemplate.find(hql,chatroomTag);
        List<ChatRoomVO> chatRoomVO = new ArrayList<>();
        if(chatroom.isEmpty()) return JsonUtils.toJSON(JsonResult.errorMsg("无法搜索到有该标签聊天室"));
            else{
            return getString(chatroom,chatRoomVO);
        }
    }

    @Override
    public String searchByName(String chatroomName) {
        String hql = "from Chatroom c where c.chatroomName like ?";
        List<Chatroom> chatroom = (List<Chatroom>) hibernateTemplate.find(hql,'%' + chatroomName + '%');
        List<ChatRoomVO> chatRoomVO = new ArrayList<>();
        if(chatroom.isEmpty()) return JsonUtils.toJSON(JsonResult.errorMsg("无法搜索到聊天室"));
        else{
            return getString(chatroom,chatRoomVO);
        }
    }

    private String getString(List<Chatroom> chatroom, List<ChatRoomVO> chatRoomVO) {
        if(chatroom.size() > 10){
            for(int i = 0; i < 10; i++){
                chatRoomVO.add(addByChatroom(chatroom.get(i)));
            }
        }
        else {
            for (Chatroom value : chatroom) {
                chatRoomVO.add(addByChatroom(value));
            }
        }
        return JsonUtils.toJSON(JsonResult.ok(chatRoomVO));
    }

    @Override
    public void addChatRoom(int userid, int chatRoomId) {
        ChatroomUser chatroomUser = new ChatroomUser();
        chatroomUser.setChatroomId(chatRoomId);
        chatroomUser.setUserId(userid);
        chatroomUserDAO.add(chatroomUser);
    }

    @Override
    public boolean isExistChatRoom(int chatroomId) {
        String hql = "from Chatroom c where c.chatroomId = ?";

        List<Chatroom> chatRoom=(List<Chatroom>) hibernateTemplate.find(hql,chatroomId);
        return !chatRoom.isEmpty();
    }

    @Override
    public boolean isInChatRoom(int userId, int chatroomId) {
        String hql = "from ChatroomUser c where c.chatroomId = ? and c.userId = ?";

        List<ChatroomUser> chatRoom=(List<ChatroomUser>) hibernateTemplate.find(hql,chatroomId,userId);
        return !chatRoom.isEmpty();
    }

    @Override
    public List<ChatRoomVO> recommendChatroom(int userId) {
        return null;
    }

    @Override
    public ChatRoomVO addByChatroom(Chatroom chatroom) {
        ChatRoomVO chatRoomVO = new ChatRoomVO();
        chatRoomVO.setChatroomId(chatroom.getChatroomId());
        chatRoomVO.setChatroomTag(chatroom.getChatroomTag());
        chatRoomVO.setChatroomName(chatroom.getChatroomName());
        return  chatRoomVO;
    }


}
