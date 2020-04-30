package com.memory.service;

import com.memory.dao.UserTagDAO;
import com.memory.pojo.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class UserTagService {

    @Autowired
    private UserTagDAO userTagDAO;

    /**
     * 通过用户ID查找tag
     * @param uid
     * @return
     */
    public List<UserTag> getByUserId(Integer uid){
        return userTagDAO.getByUserId(uid);
    }

    public UserTag get(Integer uid,Integer tid){
        return userTagDAO.get(uid,tid);
    }

    public void save(UserTag userTag){
        userTagDAO.save(userTag);
    }

    public void update(UserTag userTag){
        userTagDAO.update(userTag);
    }
}
