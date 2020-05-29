package com.memory.service;

import com.memory.pojo.Memory;

import java.util.List;

public interface MemoryService {

    public void delete(Memory memory);

    void add(Memory memory);

    Memory get(int id);

    void update(Memory memory);

    List<Memory> getAllOfUser(int userId);
}
