package com.memory.service;

import com.memory.dao.MsgDAO;
import com.memory.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional()
public class ChatMsgServiceImpl implements ChatMsgService{
    @Autowired
    private MsgDAO msgDAO;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Msg> getUnReadMsgList(int receiveId) {
        String hql = "from Msg as msg where msg.receiveUser.userId = ? and msg.msgAction = ?";
        List<Msg> msgs = (List<Msg>)hibernateTemplate.find(hql,receiveId,0);
        for(Msg msg :msgs) {
            System.out.println(msg);
        }
        return  msgs;
    }

    @Override
    public List<Msg> getAllMsgList(int userId, int receiveId) {
        String hql = "from Msg as msg where msg.sendUser.userId = ? and msg.receiveUser.userId = ?";
        List<com.memory.pojo.Msg> msgs = (List<com.memory.pojo.Msg>)hibernateTemplate.find(hql,userId,receiveId);
        return  msgs;
    }

}
