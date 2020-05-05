package com.memory.dao;

import com.memory.pojo.Tag;

import java.util.List;

/**
 * @InterfaceName TagDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/22
 * @Version 1.0
 **/
public interface TagDAO {
    void add(Tag tag);
    void update(Tag tag);
    void delete(Tag tag);
    Tag get(int id);
    Tag getByName(String name);
    List<Tag> getAll();

}
