package com.memory.service;

import com.memory.pojo.Memory;

public interface MemoryService {

    public void delete(Memory memory);

    void add(Memory memory);

    Memory get(int id);

    void update(Memory memory);
}
