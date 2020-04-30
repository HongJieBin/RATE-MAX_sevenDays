package com.memory.service;


import com.memory.dao.BugDAO;
import com.memory.pojo.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugService {

    @Autowired
    private BugDAO bugDAO;

    public void save(Bug bug){
        bugDAO.add(bug);
    }
}
