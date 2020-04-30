package com.memory.dao;

import com.memory.pojo.UserTag;

import java.util.List;

public interface UserTagDAO {

    List<UserTag> getByUserId(Integer uid);

    UserTag get(Integer uid , Integer tid);

    void save (UserTag userTag);

    void update(UserTag userTag);
}
