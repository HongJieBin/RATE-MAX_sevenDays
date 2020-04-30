package com.memory.dao;

import com.memory.pojo.Blacklist;

import java.util.List;

/**
 * @InterfaceName BlacklistDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface BlacklistDAO {
    void add(Blacklist blacklist);
    void update(Blacklist blacklist);
    void delete(Blacklist blacklist);
    List<Blacklist> getByUserID(int id);
    List<Blacklist> getByAddedID(int id);

}
