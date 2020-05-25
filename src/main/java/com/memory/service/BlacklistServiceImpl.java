package com.memory.service;


import com.memory.dao.BlacklistDAO;
import com.memory.pojo.Blacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistServiceImpl implements BlacklistService{

    @Autowired
    private BlacklistDAO blacklistDAO;

    public void add(Blacklist blacklist){
        blacklistDAO.add(blacklist);
    }

    public void delete(Blacklist blacklist){
        blacklistDAO.delete(blacklist);
    }

    public Blacklist get(int uid,int addId){
        return blacklistDAO.get(uid,addId);
    }

    public List<Blacklist> getByUserId(int id){
        return blacklistDAO.getByUserID(id);
    }
}
