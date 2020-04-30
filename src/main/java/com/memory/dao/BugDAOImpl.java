package com.memory.dao;

import com.memory.pojo.Bug;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName BugDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class BugDAOImpl implements BugDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Bug bug) {
        hibernateTemplate.save(bug);
    }

    @Override
    public void update(Bug bug) {
        hibernateTemplate.update(bug);
    }

    @Override
    public void delete(Bug bug) {
        hibernateTemplate.delete(bug);
    }

    @Override
    public Bug get(int id) {
        return hibernateTemplate.get(Bug.class, id);
    }
}
