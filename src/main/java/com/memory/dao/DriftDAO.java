package com.memory.dao;

import com.memory.pojo.Bug;
import com.memory.pojo.Drift;

import java.util.List;

/**
 * @InterfaceName DriftDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface DriftDAO {
    void add(Drift drift);
    void update(Drift drift);
    void delete(Drift drift);
    Drift get(int id);
    List<Drift> getAll();
    void save(Drift drift)throws Exception;
}
