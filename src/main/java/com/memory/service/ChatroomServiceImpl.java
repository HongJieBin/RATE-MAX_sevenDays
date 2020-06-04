package com.memory.service;

import com.memory.controller.VO.ChatRoomVO;

import com.memory.controller.VO.TagSortVO;
import com.memory.dao.*;
import com.memory.pojo.Chatroom;
import com.memory.pojo.ChatroomUser;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;

import com.memory.controller.VO.ChatroomInfoVo;

import org.hibernate.Session;

import com.memory.pojo.Chatroom;
import com.memory.pojo.ChatroomTag;
import com.memory.pojo.ChatroomUser;
import com.memory.pojo.Tag;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class ChatroomServiceImpl implements ChatroomService{


    private static long ONEDAY = 24*60*60*1000;

    @Autowired
    private ChatroomDAO chatroomDAO;
    @Autowired
    private ChatroomTagDAO chatroomTagDAO;
    @Autowired
    private TagDAO tagDAO;
    @Autowired
    private ChatroomService chatroomService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private ChatroomUserDAO chatroomUserDAO;

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override

    public String searchById(int chatroomId) {
        String hql = "from Chatroom c where c.chatroomId = ?";
        List<Chatroom> chatroom = (List<Chatroom>) hibernateTemplate.find(hql,chatroomId);
        List<ChatRoomVO> chatRoomVO = new ArrayList<>();
        if(chatroom.isEmpty()) return JsonUtils.toJSON(JsonResult.errorMsg("无法搜索到该聊天室"));
            else{
                chatRoomVO.get(0).setChatroomId(chatroom.get(0).getChatroomId());
                chatRoomVO.get(0).setChatroomName(chatroom.get(0).getChatroomName());
                chatRoomVO.get(0).setChatroomTag(chatroom.get(0).getChatroomTag());
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
    public void addChatRoom(int userId, int chatRoomId) {
        ChatroomUser chatroomUser = new ChatroomUser();
        chatroomUser.setChatroomId(chatRoomId);
        chatroomUser.setUserId(userId);
        Chatroom chatroom = chatroomDAO.get(chatRoomId);
        int num = chatroom.getChatroomNumber() + 1;
        chatroom.setChatroomNumber(num);
        chatroomDAO.add(chatroom);
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
        int cnt = chatroomDAO.getCount(); //聊天室总数
        List<Integer> randomId = new ArrayList<>();//记录推荐的id
        int random;
        List<Integer>  chatroom = new ArrayList<>();
        List<ChatroomInfoVo> chatroomInfoVoList = chatroomService.getMyJoinChatroomList(userId);

        if(chatroomInfoVoList !=null)
         for (ChatroomInfoVo chatroomInfoVo : chatroomInfoVoList) {
            chatroom.add(chatroomInfoVo.getChatroomId());
        }
        int num = 0; //总共匹配成功的聊天室
        int match = 0; //进行匹配的次数，多于30次或者超过可匹配人数上限时停止匹配（刚开始时可能聊天室较少）
        Chatroom tmp;


        //匹配推荐
        while(num < 3 && match < 30){
            random = getRandomId(cnt);
            tmp = chatroomDAO.get(random);
            if((isMatching(userId,random)) && (tmp != null) && (!chatroom.contains(userId))){

                if(!randomId.contains(random)){
                    randomId.add(num,random);
                    num++;
                }
            }

            match++;
        }

        //随机推荐
        while(num < 5){
            random = getRandomId(cnt);
            tmp = chatroomDAO.get(random);
            if((tmp != null) && (!chatroom.contains(userId))){
                if(!randomId.contains(random)){
                    randomId.add(num,random);
                    num++;
                }

            }
        }
        List<ChatRoomVO> recommendChatroom = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            recommendChatroom.add(addByChatroomId(randomId.get(i)));
        }

        return recommendChatroom;


    }

    /**
     * 推荐聊天室的匹配程度只需要有两个聊天室标签和用户匹配就算成功
     */

    private boolean isMatching(int userId, int random) {
        int pt = 0;
        TagSortVO tagSortVO = friendService.TagSort(userId);
        List<ChatroomTag> chatroomTagList = chatroomService.findById(random);

        if(tagSortVO == null || chatroomTagList == null) return false;
        for (ChatroomTag chatroomTag : chatroomTagList) {

            if (tagSortVO.getFirstTagID().equals(chatroomTag.getTagId())) pt++;
            if (tagSortVO.getSecondTagID().equals(chatroomTag.getTagId())) pt++;
            if (tagSortVO.getThirdTagID().equals(chatroomTag.getTagId())) pt++;
        }

        return pt >= 2;
    }


    public int getRandomId(int cnt){
            Random random = new Random();
            return (random.nextInt(cnt)+1);
        }


    private ChatRoomVO addByChatroomId(Integer chatroomId) {
        Chatroom c1 = chatroomDAO.get(chatroomId);
        ChatRoomVO rf1 = new ChatRoomVO();
        rf1.setChatroomName(c1.getChatroomName());
        rf1.setChatroomTag(c1.getChatroomTag());
        rf1.setChatroomId(chatroomId);
        return rf1;
    }

    @Override
    public ChatRoomVO addByChatroom(Chatroom chatroom) {
        ChatRoomVO chatRoomVO = new ChatRoomVO();
        chatRoomVO.setChatroomId(chatroom.getChatroomId());
        chatRoomVO.setChatroomTag(chatroom.getChatroomTag());
        chatRoomVO.setChatroomName(chatroom.getChatroomName());
        return  chatRoomVO;
    }



    public Chatroom addChatroom(Chatroom chatroom) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        chatroom.setChatroomStart(timestamp);

        if (chatroom.getChatroomEnd() == null){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, 1);
            date = calendar.getTime();
            timestamp = new Timestamp(date.getTime());
            chatroom.setChatroomEnd(timestamp);
        }

        chatroom.setChatroomNumber(1);

        chatroomDAO.add(chatroom);
        ChatroomUser chatroomUser = new ChatroomUser();
        chatroomUser.setChatroomId(chatroom.getChatroomId());
        chatroomUser.setUserId(chatroom.getUserId());
        chatroomUserDAO.add(chatroomUser);
        addChatroomTags(chatroom);
        return chatroom;
    }

    @Override
    public Chatroom updateChatroom(Chatroom chatroom) {
        Chatroom result = chatroomDAO.get(chatroom.getChatroomId());
        System.out.println("result:"+result);
        result.setChatroomName(chatroom.getChatroomName());
        result.setChatroomTag(chatroom.getChatroomTag());
        chatroomDAO.update(result);
        addChatroomTags(result);
        return result;
    }

    @Override
    public List<ChatroomTag> findById(int ChatroomId) {
        String hql = "from ChatroomTag ct where ct.chatroomId = ?";
        List<ChatroomTag> chatroomTagList =(List<ChatroomTag>)  hibernateTemplate.find(hql,ChatroomId);
        return  chatroomTagList;
    }

    public boolean addChatroomTags(Chatroom chatroom){
        String tags = chatroom.getChatroomTag();
        String[] tagList = tags.split(" ");
        for(String s : tagList){
            Tag tag = tagDAO.getByName(s);
            ChatroomTag chatroomTag = new ChatroomTag();
            if (chatroomTagDAO.getByBoth(chatroom.getChatroomId(), tag.getTagId()) == null){
                chatroomTag.setChatroomId(chatroom.getChatroomId());
                chatroomTag.setTagId(tag.getTagId());
                chatroomTagDAO.add(chatroomTag);
            }
        }
        return true;
    }

    @Override
    public boolean deleteChatroomById(int chatroomId, int userId) {
        Chatroom chatroom = chatroomDAO.get(chatroomId);
        System.out.println(chatroom);
        System.out.println(chatroomId);
        if (chatroom == null){
            return false;
        }
        if (chatroom.getUserId() == userId){
            chatroom.setChatroomStatement(1);
            chatroomDAO.update(chatroom);
        }
        chatroom.setChatroomNumber(chatroom.getChatroomNumber() - 1);
        chatroomDAO.update(chatroom);
        ChatroomUser chatroomUser = new ChatroomUser();
        chatroomUser.setUserId(userId);
        chatroomUser.setChatroomId(chatroomId);
        chatroomUserDAO.delete(chatroomUser);
        return true;
    }

    @Override
    public List<ChatroomInfoVo> getMyCreatChatroomInfoList(int userId) {
        String hql1 = "from Chatroom c where c.userId= ? and c.chatroomStatement=0";
        List<Chatroom> roomList= (List<Chatroom>) hibernateTemplate.find(hql1,userId);
        List<ChatroomInfoVo> chatroomInfoList = new ArrayList<ChatroomInfoVo>();
        for (Chatroom chatroom: roomList) {
            ChatroomInfoVo chatroomInfoVo = new ChatroomInfoVo();
            chatroomInfoVo.setChatroomInfo(chatroom);
            chatroomInfoList.add(chatroomInfoVo);
        }
        return chatroomInfoList;
    }


    @Override
    public ChatroomInfoVo getChatroomInfoById(int chatroomId) {
        Chatroom chatroom = chatroomDAO.get(chatroomId);
        ChatroomInfoVo chatroomInfoVo = new ChatroomInfoVo();
        chatroomInfoVo.setChatroomInfo(chatroom);
        return chatroomInfoVo;
    }

    @Override
    public List<ChatroomInfoVo> getMyJoinChatroomList(int userId) {
        String hql1 = "select chatroomId from ChatroomUser cu where cu.userId= ?";
        List<Integer> roomIdList= (List<Integer>) hibernateTemplate.find(hql1,userId);
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql3 = "from Chatroom as c where c.chatroomId in (:list) and c.chatroomStatement=0 and c.userId !="+userId;
        if(roomIdList.isEmpty()){
            return null;
        }
        List<Chatroom> roomList = (List<Chatroom>)session.createQuery(hql3).setParameterList("list",roomIdList).list();
        if(roomList.isEmpty()){
            return null;
        }
        List<ChatroomInfoVo> chatroomInfoList = new ArrayList<ChatroomInfoVo>();
        for (Chatroom chatroom: roomList) {
            ChatroomInfoVo chatroomInfoVo = new ChatroomInfoVo();
            chatroomInfoVo.setChatroomInfo(chatroom);
            chatroomInfoList.add(chatroomInfoVo);
        }
        return chatroomInfoList;
    }



    @Override
    public List<ChatroomInfoVo> getBeforeChatroomList(int userId) {
        String hql1 = "select chatroomId from ChatroomUser cu where cu.userId= ? ";
        List<Integer> roomIdList= (List<Integer>) hibernateTemplate.find(hql1,userId);
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql3 = "from Chatroom as c where c.chatroomId in (:list) and c.chatroomStatement=1";
        if(roomIdList.isEmpty()){
            return null;
        }
        List<Chatroom> roomList = (List<Chatroom>)session.createQuery(hql3).setParameterList("list",roomIdList).list();
        List<ChatroomInfoVo> chatroomInfoList = new ArrayList<ChatroomInfoVo>();
        for (Chatroom chatroom: roomList) {
            ChatroomInfoVo chatroomInfoVo = new ChatroomInfoVo();
            chatroomInfoVo.setChatroomInfo(chatroom);
            chatroomInfoList.add(chatroomInfoVo);
        }
        return chatroomInfoList;
    }
    @Override
    public Chatroom get(int chatRoomId) {
        return chatroomDAO.get(chatRoomId);
    }

    @Override
    public List<Chatroom> getAll() {
        return chatroomDAO.getAll();
    }

    @Override
    public void closeChatRoom(int chatRoomId) throws Exception{
        Chatroom chatroom = chatroomDAO.get(chatRoomId);
        if(chatroom == null)
            throw new Exception("该聊天室不存在！");
        if(chatroom.getChatroomStatement() == 1)
            throw new Exception("该聊天室已关闭！");
        chatroom.setChatroomEnd(new Timestamp(new Date().getTime()));
        chatroom.setChatroomStatement(1);
        chatroomDAO.update(chatroom);
    }

    @Override
    public void openChatRoom(int chatRoomId) throws Exception{
        Chatroom chatroom= chatroomDAO.get(chatRoomId);
        if (chatroom == null)
            throw new Exception("该聊天室不存在！");
        if(chatroom.getChatroomStatement() == 0)
            throw new Exception("该聊天室已打开！");
        chatroom.setChatroomEnd(new Timestamp(new Date().getTime() + ONEDAY));
        chatroom.setChatroomStatement(0);
        chatroomDAO.update(chatroom);
    }

    @Override
    public boolean isOpenChatroom(int chatRoomId) {
        Chatroom chatroom = chatroomDAO.get(chatRoomId);
        if(chatroom.getChatroomStatement()==0) return true;
        else return false;
    }
}
