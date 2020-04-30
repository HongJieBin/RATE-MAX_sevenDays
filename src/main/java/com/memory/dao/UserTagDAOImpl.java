package com.memory.dao;

import com.memory.pojo.User;
import com.memory.pojo.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserTagDAOImpl implements UserTagDAO{


    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<UserTag> getByUserId(Integer uid) {
        return (List< UserTag>)hibernateTemplate.find("form UserTag as u where u.user_id = ?",uid);
    }

    public UserTag get(Integer uid , Integer tid){
        List<UserTag> list = (List<UserTag>)hibernateTemplate.find("form UserTag as u where u.user_id = ? and u.tag_id = ?",
                new Object[]{uid,tid});
        if(list.size() == 0)
            return null;
        else
            return list.get(0);
    }

    @Override
    public void save(UserTag userTag) {
        hibernateTemplate.save(userTag);
    }

    @Override
    public void update(UserTag userTag) {
        hibernateTemplate.update(userTag);
    }
}
