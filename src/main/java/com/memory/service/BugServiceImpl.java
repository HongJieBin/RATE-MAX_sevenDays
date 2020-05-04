package com.memory.service;


import com.memory.dao.BugDAO;
import com.memory.pojo.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugServiceImpl implements BugService{

    @Autowired
    private BugDAO bugDAO;

    public void save(Bug bug){
        bugDAO.add(bug);
    }

    public Bug get(int id){return bugDAO.get(id);}
}
