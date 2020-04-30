package com.memory.dao;

import com.memory.pojo.Memory;
import com.memory.pojo.Msg;
import com.memory.pojo.Tag;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


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
}
