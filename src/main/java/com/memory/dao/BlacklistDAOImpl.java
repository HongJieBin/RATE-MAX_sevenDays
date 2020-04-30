package com.memory.dao;

import com.memory.pojo.Blacklist;
import com.memory.pojo.User;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName BlacklistDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class BlacklistDAOImpl implements BlacklistDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Blacklist blacklist) {
        hibernateTemplate.save(blacklist);
    }

    @Override
    public void update(Blacklist blacklist) {
        hibernateTemplate.update(blacklist);
    }

    @Override
    public void delete(Blacklist blacklist) {
        hibernateTemplate.delete(blacklist);
    }

    @Override
    public List<Blacklist> getByUserID(int id) {
        String hql = "from Blacklist as b where b.userId = ?";
        return (List<Blacklist>) hibernateTemplate.find(hql, id);
    }

    @Override
    public List<Blacklist> getByAddedID(int id) {
        String hql = "from Blacklist as b where b.addedId = ?";
        return (List<Blacklist>) hibernateTemplate.find(hql, id);
    }
}
