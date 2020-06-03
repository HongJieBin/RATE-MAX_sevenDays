package com.memory.service;

import com.memory.dao.UserTagDAO;
import com.memory.pojo.UserTag;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class UserTagServiceImpl implements UserTagService{

    @Autowired
    private UserTagDAO userTagDAO;

    @Resource
    private HibernateTemplate hibernateTemplate;

    /**
     * 通过用户ID查找tag
     * @param uid
     * @return
     */
    public List<UserTag> getByUserId(int uid){
        return userTagDAO.getByUserId(uid);
    }

    public UserTag get(int uid,int tid){
        return userTagDAO.get(uid,tid);
    }

    public void save(UserTag userTag){
        userTagDAO.save(userTag);
    }

    public void update(UserTag userTag){
        userTagDAO.update(userTag);
    }

    @Override
    public List<UserTag> getMyUserTagList(int userId) {

        String hql1 = "select tagId from UserTag ut where ut.userId= ? ";
        List<Integer> TagIdList= (List<Integer>) hibernateTemplate.find(hql1,userId);
        Session session = Objects.requireNonNull(hibernateTemplate.getSessionFactory()).getCurrentSession();
        String hql3 = "from UserTag as ut where ut.tagId in (:list)";
        if(TagIdList.isEmpty()){
            return null;
        }

        return (List<UserTag>)session.createQuery(hql3).setParameterList("list",TagIdList).list();
    }
}
