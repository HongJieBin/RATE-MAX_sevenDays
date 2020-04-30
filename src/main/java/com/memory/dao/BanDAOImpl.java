package com.memory.dao;

import com.memory.pojo.Ban;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName BanDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class BanDAOImpl implements BanDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Ban ban) {
        hibernateTemplate.save(ban);
    }

    @Override
    public void update(Ban ban) {
        hibernateTemplate.update(ban);
    }

    @Override
    public void delete(Ban ban) {
        hibernateTemplate.delete(ban);
    }

    @Override
    public Ban get(int id) {
        return hibernateTemplate.get(Ban.class, id);
    }
}
