package com.memory.service;

import com.memory.dao.MemoryDAO;
import com.memory.pojo.Memory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryServiceImpl implements MemoryService {

    @Autowired
    private MemoryDAO memoryDAO;

    @Override
    public void delete(Memory memory) {
        memoryDAO.delete(memory);
    }

    @Override
    public void add(Memory memory) {
        memoryDAO.add(memory);
    }

    @Override
    public Memory get(int id) {
        return memoryDAO.get(id);
    }

    public void update(Memory memory){
        memoryDAO.update(memory);
    }
}
