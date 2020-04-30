package com.memory.dao;

import com.memory.pojo.Ban;

/**
 * @InterfaceName BanDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface BanDAO {
    void add(Ban ban);
    void update(Ban ban);
    void delete(Ban ban);
    Ban get(int id);
}
