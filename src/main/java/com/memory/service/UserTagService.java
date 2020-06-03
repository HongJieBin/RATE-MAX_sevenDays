package com.memory.service;

import com.memory.pojo.UserTag;

import java.util.List;

public interface UserTagService {
    List<UserTag> getByUserId(int id);
    UserTag get(int userId,int tagId);
    void save(UserTag userTag);
    void update(UserTag userTag);
}
