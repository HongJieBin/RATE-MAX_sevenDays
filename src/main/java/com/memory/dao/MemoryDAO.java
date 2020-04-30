package com.memory.dao;

import com.memory.pojo.Memory;

/**
 * @InterfaceName MemoryDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/22
 * @Version 1.0
 **/
public interface MemoryDAO {
    void add(Memory memory);
    void update(Memory memory);
    void delete(Memory memory);
    Memory get(int id);
}
