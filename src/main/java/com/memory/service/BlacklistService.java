package com.memory.service;


import com.memory.dao.BlacklistDAO;
import com.memory.pojo.Blacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistService {

    @Autowired
    private BlacklistDAO blacklistDAO;

    public void add(Blacklist blacklist){
        blacklistDAO.add(blacklist);
    }

    public void delete(Blacklist blacklist){
        blacklistDAO.delete(blacklist);
    }

    public Blacklist get(Integer uid,Integer addId){
        return blacklistDAO.get(uid,addId);
    }
}
