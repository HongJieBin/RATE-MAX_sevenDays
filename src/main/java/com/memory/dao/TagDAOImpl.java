package com.memory.dao;

import com.memory.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TagDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/22
 * @Version 1.0
 **/

@Repository
@Transactional
public class TagDAOImpl implements TagDAO{
    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public void add(Tag tag) {
        hibernateTemplate.save(tag);
    }

    @Override
    public void update(Tag tag) {
        hibernateTemplate.update(tag);
    }

    @Override
    public void delete(Tag tag) {
        hibernateTemplate.delete(tag);
    }

    @Override
    public Tag get(int id) {
        return hibernateTemplate.get(Tag.class, id);
    }
}
