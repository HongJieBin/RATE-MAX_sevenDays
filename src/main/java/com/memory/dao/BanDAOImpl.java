
package com.memory.dao;

import com.memory.pojo.Ban;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public Ban getByUserId(int userId) {
        List<Ban> list = (List<Ban>) hibernateTemplate.find("from Ban as b where b.user.userId = ?" ,userId);
        if(list.size() != 0)
            return list.get(0);
        return null;
    }

    @Override
    public List<Ban> get(String param, int value) {
        String hql = "from Ban as u where u." + param + " = ?";
        return (List<Ban>) hibernateTemplate.find(hql, value);
    }
}
