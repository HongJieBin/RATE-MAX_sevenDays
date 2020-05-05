package com.memory.service;

import com.memory.pojo.Bug;

public interface BugService {

    void save(Bug bug);
    Bug get(int id);
}
