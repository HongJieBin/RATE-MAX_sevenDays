package com.memory.dao;

import com.memory.pojo.Chatroom;
import com.memory.pojo.ChatroomTag;
import com.memory.pojo.ChatroomUser;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ChatroomTagDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class ChatroomTagDAOImpl implements ChatroomTagDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;


    @Override
    public void add(ChatroomTag chatroomTag) {
        hibernateTemplate.save(chatroomTag);
    }

    @Override
    public void update(ChatroomTag chatroomTag) {
        hibernateTemplate.update(chatroomTag);
    }

    @Override
    public void delete(ChatroomTag chatroomTag) {
        hibernateTemplate.delete(chatroomTag);
    }

    @Override
    public ChatroomTag get(int id) {
        return hibernateTemplate.get(ChatroomTag.class, id);
    }

    @Override
    public ChatroomTag getByBoth(int chatroomId, int tagId) {
        String hql = "from ChatroomTag as ct where ct.chatroomId = " + chatroomId + "and ct.tagId = " + tagId;
        List<ChatroomTag> chatroomTags = (List<ChatroomTag>) hibernateTemplate.find(hql);
        if (chatroomTags == null){
            return null;
        }
        return chatroomTags.get(0);
    }
}
