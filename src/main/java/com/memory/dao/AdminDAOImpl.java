package com.memory.dao;

import com.memory.pojo.Admin;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AdminDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class AdminDAOImpl implements AdminDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Admin admin) {
        hibernateTemplate.save(admin);
    }

    @Override
    public void update(Admin admin) {
        hibernateTemplate.update(admin);
    }

    @Override
    public void delete(Admin admin) {
        hibernateTemplate.delete(admin);
    }

    @Override
    public Admin get(int id) {
        return hibernateTemplate.get(Admin.class, id);
    }
}
