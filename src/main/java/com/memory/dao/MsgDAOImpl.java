package com.memory.dao;

import com.memory.pojo.Memory;
import com.memory.pojo.Msg;
import com.memory.pojo.Tag;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Msg> getUnReadMsgList(Integer acceptUserId) {
//        SELECT * from msg where msg_action=0 and receive_id=18
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "SELECT * from msg where msg_action=0 and receive_id=18";
        SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Msg.class);
        List<Msg> list = sqlQuery.list();
        System.out.println("list about" + list);
        return list;
    }
}
