package com.memory.dao;

import com.memory.pojo.Memory;
import com.memory.pojo.Msg;
import com.memory.pojo.Tag;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


/**
 * @ClassName MemoryDAOImpl
 * @Description TODO
 * @Author Hkb
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class MsgDAOImpl implements MsgDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Msg msg) {
        hibernateTemplate.save(msg);
    }

    @Override
    public void update(Msg msg) {
        hibernateTemplate.update(msg);
    }

    @Override
    public void delete(Msg msg) {
        hibernateTemplate.delete(msg);
    }

    @Override
    public Msg get(int id) {
        return hibernateTemplate.get(Msg.class, id);
    }

    @Override
    public Integer save(Msg msg) {
        hibernateTemplate.save(msg);
        return msg.getMsgId();
    }

    @Override
    public List<Msg> getUnReadMsgList(Integer acceptUserId) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql = String.format("from Msg where Msg.msgAction=0 and Msg.receiveUser.id=%d",acceptUserId);
        System.out.println(hql);
        Query query = session.createQuery(hql);
        List<Msg> list = query.list();

        return list;
    }
}
